package main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class Viewer{
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
		// Game Loop
		while(frame.isEnabled()){
			
		}
	}
}
