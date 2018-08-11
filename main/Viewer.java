package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.management.timer.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
		frame.setSize(1406, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Waiting for initializing lag
		long lagTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - lagTime < 2500){
		}
		// Game Loop
		long lastFrameTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		boolean clicked = false, lastClicked = false;
		while(frame.isEnabled()){
			// ------------------------------------ \\
			// HANDLING USER INPUT - MOUSE & CLICKS \\
			java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(p, renderPanel);
			Point mouse = new Point(p.getX(), p.getY());
			clicked = renderPanel.clicked;
			if(lastClicked == false && clicked == true) {
				if(mouse.isWithin(debug)) {
					debug.moving = true;
					debug.offX = (int) (mouse.getX() - debug.getX());
					debug.offY = (int) (mouse.getY() - debug.getY());
				}
			}else if(clicked == true) {
				if(debug.moving) {
					debug.setX(mouse.getX() - debug.offX);
					debug.setY(mouse.getY() - debug.offY);
				}
			}else if(lastClicked == true && clicked == false) {
				debug.moving = false;
			}
			// ----------- \\
			// ROBOT STUFF \\
			long startTime = System.currentTimeMillis();
			// GETTING ACTION FROM USER
			user.robotPeriodic();
			// UPDATING BOT
			bot.tick(lastTime, startTime);
			if(System.currentTimeMillis() - lastFrameTime > (1000.0 / 40.0)){ // Capping FPS
				// DEBUG WINDOW ADDS
				debug.clear();
				debug.add("Coords: ", bot.getX(), bot.getY());
				debug.add("Encoders: ", bot.getLeftEncoderDistance(), bot.getRightEncoderDistance());
				debug.add("Velocity: ", bot.leftSpeed, bot.rightSpeed);
				debug.add("Gyro: ", bot.getGyroAngle());
				debug.add("Mouse: ", mouse.getX(), mouse.getY());
				debug.add("Clicked: ", renderPanel.clicked ? 1.0 : 0.0);
				// REPAINTING
				renderPanel.repaint();
				lastFrameTime = System.currentTimeMillis();
				System.out.println("FRAME");
			}else{
				System.out.println("NON-FRAME");
			}
			// SETTING LASTS
			lastTime = startTime;
			lastClicked = clicked;
		}
	}
}
