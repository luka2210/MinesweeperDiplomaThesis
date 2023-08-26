package input;

import javax.swing.text.PlainDocument;

public class JTextFieldDocument extends PlainDocument {
	
	private static final long serialVersionUID = 5677296948930531889L;
	
	private int minVal, maxVal;
	
	public JTextFieldDocument(int minVal, int maxVal) {
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.setDocumentFilter(new JTextFieldDocumentFilter(this.minVal, this.maxVal));
	}
}
