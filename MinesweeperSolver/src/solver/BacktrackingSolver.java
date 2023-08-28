package solver;

import java.util.ArrayList;

public class BacktrackingSolver {
	int numRows, numColumns, numMines;
	
	int numSolutions;

	public BacktrackingSolver(int numRows, int numColumns, int numMines) {
		super();
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
	}
	
	public Action getAction(Field[][] fields, int minesLeft, boolean firstClick) {
		if (firstClick)
			return new Action(numRows / 2, numColumns / 2, Click.LEFT);
		
		// set neighbors for all fields
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				setNeighbors(fields, i, j);
		
		// find all fields of interest
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				setFieldsOfInterest(fields, i, j);
		
		// set neighboring fields of interest
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				setNeighboringFieldsOfInterest(fields, i, j);
		
		// get all fields of interest in an array
		Field[] allOpenFieldsOfInterest = getAllOpenFieldsOfInterest(fields);
		Field[] allUnknownFieldsOfInterest = getAllUnknownFieldsOfInterest(fields);
		
		// check for solution
		for (var field: allOpenFieldsOfInterest)
			if (field.isOpenFieldOfInterest()) {
				int fieldMinesLeft = ngbMinesLeft(field);
				if (fieldMinesLeft == 0) {
					int row = field.getRow();
					int col = field.getCol();
					return new Action(row, col, Click.MIDDLE);
				}
				if (fieldMinesLeft == field.getUnknownNbgsOfInterest().length) {
					int row = field.getUnknownNbgsOfInterest()[0].getRow();
					int col = field.getUnknownNbgsOfInterest()[0].getCol();
					return new Action(row, col, Click.RIGHT);
				}
			}
		
		// ide gas ide rekurzija looool
		numSolutions = 0;
		findAllPossibleSolutions(allUnknownFieldsOfInterest, 0, minesLeft);
		
		Field targetField = allUnknownFieldsOfInterest[0];
		for (int i = 1; i < allUnknownFieldsOfInterest.length; i++)
			if (Math.min(targetField.getNumSolutions() / (float) numSolutions - 1, targetField.getNumSolutions() / (float) numSolutions) < 
					Math.min(allUnknownFieldsOfInterest[i].getNumSolutions() / (float) numSolutions - 1, allUnknownFieldsOfInterest[i].getNumSolutions() / (float) numSolutions))
				targetField = allUnknownFieldsOfInterest[i];
		
		if (targetField.getNumSolutions() / (float) numSolutions <= 0.5)
			return new Action(targetField.getRow(), targetField.getCol(), Click.LEFT);
		return new Action(targetField.getRow(), targetField.getCol(), Click.RIGHT);
	}
	
	private void findAllPossibleSolutions(Field[] allUnknownFieldsOfInterest, int index, int minesLeft) {
		if (minesLeft <= 0 || index == allUnknownFieldsOfInterest.length)
			return;
		
		Field unknownFieldOfInterest = allUnknownFieldsOfInterest[index];
		
		for (var openFieldOfInterest: unknownFieldOfInterest.getUnknownNbgsOfInterest())
			if (ngbMinesLeft(openFieldOfInterest) < 0) 
				return;
		
		unknownFieldOfInterest.setAssumedMine(true);
		findAllPossibleSolutions(allUnknownFieldsOfInterest, index + 1, minesLeft - 1);
		
		unknownFieldOfInterest.setAssumedMine(false);
		findAllPossibleSolutions(allUnknownFieldsOfInterest, index + 1, minesLeft);
		
		unknownFieldOfInterest.setAssumedMine(true);
		if (ngbMinesLeft(unknownFieldOfInterest) < 0) 
			unknownFieldOfInterest.setAssumedMine(true);
		
		for (var solutionField: allUnknownFieldsOfInterest)
			if (solutionField.isAssumedMine())
				solutionField.setNumSolutions(solutionField.getNumSolutions() + 1);
		numSolutions += 1;
	}
	
	private Field[] getAllUnknownFieldsOfInterest(Field[][] fields) {
		ArrayList<Field> allUnknownFieldsOfInterest = new ArrayList<Field>();
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isUnknownFieldOfInterest())
					allUnknownFieldsOfInterest.add(fields[i][j]);
		return (Field[]) allUnknownFieldsOfInterest.toArray();
	}
	
	private Field[] getAllOpenFieldsOfInterest(Field[][] fields) {
		ArrayList<Field> allOpenFieldsOfInterest = new ArrayList<Field>();
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isOpenFieldOfInterest())
					allOpenFieldsOfInterest.add(fields[i][j]);
		return (Field[]) allOpenFieldsOfInterest.toArray();
	}
	
	private int ngbMinesLeft(Field field) {
		int counter = 0;
		for (var ngbField: field.getNgbFields())
			if (ngbField.isMarked() || ngbField.isAssumedMine())
				counter++;
		return counter;
	}
	
	private void setNeighboringFieldsOfInterest(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		
		if (field.isUnknownFieldOfInterest() || field.isOpenFieldOfInterest()) {
			ArrayList<Field> openNgbsOfInterest = new ArrayList<Field>();
			for (var ngbField: field.getNgbFields())
				if (ngbField.isOpenFieldOfInterest())
					openNgbsOfInterest.add(ngbField);
			field.setOpenNgbsOfInterest((Field[]) openNgbsOfInterest.toArray());
		
			ArrayList<Field> unknownNgbsOfInterest = new ArrayList<Field>();
			for (var ngbField: field.getNgbFields())
				if (ngbField.isUnknownFieldOfInterest())
					unknownNgbsOfInterest.add(ngbField);
			field.setUnknownNbgsOfInterest((Field[]) unknownNgbsOfInterest.toArray());
		}
	}
	
	private void setFieldsOfInterest(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		
		if (field.isUnknown())
			return;
		
		for (var ngbField: field.getNgbFields())
			if (ngbField.isUnknown()) {
				field.setOpenFieldOfInterest(true);
				ngbField.setUnknownFieldOfInterest(true);
			}
	}
	
	private void setNeighbors(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		ArrayList<Field> neighbors = new ArrayList<Field>();
		for (int i = row - 1; i < row + 2; i++)
			for (int j = col - 1; j < col + 2; j++)
				if (row >= 0 && row < numRows && col > 0 && col < numColumns && fields[i][j] != field) 
					neighbors.add(fields[i][j]);
		field.setNgbFields((Field[]) neighbors.toArray());
	}
}
