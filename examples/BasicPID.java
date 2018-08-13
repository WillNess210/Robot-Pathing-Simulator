package examples;

public class BasicPID{
	double P, I, D, goal, integral, previous_error;
	public BasicPID(double p, double i, double d, double goal) {
		this.P = p;
		this.I = i;
		this.D = d;
		this.goal = goal;
	}
	public double getOutput(double cur) {
		double error = this.goal - cur;
		this.integral += (error * .02);
		double derivative = (error - this.previous_error) / .02;
		this.previous_error = error;
		return cap(this.P * error + this.I*this.integral + this.D*derivative);
	}
	public double cap(double a) {
		return Math.max(Math.min(a, 1), -1);
	}
}
