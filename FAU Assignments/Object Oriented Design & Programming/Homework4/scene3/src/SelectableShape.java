import java.awt.*;
import java.awt.geom.*;

/**
 * A shape that manages its selection state.
 */
public abstract class SelectableShape implements SceneShape {
	public void setSelected(boolean b) {
		selected = b;
	}

	public boolean isSelected() {
		return selected;
	}

	public void drawSelection(Graphics2D g2) {
		Rectangle2D bounds = getBounds();
		bounds.setRect(bounds.getMinX() - 5, bounds.getMinY() - 5,
				bounds.getWidth() + 10, bounds.getHeight() + 10);

		final float dash1[] = {4.0f};
		final BasicStroke dashed = new BasicStroke(2.0f,
				BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
				0.0f);
		
		Graphics2D g2d = (Graphics2D) g2.create();
		
		g2d.setStroke(dashed);
		g2d.setColor(Color.BLUE);
		g2d.draw(bounds);
		
		Rectangle2D corner1 = new Rectangle2D.Double(bounds.getMinX() - 3, bounds.getMinY() - 3, 6, 6);
		Rectangle2D corner2 = new Rectangle2D.Double(bounds.getMaxX() - 3, bounds.getMinY() - 3, 6, 6);
		Rectangle2D corner3 = new Rectangle2D.Double(bounds.getMinX() - 3, bounds.getMaxY() - 3, 6, 6);
		Rectangle2D corner4 = new Rectangle2D.Double(bounds.getMaxX() - 3, bounds.getMaxY() - 3, 6, 6);
		
		g2.setColor(Color.BLUE);
		g2.fill(corner1);
		g2.fill(corner2);
		g2.fill(corner3);
		g2.fill(corner4);
		
		
		g2.draw(corner1);
		g2.draw(corner2);
		g2.draw(corner3);
		g2.draw(corner4);
		
		
		g2.setColor(Color.BLACK);
		
	}

	private boolean selected;
}
