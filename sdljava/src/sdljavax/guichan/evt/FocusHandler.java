/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.evt;

import java.util.ArrayList;
import java.util.List;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.widgets.Widget;

/**
 * Used to keep track of widget focus. You will probably not have to use the FocusHandler directly
 * to handle focus. Widget has functions for handling focus which uses a FocusHandler. Use them
 * instead.
 * 
 * @see sdljavax.guichan.widgets.Widget#hasFocus()
 * @see sdljavax.guichan.widgets.Widget#requestFocus()
 * @see sdljavax.guichan.widgets.Widget#setFocusable(boolean)
 * @see sdljavax.guichan.widgets.Widget#isFocusable()
 * @see sdljavax.guichan.widgets.Widget#gotFocus()
 * @see sdljavax.guichan.widgets.Widget#lostFocus()
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class FocusHandler {
	private Widget		m_draggedWidget			= null;
	private Widget		m_focusedWidget			= null;
	private final List	m_listWidgets			= new ArrayList();
	private Widget		m_modalFocusedWidget	= null;
	private Widget		m_toBeDragged			= null;
	private Widget		m_toBeFocused			= null;

	/**
	 * Constructor.
	 */
	public FocusHandler() {
		super();
	}

	/**
	 * Adds a widget to the FocusHandler.
	 * 
	 * @param widget
	 *            the widget to add.
	 */
	public void add(Widget widget) {
		m_listWidgets.add(widget);
	}

	/**
	 * Applies the changes.
	 */
	public void applyChanges() throws GUIException {
		if (null != m_toBeFocused) {
			int toBeFocusedIndex = m_listWidgets.indexOf(m_toBeFocused);

			if (toBeFocusedIndex < 0) {
				throw new GUIException("Trying to focus a non-existing widget.");
			}

			Widget oldFocused = m_focusedWidget;

			if (oldFocused != m_toBeFocused) {
				m_focusedWidget = getWidgetAt(toBeFocusedIndex);
				if (null != oldFocused) {
					oldFocused.lostFocus();
				}
				m_focusedWidget.gotFocus();
			}

			m_toBeFocused = null;
		}

		if (null != m_toBeDragged) {
			int toBeDraggedIndex = m_listWidgets.indexOf(m_toBeDragged);

			if (toBeDraggedIndex < 0) {
				throw new GUIException("Trying to give drag to a non-existing widget.");
			}

			m_draggedWidget = getWidgetAt(toBeDraggedIndex);
			m_toBeDragged = null;
		}
	}

	/**
	 * Drag nothing.
	 */
	public void dragNone() {
		m_draggedWidget = null;
	}

	/**
	 * Focuses the next widget. If no widget has focus the first widget is focused. The order that
	 * the widgets are focused depends on the order that you add them to the GUI.
	 */
	public void focusNext() throws GUIException {
		int focusedWidget = m_listWidgets.indexOf(m_focusedWidget);

		int focused = focusedWidget;

		// i is a counter that ensures that the following loop
		// won't get stuck in an infinite loop
		int i = m_listWidgets.size();
		do {
			++focusedWidget;

			if (0 == i) {
				focusedWidget = -1;
				break;
			}

			--i;

			if (focusedWidget >= m_listWidgets.size()) {
				focusedWidget = 0;
			}

			if (focusedWidget == focused) {
				return;
			}
		} while (false == getWidgetAt(focusedWidget).isFocusable());

		if (focusedWidget >= 0) {
			m_focusedWidget = getWidgetAt(focusedWidget);
			m_focusedWidget.gotFocus();
		}

		if (focused >= 0) {
			getWidgetAt(focused).lostFocus();
		}
	}

	/**
	 * Focuses nothing.
	 */
	public void focusNone() throws GUIException {
		if (null != m_focusedWidget) {
			m_focusedWidget.lostFocus();
			m_focusedWidget = null;
		}

		m_toBeFocused = null;
	}

	/**
	 * Focuses the previous widget. If no widget has focus the first widget is focused. The order
	 * that the widgets are focused depends on the order that you add them to the GUI.
	 */
	public void focusPrevious() throws GUIException {
		if (m_listWidgets.isEmpty()) {
			m_focusedWidget = null;
			return;
		}

		int focusedWidget = m_listWidgets.indexOf(m_focusedWidget);
		int focused = focusedWidget;

		// i is a counter that ensures that the following loop
		// won't get stuck in an infinite loop
		int i = m_listWidgets.size();
		do {
			--focusedWidget;

			if (0 == i) {
				focusedWidget = -1;
				break;
			}

			--i;

			if (focusedWidget <= 0) {
				focusedWidget = m_listWidgets.size() - 1;
			}

			if (focusedWidget == focused) {
				return;
			}
		} while (false == getWidgetAt(focusedWidget).isFocusable());

		if (focusedWidget >= 0) {
			m_focusedWidget = getWidgetAt(focusedWidget);
			m_focusedWidget.gotFocus();
		}

		if (focused >= 0) {
			getWidgetAt(focused).lostFocus();
		}
	}

	/**
	 * Gets the widget that is dragged.
	 * 
	 * @return the dragged widget.
	 */
	public Widget getDragged() {
		return m_draggedWidget;
	}

	/**
	 * Gets the focused widget.
	 * 
	 * @return the focused widget.
	 */
	public Widget getFocused() {
		return m_focusedWidget;
	}

	/**
	 * Gets the Widget with modal focus.
	 * 
	 * @return the Widget with modal focus. null will be returned if no Widget has modal focus.
	 */
	public Object getModalFocused() {
		return m_modalFocusedWidget;
	}

	/**
	 * Gets the Widget with modal focus.
	 * 
	 * @return the Widget with modal focus. null will be returned if no Widget has modal focus.
	 */
	public Object getModalFocusedWidget() {
		return m_modalFocusedWidget;
	}

	/**
	 * Convenient casting method ;)
	 */
	protected Widget getWidgetAt(int nIdx) {
		return (Widget) m_listWidgets.get(nIdx);
	}

	/**
	 * Checks if a widget currently has focus.
	 * 
	 * @param widget
	 *            the widget to check.
	 * @return true if the widget has focus.
	 */
	public boolean hasFocus(Widget widget) {
		return widget == m_focusedWidget;
	}

	/**
	 * Checks if a widget is dragged
	 * 
	 * @param widget
	 *            a pointer to the widget to check.
	 * @return true if the widget is dragged.
	 */
	public boolean isDragged(Widget widget) {
		return m_draggedWidget == widget;
	}

	/**
	 * Releases modal focus if the Widget has modal focus. Otherwise nothing will be done.
	 * 
	 * @param widget
	 *            the Widget to release modal focus for.
	 */
	public void releaseModalFocus(Widget widget) {
		if (widget == m_modalFocusedWidget) {
			m_modalFocusedWidget = null;
		}
	}

	/**
	 * Removes a widget from the FocusHandler.
	 * 
	 * @param widget
	 *            the widget to remove.
	 */
	public void remove(Widget widget) {
		if (widget == m_toBeFocused) {
			m_toBeFocused = null;
		}

		if (widget == m_toBeDragged) {
			m_toBeDragged = null;
		}

		if (hasFocus(widget)) {
			m_focusedWidget = null;
			m_toBeFocused = null;
		}

		m_listWidgets.remove(widget);
	}

	/**
	 * Sets a widget to be dragged.
	 * 
	 * @param widget
	 *            the widget to be dragged.
	 */
	public void requestDrag(Widget widget) {
		m_toBeDragged = widget;
	}

	/**
	 * Sets the focus to a certain widget. Widget::lostFocus and Widget::gotFocus will be called as
	 * necessary.
	 * 
	 * @param widget
	 *            the widget to be focused.
	 */
	public void requestFocus(Widget widget) {
		m_toBeFocused = widget;
	}

	/**
	 * Sets modal focus to a Widget. If another Widget already has modal focus will an exception be
	 * thrown.
	 * 
	 * @param widget
	 *            the Widget to focus modal.
	 * @throws GUIException
	 *             when another widget already has modal focus.
	 */
	public void requestModalFocus(Widget widget) throws GUIException {
		if (null != m_modalFocusedWidget && m_modalFocusedWidget != widget) {
			throw new GUIException("Another widget already has modal focus.");
		}

		m_modalFocusedWidget = widget;

		if (null != m_focusedWidget && false == m_focusedWidget.hasModalFocus()) {
			focusNone();
		}

		if (null != m_draggedWidget && false == m_draggedWidget.hasModalFocus()) {
			dragNone();
		}
	}

	/**
	 * Focus the next Widget which allows tab in unless current focused Widget disallows tab out.
	 */
	public void tabNext() throws GUIException {
		if (null != m_focusedWidget) {
			if (false == m_focusedWidget.isTabOutEnabled()) {
				return;
			}
		}

		if (m_listWidgets.isEmpty()) {
			m_focusedWidget = null;
			return;
		}

		int focusedWidget = m_listWidgets.indexOf(m_focusedWidget);
		int focused = focusedWidget;

		// i is a counter that ensures that the following loop
		// won't get stuck in an infinite loop
		int i = m_listWidgets.size();
		Widget widget;
		boolean bDone = false;
		do {
			++focusedWidget;

			if (0 == i) {
				focusedWidget = -1;
				break;
			}

			--i;

			if (focusedWidget >= m_listWidgets.size()) {
				focusedWidget = 0;
			}

			if (focusedWidget == focused) {
				return;
			}

			widget = getWidgetAt(focusedWidget);

			if (widget.isFocusable()
				&& widget.isTabInEnabled()
				&& (null == m_modalFocusedWidget || widget.hasModalFocus())) {
				bDone = true;
			}

		} while (false == bDone);

		if (focusedWidget >= 0) {
			m_focusedWidget = getWidgetAt(focusedWidget);
			m_focusedWidget.gotFocus();
		}

		if (focused >= 0) {
			getWidgetAt(focused).lostFocus();
		}
	}

	/**
	 * Focus the previous Widget which allows tab in unless current focused Widget disallows tab
	 * out.
	 */
	public void tabPrevious() throws GUIException {
		if (null != m_focusedWidget) {
			if (false == m_focusedWidget.isTabOutEnabled()) {
				return;
			}
		}

		if (m_listWidgets.isEmpty()) {
			m_focusedWidget = null;
			return;
		}

		int focusedWidget = m_listWidgets.indexOf(m_focusedWidget);
		int focused = focusedWidget;

		// i is a counter that ensures that the following loop
		// won't get stuck in an infinite loop
		int i = m_listWidgets.size();
		boolean bDone = false;
		Widget widget;
		do {
			--focusedWidget;

			if (0 == i) {
				focusedWidget = -1;
				break;
			}

			--i;

			if (focusedWidget <= 0) {
				focusedWidget = m_listWidgets.size() - 1;
			}

			if (focusedWidget == focused) {
				return;
			}

			widget = getWidgetAt(focusedWidget);

			if (widget.isFocusable()
				&& widget.isTabInEnabled()
				&& (null == m_modalFocusedWidget || widget.hasModalFocus())) {
				bDone = true;
			}

		} while (false == bDone);

		if (focusedWidget >= 0) {
			m_focusedWidget = getWidgetAt(focusedWidget);
			m_focusedWidget.gotFocus();
		}

		if (focused >= 0) {
			getWidgetAt(focused).lostFocus();
		}
	}
}
