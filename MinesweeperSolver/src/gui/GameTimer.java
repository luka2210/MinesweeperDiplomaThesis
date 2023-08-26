package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import eventlistener.TimerEventListener;
import imageutil.ImageLoader;

public class GameTimer implements TimerEventListener {
	
	public static int DIGIT_HEIGHT = 30, DIGIT_WIDTH = 21;
	private static int TOP_MARGIN = 15;
	
	private int frameWidth;
	
	private JLabel digit1, digit2, digit3;
	private JPanel panel;
	
	private int timePassed;
	
	private Timer timer;
	
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
		
		setTimer();
	}
	
	public int getTimePassed() {
		return timePassed;
	}
	
	private void setTimer() {
		digit1.setIcon(ImageLoader.COUNTER[timePassed / 100]);
		digit2.setIcon(ImageLoader.COUNTER[timePassed % 100 / 10]);
		digit3.setIcon(ImageLoader.COUNTER[timePassed % 10]);
	}

	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		int delayMilliseconds = 1000;
		ActionListener timerActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timePassed < 1000) {
					timePassed++;
					setTimer();
				}
			}	
		};
		timer = new Timer(delayMilliseconds, timerActionListener);
		timer.start();
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		timer.stop();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		stop();
		this.timePassed = 0;
		start();
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
