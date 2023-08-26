package input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JTextFieldDocumentFilter extends DocumentFilter {
	
	private int minVal, maxVal;
	
	public JTextFieldDocumentFilter(int minVal, int maxVal) {
		this.minVal = minVal;
		this.maxVal = maxVal;
	}
    
	@Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
    		throws BadLocationException {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, text);

        if (isValidNumber(sb.toString())) {
            super.insertString(fb, offset, text, attr);
        }
    }
	
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
    		throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isValidNumber(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        }
    }


    private boolean isValidNumber(String text) {
        try {
            int number = Integer.parseInt(text);
            return number >= this.minVal && number <= this.maxVal;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
