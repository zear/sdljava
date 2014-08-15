/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.fpsdemo;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.widgets.Widget;

/**
 * @author Rainer Koschnick <a href="mailto:rainer.koschnick@ebuconnect.de"><rainer.koschnick@ebuconnect.de></a>
 * 
 */
public class FPSSlider extends Widget implements MouseListener {
	// The Markers x coordiante.
	private int		mMarkerPosition	= 0;
	// Keep track of the user is draging the marker or not.
	private boolean	mMouseDrag		= false;

	public FPSSlider() throws GUIException {
		super();
		// Add this widget to the MouseListener. We want Mouse input!
		addMouseListener(this);
		// The widget should be focusable
		setFocusable(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Widget#draw(de.arkay.sdljava.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		// Get the colors and fix the face, highlight and shadow color.
		Color faceColor = getBaseColor();
		Color shadowColor = faceColor.subtract(0x303030);

		graphics.setColor(faceColor);

		// Draw a line
		graphics.drawRectangle(new Rectangle(0, getHeight() / 2 - 1, getWidth(), 2));
		graphics.setColor(shadowColor);
		graphics.drawLine(0, getHeight() / 2 - 2, getWidth(), getHeight() / 2 - 2);
		graphics.drawLine(0, getHeight() / 2 + 1, getWidth(), getHeight() / 2 + 1);

		drawMarker(graphics);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Widget#drawBorder(de.arkay.sdljava.gfx.Graphics)
	 */
	public void drawBorder(Graphics graphics) throws GUIException {
	// TODO Auto-generated method stub

	}

	/**
	 * @param graphics
	 * @throws GUIException
	 */
	private void drawMarker(Graphics graphics) throws GUIException {
		// As with the line, fix the colors.
		Color faceColor = getBaseColor();
		Color highlightColor = faceColor.add(0x303030);
		Color shadowColor = faceColor.subtract(0x303030);

		// Get the Marker position
		int x = mMarkerPosition;

		graphics.setColor(faceColor);

		// Draw a square
		graphics.fillRectangle(new Rectangle(x + 1, 1, getHeight() - 1, getHeight() - 1));
		graphics.setColor(highlightColor);
		graphics.drawLine(x, 0, x + getHeight(), 0);
		graphics.drawLine(x + getHeight() - 1, 1, x + getHeight() - 1, getHeight() - 1);
		graphics.setColor(shadowColor);
		graphics.drawLine(x, 1, x, getHeight());
		graphics.drawLine(x, getHeight() - 1, x + getHeight() - 1, getHeight() - 1);
	}

	/**
	 * @return
	 */
	public double getPercentMarked() {
		double w = getWidth() - getHeight();
		return (int) ((mMarkerPosition * 100) / w);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Widget#lostFocus()
	 */
	public void lostFocus() throws GUIException {
		mMouseDrag = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {
		if (mMouseDrag) {
			// Set the marker positon
			setMarkerPosition(x - getHeight() / 2);
			// Tell the actionlisteners to this widget that this widgets state
			// has changed
			generateAction();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseOut()
	 */
	public void mouseOut() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		// Check for press in the widget.
		if (button == MouseInput.LEFT && x >= 0 && x <= getWidth()) {
			// Set Marker position
			setMarkerPosition(x - getHeight() / 2);

			// We have mouse drag (the user haven't release the mouse button yet!)
			mMouseDrag = true;
			// Tell the actionlisteners to this widget that this widgets state
			// has changed
			generateAction();
		} else {
			// The user clicked outside of the widget, definitaly no mouse drag.
			mMouseDrag = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {
		mMouseDrag = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {}

	/**
	 * @param position
	 */
	private void setMarkerPosition(int position) {
		if (position < 0) {
			mMarkerPosition = 0;
		} else if (position > getWidth() - getHeight()) {
			mMarkerPosition = getWidth() - getHeight();
		} else {
			mMarkerPosition = position;
		}
	}

	/**
	 * @param percent
	 */
	public void setPercentMarked(int percent) {
		double p = percent;
		double w = getWidth() - getHeight();
		mMarkerPosition = (int) (w * (p / 100));
	}
}
