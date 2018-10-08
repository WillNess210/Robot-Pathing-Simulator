package tools;

public class AngleBounder{
	public static double boundAngle(double angle) {
		if(angle > 180){
			angle += 180;
			angle = angle % 360;
			angle -= 180;
		}else if(angle < -180){
			angle = -angle; // flipping to positive
			angle += 180; // running same operation as above
			angle = angle % 360;
			angle -= 180;
			angle = -angle; // flipping to negative
		}
		return angle;
	}
}
