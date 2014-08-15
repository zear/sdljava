/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

/**
 * This is a ListModel. It is used in certain Widgets like the ListBox to handle a list wit string
 * elements. If you want to use widgets like ListBox you should implement your own version of a
 * ListModel since this is an abstract class.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface ListModel {
	/**
	 * @return the number of elements in the ListModel
	 */
	public int getNumberOfElements();

	/**
	 * Get an element at a certain index in the list.
	 * 
	 * @param i
	 *            an index in the list.
	 * @return a element as a string
	 */
	public String getElementAt(int i);
}
