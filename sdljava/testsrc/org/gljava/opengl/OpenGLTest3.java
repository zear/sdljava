package org.gljava.opengl;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTest;
import sdljava.event.SDLEvent;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class OpenGLTest3 extends SDLTest {

	private SDLSurface framebuffer;
	private GL gl;
	private boolean done = false;
	private float theta = 0.0f;
	
	public void init() throws SDLException {
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		framebuffer = SDLVideo.setVideoMode(600, 300, 32, SDLVideo.SDL_OPENGL
				| SDLVideo.SDL_DOUBLEBUF | SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_NOFRAME);
		gl = framebuffer.getGL();
		setup_opengl(600,300);
	}
	
	private void setup_opengl( int width, int height ) {
		gl.glViewport(0, 0, width, height);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0);
		gl.glDepthFunc(GL.GL_LESS);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glMatrixMode(GL.GL_MODELVIEW);
	}

	public void destroy() {
		try {
			SDLMain.quit();
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
	}
	
	private void process_event() {
		try {
			// Poll for events, and handle the ones we care about.
			SDLEvent event = SDLEvent.pollEvent();
			if(event != null)
			switch (event.getType()) {
				case SDLEvent.SDL_KEYUP:
					done = !done;
					break;
				case SDLEvent.SDL_QUIT:
					System.exit(0);
			}
		} catch (SDLException se) {
			System.err.println("Error while polling event : " + SDLMain.getError());
			System.exit(1);
		}

	}
	
	private void render() {
		while(!done){
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f,0.0f,0.0f);
			gl.glRotatef(theta, 0.0f, 0.0f, 1.0f);
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1.0f, 0.0f, 0.0f);
			gl.glVertex2f(0.0f, 1.0f);
			gl.glColor3f(0.0f, 1.0f, 0.0f);
			gl.glVertex2f(0.87f, -0.5f);
			gl.glColor3f(0.0f, 0.0f, 1.0f);
			gl.glVertex2f(-0.87f, -0.5f);
			gl.glEnd();
			theta += .5f;
			framebuffer.glSwapBuffers();
			process_event();
		}
	}
	
	public void work() throws SDLException {
		init();
		while (!done) {
			process_event();
			render();
		}
	}

	public static void main(String[] args) {
		OpenGLTest3 t = null;
		try {
			t = new OpenGLTest3();
			t.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (t != null)
				t.destroy();
		} // finally
	}
}