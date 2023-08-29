package event.listener;

import gui.SmileyButton;

public interface SmileyEventListener {
	
	void smileyLeftMouseClicked();
	void smileyRightMouseClicked();
	void smileyLeftMousePressed();
	void smileyLeftMouseReleased();
	
	void setSmiley(SmileyButton smiley);
}
