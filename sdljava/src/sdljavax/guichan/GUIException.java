/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan;

/**
 * Exception thrown by various GUI components
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */
public class GUIException extends Exception {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3257565118236864560L;

	/**
	 * Constructor for a message-only exception
	 */
	public GUIException(String strMessage) {
		super(strMessage);
	}

	/**
	 * Constructor for exception with a root exception and a message
	 * 
	 * @param strMessage
	 *            the cause of of the exception
	 * @param src
	 *            the parent Exception
	 */
	public GUIException(String strMessage, Exception src) {
		super(strMessage, src);
	}

	/**
	 * Constructor for a delegated exception
	 */
	public GUIException(Exception e) {
		super(e);
	}
}
