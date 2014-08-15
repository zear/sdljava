/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;

/**
 * This is a simple labe for displaying a short text message.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Label extends Widget {
	protected int		m_nAlignment;
	protected String	m_strCaption;

	/**
	 * Constructor.
	 */
	public Label() {
		super();
		m_nAlignment = Graphics.LEFT;
	}

	/**
	 * Constructor.
	 * 
	 * @param caption
	 *            the caption of the label.
	 */
	public Label(String caption) {
		this();
		m_strCaption = caption;
		setWidth(getFont().getWidth(caption));
		setHeight(getFont().getHeight());
	}

	/**
	 * Adjusts the Label size to fit the font size.
	 */
	public void adjustSize() {
		setWidth(getFont().getWidth(getCaption()));
		setHeight(getFont().getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		int textX;
		int textY = getHeight() / 2 - getFont().getHeight() / 2;

		switch (getAlignment()) {
			case Graphics.LEFT :
				textX = 0;
				break;
			case Graphics.CENTER :
				textX = getWidth() / 2;
				break;
			case Graphics.RIGHT :
				textX = getWidth();
				break;
			default :
				throw new GUIException("Unknown alignment.");
		}

		graphics.setFont(getFont());
		graphics.setColor(getForegroundColor());
		graphics.drawText(getCaption(), textX, textY, getAlignment());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#drawBorder(sdljavax.guichan.gfx.Graphics)
	 */
	public void drawBorder(Graphics graphics) throws GUIException {
		Color faceColor = getBaseColor();
		Color highlightColor, shadowColor;
		int alpha = getBaseColor().a;
		int width = getWidth() + getBorderSize() * 2 - 1;
		int height = getHeight() + getBorderSize() * 2 - 1;
		highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		for (int i = 0; i < getBorderSize(); ++i) {
			graphics.setColor(shadowColor);
			graphics.drawLine(i, i, width - i, i);
			graphics.drawLine(i, i, i, height - i);
			graphics.setColor(highlightColor);
			graphics.drawLine(width - i, i, width - i, height - i);
			graphics.drawLine(i, height - i, width - i, height - i);
		}
	}

	/**
	 * Get the alignment for the caption.
	 * 
	 * @return alignment of caption.
	 */
	public int getAlignment() {
		return m_nAlignment;
	}

	/**
	 * @return the caption of the Label.
	 */
	public String getCaption() {
		return m_strCaption;
	}

	/**
	 * Set the alignment for the caption.
	 * 
	 * @param alignment
	 *            Graphics.LEFT, Graphics.CENTER or Graphics.RIGHT
	 * @see Graphics
	 */
	public void setAlignment(int alignment) {
		m_nAlignment = alignment;
	}

	/**
	 * Sets the caption of the label.
	 * 
	 * @param caption
	 *            the caption of the Label.
	 */
	public void setCaption(String caption) {
		m_strCaption = caption;
	}
}
