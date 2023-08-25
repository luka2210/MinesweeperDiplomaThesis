package gui;

import javax.swing.JPanel;

import gamelogic.Board;

public class GameBoard {
	
	public static final int FIELD_HEIGHT = 20, FIELD_WIDTH = 20;
	
	private int numRows, numColumns, numMines;
	
	private GameField[][] fields;
	
	private JPanel panel;
	
	public GameBoard(int numRows, int numColumns, int numMines) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		fields = initFields();
		initialize();
	}
	
	public GameField[][] initFields() {	
		int[][] board = Board.initBoard(numRows, numColumns, numMines);
		
		GameField[][] fields = new GameField[numRows][numColumns];
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				fields[i][j] = new GameField(i, j, board[i][j] == -1, board[i][j], FIELD_HEIGHT, FIELD_WIDTH);
		
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
}
