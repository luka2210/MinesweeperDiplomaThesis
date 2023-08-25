package gui;

import javax.swing.JFrame;

public class GameWindow {

	private int numRows, numColumns, numMines;
	
	private JFrame frame;
	private int framePosX, framePosY;

	/**
	 * Create the application.
	 */
	public GameWindow(int numRows, int numColumns, int numMines, int framePosX, int framePosY) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		this.framePosX = framePosX;
		this.framePosY = framePosY;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(framePosX, framePosY, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
