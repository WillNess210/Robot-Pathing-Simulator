package main;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Viewer{
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		// Creating and Configuring JPanel & JFrame
		ViewPanel renderPanel = new ViewPanel();
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setTitle("Robot Pathing Simulator");
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Game Loop
		while(frame.isEnabled()){
			
		}
	}
}
