package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import eventlistener.GameFieldEventListener;
import imageutil.ImageLoader;

public class GameField {
	
	private JLabel fieldFrame;
	private int i, j;
	private boolean mine;
	private int ngbMines;
	private boolean opened, marked;
	private int fieldHeight, fieldWidth;
	private GameFieldEventListener listener;
	
	public GameField(int i, int j, int fieldHeight, int fieldWidth, 
			GameFieldEventListener listener) {
		this.i = i;
		this.j = j;
		this.opened = false;
		this.marked = false;
		this.fieldHeight = fieldHeight;
		this.fieldWidth = fieldWidth;
		this.fieldFrame = initFieldFrame();
		this.listener = listener;
	}
	
	private JLabel initFieldFrame() {
		JLabel fieldFrame = new JLabel();
		fieldFrame.setIcon(ImageLoader.FIELD);
		fieldFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e))
					listener.leftClick(i, j);
				else if (SwingUtilities.isRightMouseButton(e))
					listener.rightClick(i, j);
				else if (SwingUtilities.isMiddleMouseButton(e))
					listener.middleClick(i, j);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				listener.leftClickPressed(i, j);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (opened || marked) 
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

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setNgbMines(int ngbMines) {
		this.ngbMines = ngbMines;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
		if (marked) 
			fieldFrame.setIcon(ImageLoader.FIELD_MARKED);
		else
			fieldFrame.setIcon(ImageLoader.FIELD);
	}
	
	public void open() {
		opened = true;
		if (mine)
			fieldFrame.setIcon(ImageLoader.FIELD_MINE_CLICKED);
		else 
			fieldFrame.setIcon(ImageLoader.FIELDS[ngbMines]);
	}
}
