package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import imageutil.ImageLoader;

public class GameField {
	
	private JLabel fieldFrame;
	private int i, j;
	private boolean mine;
	private int ngbMines;
	private boolean opened, marked;
	private int fieldHeight, fieldWidth;
	
	public GameField(int i, int j, boolean mine, int ngbMines, int fieldHeight, int fieldWidth) {
		this.i = i;
		this.j = j;
		this.mine = mine;
		this.ngbMines = ngbMines;
		this.opened = false;
		this.marked = false;
		this.fieldHeight = fieldHeight;
		this.fieldWidth = fieldWidth;
		this.fieldFrame = initFieldFrame();
	}
	
	private JLabel initFieldFrame() {
		JLabel fieldFrame = new JLabel();
		fieldFrame.setIcon(ImageLoader.FIELD);
		fieldFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//implement function in game logic
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (opened) 
					return;
				fieldFrame.setIcon(ImageLoader.FIELDS[0]);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (opened) 
					return;
				fieldFrame.setIcon(ImageLoader.FIELD);
			}
		});
		fieldFrame.setBounds(j * fieldWidth, i * fieldHeight, fieldWidth, fieldHeight);
		return fieldFrame;
	}
	
	public boolean isMine() {
		return mine;
	}

	public int getNgbMines() {
		return ngbMines;
	}

	public boolean isMarked() {
		return marked;
	}

	public boolean isOpened() {
		return opened;
	}

	public JLabel getFieldFrame() {
		return fieldFrame;
	}

	public void changeMarked() {
		marked = !marked;
		if (marked) 
			fieldFrame.setIcon(ImageLoader.FIELD_MARKED);
		else
			fieldFrame.setIcon(ImageLoader.FIELD);
	}
	
	public boolean open() {
		opened = true;
		if (mine)
			fieldFrame.setIcon(ImageLoader.FIELD_MINE_CLICKED);
		else 
			fieldFrame.setIcon(ImageLoader.FIELDS[ngbMines]);
		return mine;
	}
}
