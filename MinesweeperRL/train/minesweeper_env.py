import numpy as np
import random

from rl.core import Env


class MinesweeperEnv(Env):

    def __init__(self, num_rows, num_cols, num_mines):
        assert (num_rows > 0 and num_cols > 0 and num_mines > 0)
        assert (num_rows * num_cols - 9 >= num_mines)

        self.rewards = {'win': 1, 'lose': -1, 'progress': 0.3, 'guess': -0.3, 'no_progress': -0.3}

        self.num_rows, self.num_cols, self.num_mines = num_rows, num_cols, num_mines

        self.num_actions = self.num_rows * self.num_cols
        self.num_clicks, self.num_progress, self.num_wins = 0, 0, 0

        self.board, self.board_state = None, self.init_board_state()

    def get_neighbors(self, board, row, col):
        neighbors = []

        for i in range(row - 1, row + 2):
            for j in range(col - 1, col + 2):
                if (0 <= i < self.num_rows and
                        0 <= j < self.num_cols and
                        (i != row or j != col)):
                    neighbors.append(board[i, j])

        return np.array(neighbors)

    def index_to_coord(self, index):
        return index // self.num_cols, index % self.num_cols

    def coord_to_index(self, row, col):
        return row * self.num_cols + col

    def init_board(self, row, col):
        board = np.zeros((self.num_rows, self.num_cols), dtype='object')

        fields_index = [i for i in range(self.num_actions)]
        for i in range(row - 1, row + 2):
            for j in range(col - 1, col + 2):
                if (0 <= i < self.num_rows and
                        0 <= j < self.num_cols and
                        (i != row or j != col)):
                    fields_index.remove(self.coord_to_index(i, j))

        mines_index = random.sample(fields_index, self.num_mines)
        for mine_index in mines_index:
            board[self.index_to_coord(mine_index)] = 'B'

        for i in range(self.num_rows):
            for j in range(self.num_cols):
                if board[i, j] != 'B':
                    board[i, j] = np.sum(self.get_neighbors(board, i, j) == 'B')
        return board

    def init_board_state(self):
        return np.full((self.num_rows, self.num_cols), 'U', dtype='object')

    def get_board_state_img(self):
        board_state_img = np.reshape(self.board_state, (self.num_rows, self.num_cols, 1)).astype(object)

        board_state_img[board_state_img == 'U'] = -1
        board_state_img[board_state_img == 'B'] = -2

        board_state_img = board_state_img.astype(np.int8) / 8
        board_state_img = board_state_img.astype(np.float16)

        return board_state_img

    def reveal_neighbors(self, row, col):
        for i in range(row - 1, row + 2):
            for j in range(col - 1, col + 2):
                if (0 <= i < self.num_rows and
                        0 <= j < self.num_cols and
                        self.board_state[i, j] == 'U'):
                    val = self.board[i, j]
                    self.board_state[i, j] = val
                    if val == 0:
                        self.reveal_neighbors(i, j)

    def click(self, row, col):
        if self.num_clicks == 0:
            self.board = self.init_board(row, col)

        val = self.board[row, col]
        self.board_state[row, col] = val

        if val == 0:
            self.reveal_neighbors(row, col)
        self.num_clicks += 1

        return val

    def get_info(self):
        return {"num_clicks": self.num_clicks,
                "num_progress": self.num_progress,
                "num_wins": self.num_wins}

    def reset(self):
        self.num_clicks, self.num_progress = 0, 0
        self.board_state = self.init_board_state()
        return self.get_board_state_img()

    def step(self, action):
        row, col = self.index_to_coord(action)
        done = False

        prev_val = self.board_state[row, col]
        new_val = self.click(row, col)

        # If agent clicked on an already open field
        if prev_val != 'U':
            reward = self.rewards['no_progress']

        # If agent clicked on a mine
        elif new_val == 'B':
            reward = self.rewards['lose']
            done = True

        # If all non-mine fields are open
        elif np.count_nonzero(self.board_state == 'U') == self.num_mines:
            reward = self.rewards['win']
            done = True
            self.num_progress, self.num_wins = self.num_progress + 1, self.num_wins + 1

        # If progress was made
        else:
            # Negative reward if agent clicked on a field that's surrounded by uncovered fields
            counter = 0
            for i in range(row - 1, row + 2):
                for j in range(col - 1, col + 2):
                    if (0 <= i < self.num_rows and
                            0 <= j < self.num_cols and
                            self.board_state[i, j] == 'U'):
                        counter += 1

            if counter == 8 and self.num_clicks > 1:
                reward = self.rewards['guess']

            # Positive reward otherwise
            else:
                reward = self.rewards['progress']
                self.num_progress += 1

        # self.print_board_state()
        return self.get_board_state_img(), reward, done, self.get_info()

    def print_board_state(self):
        print(self.board_state)
