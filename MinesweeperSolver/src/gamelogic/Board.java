package gamelogic;

import java.util.ArrayList;

import gameutil.Coordinate;
import gameutil.RandomUniqueNumbers;

public class Board {
	
	public static int[][] initBoard(int numRows, int numColumns, int numMines) {
		int[][] board = new int[numRows][numColumns]; 
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				board[i][j] = 0;
		
		// put mines randomly all over the board
		ArrayList<Integer> mineIndexes = RandomUniqueNumbers.generate(numRows * numColumns, numMines);
		
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
				if (x > 0 && x < numRows && y > 0 && y < numColumns && board[x][y] == -1)
					board[i][j] += 1;
	}
	
	private static Coordinate indexToCoordinate(int index, int numColumns) {
		return new Coordinate(index / numColumns, index % numColumns);
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
}