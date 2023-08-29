package mediator;

import java.awt.Point;
import java.awt.event.InputEvent;

import gui.GameBoard;
import gui.GameField;
import gui.MineCounter;
import solver.Action;
import solver.BacktrackingSolver;
import solver.Click;
import solver.Field;

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
	
	public void solve() {
		int numRows = gameBoard.getNumRows();
		int numColumns = gameBoard.getNumColumns();
		int numMines = gameBoard.getNumMines();
		BacktrackingSolver solver = new BacktrackingSolver(numRows, numColumns, numMines);
		while (!gameBoard.isGameOver() && gameBoard.isGameWon()) {
			Field[][] fields = InputConverter.getFields(gameBoard.getFields(), numRows, numColumns);
			
			Action action = solver.getAction(fields, numMines, false);
			
			Click click = action.getClick();
			int inputEvent;
			switch (click) {
			case LEFT:
				inputEvent = InputEvent.BUTTON1_DOWN_MASK;
				break;
			case MIDDLE:
				inputEvent = InputEvent.BUTTON2_DOWN_MASK;
				break;
			case RIGHT:
				inputEvent = InputEvent.BUTTON3_DOWN_MASK;
				break;
			default:
				inputEvent = InputEvent.BUTTON1_DOWN_MASK;
				break;
			}
			
			GameField targetField = gameBoard.getFields()[action.getRow()][action.getCol()];
			Point fieldLocation = targetField.getFieldFrame().getLocationOnScreen();
			autoclicker
		}
	}
}
