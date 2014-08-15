/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.evt;

/**
 * This is an internal class used in Guichan to grab mouse input. Generally you won't have to bother
 * using this class.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class MouseInput {
	public static final int	EMPTY		= 0;
	public static final int	LEFT		= 1;
	public static final int	MIDDLE		= 3;
	public static final int	MOTION		= 8;
	public static final int	PRESS		= 6;
	public static final int	RELEASE		= 7;
	public static final int	RIGHT		= 2;
	public static final int	WHEEL_DOWN	= 5;
	public static final int	WHEEL_UP	= 4;

	private int				m_nButton;
	private long			m_lTimeStamp;
	private int				m_nType;
	public int				x, y;

    /**
     * Constructor.
     */
	public MouseInput() {}

    /**
     * Constructor.
     *
     * @param button the button pressed.
     * @param type the type of input.
     * @param mousex the mouse x coordinate.
     * @param mousey the mouse y coordinate.
     * @param timeStamp the mouse inputs time stamp.
     */
	public MouseInput(int button, int type, int mousex, int mousey, long timeStamp) {
		m_nType = type;
		m_nButton = button;
		m_lTimeStamp = timeStamp;
		x = mousex;
		y = mousey;
	}

    /**
     * @return the button pressed.
     */
	public int getButton() {
		return m_nButton;
	}

    /**
     * @return the timestamp of the input.
     */
	public long getTimeStamp() {
		return m_lTimeStamp;
	}

    /**
     * @return the input type.
     */
	public int getType() {
		return m_nType;
	}

    /**
     * Sets the button pressed.
     *
     * @param button the button pressed.
     */
	public void setButton(int button) {
		m_nButton = button;
	}

    /**
     * Sets the timestamp for the input.
     *
     * @param lTimeStamp the timestamp of the input.
     */
	public void setTimeStamp(long lTimeStamp) {
		m_lTimeStamp = lTimeStamp;
	}

    /**
     * Sets the input type.
     *
     * @param type the type of input.
     */
	public void setType(int type) {
		m_nType = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		return new MouseInput(m_nButton, m_nType, x, y, m_lTimeStamp);
	}
}
