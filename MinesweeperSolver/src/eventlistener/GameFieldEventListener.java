package eventlistener;

public interface GameFieldEventListener {
	void leftClick(int i, int j);
	void rightClick(int i, int j);
	void middleClick(int i, int j);
}