package gui;

import javax.swing.JFrame;

import event.handler.GameEventHandler;

public class GameWindow {
	public static final int UPPER_PANEL_HEIGHT = 60;
	public static final int OS_SPECIFIC_HEIGHT_OFFSET = 28;

	private int framePosX, framePosY;
	private int frameHeight, frameWidth;
	
	private GameBoard gameBoard;
	private MineCounter mineCounter;
	private GameTimer timer;
	private SmileyButton smiley;
	
	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public GameWindow(int numRows, int numColumns, int numMines, int framePosX, int framePosY) {
		this.framePosX = framePosX;
		this.framePosY = framePosY;
		this.frameHeight = GameBoard.FIELD_HEIGHT * numRows + UPPER_PANEL_HEIGHT + OS_SPECIFIC_HEIGHT_OFFSET;
		this.frameWidth = GameBoard.FIELD_WIDTH * numColumns;
		
		GameEventHandler eventHandler = new GameEventHandler();
		
		this.mineCounter = new MineCounter(numMines);
		this.timer = new GameTimer(frameWidth);
		this.smiley = new SmileyButton(frameWidth, eventHandler);
		this.gameBoard = new GameBoard(numRows, numColumns, numMines, eventHandler);
		
		eventHandler.setMineCounter(mineCounter);
		eventHandler.setGameTimer(timer);
		eventHandler.setSmiley(smiley);
		eventHandler.setGameBoard(gameBoard);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(framePosX, framePosY, frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		frame.add(gameBoard.getPanel());
		frame.add(mineCounter.getPanel());
		frame.add(timer.getPanel());
		frame.add(smiley.getButton());
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
