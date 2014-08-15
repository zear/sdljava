package sdljavax.gui;

import java.io.File;

import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLRect;

import sdljava.util.BufferUtil;

import org.gljava.opengl.GL;
import org.gljava.opengl.ftgl.*;

public class GUITest {

    static SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(1024, 768, 32, SDLVideo.SDL_OPENGL|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public static void main(String[] args) {
	GUITest t = new GUITest();
	try {
	    t.init();

	    GL gl = framebuffer.getGL();

	    Window w = new Window(gl,
				  "test-window",
				  new SDLRect(100, 200, 256, 512),
				  new SDLRect(0, 0, framebuffer.getWidth(), framebuffer.getHeight()),
				  null);

	    Button button = new Button(gl,
				       "Fire",
				       new SDLRect(8, w.getHeight() - 64 , 64, 32),
				       "testdata" + File.separator + "gui" + File.separator + "round_button.png",
				       "testdata" + File.separator + "gui" + File.separator + "round_button_pushed.png");

	    Widget widget = new Widget(gl,
				       "widget-1",
				       new SDLRect(100, w.getHeight() - 64 , 64, 32),
				       "testdata" + File.separator + "gui" + File.separator + "brushed_metal1.png");

	    ListBox listBox = new ListBox(gl,
					  "listbox-1",
					  new SDLRect(20, 300, 200, 100),
					  null);

	    listBox.add("Fusion Reactor");
	    listBox.add("Energy Coil");
	    

	
	    w.add(button);
	    w.add(widget);
	    w.add(listBox);
	    

	    // enable 2D textures since we're using them
	    gl.glEnable(gl.GL_TEXTURE_2D);
	    
	    gl.glMatrixMode(gl.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrtho(0.0, (double) framebuffer.getWidth(), 0.0, (double) framebuffer.getHeight(), -1.0, 1.0);
	    gl.glMatrixMode(gl.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glViewport(0, 0, framebuffer.getWidth(), framebuffer.getHeight());

	    gl.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
	    
	    GUI.init(framebuffer, gl, w);
	    GUI.processEvents();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}