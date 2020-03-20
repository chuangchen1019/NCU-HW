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
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;


public class Painter extends JFrame{

	private String details;
	private JLabel comboTitle;
	private JLabel brushTitle;
	private JLabel fillTitle;
	private JLabel statusBar;
	private JButton frontButton;
	private JButton backButton;
	private JButton cleanButton;
	private JButton eraserButton;
	private JButton setInit;
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
	private static String[] penSizeOption = {"小","中","大"};
	private JRadioButton[] buttonOptions;
	private Color color;
	
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
			
					if(event.getStateChange() == ItemEvent.SELECTED) {
						System.out.println("選擇 "+optionsName[penToolOptions.getSelectedIndex()]);
						//System.out.println(ItemEvent.);
						if(penToolOptions.getSelectedIndex() == 0) {
							paintPanel.setToolFlag(0);	
						}
						else if(penToolOptions.getSelectedIndex() == 1) {
							paintPanel.setToolFlag(1);
						}
						else if(penToolOptions.getSelectedIndex() == 2) {
							paintPanel.setToolFlag(2);
						}
						else if(penToolOptions.getSelectedIndex() == 3) {
							paintPanel.setToolFlag(3);
						}
						else if(penToolOptions.getSelectedIndex() == 4) {
							paintPanel.setToolFlag(4);
						}
							
					}
				}
			}
		);
	
		add(Tools,BorderLayout.NORTH);//add Tools to the frame.
		
		
		//實作radio button
		brushTitle = new JLabel("筆刷大小");
	
		buttonOptions = new JRadioButton[3];
		radioGroup = new ButtonGroup();
		brushPanel = new JPanel();
		brushPanel.setLayout(new GridLayout(2,2));
		brushPanelBranch = new JPanel();
		brushPanelBranch.setLayout(new FlowLayout(FlowLayout.LEFT));
		RadioButtonHandler handler = new RadioButtonHandler();
		
		for(int i=0; i<buttonOptions.length; i++) {
			buttonOptions[i] = new JRadioButton(penSizeOption[i],false);
			radioGroup.add(buttonOptions[i]);
			brushPanelBranch.add(buttonOptions[i]);
			buttonOptions[i].addItemListener(handler);
		}
		brushPanel.add(comboTitle);
		brushPanel.add(brushTitle);
		brushPanel.add(penToolOptions);
		brushPanel.add(brushPanelBranch);	
		Tools.add(brushPanel);

		
		//Implement the check box
		filledOption = new JCheckBox();
		fillTitle = new JLabel("填滿");
		checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(2,1));
		checkPanel.add(fillTitle);
		checkPanel.add(filledOption);
		//Tools.add(checkPanel);
		
		
		filledOption.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event){
				
				if(penToolOptions.getSelectedIndex() == 0) {
				
					filledOption.setSelected(false);
					
				}	
				else{
					
					if(filledOption.isSelected()) {
						System.out.println("選擇填滿");
						paintPanel.doFill(true);
					}
					else {	
						System.out.println("取消填滿");
						paintPanel.doFill(false);
											}
					}
				}
			});
		
		//create the last four buttons
		frontButton = new JButton();
		backButton = new JButton();
		cleanButton = new JButton();
		eraserButton = new JButton();
		setInit = new JButton();
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		frontButton.setText("前景色");
		backButton.setText("背景色");
		cleanButton.setText("清除畫面");
		eraserButton.setText("橡皮擦");
		setInit.setText("回復預設");
		
		frontButton.addActionListener(buttonHandler);
		backButton.addActionListener(buttonHandler);
		cleanButton.addActionListener(buttonHandler);
		eraserButton.addActionListener(buttonHandler);
		setInit.addActionListener(buttonHandler);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,6));
		buttonPanel.add(checkPanel);
		buttonPanel.add(frontButton);
		buttonPanel.add(backButton);
		buttonPanel.add(cleanButton);
		buttonPanel.add(eraserButton);
		buttonPanel.add(setInit);
		
		
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
	    	 if(buttonOptions[0].isSelected()) {
	    		 System.out.println("選擇 "+penSizeOption[0]+" 筆刷");
	    		 paintPanel.setPenSize(5);
	    	 }
	    	 else if(buttonOptions[1].isSelected()) {
	    		 System.out.println("選擇 "+penSizeOption[1]+" 筆刷");
	    		 paintPanel.setPenSize(15);
	    	 }
	    	 else if(buttonOptions[2].isSelected()) {
	    		 System.out.println("選擇 "+penSizeOption[2]+" 筆刷");
	    		 paintPanel.setPenSize(30);
	    	 }
	      }
	}
	
	private class ButtonHandler implements ActionListener{
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	    	  System.out.println("點選 "+event.getActionCommand());
	    	  
	    	  if(event.getActionCommand() == "前景色") {
	    		  paintPanel.changePenColor();	  
	    	  }
	    	  else if(event.getActionCommand() == "背景色") {
	    		  color = paintPanel.setColor();    
	    	  }
	    	  else if(event.getActionCommand() == "清除畫面") {
	 
	    		  paintPanel.clearPic();
	    	  }
	    	  else if(event.getActionCommand() == "橡皮擦") {
	    		  paintPanel.setUse(true);
	    		  paintPanel.setToolFlag(5);
	    	  }

	    	  else if(event.getActionCommand() == "回復預設") {
	    		  paintPanel.refresh();
	    	  }
	    		  	 
	      }
	} 
	
	private class MouseHandler extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent event) {
			details = String.format("游標位置：(%d,%d)", event.getX(),event.getY());
			statusBar.setText(details);		
		}
		
		public void mouseDragged(MouseEvent event){
			details = String.format("游標位置：(%d,%d)", event.getX(),event.getY());
			statusBar.setText(details);	
		}
	}	
}








