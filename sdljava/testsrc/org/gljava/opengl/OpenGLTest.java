package org.gljava.opengl;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTest;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class OpenGLTest extends SDLTest {

	static SDLSurface framebuffer;

	public void init() throws SDLException {
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		framebuffer = SDLVideo.setVideoMode(800, 600, 32, SDLVideo.SDL_OPENGL
				| SDLVideo.SDL_DOUBLEBUF);
	}

	public void destroy() {
		try {
			SDLMain.quit();
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
	}

	public static void main(String[] args) {
		OpenGLTest t = null;
		try {
			t = new OpenGLTest();
			t.init();

			GL gl = framebuffer.getGL();

			gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			gl.glColor3f(1.0f, 1.0f, 1.0f);
			gl.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f);
			gl.glBegin(GL.GL_POLYGON);
			gl.glVertex3f(0.25f, 0.25f, 0.0f);
			gl.glVertex3f(0.75f, 0.25f, 0.0f);
			gl.glVertex3f(0.75f, 0.75f, 0.0f);
			gl.glVertex3f(0.25f, 0.75f, 0.0f);
			gl.glEnd();
			gl.glFlush();

			framebuffer.glSwapBuffers();

			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (t != null)
				t.destroy();
		} // finally
	}
}