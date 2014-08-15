/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.opengl;

import java.nio.IntBuffer;

import org.gljava.opengl.GL;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.ClipRectangle;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is an OpenGL implementation of the Graphics object. For more information about the Graphics
 * object please see the Graphics interface.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class OpenGLGraphics extends Graphics {

	private boolean	mAlpha	= false;
	private GL		m_gl;
	private int		m_nWidth;
	private int		m_nHeight;
	private Color	mColor;

	/**
	 * Constructor.
	 * 
	 * @param gl
	 *            the GL object to bind this graphics to
	 */
	public OpenGLGraphics(GL gl) {
		this(gl, 640, 480);
	}

	/**
	 * Constructor.
	 * 
	 * @param gl
	 *            the GL object to bind this graphics to
	 * @param width
	 *            the width of the logical drawing surface. Should be the same as the screen
	 *            resolution.
	 * @param height
	 *            the height ot the logical drawing surface. Should be the same as the screen
	 *            resolution.
	 */
	public OpenGLGraphics(GL gl, int width, int height) {
		super();
		m_gl = gl;
		setTargetPlane(width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#beginDraw()
	 */
	public void beginDraw() throws GUIException {
		m_gl.glPushAttrib(GL.GL_COLOR_BUFFER_BIT
			| GL.GL_CURRENT_BIT
			| GL.GL_DEPTH_BUFFER_BIT
			| GL.GL_ENABLE_BIT
			| GL.GL_FOG_BIT
			| GL.GL_LIGHTING_BIT
			| GL.GL_LINE_BIT
			| GL.GL_POINT_BIT
			| GL.GL_POLYGON_BIT
			| GL.GL_SCISSOR_BIT
			| GL.GL_STENCIL_BUFFER_BIT
			| GL.GL_TEXTURE_BIT
			| GL.GL_TRANSFORM_BIT);

		m_gl.glMatrixMode(GL.GL_MODELVIEW);
		m_gl.glPushMatrix();
		m_gl.glLoadIdentity();

		m_gl.glMatrixMode(GL.GL_TEXTURE);
		m_gl.glPushMatrix();
		m_gl.glLoadIdentity();

		m_gl.glMatrixMode(GL.GL_PROJECTION);
		m_gl.glPushMatrix();
		m_gl.glLoadIdentity();

		m_gl.glOrtho(0.0, m_nWidth, m_nHeight, 0.0, -1.0, 1.0);

		m_gl.glDisable(GL.GL_LIGHTING);
		m_gl.glDisable(GL.GL_CULL_FACE);
		m_gl.glDisable(GL.GL_DEPTH_TEST);

		m_gl.glEnable(GL.GL_SCISSOR_TEST);

		m_gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		m_gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

		m_gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);

		pushClipArea(new Rectangle(0, 0, m_nWidth, m_nHeight));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#endDraw()
	 */
	public void endDraw() throws GUIException {
		m_gl.glMatrixMode(GL.GL_MODELVIEW);
		m_gl.glPopMatrix();

		m_gl.glMatrixMode(GL.GL_TEXTURE);
		m_gl.glPopMatrix();

		m_gl.glMatrixMode(GL.GL_PROJECTION);
		m_gl.glPopMatrix();

		m_gl.glPopAttrib();

		popClipArea();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#popClipArea()
	 */
	public void popClipArea() throws GUIException {
		super.popClipArea();

		if (m_clipStack.isEmpty()) {
			return;
		}

		Rectangle area = (Rectangle) m_clipStack.peek();
		m_gl.glScissor(area.x, m_nHeight - area.y - area.height, area.width, area.height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#pushClipArea(sdljavax.guichan.gfx.Rectangle)
	 */
	public boolean pushClipArea(Rectangle area) throws GUIException {
		boolean bResult = super.pushClipArea(area);

		area = (Rectangle) m_clipStack.peek();
		m_gl.glScissor(area.x, m_nHeight - area.y - area.height, area.width, area.height);

		return bResult;
	}

	/**
	 * Sets the target plane on where to draw.
	 * 
	 * @param width
	 *            the width of the logical drawing surface. Should be the same as the screen
	 *            resolution.
	 * @param height
	 *            the height ot the logical drawing surface. Should be the same as the screen
	 *            resolution.
	 */
	public void setTargetPlane(int width, int height) {
		m_nWidth = width;
		m_nHeight = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawImage(sdljavax.guichan.gfx.Image, int, int, int, int,
	 *      int, int)
	 */
	public void drawImage(Image image, int srcX, int srcY, int dstX, int dstY, int width, int height)
		throws GUIException {
		ClipRectangle rect = (ClipRectangle) m_clipStack.peek();
		dstX += rect.xOffset;
		dstY += rect.yOffset;

		// The following code finds the real width and height of the texture.
		// OpenGL only supports texture sizes that are powers of two
		int realImageWidth = 1;
		int realImageHeight = 1;

		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		while (realImageWidth < imageWidth) {
			realImageWidth *= 2;
		}
		while (realImageHeight < imageHeight) {
			realImageHeight *= 2;
		}

		// Find OpenGL texture coordinates
		float texX1 = srcX / (float) realImageWidth;
		float texY1 = srcY / (float) realImageHeight;
		float texX2 = (srcX + width) / (float) realImageWidth;
		float texY2 = (srcY + height) / (float) realImageHeight;

		// Please dont look too closely at the next line, it is not pretty.
		// It uses the image data as a pointer to a GLuint
		m_gl.glBindTexture(GL.GL_TEXTURE_2D, ((IntBuffer) image.getData()).get(0));

		m_gl.glEnable(GL.GL_TEXTURE_2D);

		// Check if blending already is enabled
		if (false == mAlpha) {
			m_gl.glEnable(GL.GL_BLEND);
		}

		// Draw a textured quad -- the image
		m_gl.glBegin(GL.GL_QUADS);
		m_gl.glTexCoord2f(texX1, texY1);
		m_gl.glVertex3i(dstX, dstY, 0);

		m_gl.glTexCoord2f(texX1, texY2);
		m_gl.glVertex3i(dstX, dstY + height, 0);

		m_gl.glTexCoord2f(texX2, texY2);
		m_gl.glVertex3i(dstX + width, dstY + height, 0);

		m_gl.glTexCoord2f(texX2, texY1);
		m_gl.glVertex3i(dstX + width, dstY, 0);
		m_gl.glEnd();

		m_gl.glDisable(GL.GL_TEXTURE_2D);

		// Don't disable blending if the color has alpha
		if (false == mAlpha) {
			m_gl.glDisable(GL.GL_BLEND);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawLine(int, int, int, int)
	 */
	public void drawLine(int x1, int y1, int x2, int y2) throws GUIException {
		ClipRectangle rect = (ClipRectangle) m_clipStack.peek();

		x1 += rect.xOffset;
		y1 += rect.yOffset;
		x2 += rect.xOffset;
		y2 += rect.yOffset;

		m_gl.glBegin(GL.GL_LINES);
		m_gl.glVertex3f(x1 + 0.5f, y1 + 0.5f, 0);
		m_gl.glVertex3f(x2 + 0.5f, y2 + 0.5f, 0);
		m_gl.glEnd();

		m_gl.glBegin(GL.GL_POINTS);
		m_gl.glVertex3f(x2 + 0.5f, y2 + 0.5f, 0);
		m_gl.glEnd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawPoint(int, int)
	 */
	public void drawPoint(int x, int y) {
		ClipRectangle rect = (ClipRectangle) m_clipStack.peek();

		x += rect.xOffset;
		y += rect.yOffset;

		m_gl.glBegin(GL.GL_POINTS);
		m_gl.glVertex3i(x, y, 0);
		m_gl.glEnd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawRectangle(sdljavax.guichan.gfx.Rectangle)
	 */
	public void drawRectangle(Rectangle rectangle) {
		ClipRectangle rect = (ClipRectangle) m_clipStack.peek();

		m_gl.glBegin(GL.GL_LINE_LOOP);
		m_gl.glVertex3f(rectangle.x + rect.xOffset + 0.5f, rectangle.y + rect.yOffset + 0.5f, 0);
		m_gl.glVertex3f(rectangle.x + rectangle.width - 0.5f + rect.xOffset, rectangle.y + rect.yOffset + 0.5f, 0);
		m_gl.glVertex3f(rectangle.x + rectangle.width - 0.5f + rect.xOffset, rectangle.y
			+ rectangle.height
			+ rect.yOffset
			- 0.5f, 0);
		m_gl.glVertex3f(rectangle.x + rect.xOffset + 0.5f, rectangle.y + rectangle.height + rect.yOffset - 0.5f, 0);
		m_gl.glEnd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#fillRectangle(sdljavax.guichan.gfx.Rectangle)
	 */
	public void fillRectangle(Rectangle rectangle) throws GUIException {
		ClipRectangle rect = (ClipRectangle) m_clipStack.peek();

		m_gl.glBegin(GL.GL_QUADS);
		m_gl.glVertex3i(rectangle.x + rect.xOffset, rectangle.y + rect.yOffset, 0);
		m_gl.glVertex3i(rectangle.x + rectangle.width + rect.xOffset, rectangle.y + rect.yOffset, 0);
		m_gl.glVertex3i(rectangle.x + rectangle.width + rect.xOffset, rectangle.y + rectangle.height + rect.yOffset, 0);
		m_gl.glVertex3i(rectangle.x + rect.xOffset, rectangle.y + rectangle.height + rect.yOffset, 0);
		m_gl.glEnd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#getColor()
	 */
	public Color getColor() {
		return mColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#setColor(sdljavax.guichan.gfx.Color)
	 */
	public void setColor(Color color) {
		mColor = color;
		m_gl.glColor4f(color.r / 255.0f, color.g / 255.0f, color.b / 255.0f, color.a / 255.0f);

		mAlpha = color.a != 255;

		if (mAlpha) {
			m_gl.glEnable(GL.GL_BLEND);
		}
	}
}
