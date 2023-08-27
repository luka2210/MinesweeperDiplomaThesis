package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import event.listener.GameFieldEventListener;
import image.ImageLoader;

public class GameField {
	
	private JLabel fieldFrame;
	private int row, col;
	private boolean mine;
	private int ngbMines;
	private boolean opened, marked;
	private int fieldHeight, fieldWidth;
	private GameFieldEventListener listener;
	
	public GameField(int row, int col, int fieldHeight, int fieldWidth, 
			GameFieldEventListener listener) {
		this.row = row;
		this.col = col;
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
					listener.fieldLeftClick(row, col);
				else if (SwingUtilities.isRightMouseButton(e))
					listener.fieldRightClick(row, col);
				else if (SwingUtilities.isMiddleMouseButton(e))
					listener.fieldMiddleClick(row, col);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				listener.fieldMousePressed(row, col);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				listener.fieldMouseReleased(row, col);
			}
		});
		fieldFrame.setBounds(col * fieldWidth, row * fieldHeight, fieldWidth, fieldHeight);
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
