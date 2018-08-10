package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.management.timer.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import frc.*;
import tools.BufferedImageHelp;

public class Viewer{
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		// Creating robot
		Robot bot = new Robot();
		JPanel renderPanel = new JPanel(){
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
		};
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		while(true) {
			long curTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - curTime < 1000) {
				
			}
			bot.heading++;
			renderPanel.repaint();
		}
	}
}
