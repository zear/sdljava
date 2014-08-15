/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

/**
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */

/**
 * ClipRectangle is used internally by the graphics object. It is a regular Rectangle, extended with
 * variables xOffset and yOffset.
 */
public class ClipRectangle extends Rectangle {
	/**
	 * x-origin of drawing (used by the Graphics object)
	 */
	public int	xOffset	= 0;
	/**
	 * y-origin of drawing (used by the Graphics object)
	 */
	public int	yOffset	= 0;

	/**
	 * Constructor
	 */
	public ClipRectangle() {
		this(0, 0, 0, 0, 0, 0);
	}

	public ClipRectangle(Rectangle rect) {
		this(rect.x, rect.y, rect.width, rect.height, 0, 0);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            the rectangle x coordinate
	 * @param y
	 *            the rectangle y coordinate
	 * @param width
	 *            the rectangle width
	 * @param height
	 *            the rectangle height
	 * @param xOffset
	 *            origin of drawing (used by the Graphics object)
	 * @param yOffset
	 *            origin of drawing (used by the Graphics object)
	 */
	public ClipRectangle(int x, int y, int width, int height, int xOffset, int yOffset) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Copies x, y, width and height field from a Rectangle.
	 * 
	 * @param source
	 *            the Rectangle to copy from
	 * @return a reference to this ClipRectangle
	 */
	public ClipRectangle copyFrom(Rectangle source) {
		setAll(source.x, source.y, source.width, source.height);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Rectangle#copy()
	 */
	public Rectangle copy() {
		return new ClipRectangle(x, y, width, height, xOffset, yOffset);
	}
}
