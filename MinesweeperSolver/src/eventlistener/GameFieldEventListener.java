package eventlistener;

public interface GameFieldEventListener {
	void leftClick(int i, int j);
	void rightClick(int i, int j);
	void middleClick(int i, int j);
	void leftClickPressed(int i, int j);
	void leftClickReleased(int i, int j);
}
