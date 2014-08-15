package sdljava.image;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLColor;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.image.SDLImage;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;


public class SDLImageTestURL extends SDLTest {

    static SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public static void main(String[] args) {
	SDLImageTestURL t = null;
	try {
	    t = new SDLImageTestURL();

	    String file = null;
		
	    t.init();

	    try {
		// load bmp
		String url = args[0];
		System.out.println("loading url: " + url);
		SDLSurface image = SDLImage.load(new URL(url));
		image.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch

	    Thread.currentThread().sleep(5000);

	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}