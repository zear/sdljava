/*
 * Created on Mar 5, 2005 (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.font;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is the ugly font used by widgets by default.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class DefaultFont implements Font {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#getWidth(java.lang.String)
	 */
	public int getWidth(String strText) {
		return 8 * strText.length();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#getHeight()
	 */
	public int getHeight() {
		return 8;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#getStringIndexAt(java.lang.String, int)
	 */
	public int getStringIndexAt(String strText, int x) {
		if (x > strText.length() * 8) {
			return strText.length();
		}

		return x / 8;
	}

	/**
	 * Draw a character at a given position
	 * 
	 * @param graphics
	 *            Graphics object
	 * @param glyph
	 *            The character to draw
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 * @return the width of the drawn character
	 * @throws GUIException 
	 */
	public int drawGlyph(Graphics graphics, char glyph, int x, int y) throws GUIException {
		graphics.drawRectangle(new Rectangle(x, y, 8, 8));
		return 8;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#drawString(sdljavax.guichan.Graphics, java.lang.String, int, int)
	 */
	public void drawString(Graphics graphics, String strText, int x, int y) throws GUIException {
		for (int i = 0; i < strText.length(); ++i) {
			drawGlyph(graphics, strText.charAt(i), x, y);
			x += getWidth(strText);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#delete()
	 */
	public void delete() throws GUIException {
	}
}
