package autoclicker;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

public class AutoClicker {
	public static boolean click(Point p, int offsetX, int offsetY, int inputAction) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(p.x + offsetX, p.y + offsetY);
			robot.mousePress(inputAction);
			robot.mouseRelease(inputAction);
			return true;
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Click failed.");
			return false;
		}
	}
}
