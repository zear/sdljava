/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.sdl;

import java.util.Stack;

import sdljava.SDLTimer;
import sdljava.event.SDLActiveEvent;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLMouseButtonEvent;
import sdljava.event.SDLMouseMotionEvent;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.Input;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyInput;
import sdljavax.guichan.evt.MouseInput;

/**
 * For comments regarding functions please see the interface file.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class SDLInput implements Input {
	private boolean	m_bMouseDown		= false;

	private Stack	m_stackKeyInput		= new Stack();
	private Stack	m_stackMouseInput	= new Stack();

	/**
	 * Converts an SDL mouse button to a MouseInput value
	 * 
	 * @param nButton
	 *            SDL button value
	 * @return MouseInput value
	 */
	private int convertMouseButton(int nButton) {
		switch (nButton) {
			case SDLMouseButtonEvent.SDL_BUTTON_LEFT :
				return MouseInput.LEFT;
			case SDLMouseButtonEvent.SDL_BUTTON_RIGHT :
				return MouseInput.RIGHT;
			case SDLMouseButtonEvent.SDL_BUTTON_MIDDLE :
				return MouseInput.MIDDLE;
			case SDLMouseButtonEvent.SDL_BUTTON_WHEELUP :
				return MouseInput.WHEEL_UP;
			case SDLMouseButtonEvent.SDL_BUTTON_WHEELDOWN :
				return MouseInput.WHEEL_DOWN;
		}
		// TODO Exception!
		return 0;
	}

	/**
	 * Converts an SDL key event to a Key value
	 * 
	 * @param keysym
	 *            SDLKeyboardEvent
	 * @return the corresponding Key value
	 */
	private Key convertKeyCharacter(SDLKeyboardEvent keysym) {
		int value = 0;

		if (keysym.getUnicode() < 255) {
			value = keysym.getUnicode();
		}

		switch (keysym.getSym()) {
			case SDLKey.SDLK_TAB :
				value = Key.TAB;
				break;
			case SDLKey.SDLK_LALT :
				value = Key.LEFT_ALT;
				break;
			case SDLKey.SDLK_RALT :
				value = Key.RIGHT_ALT;
				break;
			case SDLKey.SDLK_LSHIFT :
				value = Key.LEFT_SHIFT;
				break;
			case SDLKey.SDLK_RSHIFT :
				value = Key.RIGHT_SHIFT;
				break;
			case SDLKey.SDLK_LCTRL :
				value = Key.LEFT_CONTROL;
				break;
			case SDLKey.SDLK_RCTRL :
				value = Key.RIGHT_CONTROL;
				break;
			case SDLKey.SDLK_BACKSPACE :
				value = Key.BACKSPACE;
				break;
			case SDLKey.SDLK_PAUSE :
				value = Key.PAUSE;
				break;
			case SDLKey.SDLK_SPACE :
				value = Key.SPACE;
				break;
			case SDLKey.SDLK_ESCAPE :
				value = Key.ESCAPE;
				break;
			case SDLKey.SDLK_DELETE :
				value = Key.DELETE;
				break;
			case SDLKey.SDLK_INSERT :
				value = Key.INSERT;
				break;
			case SDLKey.SDLK_HOME :
				value = Key.HOME;
				break;
			case SDLKey.SDLK_END :
				value = Key.END;
				break;
			case SDLKey.SDLK_PAGEUP :
				value = Key.PAGE_UP;
				break;
			case SDLKey.SDLK_PRINT :
				value = Key.PRINT_SCREEN;
				break;
			case SDLKey.SDLK_PAGEDOWN :
				value = Key.PAGE_DOWN;
				break;
			case SDLKey.SDLK_F1 :
				value = Key.F1;
				break;
			case SDLKey.SDLK_F2 :
				value = Key.F2;
				break;
			case SDLKey.SDLK_F3 :
				value = Key.F3;
				break;
			case SDLKey.SDLK_F4 :
				value = Key.F4;
				break;
			case SDLKey.SDLK_F5 :
				value = Key.F5;
				break;
			case SDLKey.SDLK_F6 :
				value = Key.F6;
				break;
			case SDLKey.SDLK_F7 :
				value = Key.F7;
				break;
			case SDLKey.SDLK_F8 :
				value = Key.F8;
				break;
			case SDLKey.SDLK_F9 :
				value = Key.F9;
				break;
			case SDLKey.SDLK_F10 :
				value = Key.F10;
				break;
			case SDLKey.SDLK_F11 :
				value = Key.F11;
				break;
			case SDLKey.SDLK_F12 :
				value = Key.F12;
				break;
			case SDLKey.SDLK_F13 :
				value = Key.F13;
				break;
			case SDLKey.SDLK_F14 :
				value = Key.F14;
				break;
			case SDLKey.SDLK_F15 :
				value = Key.F15;
				break;
			case SDLKey.SDLK_NUMLOCK :
				value = Key.NUM_LOCK;
				break;
			case SDLKey.SDLK_CAPSLOCK :
				value = Key.CAPS_LOCK;
				break;
			case SDLKey.SDLK_SCROLLOCK :
				value = Key.SCROLL_LOCK;
				break;
			case SDLKey.SDLK_RMETA :
				value = Key.RIGHT_META;
				break;
			case SDLKey.SDLK_LMETA :
				value = Key.LEFT_META;
				break;
			case SDLKey.SDLK_LSUPER :
				value = Key.LEFT_SUPER;
				break;
			case SDLKey.SDLK_RSUPER :
				value = Key.RIGHT_SUPER;
				break;
			case SDLKey.SDLK_MODE :
				value = Key.ALT_GR;
				break;
			case SDLKey.SDLK_UP :
				value = Key.UP;
				break;
			case SDLKey.SDLK_DOWN :
				value = Key.DOWN;
				break;
			case SDLKey.SDLK_LEFT :
				value = Key.LEFT;
				break;
			case SDLKey.SDLK_RIGHT :
				value = Key.RIGHT;
				break;
			case SDLKey.SDLK_RETURN :
				value = Key.ENTER;
				break;
			case SDLKey.SDLK_KP_ENTER :
				value = Key.ENTER;
				break;

			default :
				break;
		}

		if (false == (keysym.getMod().num())) {
			switch (keysym.getSym()) {
				case SDLKey.SDLK_KP0 :
					value = Key.INSERT;
					break;
				case SDLKey.SDLK_KP1 :
					value = Key.END;
					break;
				case SDLKey.SDLK_KP2 :
					value = Key.DOWN;
					break;
				case SDLKey.SDLK_KP3 :
					value = Key.PAGE_DOWN;
					break;
				case SDLKey.SDLK_KP4 :
					value = Key.LEFT;
					break;
				case SDLKey.SDLK_KP5 :
					value = 0;
					break;
				case SDLKey.SDLK_KP6 :
					value = Key.RIGHT;
					break;
				case SDLKey.SDLK_KP7 :
					value = Key.HOME;
					break;
				case SDLKey.SDLK_KP8 :
					value = Key.UP;
					break;
				case SDLKey.SDLK_KP9 :
					value = Key.PAGE_UP;
					break;
				default :
					break;
			}
		}

		Key key = new Key();

		key.setValue(value);
		key.setShiftPressed(keysym.getMod().shift());
		key.setControlPressed(keysym.getMod().ctrl());
		key.setAltPressed(keysym.getMod().alt());
		key.setMetaPressed(keysym.getMod().meta());

		if (keysym.getSym() >= SDLKey.SDLK_KP0 && keysym.getSym() <= SDLKey.SDLK_KP_EQUALS) {
			key.setNumericPad(true);
		}

		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.Input#isKeyQueueEmpty()
	 */
	public boolean isKeyQueueEmpty() {
		return m_stackKeyInput.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.Input#dequeueKeyInput()
	 */
	public KeyInput dequeueKeyInput() throws GUIException {
		if (m_stackKeyInput.isEmpty()) {
			throw new GUIException("The key input queue is empty");
		}

		return (KeyInput) m_stackKeyInput.pop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.Input#isMouseQueueEmpty()
	 */
	public boolean isMouseQueueEmpty() {
		return m_stackMouseInput.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.Input#dequeueMouseInput()
	 */
	public MouseInput dequeueMouseInput() throws GUIException {
		if (m_stackMouseInput.isEmpty()) {
			throw new GUIException("the mouse input queue is empty");
		}

		return (MouseInput) m_stackMouseInput.pop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.Input#pollInput()
	 */
	public void pollInput() {
	// Nothing to do for SDL
	}

	/**
	 * Add an SDLEvent to the internal input queue
	 * 
	 * @param event
	 *            SDLEvent to queue
	 */
	public void pushInput(SDLEvent event) {
		KeyInput keyInput = new KeyInput();
		MouseInput mouseInput = new MouseInput();

		switch (event.getType()) {
			case SDLEvent.SDL_KEYDOWN :
				keyInput.setKey(convertKeyCharacter((SDLKeyboardEvent) event));
				keyInput.setType(KeyInput.PRESS);
				m_stackKeyInput.push(keyInput);
				break;

			case SDLEvent.SDL_KEYUP :
				keyInput.setKey(convertKeyCharacter((SDLKeyboardEvent) event));
				keyInput.setType(KeyInput.RELEASE);
				m_stackKeyInput.push(keyInput);
				break;

			case SDLEvent.SDL_MOUSEBUTTONDOWN :
				m_bMouseDown = true;
				mouseInput.x = ((SDLMouseButtonEvent) event).getX();
				mouseInput.y = ((SDLMouseButtonEvent) event).getY();
				mouseInput.setButton(convertMouseButton(((SDLMouseButtonEvent) event).getButton()));
				mouseInput.setType(MouseInput.PRESS);
				mouseInput.setTimeStamp(SDLTimer.getTicks());
				m_stackMouseInput.push(mouseInput);
				break;

			case SDLEvent.SDL_MOUSEBUTTONUP :
				m_bMouseDown = false;
				mouseInput.x = ((SDLMouseButtonEvent) event).getX();
				mouseInput.y = ((SDLMouseButtonEvent) event).getY();
				mouseInput.setButton(convertMouseButton(((SDLMouseButtonEvent) event).getButton()));
				mouseInput.setType(MouseInput.RELEASE);
				mouseInput.setTimeStamp(SDLTimer.getTicks());
				m_stackMouseInput.push(mouseInput);
				break;

			case SDLEvent.SDL_MOUSEMOTION :
				mouseInput.x = ((SDLMouseMotionEvent) event).getX();
				mouseInput.y = ((SDLMouseMotionEvent) event).getY();
				mouseInput.setButton(MouseInput.EMPTY);
				mouseInput.setType(MouseInput.MOTION);
				mouseInput.setTimeStamp(SDLTimer.getTicks());
				m_stackMouseInput.push(mouseInput);
				break;

			case SDLEvent.SDL_ACTIVEEVENT :
				/*
				 * This occurs when the mouse leaves the window and the Gui-chan application loses
				 * its mousefocus.
				 */
				if ((((SDLActiveEvent) event).getSwigActiveEvent().getState() == SDLActiveEvent.SDL_APPMOUSEFOCUS)
					&& (0 == ((SDLActiveEvent) event).getSwigActiveEvent().getGain())) {
					if (false == m_bMouseDown) {
						mouseInput.x = -1;
						mouseInput.y = -1;
						mouseInput.setButton(MouseInput.EMPTY);
						mouseInput.setType(MouseInput.MOTION);
						m_stackMouseInput.push(mouseInput);
					}
				}
				break;
		}
	}
}
