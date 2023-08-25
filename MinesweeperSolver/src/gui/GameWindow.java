package gui;

import javax.swing.JFrame;

public class GameWindow {
	public static final int UPPER_PANEL_HEIGHT = 60;
	public static final int OS_SPECIFIC_HEIGHT_OFFSET = 28;

	private int numRows, numColumns, numMines;

	private int framePosX, framePosY;
	private int frameHeight, frameWidth;
	
	private GameBoard gameBoard;
	private MineCounter mineCounter;
	
	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public GameWindow(int numRows, int numColumns, int numMines, int framePosX, int framePosY) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		this.framePosX = framePosX;
		this.framePosY = framePosY;
		this.frameHeight = GameBoard.FIELD_HEIGHT * numRows + UPPER_PANEL_HEIGHT + OS_SPECIFIC_HEIGHT_OFFSET;
		this.frameWidth = GameBoard.FIELD_WIDTH * numColumns;
		this.mineCounter = new MineCounter(numMines);
		this.gameBoard = new GameBoard(this.numRows, this.numColumns, this.numMines, mineCounter);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(framePosX, framePosY, frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.add(gameBoard.getPanel());
		frame.add(mineCounter.getPanel());
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
