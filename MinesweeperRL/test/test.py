import argparse
from tqdm import tqdm
from tensorflow.keras.models import load_model
from minesweeper_java_env_agent import *

def main():
    my_model = load_model('trained_models/beginner_model.h5')
    agent = MinesweeperAgentWeb(my_model)

    done = False
    while not done:
        current_state = agent.state
        action = agent.get_action(current_state)

        new_state, done = agent.step(action)

if __name__ == "__main__":
    main()
