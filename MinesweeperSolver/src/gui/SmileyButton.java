package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import game.listener.SmileyEventListener;
import image.ImageLoader;

public class SmileyButton {
	
	public final static int BUTTON_WIDTH = 40, BUTTON_HEIGHT = 40;
	public final static int TOP_MARGIN = 10;
	
	private JLabel button;
	
	private int frameWidth;
	
	private SmileyEventListener listener;
	
	public SmileyButton(int frameWidth, SmileyEventListener listener) {
		this.frameWidth = frameWidth;
		this.listener = listener;
		initialize();
	}
	
	private void initialize() {
		button = new JLabel("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				listener.smileyLeftMousePressed();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				listener.smileyLeftMouseReleased();
			}
			@Override 
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					listener.smileyLeftMouseClicked();
				}
			}
		});
		button.setBounds((frameWidth - BUTTON_WIDTH) / 2, TOP_MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT);
		button.setIcon(ImageLoader.SMILEY);
	}

	public JLabel getButton() {
		return button;
	}
}
