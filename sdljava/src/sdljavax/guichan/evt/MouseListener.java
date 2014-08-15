/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.evt;

import sdljavax.guichan.GUIException;

/**
 * Mouse listeners interace. In order to use this interface you must implement it and its functions.
 * MouseListeners listen for mouse events on a Widgets. When a Widget recives a mouse event, the
 * corresponding function in all its mouse listeners will be called.
 * 
 * @see sdljavax.guichan.widgets.Widget#addMouseListener(MouseListener)
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface MouseListener {
	/**
	 * This function is called when the mouse enters into the widget area.
	 */
	public void mouseIn() throws GUIException;

	/**
	 * This function is called when the mouse leaves the widget area.
	 */
	public void mouseOut();

	/**
	 * This function is called when the mouse moves and the mouse is in the widget area or if the
	 * widget has focus.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 */
	public void mouseMotion(int x, int y) throws GUIException;

	/**
	 * This function is called when a mouse button is pressed when the mouse is in the widget area
	 * or if the widget has focus.
	 * 
	 * NOTE: A mouse press is NOT equal to a mouse click. Use mouseClickMessage to check for mouse
	 * clicks.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 * @param button
	 *            the button pressed
	 * @see #mouseClick(int, int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException;

	/**
	 * This function is called on a mouse wheel up when the mouse is in the widget area or if the
	 * widget has focus.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 */
	public void mouseWheelUp(int x, int y) throws GUIException;

	/**
	 * This function is called on a mouse wheel down when the mouse is in the widget area or if the
	 * widget has focus.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 */
	public void mouseWheelDown(int x, int y) throws GUIException;

	/**
	 * This function is called when a mouse button is released when the mouse is in the widget area
	 * or if the widget has focus.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 * @param button
	 *            the button released
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException;

	/**
	 * This function is called when a mouse button is pressed and released (clicked) when the mouse
	 * is in the widget area or if the widget has focus.
	 * 
	 * @param x
	 *            the x coordinate of the mouse relative to the widget itself.
	 * @param y
	 *            the y coordinate of the mouse relative to the widget itself.
	 * @param button
	 *            the button clicked
	 * @param count
	 *            the number of clicks
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException;
}
