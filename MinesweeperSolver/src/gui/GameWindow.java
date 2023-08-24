package gui;

import javax.swing.JFrame;

public class GameWindow {

	private int numRows, numColumns, numMines;
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public GameWindow(int numRows, int numColumns, int numMines) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
