/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan;

import java.awt.Point;

import sdljavax.guichan.evt.FocusHandler;
import sdljavax.guichan.evt.Input;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyInput;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.widgets.Widget;

/**
 * This is the main class for GUIChan. It is the core of the a gui. It holds a special widget called
 * the top widget. For more then one widget in your Gui, top widget should be a container of some
 * sort. 
 *  
 * NOTE: For the Gui to function properly you need to set a Graphics object to use and an Input
 * object to use.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class GUI {
	protected boolean		m_bTabbing		= true;
	protected FocusHandler	m_focusHandler	= new FocusHandler();
	protected Graphics		m_graphics		= null;
	protected Input			m_input			= null;
	protected Widget		m_widgetTop		= null;

	/**
	 * Constructor.
	 */
	public GUI() {
		super();
	}

	/**
	 * Destructor.
	 */
	public void delete() {
		if (Widget.widgetExists(m_widgetTop)) {
			setTop(null);
		}

		m_focusHandler = null;
	}

	/**
	 * Draws the whole GUI by calling draw functions down in the GUI hierarchy.
	 */
	public void draw() throws GUIException {
		if (null == m_widgetTop) {
			throw new GUIException("No top widget set");
		}

		if (null == m_graphics) {
			throw new GUIException("No graphics set");
		}

		if (false == m_widgetTop.isVisible()) {
			return;
		}

		m_graphics.beginDraw();

		// If top has a border,
		// draw it before drawing top
		if (m_widgetTop.getBorderSize() > 0) {
			Rectangle rec = m_widgetTop.getDimension();
			rec.x -= m_widgetTop.getBorderSize();
			rec.y -= m_widgetTop.getBorderSize();
			rec.width += 2 * m_widgetTop.getBorderSize();
			rec.height += 2 * m_widgetTop.getBorderSize();
			m_graphics.pushClipArea(rec);
			m_widgetTop.drawBorder(m_graphics);
			m_graphics.popClipArea();
		}

		m_graphics.pushClipArea(m_widgetTop.getDimension());
		m_widgetTop.draw(m_graphics);
		m_graphics.popClipArea();

		m_graphics.endDraw();
	}

	/**
	 * Focus none of the Widgets in the Gui.
	 */
	public void focusNone() throws GUIException {
		m_focusHandler.focusNone();
	}

	/**
	 * Get the graphics object used for drawing.
	 * 
	 * @return the Graphics object. NULL if no Graphics object exists.
	 */
	public Graphics getGraphics() {
		return m_graphics;
	}

	/**
	 * Get the input object used for handling input.
	 * 
	 * @return the input object. NULL if no Input object exists.
	 */
	public final Input getInput() {
		return m_input;
	}

	/**
	 * Get the top widget used by this GUI.
	 * 
	 * @return the top widget. NULL if no top widget exists.
	 * @see #setTop(Widget)
	 */
	public Widget getTop() {
		return m_widgetTop;
	}

	/**
	 * Checks if tabbing is enabled.
	 * 
	 * @return true if tabbing is enabled
	 */
	public boolean isTabbingEnabled() {
		return m_bTabbing;
	}

	/**
	 * Performs the GUI's logic by calling all logic functions down in the GUI hierarchy. Logic can
	 * be just about anything like adjusting a Widgets' size or doing some calculations.
	 * 
	 * NOTE: Logic also deals with user input (Mouse and Keyboard) for Widgets.
	 * 
	 * @throws GUIException
	 */
	public void logic() throws GUIException {
		if (null == m_widgetTop) {
			throw new GUIException("No top widget set");
		}

		m_focusHandler.applyChanges();

		if (null != m_input) {
			m_input.pollInput();

			while (false == m_input.isMouseQueueEmpty()) {
				MouseInput mi = m_input.dequeueMouseInput();

				if (mi.x > 0 && mi.y > 0 && m_widgetTop.getDimension().isPointInRect(mi.x, mi.y)) {
					if (false == m_widgetTop.hasMouse()) {
						m_widgetTop.mouseInMessage();
					}

					MouseInput mio = (MouseInput) mi.clone();
					mio.x -= m_widgetTop.getX();
					mio.y -= m_widgetTop.getY();
					m_widgetTop.mouseInputMessage(mio);
				} else if (m_widgetTop.hasMouse()) {
					m_widgetTop.mouseOutMessage();
				}

				Widget focused = m_focusHandler.getFocused();
				Widget dragged = m_focusHandler.getDragged();

				// If the focused widget doesn't have the mouse,
				// send the mouse input to the focused widget.
				if (null != focused && false == focused.hasMouse()) {
					Point pos = focused.getAbsolutePosition();
					MouseInput mio = (MouseInput) mi.clone();
					mio.x -= pos.x;
					mio.y -= pos.y;
					focused.mouseInputMessage(mio);
				}

				// If the dragged widget is different from the focused
				// widget, send the mouse input to the dragged widget.
				if (null != dragged && dragged != focused && false == dragged.hasMouse()) {
					Point pos = dragged.getAbsolutePosition();
					MouseInput mio = (MouseInput) mi.clone();
					mio.x -= pos.x;
					mio.y -= pos.y;
					dragged.mouseInputMessage(mio);
				}

				m_focusHandler.applyChanges();
			}

			while (false == m_input.isKeyQueueEmpty()) {
				KeyInput ki = m_input.dequeueKeyInput();

				if (m_bTabbing && Key.TAB == ki.getKey().getValue() && KeyInput.PRESS == ki.getType()) {
					if (ki.getKey().isShiftPressed()) {
						m_focusHandler.tabPrevious();
					} else {
						m_focusHandler.tabNext();
					}
				} else {
					// Send key inputs to the focused widgets
					if (null != m_focusHandler.getFocused()) {
						if (m_focusHandler.getFocused().isFocusable()) {
							m_focusHandler.getFocused().keyInputMessage(ki);
						} else {
							m_focusHandler.focusNone();
						}
					}
				}

				m_focusHandler.applyChanges();
			}

			// Apply changes even if no input has been processed.
			// A widget might have asked for focus.
			m_focusHandler.applyChanges();
		}

		m_widgetTop.logic();
	}

	/**
	 * Sets the Graphics object to use for drawing.
	 * 
	 * @param graphics
	 *            the Graphics object to use for drawing.
	 * @see sdljavax.guichan.gfx.Graphics
	 * @see sdljavax.guichan.sdl.SDLGraphics
	 * @see sdljavax.guichan.opengl.OpenGLGraphics
	 */
	public void setGraphics(Graphics graphics) {
		// TODO Explain about the Graphics object in this comment. (Briefly)
		m_graphics = graphics;
	}

	/**
	 * Sets the Input object to use for input handling.
	 * 
	 * @param input
	 *            the Input object to use for input handling.
	 */
	public void setInput(Input input) {
		// TODO Explain about the Input object in this comment. (Briefly)
		// TODO Maybe change the tab button to be configurable.
		m_input = input;
	}

	/**
	 * Toggle the use of the tab key to focus widgets. By default, tabbing is enabled.
	 * 
	 * @param tabbing
	 *            set to false if you want to disable tabbing
	 */
	public void setTabbingEnabled(boolean tabbing) {
		m_bTabbing = tabbing;
	}

	/**
	 * Sets the top widget of the gui.
	 * 
	 * @param top
	 *            the top widget.
	 */
	public void setTop(Widget top) {
		if (null != m_widgetTop) {
			m_widgetTop.setFocusHandler(null);
		}

		if (null != top) {
			top.setFocusHandler(m_focusHandler);
		}

		m_widgetTop = top;
	}
}
