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
		// Creating and Configuring JPanel & JFrame
		ViewPanel renderPanel = new ViewPanel();
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		// Game Loop
		while(true) {
			// UPDATING ROBOT
			bot.heading++;
			// FEEDING INFO TO JPANEL
			renderPanel.bot = bot;
			// REPAINTING
			renderPanel.repaint();
			// WAITING UNTIL NEXT TICK
			long curTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - curTime < 5) {
				
			}
		}
	}
}
