package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Gui {

	private static JButton createButton(int i) {
		String[] c = { "Green", "Blue", "Red" };
		JButton button = new JButton(c[i]);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				System.out.println(action.getActionCommand());
				if (action.getActionCommand() == "Green") {
					icon.setColor(Color.GREEN);
					label.repaint();
				} else if (action.getActionCommand() == "Red") {
					icon.setColor(Color.RED);
					label.repaint();
				} else if (action.getActionCommand() == "Blue") {
					icon.setColor(Color.BLUE);
					label.repaint();
				}
			}
		}

		);
		return button;
	}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Color Picker");
		ArrayList<JButton> buttons = new ArrayList<>();

		icon = new ColorIcon(50);
		label = new JLabel(icon);

		for (int i = 0; i < 3; i++) {
			buttons.add(Gui.createButton(i));
		}

		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(label);
		frame.add(buttons.get(0));
		frame.add(buttons.get(1));
		frame.add(buttons.get(2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	private static ColorIcon icon;
	private static JLabel label;
}
