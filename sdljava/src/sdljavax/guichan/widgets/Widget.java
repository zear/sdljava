/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.ActionListener;
import sdljavax.guichan.evt.FocusHandler;
import sdljavax.guichan.evt.KeyInput;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.font.DefaultFont;
import sdljavax.guichan.font.Font;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is the base class for all widgets. It is abstract. It handles the common logic for all
 * widgets such as user input.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */
public abstract class Widget {
	protected static final DefaultFont	c_fontDefault	= new DefaultFont();
	protected static Font				c_fontGlobal	= null;
	protected static final List			c_listWidgets	= new Vector();

	/**
	 * Sets the global font to be used by default for all widgets.
	 * 
	 * @param globalFont
	 *            the global font
	 * @see Font
	 */
	public static void setGlobalFont(Font globalFont) {
		c_fontGlobal = globalFont;

		for (Iterator it = c_listWidgets.iterator(); it.hasNext();) {
			Widget theWidget = (Widget) it.next();
			if (null == theWidget.m_fontCurrent) {
				theWidget.fontChanged();
			}
		}
	}

	/**
	 * Checks whether a widget exists or not, that is if it still exists an instance of the object.
	 * 
	 * @param widget
	 *            the widget to check
	 * @return true if the Widget exists
	 */
	public static boolean widgetExists(final Widget widget) {
		return c_listWidgets.contains(widget);
	}

	protected boolean			m_bEnabled				= true;
	protected boolean			m_bFocusable			= false;
	protected boolean			m_bHasMouse				= false;
	protected boolean			m_bTabIn				= true;
	protected boolean			m_bTabOut				= true;
	protected boolean			m_bVisible				= true;
	protected Color				m_colorBackground		= new Color(0xffffff);
	protected Color				m_colorBase				= new Color(0x808090);
	protected Color				m_colorForeground		= new Color(0x000000);
	protected BasicContainer	m_containerParent		= null;
	protected FocusHandler		m_focusHandler			= null;
	protected Font				m_fontCurrent			= null;
	protected long				m_lClickTimeStamp		= 0;
	protected final List		m_listActionListeners	= new ArrayList();
	protected final List		m_listKeyListeners		= new ArrayList();
	protected final List		m_listMouseListeners	= new ArrayList();
	protected int				m_nBorderSize			= 0;
	protected int				m_nClickButton			= 0;
	protected int				m_nClickCount			= 0;
	protected Rectangle			m_rectDimension			= new Rectangle();
	protected String			m_strEventId;

	/**
	 * Constructor. Resets member variables. Noteable, a widget is not focusable as default,
	 * therefore, widgets that are supposed to be focusable should overide this default in their own
	 * constructor.
	 */
	public Widget() {
		super();
		c_listWidgets.add(this);
	}

	/**
	 * This function adds the action listener to the widget. When an action is triggered, the widget
	 * calls its action listeners. An action can be just about anything, for example a click on a
	 * button or enter pressed in an edit box.
	 * 
	 * @param actionListener
	 *            the action listener to add.
	 * @see ActionListener
	 */
	public void addActionListener(ActionListener actionListener) {
		m_listActionListeners.add(actionListener);
	}

	/**
	 * This function adds a key listener to the widget. When a key message is recieved its sent to
	 * the key listeners.
	 * 
	 * @param keyListener
	 *            the key listener to add.
	 * @see KeyListener
	 */
	public void addKeyListener(KeyListener keyListener) {
		m_listKeyListeners.add(keyListener);
	}

	/**
	 * This function adds a mouse listener to the widget. When a mouse message is recieved its sent
	 * to the mouse listeners.
	 * 
	 * @param mouseListener
	 *            the mouse listener to add.
	 * @see MouseListener
	 */
	public void addMouseListener(MouseListener mouseListener) {
		m_listMouseListeners.add(mouseListener);
	}

	public void delete() throws GUIException {
		if (null != getParent()) {
			getParent().announceDeath(this);
		}

		setFocusHandler(null);
		c_listWidgets.remove(this);
	}

	/**
	 * This function should draw the widget. It is called by the parent widget when it is time for
	 * the widget to draw itself. The graphics object is set up so that all drawing is relative to
	 * the widget, i.e coordinate (0,0) is the top-left corner of the widget. It is not possible to
	 * draw outside of a widgets dimension.
	 * 
	 * @param graphics
	 *            a graphics object to draw with.
	 */
	public abstract void draw(Graphics graphics) throws GUIException;

	/**
	 * This function should draw a widgets border. A border is drawn around a widget. The width and
	 * height of the border is therefore the widgets height+2*bordersize. Think of a painting that
	 * has a certain size, the border surrounds the painting.
	 * 
	 * @param graphics
	 *            a graphics object to draw with.
	 */
	public abstract void drawBorder(Graphics graphics) throws GUIException;

	/**
	 * Use this function to trap font changes. It is called whenever a widgets font is chanegd. If
	 * the change is global, this function will only be called if the widget doesn't have a font of
	 * its own.
	 */
	public void fontChanged() {}

	/**
	 * This function generates an action to the widgets action listeners.
	 */
	protected void generateAction() throws GUIException {
		for (Iterator it = m_listActionListeners.iterator(); it.hasNext();) {
			ActionListener listener = (ActionListener) it.next();
			listener.action(m_strEventId);
		}
	}

	/**
	 * Gets the absolute position on the screen for the widget,
	 * 
	 * @return A Point containing this Widgets' x and y position
	 */
	public Point getAbsolutePosition() {
		Point pointResult = new Point();

		if (null == getParent()) {
			pointResult.x = m_rectDimension.x;
			pointResult.y = m_rectDimension.y;
		} else {
			Point posParent = getParent().getAbsolutePosition();
			pointResult.x = posParent.x + m_rectDimension.x;
			pointResult.y = posParent.y + m_rectDimension.y;
		}

		return pointResult;
	}

	/**
	 * @return the widgets background color.
	 * @see Color
	 */
	public Color getBackgroundColor() {
		return m_colorBackground;
	}

	/**
	 * @return the widgets foreground color.
	 * @see Color
	 */
	public Color getBaseColor() {
		return m_colorBase;
	}

	/**
	 * Gets the size of the border, or the width if you so like. The size is the number of pixels
	 * that the border extends outside the widget. Border size = 0 means no border.
	 * 
	 * @return the size of the border
	 * @see #drawBorder(Graphics)
	 */
	public int getBorderSize() {
		return m_nBorderSize;
	}

	/**
	 * @return the widgets dimension relative to its container.
	 * @see Rectangle
	 */
	public final Rectangle getDimension() {
		// Return a copy, it might get modified
		return (Rectangle) m_rectDimension.clone();
	}

	/**
	 * This function returns the widgets event identifier. An event identifier is used with action
	 * events. If this widget sends an action event, the widgets event identifier is sent along with
	 * the action so that the action listener can determine which widget sent the action event.
	 * 
	 * @return the widgets event identifier
	 */
	public String getEventId() {
		return m_strEventId;
	}

	/**
	 * This function gets the focus handler used by this widget. Should not be called or overloaded
	 * unless you know what you are doing.
	 * 
	 * @return the focus handler.
	 */
	public FocusHandler getFocusHandler() {
		return m_focusHandler;
	}

	/**
	 * This function returns the font used by this widget. If no font has been set, the global font
	 * will be returned instead. If no global font has been set either, it will fall back on an ugly
	 * default.
	 * 
	 * @return the currently used font.
	 * @see Font
	 * @see DefaultFont
	 */
	public Font getFont() {
		if (null == m_fontCurrent) {
			if (null == c_fontGlobal) {
				return c_fontDefault;
			}

			return c_fontGlobal;
		}

		return m_fontCurrent;
	}

	/**
	 * @return the widgets foreground color.
	 * @see Color
	 */
	public Color getForegroundColor() {
		return m_colorForeground;
	}

	/**
	 * @return the widget height in pixels
	 */
	public int getHeight() {
		return m_rectDimension.height;
	}

	/**
	 * @return a pointer to the widgets parent container. Returns null if the widget has no parent
	 *         for example if the widget is the gui top widget.
	 */
	protected BasicContainer getParent() {
		return m_containerParent;
	}

	/**
	 * @return the widget with in pixels
	 */
	public int getWidth() {
		return m_rectDimension.width;
	}

	/**
	 * @return the widgets x coordinate relative to its container.
	 */
	public int getX() {
		return m_rectDimension.x;
	}

	/**
	 * @return the widgets y coordinate relative to its container.
	 */
	public int getY() {
		return m_rectDimension.y;
	}

	/**
	 * Called if the widget recieves focus.
	 */
	public void gotFocus() {}

	/**
	 * @return true if the widget currently has focus.
	 */
	public boolean hasFocus() {
		if (null == m_focusHandler) {
			return false;
		}

		return (m_focusHandler.hasFocus(this));
	}

	/**
	 * Checks if the Widget or its parent has modal focus.
	 * 
	 * @return whether this Widget (or its parent) has modal focus or not
	 * @throws GUIException
	 *             if no FocusHandler is set
	 */
	public boolean hasModalFocus() throws GUIException {
		if (null == m_focusHandler) {
			throw new GUIException("No focushandler set (did you add the widget to the gui?).");
		}

		if (null != getParent()) {
			return (this == m_focusHandler.getModalFocused()) || getParent().hasModalFocus();
		}

		return this == m_focusHandler.getModalFocused();
	}

	/**
	 * @return true if the widget currently has the mouse.
	 */
	public boolean hasMouse() {
		return m_bHasMouse;
	}

	/**
	 * Checks if the widget is dragged, meaning if the mouse button has been pressed down over the
	 * widget and the mouse has been moved.
	 * 
	 * @return true if the widget is dragged.
	 */
	public boolean isDragged() {
		return m_focusHandler.isDragged(this);
	}

	/**
	 * Checks if a widget is enabled or not.
	 * 
	 * @return true if the widget is enabled, false otherwise
	 */
	public boolean isEnabled() {
		return m_bEnabled && isVisible();
	}

	/**
	 * @return true if the widget is focusable.
	 */
	public boolean isFocusable() {
		return m_bFocusable && isVisible() && isEnabled();
	}

	/**
	 * Tab in means that you can set focus to this widget by pressing the tab button. If tab in is
	 * disabled then the FocusHandler will skip this widget and focus the next in its focus order.
	 * 
	 * @return true if tab is enabled for the widget.
	 */
	public boolean isTabInEnabled() {
		return m_bTabIn;
	}

	/**
	 * Tab out means that you can lose focus to this widget by pressing the tab button. If tab out
	 * is disabled then the FocusHandler ignores tabbing and focus will stay with this widget,
	 * 
	 * @return true if tab out is enabled for the widget.
	 */
	public boolean isTabOutEnabled() {
		return m_bTabOut;
	}

	/**
	 * @return true if the widget is visible.
	 */
	public boolean isVisible() {
		if (null == getParent()) {
			return m_bVisible;
		}
		return m_bVisible && getParent().isVisible();
	}

	/**
	 * This function is used internally be the gui to handle all key messages. Don't call or
	 * overload it unless you know what you are doing.
	 * 
	 * @param keyInput
	 *             @see KeyInput
	 */
	public void keyInputMessage(KeyInput keyInput) throws GUIException {
		if (null == m_focusHandler) {
			throw new GUIException("No focushandler set (did you add the widget to the gui?).");
		}

		if (false == m_bEnabled || (null != m_focusHandler.getModalFocused() && false == hasModalFocus())) {
			return;
		}

		switch (keyInput.getType()) {
			case KeyInput.PRESS :
				for (Iterator it = m_listKeyListeners.iterator(); it.hasNext();) {
					KeyListener listener = (KeyListener) it.next();
					listener.keyPress(keyInput.getKey());
				}
				break;

			case KeyInput.RELEASE :
				for (Iterator it = m_listKeyListeners.iterator(); it.hasNext();) {
					KeyListener listener = (KeyListener) it.next();
					listener.keyRelease(keyInput.getKey());
				}
				break;
		}
	}

	/**
	 * This functions gets called for all widgets in the gui each time Gui::logic is called. You can
	 * do logic stuff here like playing an animation.
	 * 
	 * @see sdljavax.guichan.GUI
	 */
	public void logic() throws GUIException {}

	/**
	 * Called if the widget looses focus.
	 */
	public void lostFocus() throws GUIException {}

	/**
	 * This function is used internally be the gui to handle mouse in messages. Don't call this
	 * function unless you know what you are doing.
	 */
	public void mouseInMessage() throws GUIException {
		if (false == m_bEnabled) {
			return;
		}

		m_bHasMouse = true;

		for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
			MouseListener listener = (MouseListener) it.next();
			listener.mouseIn();
		}
	}

	/**
	 * This function is used internally be the gui to handle all mouse messages. Don't call or
	 * overload it unless you know what you are doing.
	 * 
	 * @param mouseInput
	 *             @see MouseInput
	 */
	public void mouseInputMessage(final MouseInput mouseInput) throws GUIException {
		if (null == m_focusHandler) {
			throw new GUIException("No focushandler set (did you add the widget to the gui?).");
		}

		if (false == m_bEnabled || (null != m_focusHandler.getModalFocused() && false == hasModalFocus())) {
			return;
		}

		int x = mouseInput.x;
		int y = mouseInput.y;
		int b = mouseInput.getButton();
		long ts = mouseInput.getTimeStamp();

		switch (mouseInput.getType()) {
			case MouseInput.MOTION :
				for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
					MouseListener listener = (MouseListener) it.next();
					listener.mouseMotion(x, y);
				}
				break;

			case MouseInput.PRESS :
				if (hasMouse()) {
					requestFocus();
					m_focusHandler.requestDrag(this);
				}

				if (b != MouseInput.WHEEL_UP && b != MouseInput.WHEEL_DOWN) {
					for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
						MouseListener listener = (MouseListener) it.next();
						listener.mousePress(x, y, b);
					}

					if (hasMouse()) {
						if (ts - m_lClickTimeStamp < 300 && m_nClickButton == b) {
							m_nClickCount++;
						} else {
							m_nClickCount = 0;
						}
						m_nClickButton = b;
						m_lClickTimeStamp = ts;
					} else {
						m_nClickButton = 0;
					}
				} else if (b == MouseInput.WHEEL_UP) {
					for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
						MouseListener listener = (MouseListener) it.next();
						listener.mouseWheelUp(x, y);
					}
				} else {
					for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
						MouseListener listener = (MouseListener) it.next();
						listener.mouseWheelDown(x, y);
					}
				}
				break;

			case MouseInput.RELEASE :
				if (isDragged()) {
					m_focusHandler.dragNone();
				}

				if (b != MouseInput.WHEEL_UP && b != MouseInput.WHEEL_DOWN) {
					for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
						MouseListener listener = (MouseListener) it.next();
						listener.mouseRelease(x, y, b);
					}
				}

				if (m_bHasMouse) {
					if (b == m_nClickButton) {
						for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
							MouseListener listener = (MouseListener) it.next();
							listener.mouseClick(x, y, b, m_nClickCount + 1);
						}
					} else {
						m_nClickButton = 0;
						m_nClickCount = 0;
					}
				} else {
					m_nClickCount = 0;
					m_lClickTimeStamp = 0;
				}
				break;
		}
	}

	/**
	 * This function is used internally be the gui to handle mouse out messages. Don't call this
	 * function unless you know what you are doing.
	 */
	public void mouseOutMessage() {
		m_bHasMouse = false;

		for (Iterator it = m_listMouseListeners.iterator(); it.hasNext();) {
			MouseListener listener = (MouseListener) it.next();
			listener.mouseOut();
		}
	}

	/**
	 * Releases modal focus. Modal focus will only be released if the Widget has the modal focus.
	 */
	public void releaseModalFocus() {
		if (null != m_focusHandler) {
			m_focusHandler.releaseModalFocus(this);
		}
	}

	/**
	 * This function removes an action listener from the widget.
	 * 
	 * @param actionListener
	 *            the action listener to remove.
	 * @see ActionListener
	 */
	public void removeActionListener(ActionListener actionListener) {
		m_listActionListeners.remove(actionListener);
	}

	/**
	 * This function removes a key listener to the widget.
	 * 
	 * @param keyListener
	 *            the key listener to remove.
	 * @see KeyListener
	 */
	public void removeKeyListener(KeyListener keyListener) {
		m_listKeyListeners.remove(keyListener);
	}

	/**
	 * This function removes a mouse listener from the widget.
	 * 
	 * @param mouseListener
	 *            the mouse listener to remove.
	 * @see MouseListener
	 */
	public void removeMouseListener(MouseListener mouseListener) {
		m_listMouseListeners.remove(mouseListener);
	}

	/**
	 * This function requests focus for the widget. If the widget is focusable it will get focus.
	 * Otherwise, this function does nothing.
	 */
	public void requestFocus() throws GUIException {
		if (null == m_focusHandler) {
			throw new GUIException("No focushandler set (did you add the widget to the gui?)");
		}

		if (isFocusable()) {
			m_focusHandler.requestFocus(this);
		}
	}

	/**
	 * Requests modal focus. When a widget has modal focus, only that Widget and it's children may
	 * recieve input. If some other Widget already has modal focus, an exception will be thrown.
	 * 
	 * @throws GUIException
	 *             if another Widget already has modal focus.
	 */
	public void requestModalFocus() throws GUIException {
		if (null == m_focusHandler) {
			throw new GUIException("No focushandler set (did you add the widget to the gui?).");
		}

		m_focusHandler.requestModalFocus(this);
	}

	/**
	 * This function requests the container the widgets in to move the widget to the bottom.
	 * 
	 * @throws GUIException
	 */
	public void requestMoveToBottom() throws GUIException {
		if (null != m_containerParent) {
			m_containerParent.moveToBottom(this);
		}
	}

	/**
	 * This function requests the container the widgets in to move the widget to the top.
	 * 
	 * @throws GUIException
	 */
	public void requestMoveToTop() throws GUIException {
		if (null != m_containerParent) {
			m_containerParent.moveToTop(this);
		}
	}

	/**
	 * Sets the widget's background color.
	 * 
	 * @param color
	 *            the background color.
	 * @see Color
	 */
	public void setBackgroundColor(final Color color) {
		m_colorBackground = color;
	}

	public void setBackgroundColor(final int color) {
		setBackgroundColor(new Color(color));
	}

	/**
	 * Sets the widget's base color. (the background colors for buttons and containers).
	 * 
	 * @param color
	 *            the foreground color.
	 * @see Color
	 */
	public void setBaseColor(final Color color) {
		m_colorBase = color;
	}

	public void setBaseColor(final int color) {
		setBaseColor(new Color(color));
	}

	/**
	 * Sets the size of the border, or the width if you so like. The size is the number of pixels
	 * that the border extends outside the widget. Border size = 0 means no border.
	 * 
	 * @param borderSize
	 * @see #drawBorder(Graphics)
	 */
	public void setBorderSize(int borderSize) {
		m_nBorderSize = borderSize;
	}

	/**
	 * @param dimension
	 *            the widgets dimension relative to is container.
	 * @see Rectangle
	 */
	public void setDimension(Rectangle dimension) {
		m_rectDimension = dimension;
	}

	/**
	 * Sets the widget to be disabled or enabled. A disabled widget will never recieve mouse or key
	 * input.
	 * 
	 * @param enabled
	 *            indicates whether the widget is enabled or not
	 */
	public void setEnabled(boolean enabled) {
		m_bEnabled = enabled;
	}

	/**
	 * This function sets the widgets event identifier. An event identifier is used with action
	 * events. If this widget sends an action event, the widgets event identifier is sent along with
	 * the action so that the action listener can determine which widget sent the action event.
	 * 
	 * NOTE: An event identifier should not be used to identify a certain widget but rather a
	 * certain event in your application. Several widgets can have the same event identifer.
	 * 
	 * @param eventId
	 *            the event identifier
	 * @see ActionListener
	 * @see #getEventId()
	 * @see #generateAction()
	 */
	public void setEventId(String eventId) {
		m_strEventId = eventId;
	}

	/**
	 * @param focusable
	 *            indicates whether the widget is focusable or not.
	 * @throws GUIException
	 */
	public void setFocusable(boolean focusable) throws GUIException {
		if (false == focusable && hasFocus()) {
			m_focusHandler.focusNone();
		}
		m_bFocusable = focusable;
	}

	/**
	 * This function sets the focus handler to be used by this widget. Should not be called or
	 * overloaded unless you know what you are doing.
	 * 
	 * @param focusHandler
	 *            a pointer to a focus handler.
	 * @see FocusHandler
	 */
	public void setFocusHandler(FocusHandler focusHandler) {
		if (null != m_focusHandler) {
			releaseModalFocus();
			m_focusHandler.remove(this);
		}

		if (null != focusHandler) {
			focusHandler.add(this);
		}

		m_focusHandler = focusHandler;
	}

	/**
	 * Sets the font for this widget. Pass NULL to this function to use the global font.
	 * 
	 * @param font
	 *            the font. If NULL, the global font will be used.
	 * @see #setGlobalFont(Font)
	 */
	public void setFont(Font font) {
		m_fontCurrent = font;
		fontChanged();
	}

	/**
	 * Sets the widget's foreground color.
	 * 
	 * @param color
	 *            the foreground color.
	 * @see Color
	 */
	public void setForegroundColor(final Color color) {
		m_colorForeground = color;
	}

	public void setForegroundColor(final int color) {
		setForegroundColor(new Color(color));
	}

	/**
	 * @param height
	 *            the widget height in pixels
	 */
	public void setHeight(int height) {
		m_rectDimension.height = height;
	}

	/**
	 * This function sets the widgets parent. It should not be called unless you know what you are
	 * doing.
	 * 
	 * @param parent
	 *            the parent container.
	 */
	public void setParent(BasicContainer parent) {
		m_containerParent = parent;
	}

	/**
	 * @param x
	 *            the widgets x coordinate relative to its container.
	 * @param y
	 *            the widgets y coordinate relative to its container.
	 */
	public void setPosition(int x, int y) {
		m_rectDimension.x = x;
		m_rectDimension.y = y;
	}

	/**
	 * Sets the size of the widget.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Tab in means that you can set focus to this widget by pressing the tab button. If tab in is
	 * disabled then the FocusHandler will skip this widget and focus the next in its focus order.
	 * 
	 * @param tabIn
	 *            true if tab in should be enabled.
	 */
	public void setTabInEnabled(boolean tabIn) {
		m_bTabIn = tabIn;
	}

	/**
	 * Tab out means that you can lose focus to this widget by pressing the tab button. If tab out
	 * is disabled then the FocusHandler ignores tabbing and focus will stay with this widget,
	 * 
	 * @param tabOut
	 *            true if tab out should be enabled.
	 */
	public void setTabOutEnabled(boolean tabOut) {
		m_bTabOut = tabOut;
	}

	/**
	 * @param visible
	 *            indicates whether the widget is visible or not.
	 */
	public void setVisible(boolean visible) throws GUIException {
		if (false == visible && hasFocus()) {
			m_focusHandler.focusNone();
		}
		m_bVisible = visible;
	}

	/**
	 * @param width
	 *            the widget width in pixels
	 */
	public void setWidth(int width) {
		m_rectDimension.width = width;
	}

	/**
	 * @param x
	 *            the widgets x coordinate relative to its container.
	 */
	public void setX(int x) {
		m_rectDimension.x = x;
	}

	/**
	 * @param y
	 *            the widgets y coordinate relative to its container.
	 */
	public void setY(int y) {
		m_rectDimension.y = y;
	}
}
