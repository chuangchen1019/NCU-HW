import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class viewFrame extends JFrame{
	
	private File file;
	private String content;
	private boolean isLike;
	private Font font;
	private JLabel contentLabel;
	private JPanel display;
	private JPanel contentPanel;
	private JPanel toolBar;
	private Color color;
	private Date date;
	private String dateString;
	private ImageIcon heart;
	private ImageIcon noheart;
	private Image iconPic;
	private JButton likeButton;
	
	public viewFrame() {
		
		setLayout(new BorderLayout());
		
		//read file
		file = new File("src/post.txt");
		try {
			Scanner input = new Scanner(file);
			isLike = input.nextBoolean();
			dateString = input.nextLine();
			
			System.out.println(date);
			
			for(int i=0 ;i<7; i++)
				input.nextLine();
			
			while(input.hasNextLine() == true) {
				content += "<br>"+input.nextLine()+"<br>";
			}		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//new and set the content area
		contentLabel = new JLabel();
		contentPanel = new JPanel();
		font = new Font("Roboto",Font.BOLD,18);
		
		contentLabel.setFont(font);	
		contentLabel.setForeground(Color.yellow);
		
		display = new JPanel();
		color = new Color(0,170,85);	
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(color);
				
		PostSerializable post = new PostSerializable();
		System.out.println(post.getContent());
		
		
		contentLabel.setText(post.getContent());
		contentPanel.add(contentLabel);
		add(contentPanel,BorderLayout.CENTER);

		//set picture
		heart = new ImageIcon("src/like.png");
		noheart = new ImageIcon("src/unlike.png");
		Image im1 = heart.getImage();
		Image im2 = noheart.getImage();
		im1 = im1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		im2 = im2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		heart = new ImageIcon(im1);
		noheart = new ImageIcon(im2);
		
		//set button 
		likeButton = new JButton(noheart);
		likeButton.setBackground(new Color(204,102,0));
		
		likeHandler lh = new likeHandler();
		likeButton.addActionListener(lh);	
		
		//set toolbar
		toolBar = new JPanel();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.setBackground(new Color(204,102,0));
		toolBar.add(likeButton);
		add(toolBar,BorderLayout.SOUTH);
				
	}
	
	private class likeHandler implements ActionListener{
		@Override
	      public void actionPerformed(ActionEvent e)
	      {
				
			//button setting 
			if(isLike == false) 
				likeButton.setIcon(heart);
			else 
				likeButton.setIcon(noheart);
			
			isLike = !isLike;
							
	      }
	}	
	
}
