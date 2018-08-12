package tools;

import java.awt.Font;
import java.awt.Graphics2D;

public class CheckBoxWithTitle extends Container{
	String title;
	public CheckBox cb;
	public CheckBoxWithTitle(String title, double x, double y, double width, double height){
		super(x, y, width, height);
		this.title = title;
		this.cb = new CheckBox(x + width - 5 - 25, y + (height-25)/2);
	}
	public void draw(Graphics2D g) {
		this.drawOutlinedWhiteBox(g);
		Font ogFont = g.getFont();
		g.setFont(new Font(g.getFont().getFamily(), g.getFont().getStyle(), 20)); 
		g.drawString(this.title, (int)this.getX() + 2, (int)this.getY() + ((int)this.getHeight() + 10)/2);
		cb.draw(g);
		g.setFont(ogFont);
	}
	public void check() {
		cb.check();
	}
	public boolean isChecked() {
		return cb.isChecked();
	}
}
