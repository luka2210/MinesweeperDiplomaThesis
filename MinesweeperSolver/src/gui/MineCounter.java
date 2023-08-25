package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eventlistener.MineCounterEventListener;
import imageutil.ImageLoader;

public class MineCounter implements MineCounterEventListener {
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
		panel.setBounds(0, 15, 63, 30);
		panel.setLayout(null);
		
		digit1 = new JLabel();
		digit1.setBounds(0, 0, 21, 30);
		panel.add(digit1);
		
		digit2 = new JLabel();
		digit2.setBounds(21, 0, 21, 30);
		panel.add(digit2);
		
		digit3 = new JLabel();
		digit3.setBounds(42, 0, 21, 30);
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

	public JPanel getPanel() {
		return panel;
	}
}
