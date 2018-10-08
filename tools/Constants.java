package tools;

import java.io.File;

public class Constants{
	// IMAGES
	public static final File blankSrc = new File("src/resources/BlankWhiteFIeld.png");
	public static final File powerUp = new File("src/resources/PowerupFieldObstacles.png");
	public static final File robotImage = new File("src/resources/Robot.png");
	// TODO ADD FUNCTION TO DRAW FIELD, PARAMETER IS Graphics2D
	public static final int imageX = 1400;
	public static final int imageY = 690;
	public static final int fieldXCM = 1036;
	public static final int fieldYCM = 511;
	public static final int fieldXPix = getPixelX(fieldXCM);
	public static final int fieldYPix = getPixelY(fieldYCM);
	public static int getPixelX(double cm) {
		return 30 + (int) (imageX * (cm/fieldXCM));
	}
	public static int getPixelY(double cm) {
		return 10 + (int) (imageY * (cm/fieldYCM));
	}
	// ROBOT SETTINGS
	public static final int robotWidthCM = 71;
	public static final int robotLengthCM = 83;
	public static final int robotWidthPix = getPixelX(robotWidthCM);
	public static final int robotLengthPix = getPixelY(robotLengthCM);
	// PHYSICS SETTINGS
	public static final double friction = 0.1;
	public static final double minPowerToKeepMoving = 0.1;
	public static final double minSpeedToKeepMoving = 10;
	public static final double minPowerToStartMoving = 0.25;
	public static final double motorPower = 400;
	public static final double slowMoFactor = 1; // 2 is 2x slower, 4 is 4x slower, 0.5 is 2x faster, etc.. 1 is normal
	// TIME CONSTANTS
	public static final int robotUpdatesPerSec = 20; //roborio updates 20 times per second
	public static final int framesPerSec = 30; // max fps
}
