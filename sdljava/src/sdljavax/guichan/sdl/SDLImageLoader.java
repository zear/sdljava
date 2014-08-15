/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.sdl;

import java.nio.IntBuffer;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljavax.gfx.SDLGfx;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.ImageLoader;

/**
 * For comments regarding functions please see the interface file.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class SDLImageLoader implements ImageLoader {
	private static int	amask;
	private static int	bmask;
	private static int	gmask;
	private static int	rmask;

	static {
		if (SDLUtils.SDL_BYTEORDER == SDLUtils.SDL_BIG_ENDIAN) {
			rmask = 0xff000000;
			gmask = 0x00ff0000;
			bmask = 0x0000ff00;
			amask = 0x000000ff;
		} else {
			rmask = 0x000000ff;
			gmask = 0x0000ff00;
			bmask = 0x00ff0000;
			amask = 0xff000000;
		}
	}

	private SDLSurface	m_currentImage;

	/**
	 * Constructor
	 */
	public SDLImageLoader() {
		m_currentImage = null;
	}

	/**
	 * Frees the current image (if one is set)
	 */
	public void delete() {
		if (null != m_currentImage) {
			try {
				m_currentImage.freeSurface();
			} catch (SDLException e) {
			}
			m_currentImage = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#discard()
	 */
	public void discard() throws GUIException {
		if (null == m_currentImage) {
			throw new GUIException("No image prepared.");
		}

		try {
			m_currentImage.freeSurface();
			m_currentImage = null;
		} catch (SDLException e) {
			throw new GUIException("Unable to free image.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#finish()
	 */
	public Object finish() throws GUIException {
		if (null == m_currentImage) {
			throw new GUIException("No image prepared.");
		}
		try {
			long lColorTransp = m_currentImage.mapRGB(255, 0, 255);
			IntBuffer pixelData = m_currentImage.getPixelData().asIntBuffer();
			for (int i = 0; i < pixelData.capacity(); i++) {
				if (SDLUtils.equalBigAndSmallEndian(pixelData.get(), (int) lColorTransp)) {
					m_currentImage.setColorKey(SDLVideo.SDL_SRCCOLORKEY, lColorTransp);
					break;
				}
			}
			SDLSurface temp = m_currentImage.displayFormat();
			m_currentImage.freeSurface();
			m_currentImage = null;
			return temp;
		} catch (SDLException e) {
			throw new GUIException("Unable to free image.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#free(sdljavax.guichan.gfx.Image)
	 */
	public void free(Image image) throws GUIException {
		if (null == image.getData()) {
			throw new GUIException("Image data points to null.");
		}
		try {
			((SDLSurface) image.getData()).freeSurface();
		} catch (SDLException e) {
			throw new GUIException("Unable to free surface", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getHeight()
	 */
	public int getHeight() throws GUIException {
		if (null == m_currentImage) {
			throw new GUIException("No image prepared.");
		}

		return m_currentImage.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getPixel(int, int)
	 */
	public Color getPixel(int x, int y) throws GUIException {
		if (null == m_currentImage) {
			throw new GUIException("No image prepared.");
		}

		if (x < 0 || y < 0 || x >= m_currentImage.getWidth() || y >= m_currentImage.getHeight()) {
			throw new GUIException("x and y out of image bound.");
		}

		return SDLUtils.sdlGetPixel(m_currentImage, x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getRawData()
	 */
	public Object getRawData() throws GUIException {
		return m_currentImage.getPixelData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getWidth()
	 */
	public int getWidth() throws GUIException {
		if (null == m_currentImage) {
			throw new GUIException("No image prepared.");
		}

		return m_currentImage.getWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#prepare(java.lang.String)
	 */
	public void prepare(String strFileName) throws GUIException {
		if (null != m_currentImage) {
			throw new GUIException(
				"Function called before finalizing or discarding last loaded image.");
		}

		try {
			SDLSurface tmp = SDLImage.load(strFileName);

			if (null == tmp) {
				throw new GUIException("Unable to load image file: " + strFileName);
			}

			m_currentImage = SDLVideo.createRGBSurface(SDLVideo.SDL_SWSURFACE, 0, 0, 32, rmask, gmask, bmask, amask);

			if (null == m_currentImage) {
				throw new GUIException("Not enough memory to load: " + strFileName);
			}

			SDLSurface tmp2 = tmp.convertSurface(m_currentImage.getFormat(), SDLVideo.SDL_SWSURFACE);

			tmp.freeSurface();
			m_currentImage.freeSurface();

			m_currentImage = tmp2;
		} catch (SDLException e) {
			throw new GUIException("Unable to load image file: " + strFileName, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#putPixel(int, int, sdljavax.guichan.gfx.Color)
	 */
	public void putPixel(int x, int y, Color color) throws GUIException {
		SDLGfx.pixelRGBA(m_currentImage, x, y, color.r, color.g, color.b, 0);
	}
}
