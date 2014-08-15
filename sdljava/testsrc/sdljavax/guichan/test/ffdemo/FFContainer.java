/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.ffdemo;

import sdljava.SDLTimer;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.widgets.Container;

public class FFContainer extends Container {
	private static Image	c_imageCornerDL;
	private static Image	c_imageCornerDR;
	private static Image	c_imageCornerUL;
	private static Image	c_imageCornerUR;
	private static Image	c_imageHorizontal;
	private static int		c_nInstances	= 0;
	private static Image	c_nVertical;

	private boolean			m_bShow;
	private long			m_lTime;
	private int				m_nCurrentSlide;
	private int				m_nRealHeight;
	private int				m_nRealWidth;
	private int				m_nSlideTarget;

	public FFContainer() throws GUIException {
		super();

		if (0 == c_nInstances) {
			c_imageCornerUL = new Image(FFDemo.dataDir + "cornerul.png");
			c_imageCornerUR = new Image(FFDemo.dataDir + "cornerur.png");
			c_imageCornerDL = new Image(FFDemo.dataDir + "cornerdl.png");
			c_imageCornerDR = new Image(FFDemo.dataDir + "cornerdr.png");
			c_imageHorizontal = new Image(FFDemo.dataDir + "horizontal.png");
			c_nVertical = new Image(FFDemo.dataDir + "vertical.png");
		}

		c_nInstances++;

		m_nRealWidth = 0;
		m_nRealHeight = 0;
		m_lTime = -1;
		m_bShow = true;
		super.setWidth(0);
		super.setHeight(0);
		m_nSlideTarget = 0;
		m_nCurrentSlide = 0;
	}

	public void delete() throws GUIException {
		c_nInstances--;

		if (c_nInstances == 0) {
			c_imageCornerUL.delete();
			c_imageCornerUR.delete();
			c_imageCornerDL.delete();
			c_imageCornerDR.delete();
			c_imageHorizontal.delete();
			c_nVertical.delete();
		}
		super.delete();
	}

	public void draw(Graphics graphics) throws GUIException {
		int i;

		if (isOpaque()) {
			double height = (m_nRealHeight - 8) / 16.0;
			Color c = new Color(0x7070FF);

			for (i = 0; i < 16; ++i) {
				graphics.setColor(c.multiply(1.0 - i / 18.0));
				graphics.fillRectangle(new Rectangle(4, (int) (i * height + 4), getWidth() - 8,
					(int) ((i * height) + height)));
			}
		}

		graphics.pushClipArea(new Rectangle(0, m_nCurrentSlide, getWidth(), getHeight()));
		drawChildren(graphics);
		graphics.popClipArea();

		for (i = 5; i < getHeight() - 10; i += 5) {
			graphics.drawImage(c_nVertical, 0, i);
			graphics.drawImage(c_nVertical, getWidth() - 4, i);
		}
		graphics.drawImage(c_nVertical, 0, 0, 0, i, 4, getHeight() - 5 - i);
		graphics.drawImage(c_nVertical, 0, 0, getWidth() - 4, i, 4, getHeight() - 5 - i);

		for (i = 5; i < getWidth() - 10; i += 5) {
			graphics.drawImage(c_imageHorizontal, i, 0);
			graphics.drawImage(c_imageHorizontal, i, getHeight() - 4);
		}
		graphics.drawImage(c_imageHorizontal, 0, 0, i, 0, getWidth() - 5 - i, 4);
		graphics.drawImage(c_imageHorizontal, 0, 0, i, getHeight() - 4, getWidth() - 5 - i, 4);

		graphics.drawImage(c_imageCornerUL, 0, 0);
		graphics.drawImage(c_imageCornerUR, getWidth() - 5, 0);
		graphics.drawImage(c_imageCornerDL, 0, getHeight() - 5);
		graphics.drawImage(c_imageCornerDR, getWidth() - 5, getHeight() - 5);
	}

	public void logic() throws GUIException {
		if (m_lTime < 0) {
			m_lTime = SDLTimer.getTicks();
		}

		long deltaTime = SDLTimer.getTicks() - m_lTime;
		m_lTime = SDLTimer.getTicks();

		if (false == m_bShow) {
			super.setWidth((int) (getWidth() - deltaTime));

			if (getWidth() < 0) {
				super.setWidth(0);
			}

			super.setHeight((int) (getHeight() - deltaTime));

			if (getHeight() < 0) {
				super.setHeight(0);
			}

			if (getHeight() == 0 && getWidth() == 0) {
				super.setVisible(false);
			}
		} else {
			if (getWidth() < m_nRealWidth) {
				super.setWidth((int) (getWidth() + deltaTime));

				if (getWidth() > m_nRealWidth) {
					super.setWidth(m_nRealWidth);
				}
			} else if (getWidth() > m_nRealWidth) {
				super.setWidth((int) (getWidth() - deltaTime));

				if (getWidth() < m_nRealWidth) {
					super.setWidth(m_nRealWidth);
				}
			}

			if (getHeight() < m_nRealHeight) {
				super.setHeight((int) (getHeight() + deltaTime));

				if (getHeight() > m_nRealHeight) {
					super.setHeight(m_nRealHeight);
				}
			} else if (getHeight() > m_nRealHeight) {
				super.setHeight((int) (getHeight() - deltaTime));

				if (getHeight() < m_nRealHeight) {
					super.setHeight(m_nRealHeight);
				}
			}
		}

		if (m_nCurrentSlide < m_nSlideTarget) {
			m_nCurrentSlide += deltaTime;
			if (m_nCurrentSlide > m_nSlideTarget) {
				m_nCurrentSlide = m_nSlideTarget;
			}
		}

		if (m_nCurrentSlide > m_nSlideTarget) {
			m_nCurrentSlide -= deltaTime;
			if (m_nCurrentSlide < m_nSlideTarget) {
				m_nCurrentSlide = m_nSlideTarget;
			}
		}

		super.logic();
	}

	public void setDimension(Rectangle dimension) {
		setPosition(dimension.x, dimension.y);
		setWidth(dimension.width);
		setHeight(dimension.height);
	}

	public void setHeight(int height) {
		m_nRealHeight = height;
	}

	public void setVisible(boolean visible) throws GUIException {
		m_bShow = visible;

		if (visible) {
			super.setVisible(visible);
		}
	}

	public void setWidth(int width) {
		m_nRealWidth = width;
	}

	public void slideContentTo(int y) {
		m_nSlideTarget = y;
	}
}
