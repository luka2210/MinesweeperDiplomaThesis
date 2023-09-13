from rl.agents import DQNAgent
from rl.policy import EpsGreedyQPolicy
from rl.memory import SequentialMemory

from tensorflow.keras.optimizers import Adam


def get_agent(model, num_actions):
    agent = DQNAgent(
        model=model,
        policy=EpsGreedyQPolicy(eps=0.1),
        enable_double_dqn=True,
        nb_actions=num_actions,
        memory=SequentialMemory(limit=100000, window_length=1),
        gamma=0.99,
        batch_size=64,
        nb_steps_warmup=1000,
        target_model_update=5
    )
    agent.compile(Adam(lr=0.001, epsilon=1e-4), metrics=[])

    return agent
