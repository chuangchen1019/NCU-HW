import javax.swing.JOptionPane;
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
		
		//initialize the value
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		penSize = 10;
		fill = true;
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
	
	public void sethp(int total) {
		this.total = total;
		return;
	}
	
	//get the hp value
	public int getTotal() {
		return total;
	}
	
	public void clear() {
		graphic.clearRect(0, 0, 500, 10);
		return;
	}
	
	//check if hp is higher than 0
	public boolean checkHp() {
		if(total > 0)
			return true;
		else 
			return false;
	}
	
	//if hp is lower than 0, the system will show the alert dialog.
	public void alertMessage() {
		JOptionPane.showMessageDialog(this,"You're die","Game over",0);
		return;
	}
}
