package frc;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import tools.Point;
// PHYSICS SOURCE: https://www.ijser.org/researchpaper/Modeling-and-Simulation-of-a-Differential-Drive-Mobile-Robot.pdf
public class Robot extends Point{
	// ROBOT SPECS
	public static int robotWidthCM = 71;
	public static int robotLengthCM = 83;
	public static int robotWidthPix = Field.getPixelX(robotWidthCM);
	public static int robotLengthPix = Field.getPixelY(robotLengthCM);
	public static final File robotImage = new File("src/resources/Robot.png");
	// ROBOT DETAILS
	public double heading, leftSpeed, rightSpeed;
	public Robot(double x, double y, double heading){
		super(x, y);
		this.heading = heading;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
	}
	public Robot(){
		super(Field.getPixelX(Field.fieldXCM/2), Field.getPixelY(Field.fieldYCM/2) / 1 - robotWidthPix / 2);
		this.heading = 0;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
	}
	public BufferedImage getRobotImage(){
		int radius = robotWidthPix > robotLengthPix ? robotWidthPix * 2 : robotLengthPix * 2;
		BufferedImage img = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
		Graphics2D toDraw = (Graphics2D) img.getGraphics();
		BufferedImage robot = null;
		try{
			robot = ImageIO.read(robotImage);
		}catch(IOException e){
			e.printStackTrace();
		}
		toDraw.drawImage(robot, (radius - robotLengthPix) / 2, (radius - robotWidthPix) / 2, null);
		return img;
	}
	public Point getRightSide(){ // cm coordinates, not pixel coordinates
		return new Point(this.getX(), this.getY() + robotWidthCM / 2);
	}
	public Point getLeftSide(){ // cm coordinates, not pixel coordinates
		return new Point(this.getX(), this.getY() - robotWidthCM / 2);
	}
	public void tick(long startTime, long endTime){
		double difference = endTime - startTime; // time to sim
		double secondsTaken = difference / 1000; // factor to multiply speed by to account for time taken
		// NORMAL DRIVING
		// double speedToUse = (difference/1000) * this.speed;
		// double rads = ((double)heading)/180;
		// double addX = speedToUse * Math.cos(rads);
		// double addY = speedToUse * Math.sin(rads);
		// this.x += addX;
		// this.y += addY;
		// INDEPENDENT SIDES
		double angSpeed = (leftSpeed - rightSpeed)/robotWidthCM;
		double ICCDist = (robotWidthCM/2) * (leftSpeed + rightSpeed) / (leftSpeed - rightSpeed);
		System.out.println("angSpeed: " + Math.toDegrees(angSpeed));
		System.out.println("ICCDist: " + ICCDist);
		Point ICC = new Point(this.getX() + ICCDist*Math.sin(Math.toRadians(this.heading)), this.getY() - ICCDist*Math.cos(Math.toRadians(this.heading)));
		// UPDATING ROBOT
		double changeAngle = angSpeed * secondsTaken;
		heading = Math.toDegrees(changeAngle) + heading;
		System.out.println("New Angle: " + heading);
//		double newX = (Math.cos(changeAngle)*(this.getX() - ICC.getX()) - Math.sin(changeAngle)*(this.getY() - ICC.getY())) + ICC.getX();
//		double newY = (Math.sin(changeAngle)*(this.getX() - ICC.getX()) + Math.cos(changeAngle)*(this.getY() - ICC.getY())) + ICC.getY();
		double newX = (Math.cos(-changeAngle)*(this.getX() - ICC.getX()) - Math.sin(-changeAngle)*(this.getY() - ICC.getY())) + ICC.getX();
		double newY = (Math.sin(-changeAngle)*(this.getX() - ICC.getX()) + Math.cos(-changeAngle)*(this.getY() - ICC.getY())) + ICC.getY();
		this.setX(newX);
		this.setY(newY);
	}
}
