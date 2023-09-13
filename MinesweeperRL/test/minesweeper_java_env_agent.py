import os
import numpy as np
import pyautogui as pg

ROOT = os.getcwd()
IMGS = f'{ROOT}/test/pics'

EPSILON = 0.01

CONFIDENCES = {
    'unsolved': 0.99,
    'zero': 0.99,
    'one': 0.99,
    'two': 0.99,
    'three': 0.99,
    'four': 0.99,
    'five': 0.99,
    'six': 0.99,
    'seven': 0.99,
    'eight': 0.99
}

TILES = {
    'U': 'unsolved',
    '0': 'zero',
    '1': 'one',
    '2': 'two',
    '3': 'three',
    '4': 'four'
}

TILES2 = {
    '5': 'five',
    '6': 'six',
    '7': 'seven',
    '8': 'eight',
}

SMILEY_CONFIDENCES = {
    'game_over': 0.95,
    'game_won': 0.95
}

BOARD_CONFIDENCES = {
    'beginner': 0.95,
    'intermediate': 0.95,
    'expert': 0.95
}

class MinesweeperAgentWeb(object):
    def __init__(self, model):
        self.mode, self.loc, self.dims = self.get_loc()
        self.nrows, self.ncols = self.dims[0], self.dims[1]
        self.ntiles = self.dims[2]
        self.board = self.get_board(self.loc)
        self.state = self.get_state(self.board)

        self.epsilon = EPSILON
        self.model = model

    def get_loc(self):
        '''
        obtain mode, screen coordinates and dimensions for Minesweeper board
        '''

        modes = {'beginner':(9,9,81), 'intermediate':(16,16,256), 'expert':(16,30,480)}
        boards = {mode: pg.locateOnScreen(f'{IMGS}/{mode}.png', grayscale=True,
                                          confidence=BOARD_CONFIDENCES[mode]) for mode in modes.keys()}

        assert boards != {'beginner':None, 'intermediate':None, 'expert':None},\
            'Minesweeper board not detected on screen'

        for mode in boards.keys():
            if boards[mode] != None:
                diff = mode
                loc = boards[mode]
                dims = modes[mode]

        return diff, loc, dims

    def get_tiles(self, tile, bbox):
        '''
        Gets all locations of a given tile.
        Different confidence values are needed to correctly find different tiles with grayscale=True
        '''
        conf = CONFIDENCES[tile]
        tiles = list(pg.locateAllOnScreen(f'{IMGS}/{tile}.png', region=bbox, grayscale=True, confidence=conf))

        return tiles

    def get_board(self, bbox):
        '''
        Gets the state of the board as a dictionary of coordinates and values,
        ordered from left to right, top to bottom
        '''

        all_tiles = [[t, self.get_tiles(TILES[t], self.loc)] for t in TILES]

        # for speedup; look for higher tiles only if n of lower tiles < total ----
        count=0
        for value, coords in all_tiles:
            count += len(coords)

        if count < self.ntiles:
            higher_tiles = [[t, self.get_tiles(TILES2[t], self.loc)] for t in TILES2]
            all_tiles += higher_tiles
        # ----

        tiles = []
        for value, coords in all_tiles:
            for coord in coords:
                tiles.append({'coord': (coord[0], coord[1]), 'value': value})

        tiles = sorted(tiles, key=lambda x: (x['coord'][1], x['coord'][0]))

        i=0
        for x in range(self.nrows):
            for y in range(self.ncols):
                tiles[i]['index'] = (y, x)
                i+=1

        return tiles

    def get_state(self, board):
        '''
        Gets the numeric image representation state of the board.
        This is what will be the input for the DQN.
        '''

        state_im = [t['value'] for t in board]
        state_im = np.reshape(state_im, (self.nrows, self.ncols, 1)).astype(object)

        state_im[state_im=='U'] = -1
        state_im[state_im=='B'] = -2

        state_im = state_im.astype(np.int8) / 8
        state_im = state_im.astype(np.float16)

        return state_im

    def get_action(self, state):
        board = self.state.reshape(1, self.ntiles)
        unsolved = [i for i, x in enumerate(board[0]) if x==-0.125]

        rand = np.random.random() # random value b/w 0 & 1

        if rand < self.epsilon: # random move (explore)
            move = np.random.choice(unsolved)
        else:
            moves = self.model.predict(np.reshape(self.state, (1, self.nrows, self.ncols, 1)))
            moves[board!=-0.125] = np.min(moves)
            move = np.argmax(moves)

        return move

    def check_if_done(self):
        game_over = pg.locateOnScreen(f'{IMGS}/game_over.png',
                                         confidence=SMILEY_CONFIDENCES['game_over'], region=self.loc, grayscale=True)
        game_won = pg.locateOnScreen(f'{IMGS}/game_won.png',
                                         confidence=SMILEY_CONFIDENCES['game_won'], region=self.loc, grayscale=True)

        return game_over is not None or game_won is not None

    def step(self, action_index):
        pg.click(self.board[action_index]['coord'])

        done = self.check_if_done()

        if not done:
            self.board = self.get_board(self.loc)
            self.state = self.get_state(self.board)

        return self.state, done
