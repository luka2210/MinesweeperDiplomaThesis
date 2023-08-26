package game.listener;

public interface GameFieldEventListener {
	
	public void fieldLeftClick(int row, int col);
	public void fieldRightClick(int row, int col);
	public void fieldMiddleClick(int row, int col);
	public void fieldLeftClickPressed(int row, int col);
	public void fieldLeftClickReleased(int row, int col); 
}
