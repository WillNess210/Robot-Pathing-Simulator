package tools;

public class Container extends Point{
	double width, height;
	public Container(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	public double getWidth(){
		return width;
	}
	public void setWidth(double width){
		this.width = width;
	}
	public double getHeight(){
		return height;
	}
	public void setHeight(double height){
		this.height = height;
	}
}
