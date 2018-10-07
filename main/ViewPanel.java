package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tools.*;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public boolean clicked = false;
	public ViewPanel() {
		addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked = true;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                clicked = false;
            }
        });
	}
	public void paintComponent(Graphics g){
		// GETTING 2D GRAPHICS
		Graphics2D g2d = (Graphics2D) g;
		// DRAWING BACKGROUND
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// DRAWING FIELD
		BufferedImage fieldImage = null;
		try{
			fieldImage = ImageIO.read(Constants.powerUp);
		}catch(IOException e){
			e.printStackTrace();
		}
		g2d.drawImage(fieldImage, 25, 10, null);
		// DRAWING MENU TOP BORDER
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 10 + fieldImage.getHeight() + 10, getWidth(), 10);
		// LIGHTER GRAY FOR MENU
		g2d.setColor(new Color(230, 230, 230));
		g2d.fillRect(0, 10 + fieldImage.getHeight() + 10 + 10, getWidth(), getHeight() - (10 + fieldImage.getHeight() + 10 + 10));
	}
}
