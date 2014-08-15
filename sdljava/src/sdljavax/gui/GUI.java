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
import sdljava.video.SDLSurface;
import sdljava.event.SDLEvent;
import sdljava.event.SDLQuitEvent;
import org.gljava.opengl.GL;


public class GUI {

    public static final int FRAME_TICKS = (1000 / 30); // 30 frames/second
    
    /**
     * Currently focused window.  This window receives incoming events.
     *
     */
    static Widget     focus;
    static SDLSurface framebuffer;
    static GL         gl;

    /**
     * Describe <code>init</code> method here.
     *
     * @param gl a <code>GL</code> value
     */
    public static void init(SDLSurface f, GL glContext, Widget initialWindow) {
	framebuffer = f;
	gl    = glContext;
	focus = initialWindow;

	initialWindow.draw(gl);
	framebuffer.glSwapBuffers();
    }

    public static void processEvents() {
	long lastnow = System.currentTimeMillis();
	
	while (true) {
	    try {

		// handle all pending events
		while (true) {
		    SDLEvent event = SDLEvent.pollEvent();
		    if (event == null) break;

		    if (event instanceof SDLQuitEvent) {
			return;
		    }

		    if (focus != null) {
			focus.handleEvent(event);
		    }
		}
		
		long now = System.currentTimeMillis();
		if (now - lastnow >= FRAME_TICKS) {
		    gl.glClear (gl.GL_COLOR_BUFFER_BIT);
		    
		    focus.draw(gl);
		    
		    framebuffer.glSwapBuffers();
		    
		    lastnow = now;
		}
		else {
		    Thread.currentThread().sleep(FRAME_TICKS - (now - lastnow));
		}
		
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}