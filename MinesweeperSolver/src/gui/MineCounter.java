package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import image.ImageLoader;

public class MineCounter {
	
	private static int DIGIT_HEIGHT = GameTimer.DIGIT_HEIGHT, DIGIT_WIDTH = GameTimer.DIGIT_WIDTH;
	private static int TOP_MARGIN = GameTimer.TOP_MARGIN;
	
	private JLabel digit1, digit2, digit3;
	private JPanel panel;
	
	private int totalMines, markedMines;
	
	public MineCounter(int totalMines) {
		this.totalMines = totalMines;
		this.markedMines = 0;
		initialize();
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setBounds(0, TOP_MARGIN, DIGIT_WIDTH * 3, DIGIT_HEIGHT);
		panel.setLayout(null);
		
		digit1 = new JLabel();
		digit1.setBounds(0, 0, DIGIT_WIDTH, DIGIT_HEIGHT);
		panel.add(digit1);
		
		digit2 = new JLabel();
		digit2.setBounds(DIGIT_WIDTH, 0, DIGIT_WIDTH, DIGIT_HEIGHT);
		panel.add(digit2);
		
		digit3 = new JLabel();
		digit3.setBounds(DIGIT_WIDTH * 2, 0, DIGIT_WIDTH, DIGIT_HEIGHT);
		panel.add(digit3);
		
		setCounter(minesLeft());
	}
	
	public int minesLeft() {
		return totalMines - markedMines;
	}
	
	public void markMine() {
		if (markedMines >= totalMines)
			return;
		
		markedMines++;
		setCounter(minesLeft());
	}
	
	public void unmarkMine() {
		if (markedMines <= 0) 
			return;
		
		markedMines--;
		setCounter(minesLeft());
	}
	
	private void setCounter(int minesLeft) {
		digit1.setIcon(ImageLoader.COUNTER[minesLeft / 100]);
		digit2.setIcon(ImageLoader.COUNTER[minesLeft % 100 / 10]);
		digit3.setIcon(ImageLoader.COUNTER[minesLeft % 10]);
	}
	
	public void reset() {
		markedMines = 0;
		setCounter(minesLeft());
	}

	public JPanel getPanel() {
		return panel;
	}
}
