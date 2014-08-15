/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.font;

import java.awt.Point;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.ImageLoader;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is an implementation of the Font class. It uses an image containing the font. You can use
 * any filetype that can be loaded with your ImageLoader.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class ImageFont implements Font {
	private String	m_strFilename;
	private int		m_nGlyphSpacing	= 0;
	private int[]	m_arrGlyphX		= new int[256];
	private int[]	m_arrGlyphY		= new int[256];
	private int[]	m_arrGlyphW		= new int[256];
	private int[]	m_arrGlyphH		= new int[256];

	private int		m_nHeight;
	private Image	m_image;
	private int		m_nRowSpacing	= 0;

	/**
	 * This constructor takes an image file containing the font and two boundaries of ASCII values.
	 * The font image should include all glyphs specified with the boundaries in increasing ASCII
	 * order. The boundaries are inclusive.
	 * 
	 * @param strFileName
	 *            the filename of the image
	 * @param cGlyphsFrom
	 *            the ASCII value of the first glyph found in the image.
	 * @param cGlyphsTo
	 *            the ASCII value of the last glyph found in the image.
	 * @throws GUIException
	 *             when glyph bondaries are incorrect or the font file is corrupt or if no
	 *             ImageLoader exists.
	 */
	public ImageFont(final String strFileName, char cGlyphsFrom, char cGlyphsTo) throws GUIException {
		this(strFileName, cGlyphsFrom, cGlyphsTo, null);
	}

	/**
	 * This constructor takes an image file containing the font and two boundaries of ASCII values.
	 * The font image should include all glyphs between 32 and 126 with the boundaries in increasing
	 * ASCII order. The boundaries are inclusive.
	 * 
	 * @param strFileName
	 *            the filename of the image
	 * @throws GUIException
	 *             when glyph bondaries are incorrect or the font file is corrupt or if no
	 *             ImageLoader exists.
	 */
	public ImageFont(final String strFileName) throws GUIException {
		this(strFileName, (char) 32, (char) 126, null);
	}

	/**
	 * This constructor takes an image file containing the font and a string containing the glyphs.
	 * The glyphs in the string should be in the same order as they appear in the font image.
	 * 
	 * @param strFileName
	 *            the filename of the image.
	 * @param strGlyphs
	 *            the glyphs found in the image.
	 * @throws GUIException
	 *             when glyph list is incorrect or the font file is corrupt or if no ImageLoader
	 *             exists.
	 */
	public ImageFont(final String strFileName, final String strGlyphs) throws GUIException {
		this(strFileName, ' ', ' ', strGlyphs);
	}

	/**
	 * This is the actual constructor which does all of the work
	 * 
	 * @param strFileName
	 *            the filename of the image.
	 * @param cGlyphsFrom
	 *            the ASCII value of the first glyph found in the image (used if glyphs is null).
	 * @param cGlyphsTo
	 *            the ASCII value of the last glyph found in the image (used if glyphs is null).
	 * @param strGlyphs
	 *            the glyphs found in the image. If null, glyphsFrom and glyphsTo will be used
	 * @throws GUIException
	 *             when glyph list is incorrect or the font file is corrupt or if no ImageLoader
	 *             exists.
	 */
	protected ImageFont(final String strFileName, char cGlyphsFrom, char cGlyphsTo, final String strGlyphs)
		throws GUIException {
		ImageLoader loader = Image.getImageLoader();

		if (null == loader) {
			throw new GUIException("I have no ImageLoader!");
		}

		m_strFilename = strFileName;

		for (int i = 0; i < 256; i++) {
			m_arrGlyphX[i] = m_arrGlyphW[i] = 0;
		}

		loader.prepare(strFileName);
		Color separator = loader.getPixel(0, 0);

		int i;
		for (i = 0; separator == loader.getPixel(i, 0) && i < loader.getWidth(); i++) {
		}

		if (i >= loader.getWidth()) {
			throw new GUIException("Corrupt image.");
		}

		int j = 0;
		for (j = 0; j < loader.getHeight(); ++j) {
			if (separator == loader.getPixel(i, j)) {
				break;
			}
		}

		m_nHeight = j;

		Point pos = new Point(0, 0);
		// Calculate glyphs from a String
		if (null != strGlyphs) {
			char k;
			for (int g = 0; g < strGlyphs.length(); ++g) {
				k = strGlyphs.charAt(g);
				addGlyph(k, pos, separator);
			}
		} else { // Calculate from a range of characters
			for (char g = cGlyphsFrom; g < cGlyphsTo + 1; g++) {
				addGlyph(g, pos, separator);
			}
		}

		int w = loader.getWidth();
		int h = loader.getHeight();

		m_image = new Image(loader.finish(), w, h);
		m_nHeight = h;
	}

	private void addGlyph(char c, Point pos, final Color separator) throws GUIException {
		ImageLoader il = Image.getImageLoader();

		Color color;
		do {
			pos.x++;
			if (pos.x >= il.getWidth()) {
				throw new GUIException("Image "
					+ m_strFilename
					+ " with font is corrupt near character '"
					+ c
					+ "'");
			}
			color = il.getPixel(pos.x, 0);
		} while (color.equals(separator));

		int w = 0;

		do {
			++w;
			if (pos.x + w >= il.getWidth()) {
				throw new GUIException("Image "
					+ m_strFilename
					+ " with font is corrupt near character '"
					+ c
					+ "'");
			}
			color = il.getPixel(pos.x + w, 0);
		} while (false == color.equals(separator));

		m_arrGlyphX[c] = pos.x;
		m_arrGlyphY[c] = pos.y;
		m_arrGlyphW[c] = w;
		m_arrGlyphH[c] = m_nHeight;

		pos.x += w;
	}

	/**
	 * Destructor.
	 */
	public void delete() throws GUIException {
		Image.getImageLoader().free(m_image);
		m_image.delete();
	}

	/**
	 * Draws a glyph.
	 * 
	 * NOTE: You normally won't use this function to draw text since the Graphics class contains
	 * better functions for drawing text.
	 * 
	 * @param graphics
	 *            a graphics object to be used for drawing.
	 * @param glyph
	 *            a glyph to draw.
	 * @param x
	 *            the x coordinate where to draw the glyph.
	 * @param y
	 *            the y coordinate where to draw the glyph.
	 * @return the width of the glyph in pixels.
	 * @see Graphics
	 */
	public int drawGlyph(Graphics graphics, final char glyph, int x, int y) throws GUIException {
		// This is needed for drawing the Glyph in the middle if we have spacing
		int nYOffset = getRowSpacing() >> 1;

		if (0 == m_arrGlyphW[glyph]) {
			graphics.drawRectangle(new Rectangle(x, y + 1 + nYOffset, m_arrGlyphW[(' ')] - 1, m_arrGlyphH[(' ')] - 2));
			return m_arrGlyphW[(' ')] + m_nGlyphSpacing;
		}

		graphics.drawImage(m_image, m_arrGlyphX[glyph], m_arrGlyphY[glyph], x, y + nYOffset, m_arrGlyphW[glyph], m_arrGlyphH[glyph]);
		return m_arrGlyphW[glyph] + m_nGlyphSpacing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.font.Font#drawString(sdljavax.guichan.Graphics, java.lang.String, int,
	 *      int)
	 */
	public void drawString(Graphics graphics, String strText, int x, int y) throws GUIException {
		for (int i = 0; i < strText.length(); ++i) {
			char theChar = strText.charAt(i);
			drawGlyph(graphics, theChar, x, y);
			x += getWidth(theChar);
		}
	}

	/**
	 * Gets the spacing between letters, in pixels.
	 * 
	 * @return the spacing.
	 */
	public int getGlyphSpacing() {
		return m_nGlyphSpacing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.Font#getHeight()
	 */
	public int getHeight() {
		return m_nHeight + m_nRowSpacing;
	}

	/**
	 * Gets the spacing between rows, in pixels.
	 * 
	 * @return the spacing.
	 */
	public int getRowSpacing() {
		return m_nRowSpacing;
	}

	/**
	 * Gets a width of a glyph.
	 * 
	 * @param glyph
	 *            the glyph which width will be returned
	 * @return the width of a glyph
	 */
	public int getWidth(final char glyph) {
		if (0 == m_arrGlyphW[glyph]) {
			return m_arrGlyphW[(' ')] + m_nGlyphSpacing;
		}
		return m_arrGlyphW[glyph] + m_nGlyphSpacing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.font.Font#getWidth(String strText)
	 */
	public int getWidth(String strText) {
		int size = 0;
		for (int i = 0; i < strText.length(); ++i) {
			size += getWidth(strText.charAt(i));
		}
		return size;
	}

	/**
	 * Sets the spacing between letters, in pixels. Default is 0 pixels. The spacing can be
	 * negative.
	 * 
	 * @param spacing
	 *            the spacing in pixels
	 */
	public void setGlyphSpacing(int spacing) {
		m_nGlyphSpacing = spacing;
	}

	/**
	 * Sets the spacing between rows, in pixels. Default is 0 pixels. The spacing can be negative.
	 * 
	 * @param rowSpacing
	 *            the spacing in pixels
	 */
	public void setRowSpacing(int rowSpacing) {
		m_nRowSpacing = rowSpacing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.font.Font#getStringIndexAt(java.lang.String, int)
	 */
	public int getStringIndexAt(String strText, int x) {
		int size = 0;
		for (int i = 0; i < strText.length(); ++i) {
			size += getWidth(strText.charAt(i));
			if (size > x) {
				return i;
			}
		}
		return strText.length();
	}
}
