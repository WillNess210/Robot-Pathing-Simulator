package robot;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import tools.Constants;
import tools.Point;

public class RobotRep extends Point{
	private double angle;
	public RobotRep() {
		super(Constants.robotLengthCM/2, Constants.fieldYCM/2);
		angle = 0;
	}
	public BufferedImage getRobotImage(){
		int radius = Constants.robotWidthPix > Constants.robotLengthPix ? Constants.robotWidthPix * 2 : Constants.robotLengthPix * 2;
		BufferedImage img = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
		Graphics2D toDraw = (Graphics2D) img.getGraphics();
		BufferedImage robot = null;
		try{
			robot = ImageIO.read(Constants.robotImage);
		}catch(IOException e){
			e.printStackTrace();
		}
		toDraw.drawImage(robot, (radius - Constants.robotLengthPix) / 2, (radius - Constants.robotWidthPix) / 2, null);
		return img;
	}
	public double getAngle() {
		return this.angle;
	}
}
