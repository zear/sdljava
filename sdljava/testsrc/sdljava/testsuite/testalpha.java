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
import sdljava.video.SDLVideoInfo;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLColor;
import sdljava.video.SDLRect;
import sdljava.event.SDLEvent;
import sdljava.event.SDLEventType;
import sdljava.event.MouseState;
import sdljava.event.SDLMouseMotionEvent;
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
 * Port of testalpha.c test program from SDL distribution
 * <P>
 *  Simple program:  Fill a colormap with gray and stripe it down the screen,
 *  Then move an alpha valued sprite around the screen.
 *
 *
 * @author  Ivan Z. Ganza
 * @version $Id: testalpha.java,v 1.1 2005/09/16 23:21:35 ivan_ganza Exp $
 */
public class testalpha extends SDLTest {

    public static final int FRAME_TICKS = (1000 / 30); // 30 frames/second

    static SDLSurface sprite;
    static SDLSurface backing;
    static SDLRect    position = new SDLRect();
    static int        x_vel, y_vel = 0;
    static int        alpha_vel    = 1;
    static boolean    sprite_visible = false;
    static int        flashes;
    static long       flashtime;

    static SDLSurface initScreen(List args) throws SDLException {
	int width  = 640;
	int height = 480;
	int bpp    = 0;
	long        videoflags = SDLVideo.SDL_SWSURFACE;
	
	/* Initialize SDL */
	SDLMain.init(SDLMain.SDL_INIT_VIDEO); // exception throw if anything goes wrong

	/* Alpha blending doesn't work well at 8-bit color */
	SDLVideoInfo videoInfo = SDLVideo.getVideoInfo();
	if (videoInfo.getFormat().getBitsPerPixel() > 8) {
	    bpp = videoInfo.getFormat().getBitsPerPixel();
	}
	else {
	    bpp = 16;
	    System.out.println("forced 16bpp mode\n");
	}
	    
	videoflags = SDLVideo.SDL_SWSURFACE;
	if (args.size() > 0) {
	    for (int i = 0; i < args.size(); i++) {
		String s = (String) args.get(i);
		    
		if (s.equals("-bpp"))        {
		    bpp = Integer.parseInt((String)args.get(++i));
		    if (bpp <= 8) {
			System.out.println("forced 16bpp mode\n");
			bpp = 16;
		    }
		}
		else if (s.equals("-hw"))         videoflags |= SDLVideo.SDL_HWSURFACE;
		else if (s.equals("-warp"))       videoflags |= SDLVideo.SDL_HWPALETTE;
		else if (s.equals("-fullscreen")) videoflags |= SDLVideo.SDL_FULLSCREEN;
		else {
		    System.err.println("Usage: testalpha [-bpp N] [-warp] [-hw] [-fullscreen]\n");
		    System.exit(1);
		}
	    }
	}

	/* Set video mode */
	return SDLVideo.setVideoMode(width, height, bpp, videoflags);
    }

    public static void initBackground(SDLSurface screen) throws SDLException {
	/* Set the surface pixels and refresh! */
	screen.lockSurface();

	int index = 0;

	if (screen.getFormat().getBytesPerPixel() != 2) {
	    ByteBuffer pixels = screen.getPixelData();
	    pixels.clear();
	    
	    
	    for (int i = 0; i < screen.getHeight(); ++i) {
		pixels.putShort((short)((i * 255)/screen.getHeight()));
		index += screen.getPitch();
				
	    }
	}

	else {
	    // currently this has a problem
	    //		buffer16 = screen.getPixelData16();
	    //		for (int i = 0; i < screen.getHeight(); ++i) {
	    //		    int gradient = ((i * (255)) / screen.getHeight());
	    //		    long color   = screen.mapRGB(gradient, gradient, gradient);
	    //		    for (int k = 0; k < screen.getWidth(); k++) {
	    //			buffer16[index+k] = (int)color;
	    //		    }
	    //		    index += screen.getPitch();
	    //		}
	    //		screen.setPixelData16(buffer16);
	}

	screen.unlockSurface();
	screen.updateRect();
    }

    static SDLSurface createLight(SDLSurface screen, int radius) throws SDLException {
	SDLSurface light;
	/* Create a 32 (8/8/8/8) bpp square with a full 8-bit alpha channel */
	int alphamask = 0x000000FF;
	light = SDLVideo.createRGBSurface(SDLVideo.SDL_SWSURFACE, 2 * radius, 2 * radius, 32, 0xFF000000, 0x00FF0000, 0x0000FF00, alphamask);
	
//	int skip = light.getPitch()-(light.getWidth()*light.getFormat().getBytesPerPixel());
//
//	IntBuffer buf = light.getPixelData().asIntBuffer();
//
//	/* Get a tranparent pixel value - we'll add alpha later */
//	long pixel = SDLVideo.mapRGBA(light.getFormat(), 0xFF, 0xDD, 0x88, 0);
//	int index = 0;
//	for (int y = 0; y < light.getHeight(); ++y) {
//	    for (int x = 0; x < light.getWidth(); ++x) {
//		buf.put(pixel);
//		buf[index++] = pixel;
//	    }
//	    index += skip;
//	}
//
//	index = 0;
//	for(int y = 0; y < light.getWidth(); ++y) {
//	    for (int x = 0; x < light.getWidth(); ++x) {
//		/* Slow distance formula (from center of light) */
//		int xdist = x-(light.getWidth()/2);
//		int ydist = y-(light.getHeight()/2);
//		int range = (int)Math.sqrt(xdist*xdist+ydist*ydist);
//		int trans = 0;
//
//		/* Scale distance to range of transparency (0-255) */
//		if ( range > radius ) {
//		    trans = alphamask;
//		} else {
//		    /* Increasing transparency with distance */
//		    trans = ((range*alphamask)/radius);
//
//		    /* Lights are very transparent */
//		    int addition = (alphamask+1)/8;
//		    if ( (int)trans+addition > alphamask ) {
//			trans = alphamask;
//		    } else {
//			trans += addition;
//		    }
//		    buf[index++] |= (255-trans);
//		}
//		index += skip;
//	    }
//	}
//	light.setPixelData32(buf);

	light.setAlpha(SDLVideo.SDL_SRCALPHA|SDLVideo.SDL_RLEACCEL, 0);

	return light;
    }

    public static void flashLight(SDLSurface screen, SDLSurface light, int x, int y) throws SDLException {
	SDLRect position = new SDLRect();;

	/* Easy, center light */
	position.setX     ( x - (light.getWidth() / 2));
	position.setY     ( y - (light.getHeight() / 2));
	position.setWidth (light.getWidth());
	position.setHeight(light.getHeight());
	//System.out.println(position);

	long start = System.currentTimeMillis();

	light.blitSurface(screen, position);

	long end = System.currentTimeMillis();

	screen.updateRect(position);
	flashes += 1;

	/* Update time spend doing alpha blitting */
	flashtime += (end - start);
    }

    public static void loadSprite(SDLSurface screen, String path) throws SDLException {
	SDLSurface converted;
	
	/* Load the sprite image */
	sprite = SDLVideo.loadBMP(path); // exception thrown if something bad happends
	System.out.println("Loaded Sprite: " + path);
	System.out.println(sprite);

	/* Set transparent pixel as the pixel at (0,0) */
	if (sprite.getFormat().getPalette() != null) {
	    sprite.setColorKey(SDLVideo.SDL_SRCCOLORKEY, sprite.getPixelData().get(0));
	}

	/* Convert sprite to video format */
	converted = sprite.displayFormat();
	sprite = converted;

	/* Create the background */
	backing = SDLVideo.createRGBSurface(SDLVideo.SDL_SWSURFACE, sprite.getWidth(), sprite.getHeight(), 8, 0, 0, 0, 0);

	/* Convert background to video format */
	converted = backing.displayFormat();
	backing.freeSurface();
	backing = converted;

	/* Set the initial position of the sprite */
	position.setX( (screen.getWidth()  - sprite.getWidth()) / 2);
	position.setY( (screen.getHeight() - sprite.getHeight()) / 2);
	position.setWidth(sprite.getWidth());
	position.setHeight(sprite.getHeight());
	
    }

    public static void attractSprite(int x, int y) {
	x_vel = (int) (x - position.getX()) / 10;
	y_vel = (int) (y - position.getY()) / 10;
    }

    public static void moveSprite(SDLSurface screen, SDLSurface light) throws SDLException {
	SDLRect[] updates = new SDLRect[2];
	updates[0] = new SDLRect();
	
	int alpha;
	
	/* Erase the sprite if it was visible */
	if (sprite_visible) {
	    updates[0] = position;
	    backing.blitSurface(null, screen, updates[0]);
	}
	
	else {
	    updates[0].setLocation(0,0);
	    updates[0].setSize(0,0);
	    sprite_visible = true;
	}
	
	/* Since the sprite is off the screen, we can do other drawing
	   without being overwritten by the saved area behind the sprite.
	*/
	MouseState mouseState = SDLEvent.getMouseState();
	if (light != null) {
	    flashLight(screen, light, mouseState.getX(), mouseState.getY());
	}
	
	/* Move the sprite, bounce at the wall */
	int x = position.getX() + x_vel;
	int y = position.getY() + y_vel;
	position.setX( x );
	position.setY( y );
	
	if (x < 0 || x > screen.getWidth()) {
	    x_vel = -x_vel;
	    position.setX(position.getX() + x_vel);
	}
	if (y < 0 || y > screen.getHeight()) {
	    y_vel = -y_vel;
	    position.setY(position.getY() + y_vel);
	}
	
	/* Update transparency (fade in and out) */
	alpha = sprite.getFormat().getAlpha();
	if ( (alpha+alpha_vel) < 0 ) {
	    alpha_vel = -alpha_vel;
	} else {
	    if ( (alpha+alpha_vel) > 255 ) {
		alpha_vel = -alpha_vel;
	    }
	}
	sprite.setAlpha(SDLVideo.SDL_SRCALPHA, alpha+alpha_vel);
	
	/* Save the area behind the sprite */
	updates[1] = position;
	screen.blitSurface(updates[1], backing, null);
	
	/* Blit the sprite onto the screen */
	updates[1] = position;
	sprite.blitSurface(null, screen, updates[1]);
	
	/* Make it so! */
	//screen.updateRects(updates);
	if(screen.isDoubleBuffered()) {
	    screen.flip();
	}
	else {
	    screen.updateRect();
	}
    }

    public static void main(String[] args) {
	try {
	    System.out.println("*** NOTE:  This test is currently not working.  Sorry, will fix as soon as possible.");
	    System.exit(-1);
	    
	    SDLSurface screen;
	    
	    List l = Arrays.asList(args);

	    screen = initScreen(l);
	    
	    initBackground(screen);

	    /* Create the light */
	    SDLSurface light = createLight(screen, 82);

	    /* Load the sprite */
	    loadSprite(screen, "testdata" + java.io.File.separator + "icon.bmp");

	    /* Set a clipping rectangle to clip the outside edge of the screen */
	    SDLRect clip = new SDLRect(32, 32, screen.getWidth() - (2 * 32), screen.getHeight() - (2 * 32));
	    screen.setClipRect(clip);

	    /* Wait for a keystroke */
	    long lastnow = System.currentTimeMillis();
	    boolean mouse_pressed = false;
	    
	    while (true) {
		/* Update the frame -- move the sprite */
		if (mouse_pressed) {
		    moveSprite(screen, light);
		    mouse_pressed = false;
		}
		else {
		    moveSprite(screen, null);
		}

		long now = System.currentTimeMillis();
		if (now - lastnow < FRAME_TICKS) {
		    Thread.currentThread().sleep(FRAME_TICKS - (now-lastnow));
		}
		lastnow = now;

		while(true) {
		    SDLEvent event = SDLEvent.pollEvent();
		    if (event == null) break;

		    switch(event.getType()) {
			
			case SDLEventType.MOUSEMOTION:
			    SDLMouseMotionEvent mouseMotionEvent = (SDLMouseMotionEvent)event;
			    if (mouseMotionEvent.getState().getState() != 0) {
				attractSprite(mouseMotionEvent.getX(), mouseMotionEvent.getY());
				mouse_pressed = true;
			    }
			    break;
			    
			case SDLEventType.MOUSEBUTTONDOWN:
			    SDLMouseButtonEvent mouseButtonEvent = (SDLMouseButtonEvent)event;
			    if (mouseButtonEvent.getButton() == SDLEvent.SDL_BUTTON_RIGHT) {
				attractSprite(mouseButtonEvent.getX(), mouseButtonEvent.getY());
				mouse_pressed = true;
			    }
			    else {
				SDLRect rect = new SDLRect();

				rect.setX( mouseButtonEvent.getX() - 16);
				rect.setY( mouseButtonEvent.getY() - 16);
				rect.setWidth(32);
				rect.setHeight(32);
				screen.fillRect(rect, 0);
				screen.updateRect(rect);
			    }
			    break;

			case SDLEventType.KEYDOWN:
			case SDLEventType.QUIT:
			    return;
		    }
		}
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    if (flashes > 0) {
		System.out.println("" + flashes + " alpha blits, " + (float)(flashtime/ flashes) + "ms per blit\n");
	    }
	    SDLMain.quit();
	}
    }
}