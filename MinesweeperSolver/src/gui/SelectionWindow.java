package gui;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;

import inpututils.JTextFieldDocument;

import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Font;

public class SelectionWindow {
	
	private final static int MIN_ROWS_INPUT = 1, MAX_ROWS_INPUT = 50;
	private static final int MIN_COLS_INPUT = 1, MAX_COLS_INPUT = 50;
	private final static int MIN_MINES_INPUT = 1, MAX_MINES_INPUT = MAX_ROWS_INPUT * MAX_COLS_INPUT;
	
	private JFrame frame;
	
	private JTextField numRowsInputField, numColumnsInputField, numMinesInputField;
	
	
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
		
		numRowsInputField = new JTextField(0);
		numRowsInputField.setDocument(new JTextFieldDocument(MIN_ROWS_INPUT, MAX_ROWS_INPUT));
		numRowsInputField.setText("20");
		numRowsInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numRowsInputField.setBounds(208, 255, 35, 25);
		frame.getContentPane().add(numRowsInputField);
        //((AbstractDocument) numRowsInputField.getDocument()).setDocumentFilter(new NumberDocumentFilter());
		
		numColumnsInputField = new JTextField(0);
		numColumnsInputField.setDocument(new JTextFieldDocument(MIN_COLS_INPUT, MAX_COLS_INPUT));
		numColumnsInputField.setText("30");
		numColumnsInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numColumnsInputField.setBounds(310, 255, 35, 25);
		frame.getContentPane().add(numColumnsInputField);
		//((AbstractDocument) numColumnsInputField.getDocument()).setDocumentFilter(new NumberDocumentFilter());
		
		numMinesInputField = new JTextField(0);
		numMinesInputField.setDocument(new JTextFieldDocument(MIN_MINES_INPUT, MAX_MINES_INPUT));
		numMinesInputField.setText("145");
		numMinesInputField.setFont(new Font("Dialog", Font.PLAIN, 20));
		numMinesInputField.setBounds(405, 255, 45, 25);
		frame.getContentPane().add(numMinesInputField);
		//((AbstractDocument) numMinesInputField.getDocument()).setDocumentFilter(new NumberDocumentFilter());
		
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
		
		JLabel lblBegginerHeight = new JLabel("9");
		lblBegginerHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerHeight.setBounds(220, 115, 35, 35);
		frame.getContentPane().add(lblBegginerHeight);
		
		JLabel lblBegginerWidth = new JLabel("9");
		lblBegginerWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerWidth.setBounds(320, 115, 35, 35);
		frame.getContentPane().add(lblBegginerWidth);
		
		JLabel lblBegginerMines = new JLabel("10");
		lblBegginerMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBegginerMines.setBounds(410, 115, 35, 35);
		frame.getContentPane().add(lblBegginerMines);
		
		JLabel lblExpertHeight = new JLabel("16");
		lblExpertHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblExpertHeight.setBounds(210, 205, 35, 35);
		frame.getContentPane().add(lblExpertHeight);
		
		JLabel lblInterWidth = new JLabel("16");
		lblInterWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterWidth.setBounds(311, 160, 35, 35);
		frame.getContentPane().add(lblInterWidth);
		
		JLabel lblExpertWidth = new JLabel("40");
		lblExpertWidth.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblExpertWidth.setBounds(311, 205, 35, 35);
		frame.getContentPane().add(lblExpertWidth);
		
		JLabel lblInterHeight = new JLabel("16");
		lblInterHeight.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterHeight.setBounds(210, 160, 35, 35);
		frame.getContentPane().add(lblInterHeight);
		
		JLabel lblInterMines = new JLabel("40");
		lblInterMines.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblInterMines.setBounds(410, 160, 35, 35);
		frame.getContentPane().add(lblInterMines);
		
		JLabel lblExpertMines = new JLabel("99");
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
					otvoriGlavniProzor(9, 9, 10);
				else if (radioIntermediate.isSelected())
					otvoriGlavniProzor(16, 16, 40);
				else if (radioExpert.isSelected()) 
					otvoriGlavniProzor(16, 30, 99);
				else 
					proveriUnos();
			}
		});
	}
	
	
	private void otvoriGlavniProzor(int m, int n, int brMina) {
		//GlavniProzor gp = new GlavniProzor(m, n, brMina, 100, 100);
	}
	
	private void proveriUnos() {
		try {
			if (numRowsInputField.getText().isBlank() || numColumnsInputField.getText().isBlank() || numMinesInputField.getText().isBlank()) 
				throw new Exception("Нисте попунили сва поља!");
			
			int m = Integer.parseInt(numRowsInputField.getText());
			int n = Integer.parseInt(numColumnsInputField.getText());
			int brMina = Integer.parseInt(numMinesInputField.getText());
			
			if (!(inRange(m, 10, 30))) 
				throw new Exception("Висина табле мора бити цео број између 10 и 30.");
			if (!(inRange(n, 10, 50))) 
				throw new Exception("Ширина табле мора бити цео број између 10 и 50.");
			if (brMina > 999) 
				throw new Exception("Број мина не сме бити већи од 999.");
			if (!(inRange(brMina, 1, n * m - 9))) 
				throw new Exception("Број мина за унете димензије табле мора бити цео број између 1 и " + (n * m - 9) + ".");
			
			otvoriGlavniProzor(m, n, brMina);
		}
		catch (NumberFormatException err) {
			JOptionPane.showMessageDialog(null, "Унете вредности морају бити цели бројеви.", "Грешка уноса", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Грешка уноса", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean inRange(int num, int min, int max) {
		return num >= min && num <= max;
	}
}
