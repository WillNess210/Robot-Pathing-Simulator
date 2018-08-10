package frc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Robot{
	// ROBOT SPECS
	public static int robotWidthCM = 71;
	public static int robotLengthCM = 83;
	public static int robotWidthPix = Field.getPixelX(robotWidthCM);
	public static int robotLengthPix = Field.getPixelY(robotLengthCM);
	public static final File robotImage = new File("src/resources/Robot.png");
	// ROBOT DETAILS
	public double x, y, heading;
	public Robot(double x, double y, double heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}
	public Robot() {
		this.x = 100;
		this.y = 100;
		this.heading = 0;
	}
	public BufferedImage getRobotImage() {
		int radius = robotWidthPix > robotLengthPix ? robotWidthPix * 2 : robotLengthPix * 2;
		BufferedImage img = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
		Graphics2D toDraw = (Graphics2D) img.getGraphics();
		BufferedImage robot = null;
		try{
			robot = ImageIO.read(robotImage);
		}catch(IOException e){
			e.printStackTrace();
		}
		toDraw.drawImage(robot, (radius - robotLengthPix)/2, (radius - robotWidthPix)/2 , null);
		return img;
	}
}
