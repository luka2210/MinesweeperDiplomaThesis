package mediator;

import gui.GameField;
import solver.Field;

public class InputConverter {
	public static Field[][] getFields(GameField[][] gameFields, int numRows, int numColumns) {
		Field[][] fields = new Field[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				boolean unknown = !gameFields[i][j].isOpened();
				fields[i][j] = new Field(i, j, unknown);
				
				if (unknown)
					fields[i][j].setMarked(gameFields[i][j].isMarked());
				else
					fields[i][j].setNgbMines(gameFields[i][j].getNgbMines());
			}
		
		return fields;
	}
}