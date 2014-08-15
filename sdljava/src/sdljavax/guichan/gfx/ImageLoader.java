/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

import sdljavax.guichan.GUIException;

/**
 * This is an abstract class used to load images in guichan.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public interface ImageLoader {

	/**
	 * Prepares an image for reading. After you have called this function you can retrieve
	 * information about it and edit it.
	 * 
	 * @param strFileName
	 *            the image file to prepare
	 * @throws GUIException
	 *             when called without having finalized or disposed to last image or when unable to
	 *             load the image.
	 * @see #finish()
	 * @see #discard()
	 */
	public void prepare(String strFileName) throws GUIException;

	/**
	 * @return the width of the image.
	 * @throws GUIException
	 *             if no image has been prepared.
	 */
	public int getWidth() throws GUIException;

	/**
	 * @return the height of the image.
	 * @throws GUIException
	 *             if no image has been prepared.
	 */
	public int getHeight() throws GUIException;

	/**
	 * This function frees an image
	 * 
	 * NOTE: There is generally no reason to call this function as it is called upon by the Image
	 * object when destroying an Image.
	 * 
	 * @param image
	 *            the image to be freed and removed.
	 * @throws GUIException
	 *             when image points to null.
	 * @see Image
	 * @see #prepare(String)
	 */
	public void free(Image image) throws GUIException;

	/**
	 * This function returns a pointer raw data of an image, the raw data is in 32 bit RGBA format.
	 * The funcion will not free a prepared image, so finalize or discard should be used afterwards.
	 * 
	 * @return the raw image data
	 */
	public Object getRawData() throws GUIException;

	/**
	 * This function finalizes an image meaning it will return the image data. If the image contains
	 * pixels with "magic pink" (0xff00ff) they will be treated as transparent pixels.
	 * 
	 * @return the image data.
	 * @throws GUIException
	 *             when no image has been prepared.
	 * @see #prepare(String)
	 * @see #discard()
	 */
	public Object finish() throws GUIException;

	/**
	 * This function discards a prepared image.
	 * 
	 * @throws GUIException
	 *             when no image has been prepared.
	 * @see #prepare(String)
	 * @see #finish()
	 */
	public void discard() throws GUIException;

	/**
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 * @return the color of the pixel.
	 */
	public Color getPixel(int x, int y) throws GUIException;

	/**
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 * @param color
	 *            the color of the pixel to put.
	 */
	public void putPixel(int x, int y, final Color color) throws GUIException;
}
