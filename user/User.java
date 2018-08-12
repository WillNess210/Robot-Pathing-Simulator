package user;

import frc.Field;
import frc.Robot;

public class User{
	public Robot robot;
	public User(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public void robotPeriodic() {
		robot.setLeftPower(1);
		robot.setRightPower(1);
	}
}
