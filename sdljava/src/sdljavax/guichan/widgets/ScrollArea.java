/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Dimension;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.FocusHandler;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;


/**
 * This is a ScrollArea. A ScrollArea can contain another Widget, i.e a TextBox or a ListBox, and
 * lets the user scroll the containing widget so all of the containing widget can be visible dispite
 * lack of space.
 * 
 * NOTE: A TextBox or a ListBox looks really ugly unless they exist in a ScrollArea.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class ScrollArea extends BasicContainer implements MouseListener {
	// Keep this static since it is used a couple of times
	protected static Rectangle	c_rectEmpty	= new Rectangle(0, 0, 0, 0);
	public static final int		SHOW_ALWAYS	= 0;
	public static final int		SHOW_AUTO	= 2;
	public static final int		SHOW_NEVER	= 1;

	protected boolean			m_bDownButtonPressed;
	protected boolean			m_bHBarVisible;
	protected boolean			m_bHorizontalMarkerPressed;
	protected boolean			m_bLeftButtonPressed;
	protected boolean			m_bRightButtonPressed;
	protected boolean			m_bUpButtonPressed;
	protected boolean			m_bVBarVisible;
	protected boolean			m_bVerticalMarkerPressed;
	protected Widget			m_content;
	protected int				m_nHorizontalMarkerMousePosition;
	protected int				m_nHPolicy;
	protected int				m_nHScroll;
	protected int				m_nScrollbarWidth;
	protected int				m_nVerticalMarkerMousePosition;
	protected int				m_nVPolicy;
	protected int				m_nVScroll;

	/**
	 * Constructor.
	 */
	public ScrollArea() throws GUIException {
		this(null, SHOW_AUTO, SHOW_AUTO);
	}

	/**
	 * Constructor.
	 * 
	 * @param content
	 *            the content of the ScrollArea.
	 */
	public ScrollArea(Widget content) throws GUIException {
		this(content, SHOW_AUTO, SHOW_AUTO);
	}

	/**
	 * Constructor.
	 * 
	 * @param content
	 *            the content of the ScrollArea.
	 * @param hPolicy
	 *            the policy for the horizontal scrollbar. See constants for policies.
	 * @param vPolicy
	 *            the policy for the vertical scrollbar. See constants for policies.
	 */
	public ScrollArea(Widget content, int hPolicy, int vPolicy) throws GUIException {
		super();
		m_nVScroll = 0;
		m_nHScroll = 0;
		m_nHPolicy = hPolicy;
		m_nVPolicy = vPolicy;
		m_nScrollbarWidth = 12;
		m_bUpButtonPressed = false;
		m_bDownButtonPressed = false;
		m_bLeftButtonPressed = false;
		m_bRightButtonPressed = false;
		m_bVerticalMarkerPressed = false;
		m_nVerticalMarkerMousePosition = 0;
		m_bHorizontalMarkerPressed = false;
		m_nHorizontalMarkerMousePosition = 0;

		if (null != content) {
			setContent(content);
			checkPolicies();
		}
		addMouseListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#announceDeath(sdljavax.guichan.widgets.Widget)
	 */
	protected void announceDeath(Widget widget) throws GUIException {
		if (widget == m_content) {
			m_content = null;
			checkPolicies();
		} else {
			throw new GUIException("Called by non-child");
		}
	}

	/**
	 * Checks the policies for the scrollbars.
	 */
	protected void checkPolicies() throws GUIException {
		int w = getWidth();
		int h = getHeight();

		m_bHBarVisible = false;
		m_bVBarVisible = false;

		if (null == m_content) {
			m_bHBarVisible = (m_nHPolicy == SHOW_ALWAYS);
			m_bVBarVisible = (m_nVPolicy == SHOW_ALWAYS);
			return;
		}

		if (m_nHPolicy == SHOW_AUTO && m_nVPolicy == SHOW_AUTO) {
			if (m_content.getWidth() <= w && m_content.getHeight() <= h) {
				m_bHBarVisible = false;
				m_bVBarVisible = false;
			}

			if (m_content.getWidth() > w) {
				m_bHBarVisible = true;
			}

			if ((m_content.getHeight() > h) || (m_bHBarVisible && m_content.getHeight() > h - m_nScrollbarWidth)) {
				m_bVBarVisible = true;
			}

			if (m_bVBarVisible && m_content.getWidth() > w - m_nScrollbarWidth) {
				m_bHBarVisible = true;
			}

			return;
		}

		switch (m_nHPolicy) {
			case SHOW_NEVER :
				m_bHBarVisible = false;
				break;

			case SHOW_ALWAYS :
				m_bHBarVisible = true;
				break;

			case SHOW_AUTO :
				if (m_nVPolicy == SHOW_NEVER) {
					m_bHBarVisible = m_content.getWidth() > w;
				} else // (m_nVPolicy == SHOW_ALWAYS)
				{
					m_bHBarVisible = m_content.getWidth() > w - m_nScrollbarWidth;
				}
				break;

			default :
				throw new GUIException("Horizontal scroll policy invalid");
		}

		switch (m_nVPolicy) {
			case SHOW_NEVER :
				m_bVBarVisible = false;
				break;

			case SHOW_ALWAYS :
				m_bVBarVisible = true;
				break;

			case SHOW_AUTO :
				if (m_nHPolicy == SHOW_NEVER) {
					m_bVBarVisible = m_content.getHeight() > h;
				} else // (m_nHPolicy == SHOW_ALWAYS)
				{
					m_bVBarVisible = m_content.getHeight() > h - m_nScrollbarWidth;
				}
				break;
			default :
				throw new GUIException("Vertical scroll policy invalid");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#delete()
	 */
	public void delete() throws GUIException {
		setContent(null);
		super.delete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		graphics.setColor(getBackgroundColor());
		graphics.fillRectangle(getContentDimension());

		int alpha = getBaseColor().a;
		Color highlightColor = getBaseColor().add(0x303030);
		highlightColor.a = alpha;
		Color shadowColor = getBaseColor().subtract(0x303030);
		shadowColor.a = alpha;

		if (m_bVBarVisible) {
			drawUpButton(graphics);
			drawDownButton(graphics);
			drawVBar(graphics);
			drawVMarker(graphics);
		}

		if (m_bHBarVisible) {
			drawLeftButton(graphics);
			drawRightButton(graphics);
			drawHBar(graphics);
			drawHMarker(graphics);
		}

		if (m_bHBarVisible && m_bVBarVisible) {
			graphics.setColor(getBaseColor());
			graphics.fillRectangle(new Rectangle(getWidth() - m_nScrollbarWidth, getHeight() - m_nScrollbarWidth,
				m_nScrollbarWidth, m_nScrollbarWidth));
		}

		if (null != m_content) {
			Rectangle contdim = m_content.getDimension();
			graphics.pushClipArea(getContentDimension());

			if (m_content.getBorderSize() > 0) {
				Rectangle rec = m_content.getDimension();
				rec.x -= m_content.getBorderSize();
				rec.y -= m_content.getBorderSize();
				rec.width += 2 * m_content.getBorderSize();
				rec.height += 2 * m_content.getBorderSize();
				graphics.pushClipArea(rec);
				m_content.drawBorder(graphics);
				graphics.popClipArea();
			}

			graphics.pushClipArea(contdim);
			m_content.draw(graphics);
			graphics.popClipArea();
			graphics.popClipArea();
		}
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
	 * Draws the content in the ScrollArea.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawContent(Graphics graphics) throws GUIException {
		if (null != m_content) {
			m_content.draw(graphics);
		}
	}

	/**
	 * Draws the down button.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawDownButton(Graphics graphics) throws GUIException {
		Rectangle dim = getDownButtonDimension();
		graphics.pushClipArea(dim);

		Color highlightColor;
		Color shadowColor;
		Color faceColor;
		int offset;
		int alpha = getBaseColor().a;

		if (m_bDownButtonPressed) {
			faceColor = getBaseColor().subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = getBaseColor();
			shadowColor.a = alpha;

			offset = 1;
		} else {
			faceColor = getBaseColor();
			faceColor.a = alpha;
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;

			offset = 0;
		}

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);

		graphics.setColor(getForegroundColor());

		int w = dim.height / 2;
		int h = w + 1;
		for (int i = 0; i < w / 2; ++i) {
			graphics.drawLine(w - i + offset, -i + h + offset, w + i + offset, -i + h + offset);
		}

		graphics.popClipArea();
	}

	/**
	 * Draws the horizontal scrollbar.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawHBar(Graphics graphics) throws GUIException {
		Rectangle dim = getHorizontalBarDimension();

		graphics.pushClipArea(dim);

		int alpha = getBaseColor().a;
		Color trackColor = getBaseColor().subtract(0x101010);
		trackColor.a = alpha;
		Color shadowColor = getBaseColor().subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(trackColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(shadowColor);
		graphics.drawLine(0, 0, dim.width, 0);

		graphics.popClipArea();
	}

	/**
	 * Draws the horizontal marker.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawHMarker(Graphics graphics) throws GUIException {
		Rectangle dim = getHorizontalMarkerDimension();
		graphics.pushClipArea(dim);

		int alpha = getBaseColor().a;
		Color faceColor = getBaseColor();
		faceColor.a = alpha;
		Color highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		Color shadowColor = faceColor.add(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(1, 1, dim.width - 1, dim.height - 1));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);

		graphics.popClipArea();
	}

	/**
	 * Draws the left button.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawLeftButton(Graphics graphics) throws GUIException {
		Rectangle dim = getLeftButtonDimension();
		graphics.pushClipArea(dim);

		Color highlightColor;
		Color shadowColor;
		Color faceColor;
		int offset;
		int alpha = getBaseColor().a;

		if (m_bLeftButtonPressed) {
			faceColor = getBaseColor().subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = getBaseColor();
			shadowColor.a = alpha;

			offset = 1;
		} else {
			faceColor = getBaseColor();
			faceColor.a = alpha;
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;

			offset = 0;
		}

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);

		graphics.setColor(getForegroundColor());

		int w = dim.width / 2;
		int h = w - 2;
		for (int i = 0; i < w / 2; ++i) {
			graphics.drawLine(i + h + offset, w - i + offset, i + h + offset, w + i + offset);
		}

		graphics.popClipArea();
	}

	/**
	 * Draws the right button.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawRightButton(Graphics graphics) throws GUIException {
		Rectangle dim = getRightButtonDimension();
		graphics.pushClipArea(dim);

		Color highlightColor;
		Color shadowColor;
		Color faceColor;
		int offset;
		int alpha = getBaseColor().a;

		if (m_bRightButtonPressed) {
			faceColor = getBaseColor().subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = getBaseColor();
			shadowColor.a = alpha;

			offset = 1;
		} else {
			faceColor = getBaseColor();
			faceColor.a = alpha;
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;

			offset = 0;
		}

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);

		graphics.setColor(getForegroundColor());

		int w = dim.width / 2;
		int h = w + 1;
		for (int i = 0; i < w / 2; ++i) {
			graphics.drawLine(-i + h + offset, w - i + offset, -i + h + offset, w + i + offset);
		}

		graphics.popClipArea();
	}

	/**
	 * Draws the up button.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawUpButton(Graphics graphics) throws GUIException {
		Rectangle dim = getUpButtonDimension();
		graphics.pushClipArea(dim);

		Color highlightColor;
		Color shadowColor;
		Color faceColor;
		int offset;
		int alpha = getBaseColor().a;

		if (m_bUpButtonPressed) {
			faceColor = getBaseColor().subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = getBaseColor();
			shadowColor.a = alpha;

			offset = 1;
		} else {
			faceColor = getBaseColor();
			faceColor.a = alpha;
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;

			offset = 0;
		}

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);

		graphics.setColor(getForegroundColor());

		int w = dim.height / 2;
		int h = w / 2 + 2;
		for (int i = 0; i < w / 2; ++i) {
			graphics.drawLine(w - i + offset, i + h + offset, w + i + offset, i + h + offset);
		}

		graphics.popClipArea();
	}

	/**
	 * Draws the vertical scrollbar.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawVBar(Graphics graphics) throws GUIException {
		Rectangle dim = getVerticalBarDimension();

		graphics.pushClipArea(dim);

		int alpha = getBaseColor().a;
		Color trackColor = getBaseColor().subtract(0x101010);
		trackColor.a = alpha;
		Color shadowColor = getBaseColor().subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(trackColor);
		graphics.fillRectangle(new Rectangle(0, 0, dim.width, dim.height));

		graphics.setColor(shadowColor);
		graphics.drawLine(0, 0, 0, dim.height);

		graphics.popClipArea();
	}

	/**
	 * Draws the vertical marker.
	 * 
	 * @param graphics
	 *            a Graphics object
	 */
	protected void drawVMarker(Graphics graphics) throws GUIException {
		Rectangle dim = getVerticalMarkerDimension();
		graphics.pushClipArea(dim);

		int alpha = getBaseColor().a;
		Color faceColor = getBaseColor();
		faceColor.a = alpha;
		Color highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		Color shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(1, 1, dim.width - 1, dim.height - 1));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, dim.width - 1, 0);
		graphics.drawLine(0, 1, 0, dim.height - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(1, dim.height - 1, dim.width - 1, dim.height - 1);
		graphics.drawLine(dim.width - 1, 0, dim.width - 1, dim.height - 1);

		graphics.popClipArea();
	}

	/**
	 * @return the content of the ScrollArea.
	 */
	public Widget getContent() {
		return m_content;
	}

	/**
	 * @return the dimension of the content button.
	 */
	protected Rectangle getContentDimension() {
		if (m_bVBarVisible && m_bHBarVisible) {
			return new Rectangle(0, 0, getWidth() - m_nScrollbarWidth, getHeight() - m_nScrollbarWidth);
		}

		if (m_bVBarVisible) {
			return new Rectangle(0, 0, getWidth() - m_nScrollbarWidth, getHeight());
		}

		if (m_bHBarVisible) {
			return new Rectangle(0, 0, getWidth(), getHeight() - m_nScrollbarWidth);
		}

		return new Rectangle(0, 0, getWidth(), getHeight());
	}

	/**
	 * @return the dimension of the down button.
	 */
	protected Rectangle getDownButtonDimension() {
		if (false == m_bVBarVisible) {
			return c_rectEmpty;
		}

		if (m_bVBarVisible && m_bHBarVisible) {
			return new Rectangle(getWidth() - m_nScrollbarWidth, getHeight() - m_nScrollbarWidth * 2,
				m_nScrollbarWidth, m_nScrollbarWidth);
		}

		return new Rectangle(getWidth() - m_nScrollbarWidth, getHeight() - m_nScrollbarWidth, m_nScrollbarWidth,
			m_nScrollbarWidth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#getDrawSize(sdljavax.guichan.widgets.Widget)
	 */
	public Dimension getDrawSize(Widget widget) throws GUIException {
		if (m_content == widget) {
			Dimension dimension = new Dimension();
			dimension.width = getContentDimension().width;
			dimension.height = getContentDimension().height;
			return dimension;
		}
		throw new GUIException("Widget not in scrollarea");
	}

	/**
	 * @return the dimension of the horizontal scrollbar.
	 */
	protected Rectangle getHorizontalBarDimension() {
		if (false == m_bHBarVisible) {
			return c_rectEmpty;
		}

		if (m_bVBarVisible) {
			return new Rectangle(getLeftButtonDimension().width, getHeight() - m_nScrollbarWidth, getWidth()
				- getLeftButtonDimension().width
				- getRightButtonDimension().width
				- m_nScrollbarWidth, m_nScrollbarWidth);
		}

		return new Rectangle(getLeftButtonDimension().width, getHeight() - m_nScrollbarWidth, getWidth()
			- getLeftButtonDimension().width
			- getRightButtonDimension().width, m_nScrollbarWidth);
	}

	/**
	 * @return the dimension of the horizontal marker.
	 */
	protected Rectangle getHorizontalMarkerDimension() throws GUIException {
		if (false == m_bHBarVisible) {
			return c_rectEmpty;
		}

		Rectangle barDim = getHorizontalBarDimension();

		int length, pos;

		if (null != m_content && m_content.getWidth() != 0) {
			length = (barDim.width * getContentDimension().width) / m_content.getWidth();
		} else {
			length = barDim.width;
		}

		if (length < m_nScrollbarWidth) {
			length = m_nScrollbarWidth;
		}

		if (length > barDim.width) {
			length = barDim.width;
		}

		if (getHorizontalMaxScroll() != 0) {
			pos = ((barDim.width - length) * getHorizontalScrollAmount()) / getHorizontalMaxScroll();
		} else {
			pos = 0;
		}

		return new Rectangle(barDim.x + pos, barDim.y, length, m_nScrollbarWidth);
	}

	/**
	 * @return the horizontal max scroll.
	 */
	public int getHorizontalMaxScroll() throws GUIException {
		checkPolicies();

		if (null == m_content) {
			return 0;
		}

		int value = m_content.getWidth() - getContentDimension().width + 2 * m_content.getBorderSize();

		if (value < 0) {
			return 0;
		}

		return value;
	}

	/**
	 * @return the scroll amount on horizontal scroll.
	 */
	public int getHorizontalScrollAmount() {
		return m_nHScroll;
	}

	/**
	 * @return the policy for the horizontal scrollbar policy. See enum with policies.
	 */
	public int getHorizontalScrollPolicy() {
		return m_nHPolicy;
	}

	/**
	 * @return the dimension of the left button.
	 */
	protected Rectangle getLeftButtonDimension() {
		if (false == m_bHBarVisible) {
			return c_rectEmpty;
		}

		return new Rectangle(0, getHeight() - m_nScrollbarWidth, m_nScrollbarWidth, m_nScrollbarWidth);
	}

	/**
	 * @return the dimension of the right button.
	 */
	protected Rectangle getRightButtonDimension() {
		if (false == m_bHBarVisible) {
			return c_rectEmpty;
		}

		if (m_bVBarVisible && m_bHBarVisible) {
			return new Rectangle(getWidth() - m_nScrollbarWidth * 2, getHeight() - m_nScrollbarWidth,
				m_nScrollbarWidth, m_nScrollbarWidth);
		}

		return new Rectangle(getWidth() - m_nScrollbarWidth, getHeight() - m_nScrollbarWidth, m_nScrollbarWidth,
			m_nScrollbarWidth);
	}

	/**
	 * @return the width of the scrollbar.
	 */
	public int getScrollbarWidth() {
		return m_nScrollbarWidth;
	}

	/**
	 * @return the dimension of the up button.
	 */
	protected Rectangle getUpButtonDimension() {
		if (false == m_bVBarVisible) {
			return c_rectEmpty;
		}

		return new Rectangle(getWidth() - m_nScrollbarWidth, 0, m_nScrollbarWidth, m_nScrollbarWidth);
	}

	/**
	 * @return the dimension of the vertical scrollbar.
	 */
	protected Rectangle getVerticalBarDimension() {
		if (false == m_bVBarVisible) {
			return c_rectEmpty;
		}

		if (m_bHBarVisible) {
			return new Rectangle(getWidth() - m_nScrollbarWidth, getUpButtonDimension().height, m_nScrollbarWidth,
				getHeight() - getUpButtonDimension().height - getDownButtonDimension().height - m_nScrollbarWidth);
		}

		return new Rectangle(getWidth() - m_nScrollbarWidth, getUpButtonDimension().height, m_nScrollbarWidth,
			getHeight() - getUpButtonDimension().height - getDownButtonDimension().height);
	}

	/**
	 * @return the dimension of the vertical marker.
	 */
	protected Rectangle getVerticalMarkerDimension() throws GUIException {
		if (false == m_bVBarVisible) {
			return c_rectEmpty;
		}

		int length, pos;
		Rectangle batRect = getVerticalBarDimension();

		if (null != m_content && m_content.getHeight() != 0) {
			length = (batRect.height * getContentDimension().height) / m_content.getHeight();
		} else {
			length = batRect.height;
		}

		if (length < m_nScrollbarWidth) {
			length = m_nScrollbarWidth;
		}

		if (length > batRect.height) {
			length = batRect.height;
		}

		if (getVerticalMaxScroll() != 0) {
			pos = ((batRect.height - length) * getVerticalScrollAmount()) / getVerticalMaxScroll();
		} else {
			pos = 0;
		}

		return new Rectangle(batRect.x, batRect.y + pos, m_nScrollbarWidth, length);
	}

	/**
	 * @return the vertical max scroll.
	 */
	public int getVerticalMaxScroll() throws GUIException {
		checkPolicies();

		if (null == m_content) {
			return 0;
		}

		int value;

		value = m_content.getHeight() - getContentDimension().height + 2 * m_content.getBorderSize();

		if (value < 0) {
			return 0;
		}

		return value;
	}

	/**
	 * @return the scroll amount on vertical scroll.
	 */
	public int getVerticalScrollAmount() {
		return m_nVScroll;
	}

	/**
	 * @return the policy for the vertical scrollbar. See enum with policies.
	 */
	public int getVerticalScrollPolicy() {
		return m_nVPolicy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#logic()
	 */
	public void logic() throws GUIException {
		checkPolicies();

		setVerticalScrollAmount(getVerticalScrollAmount());
		setHorizontalScrollAmount(getHorizontalScrollAmount());

		if (null != m_content) {
			m_content.setPosition(-m_nHScroll + getContentDimension().x + m_content.getBorderSize(), -m_nVScroll
				+ getContentDimension().y
				+ m_content.getBorderSize());

			m_content.logic();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#mouseInputMessage(sdljavax.guichan.evt.MouseInput)
	 */
	public void mouseInputMessage(MouseInput mouseInput) throws GUIException {
		super.mouseInputMessage(mouseInput);

		if (getContentDimension().isPointInRect(mouseInput.x, mouseInput.y)) {
			if (null != m_content) {
				if (false == m_content.hasMouse()) {
					m_content.mouseInMessage();
				}

				MouseInput mi = mouseInput;

				mi.x -= m_content.getX();
				mi.y -= m_content.getY();

				m_content.mouseInputMessage(mi);
			}
		} else if (null != m_content && m_content.hasMouse()) {
			m_content.mouseOutMessage();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {
		if (m_bVerticalMarkerPressed) {
			int pos = y - getVerticalBarDimension().y - m_nVerticalMarkerMousePosition;
			int length = getVerticalMarkerDimension().height;

			Rectangle barDim = getVerticalBarDimension();

			if ((barDim.height - length) > 0) {
				setVerticalScrollAmount((getVerticalMaxScroll() * pos) / (barDim.height - length));
			} else {
				setVerticalScrollAmount(0);
			}
		}
		if (m_bHorizontalMarkerPressed) {
			int pos = x - getHorizontalBarDimension().x - m_nHorizontalMarkerMousePosition;
			int length = getHorizontalMarkerDimension().width;

			Rectangle barDim = getHorizontalBarDimension();

			if ((barDim.width - length) > 0) {
				setHorizontalScrollAmount((getHorizontalMaxScroll() * pos) / (barDim.width - length));
			} else {
				setHorizontalScrollAmount(0);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseOut()
	 */
	public void mouseOut() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#mouseOutMessage()
	 */
	public void mouseOutMessage() {
		if (null != m_content && m_content.hasMouse()) {
			m_content.mouseOutMessage();
		}
		super.mouseOutMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (getUpButtonDimension().isPointInRect(x, y)) {
			setVerticalScrollAmount(getVerticalScrollAmount() - 10);
			m_bUpButtonPressed = true;
		} else if (getDownButtonDimension().isPointInRect(x, y)) {
			setVerticalScrollAmount(getVerticalScrollAmount() + 10);
			m_bDownButtonPressed = true;
		} else if (getLeftButtonDimension().isPointInRect(x, y)) {
			setHorizontalScrollAmount(getHorizontalScrollAmount() - 10);
			m_bLeftButtonPressed = true;
		} else if (getRightButtonDimension().isPointInRect(x, y)) {
			setHorizontalScrollAmount(getHorizontalScrollAmount() + 10);
			m_bRightButtonPressed = true;
		} else if (getVerticalMarkerDimension().isPointInRect(x, y)) {
			m_bVerticalMarkerPressed = true;
			m_nVerticalMarkerMousePosition = y - getVerticalMarkerDimension().y;
		} else if (getHorizontalMarkerDimension().isPointInRect(x, y)) {
			m_bHorizontalMarkerPressed = true;
			m_nHorizontalMarkerMousePosition = x - getHorizontalMarkerDimension().x;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {
		m_bUpButtonPressed = false;
		m_bDownButtonPressed = false;
		m_bLeftButtonPressed = false;
		m_bRightButtonPressed = false;
		m_bVerticalMarkerPressed = false;
		m_bHorizontalMarkerPressed = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {
		if (hasMouse()) {
			setVerticalScrollAmount(getVerticalScrollAmount() + getContentDimension().height / 8);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {
		if (hasMouse()) {
			setVerticalScrollAmount(getVerticalScrollAmount() - getContentDimension().height / 8);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToBottom(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToBottom(Widget widget) throws GUIException {
		if (widget == m_content) {
			if (null != getParent()) {
				getParent().moveToBottom(this);
			}
		} else {
			throw new GUIException("Only a ScrollArea's content may be moved to bottom");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToTop(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToTop(Widget widget) throws GUIException {
		if (widget == m_content) {
			if (null != getParent()) {
				getParent().moveToTop(this);
			}
		} else {
			throw new GUIException("Only a ScrollArea's content may be moved to top");
		}
	}

	/**
	 * Tries to scroll to a specific rectangle. If the rectangle is to large to be visible as much
	 * as possibly, begining in the rectangles upper corner, will be visible.
	 * 
	 * @param rectangle
	 *            the rectangle to scroll to.
	 */
	public void scrollToRectangle(Rectangle rectangle) throws GUIException {
		Rectangle contentDim = getContentDimension();

		if (rectangle.x + rectangle.width > getHorizontalScrollAmount() + contentDim.width) {
			setHorizontalScrollAmount(rectangle.x + rectangle.width - contentDim.width);
		}

		if (rectangle.y + rectangle.height > getVerticalScrollAmount() + contentDim.height) {
			setVerticalScrollAmount(rectangle.y + rectangle.height - contentDim.height);
		}

		if (rectangle.x < getHorizontalScrollAmount()) {
			setHorizontalScrollAmount(rectangle.x);
		}

		if (rectangle.y < getVerticalScrollAmount()) {
			setVerticalScrollAmount(rectangle.y);
		}

	}

	/**
	 * Sets the content of the ScrollArea
	 * 
	 * @param content
	 *            the content of the ScrollArea.
	 */
	public void setContent(Widget content) {
		if (null != m_content) {
			m_content.setFocusHandler(null);
			m_content.setParent(null);
		}

		m_content = content;

		if (null != m_content) {
			m_content.setFocusHandler(getFocusHandler());
			m_content.setParent(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#setFocusHandler(sdljavax.guichan.evt.FocusHandler)
	 */
	public void setFocusHandler(FocusHandler focusHandler) {
		super.setFocusHandler(focusHandler);
		if (null != m_content) {
			m_content.setFocusHandler(focusHandler);
		}
	}

	/**
	 * Set the amount to scroll horizontaly.
	 * 
	 * @param hScroll
	 *            the amount to scroll.
	 */
	public void setHorizontalScrollAmount(int hScroll) throws GUIException {
		int max = getHorizontalMaxScroll();

		m_nHScroll = hScroll;

		if (hScroll > max) {
			m_nHScroll = max;
		} else if (hScroll < 0) {
			m_nHScroll = 0;
		}
	}

	/**
	 * Sets the horizontal scrollbar policy. See enum with policies.
	 * 
	 * @param hPolicy
	 *            the policy for the horizontal scrollbar. See constants for policies.
	 */
	public void setHorizontalScrollPolicy(int hPolicy) throws GUIException {
		m_nHPolicy = hPolicy;
		checkPolicies();
	}

	/**
	 * Sets the amount to scroll horizontaly and verticaly.
	 * 
	 * @param hScroll
	 *            the amount to scroll on horizontal scroll.
	 * @param vScroll
	 *            the amount to scroll on vertical scroll.
	 * @throws GUIException
	 */
	public void setScrollAmount(int hScroll, int vScroll) throws GUIException {
		setHorizontalScrollAmount(hScroll);
		setVerticalScrollAmount(vScroll);
	}

	/**
	 * Sets the width of the scrollbar.
	 * 
	 * @param width
	 *            the width.
	 * @throws GUIException
	 */
	public void setScrollbarWidth(int width) throws GUIException {
		if (width > 0) {
			m_nScrollbarWidth = width;
		} else {
			throw new GUIException("Width should be greater then 0");
		}
	}

	/**
	 * Sets the horizontal and vertical scrollbar policy. See constants for policies.
	 * 
	 * @param hPolicy
	 *            the policy for the horizontal scrollbar. See constants for policies.
	 * @param vPolicy
	 *            the policy for the vertical scrollbar. See constants for policies.
	 */
	public void setScrollPolicy(int hPolicy, int vPolicy) throws GUIException {
		m_nHPolicy = hPolicy;
		m_nVPolicy = vPolicy;
		checkPolicies();
	}

	/**
	 * Set the amount to scroll verticaly.
	 * 
	 * @param vScroll
	 *            the amount to scroll.
	 */
	public void setVerticalScrollAmount(int vScroll) throws GUIException {
		int max = getVerticalMaxScroll();

		m_nVScroll = vScroll;

		if (vScroll > max) {
			m_nVScroll = max;
		}

		if (vScroll < 0) {
			m_nVScroll = 0;
		}
	}

	/**
	 * Sets the vertical scrollbar policy. See enum with policies.
	 * 
	 * @param vPolicy
	 *            the policy for the vertical scrollbar. See enum with policies.
	 */
	public void setVerticalScrollPolicy(int vPolicy) throws GUIException {
		m_nVPolicy = vPolicy;
		checkPolicies();
	}
}
