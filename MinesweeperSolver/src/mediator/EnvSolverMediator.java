package mediator;

import gui.GameBoard;
import gui.GameTimer;
import gui.MineCounter;
import solver.BacktrackingSolver;

public class EnvSolverMediator {
	
	private GameBoard gameBoard;
	private MineCounter mineCounter;
	private BacktrackingSolver solver;
	
	public EnvSolverMediator(GameBoard gameBoard, MineCounter mineCounter) {
		super();
		this.gameBoard = gameBoard;
		this.mineCounter = mineCounter;
		this.solver = new BacktrackingSolver(gameBoard.getNumRows(), gameBoard.getNumColumns(), 
					gameBoard.getNumMines());
	}
	
	
}
