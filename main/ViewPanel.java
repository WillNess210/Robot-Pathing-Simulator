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
import frc.Field;
import frc.Robot;
import tools.BufferedImageHelp;
import tools.CheckBoxWithTitle;
import tools.DebugWindow;

public class ViewPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Robot bot = null;
	DebugWindow debug = null;
	CheckBoxWithTitle inconsis = null;
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
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// DRAWING FIELD
		BufferedImage fieldImage = null;
		try{
			fieldImage = ImageIO.read(Field.powerUp);
		}catch(IOException e){
			e.printStackTrace();
		}
		g2d.drawImage(fieldImage, 0, 0, null);
		// DRAWING ROBOT
		BufferedImage botImage = bot.getRobotImage();
		BufferedImageHelp.drawRotatedImage(g2d, botImage, Field.getPixelX(bot.getX())-botImage.getWidth()/2, Field.getPixelY(bot.getY())-botImage.getHeight()/2, (int) bot.heading);
		// DRAWING DEBUG
		
		if(inconsis != null) {
			inconsis.draw(g2d);
		}
		if(debug != null) {
			debug.draw(g2d);
		}
	}
}
