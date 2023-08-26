package image;

import javax.swing.ImageIcon;

public class ImageLoader {
	public static final ImageIcon[] FIELDS, COUNTER;
	public static final ImageIcon FIELD;
	public static final ImageIcon FIELD_MARKED, FIELD_MARKED_WRONG;
	public static final ImageIcon FIELD_MINE, FIELD_MINE_CLICKED;
	public static final ImageIcon SMILEY, SMILEY_PRESSED;
	public static final ImageIcon SMILEY_DEAD, SMILEY_WON;
	public static final ImageIcon GAME_ICON;
	
	static {
		var loader = ImageLoader.class;
		
		FIELDS = new ImageIcon[9];
		for (int i = 0; i < 9; i++)
			FIELDS[i] = new ImageIcon(loader.getResource(getImagePath("field", i, "png")));
		
		COUNTER = new ImageIcon[10];
		for (int i = 0; i < 10; i++)
			COUNTER[i] = new ImageIcon(loader.getResource(getImagePath("counter", i, "png")));
		
		FIELD = new ImageIcon(loader.getResource(getImagePath("field", "png")));
		FIELD_MARKED = new ImageIcon(loader.getResource(getImagePath("fieldMarked", "png")));
		FIELD_MARKED_WRONG = new ImageIcon(loader.getResource(getImagePath("fieldMarkedWrong", "png")));
		FIELD_MINE = new ImageIcon(loader.getResource(getImagePath("fieldMine", "png")));
		FIELD_MINE_CLICKED = new ImageIcon(loader.getResource(getImagePath("fieldMineClicked", "png")));
		
		SMILEY = new ImageIcon(loader.getResource(getImagePath("smiley", "png")));
		SMILEY_PRESSED = new ImageIcon(loader.getResource(getImagePath("smileyPressed", "png")));
		SMILEY_DEAD = new ImageIcon(loader.getResource(getImagePath("smileyDead", "png")));
		SMILEY_WON = new ImageIcon(loader.getResource(getImagePath("smileyWon", "png")));
		
		GAME_ICON = new ImageIcon(loader.getResource(getImagePath("gameIcon", "png")));
	}
	
	private static String getImagePath(String imgName, Integer val, String imgFormat) {
		return "/static/images/".
				concat(imgName).
				concat(val.toString()).
				concat(".").
				concat(imgFormat);
	}
	
	private static String getImagePath(String imgName, String imgFormat) {
		return "/static/images/".
				concat(imgName).
				concat(".").
				concat(imgFormat);
	}
}
