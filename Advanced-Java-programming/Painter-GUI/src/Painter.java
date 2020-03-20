import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class Painter extends JFrame{

	private String details;
	private JLabel comboTitle;
	private JLabel brushTitle;
	private JLabel fillTitle;
	private JLabel statusBar;
	private JButton frontButton;
	private JButton backButton;
	private JButton cleanButton;
	private JCheckBox filledOption;
	private JPanel Tools;
	private JPanel comboPanel;
	private JPanel brushPanel;
	private JPanel brushPanelBranch;
	private JPanel checkPanel;
	private JPanel buttonPanel;
	private PaintPanel paintPanel;
	private JRadioButton smallButton;
	private JRadioButton mediumButton;
	private JRadioButton largeButton;
	private ButtonGroup radioGroup;
	private JComboBox<String> penToolOptions;
	private static String[] optionsName = {"筆刷","直線","橢圓形","矩形","圓角矩形"};
	
	public Painter() {
		
		super("小畫家");
		setLayout(new BorderLayout());
		
		//Create tool list panel and set the layout.
		Tools = new JPanel();
		Tools.setLayout(new GridLayout(1,4,10,10));

		//Create combo box and its title.
		comboTitle = new JLabel("繪圖工具");
		penToolOptions = new JComboBox<String>(optionsName);
		penToolOptions.setMaximumRowCount(3);
		
		//add itemListener
		penToolOptions.addItemListener(new ItemListener()
			{	
				@Override
				public void itemStateChanged(ItemEvent event){
			
					if(event.getStateChange() == ItemEvent.SELECTED)
						System.out.println("選擇 "+optionsName[penToolOptions.getSelectedIndex()]);						
				}
			}
		);
	
		//Create a panel for the combo box component.(方便排版)
		comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(2,1));
		comboPanel.add(comboTitle);
		comboPanel.add(penToolOptions);
		Tools.add(comboPanel);//add the panel to Tools.
		add(Tools,BorderLayout.NORTH);//add Tools to the frame.
		
		
		//實作radio button
		brushTitle = new JLabel("筆刷大小");
		smallButton = new JRadioButton("小",false);
		mediumButton = new JRadioButton("中",false);
		largeButton = new JRadioButton("大",false);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(smallButton);
		radioGroup.add(mediumButton);
		radioGroup.add(largeButton);
		
		//Add buttons to brushPanel and set the layout
		brushPanel = new JPanel();
		brushPanel.setLayout(new GridLayout(2,1));
		brushPanelBranch = new JPanel();
		brushPanelBranch.setLayout(new GridLayout(1,3));
		brushPanelBranch.add(smallButton);
		brushPanelBranch.add(mediumButton);
		brushPanelBranch.add(largeButton);
		brushPanel.add(brushTitle);
		brushPanel.add(brushPanelBranch);	
		Tools.add(brushPanel);
		
		//Add handler to buttons
		RadioButtonHandler handler = new RadioButtonHandler();
		smallButton.addItemListener(handler);
		mediumButton.addItemListener(handler);
		largeButton.addItemListener(handler);
		
		//Implement the check box
		filledOption = new JCheckBox();
		fillTitle = new JLabel("填滿");
		checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(2,1));
		checkPanel.add(fillTitle);
		checkPanel.add(filledOption);
		Tools.add(checkPanel);
		
		filledOption.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event){
				if(filledOption.isSelected())
					System.out.println("選擇填滿");
				else
					System.out.println("取消填滿");
			}
		});
		
		//create the last three buttons
		frontButton = new JButton();
		backButton = new JButton();
		cleanButton = new JButton();
		ButtonHandler buttonHandler = new ButtonHandler();
		
		frontButton.setText("前景色");
		backButton.setText("背景色");
		cleanButton.setText("清除畫面");
		
		frontButton.addActionListener(buttonHandler);
		backButton.addActionListener(buttonHandler);
		cleanButton.addActionListener(buttonHandler);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(frontButton);
		buttonPanel.add(backButton);
		buttonPanel.add(cleanButton);
		Tools.add(buttonPanel);
		
		paintPanel = new PaintPanel();
		paintPanel.setBackground(Color.WHITE);
		add(paintPanel,BorderLayout.CENTER);
		
		//Create a status bar to record the location of mouse.
		statusBar = new JLabel("游標位置：");
		statusBar.setBackground(Color.BLACK);
		add(statusBar,BorderLayout.SOUTH);
		
		MouseHandler mhandler = new MouseHandler();
		paintPanel.addMouseMotionListener(mhandler);
	
	}
	private class RadioButtonHandler implements ItemListener{
		@Override
	      public void itemStateChanged(ItemEvent event)
	      {
	    	 if(smallButton.isSelected()) 
	    		 System.out.println("選擇 小 筆刷");
	    	 else if(mediumButton.isSelected())
	    		 System.out.println("選擇 中 筆刷");
	    	 else if(largeButton.isSelected())
	    		 System.out.println("選擇 大 筆刷");
	      } 				
	}
	
	private class ButtonHandler implements ActionListener{
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	    	  System.out.println("點選 "+event.getActionCommand());
	      }
	} 
	
	private class MouseHandler extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent event) {
			details = String.format("游標位置：(%d,%d)", event.getX(),event.getY());
			statusBar.setText(details);		
		}
	}	
}








