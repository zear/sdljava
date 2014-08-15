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
 * The slider widget is ideal for volume controls and the like.
 * 
 * You can set the scale of the slider yourself so that it ranges between, for example -1.0 and 2.0.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Slider extends Widget implements MouseListener, KeyListener {
	public static final int	HORIZONTAL			= 0;
	public static final int	VERTICAL			= 1;

	protected boolean		m_bMouseDrag		= false;
	protected double		m_dScaleEnd;
	protected double		m_dScaleStart;
	protected int			m_nMarkerPosition	= 0;
	protected int			m_nMarkerLength		= 10;
	protected int			m_nOrientation		= HORIZONTAL;
	protected double		m_dStepLength;
	protected double		m_dValue;

	/**
	 * In this constructor, the scale start is 0.
	 * 
	 * @param scaleEnd
	 *            the end of the slider scale.
	 * @throws GUIException 
	 */
	public Slider(double scaleEnd) throws GUIException {
		this(0, scaleEnd);
	}

	/**
	 * Constructor
	 * 
	 * @param scaleStart
	 *            the start of the scale.
	 * @param scaleEnd
	 *            the end of the scale.
	 * @throws GUIException 
	 */
	public Slider(double scaleStart, double scaleEnd) throws GUIException {
		super();

		m_dScaleStart = scaleStart;
		m_dScaleEnd = scaleEnd;

		setFocusable(true);
		setBorderSize(1);

		setValue(scaleStart);
		setStepLength((scaleEnd - scaleStart) / 10);

		addMouseListener(this);
		addKeyListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		Color shadowColor = getBaseColor().subtract(0x101010);
		int alpha = getBaseColor().a;
		shadowColor.a = alpha;

		graphics.setColor(shadowColor);
		graphics.fillRectangle(new Rectangle(0, 0, getWidth(), getHeight()));

		drawMarker(graphics);
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
			graphics.drawLine(i, i, i, height - i);
			graphics.setColor(highlightColor);
			graphics.drawLine(width - i, i, width - i, height - i);
			graphics.drawLine(i, height - i, width - i, height - i);
		}
	}

	/**
	 * Draws the marker.
	 * 
	 * @param graphics
	 *            a graphics object to be used for drawing.
	 */
	protected void drawMarker(Graphics graphics) throws GUIException {
		Color faceColor = getBaseColor();
		Color highlightColor, shadowColor;
		int alpha = getBaseColor().a;
		highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(faceColor);

		if (HORIZONTAL == getOrientation()) {
			int v = getMarkerPosition();
			graphics.fillRectangle(new Rectangle(v + 1, 1, getMarkerLength() - 2, getHeight() - 2));

			graphics.setColor(highlightColor);
			graphics.drawLine(v, 0, v + getMarkerLength() - 1, 0);
			graphics.drawLine(v, 0, v, getHeight() - 1);
			graphics.setColor(shadowColor);
			graphics.drawLine(v + getMarkerLength() - 1, 1, v + getMarkerLength() - 1, getHeight() - 1);
			graphics.drawLine(v + 1, getHeight() - 1, v + getMarkerLength() - 1, getHeight() - 1);

			if (hasFocus()) {
				graphics.setColor(getForegroundColor());
				graphics.drawRectangle(new Rectangle(v + 2, 2, getMarkerLength() - 4, getHeight() - 4));
			}
		} else {
			int v = (getHeight() - getMarkerLength()) - getMarkerPosition();
			graphics.fillRectangle(new Rectangle(1, v + 1, getWidth() - 2, getMarkerLength() - 2));
			graphics.setColor(highlightColor);
			graphics.drawLine(0, v, 0, v + getMarkerLength() - 1);
			graphics.drawLine(0, v, getWidth() - 1, v);
			graphics.setColor(shadowColor);
			graphics.drawLine(1, v + getMarkerLength() - 1, getWidth() - 1, v + getMarkerLength() - 1);
			graphics.drawLine(getWidth() - 1, v + 1, getWidth() - 1, v + getMarkerLength() - 1);

			if (hasFocus()) {
				graphics.setColor(getForegroundColor());
				graphics.drawRectangle(new Rectangle(2, v + 2, getWidth() - 4, getMarkerLength() - 4));
			}
		}
	}

	/**
	 * Get the marker position for the current value.
	 * 
	 * @return the marker position for the current value.
	 */
	protected int getMarkerPosition() {
		return valueToMarkerPosition(getValue());
	}

	/**
	 * Converts a value to a marker position.
	 * 
	 * @param value
	 *            the value to convert.
	 * @return the position corresponding to the value.
	 */
	protected int valueToMarkerPosition(double value) {
		int v;
		if (getOrientation() == HORIZONTAL) {
			v = getWidth();
		} else {
			v = getHeight();
		}

		int w = (int) ((v - getMarkerLength()) * (value - getScaleStart()) / (getScaleEnd() - getScaleStart()));

		if (w < 0) {
			return 0;
		}

		if (w > v - getMarkerLength()) {
			return v - getMarkerLength();
		}

		return w;
	}

	/**
	 * Gets the length of the marker.
	 * 
	 * @return the length of the marker.
	 */
	public int getMarkerLength() {
		return m_nMarkerLength;
	}

	/**
	 * Gets the scale end.
	 * 
	 * @return the scale emd.
	 */
	public double getScaleEnd() {
		return m_dScaleEnd;
	}

	/**
	 * Gets the scale start.
	 * 
	 * @return the scale start.
	 */
	public double getScaleStart() {
		return m_dScaleStart;
	}

	/**
	 * Gets the current value.
	 * 
	 * @return the current value.
	 */
	public double getValue() {
		return m_dValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (HORIZONTAL == getOrientation()) {
			if (Key.RIGHT == key.getValue()) {
				setValue(getValue() + getStepLength());
				generateAction();
			} else if (Key.LEFT == key.getValue()) {
				setValue(getValue() - getStepLength());
				generateAction();
			}
		} else {
			if (Key.UP == key.getValue()) {
				setValue(getValue() + getStepLength());
				generateAction();
			} else if (Key.DOWN == key.getValue()) {
				setValue(getValue() - getStepLength());
				generateAction();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyRelease(sdljavax.guichan.evt.Key)
	 */
	public void keyRelease(Key key) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#lostFocus()
	 */
	public void lostFocus() throws GUIException {
		m_bMouseDrag = false;
	}

	/**
	 * Converts a marker position to a value.
	 * 
	 * @param v
	 *            the position to convert.
	 * @return the value corresponding to the position.
	 */
	protected double markerPositionToValue(int v) {
		int w;
		if (HORIZONTAL == getOrientation()) {
			w = getWidth();
		} else {
			w = getHeight();
		}

		double pos = v / ((double) w - getMarkerLength());
		return (1.0 - pos) * getScaleStart() + pos * getScaleEnd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {
		if (m_bMouseDrag) {
			if (HORIZONTAL == getOrientation()) {
				setValue(markerPositionToValue(x - getMarkerLength() / 2));
			} else {
				setValue(markerPositionToValue(getHeight() - y - getMarkerLength() / 2));
			}
			generateAction();
		}
	}

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
		if (MouseInput.LEFT == button && x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight()) {
			if (HORIZONTAL == getOrientation()) {
				setValue(markerPositionToValue(x - getMarkerLength() / 2));
			} else {
				setValue(markerPositionToValue(getHeight() - y - getMarkerLength() / 2));
			}

			m_bMouseDrag = true;
			generateAction();
		} else {
			m_bMouseDrag = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) {
		m_bMouseDrag = false;
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
	 * Sets the length of the marker.
	 * 
	 * @param length
	 *            new length for the marker.
	 */
	public void setMarkerLength(int length) {
		m_nMarkerLength = length;
	}

	/**
	 * Set the orientation of the slider. A slider can be drawn verticaly or horizontaly. For
	 * orientation, see the enum in this class.
	 * 
	 * @param orientation
	 *            the orientation.
	 */
	public void setOrientation(int orientation) {
		m_nOrientation = orientation;
	}

	/**
	 * Sets the scale.
	 * 
	 * @param scaleStart
	 *            the start of the scale.
	 * @param scaleEnd
	 *            the end of the scale.
	 */
	public void setScale(double scaleStart, double scaleEnd) {
		m_dScaleStart = scaleStart;
		m_dScaleEnd = scaleEnd;
	}

	/**
	 * Sets the scale end.
	 * 
	 * @param scaleEnd
	 *            the end of the scale.
	 */
	public void setScaleEnd(double scaleEnd) {
		m_dScaleEnd = scaleEnd;
	}

	/**
	 * Sets the scale start.
	 * 
	 * @param scaleStart
	 *            the start of the scale.
	 */
	public void setScaleStart(double scaleStart) {
		m_dScaleStart = scaleStart;
	}

	/**
	 * Sets the current value.
	 * 
	 * @param value
	 *            a scale value.
	 */
	public void setValue(double value) {
		if (value > getScaleEnd()) {
			m_dValue = getScaleEnd();
			return;
		}

		if (value < getScaleStart()) {
			m_dValue = getScaleStart();
			return;
		}

		m_dValue = value;
	}

	/**
	 * Set the step length of the slider. Step length is used when the keys left and right are
	 * pressed.
	 * 
	 * @param length
	 *            the step length.
	 */
	public void setStepLength(double length) {
		m_dStepLength = length;
	}

	/**
	 * Get the step length of the slider.
	 * 
	 * @return the step length.
	 */
	public double getStepLength() {
		return m_dStepLength;
	}

	/**
	 * Get the orientation of the slider.
	 * 
	 * @return the orientation of the slider.
	 */
	public int getOrientation() {
		return m_nOrientation;
	}
}
