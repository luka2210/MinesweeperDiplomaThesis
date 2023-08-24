package inpututils;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {

    private Pattern regexCheck = Pattern.compile("[0-9]");
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attrs)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if (regexCheck.matcher(str).matches()) {
            fb.replace(offset, length, str, attrs);
        }
    }

}