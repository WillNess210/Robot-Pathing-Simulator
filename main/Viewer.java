package main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import frc.*;
//import tools.CheckBoxWithTitle;
import tools.DebugWindow;
import tools.Point;
import user.User;

public class Viewer{
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		// Creating and Configuring JPanel & JFrame
		ViewPanel renderPanel = new ViewPanel();
		// Creating robot
		Robot bot = new Robot();
		renderPanel.bot = bot;
		User user = new User(bot);
		// CREATING CONTAINERS & BUTTONS
		DebugWindow debug = new DebugWindow(10);
		renderPanel.debug = debug;
		/*
		 * CheckBoxWithTitle inconsis = new CheckBoxWithTitle("Inconsistencies:", 10,
		 * 700, 180, 50); // inconsis.check(); renderPanel.inconsis = inconsis;
		 */
		// Creating and Configuring JPanel & JFrame
		pane.add(renderPanel, BorderLayout.CENTER);
		// Nick's Swing Code
		final JPanel infoInput = new JPanel();
		infoInput.setLayout(new GridLayout(0, 1));
		final JCheckBox variability = new JCheckBox("Inconsistencies",
				new ImageIcon(Viewer.class.getResource("/resources/CheckBox.png")));
		variability.setSelectedIcon(new ImageIcon(Viewer.class.getResource("/resources/CheckBoxSelected.png")));
		variability.setFont(variability.getFont().deriveFont(32f));
		variability.addActionListener(new ActionListener(){
			@Override
			public final void actionPerformed(final ActionEvent e){
				if(variability.isSelected()){ // MAKE ROBOT LIKE AN ACTUAL ROBOT
					bot.rightToLeftFactor = 1.05;
					System.out.println("IRL");
				}else{ // MAKE ROBOT RUN AS IT WOULD IN PERFECT CONDITIONS
					bot.rightToLeftFactor = 1;
					System.out.println("Sim");
				}
			}
		});
		variability.setSelected(true);
		infoInput.add(variability);
		pane.add(infoInput, BorderLayout.PAGE_END);
		frame.setTitle("Robot Pathing Simulator");
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Game Loop
		long lagTime = System.currentTimeMillis();
		long lastFrameTime = System.currentTimeMillis();
		long lastRobotTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		boolean clicked = false, lastClicked = false;
		while(frame.isEnabled()){
			// ------------------------------------ \\
			// HANDLING USER INPUT - MOUSE & CLICKS \\
			java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(p, renderPanel);
			Point mouse = new Point(p.getX(), p.getY());
			clicked = renderPanel.clicked;
			if(lastClicked == false && clicked == true){ // JUST CLICKED
				if(mouse.isWithin(debug)){
					debug.moving = true;
					debug.offX = (int) (mouse.getX() - debug.getX());
					debug.offY = (int) (mouse.getY() - debug.getY());
				} /*
					 * else if (mouse.isWithin(inconsis.cb)) { inconsis.check(); }
					 */
			}else if(clicked == true){ // HELD DOWN
				if(debug.moving){
					debug.setX(mouse.getX() - debug.offX);
					debug.setY(mouse.getY() - debug.offY);
				}
			}else if(lastClicked == true && clicked == false){ // JUST RELEASED
				debug.moving = false;
			}
			/*
			 * if (inconsis.isChecked()) { // MAKE ROBOT LIKE AN ACTUAL ROBOT
			 * bot.rightToLeftFactor = 1.05; } else { // MAKE ROBOT RUN AS IT WOULD IN
			 * PERFECT CONDITIONS bot.rightToLeftFactor = 1; }
			 */
			// ----------- \\
			// ROBOT STUFF \\
			long startTime = System.currentTimeMillis();
			if(System.currentTimeMillis() - lagTime > 2500){
				// GETTING ACTION FROM USER
				if((System.currentTimeMillis() - lastRobotTime)/Field.slowMoFactor > (1000.0 / 50.0)){ // this is to simulate how often the
																					// robot can update motors and such
					user.robotPeriodic();
					lastRobotTime = System.currentTimeMillis();
				}
				// UPDATING BOT
				bot.tick(lastTime, startTime);
			}
			if(System.currentTimeMillis() - lastFrameTime > (1000.0 / 40.0)){ // Capping FPS
				// DEBUG WINDOW ADDS
				debug.clear();
				debug.add("Coords: ", bot.getX(), bot.getY());
				debug.add("Encoders: ", bot.getLeftEncoderDistance(), bot.getRightEncoderDistance());
				debug.add("Velocity: ", bot.leftSpeed, bot.rightSpeed);
				debug.add("Gyro: ", bot.getGyroAngle());
				debug.add("Mouse: ", mouse.getX(), mouse.getY());
				debug.add("Clicked: ", renderPanel.clicked ? 1.0 : 0.0);
				debug.add("Powers: ", bot.setLeft, bot.setRight);
				// REPAINTING
				renderPanel.repaint();
				lastFrameTime = System.currentTimeMillis();
			}
			// SETTING LASTS
			lastTime = startTime;
			lastClicked = clicked;
		}
	}
}
