package solver;

import java.util.ArrayList;
import java.util.Random;

public class BacktrackingSolver {
	int numRows, numColumns, numMines;
	
	int totalNumSolutions;

	Field[] allUnknownFieldsOfInterest;
	Field[] allOpenFieldsOfInterest;
	
	ArrayList<Field> unknownFieldsBatch;
	
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
		
		// if there are no fields of interest open random field
		if (allOpenFieldsOfInterest.length == 0) {
			Field marginalField = getRandomMarginalField(fields);
			int row = marginalField.getRow();
			int col = marginalField.getCol();
			return new Action(row, col, Click.LEFT);
		}
		
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
		
		//split into batches
		for (var unknownField: allUnknownFieldsOfInterest)
			if (!unknownField.isInBatch()) {
				unknownFieldsBatch = new ArrayList<Field>();
				formBatch(unknownField, unknownFieldsBatch);
				
				// find all solutions for the batch
				totalNumSolutions = 0;
				
				betterRecursion(0, minesLeft);
				
				for (var field: unknownFieldsBatch)
					field.setProbabilityOfMine(totalNumSolutions);
			}
		
		
		//print out values
		for (var field: allUnknownFieldsOfInterest)
			System.out.print(field.getProbabilityOfMine() + " ");
		System.out.println();
		
		Field targetField = allUnknownFieldsOfInterest[0];
		for (var field: allUnknownFieldsOfInterest) {
			float targetAbsProbability = absoluteProbability(targetField.getProbabilityOfMine());
			float otherAbsProbability = absoluteProbability(field.getProbabilityOfMine());
			if (targetAbsProbability > otherAbsProbability)
				targetField = field;
			if (targetAbsProbability == otherAbsProbability 
					&& targetField.getOpenNgbsOfInterest().length < field.getOpenNgbsOfInterest().length)
				targetField = field;
		}
		
		if (targetField.getProbabilityOfMine() < 0.5) {
			System.out.println("Best guess: " + targetField.getProbabilityOfMine() * 100 + "% mine.");
			return new Action(targetField.getRow(), targetField.getCol(), Click.LEFT);
		}
		System.out.println("Best guess: " + targetField.getProbabilityOfMine() * 100 + "% mine.");
		return new Action(targetField.getRow(), targetField.getCol(), Click.RIGHT);
	}
	
	private void betterRecursion(int index, int minesLeft) {
		if (minesLeft < 0)
			return;
		
		if (index == unknownFieldsBatch.size()) {
			for (Field field: unknownFieldsBatch)
				if (field.isAssumedMine())
					field.setNumSolutions(field.getNumSolutions() + 1);
			totalNumSolutions += 1;
			System.out.println("Solution found.");
			return;
		}
		
		Field field = unknownFieldsBatch.get(index);
		field.setProcessed(true);
		
		// try putting a mine
		field.setAssumedMine(true);
		if (canBe(field)) 
			betterRecursion(index + 1, minesLeft - 1);
		field.setAssumedMine(false);
		
		if (canBe(field)) 
			betterRecursion(index + 1, minesLeft);
		
		field.setProcessed(false);
	}
	
	private boolean canBe(Field field) {
		for (Field neighbor: field.getOpenNgbsOfInterest()) {
			int ngbMines = ngbMinesLeft(neighbor);
			int ngbUnprocessed = ngbUnprocessedLeft(neighbor);
			if (ngbMines < 0 || ngbUnprocessed < ngbMines)
				return false;
		}
		return true;
	}
	
	private int ngbMinesLeft(Field field) {
		int counter = 0;
		for (var ngbField: field.getNgbFields())
			if (ngbField.isMarked() || ngbField.isAssumedMine())
				counter++;
		return field.getNgbMines() - counter;
	}
	
	private int ngbUnprocessedLeft(Field field) {
		int counter = 0;
		for (var ngbField: field.getUnknownNbgsOfInterest())
			if (!ngbField.isProcessed())
				counter++;
		return counter;
	}
	
	private void formBatch(Field unknownField, ArrayList<Field> unknownFieldBatch) {
		unknownField.setInBatch(true);
		unknownFieldBatch.add(unknownField);
		
		for (Field ngbOpenField: unknownField.getOpenNgbsOfInterest())
			for (Field ngbUnknownField: ngbOpenField.getUnknownNbgsOfInterest())
				if (!ngbUnknownField.isInBatch()) 
					formBatch(ngbUnknownField, unknownFieldBatch);
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
	
	private Field getRandomMarginalField(Field[][] fields) {
		ArrayList<Field> allMarginalFields = new ArrayList<Field>();
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				if (fields[i][j].isUnknown() && !fields[i][j].isUnknownFieldOfInterest())
					allMarginalFields.add(fields[i][j]);
		Random rand = new Random();
		int index = rand.nextInt(allMarginalFields.size());
		Field marginalField = allMarginalFields.get(index);
		return marginalField;
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
	
	private static float absoluteProbability(float probability) {
		return Math.min(1 - probability, probability);
	}
}
