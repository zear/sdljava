package sdljavax.gui;

import java.io.File;

import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLRect;

import org.gljava.opengl.GL;

public class WidgetTest {

    static SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, SDLVideo.SDL_OPENGL|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public static void main(String[] args) {
	WidgetTest t = new WidgetTest();
	try {
	    t.init();

	    GL gl = framebuffer.getGL();

	    Widget w = new Widget(gl,
				  "test-widet",
				  new SDLRect(0, 0, 512, 256),
				  "testdata" + File.separator + "gui" + File.separator + "brushed_metal1.png");

	    // enable 2D textures since we're using them
	    gl.glEnable(gl.GL_TEXTURE_2D);
	    
	    gl.glMatrixMode(gl.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrtho(0.0, (double) framebuffer.getWidth(), 0.0, (double) framebuffer.getHeight(), -1.0, 1.0);
	    gl.glMatrixMode(gl.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glViewport(0, 0, framebuffer.getWidth(), framebuffer.getHeight());

	    gl.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);

	    w.draw(gl, 64, 256);

	    framebuffer.glSwapBuffers();

	    Thread.currentThread().sleep(5000);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}