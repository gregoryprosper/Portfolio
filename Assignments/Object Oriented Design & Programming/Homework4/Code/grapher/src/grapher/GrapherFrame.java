package grapher;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Gregory Prosper A frame which contains a TextPanel and GraphComponent
 * */
public class GrapherFrame extends JFrame {

	private JPanel mainPanel;
	private TextPanel textPanel;
	private GraphComponent graph;
	private JLabel label;
	private Data data;

	/**
	 * Creates Grapher Frame
	 */
	public GrapherFrame() {

		// Create Data Object
		data = new Data();

		// Create ChangeListener for Data Objec
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				graph.update(data.getData1(), data.getData2());
			}
		};

		// Add Listener to Data Object
		data.addListener(listener);

		// initialize components
		mainPanel = new JPanel();
		label = new JLabel("Choose numbers between 0 to 100", JLabel.CENTER);
		graph = new GraphComponent(data.getData1(), data.getData2());
		textPanel = new TextPanel();

		// Create textfield 1 for textfield panel and add Document Listener to
		// it
		final JTextField field1 = new JTextField("0", 10);
		field1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String s = field1.getText();
				if (s.length() > 0) {
					data.changeData1(Integer.parseInt(s));
				} else
					data.changeData1(0);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String s = field1.getText();
				if (s.length() > 0) {
					try {
						int number = Integer.parseInt(s);
						if (number > 100 || number < 0)
							throw new NumberFormatException();
						data.changeData1(number);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Choose numbers between 0 to 100");

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								field1.setText("0");
							}
						});
					}
				} else
					data.changeData1(0);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// Create textfield 2 for textfield panel and add Document Listener to
		// it
		final JTextField field2 = new JTextField("0", 10);
		field2.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String s = field2.getText();
				if (s.length() > 0) {
					data.changeData2(Integer.parseInt(s));
				} else
					data.changeData2(0);

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String s = field2.getText();
				if (s.length() > 0) {
					try {
						int number = Integer.parseInt(s);
						if (number > 100 || number < 0)
							throw new NumberFormatException();
						data.changeData2(number);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Choose numbers between 0 to 100");

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								field2.setText("0");
							}
						});

					}
				} else
					data.changeData2(0);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// Add text fields to textPanel
		textPanel.addTextField(field1);
		textPanel.addTextField(field2);

		// Set mainPanel layout
		mainPanel.setLayout(new BorderLayout());

		// Add components to mainPanel
		mainPanel.add(this.label, BorderLayout.NORTH);
		mainPanel.add(this.textPanel, BorderLayout.WEST);
		mainPanel.add(this.graph, BorderLayout.CENTER);
		add(mainPanel);
		setSize(500, 150);
		setResizable(false);
	}
}
