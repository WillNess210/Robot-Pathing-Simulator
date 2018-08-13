package examples;

import frc.Field;
import frc.Robot;

public class UserDriveStraightExampleWithPID{
	public Robot robot;
	public UserDriveStraightExampleWithPID(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	double cmGoal = (Field.fieldXCM/2) - (robot.robotLengthCM/2);
	BasicPID leftPID = new BasicPID(1.0/400.0, 0.0, 0, cmGoal);
	BasicPID rightPID = new BasicPID(1.0/400.0, 0.0, 0, cmGoal);
	BasicPID turnPID = new BasicPID(1.0/8.0, 0.0, 0.0, 0.0);
	public void robotPeriodic() {
		double aX = turnPID.getOutput(robot.getGyroAngle());
		robot.setLeftPower(leftPID.getOutput(robot.getLeftEncoderDistance()) + aX);
		robot.setRightPower(rightPID.getOutput(robot.getRightEncoderDistance()) - aX);
	}
}
