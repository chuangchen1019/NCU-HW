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
import java.io.EOFException;
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


public class EditFrame extends JFrame{
	
	private String content;
	private String like;
	private boolean isLike;
	private String editTime;
	private File file;
	private JFrame systemFrame;
	private JLabel title;
	private JLabel contentLabel;
	private JPanel workplace;
	private JButton likeIcon;
	private JButton editButton;
	private JButton newPostButton;
	private Image iconPic;
	private Font font;
	private Color color;
	private JTextArea editContent;
	private JPanel contentPanel;
	private JPanel textPanel;
	private JPanel toolBar;
	private JPanel setLoc;
	private JButton saveButton;
	private JButton saveAsButton;
	private JButton importButton;
	private JButton cancelButton;
	private String editWord;
	private ImageIcon heart;
	private ImageIcon noheart;
	private JPanel returnPost;
	private static ObjectInputStream input;
	public PostSerializable post;
 	
	public EditFrame() {
		
		//editFrame layout
		setLayout(new BorderLayout());
		color = new Color(0,170,85);
		
		file = new File("src/post.txt");
		try {
			Scanner input = new Scanner(file);
			Scanner input2 = new Scanner(file);
			
			input2.nextLine();
			input2.nextLine();
			
			like = input.nextLine();
			if(like == "false")
				isLike = false;
			else 
				isLike = true;
			
			System.out.println(like);
			editTime = input.nextLine();
			//content = input.nextLine();
			while(input.hasNextLine() != false) {
				content += "<br>"+input.nextLine()+"</br>";
			}				
			while(input2.hasNextLine() != false) {
				editWord += input2.nextLine()+"\n";
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//use PostSerializable.java to set variables
		post = new PostSerializable();
		post.setContent("<html>"+content+"<br></br></html>");
		post.setIsLike(isLike);
		post.setEditTime(editTime);
		
		//test read files
		System.out.println(post.getIsLike());
		System.out.println(post.getEditTime());
		System.out.println(post.getContent());
		
		
		//realize title and contentLabel
		title = new JLabel();
		contentLabel = new JLabel();
		
		//parse content to HTML and set the text to label
		title.setText("<html>進JA助教<br></br><br>"+post.getEditTime()+"</br></html>");
		contentLabel.setText(post.getContent());
				
		
		//set font and color for title and content
		font = new Font("Roboto",Font.BOLD,18);
		title.setFont(font);
		contentLabel.setFont(font);	
		title.setForeground(Color.WHITE);
		contentLabel.setForeground(Color.yellow);
		
		//realize workplace, contentPanel and textPanel
		workplace = new JPanel();
		contentPanel = new JPanel();
		textPanel = new JPanel();
		
		//contentPanel textPanel background and layout setting
		contentPanel.setBackground(color);
		textPanel.setBackground(color);
		contentPanel.setLayout(new BorderLayout());
		textPanel.setLayout(new BorderLayout());
		workplace.setLayout(new BorderLayout());
		
		//排版
		contentPanel.add(title,BorderLayout.NORTH);
		textPanel.add(contentLabel,BorderLayout.CENTER);
		contentPanel.add(textPanel);
		workplace.add(contentPanel);
		
		//set the copy
		returnPost = new JPanel();
		returnPost = textPanel;
		
		add(workplace);
		workplace.setBackground(color);
		
		//realize toolBar setLoc
		toolBar = new JPanel();
		setLoc = new JPanel();
		
		//toolBar,setLoc background and layout setting
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.setBackground(new Color(204,102,0));
		setLoc.setLayout(new GridLayout(1,2));
		setLoc.setBackground(new Color(204,102,0));
		
		//set picture
		heart = new ImageIcon("src/like.png");
		noheart = new ImageIcon("src/unlike.png");
		Image im1 = heart.getImage();
		Image im2 = noheart.getImage();
		im1 = im1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		im2 = im2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		heart = new ImageIcon(im1);
		noheart = new ImageIcon(im2);
		
		
		//realize the buttons
		likeIcon = new JButton(noheart);
		editButton = new JButton("編輯");
		newPostButton = new JButton("全新貼文");
		
		
		//realize text area and buttons
		editContent = new JTextArea(100,100);
		saveButton = new JButton("Save");
		saveAsButton = new JButton("Save as");
		importButton = new JButton("Import");
		cancelButton = new JButton("Cancel");
						
		
		//realize the action listener
		editHandler handler = new editHandler();
		editButton.addActionListener(handler);
		likeIcon.setEnabled(false);
		
		saveButton.setVisible(false);
		saveAsButton.setVisible(false);
		importButton.setVisible(false);
		cancelButton.setVisible(false);
		
		cancelHandler cancelH = new cancelHandler();
		cancelButton.addActionListener(cancelH);
		
		newPostHandler nphandler = new newPostHandler();
		newPostButton.addActionListener(nphandler);
		
		saveHandler shandler = new saveHandler();
		saveButton.addActionListener(shandler);
		
				
		//排版	
		setLoc.add(editButton);
		setLoc.add(newPostButton);
		
		toolBar.add(likeIcon);	
		toolBar.add(setLoc);
		toolBar.add(saveButton);
		toolBar.add(saveAsButton);
		toolBar.add(importButton);
		toolBar.add(cancelButton);
		
		workplace.add(toolBar,BorderLayout.SOUTH);
	
	}
	
	private class editHandler implements ActionListener{
		@Override
	      public void actionPerformed(ActionEvent e)
	      {
			//content setting
			//textPanel.remove(contentLabel);
			contentLabel.setVisible(false);
			editContent.setVisible(true);
			editContent.setFont(font);
			editContent.setText(editWord);
			textPanel.add(editContent);
			textPanel.validate();
			textPanel.repaint();

			//Button setting
			likeIcon.setVisible(false);
			setLoc.setVisible(false);
			saveButton.setVisible(true);
			saveAsButton.setVisible(true);
			importButton.setVisible(true);
			cancelButton.setVisible(true);

	      } 				
	}
	
	private class cancelHandler implements ActionListener{
		@Override
	      public void actionPerformed(ActionEvent e)
	      {					
			likeIcon.setVisible(true);
			setLoc.setVisible(true);
			saveButton.setVisible(false);
			saveAsButton.setVisible(false);
			importButton.setVisible(false);
			cancelButton.setVisible(false);		
			
			contentLabel.setVisible(true);
			editContent.setVisible(false);
			
			editContent.setText(null);		
	      }
	}
	
	private class saveHandler implements ActionListener{
		@Override
	      public void actionPerformed(ActionEvent e)
	      {
			content = editContent.getText();
			post.setContent(content);
			contentLabel.setText(post.getContent());
			
			editContent.setVisible(false);
			contentLabel.setVisible(true);
			likeIcon.setVisible(true);
			setLoc.setVisible(true);
			saveButton.setVisible(false);
			saveAsButton.setVisible(false);
			importButton.setVisible(false);
			cancelButton.setVisible(false);	
					
	      }
	}
	
	private class newPostHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//content setting
			
			contentLabel.setVisible(false);
			editContent.setVisible(true);
			editContent.setText(null);
			editContent.setFont(font);
			textPanel.add(editContent);
			textPanel.validate();
			textPanel.repaint();

			//Button setting
			likeIcon.setVisible(false);
			setLoc.setVisible(false);
			saveButton.setVisible(true);
			saveAsButton.setVisible(true);
			importButton.setVisible(true);
			cancelButton.setVisible(true);
			
		}
	}
	
	private class saveAsHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
	