package org.gljava.opengl.model;
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
import java.io.FileNotFoundException;
import org.jdom.JDOMException;

import java.util.List;

import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.event.SDLEvent;
import sdljava.event.SDLEventType;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLQuitEvent;

import org.gljava.opengl.GL;
/**
 * ModelViewer program to facilitate viewing models stored in gljava (.xml) format.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: ModelViewer.java,v 1.1 2005/02/10 04:18:42 ivan_ganza Exp $
 */
public class ModelViewer {

    static SDLSurface framebuffer;
    
    static GL gl;
    static Mesh mesh;
    static float zoom = -50;

    public ModelViewer() {
    }

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 0, SDLVideo.SDL_OPENGL|SDLVideo.SDL_DOUBLEBUF);

	gl = framebuffer.getGL();

	gl.glClearColor(0.0f, 0.0f, 0.2f, 0.0f);
	gl.glShadeModel(gl.GL_SMOOTH);
	gl.glViewport(0,0,framebuffer.getWidth(),framebuffer.getHeight());
	gl.glMatrixMode(gl.GL_PROJECTION);
	gl.glLoadIdentity();
	gl.gluPerspective(45.0f, framebuffer.getWidth() / framebuffer.getHeight(),1.0f,1000.0f);
	//gl.glEnable(gl.GL_DEPTH_TEST);
	//gl.glPolygonMode (gl.GL_FRONT_AND_BACK, gl.GL_FILL);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public void loadAndShowModel(String path) throws IOException, FileNotFoundException, JDOMException{
	List l = XMLModelLoader.loadModel(path);
	mesh = (Mesh) l.get(0);
    }

    public static void main(String[] args) {
	ModelViewer t = null;
	try {
	    t = new ModelViewer();
	    t.init();

	    t.loadAndShowModel(args[0]);

	    while (true) {
		SDLEvent event = SDLEvent.pollEvent();
		if (event instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyEvent = (SDLKeyboardEvent) event;
		    if (keyEvent.getType() != SDLEventType.KEYDOWN) continue;

		    switch (keyEvent.getSym()) {
			case SDLKey.SDLK_ESCAPE:
			    return;

			case SDLKey.SDLK_UP:
			    zoom += 5;
			    break;
			    
			case SDLKey.SDLK_DOWN:
			    zoom -= 5;
			    break;
		    }

		    System.out.println(zoom);
		    gl.glMatrixMode(gl.GL_MODELVIEW);
		    gl.glLoadIdentity();
		    gl.glClear (gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT);
		    gl.glColor3f(1.0f,0.0f,0.0f);
		    gl.glTranslatef(0.0f, 0.0f, zoom);
	
		    mesh.render(gl);

		    gl.glFlush();
		    framebuffer.glSwapBuffers();
		}

		else if (event instanceof SDLQuitEvent) break;
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (t != null) t.destroy();
	}
    }
}