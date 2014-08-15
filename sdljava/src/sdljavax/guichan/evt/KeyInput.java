/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.evt;

/**
 * This is an internal class used in Guichan to grab keyboard input. Generally you won't have to
 * bother using this class.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class KeyInput {

	public static final int	EMPTY	= 0;
	public static final int	PRESS	= 1;
	public static final int	RELEASE	= 2;

	private Key				m_key;
	private int				m_nType;

	/**
	 * Constructor.
	 */
	public KeyInput() {}

	/**
	 * Constructor.
	 * 
	 * @param key
	 *            the Key the input concerns.
	 * @param type
	 *            the type of input.
	 */
	public KeyInput(final Key key, int type) {
		m_key = key;
		m_nType = type;
	}

	/**
	 * @return the Key the input concerns.
	 */
	public Key getKey() {
		return m_key;
	}

	/**
	 * @return the input type.
	 */
	public int getType() {
		return m_nType;
	}

	/**
	 * Sets the key the input concerns.
	 * 
	 * @param key
	 *            the Key the input concerns.
	 */
	public void setKey(final Key key) {
		m_key = key;
	}

	/**
	 * Sets the input type.
	 * 
	 * @param type
	 *            the type of input.
	 */
	public void setType(int type) {
		m_nType = type;
	}
}
