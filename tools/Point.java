package tools;

public class Point{
	private double x, y;
	// CONSTRUCTORS
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point b) {
		this.x = b.x;
		this.y = b.y;
	}
	public Point() {
		this.x = -1;
		this.y = -1;
	}
	// GETTERS & SETTERS
	public double getX(){
		return x;
	}
	public int getXInt() {
		return (int) x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return y;
	}
	public int getYInt() {
		return (int) y;
	}
	public void setY(double y){
		this.y = y;
	}
	// HELPER FUNCTIONS
	public double getDist(Point b) {
		return Math.hypot(this.getX() - b.getX(), this.getY() - b.getY());
	}
}
