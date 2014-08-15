/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.ffdemo;

import java.util.Vector;

import sdljavax.guichan.widgets.ListModel;


public class StringListModel implements ListModel {

	private Vector	mStrings	= new Vector();

	public StringListModel() {
		super();
	}

	public int getNumberOfElements() {
		return mStrings.size();
	}

	public String getElementAt(int nPos) {
		return (String) mStrings.get(nPos);
	}

	public void add(String text) {
		mStrings.add(text);
	}

	public void delete() {}
}
