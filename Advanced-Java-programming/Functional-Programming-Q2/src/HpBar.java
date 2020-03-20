import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;


public class HpBar extends JPanel{

	private Color color = Color.PINK; 
	private Graphics2D graphic;
	private BufferedImage image;
	private int penSize;
	private boolean fill;
	private int total;
	
	public HpBar() {
		
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		penSize = 10;
		fill = false;
		total = 500;	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 	
		Graphics gg = image.createGraphics();
		graphic = (Graphics2D) gg;
		graphic.setPaint(color);
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
		graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		graphic.drawRect(0, 0, total, 10);
		g.drawImage(image,0,0,this);
	}	
	
	public void sethp(int flag) {
		
		if(flag == 0) {
			total -= 25;
		}
		if(flag == 1) {
			total -= 100;
		}
		repaint();
		return;
	}
	
}
