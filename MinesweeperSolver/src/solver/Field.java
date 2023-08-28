package solver;

import java.util.ArrayList;

public class Field {
	
	private boolean unknown;
	
	private boolean marked;
	private int ngbMines;
	
	private boolean assumeMine;
	
	private ArrayList<Field> ngbFields;
	
	private boolean unknownFieldOfInterest;
	private boolean openFieldOfInterest;
	
	private ArrayList<Field> unknownNbgOfInterest;
	private ArrayList<Field> openNgbOfInterest;
	
	public Field(int val) {
		super();
		this.unknown = unknown;
		this.marked = marked;
		this.ngbMines = ngbMines;
		this.assumeMine = false;
	}
	
	
}
