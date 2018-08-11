package user;

import frc.Field;
import frc.Robot;

public class User{
	public static Robot robot;
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeftPower(double), setRightPower(double), double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public static void robotPeriodic() {
		// DEFAULT
//		robot.setLeft(-1);
//		robot.setRight(-1);
		// PROPORTIONAL
		double cmGoal = (Field.fieldXCM/2) - (robot.robotLengthCM/2);
		double kP = 1.0/180.0;
		double leftDif = cmGoal - robot.getLeftEncoderDistance();
		double rightDif = cmGoal - robot.getRightEncoderDistance();
		robot.setLeftPower(leftDif * kP);
		robot.setRightPower(rightDif * kP);
	}
}
