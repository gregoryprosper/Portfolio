package grapher;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Gregory Prosper
 * A Panel which contains text fields
 */
public class TextPanel extends JPanel {

	/**
	 * Creates TextPanel with 2 rows and 1 column
	 */
	public TextPanel() {
		setLayout(new GridLayout(2, 1));
	}

	/**
	 * Adds text field to panel
	 * @param field text field to be added to panel
	 */
	public void addTextField(JTextField field) {
		add(field);
	}
}
