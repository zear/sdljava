package sdljava.testsuite;
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
import sdljava.SDLTest;
import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLColor;
import sdljava.video.SDLRect;
import sdljava.event.SDLEvent;
import sdljava.event.SDLEventType;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLMouseButtonEvent;
import sdljava.event.SDLQuitEvent;
import sdljava.x.swig.SDLPressedState;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

import java.nio.ByteBuffer;
/**
 * Port of graywin.c test program from SDL distribution
 *
 * @author  Ivan Z. Ganza
 * @version $Id: graywin.java,v 1.1 2005/09/16 23:21:35 ivan_ganza Exp $
 */
public class graywin extends SDLTest {

    public static final int NUM_COLORS = 256;

    static Random r = new Random(System.currentTimeMillis());

    public static void drawBackground(SDLSurface screen) throws SDLException {
	int index = 0;
	
	
	screen.lockSurface();

	if (screen.getFormat().getBytesPerPixel() != 2) {
	    ByteBuffer pixels = screen.getPixelData();
	    pixels.clear();

	    for(int i = 0; i < screen.getHeight(); ++i) {
		pixels.putShort((short)(i *(NUM_COLORS-1)));
		
		index += screen.getPitch();
	    }
	}

	else {
	    // currently this has a problem
	    
	    //throw new IllegalArgumentException("" + screen.getFormat().getBitsPerPixel() + " bpp mode currently not working");
	    //buffer16 = screen.getPixelData16();
	    //for(int i = 0; i < screen.getHeight(); i++) {
	    //    int gradient = ((i * (NUM_COLORS -1)) / screen.getHeight());
	    //    long color   = screen.mapRGB(gradient, gradient, gradient);
	    //    //index = 0;
	    //    for (int k = 0; k < screen.getWidth(); k++) {
	    //        buffer16[index+k] = (int)color;
	    //    }
	    //    index += screen.getPitch();
	    //}
	    //screen.setPixelData16(buffer16);
	}

	screen.unlockSurface();
	if (screen.isDoubleBuffered()) {
	    screen.flip();
	}
	else {
	    screen.updateRect();
	}
    }
    
    /**
     * Draw a randomly sized and colored box centered about (X,Y)
     *
     * @param screen a <code>SDLSurface</code> value
     * @param x an <code>int</code> value
     * @param y an <code>int</code> value
     * @param width an <code>int</code> value
     * @param height an <code>int</code> value
     * @exception SDLException if an error occurs
     */
    public static void drawBox(SDLSurface screen, int x, int y, int width, int height) throws SDLException {
	/* Get the bounds of the rectangle */

	int x_area = width  - x / 2;
	int y_area = height - y / 2;
	
	int area_w = r.nextInt(x_area);
	int area_h = r.nextInt(y_area);
	x = x - area_w / 2;
	y = y - area_h / 2;
	if (x < 0) x = 0;
	if (y < 0) y = 0;
	
	SDLRect area = new SDLRect(x , y, area_w, area_h);

	int randc = r.nextInt(NUM_COLORS);
	long color = -1;

	if (screen.getFormat().getBitsPerPixel() == 1) {
	    color = randc;
	}

	else {
	    color = SDLVideo.mapRGB(screen.getFormat(), randc, randc, randc);
	}
	
	/* Do it! */
	screen.fillRect(area, color);
	if (screen.isDoubleBuffered()) {
	    screen.flip();
	}
	else {
	    screen.updateRect(area);
	}
    }

    public static SDLSurface createScreen(int w, int h, int bpp, long flags) throws SDLException {
	SDLSurface screen = SDLVideo.setVideoMode(w, h, bpp, flags);

	long fullscreen = screen.getFlags() & SDLVideo.SDL_FULLSCREEN;
	System.out.println("Screen is in " + (fullscreen == 1 ? "fullscreen" : "windowed") + " mode");

	if (bpp == 8) {
	    SDLColor palette[] = new SDLColor[NUM_COLORS];
	    for (int i = 0; i < NUM_COLORS; i++) {
		palette[i] = new SDLColor();
		
		palette[i].setRed  ((NUM_COLORS-1)-i * (256 / NUM_COLORS));
		palette[i].setGreen((NUM_COLORS-1)-i * (256 / NUM_COLORS));
		palette[i].setBlue ((NUM_COLORS-1)-i * (256 / NUM_COLORS));
	    }
	    screen.setColors(palette);
	}

	return screen;
    }

    public static void main(String[] args) {
	try {
	    SDLSurface screen;
	    long        videoflags = SDLVideo.SDL_SWSURFACE;
	    int        width = 640;
	    int       height = 480;
	    int          bpp =   0;
		
	    List l = Arrays.asList(args);
	    
	    SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	    
	    videoflags = SDLVideo.SDL_SWSURFACE;
	    if (l.size() > 0) {
		for (int i = 0; i < l.size(); i++) {
		    String s = (String) l.get(i);
		    
		    if (s.equals("-width"))       width  = Integer.parseInt((String)l.get(++i));
		    else if (s.equals("-height")) height = Integer.parseInt((String)l.get(++i));
		    else if (s.equals("-bpp"))       bpp = Integer.parseInt((String)l.get(++i));
		    else if (s.equals("-hw"))         videoflags |= SDLVideo.SDL_HWSURFACE;
		    else if (s.equals("-hwpalette"))  videoflags |= SDLVideo.SDL_HWPALETTE;
		    else if (s.equals("-flip"))       videoflags |= SDLVideo.SDL_DOUBLEBUF;
		    else if (s.equals("-noframe"))    videoflags |= SDLVideo.SDL_NOFRAME;
		    else if (s.equals("-fullscreen")) videoflags |= SDLVideo.SDL_FULLSCREEN;
		    else {
			System.err.println("Usage: graywin [-width] [-height] [-bpp] [-hw] [-hwpalette] [-flip] [-noframe] [-fullscreen]\n");
			System.exit(1);
		    }
		}
	    }

	    screen = createScreen(width, height, bpp, videoflags);

	    drawBackground(screen);

	    while (true) {
		SDLEvent e = SDLEvent.waitEvent();

		switch(e.getType()) {
		    case SDLEventType.MOUSEBUTTONDOWN:
			SDLMouseButtonEvent mouseButtonEvent = (SDLMouseButtonEvent) e;
			drawBox(screen, mouseButtonEvent.getX(), mouseButtonEvent.getY(), width, height);
			break;
			
		    case SDLEventType.KEYDOWN:
			SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent)e;
			if (keyboardEvent.getSym() == SDLKey.SDLK_SPACE) {
			    SDLVideo.warpMouse(width / 2, height / 2);
			}

			else if (keyboardEvent.getSym() == SDLKey.SDLK_SPACE) {
			    videoflags ^= SDLVideo.SDL_FULLSCREEN;
			    screen = createScreen(screen.getWidth(), screen.getHeight(), screen.getFormat().getBitsPerPixel(), videoflags);
			}

			else {
			    return;
			}
			break;
			
		    case SDLEventType.QUIT:
			return;
			
		    case SDLEventType.VIDEOEXPOSE:
			drawBackground(screen);
			break;
		}
	    }
	    
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    SDLMain.quit();
	}
    }
}