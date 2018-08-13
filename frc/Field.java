package frc;

import java.io.File;
// TODO RESEARCH ENUM TO MAKE SELECTION EASIER
public class Field{
	public static final File blankSrc = new File("src/resources/BlankWhiteFIeld.png");
	public static final File powerUp = new File("src/resources/PowerupFieldObstacles.png");
	// TODO ADD FUNCTION TO DRAW FIELD, PARAMETER IS Graphics2D
	public static final int imageX = 1400;
	public static final int imageY = 690;
	public static final int fieldXCM = 1036;
	public static final int fieldYCM = 511;
	public static final int fieldXPix = getPixelX(fieldXCM);
	public static final int fieldYPix = getPixelY(fieldYCM);
	public static int getPixelX(double cm) {
		return (int) (imageX * (cm/fieldXCM));
	}
	public static int getPixelY(double cm) {
		return (int) (imageY * (cm/fieldYCM));
	}
	// PHYSICS SETTINGS
	public static double friction = 0.1;
	public static double minPowerToKeepMoving = 0.1;
	public static double minSpeedToKeepMoving = 15;
	public static double minPowerToStartMoving = 0.25;
	public static double motorPower = 400;
	public static double slowMoFactor = 1; // 2 is 2x slower, 4 is 4x slower, 0.5 is 2x faster, etc.. 1 is normal
}
