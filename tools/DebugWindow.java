package tools;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import frc.Field;

public class DebugWindow{
	// CONSTANTS
	public final int WIDTH = 200;
	public final int HEIGHTPERITEM = 15;
	// Array to store + variable to keep track of input
	String[][] display;
	int numAdded;
	// CONSTRUCTOR
	public DebugWindow(int numToDisplay){
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
		}
	}
	public void add(String title, double a, double b) {
		if(numAdded < display.length) {
			display[numAdded][0] = title;
			display[numAdded][1] = ((double)((int)(a*100)))/100 + "";
			display[numAdded++][2] = ((double)((int)(b*100)))/100 + "";
		}
	}
	public void add(String title, double a, String b) {
		if(numAdded < display.length) {
			display[numAdded][0] = title;
			display[numAdded][1] = ((double)((int)(a*100)))/100 + "";
			display[numAdded++][2] = b;
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
	}
	float alpha = 0.75f;
	int type = AlphaComposite.SRC_OVER;
	AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
	public void draw(Graphics2D g){
		Color color = new Color(1, 1, 1, alpha);
		g.setColor(color);
		g.fillRect(Field.fieldXPix - this.WIDTH, 1, this.WIDTH - 1, this.HEIGHTPERITEM * this.numAdded);
		g.setColor(Color.black);
		g.drawRect(Field.fieldXPix - this.WIDTH, 1, this.WIDTH - 1, this.HEIGHTPERITEM * this.numAdded);
		for(int i = 0; i < this.numAdded; i++){
			for(int j = 0; j < display[i].length; j++){
				g.drawString(display[i][j],(Field.fieldXPix - this.WIDTH) + (j * 60 + 2), (i * this.HEIGHTPERITEM) + 13);
			}
		}
	}
}
