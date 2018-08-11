package user;

import frc.Field;
import frc.Robot;

public class User{
	public static Robot robot;
	// ONLY HAVE USE TO THE FOLLOWING FUNCTIONS:
	// setLeft(double), setRight(double), double getLeftEncoderDistance(), double getRightEncoderDistance(), double getGyroAngle()
	// use these to program a awesome path planning
	public static void robotPeriodic() {
		// DEFAULT
//		robot.setLeft(-1);
//		robot.setRight(-1);
		// PROPORTIONAL
		double cmGoal = Field.fieldXCM/2; // goal to drive to
		double pK = 1.0/240.0; // variable to multiply difference by to get setpowers;
		double leftDif = cmGoal - robot.getLeftEncoderDistance();
		double rightDif = cmGoal - robot.getRightEncoderDistance();
		robot.setLeft(leftDif * pK);
		robot.setRight(rightDif * pK);
	}
}
