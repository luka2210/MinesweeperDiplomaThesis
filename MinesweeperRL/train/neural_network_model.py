from tensorflow.keras.layers import Reshape, Conv2D, Flatten, Dense
from tensorflow.keras.models import Sequential


def get_model(input_dims, num_actions, conv_units, dense_units):
    model = Sequential([
                Reshape(target_shape=(9, 9, 1), input_shape=(1, 9, 9, 1)),
                Conv2D(conv_units, (3, 3), activation='relu', padding='same', input_shape=input_dims),
                Conv2D(conv_units, (3, 3), activation='relu', padding='same'),
                Conv2D(conv_units, (3, 3), activation='relu', padding='same'),
                Conv2D(conv_units, (3, 3), activation='relu', padding='same'),
                Flatten(),
                Dense(dense_units, activation='relu'),
                Dense(dense_units, activation='relu'),
                Dense(num_actions, activation='linear')])

    # model.compile(optimizer=tf.keras.optimizers.Adam(lr=learn_rate, epsilon=1e-4), loss='mse')
    model.summary()
    return model
