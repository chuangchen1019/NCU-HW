import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.BorderLayout;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class Current extends JFrame{
	
	private JPanel[][] block = new JPanel[10][10];
	private JPanel workplace;
	ArrayList<Integer> location = new ArrayList<Integer>();
	ArrayList<Integer> walLoc = new ArrayList<Integer>();
	List<Integer> randomLoc = new ArrayList<Integer>();
	private int count = 0;
	private int count2 = 0;
	private JLabel[] imageLabel = new JLabel[100];
	private int wallnum = 0;
	private HpBar hpBar;
	
	
	public Current() {
	
		BorderLayout layout = new BorderLayout();
		layout.setVgap(0);
		setLayout(layout);
		workplace = new JPanel();
		hpBar = new HpBar();
		//hpBar.setSize(500,10);
		workplace.setLayout(new GridLayout(10,10));
		workplace.setBorder(new EmptyBorder(0,0,0,0));	
		
		readMap();
		
		mouseHandler handler = new mouseHandler();
		workplace.addMouseListener(handler);
		
		
		add(workplace,BorderLayout.CENTER);
		add(hpBar,BorderLayout.NORTH);
		
	}
	
	public void readMap() {
		
		File file = new File("src/map.txt");
		try {
			Scanner input = new Scanner(file);
			while(input.hasNextInt()) {
				location.add(input.nextInt());
				if(location.get(count2)== 1) {
					wallnum++;
				}
				count2++;
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//to handle image icon and image 
		ImageIcon bricks = new ImageIcon("src/brickwall.png");
		ImageIcon heart = new  ImageIcon("src/heart.png");
		ImageIcon diamond = new ImageIcon("src/diamond.png");

		Image im1 = bricks.getImage();
		Image im2 = heart.getImage();
		Image im3 = diamond.getImage();
		im1 = im1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		im2 = im2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		im3 = im3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		bricks = new ImageIcon(im1);
		heart = new ImageIcon(im2);
		diamond = new ImageIcon(im3);
		
		//get the location of wall and save them to an array list
		for(int k=0;k<10;k++) {
			for(int l=0;l<10;l++) {
				if(location.get(count) == 1) {	
					walLoc.add(count);
				}
				count++;
			}	
		}
		
		//reset count
		count = 0;
		
		changeHeart(walLoc);
		
		//decide the icon and set it to the correct location		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				block[i][j] = new JPanel();
				imageLabel[count] = new JLabel();
				if(location.get(count) == 1) {	
					imageLabel[count].setIcon(bricks);
					block[i][j].add(imageLabel[count]);
				}
				else if(location.get(count) == 2) {
					imageLabel[count].setIcon(diamond);
					block[i][j].add(imageLabel[count]);
				}
				else if(location.get(count) == 3) {
					imageLabel[count].setIcon(heart);
					block[i][j].add(imageLabel[count]);
				}
				count++;
				workplace.add(block[i][j]);
			}	
		}
		
		//reset count
		count = 0;
		
		return;
	}
	
	public void changeHeart(ArrayList<Integer> walLoc) {
		
		Random rand = new Random();
		int r = 1+rand.nextInt(wallnum);
		
		//generate the random stream
		SecureRandom random = new SecureRandom();
		randomLoc = random.ints(r,0,walLoc.size()-1).boxed().sorted().collect(Collectors.toList());
		
		//select the location which is decided to change 
		for(int i=0;i<r;i++) {
			location.remove(walLoc.get(randomLoc.get(i).intValue()).intValue());
			location.add(walLoc.get(randomLoc.get(i).intValue()).intValue(),3);		
		}		
		return;		
	}
	
	private class mouseHandler extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
		
			int loc = (int)e.getX()/50 + (int)e.getY()/60;
			for(int i=0;i<walLoc.size();i++) {
				if(walLoc.get(i)==loc) {
					hpBar.sethp(1);
				}
			}	
		}	
	}
}
