/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany. All rights reserved.
 */

package sdljavax.guichan.sdl;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;
import sdljavax.gfx.SDLGfx;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.ClipRectangle;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;

/**
 * This is an SDL implementation of the Graphics object. For more information about the Graphics
 * object please see the Graphics interface.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */
public class SDLGraphics extends Graphics {
	protected boolean		m_bAlpha		= false;
	protected Color			m_color			= new Color();
	protected SDLSurface	m_target		= null;
	private final SDLRect	m_tempRectDst	= new SDLRect();
	private final SDLRect	m_tempRectSrc	= new SDLRect();

	/**
	 * Constructor.
	 */
	public SDLGraphics() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#beginDraw()
	 */
	public void beginDraw() throws GUIException {
		pushClipArea(new Rectangle(0, 0, m_target.getWidth(), m_target.getHeight()));
	}

	/**
	 * Helper function for blitting a surface which uses a static SDLRect member
	 * 
	 * @param nSrcX
	 *            source x position
	 * @param nSrcY
	 *            source y position
	 * @param nWidth
	 *            width of blit
	 * @param nHeight
	 *            height blit
	 * @param source
	 *            source SDLSurface
	 * @param nDstX
	 *            destination x position
	 * @param nDstY
	 *            destination y position
	 * @throws SDLException
	 */
	private void blitSurface(int nSrcX, int nSrcY, int nWidth, int nHeight, SDLSurface source, int nDstX, int nDstY)
		throws SDLException {
		m_tempRectSrc.x = nSrcX;
		m_tempRectSrc.y = nSrcY;
		m_tempRectSrc.width = nWidth;
		m_tempRectSrc.height = nHeight;
		m_tempRectDst.x = nDstX;
		m_tempRectDst.y = nDstY;
		source.blitSurface(m_tempRectSrc, m_target, m_tempRectDst);
	}

	/**
	 * This function draws a horizontal line.
	 * 
	 * @param x1
	 *            the start coordinate of the line
	 * @param y
	 *            the y coordinate of the line
	 * @param x2
	 *            the end coordinate of the line
	 */
	protected void drawHLine(int x1, int y, int x2) {
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		x1 += top.xOffset;
		y += top.yOffset;
		x2 += top.xOffset;

		SDLGfx.hlineRGBA(m_target, x1, x2, y, m_color.r, m_color.g, m_color.b, m_color.a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawImage(sdljavax.guichan.gfx.Image, int, int, int, int,
	 *      int, int)
	 */
	public void drawImage(Image image, int srcX, int srcY, int dstX, int dstY, int width, int height)
		throws GUIException {
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		SDLSurface srcImage = (SDLSurface) image.getData();
		try {
			blitSurface(srcX, srcY, width, height, srcImage, dstX + top.xOffset, dstY + top.yOffset);
		} catch (SDLException e) {
			throw new GUIException("Unable to blit image", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawLine(int, int, int, int)
	 */
	public void drawLine(int x1, int y1, int x2, int y2) throws GUIException {
		if (x1 == x2) {
			drawVLine(x1, y1, y2);
			return;
		}
		if (y1 == y2) {
			drawHLine(x1, y1, x2);
			return;
		}

		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		x1 += top.xOffset;
		y1 += top.yOffset;
		x2 += top.xOffset;
		y2 += top.yOffset;

		// Draw a line with Bresenham

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		if (dx > dy) {
			if (x1 > x2) {
				// swap x1, x2
				x1 ^= x2;
				x2 ^= x1;
				x1 ^= x2;

				// swap y1, y2
				y1 ^= y2;
				y2 ^= y1;
				y1 ^= y2;
			}

			if (y1 < y2) {
				int y = y1;
				int p = 0;

				for (int x = x1; x <= x2; x++) {
					if (top.isPointInRect(x, y)) {
						SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);
					}

					p += dy;

					if (p * 2 >= dx) {
						y++;
						p -= dx;
					}
				}
			} else {
				int y = y1;
				int p = 0;

				for (int x = x1; x <= x2; x++) {
					if (top.isPointInRect(x, y)) {
						SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);

					}

					p += dy;

					if (p * 2 >= dx) {
						y--;
						p -= dx;
					}
				}
			}
		} else {
			if (y1 > y2) {
				// swap y1, y2
				y1 ^= y2;
				y2 ^= y1;
				y1 ^= y2;

				// swap x1, x2
				x1 ^= x2;
				x2 ^= x1;
				x1 ^= x2;
			}

			if (x1 < x2) {
				int x = x1;
				int p = 0;

				for (int y = y1; y <= y2; y++) {
					if (top.isPointInRect(x, y)) {
						SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);
					}

					p += dx;

					if (p * 2 >= dy) {
						x++;
						p -= dy;
					}
				}
			} else {
				int x = x1;
				int p = 0;

				for (int y = y1; y <= y2; y++) {
					if (top.isPointInRect(x, y)) {
						SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);
					}

					p += dx;

					if (p * 2 >= dy) {
						x--;
						p -= dy;
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawPoint(int, int)
	 */
	public void drawPoint(int x, int y) {
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		x += top.xOffset;
		y += top.yOffset;

		if (false == top.isPointInRect(x, y))
			return;

		SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#drawRectangle(sdljavax.guichan.gfx.Rectangle)
	 */
	public void drawRectangle(Rectangle rectangle) {
		int x1 = rectangle.x;
		int x2 = rectangle.x + rectangle.width - 1;
		int y1 = rectangle.y;
		int y2 = rectangle.y + rectangle.height - 1;

		drawHLine(x1, y1, x2);
		drawHLine(x1, y2, x2);

		drawVLine(x1, y1, y2);
		drawVLine(x2, y1, y2);
	}

	/**
	 * Draws an SDLSurface on the target surface. Normaly you'll use drawImage, but if you want to
	 * write SDL specific code this function might come in handy.
	 * 
	 * NOTE: The clip area will be taken into account.
	 * 
	 * @param surface
	 *            surface to draw on the target
	 * @param source
	 *            source SDLRect for drawing
	 * @param destination
	 *            destination SDLRect for drawing
	 */
	public void drawSDLSurface(SDLSurface surface, SDLRect source, SDLRect destination) throws SDLException {
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		destination.x += top.xOffset;
		destination.y += top.yOffset;
		surface.blitSurface(source, m_target, destination);
	}

	/**
	 * This function draws a vertical line.
	 * 
	 * @param x
	 *            the x coordinate of the line
	 * @param y1
	 *            the start coordinate of the line
	 * @param y2
	 *            the end coordinate of the line
	 */
	protected void drawVLine(int x, int y1, int y2) {
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();
		x += top.xOffset;
		y1 += top.yOffset;
		y2 += top.yOffset;

		SDLGfx.vlineRGBA(m_target, x, y1, y2, m_color.r, m_color.g, m_color.b, m_color.a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#endDraw()
	 */
	public void endDraw() throws GUIException {
		popClipArea();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#fillRectangle(sdljavax.guichan.gfx.Rectangle)
	 */
	public void fillRectangle(Rectangle rectangle) throws GUIException {
		Rectangle area = (Rectangle) rectangle.clone();
		ClipRectangle top = (ClipRectangle) m_clipStack.peek();

		area.x += top.xOffset;
		area.y += top.yOffset;

		if (false == area.intersect(top)) {
			return;
		}

		if (m_bAlpha) {
			int x1 = area.x > top.x ? area.x : top.x;
			int y1 = area.y > top.y ? area.y : top.y;
			int x2 = area.x + area.width < top.x + top.width ? area.x + area.width : top.x + top.width;
			int y2 = area.y + area.height < top.y + top.height ? area.y + area.height : top.y + top.height;
			int x, y;
			try {
				m_target.lockSurface();
				for (y = y1; y < y2; y++) {
					for (x = x1; x < x2; x++) {
						SDLGfx.pixelRGBA(m_target, x, y, m_color.r, m_color.g, m_color.b, m_color.a);
					}
				}
				m_target.unlockSurface();
			} catch (SDLException e) {
				throw new GUIException("Unable to fill rectangle", e);
			}
		} else {
			try {
				long color = m_target.mapRGBA(m_color.r, m_color.g, m_color.b, m_color.a);
				m_target.fillRect(new SDLRect(area.x, area.y, area.width, area.height), color);
			} catch (SDLException e) {
				throw new GUIException("Unable to fill rectangle", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#getColor()
	 */
	public Color getColor() {
		return m_color;
	}

	/**
	 * @return the target on which Gui-chan is drawn
	 */
	public SDLSurface getTarget() {
		return m_target;
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

		try {
			ClipRectangle carea = (ClipRectangle) m_clipStack.peek();
			setClipRect(carea.x, carea.y, carea.width, carea.height);
		} catch (SDLException e) {
			throw new GUIException("Unable to set new cliprect", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#pushClipArea(sdljavax.guichan.gfx.Rectangle)
	 */
	public boolean pushClipArea(Rectangle area) throws GUIException {
		boolean result = super.pushClipArea(area);

		try {
			ClipRectangle carea = (ClipRectangle) m_clipStack.peek();
			setClipRect(carea.x, carea.y, carea.width, carea.height);
		} catch (SDLException e) {
			throw new GUIException("Unable to set new cliprect", e);
		}

		return result;
	}

	/**
	 * Helper function for setting the cliprect using a static SDLRect member
	 * 
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @throws SDLException
	 */
	private void setClipRect(int x, int y, int width, int height) throws SDLException {
		m_tempRectSrc.x = x;
		m_tempRectSrc.y = y;
		m_tempRectSrc.width = width;
		m_tempRectSrc.height = height;
		m_target.setClipRect(m_tempRectSrc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.gfx.Graphics#setColor(sdljavax.guichan.gfx.Color)
	 */
	public void setColor(Color color) {
		m_color = color;
		m_bAlpha = color.a != 255;
	}

	/**
	 * This function sets the target on which to draw Gui-chan. Gui-chan is drawn upon calling the
	 * draw function found in the Gui class. As you might notice, Gui-chan can be drawn on any
	 * SDL_Surface. This funtion also pushes a clip area corresponding to the dimension of the
	 * target.
	 * 
	 * @param target
	 *            the target where to draw Gui-chan
	 */
	public void setTarget(SDLSurface target) {
		m_target = target;
	}
}
