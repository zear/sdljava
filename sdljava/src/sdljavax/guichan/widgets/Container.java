/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.FocusHandler;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;


/**
 * This is the container base class. It is a widget that holds other widgets. A widgets position in
 * the container is always relativ to the container itself, not the screen. Using a container as the
 * top widget is the only way to use more then one widget in the gui.
 * 
 * @see sdljavax.guichan.widgets.Widget
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Container extends BasicContainer {
	protected boolean	m_bOpaque			= true;
	protected List		m_listWidgets		= new Vector();
	protected Widget	m_widgetWithMouse	= null;

	/**
	 * Constructor. A container is opauqe as default.
	 * 
	 * @see #setOpaque(boolean)
	 * @see #isOpaque()
	 */
	public Container() {
		super();
	}

	/**
	 * Adds a widget to the container.
	 * 
	 * @param widget
	 *            the widget to add.
	 * @see #remove(Widget)
	 */
	public void add(Widget widget) {
		m_listWidgets.add(widget);
		widget.setFocusHandler(getFocusHandler());
		widget.setParent(this);
	}

	/**
	 * Adds a widget to the container and also specifices its position.
	 * 
	 * @param widget
	 *            the widget to add.
	 * @param x
	 *            the x coordinat for the widget in the container
	 * @param y
	 *            the y coordinat for the widget in the container
	 * @see #remove(Widget)
	 */
	public void add(Widget widget, int x, int y) {
		widget.setPosition(x, y);
		add(widget);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#announceDeath(sdljavax.guichan.widgets.Widget)
	 */
	protected void announceDeath(Widget widget) throws GUIException {
		if (widget == m_widgetWithMouse) {
			m_widgetWithMouse = null;
		}

		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget == widget) {
				m_listWidgets.remove(theWidget);
				return;
			}
		}

		throw new GUIException("There is no such widget in this container");
	}

	/**
	 * Clears the container of all widgets.
	 * 
	 * @see #add(Widget)
	 * @see #remove(Widget)
	 */
	public void clear() {
		m_widgetWithMouse = null;

		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			theWidget.setFocusHandler(null);
			theWidget.setParent(null);
		}

		m_listWidgets.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#delete()
	 */
	public void delete() throws GUIException {
		clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		if (isOpaque()) {
			graphics.setColor(getBaseColor());
			graphics.fillRectangle(new Rectangle(0, 0, getWidth(), getHeight()));
		}

		drawChildren(graphics);
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
	 * This function draws all children of the container. The widgets will bedrawn in the order the
	 * widgets were added.
	 * 
	 * @param graphics
	 *            the Graphics object to use for drawing.
	 */
	protected void drawChildren(Graphics graphics) throws GUIException {
		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget.isVisible()) {
				// If the widget has a border,
				// draw it before drawing the widget
				int nBorderSize = theWidget.getBorderSize();
				if (nBorderSize > 0) {
					Rectangle rec = theWidget.getDimension();
					rec.x -= nBorderSize;
					rec.y -= nBorderSize;
					rec.width += 2 * nBorderSize;
					rec.height += 2 * nBorderSize;
					graphics.pushClipArea(rec);
					theWidget.drawBorder(graphics);
					graphics.popClipArea();
				}

				graphics.pushClipArea(theWidget.getDimension());
				theWidget.draw(graphics);
				graphics.popClipArea();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#getDrawSize(sdljavax.guichan.widgets.Widget)
	 */
	public Dimension getDrawSize(Widget widget) throws GUIException {
		boolean contains = false;

		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (widget == theWidget) {
				contains = true;
				break;
			}
		}

		if (contains) {
			Rectangle rectDimWidget = widget.getDimension();
			Rectangle rectDimThis = getDimension();

			Dimension result = new Dimension(rectDimWidget.width, rectDimWidget.height);

			if (rectDimWidget.x < 0) {
				result.width += rectDimWidget.x;
			}

			if (rectDimWidget.y < 0) {
				result.height += rectDimWidget.y;
			}

			if (rectDimWidget.x + rectDimWidget.width > rectDimThis.width) {
				result.width -= (rectDimWidget.x + rectDimWidget.width) - rectDimThis.width;
			}

			if (rectDimWidget.y + rectDimWidget.height > rectDimThis.height) {
				result.height -= (rectDimWidget.y + rectDimWidget.height) - rectDimThis.height;
			}

			if (result.width < 0) {
				result.width = 0;
			}

			if (result.height < 0) {
				result.height = 0;
			}

			return result;
		}
		throw new GUIException("Widget not in container");
	}

	/**
	 * @return true if the container is opaque.
	 * @see #setOpaque(boolean)
	 */
	public boolean isOpaque() {
		return m_bOpaque;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#logic()
	 */
	public void logic() throws GUIException {
		logicChildren();
	}

	/**
	 * This function calls the logic function for all children of container. The widgets logic
	 * function will be called in the order the widgets were added.
	 */
	protected void logicChildren() throws GUIException {
		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			theWidget.logic();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#mouseInputMessage(sdljavax.guichan.evt.MouseInput)
	 */
	public void mouseInputMessage(final MouseInput mouseInput) throws GUIException {
		Widget tempWidgetWithMouse = null;

		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget.isVisible() && theWidget.getDimension().isPointInRect(mouseInput.x, mouseInput.y)) {
				tempWidgetWithMouse = theWidget;
			}
		}

		if (tempWidgetWithMouse != m_widgetWithMouse) {
			if (null != m_widgetWithMouse) {
				m_widgetWithMouse.mouseOutMessage();
			}

			if (null != tempWidgetWithMouse) {
				tempWidgetWithMouse.mouseInMessage();
			}

			m_widgetWithMouse = tempWidgetWithMouse;
		}

		if (null != m_widgetWithMouse) {
			MouseInput mi = (MouseInput) mouseInput.clone();
			mi.x -= m_widgetWithMouse.getX();
			mi.y -= m_widgetWithMouse.getY();
			m_widgetWithMouse.mouseInputMessage(mi);
		} else {
			super.mouseInputMessage(mouseInput);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#mouseOutMessage()
	 */
	public void mouseOutMessage() {
		if (null != m_widgetWithMouse) {
			m_widgetWithMouse.mouseOutMessage();
			m_widgetWithMouse = null;
		}

		super.mouseOutMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToBottom(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToBottom(Widget widget) throws GUIException {
		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget == widget) {
				m_listWidgets.remove(theWidget);
				m_listWidgets.add(0, widget);
				return;
			}
		}

		throw new GUIException("There is no such widget in this container");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToTop(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToTop(Widget widget) throws GUIException {
		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget == widget) {
				m_listWidgets.remove(theWidget);
				m_listWidgets.add(widget);
				return;
			}
		}

		throw new GUIException("There is no such widget in this container");
	}

	/**
	 * Removes a widgets.
	 * 
	 * @param widget
	 *            the widget to remove.
	 * @throws GUIException
	 *             when the widget is not in the container
	 * @see #add(Widget)
	 * @see #clear()
	 */
	public void remove(Widget widget) throws GUIException {
		if (m_widgetWithMouse == widget) {
			m_widgetWithMouse = null;
		}

		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (theWidget == widget) {
				m_listWidgets.remove(theWidget);
				theWidget.setFocusHandler(null);
				theWidget.setParent(null);
				return;
			}
		}

		throw new GUIException("There is no such widget in this container");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#setFocusHandler(sdljavax.guichan.evt.FocusHandler)
	 */
	public void setFocusHandler(FocusHandler focusHandler) {
		super.setFocusHandler(focusHandler);
		for (Iterator it = m_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			theWidget.setFocusHandler(focusHandler);
		}
	}

	/**
	 * Sets wheter the widget should draw its background or not. If the widget is not opaque it will
	 * be completely transparent.
	 * 
	 * NOTE: This is not the same as set visible. A nonvisible container will not draw its content.
	 * 
	 * @param opaque
	 *            true it the widget should be opaque
	 * @see #isOpaque()
	 */
	public void setOpaque(boolean opaque) {
		m_bOpaque = opaque;
	}
}
