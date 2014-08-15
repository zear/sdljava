/*
 * Created on Mar 9, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.openglwidgets;

import sdljavax.guichan.widgets.ListModel;

public class DemoListModel implements ListModel {

	public DemoListModel() {
		super();
	}

	public int getNumberOfElements() {
		return 5;
	}

	public String getElementAt(int nPos) {
		switch (nPos) {
			case 0 :
				return "zero";
			case 1 :
				return "one";
			case 2 :
				return "two";
			case 3 :
				return "three";
			case 4 :
				return "four";
			default : // Just to keep warnings away
				return "";
		}
	}
}
