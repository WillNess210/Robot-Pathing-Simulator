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
import user.User;

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
		// Waiting for initializing lag
		long lagTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - lagTime < 2000) {
			
		}
		// Game Loop
		bot.leftSpeed = 0;
		bot.rightSpeed = 0;
		bot.heading = 0;
		long lastTime = System.currentTimeMillis();
		while(frame.isEnabled()){
			long startTime = System.currentTimeMillis();
			// GIVING INFO TO USER
			User.robot = bot;
			User.robotPeriodic();
			// UPDATING BOT
			bot.tick(lastTime, startTime);
			// FEEDING INFO TO JPANEL
			renderPanel.bot = bot;
			// REPAINTING
			renderPanel.repaint();
			// DEBUG PRINT STATEMENTS
			System.out.println("Velocity: " + bot.leftSpeed);
			System.out.println("Left Encoder: " + bot.getLeftEncoderDistance());
			System.out.println("Right Encoder: " + bot.getRightEncoderDistance());
			// SETTING LAST TIME
			lastTime = startTime;
		}
	}
}
