package gui;

import javax.swing.JPanel;

import event.listener.GameBoardEventListener;

public class GameBoard {
	
	public static final int FIELD_HEIGHT = 25, FIELD_WIDTH = 25;
	
	private int numRows, numColumns, numMines;
	
	private GameBoardEventListener listener;
	
	private GameField[][] fields;
	
	private JPanel panel;
	
	
	public GameBoard(int numRows, int numColumns, int numMines, 
			GameBoardEventListener listener) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		this.listener = listener;
		fields = initFields();
		initialize();
	}
	
	public GameField[][] initFields() {	
		GameField[][] fields = new GameField[numRows][numColumns];
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				fields[i][j] = new GameField(i, j, FIELD_HEIGHT, FIELD_WIDTH, listener);
		
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

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	public GameField[][] getFields() {
		return fields;
	}

	public void setFields(GameField[][] fields) {
		this.fields = fields;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
