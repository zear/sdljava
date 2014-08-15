/*
 * Created on Mar 5, 2005 (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

import java.util.Stack;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.font.Font;


/**
 * This is the graphics object used for drawing in the SDLJavaUI library. It contains all vital
 * member functions for drawing. The class is abstract and should be overloaded, to create graphic
 * drivers to specific platforms. We have included graphic drivers for some common platforms, like
 * the SDL library and the OpenGL library.
 * 
 * In the graphics object you can set clip areas to limit drawing to certain areas of the screen.
 * Clip areas are put on a stack, which means that you can push smaller and smaller clip areas onto
 * the stack. All coordinates will be relative to the topmost clip area. In most cases you won't
 * have to worry about the clip areas, unless you want to implement some really complex widget.
 * Pushing and poping of clip areas are handled automatically by container widgets when their child
 * widgets are drawn.
 * 
 * IMPORTANT: Remember to pop each clip area that you pushed on the stack after you are done with
 * it.
 * 
 * If you feel that the graphics object is to restrictive for your needs, there is nothing stopping
 * you from using your own code for drawing, for example with a library like SDL. However, this
 * might hurt the portability of your application.
 * 
 * If you implement a new graphics driver for a platform we don't support, we would be very pleased
 * to add it to SDLJavaUI.
 * 
 * @see sdljavax.guichan.opengl.OpenGLGraphics
 * @see sdljavax.guichan.sdl.SDLGraphics
 * @see sdljavax.guichan.gfx.Image
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public abstract class Graphics {
	/**
	 * Centered text
	 */
	public static final int	CENTER		= 1;
	/**
	 * Left-aligned text
	 */
	public static final int	LEFT		= 0;
	/**
	 * Right-aligned text
	 */
	public static final int	RIGHT		= 2;

	protected Stack			m_clipStack	= new Stack();
	private Font			m_font		= null;

	public Graphics() {
		super();
	}

	/**
	 * This function is called by the Gui class when Gui::draw() is called. It is needed by some
	 * graphics objects to perform preparations before they draw (for example, OpenGLGraphics).
	 * 
	 * NOTE: You will never need to call this function yourself, the Gui object will do it for you.
	 * 
	 * @see Graphics#endDraw()
	 * @see sdljavax.guichan.GUI#draw()
	 */
	public void beginDraw() throws GUIException {}

	/**
	 * This is a simplified version of the other drawImage. It will draw a whole image at the
	 * coordinate you specify. It is equivalent to calling: drawImage(myImage, 0, 0, dstX, dstY,
	 * image.getWidth(), image.getHeight());
	 * 
	 * @see Graphics#drawImage(Image, int, int, int, int, int, int)
	 */
	public void drawImage(final Image image, int dstX, int dstY) throws GUIException {
		drawImage(image, 0, 0, dstX, dstY, image.getWidth(), image.getHeight());
	}

	/**
	 * Draws a part of an image. Note that the width and height arguments will not scale the image,
	 * but specifies the size of the part to be drawn. If you want to draw the whole image there is
	 * a simplified version of this function.
	 * 
	 * EXAMPLE: drawImage(myImage, 10, 10, 20, 20, 40, 40); will draw a rectangular piece of myImage
	 * starting at coordinate (10, 10) in myImage, with width and height 40. The piece will be drawn
	 * with it's top left corner at coordinate (20, 20).
	 * 
	 * @param image
	 *            the image to draw.
	 * @param srcX
	 *            source image x coordinate
	 * @param srcY
	 *            source image y coordinate
	 * @param dstX
	 *            destination x coordinate
	 * @param dstY
	 *            destination y coordinate
	 * @param width
	 *            the width of the piece
	 * @param height
	 *            the height of the piece
	 * @see Image
	 */
	public abstract void drawImage(final Image image, int srcX, int srcY, int dstX, int dstY, int width, int height)
		throws GUIException;

	/**
	 * This function draws a line.
	 * 
	 * @param x1
	 *            the first x coordinate
	 * @param y1
	 *            the first y coordinate
	 * @param x2
	 *            the second x coordinate
	 * @param y2
	 *            the second y coordinate
	 * @throws GUIException
	 */
	public abstract void drawLine(int x1, int y1, int x2, int y2) throws GUIException;

	/**
	 * This function draws a single point (pixel).
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public abstract void drawPoint(int x, int y);

	/**
	 * This function draws a simple, non-filled, rectangle with one pixel border.
	 * 
	 * @param rectangle
	 *            the rectangle to draw
	 * @throws SDLException
	 * @see Rectangle
	 */
	public abstract void drawRectangle(final Rectangle rectangle);

	/**
	 * Draw text, left aligned.
	 * 
	 * @param text
	 *            the text to be drawn.
	 * @param x
	 *            the x coordinate where to draw the text.
	 * @param y
	 *            the y coordinate where to draw the text.
	 * @throws GUIException
	 *             when no font is set.
	 */
	public void drawText(final String text, int x, int y) throws GUIException {
		drawText(text, x, y, LEFT);
	}

	/**
	 * Draw text.
	 * 
	 * @param text
	 *            the text to be drawn.
	 * @param x
	 *            the x coordinate where to draw the text.
	 * @param y
	 *            the y coordinate where to draw the text.
	 * @param alignment
	 *            Graphics::LEFT, Graphics::CENTER or Graphics::RIGHT.
	 * @throws GUIException
	 *             when no font is set.
	 */
	public void drawText(final String text, int x, int y, int alignment) throws GUIException {
		if (null == m_font) {
			throw new GUIException("No font set.");
		}

		switch (alignment) {
			case LEFT :
				m_font.drawString(this, text, x, y);
				break;
			case CENTER :
				m_font.drawString(this, text, x - m_font.getWidth(text) / 2, y);
				break;
			case RIGHT :
				m_font.drawString(this, text, x - m_font.getWidth(text), y);
				break;
			default :
				throw new GUIException("Unknown alignment.");
		}
	}

	/**
	 * This function is called by the Gui class when a Gui::draw() is done. It should reset any
	 * state changes made by _beginDraw().
	 * 
	 * NOTE: You will never need to call this function yourself, the Gui object will do it for you.
	 * 
	 * @see Graphics#beginDraw()
	 * @see sdljavax.guichan.GUI#draw()
	 */
	public void endDraw() throws GUIException {}

	/**
	 * This function draws a filled rectangle.
	 * 
	 * @param rectangle
	 *            the filled rectangle to draw
	 * @see sdljavax.guichan.gfx.Rectangle
	 */
	public abstract void fillRectangle(final Rectangle rectangle) throws GUIException;

	/**
	 * Get the color used when drawing primitives.
	 * 
	 * @return a Color.
	 */
	public abstract Color getColor();

	/**
	 * Get the current clip area used in graphics. Usefull if you want to do drawing without the
	 * Graphics object.
	 * 
	 * @return a ClipRectangle
	 */
	public ClipRectangle getCurrentClipArea() throws GUIException {
		if (m_clipStack.isEmpty()) {
			throw new GUIException("The clip area stack is empty.");
		}

		return (ClipRectangle) m_clipStack.peek();
	}

	/**
	 * Removes the topmost clip area from the stack.
	 * 
	 * @throws GUIException
	 *             if the stack is empty when calling this function.
	 */
	public void popClipArea() throws GUIException {
		if (m_clipStack.isEmpty()) {
			throw new GUIException("Tried to pop clip area from empty stack.");
		}

		m_clipStack.pop();
	}

	/**
	 * This function pushes a clip area onto the stack. The x and y coordinates in the Rectangle
	 * will be relative to the last pushed clip area. If the new area falls outside the current clip
	 * area it will be clipped as necessary.
	 * 
	 * @param area
	 *            the clip area to be pushed onto the stack.
	 * @return false if the the new area lays totally outside the current clip area. Note that an
	 *         empty clip area will be pushed in this case.
	 * @see Rectangle
	 */
	public boolean pushClipArea(Rectangle area) throws GUIException {
		if (m_clipStack.isEmpty()) {
			ClipRectangle carea = new ClipRectangle(area);
			m_clipStack.push(carea);
			return true;
		}

		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		ClipRectangle carea = new ClipRectangle(area);
		carea.xOffset = top.xOffset + carea.x;
		carea.yOffset = top.yOffset + carea.y;
		carea.x += top.xOffset;
		carea.y += top.yOffset;

		boolean result = carea.intersect(top);

		m_clipStack.push(carea);

		return result;
	}

	/**
	 * Sets the color to be used when drawing primitives.
	 * 
	 * @param color
	 *            a color
	 */
	public abstract void setColor(final Color color);

	/**
	 * Sets the color to be used when drawing primitives.
	 * 
	 * @param value
	 *            a color value
	 */
	public void setColor(int value) {
		setColor(new Color(value));
	}

	/**
	 * Set the font to be used.
	 * 
	 * @param font
	 *            the font to be used.
	 */
	public void setFont(Font font) {
		m_font = font;
	}
}
