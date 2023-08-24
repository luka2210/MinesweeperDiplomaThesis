package gui;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;

import inpututils.JTextFieldDocument;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Font;

public class SelectionWindow {
	
	private final static Integer NUM_ROWS_EASY = 9, NUM_COLS_EASY = 9, NUM_MINES_EASY = 10;
	private final static Integer NUM_ROWS_MEDIUM = 16, NUM_COLS_MEDIUM = 16, NUM_MINES_MEDIUM = 40;
	private final static Integer NUM_ROWS_HARD = 16, NUM_COLS_HARD = 30, NUM_MINES_HARD = 99;
	
	private final static Integer MIN_ROWS = 9, MAX_ROWS = 50;
	private final static Integer MIN_COLS = 9, MAX_COLS = 50;
	private final static Integer MIN_MINES = 1, MAX_MINES = MAX_ROWS * MAX_COLS;
	
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionWindow window = new SelectionWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectionWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 515, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JTextField numRowsInputField = new JTextField(0);
		numRowsInputField.setDocument(new JTextFieldDocument(1, MAX_ROWS));
		numRowsInputField.setText("20");
		numRowsInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numRowsInputField.setBounds(208, 255, 35, 25);
		frame.getContentPane().add(numRowsInputField);
		
		JTextField numColumnsInputField = new JTextField(0);
		numColumnsInputField.setDocument(new JTextFieldDocument(1, MAX_COLS));
		numColumnsInputField.setText("30");
		numColumnsInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numColumnsInputField.setBounds(310, 255, 35, 25);
		frame.getContentPane().add(numColumnsInputField);
		
		JTextField numMinesInputField = new JTextField(0);
		numMinesInputField.setDocument(new JTextFieldDocument(1, MAX_MINES));
		numMinesInputField.setText("145");
		numMinesInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numMinesInputField.setBounds(405, 255, 45, 25);
		frame.getContentPane().add(numMinesInputField);
		
		Panel titlePanel = new Panel();
		titlePanel.setBackground(new Color(0, 102, 255));
		titlePanel.setBounds(0, 0, 515, 56);
		frame.getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Minesweeper");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 27));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(159, 0, 254, 56);
		titlePanel.add(titleLabel);
		
		Panel panelUp = new Panel();
		panelUp.setBackground(new Color(221, 221, 221));
		panelUp.setBounds(0, 52, 515, 56);
		frame.getContentPane().add(panelUp);
		panelUp.setLayout(null);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblHeight.setBounds(193, 23, 69, 23);
		panelUp.add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblWidth.setBounds(297, 23, 69, 23);
		panelUp.add(lblWidth);
		
		JLabel lblMines = new JLabel("Mines");
		lblMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMines.setBounds(393, 23, 82, 23);
		panelUp.add(lblMines);
		
		JRadioButton radioBegginer = new JRadioButton(" Beginner");
		radioBegginer.setFont(new Font("Dialog", Font.PLAIN, 20));
		radioBegginer.setBounds(10, 115, 170, 40);
		frame.getContentPane().add(radioBegginer);
		
		JRadioButton radioIntermediate = new JRadioButton(" Intermediate");
		radioIntermediate.setFont(new Font("Dialog", Font.PLAIN, 20));
		radioIntermediate.setBounds(10, 160, 170, 40);
		frame.getContentPane().add(radioIntermediate);
		
		JRadioButton radioExpert = new JRadioButton(" Expert");
		radioExpert.setFont(new Font("Dialog", Font.PLAIN, 20));
		radioExpert.setBounds(10, 205, 170, 40);
		frame.getContentPane().add(radioExpert);
		
		JRadioButton radioCustom = new JRadioButton(" Custom");
		radioCustom.setFont(new Font("Dialog", Font.PLAIN, 20));
		radioCustom.setBounds(10, 250, 170, 40);
		frame.getContentPane().add(radioCustom);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(radioBegginer);
	    group.add(radioIntermediate);
	    group.add(radioExpert);
	    group.add(radioCustom);
	    radioBegginer.setSelected(true);
		
		JLabel lblBegginerHeight = new JLabel(NUM_ROWS_EASY.toString());
		lblBegginerHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerHeight.setBounds(220, 115, 35, 35);
		frame.getContentPane().add(lblBegginerHeight);
		
		JLabel lblBegginerWidth = new JLabel(NUM_COLS_EASY.toString());
		lblBegginerWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerWidth.setBounds(320, 115, 35, 35);
		frame.getContentPane().add(lblBegginerWidth);
		
		JLabel lblBegginerMines = new JLabel(NUM_MINES_EASY.toString());
		lblBegginerMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerMines.setBounds(410, 115, 35, 35);
		frame.getContentPane().add(lblBegginerMines);
		
		JLabel lblInterHeight = new JLabel(NUM_ROWS_MEDIUM.toString());
		lblInterHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterHeight.setBounds(210, 160, 35, 35);
		frame.getContentPane().add(lblInterHeight);
		
		JLabel lblInterWidth = new JLabel(NUM_COLS_MEDIUM.toString());
		lblInterWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterWidth.setBounds(310, 160, 35, 35);
		frame.getContentPane().add(lblInterWidth);
		
		JLabel lblInterMines = new JLabel(NUM_MINES_MEDIUM.toString());
		lblInterMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterMines.setBounds(410, 160, 35, 35);
		frame.getContentPane().add(lblInterMines);
		
		JLabel lblExpertHeight = new JLabel(NUM_ROWS_HARD.toString());
		lblExpertHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblExpertHeight.setBounds(210, 205, 35, 35);
		frame.getContentPane().add(lblExpertHeight);
		
		JLabel lblExpertWidth = new JLabel(NUM_COLS_HARD.toString());
		lblExpertWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblExpertWidth.setBounds(311, 205, 35, 35);
		frame.getContentPane().add(lblExpertWidth);
		
		JLabel lblExpertMines = new JLabel(NUM_MINES_HARD.toString());
		lblExpertMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblExpertMines.setBounds(410, 205, 35, 35);
		frame.getContentPane().add(lblExpertMines);
		
		Panel panelDown = new Panel();
		panelDown.setLayout(null);
		panelDown.setBackground(new Color(221, 221, 221));
		panelDown.setBounds(0, 318, 515, 56);
		frame.getContentPane().add(panelDown);
		
		Button submitButton = new Button("New Game");
		submitButton.setFont(new Font("Dialog", Font.BOLD, 18));
		submitButton.setBackground(new Color(0, 102, 255));
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.setBounds(194, 10, 143, 36);
		panelDown.add(submitButton);
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (radioBegginer.isSelected()) 
					openGameWindow(NUM_ROWS_EASY, NUM_COLS_EASY, NUM_MINES_EASY);
				else if (radioIntermediate.isSelected())
					openGameWindow(NUM_ROWS_MEDIUM, NUM_COLS_MEDIUM, NUM_MINES_MEDIUM);
				else if (radioExpert.isSelected()) 
					openGameWindow(NUM_ROWS_HARD, NUM_COLS_HARD, NUM_MINES_HARD);
				else
					checkInput(numRowsInputField.getText(), 
							numColumnsInputField.getText(), 
							numMinesInputField.getText());
			}
		});
	}
	
	
	private void openGameWindow(int numRows, int numColumns, int numMines) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(numRows, numColumns, numMines);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void checkInput(String numRowsStr, String numColumnsStr, String numMinesStr) {
		if (numRowsStr.isBlank()) 
			numRowsStr = MIN_ROWS.toString();
		if (numColumnsStr.isBlank())
			numColumnsStr = MIN_COLS.toString();
		if (numMinesStr.isBlank())
			numMinesStr = MIN_MINES.toString();
		
		int numRows = inRange(Integer.parseInt(numRowsStr), MIN_ROWS, MAX_ROWS);
		int numColumns = inRange(Integer.parseInt(numColumnsStr), MIN_COLS, MAX_COLS);
		int numMines = inRange(Integer.parseInt(numMinesStr), MIN_MINES, Math.min(numRows * numColumns, MAX_MINES));
		
		openGameWindow(numRows, numColumns, numMines);
	}
	
	private int inRange(int num, int min, int max) {
		if (num < min)
			return min;
		if (num > max)
			return max;
		return num;
	}
}
