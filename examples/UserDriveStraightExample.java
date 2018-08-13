package examples;

import frc.Field;
import frc.Robot;

public class UserDriveStraightExample{
	public Robot robot;
	public UserDriveStraightExample(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public void robotPeriodic() {
		double cmGoal = (Field.fieldXCM/2) - (robot.robotLengthCM/2);
		double kP = 1.0/120.0;
		double leftDif = cmGoal - robot.getLeftEncoderDistance();
		double rightDif = cmGoal - robot.getRightEncoderDistance();
		double angGoal = 0;
		double aKP = 1.0/8.0;
		double angDif = robot.getGyroAngle();
		double leftY = cap(leftDif * kP);
		double rightY = cap(rightDif * kP);
		double aX = cap(angDif * aKP);
		robot.setLeftPower(leftY - aX);
		robot.setRightPower(rightY + aX);
	}
	public double cap(double a) {
		return Math.max(Math.min(a, 1), -1);
	}
}
