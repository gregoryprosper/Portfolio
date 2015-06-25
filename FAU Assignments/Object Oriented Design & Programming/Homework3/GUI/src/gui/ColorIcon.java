package gui;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.Icon;

public class ColorIcon implements Icon {

	public ColorIcon(int aSize, Color c) {
		this.size = aSize;
		this.color = c;
	}

	public ColorIcon(int aSize) {
		size = aSize;
		this.color = Color.RED;
	}

	public int getIconWidth() {
		return size;
	}

	public int getIconHeight() {
		return size;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double shape = new Ellipse2D.Double(x, y, size, size);
		g2.setColor(this.color);
		g2.fill(shape);
	}

	private int size;
	private Color color;

}
