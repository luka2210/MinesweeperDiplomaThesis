package gamelogic;

import java.util.ArrayList;

import gameutil.Coordinate;
import gameutil.RandomUniqueNumbers;
import gui.GameField;

public class Board {
	
	public static int[][] initBoard(int numRows, int numColumns, int numMines, int row, int col) {
		Coordinate coordAvoid = new Coordinate(row, col);
		
		int[][] board = new int[numRows][numColumns]; 
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				board[i][j] = 0;
		
		// put mines randomly all over the board
		int indexAvoid = CoordinateToIndex(coordAvoid, numColumns);
		ArrayList<Integer> mineIndexes = RandomUniqueNumbers.generate(numRows * numColumns, numMines, indexAvoid);
		
		for (int mineIndex: mineIndexes) {
			Coordinate coord = indexToCoordinate(mineIndex, numColumns);
			board[coord.getI()][coord.getJ()] = -1;
		}
		
		// count neighboring mines
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (board[i][j] != -1)
					countMines(board, i, j, numRows, numColumns);
		
		printBoard(board, numRows, numColumns);
		return board;
	}
	
	private static void countMines(int[][] board, int i, int j, int numRows, int numColumns) {
		for (int x = i - 1; x < i + 2; x++)
			for (int y = j - 1; y < j + 2; y++)
				if (x >= 0 && x < numRows && y >= 0 && y < numColumns && board[x][y] == -1)
					board[i][j] += 1;
	}
	
	private static Coordinate indexToCoordinate(int index, int numColumns) {
		return new Coordinate(index / numColumns, index % numColumns);
	}
	
	private static int CoordinateToIndex(Coordinate coord, int numColumns) {
		return coord.getI() * numColumns + coord.getJ();
	}
	
	private static void printBoard(int[][] board, int numRows, int numColumns) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++)
				if (board[i][j] == -1)
					System.out.print('B');
				else if (board[i][j] == -2)
					System.out.print('U');
				else 
					System.out.print(board[i][j]);
			System.out.println();
		}
	}
	
	public static int[][] getBoardState(GameField[][] fields, int numRows, int numColumns) {
		int[][] boardState = new int[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++) {
				if (fields[i][j].isOpened())
					boardState[i][j] = -2;
				else if (fields[i][j].isMarked())
					boardState[i][j] = -3;
				else if (fields[i][j].isMine())
					boardState[i][j] = -1;
				else
					boardState[i][j] = fields[i][j].getNgbMines();
			}
		
		return boardState;
	}
}
