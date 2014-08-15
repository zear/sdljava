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
import sdljava.event.SDLEvent;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLQuitEvent;

import sdljava.x.swig.SDLPressedState;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Port of checkkeys.c test program from SDL distribution
 *
 * @author  Ivan Z. Ganza
 * @version $Id: checkkeys.java,v 1.1 2005/09/16 23:21:35 ivan_ganza Exp $
 */
public class checkkeys extends SDLTest {

    public static void printKey(SDLKeyboardEvent e) {
	System.out.print("Key " +
			   (e.getState() == SDLPressedState.PRESSED ? "pressed" : "released:") +
			   "\t" +
			   e.getSym() +
			   "-" +
			   SDLEvent.getKeyName(e.getSym()));

	int unicode = e.getUnicode();
	if (unicode != 0) {
	    if(unicode < ' ' ) {
		System.out.print( "(^" + unicode + ")@");
	    }
	    else {
		System.out.print(" (" + unicode + ")");
	    }
	}

	System.out.println(" modifiers: " + e.getMod().toStringBrief());
    }

    public static void main(String[] args) {
	try {
	    List l = Arrays.asList(args);
	    
	    SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	    
	    long videoflags = SDLVideo.SDL_SWSURFACE;
	    if (l.size() > 0) {
		Iterator i = l.iterator();
		while (i.hasNext()) {
		    String s = (String) i.next();
		    
		    if (s.equals("-fullscreen")) videoflags |= SDLVideo.SDL_FULLSCREEN;
		    else {
			System.err.println("Usage: checkkeys [-fullscreen]\n");
			System.exit(1);
		    }
		}
	    }

	    /* Set 640x480 video mode */
	    SDLVideo.setVideoMode(648, 480, 0 , videoflags);

	    SDLEvent.enableUNICODE(1);

	    SDLEvent.enableKeyRepeat(SDLEvent.SDL_DEFAULT_REPEAT_DELAY,
				     SDLEvent.SDL_DEFAULT_REPEAT_INTERVAL);

	    /* Watch keystrokes */
	    while (true) {
		SDLEvent e = SDLEvent.waitEvent();
		if (e instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent)e;
		    printKey(keyboardEvent);
		}
		else if (e instanceof SDLQuitEvent) {
		    return;
		}
	    }
			     
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    //SDLMain.quit();
	}
    }
}