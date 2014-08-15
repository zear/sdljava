/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.fpsdemo;

import sdljavax.guichan.widgets.ListModel;

/**
 * @author Rainer Koschnick <a href="mailto:rainer.koschnick@ebuconnect.de"><rainer.koschnick@ebuconnect.de></a>
 * 
 */
public class ResolutionListModel implements ListModel {

	public ResolutionListModel() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.ListModel#getNumberOfElements()
	 */
	public int getNumberOfElements() {
		return 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.ListModel#getElementAt(int)
	 */
	public String getElementAt(int nPos) {
		switch (nPos) {
			case 0 :
				return "1024x768";
			case 1 :
				return "800x600";
			default :
				return "";
		}
	}
}
