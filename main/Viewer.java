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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Viewer implements Runnable{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu runButton;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem openMenuItem;
	private JMenuItem cutMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem pasteMenuItem;
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Viewer());
	}
	public void run() {
		// Creating frame & pane
		frame = new JFrame();
		// Creating menu
		menuBar = new JMenuBar();
		// build the run button
		runButton = new JMenu("Run");
	    // build the File menu
	    fileMenu = new JMenu("File");
	    openMenuItem = new JMenuItem("Open");
	    fileMenu.add(openMenuItem);

	    // build the Edit menu
	    editMenu = new JMenu("Edit");
	    cutMenuItem = new JMenuItem("Cut");
	    copyMenuItem = new JMenuItem("Copy");
	    pasteMenuItem = new JMenuItem("Paste");
	    editMenu.add(cutMenuItem);
	    editMenu.add(copyMenuItem);
	    editMenu.add(pasteMenuItem);

	    // add menus to menubar
	    menuBar.add(runButton);
	    menuBar.add(fileMenu);
	    menuBar.add(editMenu);

	    // put the menubar on the frame
	    frame.setJMenuBar(menuBar);
		// Configuring frame
		frame.setTitle("Robot Pathing Simulator");
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
