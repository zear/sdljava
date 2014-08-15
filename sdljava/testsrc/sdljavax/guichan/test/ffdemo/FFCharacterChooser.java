/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.ffdemo;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.widgets.Widget;

public class FFCharacterChooser extends Widget implements KeyListener {
	private int		mSelected;
	private int		mDistance;
	private Image	mHand;

	public FFCharacterChooser() throws GUIException {
		super();
		setWidth(20);
		setHeight(240);
		mSelected = 0;
		mDistance = 76;
		mHand = new Image("data/ffdemo/images/hand.png");
		setFocusable(true);
		addKeyListener(this);
	}

	public void delete() throws GUIException {
		mHand.delete();
		super.delete();
	}

	public void draw(Graphics graphics) throws GUIException {
		if (hasFocus()) {
			graphics.drawImage(mHand, 0, mDistance * mSelected);
		}
	}

	public void drawBorder(Graphics graphics) {}

	public int getSelected() {
		return mSelected;
	}

	public void setSelected(int selected) {
		mSelected = selected;
	}

	public void setDistance(int distance) {
		mDistance = distance;
	}

	public void keyPress(Key key) throws GUIException {
		if (key.getValue() == Key.UP) {
			mSelected--;
			if (mSelected < 0) {
				mSelected = 0;
			}
		} else if (key.getValue() == Key.DOWN) {
			mSelected++;
			if (mSelected > 2) {
				mSelected = 2;
			}
		} else if (key.getValue() == Key.ENTER) {
			generateAction();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.KeyListener#keyRelease(de.arkay.sdljava.evt.Key)
	 */
	public void keyRelease(Key key) throws GUIException {}
}
