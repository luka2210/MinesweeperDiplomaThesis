package event.listener;

import gui.GameBoard;

public interface GameBoardEventListener extends GameFieldEventListener {
	void setGameBoard(GameBoard gameBoard);
}
