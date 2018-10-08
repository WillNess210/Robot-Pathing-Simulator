package main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import robot.RobotRep;
import tools.Constants;
import tools.UpdateHandler;

public class Viewer{
	public static RobotRep robot;
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		JButton buttonOne = new JButton();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		// Creating and Configuring JPanel & JFrame
		ViewPanel renderPanel = new ViewPanel();
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setTitle("Robot Pathing Simulator");
		frame.setResizable(false);
		frame.setSize(1456, 850);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creating & initializing robot
		robot = new RobotRep();
		// Game Loop
		UpdateHandler frameUpdater = new UpdateHandler(Constants.framesPerSec);
		UpdateHandler robotUpdater = new UpdateHandler(Constants.robotUpdatesPerSec);
		while(frame.isEnabled()){
			// ROBOT UPDATING
			if(robotUpdater.shouldUpdate()) {
				double time = robotUpdater.timeSinceLastTick()/1000.0;
				robot.tick(time);
				
			}
			// FRAME UPDATING
			if(frameUpdater.shouldUpdate()) {
				renderPanel.repaint();
			}
		}
	}
}
