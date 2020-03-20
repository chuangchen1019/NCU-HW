import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int flag = JOptionPane.showConfirmDialog(null,"是否為發佈者？","登入",
				JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
		
		if(flag == JOptionPane.YES_OPTION) {
			EditFrame s = new EditFrame();
			s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			s.setSize(650,580);
			s.setResizable(false);
			s.setVisible(true);
		}
		else if(flag == JOptionPane.NO_OPTION) {
			viewFrame v = new viewFrame();
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setSize(650,580);
			v.setResizable(false);
			v.setVisible(true);
		}
		
	}

}
