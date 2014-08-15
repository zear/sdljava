/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.fpsdemo;

import sdljava.SDLException;
import sdljava.mixer.MixChunk;
import sdljava.mixer.SDLMixer;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.font.Font;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.widgets.Button;

/**
 * @author Rainer Koschnick <a href="mailto:rainer.koschnick@ebuconnect.de"><rainer.koschnick@ebuconnect.de></a>
 * 
 */
public class FPSButton extends Button {

	private static int		mInstances	= 0;
	private static MixChunk	mHoverSound	= null;
	private Font			mHighLightFont;

	/**
	 * @param strCaption
	 * @throws GUIException
	 */
	public FPSButton(String strCaption) throws GUIException {
		super(strCaption);
		if (mInstances == 0) {
			try {
				mHoverSound = SDLMixer.loadWAV(FPSDemo.dataDir + "sound5.wav");
				SDLMixer.volumeChunk(mHoverSound, 60);
			} catch (SDLException e) {
				throw new GUIException("Unable to handle sound", e);
			}
		}
		setBorderSize(0);
		++mInstances;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Button#draw(de.arkay.sdljava.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		if (hasMouse()) {
			graphics.setFont(mHighLightFont);
			graphics.drawText(getCaption(), 0, 0);
		} else {
			graphics.setFont(getFont());
			graphics.drawText(getCaption(), 0, 0);
		}
	}

	/**
	 * @param highLightFont
	 *            The highLightFont to set.
	 */
	public void setHighLightFont(Font highLightFont) {
		mHighLightFont = highLightFont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Widget#delete()
	 */
	public void delete() throws GUIException {
		--mInstances;

		if (mInstances == 0) {
			try {
				SDLMixer.freeChunk(mHoverSound);
			} catch (SDLException e) {
				throw new GUIException(e);
			} finally {
				super.delete();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.widgets.Button#mouseIn()
	 */
	public void mouseIn() throws GUIException {
		try {
			SDLMixer.playChannel(-1, mHoverSound, 0);
		} catch (SDLException e) {
			throw new GUIException("Unable to play sound", e);
		}
	}

}
