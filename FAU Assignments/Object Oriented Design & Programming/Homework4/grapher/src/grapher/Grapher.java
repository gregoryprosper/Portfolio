package grapher;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Grapher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable (){
			public void run (){
				GrapherFrame frame = new GrapherFrame();
				frame.setTitle("Grapher");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
