/*
 * Created on Mar 21, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
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
 * This is a movable window which can contain another widget.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Window extends BasicContainer implements MouseListener {
	protected boolean	m_bMouseDrag;
	protected boolean	m_bMovable;
	protected boolean	m_bOpaque;
	protected int		m_nAlignment;
	protected int		m_nMouseXOffset;
	protected int		m_nMouseYOffset;
	protected int		m_nPadding;
	protected int		m_nTitleBarHeight;
	protected String	m_strCaption;
	protected Widget	m_widgetContent;

	/**
	 * Constructor.
	 */
	public Window() {
		this(null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param caption
	 *            the caption of the window.
	 */
	public Window(String caption) {
		this(null, caption);
	}

	/**
	 * Constructor.
	 * 
	 * @param content
	 *            the content widget.
	 * @param caption
	 *            the caption of the window.
	 */
	public Window(Widget content, String caption) {
		super();
		m_bMouseDrag = false;
		if (null != content) {
			setContent(content);
		} else {
			m_widgetContent = null;
		}
		setCaption(caption);
		setBorderSize(1);
		setPadding(2);
		setTitleBarHeight(16);
		setAlignment(Graphics.CENTER);
		addMouseListener(this);
		setMovable(true);
		setOpaque(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#announceDeath(sdljavax.guichan.widgets.Widget)
	 */
	protected void announceDeath(Widget widget) throws GUIException {
		m_widgetContent = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#delete()
	 */
	public void delete() throws GUIException {
		super.delete();
		setContent(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		Color faceColor = getBaseColor();
		Color highlightColor, shadowColor;
		int alpha = getBaseColor().a;
		highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		Rectangle d = getContentDimension();

		// Fill the background around the content
		graphics.setColor(faceColor);
		// Fill top
		graphics.fillRectangle(new Rectangle(0, 0, getWidth(), d.y - 1));
		// Fill left
		graphics.fillRectangle(new Rectangle(0, d.y - 1, d.x - 1, getHeight() - d.y + 1));
		// Fill right
		graphics.fillRectangle(new Rectangle(d.x + d.width + 1, d.y - 1, getWidth() - d.x - d.width - 1, getHeight()
			- d.y
			+ 1));
		// Fill bottom
		graphics.fillRectangle(new Rectangle(d.x - 1, d.y + d.height + 1, d.width + 2, getHeight() - d.height - d.y - 1));

		if (isOpaque()) {
			graphics.fillRectangle(d);
		}

		// Construct a rectangle one pixel bigger than the content
		d.x -= 1;
		d.y -= 1;
		d.width += 2;
		d.height += 2;

		// Draw a border around the content
		graphics.setColor(shadowColor);
		// Top line
		graphics.drawLine(d.x, d.y, d.x + d.width - 2, d.y);

		// Left line
		graphics.drawLine(d.x, d.y + 1, d.x, d.y + d.height - 1);

		graphics.setColor(highlightColor);
		// Right line
		graphics.drawLine(d.x + d.width - 1, d.y, d.x + d.width - 1, d.y + d.height - 2);
		// Bottom line
		graphics.drawLine(d.x + 1, d.y + d.height - 1, d.x + d.width - 1, d.y + d.height - 1);

		drawContent(graphics);

		int textX;
		int textY = (getTitleBarHeight() - getFont().getHeight()) / 2;
		switch (getAlignment()) {
			case Graphics.LEFT :
				textX = 4;
				break;
			case Graphics.CENTER :
				textX = getWidth() / 2;
				break;
			case Graphics.RIGHT :
				textX = getWidth() - 4;
				break;
			default :
				throw new GUIException("Unknown alignment.");
		}

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());
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
			graphics.setColor(highlightColor);
			graphics.drawLine(i, i, width - i, i);
			graphics.drawLine(i, i + 1, i, height - i - 1);
			graphics.setColor(shadowColor);
			graphics.drawLine(width - i, i + 1, width - i, height - i);
			graphics.drawLine(i, height - i, width - i - 1, height - i);
		}
	}

	/**
	 * Draw the content of the window. This functions uses the getContentDimension to determin where
	 * to draw the content.
	 * 
	 * @param graphics
	 *            a graphics object to draw with.
	 */
	public void drawContent(Graphics graphics) throws GUIException {
		if (null != getContent() && getContent().isVisible()) {
			graphics.pushClipArea(getContentDimension());
			graphics.pushClipArea(new Rectangle(0, 0, getContent().getWidth(), getContent().getHeight()));
			getContent().draw(graphics);
			graphics.popClipArea();
			graphics.popClipArea();
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
	 * @return the caption of the window.
	 */
	public final String getCaption() {
		return m_strCaption;
	}

	/**
	 * Get the content widget in the window.
	 * 
	 * @return the contant widget.
	 */
	public final Widget getContent() {
		return m_widgetContent;
	}

	/**
	 * Gets the area in the window that the content occupies
	 */
	protected Rectangle getContentDimension() {
		return new Rectangle(getPadding(), getTitleBarHeight(), getWidth() - getPadding() * 2, getHeight()
			- getPadding()
			- getTitleBarHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#getDrawSize(sdljavax.guichan.widgets.Widget)
	 */
	public Dimension getDrawSize(Widget widget) throws GUIException {
		if (widget != getContent()) {
			throw new GUIException("Widget is not content of window");
		}

		Rectangle d = getContentDimension();
		return new Dimension(d.width, d.height);
	}

	/**
	 * Get the padding of the window.
	 * 
	 * @return the padding value.
	 */
	public int getPadding() {
		return m_nPadding;
	}

	/**
	 * Get the title bar height.
	 * 
	 * @return the title bar height.
	 */
	public int getTitleBarHeight() {
		return m_nTitleBarHeight;
	}

	/**
	 * Check if the window is movable.
	 * 
	 * @return true or false.
	 */
	public boolean isMovable() {
		return m_bMovable;
	}

	/**
	 * Check if the window is opaque.
	 * 
	 * @return true or false.
	 */
	public boolean isOpaque() {
		return m_bOpaque;
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#logic()
	 */
	public void logic() throws GUIException {
		if (null != getContent()) {
			getContent().logic();
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
	public void mouseIn() throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#mouseInputMessage(sdljavax.guichan.evt.MouseInput)
	 */
	public void mouseInputMessage(MouseInput mouseInput) throws GUIException {
		super.mouseInputMessage(mouseInput);
		Widget content = getContent();
		if (null != content) {
			if (getContentDimension().isPointInRect(mouseInput.x, mouseInput.y)
				&& content.getDimension().isPointInRect(mouseInput.x, mouseInput.y)) {
				if (false == content.hasMouse()) {
					content.mouseInMessage();
				}
				MouseInput mi = mouseInput;
				mi.x -= content.getX();
				mi.y -= content.getY();
				content.mouseInputMessage(mi);
			} else if (getContent().hasMouse()) {
				content.mouseOutMessage();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {
		if (m_bMouseDrag && isMovable()) {
			setPosition(x - m_nMouseXOffset + getX(), y - m_nMouseYOffset + getY());
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
		super.mouseOutMessage();

		if (null != getContent() && getContent().hasMouse()) {
			getContent().mouseOutMessage();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (null != getParent()) {
			getParent().moveToTop(this);
		}

		if (isMovable() && hasMouse() && y < (getTitleBarHeight() + getPadding()) && MouseInput.LEFT == button) {
			m_bMouseDrag = true;
			m_nMouseXOffset = x;
			m_nMouseYOffset = y;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {
		if (MouseInput.LEFT == button) {
			m_bMouseDrag = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToBottom(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToBottom(Widget widget) throws GUIException {
		if (widget != getContent()) {
			throw new GUIException("Widget is not content of window");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToTop(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToTop(Widget widget) throws GUIException {
		if (widget != getContent()) {
			throw new GUIException("Widget is not content of window");
		}
	}

	/**
	 * Moves the content to the top left corner of the window, uses getContentDimension to get the
	 * offset
	 */
	protected void repositionContent() {
		if (null == getContent()) {
			return;
		}

		Rectangle d = getContentDimension();
		m_widgetContent.setPosition(d.x, d.y);
	}

	/**
	 * Resize the window to fit the content.
	 */
	public void resizeToContent() {
		if (null != getContent()) {
			setSize(getContent().getWidth() + 2 * getPadding(), getContent().getHeight()
				+ getPadding()
				+ getTitleBarHeight());
		}
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
	 * Set the caption of the window.
	 * 
	 * @param caption
	 *            the caption of the button.
	 */
	protected void setCaption(String caption) {
		m_strCaption = caption;
	}

	/**
	 * Set the content widget in the window.
	 * 
	 * @param widget
	 *            the contant widget.
	 */
	public void setContent(Widget widget) {
		if (null != getContent()) {
			getContent().setParent(null);
			getContent().setFocusHandler(null);
		}

		if (null != widget) {
			widget.setParent(this);
			widget.setFocusHandler(getFocusHandler());
		}

		m_widgetContent = widget;
		repositionContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#setFocusHandler(sdljavax.guichan.evt.FocusHandler)
	 */
	public void setFocusHandler(FocusHandler focusHandler) {
		if (null != getContent()) {
			getContent().setFocusHandler(focusHandler);
		}

		super.setFocusHandler(focusHandler);
	}

	/**
	 * Set the window to be movable.
	 * 
	 * @param movable
	 *            true or false.
	 */
	public void setMovable(boolean movable) {
		m_bMovable = movable;
	}

	/**
	 * Set the window to be opaque. If it's not opaque, the content area will not be filled with a
	 * color.
	 * 
	 * @param opaque
	 *            true or false.
	 */
	public void setOpaque(boolean opaque) {
		m_bOpaque = opaque;
	}

	/**
	 * Set the padding of the window which is the distance between the window border and the
	 * content.
	 * 
	 * @param padding
	 *            the padding value.
	 */
	public void setPadding(int padding) {
		m_nPadding = padding;
		repositionContent();
	}

	/**
	 * Set the title bar height.
	 * 
	 * @param height
	 *            the title height value.
	 */
	public void setTitleBarHeight(int height) {
		m_nTitleBarHeight = height;
		repositionContent();
	}
}
