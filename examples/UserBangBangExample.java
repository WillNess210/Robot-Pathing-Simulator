package examples;

import frc.Field;
import frc.Robot;

public class UserBangBangExample{
	public Robot robot;
	public UserBangBangExample(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public void robotPeriodic() {
		double cmGoal = (Field.fieldXCM/2) - (Robot.robotLengthCM/2);
		// LEFT SIDE
		if(robot.getLeftEncoderDistance() < cmGoal) {
			robot.setLeftPower(0.5);
		}else if(robot.getLeftEncoderDistance() > cmGoal) {
			robot.setLeftPower(-0.5);
		}else {
			robot.setLeftPower(0);
		}
		// RIGHT SIDE
		if(robot.getRightEncoderDistance() < cmGoal) {
			robot.setRightPower(0.5);
		}else if(robot.getRightEncoderDistance() > cmGoal) {
			robot.setRightPower(-0.5);
		}else {
			robot.setRightPower(0);
		}
	}
}
