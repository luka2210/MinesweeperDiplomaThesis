package solver;

import java.util.ArrayList;

public class BacktrackingSolver {
	int numRows, numColumns, numMines;
	
	int totalNumSolutions;

	Field[] allUnknownFieldsOfInterest;
	Field[] allOpenFieldsOfInterest;
	
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
		allOpenFieldsOfInterest = getAllOpenFieldsOfInterest(fields);
		allUnknownFieldsOfInterest = getAllUnknownFieldsOfInterest(fields);
		totalNumSolutions = 0;
		
		// check for solution
		for (var field: allOpenFieldsOfInterest) {
			int fieldMinesLeft = ngbMinesLeft(field);
			if (fieldMinesLeft == 0) {
				int row = field.getRow();
				int col = field.getCol();
				return new Action(row, col, Click.MIDDLE);
			}
			else if (fieldMinesLeft == field.getUnknownNbgsOfInterest().length) {
				int row = field.getUnknownNbgsOfInterest()[0].getRow();
				int col = field.getUnknownNbgsOfInterest()[0].getCol();
				return new Action(row, col, Click.RIGHT);
			}
		}
		
		// find all solutions
		findAllPossibleSolutions(0, minesLeft);
		
		for (var field: allUnknownFieldsOfInterest)
			field.setProbabilityOfMine(totalNumSolutions);
		
		Field targetField = allUnknownFieldsOfInterest[0];
		for (int i = 1; i < allUnknownFieldsOfInterest.length; i++)
			if (Math.min(1 - allUnknownFieldsOfInterest[i].getProbabilityOfMine(), allUnknownFieldsOfInterest[i].getProbabilityOfMine()) 
					< Math.min(1 - targetField.getProbabilityOfMine(), targetField.getProbabilityOfMine()))
				targetField = allUnknownFieldsOfInterest[i];
		
		if (targetField.getProbabilityOfMine() <= 0.5)
			return new Action(targetField.getRow(), targetField.getCol(), Click.LEFT);
		return new Action(targetField.getRow(), targetField.getCol(), Click.RIGHT);
	}
	
	private void findAllPossibleSolutions(int index, int minesLeft) {
		if (index == allUnknownFieldsOfInterest.length) {
			if (minesLeft < 0)
				return;
			
			for (Field openField: allOpenFieldsOfInterest) 
				if (ngbMinesLeft(openField) != 0)
					return;
			
			for (var solutionField: allUnknownFieldsOfInterest)
				if (solutionField.isAssumedMine())
					solutionField.setNumSolutions(solutionField.getNumSolutions() + 1);
			totalNumSolutions += 1;
			return;
		}
		
		Field unknownFieldOfInterest = allUnknownFieldsOfInterest[index];
		
		unknownFieldOfInterest.setAssumedMine(true);
		findAllPossibleSolutions(index + 1, minesLeft - 1);
		
		unknownFieldOfInterest.setAssumedMine(false);
		findAllPossibleSolutions(index + 1, minesLeft);
	}
	
	private Field[] getAllUnknownFieldsOfInterest(Field[][] fields) {
		ArrayList<Field> allUnknownFieldsOfInterest = new ArrayList<Field>();
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isUnknownFieldOfInterest())
					allUnknownFieldsOfInterest.add(fields[i][j]);
		return allUnknownFieldsOfInterest.toArray(new Field[allUnknownFieldsOfInterest.size()]);
	}
	
	private Field[] getAllOpenFieldsOfInterest(Field[][] fields) {
		ArrayList<Field> allOpenFieldsOfInterest = new ArrayList<Field>();
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isOpenFieldOfInterest())
					allOpenFieldsOfInterest.add(fields[i][j]);
		return allOpenFieldsOfInterest.toArray(new Field[allOpenFieldsOfInterest.size()]);
	}
	
	private int ngbMinesLeft(Field field) {
		int counter = 0;
		for (var ngbField: field.getNgbFields())
			if (ngbField.isMarked() || ngbField.isAssumedMine())
				counter++;
		return field.getNgbMines() - counter;
	}
	
	private void setNeighboringFieldsOfInterest(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		
		if (field.isUnknownFieldOfInterest() || field.isOpenFieldOfInterest()) {
			ArrayList<Field> openNgbsOfInterest = new ArrayList<Field>();
			for (var ngbField: field.getNgbFields())
				if (ngbField.isOpenFieldOfInterest())
					openNgbsOfInterest.add(ngbField);
			field.setOpenNgbsOfInterest(openNgbsOfInterest.toArray(new Field[openNgbsOfInterest.size()]));
		
			ArrayList<Field> unknownNgbsOfInterest = new ArrayList<Field>();
			for (var ngbField: field.getNgbFields())
				if (ngbField.isUnknownFieldOfInterest())
					unknownNgbsOfInterest.add(ngbField);
			field.setUnknownNbgsOfInterest(unknownNgbsOfInterest.toArray(new Field[unknownNgbsOfInterest.size()]));
		}
	}
	
	private void setFieldsOfInterest(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		
		if (field.isUnknown())
			return;
		
		for (var ngbField: field.getNgbFields())
			if (ngbField.isUnknown() && !ngbField.isMarked()) {
				field.setOpenFieldOfInterest(true);
				ngbField.setUnknownFieldOfInterest(true);
			}
	}
	
	private void setNeighbors(Field[][] fields, int row, int col) {
		Field field = fields[row][col];
		ArrayList<Field> neighbors = new ArrayList<Field>();
		for (int i = row - 1; i < row + 2; i++)
			for (int j = col - 1; j < col + 2; j++)
				if (i >= 0 && i < numRows && j >= 0 && j < numColumns && fields[i][j] != field) 
					neighbors.add(fields[i][j]);
		field.setNgbFields(neighbors.toArray(new Field[neighbors.size()]));
	}
}