package tools;
import frc.Field;

public class Point{
	double x, y;
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public Point(Point b) {
		this.x = b.x;
		this.y = b.y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getDistance(Point b){
		return Math.hypot(this.getX() - b.getX(), this.getY() - b.getY());
	}
	public Point middle(Point b){
		return new Point((this.getX() + b.getX())/2,(this.getY() + b.getY())/2);
	}
	public Point toPixelCoords(){
		return new Point(Field.getPixelX(this.getX()), Field.getPixelY(this.getY()));
	}
	public String toString(){
		return this.getX() + ", " + this.getY();
	}
	public boolean isWithin(Container b) {
		return this.getX() >= b.getX() && this.getX() <= b.getX() + b.getWidth() && this.getY() >= b.getY() && this.getY() <= b.getY() + b.getHeight();
	}
}
