package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
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
		renderPanel.bot = bot;
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setTitle("Robot Pathing Simulator");
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Game Loop
		bot.leftSpeed = 100;
		bot.rightSpeed = 200;
		long lastTime = System.currentTimeMillis();
		while(true){
//			while(System.currentTimeMillis() - lastTime < 1){
//			}
			long startTime = System.currentTimeMillis();
			// UPDATING BOT
			System.out.println("TURN");
			bot.tick(lastTime, startTime);
			System.out.println("Robot: " + bot.toString());
			// FEEDING INFO TO JPANEL
			renderPanel.bot = bot;
			// REPAINTING
			renderPanel.repaint();
			// SETTING LAST TIME
			lastTime = startTime;
		}
	}
}
