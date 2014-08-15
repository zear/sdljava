package org.gljava.opengl;
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
import java.net.URL;

/**
 * A simple sprite which has a texture and knows how to draw itself given a context and co-ordinates
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Sprite.java,v 1.4 2005/02/10 04:18:56 ivan_ganza Exp $
 */
public class Sprite {
    
    protected Texture texture;
    protected int     width;
    protected int     height;

    public Sprite() {
    }

    /**
     * Create a new sprite from given Texture
     *
     * @param t a <code>Texture</code> value
     */
    public Sprite(Texture t) {
	this.texture = t;
	width   = texture.getImageWidth();
	height  = texture.getImageHeight();
    }

    /**
     * Create a new sprite from the image given at texturePath
     * 
     * @param gl a <code>GL</code> value
     * @param texturePath a <code>String</code> value
     * @exception IOException if an error occurs
     */
    public Sprite(GL gl, String texturePath) throws IOException {
	texture = TextureFactory.getFactory().loadTexture(gl, texturePath);
	width   = texture.getImageWidth();
	height  = texture.getImageHeight();

    }

    /**
     * Create a new sprite from the image given at url
     *
     * @param gl a <code>GL</code> value
     * @param url an <code>URL</code> value
     * @exception IOException if an error occurs
     */
    public Sprite(GL gl, URL url) throws IOException {
	texture = TextureFactory.getFactory().loadTexture(gl, url);
	width   = texture.getImageWidth();
	height  = texture.getImageHeight();
    }

    /**
     * Create a new sprite from the image found at the given resourceName
     *
     * @param gl a <code>GL</code> value
     * @param resourceName a <code>String</code> value
     * @param resource a <code>boolean</code> value
     * @exception IOException if an error occurs
     */
    public Sprite(GL gl, String resourceName, boolean resource) throws IOException {
	texture = TextureFactory.getFactory().loadTextureResource(gl, resourceName);
	width   = texture.getImageWidth();
	height  = texture.getImageHeight();
    }

    /**
     * Get the width of this sprite in pixels
     * 
     * @return The width of this sprite in pixels
     */
    public int getWidth() {
	return texture.getImageWidth();
    }

    public void setWidth(int w) {
	this.width = w;
    }

    /**
     * Get the height of this sprite in pixels
     * 
     * @return The height of this sprite in pixels
     */
    public int getHeight() {
	return texture.getImageHeight();
    }

    public void setHeight(int h) {
	this.height = h;
    }

    /**
     * Draw the sprite at the specified location
     * 
     * @param x The x location at which to draw this sprite
     * @param y The y location at which to draw this sprite
     */
    public void draw(GL gl, float x, float y) {
	// store current model matrix
	gl.glPushMatrix();

	texture.bind(gl);

	gl.glTranslatef(x, y, 0);

	// draw a quad textured to match the sprite
	gl.glBegin(gl.GL_QUADS);
	{
	    gl.glTexCoord2f(0, 0);
	    gl.glVertex2f(0, 0);

	    gl.glTexCoord2f(0, texture.getHeight());
	    gl.glVertex2f(0, height);

	    gl.glTexCoord2f(texture.getWidth(), texture.getHeight());
	    gl.glVertex2f(width, height);

	    gl.glTexCoord2f(texture.getWidth(), 0);
	    gl.glVertex2f(width, 0);
	}
	gl.glEnd();

	// restore the model view matrix
	gl.glPopMatrix();
    }
}