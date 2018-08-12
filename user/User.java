package user;
import frc.Field;
import frc.Robot;

public class User{
	public Robot robot;
	public User(Robot robot){
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double
	// getLeftEncoderDistance(), double getRightEncoderDistance(), double
	// getGyroAngle()
	// use these to program a awesome path planning
	public void robotPeriodic() {
		double cmGoal = (Field.fieldXCM/2) - (robot.robotLengthCM/2);
		double kP = 1.0/120.0;
		double leftDif = cmGoal - robot.getLeftEncoderDistance();
		double rightDif = cmGoal - robot.getRightEncoderDistance();
		double angGoal = 0;
		double aKP = 1.0/8.0;
		double angDif = robot.getGyroAngle();
		double leftY = leftDif * kP;
		double rightY = rightDif * kP;
		double aX = angDif * aKP;
		robot.setLeftPower(leftY - aX);
		robot.setRightPower(rightY + aX);
	}
}
