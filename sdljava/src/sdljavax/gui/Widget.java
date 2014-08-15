package sdljavax.gui;
/**
 *  sdljava - a java binding to the SDL API
 *
 *  Copyright (C) 2004  Ivan Z. Ganza
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA
 *
 *  Ivan Z. Ganza (ivan_ganza@yahoo.com)
 */
import java.io.IOException;

import sdljava.video.SDLRect;
import sdljava.event.SDLEventListener;
import sdljava.event.SDLEvent;

import org.gljava.opengl.GL;
import org.gljava.opengl.TextureFactory;
import org.gljava.opengl.Texture;

import org.gljava.opengl.ftgl.FTFont;
import org.gljava.opengl.ftgl.FTGLBitmapFont;

/**
 * An entity which can be drawn to a screen and may interact with the user.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Widget.java,v 1.2 2005/01/30 22:02:07 ivan_ganza Exp $
 */
public class Widget implements SDLEventListener {

    protected Container container;
    protected String  name;
    protected int     xPos;
    protected int     yPos;
    protected int     width;
    protected int     height;
    protected int     border = 1;
    protected Texture backgroundTexture;
    protected boolean visible = true;
    protected boolean drawBorder = true;
    
    protected static FTFont defaultFont;

    public Widget(GL gl, String name, SDLRect dimensions, String backgroundTexturePath) throws IOException {
	this.name   = name;
	this.xPos   = dimensions.x;
	this.yPos   = dimensions.y;
	this.width  = dimensions.width;
	this.height = dimensions.height;
	
	if (backgroundTexturePath != null) {
	    this.backgroundTexture = TextureFactory.getFactory().loadTexture(gl, backgroundTexturePath);
	}
    }
    
    /**
     * Gets the value of x
     *
     * @return the value of x
     */
    public int getX()  {
	return this.xPos;
    }

    /**
     * Sets the value of x
     *
     * @param argX Value to assign to this.x
     */
    public void setX(int argX) {
	this.xPos = argX;
    }

    /**
     * Gets the value of y
     *
     * @return the value of y
     */
    public int getY()  {
	return this.yPos;
    }

    /**
     * Sets the value of y
     *
     * @param argY Value to assign to this.y
     */
    public void setY(int argY) {
	this.yPos = argY;
    }

    /**
     * Gets the value of width
     *
     * @return the value of width
     */
    public int getWidth()  {
	return this.width;
    }

    /**
     * Sets the value of width
     *
     * @param argWidth Value to assign to this.width
     */
    public void setWidth(int argWidth) {
	this.width = argWidth;
    }

    /**
     * Gets the value of height
     *
     * @return the value of height
     */
    public int getHeight()  {
	return this.height;
    }

    /**
     * Sets the value of height
     *
     * @param argHeight Value to assign to this.height
     */
    public void setHeight(int argHeight) {
	this.height = argHeight;
    }

    /**
     * Gets the value of backgroundTexture
     *
     * @return the value of backgroundTexture
     */
    public Texture getBackgroundTexture()  {
	return this.backgroundTexture;
    }

    /**
     * Sets the value of backgroundTexture
     *
     * @param argBackgroundTexture Value to assign to this.backgroundTexture
     */
    public void setBackgroundTexture(Texture argBackgroundTexture) {
	this.backgroundTexture = argBackgroundTexture;
    }

    
    /**
     * Gets the value of name
     *
     * @return the value of name
     */
    public String getName()  {
	return this.name;
    }

    /**
     * Sets the value of name
     *
     * @param argName Value to assign to this.name
     */
    public void setName(String argName) {
	this.name = argName;
    }

    /**
     * Describe <code>draw</code> method here.
     *
     * @param gl a <code>GL</code> value
     */
    public void draw(GL gl) {
	draw(gl, xPos, yPos);
    }

    /**
     * Draw myself at the given co-ordinates
     *
     * @param gl a <code>GL</code> value
     * @param x an <code>int</code> value
     * @param y an <code>int</code> value
     */
    public void draw(GL gl, int x, int y) {
	if (container != null) {
	    x += container.xPos;
	    y += container.yPos;
	}
	
	// store current model matrix
	gl.glPushMatrix();
	
	// move to appropriate position
	gl.glTranslatef(x, y, 0);
	
	if (backgroundTexture != null) {
	    gl.glEnable(gl.GL_TEXTURE_2D);
	
	    backgroundTexture.bind(gl);

	    // draw the texture
	    gl.glBegin(gl.GL_QUADS);
	    {
		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(0, 0);
	
		gl.glTexCoord2f(0, backgroundTexture.getHeight());
		gl.glVertex2f(0, height);
	
		gl.glTexCoord2f(backgroundTexture.getWidth(), backgroundTexture.getHeight());
		gl.glVertex2f(width, height);
	
		gl.glTexCoord2f(backgroundTexture.getWidth(), 0);
		gl.glVertex2f(width, 0);
	    }
	    gl.glEnd();
	}

	if (drawBorder) {
	    gl.glDisable(gl.GL_TEXTURE_2D);

	    // draw the border around the window
	    gl.glBegin(gl.GL_LINES);
	    {
		gl.glColor3f(1.0f,1.0f,1.0f);

		gl.glVertex2f(0, 0); gl.glVertex2f(width, 0);
		gl.glVertex2f(width, 0); gl.glVertex2f(width, height);
		gl.glVertex2f(width, height); gl.glVertex2f(0, height);
		gl.glVertex2f(0, height); gl.glVertex2f(0, 0);
	    
	    }
	    gl.glEnd();
	}

	// restore the model view matrix
	gl.glPopMatrix();
    }

    /**
     * Describe <code>handleEvent</code> method here.
     *
     * @param event a <code>SDLEvent</code> value
     */
    public void handleEvent(SDLEvent event) {
    }

    /**
     * Describe <code>isVisible</code> method here.
     *
     * @return a <code>boolean</code> value
     */
    public boolean isVisible() { return this.visible ;}

    /**
     * Describe <code>hide</code> method here.
     *
     */
    public void hide() {
	this.visible = false;
    }
    
    /**
     * Describe <code>show</code> method here.
     *
     */
    public void show() {
	this.visible = true;
    }

    /**
     * Describe <code>contains</code> method here.
     *
     * @param rect a <code>SDLRect</code> value
     * @return a <code>boolean</code> value
     */
    public boolean contains(SDLRect rect) {
	return contains(rect.x, rect.x);
    }

    /**
     * x and y absolute
     * 
     *
     *
     *
     */
    public boolean contains(int x, int y) {
	int boundsX      = -1;
	int boundsY      = -1;
	int boundsWidth  = -1;
	int boundsHeight = -1;
	
	if (container != null) {
	    boundsX += xPos + container.xPos;
	    boundsY += yPos + container.yPos;
	    boundsWidth  = width  + boundsX;
	    boundsHeight = height + boundsY;
	}
	else {
	    boundsX      = xPos;
	    boundsY      = yPos;
	    boundsWidth  = width  + boundsX;
	    boundsHeight = height + boundsY;
	}
	
	//System.out.println("contains: x=" + x + ",y=" + y + ",xPos=" + xPos + ", yPos=" +yPos + ",width=" + width + ", height=" + height);
	
	return (x >= boundsX && x <= boundsWidth && y >= boundsY && y <= boundsHeight) ? true : false;
    }

    public void mouseEntered() {
	System.out.println("Widget[" + getName() + "]  mouse entered");
    }

    public void mouseExit() {
	System.out.println("Widget[" + getName() + "]  mouse exit");
    }

    static {
	try {
	    defaultFont = new FTGLBitmapFont("testdata" + java.io.File.separator + "arial.ttf");
	    defaultFont.faceSize(14, 72);
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }
}