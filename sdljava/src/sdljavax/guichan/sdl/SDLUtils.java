/*
 * Created on Mar 5, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.sdl;

import java.nio.ByteBuffer;

import sdljava.SDLException;
import sdljava.video.SDLColor;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.gfx.Color;

/**
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 * 
 */
public class SDLUtils {
	public static int			SDL_BYTEORDER		= -1;
	public static final int		SDL_BIG_ENDIAN		= 0;
	public static final int		SDL_LITTLE_ENDIAN	= 1;
	public static final byte[]	c_buff				= new byte[4];

	static {
		// TODO add LITTLE_ENDIAN | BIG_ENDIAN setting
		SDL_BYTEORDER = SDL_LITTLE_ENDIAN;
	}

	/**
	 * Convert a byte array to an unsigned long value
	 * 
	 * @param b
	 *            byte array
	 * @return long value
	 */
	public static final long unsignedIntToLong(byte[] b) {
		long l = 0;
		l |= b[0] & 0xFF;
		l <<= 8;
		l |= b[1] & 0xFF;
		l <<= 8;
		l |= b[2] & 0xFF;
		l <<= 8;
		l |= b[3] & 0xFF;
		return l;
	}

	/**
	 * Convert a byte array to an unsigned int value
	 * 
	 * @param b
	 *            byte array
	 * @return int value
	 */
	public static final int unsignedIntToInt(byte[] b) {
		int i = 0;
		i |= b[0] & 0xFF;
		i <<= 8;
		i |= b[1] & 0xFF;
		i <<= 8;
		i |= b[2] & 0xFF;
		i <<= 8;
		i |= b[3] & 0xFF;
		return i;
	}

	/**
	 * Compare a big and small endian value with each other
	 * 
	 * @param nValueB
	 *            Big endian value
	 * @param nValueS
	 *            Small endian value
	 * @return true if values are equals
	 */
	public static boolean equalBigAndSmallEndian(int nValueB, int nValueS) {
		c_buff[0] = (byte) (nValueB >> 24 & 0xFF);
		c_buff[1] = (byte) (nValueB >> 16 & 0xFF);
		c_buff[2] = (byte) (nValueB >> 8 & 0xFF);
		c_buff[3] = (byte) (nValueB & 0xFF);
		int nResultB = c_buff[0] & 0xFF | c_buff[1] & 0xFF << 8 | c_buff[2] & 0xFF << 16 | c_buff[3] & 0xFF << 24;
		c_buff[0] = (byte) (nValueS & 0xFF);
		c_buff[1] = (byte) (nValueS >> 8 & 0xFF);
		c_buff[2] = (byte) (nValueS >> 16 & 0xFF);
		c_buff[3] = (byte) (nValueS >> 24 & 0xFF);
		int nResultS = c_buff[0] & 0xFF | c_buff[1] & 0xFF << 8 | c_buff[2] & 0xFF << 16 | c_buff[3] & 0xFF << 24;
		return nResultB == nResultS;
	}

	/**
	 * Retrieve a Color value from an SDLSurface at position x, y
	 * 
	 * @param surface
	 *            SDLSurface to use
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 * @return Color object
	 * @throws GUIException
	 */
	public static Color sdlGetPixel(SDLSurface surface, int x, int y) throws GUIException {
		int bpp = surface.getFormat().getBytesPerPixel();

		try {
			surface.lockSurface();

			byte[] buff = new byte[bpp];
			// Read 4 bytes (1 long)
			ByteBuffer pixelData = surface.getPixelData();
			int nOffset = (y * surface.getPitch() + (x * bpp));
			for (int nByte = 0; nByte < bpp; nByte++) {
				buff[nByte] = pixelData.get(nOffset + nByte);
			}

			int color = 0;

			switch (bpp) {
				case 1 :
					color = buff[0];
					break;
				case 2 :
					color = buff[0];
					break;
				case 3 :
					if (SDL_BYTEORDER == SDL_BIG_ENDIAN) {
						color = buff[0] << 16 | buff[1] << 8 | buff[2];
					} else {
						color = buff[0] | buff[1] << 8 | buff[2] << 16;
					}
					break;
				case 4 :
					// TODO Check color conversion
					color = unsignedIntToInt(buff);
					break;
			}

			SDLColor theColor = SDLVideo.getRGBA(color, surface.getFormat());
			surface.unlockSurface();

			Color newColor = new Color();
			newColor.r = theColor.getRed();
			newColor.g = theColor.getGreen();
			newColor.b = theColor.getBlue();
			newColor.a = theColor.getAlpha();
			return newColor;
		} catch (SDLException e) {
			throw new GUIException("Unable to get pixel color", e);
		}
	}
}