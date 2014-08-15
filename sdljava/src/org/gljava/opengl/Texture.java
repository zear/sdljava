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
/**
 * An OpenGL Texture
 *
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Texture.java,v 1.3 2005/02/10 04:18:56 ivan_ganza Exp $
 */
public class Texture {

    /** The GL target type */
    int		target;

    /** The GL texture ID */
    int		textureID;

    int		width;
    int		height;

    int		texWidth;
    int		texHeight;

    float	widthRatio;
    float	heightRatio;

    /**
     * Create a new texture
     *
     * @param target The GL target 
     * @param textureID The GL texture ID
     */
    public Texture(int target, int textureID) {
	this.target = target;
	this.textureID = textureID;
    }

    /**
     * Bind the specified GL context to a texture
     *
     * @param gl The GL context to bind to
     */
    public void bind(GL gl) {
	gl.glBindTexture(target, textureID);
    }

    /**
     * Set the height of the image
     *
     * @param height The height of the image
     */
    public void setHeight(int height) {
	this.height = height;
	calculateHeightRatio();
    }

    /**
     * Set the width of the image
     *
     * @param width The width of the image
     */
    public void setWidth(int width) {
	this.width = width;
	calculateWidthRatio();
    }

    /**
     * Get the height of the original image
     *
     * @return The height of the original image
     */
    public int getImageHeight() {
	return height;
    }

    /** 
     * Get the width of the original image
     *
     * @return The width of the original image
     */
    public int getImageWidth() {
	return width;
    }

    /**
     * Get the height of the physical texture
     *
     * @return The height of physical texture
     */
    public float getHeight() {
	return heightRatio;
    }

    /**
     * Get the width of the physical texture
     *
     * @return The width of physical texture
     */
    public float getWidth() {
	return widthRatio;
    }

    /**
     * Set the height of this texture 
     *
     * @param texHeight The height of the texture
     */
    public void setTextureHeight(int texHeight) {
	this.texHeight = texHeight;
	calculateHeightRatio();
    }

    /**
     * Set the width of this texture 
     *
     * @param texWidth The width of the texture
     */
    public void setTextureWidth(int texWidth) {
	this.texWidth = texWidth;
	calculateWidthRatio();
    }

    /**
     * Set the height of the texture. This will update the
     * ratio also.
     */
    private void calculateHeightRatio() {
	if (texHeight != 0) {
	    heightRatio = ((float) height) / texHeight;
	}
    }

    /**
     * Set the width of the texture. This will update the
     * ratio also.
     */
    private void calculateWidthRatio() {
	if (texWidth != 0) {
	    widthRatio = ((float) width) / texWidth;
	}
    }
}