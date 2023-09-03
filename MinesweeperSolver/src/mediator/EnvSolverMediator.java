package mediator;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

import autoclicker.AutoClicker;
import gui.GameBoard;
import gui.GameField;
import gui.MineCounter;
import solver.Action;
import solver.BacktrackingSolver;
import solver.Click;
import solver.Field;

public class EnvSolverMediator {
	
	public static final int DELAY = 100;
	
	private GameBoard gameBoard;
	private MineCounter mineCounter;
	private BacktrackingSolver solver;
	
	private int numRows, numColumns, numMines;
	
	private Timer timer;
	
	public EnvSolverMediator(GameBoard gameBoard, MineCounter mineCounter) {
		super();
		this.gameBoard = gameBoard;
		this.mineCounter = mineCounter;
		
		this.numRows = gameBoard.getNumRows();
		this.numColumns = gameBoard.getNumColumns();
		this.numMines = gameBoard.getNumMines();
		this.solver = new BacktrackingSolver(numRows, numColumns, numMines);
		
		this.timer = new Timer();
	}
	
	public synchronized void solve() {
		boolean keepSolving = step();
		if (keepSolving)
			timer.schedule(getTimerTask(), DELAY);
	}
	
	private synchronized boolean step() {
		if (gameBoard.isGameOver() || gameBoard.isGameWon()) 
			return false;
			
		Field[][] fields = InputConverter.getFields(gameBoard.getFields(), numRows, numColumns);
			
		Action action = solver.getAction(fields, mineCounter.minesLeft(), gameBoard.isFirstClick());
			
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
		boolean clicked = AutoClicker.click(fieldLocation, GameBoard.FIELD_HEIGHT / 2, GameBoard.FIELD_WIDTH / 2, inputEvent);
		
		return clicked;
	}
	
	private TimerTask getTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				solve();
			}	
		};
	}
}
