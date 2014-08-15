/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.evt;

/**
 * This represents a key or a character.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Key {
	/**
	 * Key values.
	 */
	public static int	SPACE			= ' ';
	public static int	TAB				= '\t';
	public static int	ENTER			= '\n';
	public static int	LEFT_ALT		= 1000;
	public static int	RIGHT_ALT		= 1001;
	public static int	LEFT_SHIFT		= 1002;
	public static int	RIGHT_SHIFT		= 1003;
	public static int	LEFT_CONTROL	= 1004;
	public static int	RIGHT_CONTROL	= 1005;
	public static int	LEFT_META		= 1006;
	public static int	RIGHT_META		= 1007;
	public static int	LEFT_SUPER		= 1008;
	public static int	RIGHT_SUPER		= 1009;
	public static int	INSERT			= 1010;
	public static int	HOME			= 1011;
	public static int	PAGE_UP			= 1012;
	public static int	DELETE			= 1013;
	public static int	END				= 1014;
	public static int	PAGE_DOWN		= 1015;
	public static int	ESCAPE			= 1016;
	public static int	CAPS_LOCK		= 1017;
	public static int	BACKSPACE		= 1018;
	public static int	F1				= 1019;
	public static int	F2				= 1020;
	public static int	F3				= 1021;
	public static int	F4				= 1022;
	public static int	F5				= 1023;
	public static int	F6				= 1024;
	public static int	F7				= 1025;
	public static int	F8				= 1026;
	public static int	F9				= 1027;
	public static int	F10				= 1028;
	public static int	F11				= 1029;
	public static int	F12				= 1030;
	public static int	F13				= 1031;
	public static int	F14				= 1032;
	public static int	F15				= 1033;
	public static int	PRINT_SCREEN	= 1034;
	public static int	SCROLL_LOCK		= 1035;
	public static int	PAUSE			= 1036;
	public static int	NUM_LOCK		= 1037;
	public static int	ALT_GR			= 1038;
	public static int	LEFT			= 1039;
	public static int	RIGHT			= 1040;
	public static int	UP				= 1041;
	public static int	DOWN			= 1042;

	private int			m_nValue		= 0;
	private boolean		m_bShiftPressed;
	private boolean		m_bControlPressed;
	private boolean		m_bAltPressed;
	private boolean		m_bMetaPressed;
	private boolean		m_bNumericPad;

	/**
	 * Constructor.
	 */
	public Key() {
		m_bShiftPressed = false;
		m_bControlPressed = false;
		m_bAltPressed = false;
		m_bMetaPressed = false;
		m_bNumericPad = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            the ascii or enum value for the key.
	 */
	public Key(int value) {
		this();
		m_nValue = value;
	}

	private boolean isControl() {
		return (m_nValue >= LEFT_ALT && m_nValue <= DOWN) || m_nValue == ENTER;
	}

	/**
	 * @return true if the key is a letter, number or whitespace.
	 */
	public boolean isCharacter() {
		return Character.isLetterOrDigit((char) m_nValue) && false == isControl();
	}

	/**
	 * @return true if the key is a number (0-9).
	 */
	public boolean isNumber() {
		return Character.isDigit((char) m_nValue) && false == isControl();
	}

	/**
	 * @return true if the key is a letter (a-z,A-Z).
	 */
	public boolean isLetter() {
		return Character.isLetter((char) m_nValue) && false == isControl();
	}

	/**
	 * @return true if shift was pressed at the same time as the key.
	 */
	public boolean isShiftPressed() {
		return m_bShiftPressed;
	}

	/**
	 * Sets the shift pressed flag.
	 * 
	 * @param pressed
	 *            the shift flag value.
	 */
	public void setShiftPressed(boolean pressed) {
		m_bShiftPressed = pressed;
	}

	/**
	 * @return true if control was pressed at the same time as the key.
	 */
	public boolean isControlPressed() {
		return m_bControlPressed;
	}

	/**
	 * Sets the control pressed flag.
	 * 
	 * @param pressed
	 *            the control flag value.
	 */
	public void setControlPressed(boolean pressed) {
		m_bControlPressed = pressed;
	}

	/**
	 * @return true if alt was pressed at the same time as the key.
	 */
	public boolean isAltPressed() {
		return m_bAltPressed;
	}

	/**
	 * Sets the alt pressed flag.
	 * 
	 * @param pressed
	 *            the alt flag value.
	 */
	public void setAltPressed(boolean pressed) {
		m_bAltPressed = pressed;
	}

	/**
	 * @return true if meta was pressed at the same time as the key.
	 */
	public boolean isMetaPressed() {
		return m_bMetaPressed;
	}

	/**
	 * Sets the meta pressed flag.
	 * 
	 * @param pressed
	 *            the meta flag value.
	 */
	public void setMetaPressed(boolean pressed) {
		m_bMetaPressed = pressed;
	}

	/**
	 * @return true if key pressed at the numeric pad.
	 */
	public boolean isNumericPad() {
		return m_bNumericPad;
	}

	/**
	 * Sets the numeric pad flag.
	 * 
	 * @param numpad
	 *            the numeric pad flag value.
	 */
	public void setNumericPad(boolean numpad) {
		m_bNumericPad = numpad;
	}

	/**
	 * @return the value of the key, an ascii value if exists otherwise an enum value will be
	 *         returned.
	 */
	public int getValue() {
		return m_nValue;
	}

	/**
	 * Sets the value of the key. An ascii value or an enum value.
	 * 
	 * @param value
	 *            the key value.
	 */
	public void setValue(int value) {
		m_nValue = value;
	}
}
