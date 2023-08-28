package solver;

public class Action {
	
	private int row, col;
	private Click click;
	
	public Action(int row, int col, Click click) {
		super();
		this.row = row;
		this.col = col;
		this.click = click;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Click getClick() {
		return click;
	}

}
