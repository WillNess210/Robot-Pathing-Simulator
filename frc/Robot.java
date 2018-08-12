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
	// ROBOT INCONSISTENCIES
	public double rightToLeftFactor = 1; // 1 is straight, multiplied to right speed
	// ROBOT DETAILS
	public double heading, leftSpeed, rightSpeed, setLeft, setRight, encoderLeft, encoderRight;
	public Point ICC;
	public Robot lastBot = null;
	public Robot(double x, double y, double heading){
		super(x, y);
		this.heading = heading;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
		ICC = this;
		setLeft = 0;
		setRight = 0;
		this.resetEncoders();
	}
	public Robot(){
		super(robotLengthCM / 2, Field.fieldYCM / 2);
		this.heading = 0;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
		ICC = this;
		setLeft = 0;
		setRight = 0;
		this.resetEncoders();
	}
	public Robot(Robot b){
		super(b.getX(), b.getY());
		this.heading = b.heading;
		this.leftSpeed = b.leftSpeed;
		this.rightSpeed = b.rightSpeed;
		this.ICC = new Point(b.ICC);
		setLeft = b.setLeft;
		setRight = b.setRight;
		this.encoderLeft = b.encoderLeft;
		this.encoderRight = b.encoderRight;
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
		double width = robotWidthCM / 2;
		double ang = Math.toRadians(-heading);
		return new Point(this.getX() + width * Math.sin(ang), this.getY() + width * Math.cos(ang));
	}
	public Point getLeftSide(){ // cm coordinates, not pixel coordinates
		double width = robotWidthCM / 2;
		double ang = Math.toRadians(-heading);
		return new Point(this.getX() - width * Math.sin(ang), this.getY() - width * Math.cos(ang));
	}
	public void tick(long startTime, long endTime){
		// UPDATING LAST BOT
		this.lastBot = new Robot(this);
		// GETTING TIME DIFFERENCE
		double difference = endTime - startTime; // time to sim
		double secondsTaken = difference / (1000 * Field.slowMoFactor); // factor to multiply speed by to account for
																		// time taken
		// UPDATING SIDE VELOCITY BASED ON USER INPUT
		this.leftSpeed += (Math.signum(this.setLeft) * this.setLeft * this.setLeft * Field.motorPower) * secondsTaken;
		this.rightSpeed += (Math.signum(this.setRight) * this.setRight * this.setRight * Field.motorPower * this.rightToLeftFactor) * secondsTaken;
		// CHECKING LEFT SIDE FOR MINIMUM SPEEDS
		if(lastBot.leftSpeed == 0 && Math.abs(this.setLeft) < Field.minPowerToStartMoving){
			this.leftSpeed = 0;
		}else if(Math.abs(this.leftSpeed) < Field.minSpeedToKeepMoving
				&& Math.abs(this.setLeft) < Field.minPowerToKeepMoving){
			this.leftSpeed = 0;
		}
		// CHECKING RIGHT SIDE FOR MINIMUM SPEEDS
		if(lastBot.rightSpeed == 0 && Math.abs(this.setRight) < Field.minPowerToStartMoving){
			this.rightSpeed = 0;
		}else if(Math.abs(this.rightSpeed) < Field.minSpeedToKeepMoving
				&& Math.abs(this.setRight) < Field.minPowerToKeepMoving){
			this.rightSpeed = 0;
		}
		// UPDATING ROBOT BASED ON CURRENT SPEED
		if(leftSpeed == rightSpeed && leftSpeed != 0){
			double distanceTraveled = leftSpeed * secondsTaken;
			double newX = this.getX() + distanceTraveled * Math.cos(Math.toRadians(this.heading));
			double newY = this.getY() + distanceTraveled * Math.sin(Math.toRadians(this.heading));
			this.setX(newX);
			this.setY(newY);
		}else if(leftSpeed == -rightSpeed){
			double angSpeed = (leftSpeed - rightSpeed) / robotWidthCM; // angular velocity
			double changeAngle = angSpeed * secondsTaken;
			heading = Math.toDegrees(changeAngle) + heading;
		}else{
			double angSpeed = (leftSpeed - rightSpeed) / robotWidthCM; // angular velocity
			double ICCDist = (robotWidthCM / 2) * (leftSpeed + rightSpeed) / (leftSpeed - rightSpeed); // distance to
																										// turning point
			this.ICC = new Point(this.getX() + ICCDist * Math.sin(Math.toRadians(this.heading)),
					this.getY() - ICCDist * Math.cos(Math.toRadians(this.heading)));
			// GETTING MULTI-USE VARIABLES
			double changeAngle = angSpeed * secondsTaken;
			double angCos = Math.cos(-changeAngle);
			double angSin = Math.sin(-changeAngle);
			double xDif = this.getX() - this.ICC.getX();
			double yDif = this.getY() - this.ICC.getY();
			// GETTING NEW POSITIONS
			double newX = (angCos * xDif - angSin * yDif) + this.ICC.getX();
			double newY = (angSin * xDif + angCos * yDif) + this.ICC.getY();
			// UPDATING ROBOT
			this.setX(newX);
			this.setY(newY);
			heading = Math.toDegrees(changeAngle) + heading;
		}
		// UPDATING ENCODERS BASED ON NEW POSITION
		encoderLeft += this.getLeftSide().getDistance(lastBot.getLeftSide()) * Math.signum(this.leftSpeed);
		encoderRight += this.getRightSide().getDistance(lastBot.getRightSide()) * Math.signum(this.rightSpeed);
		// BOUNDING HEADING to [-180, 180]
		if(heading > 180){
			heading += 180;
			heading = heading % 360;
			heading -= 180;
		}else if(heading < -180){
			heading = -heading; // flipping to positive
			heading += 180; // running same operation as above
			heading = heading % 360;
			heading -= 180;
			heading = -heading; // flipping to negative
		}
		// FACTORING IN FRICTION AND STUFF
		double friction = Field.friction;
		double frictionToApply = 1 - ((1 - friction) * secondsTaken);
		this.leftSpeed *= frictionToApply;
		this.rightSpeed *= frictionToApply;
	}
	// FUNCTIONS FOR USER TO INTERACT WITH
	public void setLeftPower(double a){
		a = Math.max(Math.min(a, 1), -1); // bounds a to [-1, 1]
		this.setLeft = a;
	}
	public void setRightPower(double a){
		a = Math.max(Math.min(a, 1), -1); // bounds a to [-1, 1]
		this.setRight = a;
	}
	public void resetEncoders(){
		this.encoderLeft = 0;
		this.encoderRight = 0;
	}
	public double getLeftEncoderDistance(){
		return encoderLeft;
	}
	public double getRightEncoderDistance(){
		return encoderRight;
	}
	public double getGyroAngle(){
		return heading;
	}
}
