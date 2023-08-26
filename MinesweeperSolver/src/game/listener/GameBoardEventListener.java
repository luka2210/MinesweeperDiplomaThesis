package game.listener;

import gui.GameBoard;

public interface GameBoardEventListener extends GameFieldEventListener {
	void setGameBoard(GameBoard gameBoard);
}
