/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.evt;

import sdljavax.guichan.GUIException;

/**
 * Key listeners interface. In order to use this interfce you must implement it and its functions.
 * KeyListeners listen for key events on a Widgets. When a Widget recives a key event, the
 * corresponding function in all its key listeners will be called. Only focused Widgets will
 * generate key events.
 * 
 * @see sdljavax.guichan.widgets.Widget#addKeyListener(KeyListener)
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface KeyListener {
	/**
	 * This function is called if a key is pressed when the widget has keyboard focus.
	 * 
	 * If a key is held down the widget will generate multiple key presses.
	 * 
	 * @param key
	 *            the key pressed
	 * @see Key
	 */
	public void keyPress(final Key key) throws GUIException;

	/**
	 * This function is called if a key is released when the widget has keyboard focus.
	 * 
	 * @param key
	 *            the key released
	 * @see Key
	 */
	public void keyRelease(final Key key) throws GUIException;
}
