/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is a regular button. Add an ActionListener to it to know when it has been clicked.
 * 
 * NOTE: You can only have text (a caption) on the button. If you want it to handle, for instance
 * images, you can implement an ImageButton of your own and overload member functions from Button.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Button extends Widget implements MouseListener, KeyListener {
	protected boolean	m_bKeyDown;
	protected boolean	m_bMouseDown;
	protected int		m_nAlignment;
	protected String	m_strCaption;

	/*
	 * Constructor
	 */
	public Button() throws GUIException {
		this(null);
	}

	/**
	 * Create a Button with a caption
	 * 
	 * @param strCaption
	 *            Caption for this button
	 * @throws GUIException 
	 */
	public Button(String strCaption) throws GUIException {
		super();
		m_strCaption = strCaption;
		m_nAlignment = Graphics.CENTER;
		setFocusable(true);

		adjustSize();
		setBorderSize(1);

		m_bMouseDown = false;
		m_bKeyDown = false;

		addMouseListener(this);
		addKeyListener(this);
	}

	/**
	 * Adjusts the buttons size to fit the content.
	 */
	public void adjustSize() {
		setWidth(getFont().getWidth(m_strCaption) + 8);
		setHeight(getFont().getHeight() + 8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		Color faceColor = getBaseColor();
		Color highlightColor, shadowColor;
		int alpha = getBaseColor().a;

		if (isPressed()) {
			faceColor = faceColor.subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.add(0x303030);
			shadowColor.a = alpha;
		} else {
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;
		}

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(1, 1, getDimension().width - 1, getHeight() - 1));

		graphics.setColor(highlightColor);
		graphics.drawLine(0, 0, getWidth() - 1, 0);
		graphics.drawLine(0, 1, 0, getHeight() - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight() - 1);
		graphics.drawLine(1, getHeight() - 1, getWidth() - 1, getHeight() - 1);

		graphics.setColor(getForegroundColor());

		int textX;
		int textY = getHeight() / 2 - getFont().getHeight() / 2;

		switch (getAlignment()) {
			case Graphics.LEFT :
				textX = 4;
				break;
			case Graphics.CENTER :
				textX = getWidth() / 2;
				break;
			case Graphics.RIGHT :
				textX = getWidth() - 4;
				break;
			default :
				throw new GUIException("Unknown alignment.");
		}

		graphics.setFont(getFont());

		if (isPressed()) {
			graphics.drawText(getCaption(), textX + 1, textY + 1, getAlignment());
		} else {
			graphics.drawText(getCaption(), textX, textY, getAlignment());

			if (hasFocus()) {
				graphics.drawRectangle(new Rectangle(2, 2, getWidth() - 4, getHeight() - 4));
			}
		}
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
			graphics.drawLine(i, i + 1, i, height - i - 1);
			graphics.setColor(highlightColor);
			graphics.drawLine(width - i, i + 1, width - i, height - i);
			graphics.drawLine(i, height - i, width - i - 1, height - i);
		}
	}

	/**
	 * Get the alignment for the caption.
	 * 
	 * @return alignment of caption.
	 */
	public int getAlignment() {
		return m_nAlignment;
	}

	/**
	 * @return the caption of the button.
	 */
	public String getCaption() {
		return m_strCaption;
	}

	/**
	 * Checks if the button is pressed down, useful when drawing
	 * 
	 * @return true if the button is pressed down.
	 */
	public boolean isPressed() {
		return (hasMouse() && m_bMouseDown) || m_bKeyDown;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (Key.ENTER == key.getValue() || Key.SPACE == key.getValue()) {
			m_bKeyDown = true;
		}
		m_bMouseDown = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyRelease(sdljavax.guichan.evt.Key)
	 */
	public void keyRelease(Key key) throws GUIException {
		if ((Key.ENTER == key.getValue() || Key.SPACE == key.getValue()) && m_bKeyDown) {
			m_bKeyDown = false;
			generateAction();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#lostFocus()
	 */
	public void lostFocus() throws GUIException {
		m_bMouseDown = false;
		m_bKeyDown = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {
		if (MouseInput.LEFT == button) {
			generateAction();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseOut()
	 */
	public void mouseOut() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (MouseInput.LEFT == button && hasMouse()) {
			m_bMouseDown = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {
		if (MouseInput.LEFT == button) {
			m_bMouseDown = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {}

	/**
	 * Set the alignment for the caption.
	 * 
	 * @param alignment
	 *            Graphics.LEFT, Graphics.CENTER or Graphics.RIGHT
	 * @see Graphics
	 */
	public void setAlignment(int alignment) {
		m_nAlignment = alignment;
	}

	/**
	 * Set the caption of a button.
	 * 
	 * @param caption
	 *            the caption of the button.
	 */
	public void setCaption(String caption) {
		m_strCaption = caption;
	}
}
