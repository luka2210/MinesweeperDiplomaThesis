package solver;

public class Field {
	
	private int row, col;
	
	private boolean unknown;
	private boolean marked;
	private int ngbMines;
	
	private boolean assumedMine;
	
	private boolean unknownFieldOfInterest;
	private boolean openFieldOfInterest;
	
	private Field[] ngbFields;
	private Field[] unknownNbgsOfInterest;
	private Field[] openNgbsOfInterest;
	
	public Field(int row, int col, boolean unknown, boolean marked, int ngbMines) {
		super();
		this.unknown = unknown;
		this.marked = marked;
		this.ngbMines = ngbMines;
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
	
	
}
