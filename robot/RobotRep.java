package robot;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import tools.AngleBounder;
import tools.Constants;
import tools.Point;

public class RobotRep extends Point{
	private double angle, setLeft, setRight, leftSpeed, rightSpeed, rightToLeftFactor, leftEncoder, rightEncoder;
	private Point ICC;
	public RobotRep(){
		super(Constants.robotLengthCM / 2, Constants.fieldYCM / 2);
		this.angle = 0;
		this.setLeft = 0.5;
		this.setRight = 0.5;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
		this.rightToLeftFactor = 1;
		this.leftEncoder = 0;
		this.rightEncoder = 0;
		ICC = new Point();
	}
	public RobotRep(RobotRep b){
		this.angle = b.angle;
		this.setLeft = b.setLeft;
		this.setRight = b.setRight;
		this.leftSpeed = b.leftSpeed;
		this.rightSpeed = b.rightSpeed;
		this.rightToLeftFactor = b.rightToLeftFactor;
		this.leftEnoder = b.leftEncoder;
		this.rightEncoder = b.rightEncoder;
		this.ICC = new Point(b.ICC);
	}
	public BufferedImage getRobotImage(){
		int radius = Constants.robotWidthPix > Constants.robotLengthPix ? Constants.robotWidthPix * 2
				: Constants.robotLengthPix * 2;
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
	public double getAngle(){
		return this.angle;
	}
	public void tick(double secondsToSim){
		// UPDATING SIDE VELOCITY BASED ON USER INPUT
		this.leftSpeed += (Math.signum(this.setLeft) * this.setLeft * this.setLeft * Constants.motorPower) * secondsToSim;
		this.rightSpeed += (Math.signum(this.setRight) * this.setRight * this.setRight * Constants.motorPower * this.rightToLeftFactor) * secondsToSim;
		// UPDATING ROBOT BASED ON CURRENT SPEED
		if(leftSpeed == rightSpeed && leftSpeed != 0){
			double distanceTraveled = leftSpeed * secondsToSim;
			double newX = this.getX() + distanceTraveled * Math.cos(Math.toRadians(this.angle));
			double newY = this.getY() + distanceTraveled * Math.sin(Math.toRadians(this.angle));
			this.setX(newX);
			this.setY(newY);
		}else if(leftSpeed == -rightSpeed){
			double angSpeed = (leftSpeed - rightSpeed) / Constants.robotWidthCM; // angular velocity
			double changeAngle = angSpeed * secondsToSim;
			this.angle = Math.toDegrees(changeAngle) + this.angle;
		}else{
			double angSpeed = (leftSpeed - rightSpeed) / Constants.robotWidthCM; // angular velocity
			double ICCDist = (Constants.robotWidthCM / 2) * (leftSpeed + rightSpeed) / (leftSpeed - rightSpeed); // distance
																													// to
			// turning point
			this.ICC = new Point(this.getX() + ICCDist * Math.sin(Math.toRadians(this.angle)),
					this.getY() - ICCDist * Math.cos(Math.toRadians(this.angle)));
			// GETTING MULTI-USE VARIABLES
			double changeAngle = angSpeed * secondsToSim;
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
			angle = Math.toDegrees(changeAngle) + angle;
		}
		// UPDATING ENCODERS BASED ON NEW POSITION
		encoderLeft += this.getLeftSide().getDistance(lastBot.getLeftSide()) * Math.signum(this.leftSpeed);
		encoderRight += this.getRightSide().getDistance(lastBot.getRightSide()) * Math.signum(this.rightSpeed);
		// BOUNDING HEADING to [-180, 180]
		angle = AngleBounder.boundAngle(angle);
		// FACTORING IN FRICTION AND STUFF
		double friction = Constants.friction;
		double frictionToApply = 1 - ((1 - friction) * secondsToSim);
		this.leftSpeed *= frictionToApply;
		this.rightSpeed *= frictionToApply;
	}
}
