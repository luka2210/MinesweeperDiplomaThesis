package mediator;

import gui.GameField;
import solver.Field;

public class InputConverter {
	public static Field[][] getFields(GameField[][] gameFields, int numRows, int numColumns) {
		Field[][] fields = new Field[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				GameField gameField = gameFields[i][j];
				int row = gameField.getRow();
				int col = gameField.getCol();
				boolean unknown = !gameField.isOpened();
				boolean marked = gameField.isMarked();
				int ngbMines = gameField.getNgbMines();
				fields[i][j] = new Field(row, col, unknown, marked, ngbMines);
			}
		
		return fields;
	}
}
