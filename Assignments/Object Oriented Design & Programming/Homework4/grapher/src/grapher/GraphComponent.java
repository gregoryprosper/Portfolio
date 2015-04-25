package grapher;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.JComponent;

/**
 * @author Gregory Prosper
 * Component which draws bar graphs
 */
public class GraphComponent extends JComponent {

	private final int DEFAULT_WIDTH = 300;
	private final int DEFAULT_HEIGHT = 200;
	private Timer timer = new Timer();
	private Rectangle2D rect1;
	private Rectangle2D rect2;
	private int data1;
	private int data2;
	private boolean isAnimating;

	/**
	 * Creates Graph with bars initialized to x1 and x2
	 * @param x1 number for bar 1
	 * @param x2 number for bar 2
	 */
	public GraphComponent(int x1, int x2) {
		this.data1 = x1;
		this.data2 = x2;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setPaint(Color.RED);
		rect1 = new Rectangle2D.Double(10, 7, toPercentage(this.data1)
				* DEFAULT_WIDTH, 40);
		g2.draw(rect1);
		g2.fill(rect1);

		g2.setPaint(Color.BLUE);
		rect2 = new Rectangle2D.Double(10, 65, toPercentage(this.data2)
				* DEFAULT_WIDTH, 40);
		g2.draw(rect2);
		g2.fill(rect2);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Updates bar graph values
	 * @param x1 value for bar 1
	 * @param x2 value for bar 2
	 */
	public void update(int x1, int x2) {
		if (isAnimating){
			this.timer.cancel();
			this.timer = new Timer();
		}
		this.timer.schedule(new Animate(x1, x2), 0, 10);
	}

	/**
	 * @author Gregory Prosper
	 * TimerTask which animates the update of bar graphs
	 */
	private class Animate extends TimerTask {

		/**
		 * Creates Animate object with numbers to be updated to
		 * @param x1 value for bar 1
		 * @param x2 value for bar 2
		 */
		public Animate(int x1, int x2) {
			this.x1 = x1;
			this.x2 = x2;
		}

		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			isAnimating = true;
			
			if (this.x1 > data1) {
				data1++;
			} else if (this.x1 < data1) {
				data1--;
			}

			if (this.x2 > data2) {
				data2++;
			} else if (this.x2 < data2) {
				data2--;
			}

			if (this.x1 == data1 && this.x2 == data2) {
				cancel();
				isAnimating = false;
			}

			repaint();
		}

		private int x1;
		private int x2;
	}

	/**
	 * Changes regular number into percentage
	 * @param i number to be converted
	 * @return regular number as percentage
	 */
	private float toPercentage(int i) {
		return (float) i / (float) 100;
	}
}
