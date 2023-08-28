package util;

import gui.GameField;

public class BoardConverter {
	public static int[][] toIntMatrix(GameField[][] fields, int numRows, int numColumns) {
		int[][] boardState = new int[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				if (fields[i][j].isOpened())
					if (fields[i][j].isMine())
						boardState[i][j] = -3;
					else
						boardState[i][j] = fields[i][j].getNgbMines();
				else
					if (fields[i][j].isMarked())
						boardState[i][j] = -2;
					else
						boardState[i][j] = -1;
			}
		
		return boardState;
	}
}
