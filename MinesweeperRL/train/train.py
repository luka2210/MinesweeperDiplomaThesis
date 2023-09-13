from minesweeper_env import MinesweeperEnv
from neural_network_model import get_model
from dqnagent import get_agent

from tensorflow.compat.v1 import ConfigProto
from tensorflow.compat.v1 import InteractiveSession

config = ConfigProto()
config.gpu_options.allow_growth = True
session = InteractiveSession(config=config)


def train():
    env = MinesweeperEnv(9, 9, 10)

    input_dims = env.get_board_state_img().shape
    num_actions = env.num_actions

    model = get_model(input_dims, num_actions, conv_units=64, dense_units=512)
    agent = get_agent(model, num_actions)

    agent.fit(env, nb_steps=10_000_000, visualize=False, verbose=1)

    agent.save_weights("trained_nns/beginner_model.h5", overwrite=False)

    agent.test(env, nb_episodes=10_000, visualize=False, verbose=1)
    

if __name__ == '__main__':
    train()