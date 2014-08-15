/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Dimension;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.ActionListener;
import sdljavax.guichan.evt.FocusHandler;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyInput;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;


/**
 * This is a drop down box (you know, the ones you can drop down and select between different
 * values, like a list box). It is one of the most complicated widgets you will find in gui-chan.
 * For drawing the dropped down box it uses one ScrollArea and one ListBox. It also uses an internal
 * FocusHandler to handle the focus of the internal ScollArea and ListBox.
 * 
 * It uses a ListModel to look up it's values, just like the ListBox.
 * 
 * @see sdljavax.guichan.widgets.ListModel
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class DropDown extends BasicContainer implements MouseListener, KeyListener, ActionListener {
	private ListBox			m_defaultListBox;
	private ScrollArea		m_defaultScrollArea;

	private boolean			m_bDroppedDown;
	private FocusHandler	m_focusHandler	= new FocusHandler();
	private ListBox			m_listBox;
	private int				m_nOldH;
	private boolean			m_bPushed;
	private ScrollArea		m_scrollArea;

	/**
	 * Constructor.
	 */
	public DropDown() throws GUIException {
		this(null, null, null);
	}

	/**
	 * Contructor.
	 * 
	 * @param listModel
	 *            the ListModel to be used.
	 * @see ListModel
	 */
	public DropDown(ListModel listModel) throws GUIException {
		this(listModel, null, null);
	}

	/**
	 * Contructor.
	 * 
	 * @param listModel
	 *            the ListModel to be used.
	 * @param scrollArea
	 *            the ScrollArea to be used.
	 * @param listBox
	 *            the listBox to be used.
	 * @see ListModel
	 * @see ScrollArea
	 * @see ListBox
	 */
	public DropDown(ListModel listModel, ScrollArea scrollArea, ListBox listBox) throws GUIException {
		super();
		setWidth(100);
		setFocusable(true);
		m_bDroppedDown = false;
		m_bPushed = false;

		if (null != scrollArea) {
			m_defaultScrollArea = null;
			m_scrollArea = scrollArea;
			m_scrollArea.setFocusHandler(m_focusHandler);
		} else {
			m_defaultScrollArea = new ScrollArea();
			m_defaultScrollArea.setHorizontalScrollPolicy(ScrollArea.SHOW_NEVER);
			m_scrollArea = m_defaultScrollArea;
			m_scrollArea.setFocusHandler(m_focusHandler);
			m_scrollArea.setParent(this);
		}

		if (null != listBox) {
			m_defaultListBox = null;
			m_listBox = listBox;
			m_listBox.addActionListener(this);
			m_scrollArea.setContent(m_listBox);
			m_scrollArea.setParent(this);
		} else {
			m_defaultListBox = new ListBox();
			m_listBox = m_defaultListBox;
			m_listBox.addActionListener(this);
			m_scrollArea.setContent(m_listBox);
		}

		if (null != listModel) {
			setListModel(listModel);
			if (m_listBox.getSelected() < 0) {
				m_listBox.setSelected(0);
			}
		}

		addMouseListener(this);
		addKeyListener(this);
		adjustHeight();
		setBorderSize(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.ActionListener#action(java.lang.String)
	 */
	public void action(String strEventId) throws GUIException {
		foldUp();
		generateAction();
	}

	/**
	 * Adjusts the height of the DropDown fitting its parents height.
	 */
	protected void adjustHeight() throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		int listBoxHeight = m_listBox.getHeight();
		int h2 = getFont().getHeight();

		setHeight(h2);

		// The addition/subtraction of 2 compensates for the seperation lines
		// seperating the selected element view and the scroll area.

		if (m_bDroppedDown && null != getParent()) {
			int h = getParent().getHeight() - getY();

			if (listBoxHeight > h - h2 - 2) {
				m_scrollArea.setHeight(h - h2 - 2);
				setHeight(h);
			} else {
				setHeight(listBoxHeight + h2 + 2);
				m_scrollArea.setHeight(listBoxHeight);
			}
		}

		m_scrollArea.setWidth(getWidth());
		m_scrollArea.setPosition(0, h2 + 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.BasicContainer#announceDeath(sdljavax.guichan.widgets.Widget)
	 */
	protected void announceDeath(Widget widget) throws GUIException {
		if (widget == m_scrollArea) {
			m_scrollArea = null;
		} else {
			throw new GUIException("Death announced for unknown widget.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#delete()
	 */
	public void delete() throws GUIException {
		if (null != m_scrollArea) {
			m_scrollArea.setFocusHandler(null);
		}

		if (null != m_defaultScrollArea) {
			m_defaultScrollArea.delete();
		}

		if (null != m_defaultListBox) {
			m_defaultListBox.delete();
		}

		if (widgetExists(m_listBox)) {
			m_listBox.removeActionListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		int h;

		if (m_bDroppedDown) {
			h = m_nOldH;
		} else {
			h = getHeight();
		}

		int alpha = getBaseColor().a;
		Color faceColor = getBaseColor();
		faceColor.a = alpha;
		Color highlightColor = faceColor.add(0x303030);
		highlightColor.a = alpha;
		Color shadowColor = faceColor.subtract(0x303030);
		shadowColor.a = alpha;

		graphics.setColor(getBackgroundColor());
		graphics.fillRectangle(new Rectangle(0, 0, getWidth(), h));

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());

		if (null != m_listBox.getListModel() && m_listBox.getSelected() >= 0) {
			graphics.drawText(m_listBox.getListModel().getElementAt(m_listBox.getSelected()), 1, 0);
		}

		if (hasFocus()) {
			graphics.drawRectangle(new Rectangle(0, 0, getWidth() - h, h));
		}

		drawButton(graphics);

		if (m_bDroppedDown) {
			graphics.pushClipArea(m_scrollArea.getDimension());
			m_scrollArea.draw(graphics);
			graphics.popClipArea();

			// Draw two lines separating the ListBox with se selected
			// element view.
			graphics.setColor(highlightColor);
			graphics.drawLine(0, h, getWidth(), h);
			graphics.setColor(shadowColor);
			graphics.drawLine(0, h + 1, getWidth(), h + 1);
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
	 * Draws the button with the little down arrow.
	 * 
	 * @param graphics
	 *            a Graphics object.
	 */
	protected void drawButton(Graphics graphics) throws GUIException {
		Color faceColor, highlightColor, shadowColor;
		int offset;
		int alpha = getBaseColor().a;

		if (m_bPushed) {
			faceColor = getBaseColor().subtract(0x303030);
			faceColor.a = alpha;
			highlightColor = faceColor.subtract(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.add(0x303030);
			shadowColor.a = alpha;
			offset = 1;
		} else {
			faceColor = getBaseColor();
			faceColor.a = alpha;
			highlightColor = faceColor.add(0x303030);
			highlightColor.a = alpha;
			shadowColor = faceColor.subtract(0x303030);
			shadowColor.a = alpha;
			offset = 0;
		}

		int h;
		if (m_bDroppedDown) {
			h = m_nOldH;
		} else {
			h = getHeight();
		}
		int x = getWidth() - h;
		int y = 0;

		graphics.setColor(faceColor);
		graphics.fillRectangle(new Rectangle(x + 1, y + 1, h - 2, h - 2));

		graphics.setColor(highlightColor);
		graphics.drawLine(x, y, x + h - 1, y);
		graphics.drawLine(x, y + 1, x, y + h - 1);

		graphics.setColor(shadowColor);
		graphics.drawLine(x + h - 1, y + 1, x + h - 1, y + h - 1);
		graphics.drawLine(x + 1, y + h - 1, x + h - 2, y + h - 1);

		graphics.setColor(getForegroundColor());

		int hh = h / 3;
		int hx = x + h / 2;
		int hy = y + (h * 2) / 3;
		for (int i = 0; i < hh; i++) {
			graphics.drawLine(hx - i + offset, hy - i + offset, hx + i + offset, hy - i + offset);
		}
	}

	/**
	 * Sets the DropDown widget to dropped-down mode.
	 */
	protected void dropDown() throws GUIException {
		if (!m_bDroppedDown) {
			m_bDroppedDown = true;
			m_nOldH = getHeight();
			adjustHeight();

			if (null != getParent()) {
				getParent().moveToTop(this);
			}
		}

		m_focusHandler.requestFocus(m_scrollArea.getContent());
	}

	/**
	 * Sets the DropDown widget to folded-up mode.
	 */
	protected void foldUp() throws GUIException {
		if (m_bDroppedDown) {
			m_bDroppedDown = false;
			m_focusHandler.focusNone();
			adjustHeight();
		}
	}

	public Dimension getDrawSize(Widget widget) throws GUIException {
		if (widget == m_scrollArea) {
			Dimension size = new Dimension();
			if (m_bDroppedDown) {
				size.height = getHeight() - m_nOldH;
				size.width = getWidth();
			} else {
				size.width = size.height = 0;
			}
			return size;
		}
		throw new GUIException("Widget is not the ScrollArea (weeeird...)");
	}

	/**
	 * @return the ListBox used.
	 * @see ListBox
	 */
	public ListBox getListBox() {
		return m_listBox;
	}

	/**
	 * @return the ListModel used.
	 * @see ListModel
	 */
	public ListModel getListModel() throws GUIException {
		if (m_scrollArea == null || m_scrollArea.getContent() == null) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		return m_listBox.getListModel();
	}

	/**
	 * @return the ScrollArea used.
	 * @see ScrollArea
	 */
	public ScrollArea getScrollArea() {
		return m_scrollArea;
	}

	/**
	 * @return the selected element.
	 */
	public int getSelected() throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		return m_listBox.getSelected();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#keyInputMessage(sdljavax.guichan.evt.KeyInput)
	 */
	public void keyInputMessage(KeyInput keyInput) throws GUIException {
		if (m_bDroppedDown) {
			if (null == m_scrollArea || null == m_scrollArea.getContent()) {
				throw new GUIException("ScrollArea or ListBox is NULL");
			}

			if (null != m_focusHandler.getFocused()) {
				m_focusHandler.getFocused().keyInputMessage(keyInput);
			}
		} else {
			super.keyInputMessage(keyInput);
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		if ((key.getValue() == Key.ENTER || key.getValue() == Key.SPACE) && !m_bDroppedDown) {
			dropDown();
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#logic()
	 */
	public void logic() throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		m_scrollArea.logic();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#lostFocus()
	 */
	public void lostFocus() throws GUIException {
		foldUp();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#mouseInputMessage(sdljavax.guichan.evt.MouseInput)
	 */
	public void mouseInputMessage(MouseInput mouseInput) throws GUIException {
		super.mouseInputMessage(mouseInput);

		if (m_bDroppedDown) {
			if (null == m_scrollArea || null == m_scrollArea.getContent()) {
				throw new GUIException("ScrollArea or ListBox is NULL");
			}

			if (mouseInput.y >= m_nOldH) {
				MouseInput mi = mouseInput;
				mi.y -= m_scrollArea.getY();
				m_scrollArea.mouseInputMessage(mi);

				if (m_listBox.hasFocus()) {
					mi.y -= m_listBox.getY();
					m_listBox.mouseInputMessage(mi);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (button == MouseInput.LEFT && hasMouse() && !m_bDroppedDown) {
			m_bPushed = true;
			dropDown();
		}
		// Fold up the listbox if the upper part is clicked after fold down
		else if (button == MouseInput.LEFT && hasMouse() && m_bDroppedDown && y < m_nOldH) {
			foldUp();
		} else if (!hasMouse()) {
			foldUp();
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) {
		if (button == MouseInput.LEFT) {
			m_bPushed = false;
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToBottom(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToBottom(Widget widget) throws GUIException {
		if (null != getParent()) {
			getParent().moveToBottom(this);
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.BasicContainer#moveToTop(sdljavax.guichan.widgets.Widget)
	 */
	public void moveToTop(Widget widget) throws GUIException {
		if (null != getParent()) {
			getParent().moveToTop(this);
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#setBackgroundColor(sdljavax.guichan.gfx.Color)
	 */
	public void setBackgroundColor(Color color) {
		if (m_defaultScrollArea == m_scrollArea && null != m_scrollArea) {
			m_scrollArea.setBackgroundColor(color);
		}

		if (m_defaultListBox == m_listBox && null != m_listBox) {
			m_listBox.setBackgroundColor(color);
		}

		super.setBackgroundColor(color);
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#setBaseColor(sdljavax.guichan.gfx.Color)
	 */
	public void setBaseColor(Color color) {
		if (m_defaultScrollArea == m_scrollArea && null != m_scrollArea) {
			m_scrollArea.setBaseColor(color);
		}

		if (m_defaultListBox == m_listBox && null != m_listBox) {
			m_listBox.setBaseColor(color);
		}

		super.setBaseColor(color);
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#setForegroundColor(sdljavax.guichan.gfx.Color)
	 */
	public void setForegroundColor(Color color) {
		if (m_defaultScrollArea == m_scrollArea && null != m_scrollArea) {
			m_scrollArea.setForegroundColor(color);
		}

		if (m_defaultListBox == m_listBox && null != m_listBox) {
			m_listBox.setForegroundColor(color);
		}

		super.setForegroundColor(color);
	}

	/**
	 * Sets the ListBox to be used.
	 * 
	 * @param listBox
	 *            the ListBox to be used.
	 * @see ListBox
	 */
	public void setListBox(ListBox listBox) throws GUIException {
		listBox.setSelected(m_listBox.getSelected());
		listBox.setListModel(m_listBox.getListModel());
		listBox.addActionListener(this);

		if (null != m_scrollArea.getContent()) {
			m_listBox.removeActionListener(this);
		}

		m_listBox = listBox;

		m_scrollArea.setContent(m_listBox);

		if (m_listBox.getSelected() < 0) {
			m_listBox.setSelected(0);
		}
	}

	/**
	 * Sets the ListModel to be used.
	 * 
	 * @param listModel
	 *            the ListModel to be used.
	 * @see ListModel
	 */
	public void setListModel(ListModel listModel) throws GUIException {
		if (m_scrollArea == null || m_scrollArea.getContent() == null) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		m_listBox.setListModel(listModel);

		if (m_listBox.getSelected() < 0) {
			m_listBox.setSelected(0);
		}

		adjustHeight();
	}

	/**
	 * Sets the ScrollArea to be used.
	 * 
	 * @param scrollArea
	 *            the ScrollArea to be used.
	 * @see ScrollArea
	 */
	public void setScrollArea(ScrollArea scrollArea) throws GUIException {
		m_scrollArea.setFocusHandler(null);
		m_scrollArea.setParent(null);
		m_scrollArea = scrollArea;
		m_scrollArea.setFocusHandler(m_focusHandler);
		m_scrollArea.setContent(m_listBox);
		m_scrollArea.setParent(this);
		adjustHeight();
	}

	/**
	 * Set the selected element.
	 * 
	 * @param selected
	 *            the index of the element to be selected.
	 */
	public void setSelected(int selected) throws GUIException {
		if (null == m_scrollArea || null == m_scrollArea.getContent()) {
			throw new GUIException("ScrollArea or ListBox is NULL");
		}

		if (selected >= 0) {
			m_listBox.setSelected(selected);
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
