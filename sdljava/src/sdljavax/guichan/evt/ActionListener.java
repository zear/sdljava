/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.evt;

import sdljavax.guichan.GUIException;

/**
 * Listener of action events from Widgets. To be able to listen for actions you must create a class
 * which implements this class and its action function.
 * 
 * @see sdljavax.guichan.widgets.Widget#addActionListener(ActionListener)
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface ActionListener {

	/**
	 * This function is called upon an action recieved from a widget.
	 * 
	 * @param strEventId
	 *            the identifier of the widget.
	 */
	void action(String strEventId) throws GUIException;
}
