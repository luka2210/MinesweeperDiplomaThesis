package solver;

public class Field {
	
	private int row, col;
	private boolean unknown;
	
	private boolean marked;
	private int ngbMines;
	
	private boolean assumedMine = false;
	
	private boolean unknownFieldOfInterest = false;
	private boolean openFieldOfInterest = false;
	
	private Field[] ngbFields;
	private Field[] unknownNbgsOfInterest;
	private Field[] openNgbsOfInterest;
	
	private int numSolutions = 0;
	
	private float probabilityOfMine;
	
	public Field(int row, int col, boolean unknown) {
		this.row = row;
		this.col = col;
		this.unknown = unknown;
	}

	public boolean isUnknown() {
		return unknown;
	}

	public void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public int getNgbMines() {
		return ngbMines;
	}

	public void setNgbMines(int ngbMines) {
		this.ngbMines = ngbMines;
	}

	public boolean isAssumedMine() {
		return assumedMine;
	}

	public void setAssumedMine(boolean assumedMine) {
		this.assumedMine = assumedMine;
	}

	public Field[] getNgbFields() {
		return ngbFields;
	}

	public void setNgbFields(Field[] ngbFields) {
		this.ngbFields = ngbFields;
	}

	public boolean isUnknownFieldOfInterest() {
		return unknownFieldOfInterest;
	}

	public void setUnknownFieldOfInterest(boolean unknownFieldOfInterest) {
		this.unknownFieldOfInterest = unknownFieldOfInterest;
	}

	public boolean isOpenFieldOfInterest() {
		return openFieldOfInterest;
	}

	public void setOpenFieldOfInterest(boolean openFieldOfInterest) {
		this.openFieldOfInterest = openFieldOfInterest;
	}

	public Field[] getUnknownNbgsOfInterest() {
		return unknownNbgsOfInterest;
	}

	public void setUnknownNbgsOfInterest(Field[] unknownNbgsOfInterest) {
		this.unknownNbgsOfInterest = unknownNbgsOfInterest;
	}

	public Field[] getOpenNgbsOfInterest() {
		return openNgbsOfInterest;
	}

	public void setOpenNgbsOfInterest(Field[] openNgbsOfInterest) {
		this.openNgbsOfInterest = openNgbsOfInterest;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public int getNumSolutions() {
		return numSolutions;
	}

	public void setNumSolutions(int numSolutions) {
		this.numSolutions = numSolutions;
	}

	public float getProbabilityOfMine() {
		return probabilityOfMine;
	}

	public void setProbabilityOfMine(float totalNumSolutions) {
		this.probabilityOfMine = numSolutions / totalNumSolutions;
	}
}
