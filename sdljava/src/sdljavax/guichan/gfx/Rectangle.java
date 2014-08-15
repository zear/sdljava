/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

/**
 * This class is a basic rectangle class.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Rectangle {
	public int	x;
	public int	y;
	public int	width;
	public int	height;

	/**
	 * Constructor. Resets member variables.
	 */
	public Rectangle() {
		this(0, 0, 0, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            the rectangle x coordinate
	 * @param y
	 *            the rectangle y coordinate
	 * @param width
	 *            the rectangle width
	 * @param height
	 *            the rectangle height
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Object clone() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * This functions sets the dimension of a rectangle.
	 * 
	 * @param x
	 *            the rectangle x coordinate
	 * @param y
	 *            the rectangle y coordinate
	 * @param width
	 *            the rectangle width
	 * @param height
	 *            the rectangle height
	 */
	public void setAll(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * This functinion checks if another rectangle intersects with the rectangle.
	 * 
	 * @param rectangle
	 *            another rectangle
	 * @return true if both rectangles intersect
	 */
	public boolean intersect(final Rectangle rectangle) {
		x -= rectangle.x;
		y -= rectangle.y;

		if (x < 0) {
			width += x;
			x = 0;
		}

		if (y < 0) {
			height += y;
			y = 0;
		}

		if (x + width > rectangle.width) {
			width = rectangle.width - x;
		}

		if (y + height > rectangle.height) {
			height = rectangle.height - y;
		}

		if (width <= 0 || height <= 0) {
			height = 0;
			width = 0;
			x += rectangle.x;
			y += rectangle.y;
			return false;
		}

		x += rectangle.x;
		y += rectangle.y;
		return true;
	}

	/**
	 * This function check if a point is inside the rectangle
	 * 
	 * @param x
	 *            the point x coordinate
	 * @param y
	 *            the point y coordinate
	 * @return true if the point is inside the rectangle
	 */
	public boolean isPointInRect(int x, int y) {
		return ((x >= this.x) && (y >= this.y) && x < (this.x + this.width) && y < (this.y + this.height));
	}
}
