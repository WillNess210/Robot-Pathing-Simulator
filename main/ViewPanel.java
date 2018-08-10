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
		BufferedImage botImage = bot.getRobotImage();
		BufferedImageHelp.drawRotatedImage(g2d, botImage, Field.getPixelX(bot.x)-botImage.getWidth()/2, Field.getPixelY(bot.y)-botImage.getHeight()/2, (int) bot.heading);
	}
}
