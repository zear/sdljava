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

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;
/**
 * Port of testsprite.c test program from SDL distribution
 *
 *
 * @author  Ivan Z. Ganza
 * @version $Id: testsprite.java,v 1.1 2005/09/16 23:21:35 ivan_ganza Exp $
 */
public class testsprite extends SDLTest {

    public static final int NUM_SPRITES = 100;
    static int spriteCount = NUM_SPRITES;

    static int frames = 0;
    static List spriteList = new ArrayList(NUM_SPRITES);
    
    /**
     * Move the little sprites!
     *
     * @param screen a <code>SDLSurface</code> value
     * @param background a <code>long</code> value
     * @exception SDLException if an error occurs
     */
    public static void moveSprites(SDLSurface screen, long background) throws SDLException {
	screen.fillRect(background);

	Iterator i = spriteList.iterator();

	while(i.hasNext()) {
	    Sprite s = (Sprite)i.next();
	    s.move(screen, background);
	}

	if (screen.isDoubleBuffered()) {
	    screen.flip();
	}
	else {
	    i = spriteList.iterator();
	    while(i.hasNext()) {
		Sprite s = (Sprite)i.next();
		// would be better to update the rects all at once but this isn't yet possible
		screen.updateRect(s.getPosition());
	    }
	}
    }

    /* This is a way of telling whether or not to use hardware surfaces */
    public static long fastestFlags(long flags, int width, int height, int bpp) throws SDLException {
	// Hardware acceleration is only used in fullscreen mode
	flags |= SDLVideo.SDL_FULLSCREEN;

	// Check for various video capabilities
	SDLVideoInfo info = SDLVideo.getVideoInfo();
	if (info.getBlit_hw_CC() > 0 && info.getBlit_fill() > 0) {
	    // We use accelerated colorkeying and color filling
	    flags |= SDLVideo.SDL_HWSURFACE;
	}

	// If we have enough video memory, and will use accelerated
	// blits directly to it, then use page flipping.
	if((flags & SDLVideo.SDL_HWSURFACE) == SDLVideo.SDL_HWSURFACE) {

	    // Direct hardware blitting without double-buffering
	    // causes really bad flickering.
	    
	    if (info.getVideoMemory() > (width*height*bpp/8)) {
		flags |= SDLVideo.SDL_DOUBLEBUF;
	    }
	    else {
		flags &= ~SDLVideo.SDL_HWSURFACE;
	    } // else
	}
	
	return flags;
    }

    
    /**
     * Initialize the screen according to passed in arguments
     *
     * @return a <code>SDLSurface</code> value
     * @exception SDLException if an error occurs
     */
    public static SDLSurface initScreen(List args) throws SDLException {
	/* Initialize SDL */
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);

	long videoflags = SDLVideo.SDL_SWSURFACE|SDLVideo.SDL_ANYFORMAT;
	int  width  = 640;
	int  height = 480;
	int  bpp    = 8;
	    
	if (args.size() > 0) {
	    for (int i = 0; i < args.size(); i++) {
		String s = (String)args.get(i);
		    
		if (s.equals("-bpp"))        {
		    bpp = Integer.parseInt((String)args.get(++i));
		    videoflags &= ~SDLVideo.SDL_ANYFORMAT;
		}
		else if (s.equals("-width"))  width  = Integer.parseInt((String)args.get(++i));
		else if (s.equals("-height")) height = Integer.parseInt((String)args.get(++i));
		else if (s.equals("-bpp"))    bpp    = Integer.parseInt((String)args.get(++i));
		else if (s.equals("-hw"))         videoflags |= SDLVideo.SDL_HWSURFACE;
		else if (s.equals("-fast"))       videoflags  = fastestFlags(videoflags, width, height, bpp);
		else if (s.equals("-flip"))       videoflags |= SDLVideo.SDL_DOUBLEBUF;
		else if (s.equals("-fullscreen")) videoflags |= SDLVideo.SDL_FULLSCREEN;
		else if (s.equals("-numsprites")) spriteCount = Integer.parseInt((String)args.get(++i));
		else {
		    System.err.println("Usage: testsprite [-width N] [-height N] [-bpp N] [-hw] [-flip] [-fast] [-fullscreen] [-numsprites N]\n");
		    System.exit(1);
		}
	    }
	}
	    
	System.out.println(SDLVideo.getVideoInfo());
	SDLSurface screen = SDLVideo.setVideoMode(width, height, bpp, videoflags);

	System.out.println(screen);

	SDLVideo.wmSetCaption("testsprite", "testsprite");

	return screen;
    }

    /**
     * Initialize NUM_SPRITES sprites
     *
     * 
     * @exception SDLException if an error occurs
     */
    public static void initSprites(SDLSurface screen) throws SDLException {
	for (int i = 0; i < spriteCount; i++) {
	    spriteList.add(new Sprite(screen, "testdata" + java.io.File.separator + "icon.bmp"));
	}
	Sprite sprite = (Sprite) spriteList.get(0);

	/* Print out information about our surfaces */
	if (screen.isHardwareSurface()) {
	    System.out.println("Screen is in video memory");
	}
	else {
	    System.out.println("Screen is in system memory");
	}
	if (screen.isDoubleBuffered()) {
	    System.out.println("Screen has double-buffering enabled");
	}
	if (sprite.getSurface().isHardwareSurface()) {
	    System.out.println("Sprite is in video memory");
	}
	else {
	    System.out.println("Sprite is in system memory");
	}
	
	/* Run a sample blit to trigger blit acceleration */
	sprite.getSurface().blitSurface(screen);

	long background = screen.mapRGB(0x00, 0x00, 0x00);
	screen.fillRect(background);
	if (sprite.getSurface().isHardwareAccelerated()) {
	    System.out.println("Sprite blit uses hardware acceleration");
	}
	if (sprite.getSurface().isRLEAccelerated()) {
	    System.out.println("Sprite blit uses RLE acceleration");
	}
    }

    
    /**
     * Describe <code>main</code> method here.
     *
     * @param args a <code>String[]</code> value
     */
    public static void main(String[] args) {
	try {
	    SDLSurface screen = initScreen(Arrays.asList(args));
	    
	    initSprites(screen);

	    long background = screen.mapRGB(0x00, 0x00, 0x00);

	    long start = System.currentTimeMillis();
	    boolean loop = true;
	    while (loop) {
		frames+=1;
		moveSprites(screen, background);
		
		SDLEvent event = SDLEvent.pollEvent();
		if (event == null) continue;

		switch(event.getType()) {
		    case SDLEventType.MOUSEBUTTONDOWN:
			SDLMouseButtonEvent mouseButtonEvent = (SDLMouseButtonEvent)event;
			SDLVideo.warpMouse(screen.getWidth() / 2, screen.getHeight() / 2);
			break;
			
			/* Any keypress quits the app... */
		    case SDLEventType.KEYDOWN:
		    case SDLEventType.QUIT:
			loop = false;
			break;
		}

	    }
	    long end = System.currentTimeMillis();

	    if (end > start) {
		System.out.println("" + ((double)frames*1000/(end-start)) + " frames per second\n");
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    SDLMain.quit();
	}
    }
}

/**
 * Onc instance of a sprite we want to draw,
 * the actual bitmap surface is static 
 *
 * 
 * 
 */
class Sprite {
    public static final int MAX_SPEED = 2;
    
    static SDLSurface spriteSurface;
    static int widthMax, heightMax;
    static Random random = new Random(System.currentTimeMillis());

    SDLRect position = new SDLRect();

    int velocityX, velocityY;

    public Sprite(SDLSurface screen, String imagePath) throws SDLException {
	if (spriteSurface == null) {
	    /* Load the sprite image */
	    spriteSurface = SDLVideo.loadBMP(imagePath); // exception thrown if anything bad happends

	    /* Set transparent pixel as the pixel at (0,0) */
	    if (spriteSurface.getFormat().getPalette() != null) {
		spriteSurface.setColorKey(SDLVideo.SDL_SRCCOLORKEY|SDLVideo.SDL_RLEACCEL, spriteSurface.getPixelData().get(0));
	    }

	    // convert to display format
	    spriteSurface = spriteSurface.displayFormat(); // original sprite will be cleaned up by gc

	    widthMax = screen.getWidth() - spriteSurface.getWidth();
	    heightMax = screen.getHeight() - spriteSurface.getHeight();
	}

	position.setX( random.nextInt(screen.getWidth() - spriteSurface.getWidth()));
	position.setY( random.nextInt(screen.getHeight() - spriteSurface.getHeight()));
	position.setWidth(spriteSurface.getWidth());
	position.setHeight(spriteSurface.getHeight());

	velocityX = (random.nextInt(MAX_SPEED) - MAX_SPEED);
	velocityY = (random.nextInt(MAX_SPEED) - MAX_SPEED);
    }

    public void move(SDLSurface screen, long background) throws SDLException {
	int x = position.getX();
	int y = position.getY();

	x += velocityX;
	y += velocityY;

	if (x < 0 || x >= widthMax) {
	    velocityX = -velocityX;
	    x += velocityX;
	}

	if (y < 0 || y >= heightMax) {
	    velocityY = -velocityY;
	    y += velocityY;
	}
	position.setLocation(x,y);

	// blit to screen surface
	spriteSurface.blitSurface(screen, position);
    }

    public SDLSurface getSurface() {
	return spriteSurface;
    }

    public SDLRect getPosition() {
	return position;
    }
}

