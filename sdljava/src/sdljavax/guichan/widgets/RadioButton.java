/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;


/**
 * This is a simple RadioButton. A RadioButton can belong to a group. If a RadioButton belongs to a
 * group, only one RadioButton in the group can be selected.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class RadioButton extends Widget implements MouseListener, KeyListener {
	protected static HashMap	m_mapGroup	= new HashMap();
	protected boolean			m_bMarked;
	protected String			m_strCaption;
	protected String			m_strGroup;

	/**
	 * Constructor.
	 */
	public RadioButton() throws GUIException {
		this(null, null, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param caption
	 *            the radiobuttons caption.
	 * @param group
	 *            the group the RadioButton belongs to.
	 */
	public RadioButton(String caption, String group) throws GUIException {
		this(caption, group, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param caption
	 *            the radiobuttons caption.
	 * @param group
	 *            the group the RadioButton belongs to.
	 * @param marked
	 *            true if the RadioButton should be marked.
	 */
	public RadioButton(String caption, String group, boolean marked) throws GUIException {
		setCaption(caption);
		setGroup(group);
		setMarked(marked);
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		adjustSize();
	}

	/**
	 * Adjusts the RadioButtons size to fit the font size.
	 */
	public void adjustSize() {
		int height = getFont().getHeight();

		setHeight(height);
		setWidth(getFont().getWidth(getCaption()) + height + height / 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#delete()
	 */
	public void delete() {
		setGroup("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		drawBox(graphics);

		graphics.setFont(getFont());
		graphics.setColor(getForegroundColor());

		int h = getHeight() + getHeight() / 2;

		graphics.drawText(getCaption(), h - 2, 0);

		if (hasFocus()) {
			graphics.drawRectangle(new Rectangle(h - 4, 0, getWidth() - h + 3, getHeight()));
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
			graphics.drawLine(i, i, i, height - i);
			graphics.setColor(highlightColor);
			graphics.drawLine(width - i, i, width - i, height - i);
			graphics.drawLine(i, height - i, width - i, height - i);
		}
	}

	/**
	 * Draws the box i.a not the caption.
	 * 
	 * @param graphics
	 *            a Graphics object.
	 */
	protected void drawBox(Graphics graphics) throws GUIException {
		int h;

		if (getHeight() % 2 == 0) {
			h = getHeight() - 2;
		} else {
			h = getHeight() - 1;
		}

		int alpha = getBaseColor().a;
		Color faceColor = getBaseColor();
		faceColor.a = alpha;
		Color highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		Color shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(getBackgroundColor());

		int hh = (h + 1) / 2;

		for (int i = 1; i <= hh; ++i) {
			graphics.drawLine(hh - i + 1, i, hh + i - 1, i);
		}

		for (int i = 1; i < hh; ++i) {
			graphics.drawLine(hh - i + 1, h - i, hh + i - 1, h - i);
		}

		graphics.setColor(shadowColor);
		graphics.drawLine(hh, 0, 0, hh);
		graphics.drawLine(hh + 1, 1, h - 1, hh - 1);

		graphics.setColor(highlightColor);
		graphics.drawLine(1, hh + 1, hh, h);
		graphics.drawLine(hh + 1, h - 1, h, hh);

		graphics.setColor(getForegroundColor());

		int hhh = hh - 3;
		if (isMarked()) {
			for (int i = 0; i < hhh; ++i) {
				graphics.drawLine(hh - i, 4 + i, hh + i, 4 + i);
			}
			for (int i = 0; i < hhh; ++i) {
				graphics.drawLine(hh - i, h - 4 - i, hh + i, h - 4 - i);
			}
		}
	}

	/**
	 * @return the caption of the RadioButton.
	 */
	public String getCaption() {
		return m_strCaption;
	}

	/**
	 * @return the group the RadioButton belongs to.
	 */
	public String getGroup() {
		return m_strGroup;
	}

	/**
	 * @return true if the RadioButton is marked.
	 */
	public boolean isMarked() {
		return m_bMarked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (key.getValue() == Key.ENTER || key.getValue() == Key.SPACE) {
			setMarked(true);
			generateAction();
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
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {
		if (button == MouseInput.LEFT) {
			setMarked(true);
			generateAction();
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
	public void mousePress(int x, int y, int button) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {}

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
	 * Sets the caption of the RadioButton.
	 * 
	 * @param caption
	 *            the RadioButton caption.
	 */
	public void setCaption(String caption) {
		m_strCaption = caption;
	}

	/**
	 * Set the group the RadioButton should belong to.
	 * 
	 * @param group
	 *            the name of the group.
	 */
	public void setGroup(String group) {
		if (null != m_strGroup && m_strGroup.length() > 0) {
			Vector vecGroup = (Vector) m_mapGroup.get(group);
			if (null != vecGroup) {
				for (Iterator it = vecGroup.iterator(); it.hasNext();) {
					RadioButton theButton = (RadioButton) it.next();
					if (this == theButton) {
						vecGroup.remove(this);
						break;
					}
				}
			}
		}

		// TODO Validate groups, check if empty

		if (group.length() > 0) {
			Vector vecGroup = (Vector) m_mapGroup.get(group);
			if (null != vecGroup) {
				vecGroup.add(this);
			} else {
				vecGroup = new Vector();
				vecGroup.add(this);
				m_mapGroup.put(group, vecGroup);
			}
		}

		m_strGroup = group;
	}

	/**
	 * Set the RadioButton marked.
	 * 
	 * @param marked
	 *            true if the RadioButton should be marked.
	 */
	public void setMarked(boolean marked) throws GUIException {
		if (marked && m_strGroup.length() > 0) {
			Vector vecGroup = (Vector) m_mapGroup.get(m_strGroup);
			if (null != vecGroup) {
				for (Iterator it = vecGroup.iterator(); it.hasNext();) {
					RadioButton theButton = (RadioButton) it.next();
					if (theButton.isMarked()) {
						theButton.setMarked(false);
					}
				}
			} else {
				throw new GUIException("Group for button not found!");
			}
		}

		m_bMarked = marked;
	}
}
