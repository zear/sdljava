/*
 * Created on Mar 6, 2005
 *
 * (c)2005 RKSoft, Meerbusch, Germany
 * All right reserved.
 */

package sdljavax.guichan.test.ffdemo;

import sdljava.SDLTimer;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.widgets.ListBox;

public class FFListBox extends ListBox {
	private static Image	mHand		= null;
	private static int		mInstances	= 0;

	public FFListBox() throws GUIException {
		super();
		if (0 == mInstances) {
			mHand = new Image(FFDemo.dataDir + "hand.png");
		}
		mInstances++;
		setBorderSize(0);
	}

	public void delete() throws GUIException {
		mInstances--;
		if (mInstances == 0) {
			mHand.delete();
		}
		super.delete();
	}

	public void draw(Graphics graphics) throws GUIException {
		if (null == m_listModel) {
			return;
		}

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());

		int i, fontHeight;
		int y = 0;

		fontHeight = getFont().getHeight();

		/**
		 * TODO Check cliprects so we do not have to iterate over elements in the list model
		 */
		for (i = 0; i < m_listModel.getNumberOfElements(); ++i) {
			graphics.drawText(m_listModel.getElementAt(i), 16, y);

			if (i == m_nSelected) {
				if (hasFocus()) {
					graphics.drawImage(mHand, 0, y);
				} else if (0 != ((SDLTimer.getTicks() / 100) & 1)) {
					graphics.drawImage(mHand, 0, y);
				}
			}

			y += fontHeight;
		}
	}

	public void setSelected(int selected) throws GUIException {
		if (selected >= 0
			&& selected < getListModel().getNumberOfElements()
			&& 0 == getListModel().getElementAt(selected).length()) {
			if (selected < getSelected()) {
				selected--;
			} else {
				selected++;
			}
		}
		super.setSelected(selected);
	}
}
