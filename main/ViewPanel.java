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
import tools.Point;

public class ViewPanel extends JPanel{
	Robot bot = null;
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
		// ICC
		double ICCDist = (bot.robotWidthCM/2) * (bot.leftSpeed + bot.rightSpeed) / (bot.rightSpeed - bot.leftSpeed);
		Point ICC = new Point(bot.getX() + ICCDist*Math.sin(Math.toRadians(bot.heading)), bot.getY() - ICCDist*Math.cos(Math.toRadians(bot.heading)));
		ICC = ICC.toPixelCoords();
		Point pointBot = bot.toPixelCoords();
		g2d.setColor(Color.RED);
		g2d.drawLine((int)ICC.getX(), (int)ICC.getY(), (int)pointBot.getX(), (int)pointBot.getY());

	}
}
