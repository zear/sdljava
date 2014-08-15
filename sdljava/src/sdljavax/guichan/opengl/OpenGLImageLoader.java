/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.gljava.opengl.GL;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.ImageLoader;


/**
 * This is an OpenGL implementation of the ImageLoader. However, it cant load images by itself. It
 * requires another imageloader, such as the SDLImageLoader, to be able to load images.
 * 
 * @see sdljavax.guichan.gfx.ImageLoader
 * @see sdljavax.guichan.sdl.SDLImageLoader
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */
public class OpenGLImageLoader implements ImageLoader {

	// TODO Flags for transperancy.

	private GL			m_gl			= null;
	private ImageLoader	m_imageLoader	= null;

	/**
	 * Constructor.
	 */
	public OpenGLImageLoader(GL gl) {
		this(gl, null);
	}

	/**
	 * Constructor.
	 */
	public OpenGLImageLoader(GL gl, ImageLoader loader) {
		super();
		m_gl = gl;
		m_imageLoader = loader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#prepare(java.lang.String)
	 */
	public void prepare(String strFileName) throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		m_imageLoader.prepare(strFileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getWidth()
	 */
	public int getWidth() throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		return m_imageLoader.getWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getHeight()
	 */
	public int getHeight() throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		return m_imageLoader.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#free(sdljavax.guichan.gfx.Image)
	 */
	public void free(Image image) throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		m_gl.glDeleteTextures(1, (IntBuffer) image.getData());

		image = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getRawData()
	 */
	public Object getRawData() throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		return m_imageLoader.getRawData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#_finalize()
	 */
	public Object finish() throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}

		ByteBuffer rawData = (ByteBuffer) m_imageLoader.getRawData();
		int width = m_imageLoader.getWidth();
		int height = m_imageLoader.getHeight();
		int realWidth = 1, realHeight = 1;

		while (realWidth < width) {
			realWidth *= 2;
		}

		while (realHeight < height) {
			realHeight *= 2;
		}

		IntBuffer realData = ByteBuffer.allocateDirect(realWidth * realHeight * 4).order(rawData.order()).asIntBuffer();

		for (int y = 0; y < realHeight; y++) {
			for (int x = 0; x < realWidth; x++) {
				if (x < width && y < height) {
					int nRaw = rawData.getInt();
					if (nRaw == 0xff00ffff) { // Transparent
						realData.put(0x00000000);
					} else {
						realData.put(nRaw);
					}
				} else {
					realData.put(0x00000000);
				}
			}
		}

		IntBuffer texture = ByteBuffer.allocateDirect(1 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();

		m_gl.glGenTextures(1, texture);
		m_gl.glBindTexture(GL.GL_TEXTURE_2D, texture.get(0));
		m_gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, realWidth, realHeight, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE,
			realData);
		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);

		realData = null;

		m_imageLoader.discard();

		int error = (int) m_gl.glGetError();
		if (0 != error) {
			String errmsg = "";
			switch (error) {
				case GL.GL_INVALID_ENUM :
					errmsg = "GL_INVALID_ENUM";
					break;

				case GL.GL_INVALID_VALUE :
					errmsg = "GL_INVALID_VALUE";
					break;

				case GL.GL_INVALID_OPERATION :
					errmsg = "GL_INVALID_OPERATION";
					break;

				case GL.GL_STACK_OVERFLOW :
					errmsg = "GL_STACK_OVERFLOW";
					break;

				case GL.GL_STACK_UNDERFLOW :
					errmsg = "GL_STACK_UNDERFLOW";
					break;

				case GL.GL_OUT_OF_MEMORY :
					errmsg = "GL_OUT_OF_MEMORY";
					break;
			}

			throw new GUIException("glGetError said: " + errmsg);
		}

		return texture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#discard()
	 */
	public void discard() throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		m_imageLoader.discard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#getPixel(int, int)
	 */
	public Color getPixel(int x, int y) throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		return m_imageLoader.getPixel(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.ImageLoader#putPixel(int, int, sdljavax.guichan.gfx.Color)
	 */
	public void putPixel(int x, int y, Color color) throws GUIException {
		if (null == m_imageLoader) {
			throw new GUIException("No host ImageLoader present.");
		}
		m_imageLoader.putPixel(x, y, color);
	}
}
