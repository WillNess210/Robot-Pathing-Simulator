package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import frc.Field;
import frc.Robot;
import tools.BufferedImageHelp;
import tools.DebugWindow;
import tools.Point;

public class ViewPanel extends JPanel{
	Robot bot = null;
	DebugWindow debug = null;
	public void paintComponent(Graphics g){
		// GETTING 2D GRAPHICS
		Graphics2D g2d = (Graphics2D) g;
		// DRAWING BACKGROUND
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// DRAWING FIELD
		BufferedImage fieldImage = null;
		try{
			fieldImage = ImageIO.read(Field.powerUp);
		}catch(IOException e){
			e.printStackTrace();
		}
		g2d.drawImage(fieldImage, 0, 0, null);
		// DRAWING ROBOT
		BufferedImage botImage = bot.getRobotImage();
		BufferedImageHelp.drawRotatedImage(g2d, botImage, Field.getPixelX(bot.getX())-botImage.getWidth()/2, Field.getPixelY(bot.getY())-botImage.getHeight()/2, (int) bot.heading);
		g2d.setColor(Color.RED);
		g2d.fillRect(Field.getPixelX(bot.getRightSide().getX()) - 1, Field.getPixelY(bot.getRightSide().getY()) - 1, 3, 3);
		g2d.fillRect(Field.getPixelX(bot.getLeftSide().getX()) - 1, Field.getPixelY(bot.getLeftSide().getY()) - 1, 3, 3);
//		// ICC
//		double ICCDist = (bot.robotWidthCM/2) * (bot.leftSpeed + bot.rightSpeed) / (bot.rightSpeed - bot.leftSpeed);
//		Point ICC = new Point(bot.getX() + ICCDist*Math.sin(Math.toRadians(bot.heading)), bot.getY() - ICCDist*Math.cos(Math.toRadians(bot.heading)));
//		System.out.println("Viewer ICC: " + ICC.toString());
//		ICC = ICC.toPixelCoords();
//		Point pointBot = bot.toPixelCoords();
//		g2d.drawLine((int)ICC.getX(), (int)ICC.getY(), (int)pointBot.getX(), (int)pointBot.getY());
		// GOAL LINE
		int xGoal = Field.getPixelX(Field.fieldXCM/2);
		g2d.drawLine(xGoal, 0, xGoal, this.getHeight());
		if(debug != null) {
			debug.draw(g2d);
		}
	}
}
