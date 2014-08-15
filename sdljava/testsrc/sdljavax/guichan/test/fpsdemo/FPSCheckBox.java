/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.fpsdemo;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.widgets.CheckBox;

/**
 * @author Rainer Koschnick <a href="mailto:rainer.koschnick@ebuconnect.de"><rainer.koschnick@ebuconnect.de></a>
 * 
 */
public class FPSCheckBox extends CheckBox {

	/**
	 * @param strCaption
	 * @throws GUIException 
	 */
	public FPSCheckBox(String strCaption) throws GUIException {
		super(strCaption);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.CheckBox#draw(de.arkay.sdljava.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		graphics.setFont(getFont());
		graphics.setColor(getForegroundColor());

		// First draw the caption
		graphics.drawText(m_strCaption, 0, 0);

		// Calculate the x coordinate for the box
		int x = getFont().getWidth(m_strCaption) + getHeight() / 2;

		// Push a clip area where the box should draw itself
		graphics.pushClipArea(new Rectangle(x, 0, getWidth() - x, getHeight()));
		drawBox(graphics);
		// Pop the cliparea. VERY IMPORTANT!
		graphics.popClipArea();
	}
}
