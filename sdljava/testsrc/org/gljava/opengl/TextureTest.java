package org.gljava.opengl;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTest;
import sdljava.SDLTimer;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class TextureTest extends SDLTest {

	static SDLSurface framebuffer;

	public void init() throws SDLException {
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		framebuffer = SDLVideo.setVideoMode(640, 480, 32, SDLVideo.SDL_OPENGL
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
		TextureTest t = null;
		try {
			t = new TextureTest();
			t.init();

			GL gl = framebuffer.getGL();

			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(0.0, (double) framebuffer.getWidth(), 0.0,
					(double) framebuffer.getHeight(), -1.0, 1.0);
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl
					.glViewport(0, 0, framebuffer.getWidth(), framebuffer
							.getHeight());

			// enable 2D textures since we're using them
			gl.glEnable(GL.GL_TEXTURE_2D);

			// we're using 2D graphics so no need to depth test
			gl.glDisable(GL.GL_DEPTH_TEST);

			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();

			gl.glOrtho(0, framebuffer.getWidth(), framebuffer.getHeight(), 0,
					-1, 1);

			gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

			Sprite sprite = new Sprite(gl, "testdata" + java.io.File.separator
					+ "ship.bmp");

			for (float i = 0; i < 128 ; i++) {
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);
				sprite.draw(gl, (float)(320 + Math.sin((SDLTimer.getTicks()+ i * 5) * 0.003459734f) * 300) , (float)(240 + Math.sin((SDLTimer.getTicks()+ i * 5) * 0.003345973f) * 220));
				gl.glFlush();

				framebuffer.glSwapBuffers();
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
		finally {
			if (t != null)
				t.destroy();
		} // finally
	}
}