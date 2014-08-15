/*
 * Created on Mar 6, 2005
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
 * This is a text field. It holds exactly one line of text that can be edited. If the enter key is
 * pressed when the textfield has focus it will fire an action to any connected ActionListeners.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class TextField extends Widget implements MouseListener, KeyListener {
	protected String	mText;
	protected int		mCaretPosition;
	protected int		mXScroll;

	/**
	 * Default constructor.
	 * @throws GUIException 
	 */
	public TextField() throws GUIException {
		this("");
	}

	/**
	 * Constructor, initializes the textfield with a given string.
	 * 
	 * @param text
	 *            the initial content of the text field
	 * @throws GUIException 
	 */
	public TextField(String text) throws GUIException {
		super();
		mCaretPosition = 0;
		mXScroll = 0;
		mText = text;
		adjustSize();
		setBorderSize(1);
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
	}

	/**
	 * Adjusts the size (width and height) of the text field, so that the text fits precisely. The
	 * constructor taking a string uses this function to initialize the size of the text field.
	 */
	public void adjustSize() {
		setWidth(getFont().getWidth(mText) + 4);
		adjustHeight();
		fixScroll();
	}

	/**
	 * Adjusts the height of the text field, so that the text fits precisely. the height of the text
	 * field is initialized with this function by the constructors.
	 */
	public void adjustHeight() {
		setHeight(getFont().getHeight() + 2);
	}

	/**
	 * Scrolls the text horizontally so that the caret shows. (if needed)
	 */
	protected void fixScroll() {
		if (hasFocus()) {
			int caretX = getFont().getWidth(mText.substring(0, mCaretPosition));
			if (caretX - mXScroll > getWidth() - 4) {
				mXScroll = caretX - getWidth() + 4;
			} else if (caretX - mXScroll < getFont().getWidth(" ")) {
				mXScroll = caretX - getFont().getWidth(" ");
				if (mXScroll < 0) {
					mXScroll = 0;
				}
			}
		}
	}

	/**
	 * Gets the position of the caret.
	 * 
	 * @return the position of the caret.
	 */
	public int getCaretPosition() {
		return mCaretPosition;
	}

	/**
	 * Sets the position of the caret.
	 * 
	 * @param position the new position
	 */
	public void setCaretPosition(int position) {
		if (position > mText.length()) {
			mCaretPosition = mText.length();
		} else {
			mCaretPosition = position;
		}
		fixScroll();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#fontChanged()
	 */
	public void fontChanged() {
		fixScroll();
	}

	/**
	 * Gets the content of the text field.
	 * 
	 * @return the content of the text field
	 */
	public String getText() {
		return mText;
	}

	/**
	 * Sets the text of the text field.
	 * 
	 * @param text
	 *            the new content
	 */
	public void setText(String text) {
		if (text.length() < mCaretPosition) {
			mCaretPosition = text.length();
		}
		mText = text;
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		graphics.setColor(getBackgroundColor());
		graphics.fillRectangle(new Rectangle(0, 0, getWidth(), getHeight()));

		if (hasFocus()) {
			drawCaret(graphics, getFont().getWidth(mText.substring(0, mCaretPosition)) - mXScroll);
		}

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());
		graphics.drawText(mText, 1 - mXScroll, 1);
	}

	/**
	 * Draws the caret (the little marker in the text that shows where the letters you type will
	 * appear). Easily overloaded if you want to change the style of the caret.
	 * 
	 * @param graphics
	 *            the graphics object
	 * @param x
	 *            the caret's x-position
	 */
	public void drawCaret(Graphics graphics, int x) throws GUIException {
		graphics.setColor(getForegroundColor());
		graphics.drawLine(x, getHeight() - 2, x, 1);
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		System.out.println("mCaretPosition = " + mCaretPosition);
		if (key.getValue() == Key.LEFT && mCaretPosition > 0) {
			--mCaretPosition;
		} else if (key.getValue() == Key.RIGHT && mCaretPosition < mText.length()) {
			++mCaretPosition;
		} else if (key.getValue() == Key.DELETE && mCaretPosition < mText.length()) {
			mText = mText.substring(0, mCaretPosition) + mText.substring(mCaretPosition + 1);
		} else if (key.getValue() == Key.BACKSPACE && mCaretPosition > 0) {
			mText = mText.substring(0, mCaretPosition - 1) + mText.substring(mCaretPosition);
			--mCaretPosition;
		} else if (key.getValue() == Key.ENTER) {
			generateAction();
		} else if (key.getValue() == Key.HOME) {
			mCaretPosition = 0;
		} else if (key.getValue() == Key.END) {
			mCaretPosition = mText.length();
		} else if (key.isCharacter()) {
			mText = mText.substring(0, mCaretPosition) + (char) key.getValue() + mText.substring(mCaretPosition);
			++mCaretPosition;
		}
		fixScroll();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (hasMouse() && button == MouseInput.LEFT) {
			mCaretPosition = getFont().getStringIndexAt(mText, x + mXScroll);
			fixScroll();
		}
	}

	/* (non-Javadoc)
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
			graphics.drawLine(i, i, i, height - i);
			graphics.setColor(highlightColor);
			graphics.drawLine(width - i, i, width - i, height - i);
			graphics.drawLine(i, height - i, width - i, height - i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseOut()
	 */
	public void mouseOut() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyRelease(sdljavax.guichan.evt.Key)
	 */
	public void keyRelease(Key key) throws GUIException {}
}
