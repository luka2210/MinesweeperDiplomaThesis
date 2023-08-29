package event.handler;

import event.listener.GameBoardEventListener;
import event.listener.SmileyEventListener;
import gui.GameBoard;
import gui.GameField;
import gui.GameTimer;
import gui.MineCounter;
import gui.SmileyButton;
import image.ImageLoader;
import mediator.EnvSolverMediator;
import util.BoardInitializer;

public class GameEventHandler implements GameBoardEventListener, SmileyEventListener{
	
	private GameBoard gameBoard;
	private GameTimer gameTimer;
	private MineCounter mineCounter;
	private SmileyButton smiley;
	
	private int numRows, numColumns, numMines;
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	@Override
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.numRows = gameBoard.getNumRows();
		this.numColumns = gameBoard.getNumColumns();
		this.numMines = gameBoard.getNumMines();
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
	public SmileyButton getSmiley() {
		return smiley;
	}
	@Override
	public void setSmiley(SmileyButton smiley) {
		this.smiley = smiley;
	}
	
	private void revealMines(GameField[][] fields) {
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isMarked() && !fields[i][j].isMine())
					fields[i][j].getFieldFrame().setIcon(ImageLoader.FIELD_MARKED_WRONG);
				else if (fields[i][j].isOpened() && fields[i][j].isMine())
					fields[i][j].getFieldFrame().setIcon(ImageLoader.FIELD_MINE_CLICKED);
				else if (!fields[i][j].isMarked() && fields[i][j].isMine())
					if (gameBoard.isGameOver())
						fields[i][j].getFieldFrame().setIcon(ImageLoader.FIELD_MINE);
					else if (gameBoard.isGameWon())
						fields[i][j].getFieldFrame().setIcon(ImageLoader.FIELD_MARKED);
	}
	
	private boolean checkGameWon(GameField[][] fields) {
		int counter = 0;
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isOpened())
					counter++;
		return counter == numRows * numColumns - numMines && !gameBoard.isGameOver();
	}
	
	private void assignMines(GameField[][] fields, int row, int col) {
		int[][] board = BoardInitializer.initBoard(numRows, numColumns, numMines, row, col);
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				fields[i][j].setMine(board[i][j] == -1);
				fields[i][j].setNgbMines(board[i][j]);
			}
	}
	
	private void openAllNeighbors(GameField[][] fields, int row, int col) {
		for (int x = row - 1; x < row + 2; x++)
			for (int y = col - 1; y < col + 2; y++)
				if (x >= 0 && x < numRows && y >= 0 && y < numColumns && !fields[x][y].isOpened())
					fieldLeftClick(x, y);
	}
	
	private void setSmileyIcon() {
		if (gameBoard.isGameOver())
			smiley.getButton().setIcon(ImageLoader.SMILEY_DEAD);
		else if (gameBoard.isGameWon())
			smiley.getButton().setIcon(ImageLoader.SMILEY_WON);
	}
	
	@Override
	public void fieldLeftClick(int row, int col) {
		GameField[][] fields = gameBoard.getFields();
		GameField field = fields[row][col];
		
		if (field.isOpened() || field.isMarked() 
				|| gameBoard.isGameOver() || gameBoard.isGameWon())
			return;
		
		if (gameBoard.isFirstClick()) {
			assignMines(fields, row, col);
			gameTimer.start();
			gameBoard.setFirstClick(false);
		}
		
		field.open();
		
		gameBoard.setGameOver(field.isMine());
		gameBoard.setGameWon(checkGameWon(fields));
		
		if (gameBoard.isGameOver() || gameBoard.isGameWon()) {
			revealMines(fields);
			setSmileyIcon();
			gameTimer.stop();
		}
		
		if (field.getNgbMines() == 0)
			openAllNeighbors(fields, row, col);
	}
	
	@Override
	public void fieldRightClick(int row, int col) {
		GameField field = gameBoard.getFields()[row][col];
		
		if (field.isOpened() || gameBoard.isGameOver() || gameBoard.isGameWon())
			return;
		
		if (!field.isMarked() && mineCounter.minesLeft() > 0) {
			mineCounter.markMine();
			field.setMarked(true);
		}
		else if (field.isMarked()) {
			mineCounter.unmarkMine();
			field.setMarked(false);
		}
	}
	
	@Override
	public void fieldMiddleClick(int row, int col) {
		GameField[][] fields = gameBoard.getFields();
		GameField field = fields[row][col];
		
		if (!field.isOpened() || field.isMarked() || gameBoard.isGameOver() || gameBoard.isGameWon())
			return;
		
		int counter = 0;
		for (int x = row - 1; x < row + 2; x++)
			for (int y = col - 1; y < col + 2; y++)
				if (x >= 0 && x < numRows && y >= 0 && y < numColumns && fields[x][y].isMarked())
					counter++;
		
		if (field.getNgbMines() == counter)
			openAllNeighbors(fields, row, col);
		
		
	}
	
	@Override
	public void fieldMousePressed(int row, int col) {
		GameField field = gameBoard.getFields()[row][col];
		
		if (field.isOpened() || field.isMarked() || gameBoard.isGameOver() || gameBoard.isGameWon()) 
			return;
		field.getFieldFrame().setIcon(ImageLoader.FIELDS[0]);
		
		smiley.getButton().setIcon(ImageLoader.SMILEY_SURPRISED);
	}

	@Override
	public void fieldMouseReleased(int row, int col) {
		GameField field = gameBoard.getFields()[row][col];
		
		if (field.isOpened() || field.isMarked() || gameBoard.isGameOver() || gameBoard.isGameWon()) 
			return;
		
		field.getFieldFrame().setIcon(ImageLoader.FIELD);
		
		smiley.getButton().setIcon(ImageLoader.SMILEY);
	}
	@Override
	public void smileyLeftMouseClicked() {
		reset();
	}
	@Override
	public void smileyLeftMousePressed() {
		smiley.getButton().setIcon(ImageLoader.SMILEY_PRESSED);
	}
	@Override
	public void smileyLeftMouseReleased() {
		if (gameBoard.isGameOver())
			smiley.getButton().setIcon(ImageLoader.SMILEY_DEAD);
		else if (gameBoard.isGameWon())
			smiley.getButton().setIcon(ImageLoader.SMILEY_WON);
		else
			smiley.getButton().setIcon(ImageLoader.SMILEY);
	}
	
	private void reset() {
		gameBoard.setGameOver(false);
		gameBoard.setGameWon(false); 
		gameBoard.setFirstClick(true);
		gameBoard.reset();
		smiley.reset();
		mineCounter.reset();
		gameTimer.stop();
		gameTimer.resetTime();
	}
	@Override
	public void smileyRightMouseClicked() {
		// TODO Auto-generated method stub
		EnvSolverMediator mediator = new EnvSolverMediator(gameBoard, mineCounter);
		mediator.solve();
	}
}
