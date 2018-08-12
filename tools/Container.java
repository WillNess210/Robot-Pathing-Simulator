package tools;

import java.awt.Color;
import java.awt.Graphics2D;

public class Container extends Point{
	public boolean moving;
	double width, height;
	public int offX, offY;
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
	public void drawOutlinedWhiteBox(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
	}
}
