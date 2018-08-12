package user;

import frc.Robot;

public class User{
	public Robot robot;
	public User(Robot robot) {
		this.robot = robot;
	}
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double) [-1,1], setRightPower(double) [-1,1], double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	long startTime = 0;
	public void robotPeriodic() {
		if(startTime == 0) {
			startTime = System.currentTimeMillis();
			robot.setLeftPower(0);
			robot.setRightPower(0);
		}else {
			if(System.currentTimeMillis() - startTime > 1000) {
				robot.setLeftPower(0);
				robot.setRightPower(0);
			}else {
				robot.setLeftPower(1);
				robot.setRightPower(1);
			}
		}
		
	}
}
