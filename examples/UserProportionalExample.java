package examples;

import frc.Field;
import frc.Robot;

public class UserProportionalExample{
	public Robot robot;
	public UserProportionalExample(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public void robotPeriodic() {
		double cmGoal = (Field.fieldXCM/2) - (Robot.robotLengthCM/2);
		double kP = 1.0/120.0;
		double leftDif = cmGoal - robot.getLeftEncoderDistance();
		double rightDif = cmGoal - robot.getRightEncoderDistance();
		robot.setLeftPower(leftDif * kP);
		robot.setRightPower(rightDif * kP);
	}
}
