/*
 * Created on Mar 5, 2005 (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.font;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Graphics;

/**
 * Holder of a font. Fonts should implement this interface and its functions.
 * 
 * @see ImageFont
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface Font {
	/**
	 * Gets the width of a string. The width of a string is not necesserily the sum of all the
	 * widths of its glyphs.
	 * 
	 * @param strText
	 *            the string of which width will be returned
	 * @return the width of a string
	 */
	public int getWidth(String strText);

	/**
	 * Gets the height of the glyphs.
	 * 
	 * @return the height of the glyphs
	 */
	public int getHeight();

	/**
	 * Use this function to retrive a string index (for a character in a string) at a certain x
	 * position. This function is especially useful when a mouse clicks in a TextField and you want
	 * to know which character was clicked.
	 * 
	 * @param strText
	 *            The String
	 * @param x
	 *            The position
	 * @return the string index at coordinate x.
	 */
	public int getStringIndexAt(String strText, int x);

	/**
	 * Draws a string. NOTE: You normally won't use this function to draw text since the Graphics
	 * class contains better functions for drawing text.
	 * 
	 * @param graphics
	 *            a graphics object to be used for drawing
	 * @param strText
	 *            the string to draw
	 * @param x
	 *            the x coordinate where to draw the string
	 * @param y
	 *            the y coordinate where to draw the string
	 * @see Graphics
	 */
	public void drawString(Graphics graphics, String strText, int x, int y) throws GUIException;

	/**
	 * Delete this object
	 * 
	 * @throws GUIException
	 */
	public void delete() throws GUIException;
}
