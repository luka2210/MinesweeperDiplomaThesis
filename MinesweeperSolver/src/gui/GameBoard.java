package gui;

import javax.swing.JPanel;

import eventlistener.GameFieldEventListener;
import eventlistener.MineCounterEventListener;
import gameutil.Board;
import imageutil.ImageLoader;


public class GameBoard implements GameFieldEventListener {
	
	public static final int FIELD_HEIGHT = 20, FIELD_WIDTH = 20;
	
	private int numRows, numColumns, numMines;
	
	private GameField[][] fields;
	
	private JPanel panel;
	
	private boolean firstClick, gameOver, gameWon;
	
	private MineCounterEventListener cntListener;
	
	public GameBoard(int numRows, int numColumns, int numMines, MineCounterEventListener cntListener) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		this.firstClick = true;
		this.gameOver = false;
		this.gameWon = false;
		this.cntListener = cntListener;
		fields = initFields();
		initialize();
	}
	
	public GameField[][] initFields() {	
		GameField[][] fields = new GameField[numRows][numColumns];
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				fields[i][j] = new GameField(i, j, FIELD_HEIGHT, FIELD_WIDTH, this);
		
		return fields;
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setBounds(0, 60, FIELD_WIDTH * numColumns, FIELD_HEIGHT * numRows);
		panel.setLayout(null);
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				panel.add(fields[i][j].getFieldFrame());
	}

	public JPanel getPanel() {
		return panel;
	}
	
	@Override
	public void leftClick(int row, int col) {
		GameField field = fields[row][col];
		
		if (field.isOpened() || field.isMarked() || gameOver)
			return;
		
		if (firstClick) {
			assignMines(row, col);
			firstClick = false;
		}
		
		field.open();
		
		gameOver = field.isMine();
		if (gameOver) {
			return;
		}
		
		if (field.getNgbMines() == 0)
			openAllNeighbors(row, col);
	}
	
	private void assignMines(int row, int col) {
		int[][] board = Board.initBoard(numRows, numColumns, numMines, row, col);
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				fields[i][j].setMine(board[i][j] == -1);
				fields[i][j].setNgbMines(board[i][j]);
			}
	}
	
	private void openAllNeighbors(int row, int col) {
		for (int x = row - 1; x < row + 2; x++)
			for (int y = col - 1; y < col + 2; y++)
				if (x >= 0 && x < numRows && y >= 0 && y < numColumns && !fields[x][y].isOpened())
					leftClick(x, y);
	}
	
	@Override
	public void rightClick(int row, int col) {
		GameField field = fields[row][col];
		
		if (field.isOpened() || gameOver)
			return;
		
		if (!field.isMarked() && cntListener.minesLeft() > 0) {
			cntListener.markMine();
			field.setMarked(true);
		}
		else if (field.isMarked()) {
			cntListener.unmarkMine();
			field.setMarked(false);
		}
	}

	@Override
	public void middleClick(int row, int col) {
		GameField field = fields[row][col];
		
		if (!field.isOpened() || field.isMarked() || gameOver)
			return;
		
		int counter = 0;
		for (int x = row - 1; x < row + 2; x++)
			for (int y = col - 1; y < col + 2; y++)
				if (x >= 0 && x < numRows && y >= 0 && y < numColumns && fields[x][y].isMarked())
					counter++;
		
		if (field.getNgbMines() == counter)
			openAllNeighbors(row, col);
	}

	@Override
	public void leftClickPressed(int row, int col) {
		GameField field = fields[row][col];
		if (field.isOpened() || field.isMarked() || gameOver) 
			return;
		field.getFieldFrame().setIcon(ImageLoader.FIELDS[0]);
	}

	@Override
	public void leftClickReleased(int row, int col) {
		GameField field = fields[row][col];
		if (field.isOpened() || field.isMarked() || gameOver) 
			return;
		field.getFieldFrame().setIcon(ImageLoader.FIELD);
	}
}
