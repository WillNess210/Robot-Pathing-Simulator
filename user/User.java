package user;

import frc.Robot;

public class User{
	public static Robot robot;
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeft(double), setRight(double), double getLeftEncoderDistance(), double getRightEncoderDistance()
	// use these to program a awesome path planning
	public static void robotPeriodic() {
		robot.setLeft(0.5);
		robot.setRight(0.5);
	}
}
