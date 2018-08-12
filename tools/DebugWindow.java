package tools;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import frc.Field;

public class DebugWindow extends Container{
	// CONSTANTS
	public final int HEIGHTPERITEM = 15;
	// Array to store + variable to keep track of input
	String[][] display;
	public int numAdded = 0;
	// CONSTRUCTOR
	public DebugWindow(int numToDisplay){
		super(Field.fieldXPix - 200, 1, 200, 0);
		display = new String[numToDisplay][3];
		for(int i = 0; i < numToDisplay; i++){
			for(int j = 0; j < 3; j++){
				display[i][j] = "";
			}
		}
		numAdded = 0;
	}
	// ADDING A DEBUG ITEM
	public void add(String title, String val1, String val2){
		if(numAdded < display.length){
			display[numAdded][0] = title;
			display[numAdded][1] = val1;
			display[numAdded++][2] = val2;
			this.height += HEIGHTPERITEM;
		}
	}
	public void add(String title, double a, double b) {
		if(numAdded < display.length) {
			display[numAdded][0] = title;
			display[numAdded][1] = ((double)((int)(a*100)))/100 + "";
			display[numAdded++][2] = ((double)((int)(b*100)))/100 + "";
			this.height += HEIGHTPERITEM;
		}
	}
	public void add(String title, double a, String b) {
		if(numAdded < display.length) {
			display[numAdded][0] = title;
			display[numAdded][1] = ((double)((int)(a*100)))/100 + "";
			display[numAdded++][2] = b;
			this.height += HEIGHTPERITEM;
		}
	}
	public void add(String title, double a) {
		if(numAdded < display.length) {
			display[numAdded][0] = title;
			display[numAdded][1] = ((double)((int)(a*100)))/100 + "";
			display[numAdded++][2] = "";
			this.height += HEIGHTPERITEM;
		}
	}
	// CLEARING TO EMPTY
	public void clear(){
		for(int i = 0; i < numAdded; i++){
			for(int j = 0; j < display[i].length; j++){
				display[i][j] = "";
			}
		}
		numAdded = 0;
		this.height = 0;
	}
	float alpha = 0.75f;
	int type = AlphaComposite.SRC_OVER;
	AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
	public void draw(Graphics2D g){
		if(this.empty() == false) {
			Color color = new Color(1, 1, 1, alpha);
			g.setColor(color);
			g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
			g.setColor(Color.black);
			g.drawRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
			for(int i = 0; i < this.numAdded; i++){
				for(int j = 0; j < display[i].length; j++){
					g.drawString(display[i][j],(int)this.getX() + (j * 60 + 2), (int)this.getY() + (i * this.HEIGHTPERITEM) + 12);
				}
			}
		}
	}
	public boolean empty() {
		return this.numAdded == 0;
	}
}
