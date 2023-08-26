package game.listener;

import gui.SmileyButton;

public interface SmileyEventListener {
	
	void smileyLeftMouseClicked();
	void smileyLeftMousePressed();
	void smileyLeftMouseReleased();
	
	void setSmiley(SmileyButton smiley);
}
