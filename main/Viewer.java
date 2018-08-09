package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import frc.Field;

public class Viewer{
	public static void main(String[] args){
		// Creating frame & pane
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel renderPanel = new JPanel(){
			public void paintComponent(Graphics g){
				// GETTING 2D GRAPHICS
				Graphics2D g2d = (Graphics2D) g;
				// DRAWING BACKGROUND
				g2d.setColor(Color.WHITE);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				// DRAWING FIELD
				BufferedImage img = null;
				try{
					img = ImageIO.read(Field.powerUp);
				}catch(IOException e){
					e.printStackTrace();
				}
				g2d.drawImage(img, 0, 0, null);
			}
		};
		pane.add(renderPanel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setSize(1406, 800);
		frame.setVisible(true);
		
	}
}
