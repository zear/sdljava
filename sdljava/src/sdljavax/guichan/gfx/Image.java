/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

import sdljavax.guichan.GUIException;

/**
 * This is a class holding images in SDLJavaUI. To be able to use this class you must first set the
 * ImageLoader in Image by calling Image::setImageLoader(myImageLoader) (it is static). If this is
 * not done, the constructor taking a filename will through an exception. The ImageLoader you use
 * must be compatible with your Graphics obejct.
 * 
 * Ex: If you use SDLGraphics you should use SDLImageLoader otherwise your program will crash in a
 * most bizarre way.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Image {
	private static ImageLoader	c_imageLoader	= null;

	/**
	 * @return a pointer to the currently used ImageLoader
	 * @see sdljavax.guichan.sdl.SDLImageLoader
	 */
	public static ImageLoader getImageLoader() {
		return c_imageLoader;
	}

	/**
	 * Sets the ImageLoader to be used be the Image class.
	 * 
	 * IMPORTANT: The ImageLoader is static and MUST be set before loading images!
	 * 
	 * @param imageLoader
	 *            the ImageLoader to be used.
	 * @see sdljavax.guichan.sdl.SDLImageLoader
	 */
	public static void setImageLoader(ImageLoader imageLoader) {
		c_imageLoader = imageLoader;
	}

	private boolean	m_bLoadedWithImageLoader;
	private int		m_nHeight;
	private int		m_nWidth;
	private Object	m_objData;
	private String	m_strFileName;

	/**
	 * Default constructor. It is protected, but it's here so that you can overload Image it.
	 */
	protected Image() {
		m_bLoadedWithImageLoader = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param data
	 *            the data of the image
	 * @param width
	 *            the width of the image
	 * @param height
	 *            the height of the image
	 */
	public Image(Object data, int width, int height) {
		m_objData = data;
		m_nWidth = width;
		m_nHeight = height;
		m_bLoadedWithImageLoader = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param strFileName
	 *            the filename of the image.
	 * @throws GUIException
	 *             when no ImageLoader exists.
	 */
	public Image(String strFileName) throws GUIException {
		if (null == c_imageLoader) {
			throw new GUIException("I have no ImageLoader!");
		}
		m_strFileName = strFileName;
		m_bLoadedWithImageLoader = true;
		c_imageLoader.prepare(strFileName);
		m_nWidth = c_imageLoader.getWidth();
		m_nHeight = c_imageLoader.getHeight();
		m_objData = c_imageLoader.finish();
	}

	/**
	 * Destructor. Unloads the image with the ImageLoader, if it was loaded with it.
	 * 
	 * @throws GUIException
	 */
	public void delete() throws GUIException {
		if (m_bLoadedWithImageLoader) {
			c_imageLoader.free(this);
		}
	}

	/**
	 * @return an object with the image data. Image data can be different things depending on what
	 *         ImageLoader you use. If you for instance use the SDLImageLoader then an SDL_Surface
	 *         will be returned.
	 * @see sdljavax.guichan.sdl.SDLImageLoader
	 */
	public Object getData() {
		return m_objData;
	}

	/**
	 * @return the filename of the image (if existing)
	 */
	public String getFileName() {
		return m_strFileName;
	}

	/**
	 * @return the image height
	 */
	public int getHeight() {
		return m_nHeight;
	}

	/**
	 * @return the image width
	 */
	public int getWidth() {
		return m_nWidth;
	}
}
