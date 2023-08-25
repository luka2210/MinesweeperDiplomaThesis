package eventlistener;

public interface MineCounterEventListener {
	void markMine();
	void unmarkMine();
	int minesLeft();
}
