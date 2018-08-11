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
import tools.DebugWindow;
import tools.Point;
import user.User;

public class Viewer{
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		// Creating robot
		Robot bot = new Robot();
		User user = new User(bot);
		// Creating debugger
		DebugWindow debug = new DebugWindow(10);
		// Creating and Configuring JPanel & JFrame
		ViewPanel renderPanel = new ViewPanel();
		renderPanel.bot = bot;
		renderPanel.debug = debug;
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
		long lastTime = System.currentTimeMillis();
		while(frame.isEnabled()){ // TODO add slowmo options
			long startTime = System.currentTimeMillis();
			// GETTING ACTION FROM USER
			user.robotPeriodic();
			// UPDATING BOT
			bot.tick(lastTime, startTime);
			// FEEDING INFO TO JPANEL
			renderPanel.bot = bot;
			// DEBUG PRINT STATEMENTS
			System.out.println("----------------------------");
			System.out.println("Coords: " + bot.toString());
			System.out.println("setLeft: " + bot.setLeft);
			System.out.println("setRight: " + bot.setRight);
			System.out.println("Velocity Left: " + bot.leftSpeed);
			System.out.println("Velocity Right: " + bot.rightSpeed);
			System.out.println("Left Encoder: " + bot.getLeftEncoderDistance());
			System.out.println("Right Encoder: " + bot.getRightEncoderDistance());
			System.out.println("Gyro Angle: " + bot.getGyroAngle());
			// DEBUG WINDOW ADDS
			debug.clear();
			debug.add("Coords: ", bot.getX(), bot.getY());
			debug.add("Encoders: ", bot.getLeftEncoderDistance(), bot.getRightEncoderDistance());
			debug.add("Velocity: ", bot.leftSpeed, bot.rightSpeed);
			debug.add("Gyro: ", bot.getGyroAngle());
			// REPAINTING
			renderPanel.repaint();
			// SETTING LAST TIME
			lastTime = startTime;
		}
	}
}
