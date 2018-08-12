package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class CheckBox extends Container{
	public boolean checked, hovered;
	public CheckBox(double x, double y, double width, double height){
		super(x, y, width, height);
		checked = false;
		hovered = false;
	}
	public CheckBox(double x, double y){
		super(x, y, 25, 25);
		checked = false;
		hovered = false;
	}
	public void check() {
		checked = !checked;
	}
	public boolean isChecked() {
		return checked;
	}
	public void draw(Graphics2D g) {
		Stroke ogStroke = g.getStroke();
		g.setStroke(new BasicStroke(3));
		if(hovered) {
			g.setColor(new Color((float)(174.0/255.0), (float)(206.0/255.0), (float)(208.0/255.0)));
		}else {
			g.setColor(new Color((float)(215.0/255.0), (float)(231.0/255.0), (float)(232.0/255.0)));
		}
		g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
		if(this.isChecked()) {
			g.drawLine((int) this.getX() + 1, (int)this.getY() + 1,(int) (this.getX() + this.getWidth() - 1), (int)(this.getY() + this.getHeight() - 1));
			g.drawLine((int) (this.getX() + this.getWidth() - 1), (int)this.getY() + 1,(int) this.getX() + 1, (int)(this.getY() + this.getHeight() - 1));
		}
		g.setStroke(ogStroke);
	}
}
