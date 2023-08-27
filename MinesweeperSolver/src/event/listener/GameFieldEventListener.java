package event.listener;

public interface GameFieldEventListener {
	
	public void fieldLeftClick(int row, int col);
	public void fieldRightClick(int row, int col);
	public void fieldMiddleClick(int row, int col);
	public void fieldMousePressed(int row, int col);
	public void fieldMouseReleased(int row, int col); 
}
