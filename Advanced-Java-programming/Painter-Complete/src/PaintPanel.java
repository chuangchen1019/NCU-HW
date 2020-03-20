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
import javax.swing.JColorChooser;

public class PaintPanel extends JPanel{
	
	private Color color = Color.BLACK; //預設顏色
	private Graphics2D graphic;
	private BufferedImage image;
	private int penSize;
	private boolean fill;
	private int tools;
	private int oldX, oldY;
	private int nowX, nowY;
	private int toolFlag;
	private boolean useFlag;
	
	
	public PaintPanel() {
			
			image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
			addMouseListener(new MouseHandler()); // not implement yet
			addMouseMotionListener(new MouseMotionHandler());
			penSize = 10;
			fill = false;
			
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 	
		Graphics gg = image.createGraphics();
		graphic = (Graphics2D) gg;
		graphic.setPaint(color);
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
		graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.drawImage(image,0,0,this);

	}	
	
	public void setPenSize(int penSize) {
		this.penSize = penSize;
		return;
	}
	
	public void doFill(boolean fill) {	
		this.fill = fill;
		return;
	}
	
	public void setToolFlag(int toolFlag) {
		this.toolFlag = toolFlag;
		return;
	}
	
	public Color setColor() {
		color = JColorChooser.showDialog(this,"please choose the color", color);
		return color;
	}
	
	public void changePenColor() {
		color = JColorChooser.showDialog(this,"please choose the color", color);
		graphic.setPaint(color);
		return;
	}
	
	public void refresh() {
		
		penSize = 10;
		color = Color.BLACK;
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
		graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
		return;	
	}
	
	public void clearPic() {
		
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		repaint();
		return;
	}
	
	public void setUse(boolean useFlag) {
		
		this.useFlag = useFlag;
		return;
	}

	
	private class MouseHandler extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent event) {
			
			switch(toolFlag)
			{
				//筆刷工具
				case 0:	
				{	
					oldX = event.getX();
					oldY = event.getY();
					graphic.setPaint(color);
					graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
					break;
				}	
				
				case 1:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				
				case 2:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				
				case 3:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				
				case 4:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;	
				}
				
				case 5:
				{
					if(useFlag == true) {
						oldX = event.getX();
						oldY = event.getY();
						color = Color.WHITE;
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL));
						graphic.drawRect(Math.min(oldX, nowX),Math.min(oldY, nowY),penSize, penSize);
					}
					break;
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent event){
				
			switch(toolFlag) 
			{
				case 0:
					break;
				
				case 1:
				{
					nowX = event.getX();
					nowY = event.getY();
					float[] dash = new float[] {25,25};
					
					if(fill == true) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
						graphic.drawLine(oldX, oldY, nowX, nowY);
						//repaint();
						
					}
					else if(fill == false) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f,dash,10f)); 
						graphic.drawLine(oldX, oldY, nowX, nowY);
						
					}	
					repaint();
					break;
						
				}
				
				case 2:
				{
					nowX = event.getX();
					nowY = event.getY();
					
					if(fill == true) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
						graphic.fillOval(Math.min(oldX, nowX), Math.min(oldY, nowY), Math.abs(oldX-nowX), Math.abs(oldY-nowY));
					}
					else if(fill == false) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
						graphic.drawOval(Math.min(oldX, nowX), Math.min(oldY, nowY), Math.abs(oldX-nowX), Math.abs(oldY-nowY));
					}
					
					repaint();
					break;
						
				}
				
				case 3 :
				{
					nowX = event.getX();
					nowY = event.getY();
					
					if(fill == true) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
						graphic.fillRect(Math.min(oldX, nowX), Math.min(oldY, nowY), Math.abs(nowX-oldX), Math.abs(nowY-oldY));
						
					}
					else if(fill == false) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0f));
						graphic.drawRect(Math.min(oldX, nowX), Math.min(oldY, nowY),  Math.abs(nowX-oldX), Math.abs(nowY-oldY));
					}
						
					repaint();
					break;
					
				}
				
				case 4:
				{
					nowX = event.getX();
					nowY = event.getY();
					
					if(fill == true) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
						graphic.fillRect(Math.min(oldX, nowX), Math.min(oldY, nowY), Math.abs(nowX-oldX), Math.abs(nowY-oldY));
						
					}
					else if(fill == false) {
						graphic.setPaint(color);
						graphic.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
						graphic.drawRect(Math.min(oldX, nowX), Math.min(oldY, nowY),  Math.abs(nowX-oldX), Math.abs(nowY-oldY));
					}
						
					repaint();	
					break;
				}
				
				case 5:
				{
					break;
				}
			}
		}		
	}
			
	private class MouseMotionHandler extends MouseMotionAdapter{
		
		public void mouseDragged(MouseEvent event) {
			
			switch(toolFlag) 
			{
				 case 0:
				 {
					nowX = event.getX();
					nowY = event.getY();
					graphic.drawLine(oldX, oldY, nowX, nowY);
					repaint();
					oldX = nowX;
					oldY = nowY;
					break;
				 }
				
				 case 1:
					break;
				 
				 case 2:
					 break;
				 
				 case 3:
					 break;
				 
				 case 4:
					 break;
					 
				 case 5:
					nowX = event.getX();
					nowY = event.getY();
					graphic.drawLine(oldX, oldY, nowX, nowY);
					repaint();
					oldX = nowX;
					oldY = nowY;
					break;
			}
			
		}
	}
}


