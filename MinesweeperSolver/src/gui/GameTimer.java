package gui;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import image.ImageLoader;

public class GameTimer {
	
	public static int DIGIT_HEIGHT = 40, DIGIT_WIDTH = 27;
	public static int TOP_MARGIN = 10;
	
	private int frameWidth;
	
	private volatile JLabel digit1, digit2, digit3;
	private JPanel panel;
	
	private volatile int timePassed;
	
	private Timer timer;
	
	private static long DELAY_SECOND = 1000;
	
	public GameTimer(int frameWidth) {
		this.frameWidth = frameWidth;
		this.timePassed = 0;
		initialize();
	}

	private void initialize() {
		panel = new JPanel();
		panel.setBounds(frameWidth - DIGIT_WIDTH * 3, TOP_MARGIN, DIGIT_WIDTH * 3, DIGIT_HEIGHT);
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
		
		setDigits();
	}
	
	public int getTimePassed() {
		return timePassed;
	}
	
	private void setDigits() {
		digit1.setIcon(ImageLoader.COUNTER[timePassed / 100]);
		digit2.setIcon(ImageLoader.COUNTER[timePassed % 100 / 10]);
		digit3.setIcon(ImageLoader.COUNTER[timePassed % 10]);
	}

	private TimerTask getTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				if (timePassed < 1000) {
					timePassed++;
					setDigits();
				}
			}	
		};
	}
	
	public void start() {
		timer = new Timer();
		timer.schedule(getTimerTask(), 0, DELAY_SECOND);
	}

	public void stop() {
		if (timer == null)
			return;
		timer.cancel();
		timer.purge();
	}
	
	public void resetTime() {
		timePassed = 0;
		setDigits();
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
