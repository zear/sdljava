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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;
import javax.imageio.ImageIO;
/**
 * Factory for easy loading of Textures.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: TextureFactory.java,v 1.2 2005/02/02 19:22:28 ivan_ganza Exp $
 */
public class TextureFactory {
    
    /**
     * Factory instance
     *
     */
    static TextureFactory instance;

    /**
     * Cache of loaded Textures
     *
     */
    HashMap textureCache = new HashMap();

    /**
     * Colour model including alpha for the GL image
     *
     */
    ColorModel glAlphaColorModel;
    
    /**
     * colour model for the GL image
     *
     */
    ColorModel glColorModel;
    
    /**
     * buffer for texture ID's
     *
     */
    IntBuffer textureIDBuffer = ByteBuffer.allocateDirect(1 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    
    static {
	instance = new TextureFactory();
    }

    /**
     *  initialization
     *
     */
    public static TextureFactory getFactory() {
	return instance;
    }

    /**
     * Creates a new <code>TextureFactory</code> instance.
     *
     */
    public TextureFactory() 
    {
	glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
						    new int[] {8,8,8,8},
						    true,
						    false,
						    ComponentColorModel.TRANSLUCENT,
						    DataBuffer.TYPE_BYTE);
                                            
        glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
					       new int[] {8,8,8,0},
					       false,
					       false,
					       ComponentColorModel.OPAQUE,
					       DataBuffer.TYPE_BYTE);
    }

    /**
     * Create a new texture ID 
     *
     * @return A new texture ID
     */
    int createTextureID(GL gl) {
	gl.glGenTextures(1, textureIDBuffer); 
	return textureIDBuffer.get(0);
    }

    /**
     * Load a Texture from the filesystem
     *
     * @param gl a <code>GL</code> value
     * @param path The path on the filesystem to the Texture
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public  Texture loadTexture(GL gl, String path) throws IOException {
	Texture texture = (Texture) textureCache.get(path);
	if (texture != null) return texture;

	BufferedImage bufferedImage = loadImage(path); 

	texture = loadTexture(gl,
			      bufferedImage,
			      gl.GL_TEXTURE_2D, // target
			      gl.GL_RGBA,       // dst pixel format
			      gl.GL_LINEAR,     // min filter (not used)
			      gl.GL_LINEAR);

	textureCache.put(path, texture);

	return texture;
    }

    public Texture loadSubTexture2D(GL gl, String path, int xOffset, int yOffset, int width, int height) throws IOException {
	Texture texture = (Texture) textureCache.get(path + "-" + xOffset + "-" + yOffset);
	if (texture != null) return texture;

	BufferedImage bufferedImage = loadImage(path); 

	texture = loadSubTexture2D(gl,
				   bufferedImage,
				   gl.GL_TEXTURE_2D, // target
				   gl.GL_RGBA,       // dst pixel format
				   gl.GL_LINEAR,     // min filter (not used)
				   gl.GL_LINEAR,
				   xOffset,
				   yOffset,
				   width,
				   height);

	textureCache.put(path + "-" + xOffset + "-" + yOffset, texture);
	
	return texture;
    }

    /**
     * Load a Texture from the given URL
     *
     * @param gl a <code>GL</code> value
     * @param url an <code>URL</code> value
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public  Texture loadTexture(GL gl, URL url) throws IOException {
	Texture texture = (Texture) textureCache.get(url);
	if (texture != null) return texture;

	BufferedImage bufferedImage = loadImage(url); 

	texture = loadTexture(gl,
			      bufferedImage,
			      gl.GL_TEXTURE_2D, // target
			      gl.GL_RGBA,       // dst pixel format
			      gl.GL_LINEAR,     // min filter (not used)
			      gl.GL_LINEAR);

	textureCache.put(url, texture);

	return texture;
    }

    /**
     * Load a Texture as a resource with the given name
     *
     * @param gl a <code>GL</code> value
     * @param resourceName a <code>String</code> value
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public  Texture loadTextureResource(GL gl, String resourceName) throws IOException {
	Texture texture = (Texture) textureCache.get(resourceName);
	if (texture != null) return texture;

	texture = loadTextureResource(gl,
			      resourceName,
			      gl.GL_TEXTURE_2D, // target
			      gl.GL_RGBA,       // dst pixel format
			      gl.GL_LINEAR,     // min filter (not used)
			      gl.GL_LINEAR);

	textureCache.put(resourceName, texture);

	return texture;
    }
    
    /**
     * Load a Texture as a resource with the given name
     *
     * @param gl a <code>GL</code> value
     * @param resourceName a <code>String</code> value
     * @param target an <code>int</code> value
     * @param dstPixelFormat an <code>int</code> value
     * @param minFilter an <code>int</code> value
     * @param magFilter an <code>int</code> value
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public  Texture loadTextureResource(GL gl, String resourceName, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
	BufferedImage bufferedImage = loadImageResource(resourceName); 

	return loadTexture(gl, bufferedImage, target, dstPixelFormat, minFilter, magFilter);
    }

    
    /**
     * Describe <code>loadTexture</code> method here.
     *
     * @param gl a <code>GL</code> value
     * @param image a <code>BufferedImage</code> value
     * @param target an <code>int</code> value
     * @param dstPixelFormat an <code>int</code> value
     * @param minFilter an <code>int</code> value
     * @param magFilter an <code>int</code> value
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public Texture loadTexture(GL gl, BufferedImage bufferedImage, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
	int srcPixelFormat = 0;

	// create texture ID
        int textureID = createTextureID(gl); 
        Texture texture = new Texture(target,textureID);

	gl.glBindTexture(target, textureID);

	texture.setWidth(bufferedImage.getWidth());
        texture.setHeight(bufferedImage.getHeight());
	
	if (bufferedImage.getColorModel().hasAlpha()) {
            srcPixelFormat = gl.GL_RGBA;
        } else {
            srcPixelFormat = gl.GL_RGB;
        }

	// convert image into a byte buffer of texture data 
        ByteBuffer textureBuffer = convertImageData(bufferedImage,texture); 
        
        if (target == gl.GL_TEXTURE_2D) { 
            gl.glTexParameteri(target, gl.GL_TEXTURE_MIN_FILTER, minFilter); 
            gl.glTexParameteri(target, gl.GL_TEXTURE_MAG_FILTER, magFilter); 
        } 
 
	// create the actual OpenGL texture 
        gl.glTexImage2D(target, 
			0, 
			dstPixelFormat, 
			getClosestGreaterPowerOf2(bufferedImage.getWidth()), 
			getClosestGreaterPowerOf2(bufferedImage.getHeight()), 
			0, 
			srcPixelFormat, 
			gl.GL_UNSIGNED_BYTE, 
			textureBuffer ); 
        
        return texture; 
    }

    /**
     * Describe <code>loadSubTexture</code> method here.
     *
     * @param gl a <code>GL</code> value
     * @param bufferedImage a <code>BufferedImage</code> value
     * @param target an <code>int</code> value
     * @param dstPixelFormat an <code>int</code> value
     * @param minFilter an <code>int</code> value
     * @param magFilter an <code>int</code> value
     * @param width an <code>int</code> value
     * @param height an <code>int</code> value
     * @return a <code>Texture</code> value
     * @exception IOException if an error occurs
     */
    public Texture loadSubTexture2D(GL gl, BufferedImage bufferedImage, int target, int dstPixelFormat, int minFilter, int magFilter, int xOffset, int yOffset, int width, int height) throws IOException {

	System.out.println("xOffset=" + xOffset + ",yOffset=" + yOffset + ",width=" + width + ", height=" + height);

	int srcPixelFormat = 0;

	// create texture ID
        int textureID = createTextureID(gl); 
        Texture texture = new Texture(target,textureID);

	gl.glBindTexture(target, textureID);

	texture.setWidth(width);
        texture.setHeight(height);

	System.out.println("w="+texture.getWidth()+",h="+texture.getHeight());
	
	if (bufferedImage.getColorModel().hasAlpha()) {
            srcPixelFormat = gl.GL_RGBA;
        } else {
            srcPixelFormat = gl.GL_RGB;
        }

	// convert image into a byte buffer of texture data 
        ByteBuffer textureBuffer = convertImageData(bufferedImage,texture); 
        
        if (target == gl.GL_TEXTURE_2D) { 
            gl.glTexParameteri(target, gl.GL_TEXTURE_MIN_FILTER, minFilter); 
            gl.glTexParameteri(target, gl.GL_TEXTURE_MAG_FILTER, magFilter); 
        } 
 
	// create the actual OpenGL texture 
        gl.glTexSubImage2D(target, 
			   0, 
			   xOffset,
			   yOffset,
			   getClosestGreaterPowerOf2(width), 
			   getClosestGreaterPowerOf2(height),
			   srcPixelFormat, 
			   gl.GL_UNSIGNED_BYTE, 
			   textureBuffer ); 
        
        return texture; 
    }

    /**
     * Convert the buffered image to byte buffer with the data in appropriate format
     * to pass to opengl
     *
     * @param bufferedImage The image to convert to a texture
     * @param texture The texture to store the data into
     * @return A buffer containing the data
     */
    ByteBuffer convertImageData(BufferedImage bufferedImage, Texture texture) { 
        ByteBuffer imageBuffer = null; 
        WritableRaster raster;
        BufferedImage texImage;
        
        int texWidth = 2;
        int texHeight = 2;
        
        // find closest power of 2 for the width and height
        // of the target texture
        while (texWidth < bufferedImage.getWidth()) {
            texWidth *= 2;
        }
        while (texHeight < bufferedImage.getHeight()) {
            texHeight *= 2;
        }
        
        texture.setTextureHeight(texHeight);
        texture.setTextureWidth(texWidth);
        
        if (bufferedImage.getColorModel().hasAlpha()) {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,4,null);
            texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable());
        } else {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,3,null);
            texImage = new BufferedImage(glColorModel,raster,false,new Hashtable());
        }
            
        // copy source image into the produced image
        Graphics g = texImage.getGraphics();
        g.setColor(new Color(0f,0f,0f,0f));
        g.fillRect(0,0,texWidth,texHeight);
        g.drawImage(bufferedImage,0,0,null);
        
        // build a byte buffer from the temporary image 
        // that be used by OpenGL to produce a texture.
        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

        imageBuffer = ByteBuffer.allocateDirect(data.length); 
        imageBuffer.order(ByteOrder.nativeOrder()); 
        imageBuffer.put(data, 0, data.length); 
        imageBuffer.flip();

        return imageBuffer; 
    } 

    /**
     * Get the closest greater power of 2
     * 
     * @param target The target number
     * @return The power of 2
     */
    final int getClosestGreaterPowerOf2(int target) {
        int ret = 2;
        while (ret < target) {
            ret *= 2;
        }
        return ret;
    }

    /**
     * Describe <code>loadImage</code> method here.
     *
     * @param path a <code>String</code> value
     * @return a <code>BufferedImage</code> value
     * @exception IOException if an error occurs
     */
    BufferedImage loadImage(String path) throws IOException {
	return ImageIO.read(new BufferedInputStream(new FileInputStream(path)));
    }

    /**
     * Describe <code>loadImage</code> method here.
     *
     * @param url an <code>URL</code> value
     * @return a <code>BufferedImage</code> value
     * @exception IOException if an error occurs
     */
    BufferedImage loadImage(URL url) throws IOException {
	return ImageIO.read(url);
    }

    /**
     * Describe <code>loadImageResource</code> method here.
     *
     * @param url an <code>URL</code> value
     * @return a <code>BufferedImage</code> value
     * @exception IOException if an error occurs
     */
    BufferedImage loadImageResource(String resourceName) throws IOException {
	return ImageIO.read(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(resourceName))); 
    }
    
} // class TextureFactory