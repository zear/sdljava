/*
 * Created on Mar 5, 2005
 *
 * (c)2005 RKSoft, Meerbusch, Germany
 * All right reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Dimension;

import sdljavax.guichan.GUIException;


/**
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 *
 */
public abstract class BasicContainer extends Widget {

	/**
	 * This function is automatically called by the containers children
	 * when they get destroyed.
	 *
	 * @param widget the destroyed widget
	 */
	abstract protected void announceDeath(Widget widget) throws GUIException;

	/**
	 * Move a widget to the top of the basic container. The effect
	 * of this function is that the widget will be drawn above all
	 * other widgets in the basic container.
	 *
	 * @param widget the widget to move.
	 */
	public abstract void moveToTop(Widget widget) throws GUIException;

	/**
	 * Move a widget to the bottom of the basic container. The effect
	 * of this function is that the widget will be drawn below all
	 * other widgets in the basic container.
	 *
	 * @param widget the widget to move.
	 */
	public abstract void moveToBottom(Widget widget) throws GUIException;

	/**
	 * Used to check how much space a widget gets to draw itself which is
	 * not necessarily the same as the widgets width and height.
	 *
	 * NOTE: This function does not check the size recursively all the way
	 *       back to the top widget. If the container itself is clipped,
	 *       the size may be inaccurate.
	 * 
	 * @param widget a widget calling the function
	 * @return Dimension the drawing Dimensions of the Widget
	 */
	public abstract Dimension getDrawSize(Widget widget) throws GUIException;
}
