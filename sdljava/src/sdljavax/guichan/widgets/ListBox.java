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
 * This is a ListBox. It is a box with objects that can be selected. Only one object can be
 * selected. ListBox uses a ListModel to handle the list. To be able to use ListBox you must give
 * ListModel an implemented ListModel which represents your list.
 * 
 * @see sdljavax.guichan.widgets.ListModel
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class ListBox extends Widget implements MouseListener, KeyListener {
	protected ListModel	m_listModel;
	protected int		m_nSelected;

	/**
	 * Constructor.
	 * @throws GUIException 
	 */
	public ListBox() throws GUIException {
		super();
		m_nSelected = -1;
		m_listModel = null;
		setWidth(100);
		setFocusable(true);

		addMouseListener(this);
		addKeyListener(this);
	}

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            the ListModel to be used.
	 * @throws GUIException 
	 * @see ListModel
	 */
	public ListBox(ListModel model) throws GUIException {
		this();
		setListModel(model);
	}

	/**
	 * Adjusts the size of the listbox to fit the font used.
	 */
	protected void adjustSize() {
		if (null != m_listModel) {
			setHeight(getFont().getHeight() * m_listModel.getNumberOfElements());
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		graphics.setColor(getBackgroundColor());
		graphics.fillRectangle(new Rectangle(0, 0, getWidth(), getHeight()));

		if (null == m_listModel) {
			return;
		}

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());

		int fontHeight;

		fontHeight = getFont().getHeight();

		/**
		 * TODO Check cliprects so we do not have to iterate over elements in the list model
		 */
		int y = 0;

		for (int i = 0; i < m_listModel.getNumberOfElements(); ++i) {
			if (i == m_nSelected) {
				graphics.drawRectangle(new Rectangle(0, y, getWidth(), fontHeight));
			}

			graphics.drawText(m_listModel.getElementAt(i), 1, y);
			y += fontHeight;
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

	/**
	 * @return the ListModel used.
	 * @see ListModel
	 */
	public ListModel getListModel() {
		return m_listModel;
	}

	/**
	 * @return the selected element.
	 */
	public int getSelected() {
		return m_nSelected;
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (key.getValue() == Key.ENTER || key.getValue() == Key.SPACE) {
			generateAction();
		} else if (key.getValue() == Key.UP) {
			setSelected(m_nSelected - 1);

			if (m_nSelected == -1) {
				setSelected(0);
			}
		} else if (key.getValue() == Key.DOWN) {
			setSelected(m_nSelected + 1);
		}
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.widgets.Widget#logic()
	 */
	public void logic() {
		adjustSize();
	}

	/* (non-Javadoc)
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (button == MouseInput.LEFT && hasMouse()) {
			setSelected(y / getFont().getHeight());
			generateAction();
		}
	}

	/**
	 * Sets the ListModel to be used.
	 * 
	 * @param listModel
	 *            the ListModel to be used.
	 * @see ListModel
	 */
	public void setListModel(ListModel listModel) {
		m_nSelected = -1;
		m_listModel = listModel;
		adjustSize();
	}

	/**
	 * Set the selected element.
	 * 
	 * @param selected
	 *            the number of the element to be selected.
	 */
	public void setSelected(int selected) throws GUIException {
		if (null == m_listModel) {
			m_nSelected = -1;
		} else {
			if (selected < 0) {
				m_nSelected = -1;
			} else if (selected >= m_listModel.getNumberOfElements()) {
				m_nSelected = m_listModel.getNumberOfElements() - 1;
			} else {
				m_nSelected = selected;
			}

			Widget par = getParent();
			if (null == par) {
				return;
			}

			if (par instanceof ScrollArea) {
				ScrollArea scrollArea = (ScrollArea) par;
				if (null != scrollArea) {
					Rectangle scroll = new Rectangle();
					scroll.y = getFont().getHeight() * m_nSelected;
					scroll.height = getFont().getHeight();
					scrollArea.scrollToRectangle(scroll);
				}
			}
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
