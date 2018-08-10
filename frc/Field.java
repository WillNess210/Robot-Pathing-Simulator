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
	public static int getPixelX(double cm) {
		return (int) (imageX * (cm/fieldXCM));
	}
	public static int getPixelY(double cm) {
		return (int) (imageY * (cm/fieldYCM));
	}
}
