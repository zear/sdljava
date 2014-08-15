/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.evt;

import sdljavax.guichan.GUIException;

/**
 * Input Interface. Contains basic Input functions every input class should have.
 * Input classes should implement this interface and its functions. 
 * 
 * @see sdljavax.guichan.sdl.SDLInput
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface Input {
	/**
	 * Checks whether the key queue is empty or not.
	 * 
	 * @return true if the key queue is empty.
	 */
	public boolean isKeyQueueEmpty();

	/**
	 * Dequeues the key input queue.
	 * 
	 * @return a KeyInput object.
	 * @see KeyInput
	 */
	public KeyInput dequeueKeyInput() throws GUIException;

	/**
	 * Checks whether the mouse queue is empyt or not.
	 * 
	 * @return true if the mouse queue is empty.
	 */
	public boolean isMouseQueueEmpty();

	/**
	 * Dequeues the mouse input queue.
	 * 
	 * @return a MouseInput object.
	 * @see MouseInput
	 */
	public MouseInput dequeueMouseInput() throws GUIException;

	/**
	 * This function polls all input. It exists for input driver compatibility. It is used
	 * internally by the library.
	 */
	public void pollInput();
}
