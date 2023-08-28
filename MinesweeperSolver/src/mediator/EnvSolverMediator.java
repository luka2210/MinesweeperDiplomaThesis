package mediator;

import gui.GameBoard;
import gui.GameTimer;
import gui.MineCounter;

public class EnvSolverMediator {
	
	private GameBoard gameBoard;
	private GameTimer gameTimer;
	private MineCounter mineCounter;
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	public GameTimer getGameTimer() {
		return gameTimer;
	}
	public void setGameTimer(GameTimer gameTimer) {
		this.gameTimer = gameTimer;
	}
	public MineCounter getMineCounter() {
		return mineCounter;
	}
	public void setMineCounter(MineCounter mineCounter) {
		this.mineCounter = mineCounter;
	}
	
	
}
