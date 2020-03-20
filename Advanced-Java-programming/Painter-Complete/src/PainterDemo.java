import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class PainterDemo {

	public static void main(String[] args) {

		Painter painter = new Painter();	
		painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painter.setSize(1000,500);
		painter.setVisible(true);
		
		//JOptionPane.showMessageDialog(painter,"wellcome");
	}

}
