package tools;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class BufferedImageHelp{
	public static void drawRotatedImage(Graphics2D g, BufferedImage img, int x, int y, int angle) {
		double rads = Math.toRadians(angle);
		double locationX = img.getHeight() / 2;
	    double locationY = img.getHeight() / 2;
	    AffineTransform tx = AffineTransform.getRotateInstance(rads, locationX, locationY);
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	    g.drawImage(op.filter((BufferedImage)img, null), x, y, null);
	}
}
