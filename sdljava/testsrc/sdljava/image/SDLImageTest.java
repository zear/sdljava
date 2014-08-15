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

public class SDLImageTest extends SDLTest {

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
	SDLImageTest t = null;
	try {
	    t = new SDLImageTest();

	    String file = null;
		
	    t.init();

	    try {
		// load bmp
		file = "hubble_1";
		System.out.println("loading bmp: " + file + ".bmp");
		SDLSurface bmp = SDLImage.load("testdata" + File.separator + file + ".bmp");
		bmp.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch

	    try {
		// load xpm
		file = "hubble_2";
		System.out.println("loading xpm: " + file + ".xpm");
		SDLSurface xpm = SDLImage.load("testdata" + File.separator + file + ".xpm");
		xpm.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch
	    

	    try {
		// load xcf
		file = "hubble_3";
		System.out.println("loading xcf: " + file + ".xcf");
		SDLSurface xcf = SDLImage.load("testdata" + File.separator + file + ".xcf");
		xcf.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch
	    

	    try {
		// load pcx
		file = "hubble_4";
		System.out.println("loading pcx: " + file + ".pcx");
		SDLSurface pcx = SDLImage.load("testdata" + File.separator + file + ".pcx");
		pcx.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch


	    try {
		// load gif
		file = "hubble_5";
		System.out.println("loading gif: " + file + ".gif");
		SDLSurface gif = SDLImage.load("testdata" + File.separator + file + ".gif");
		gif.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch

	    try {
		// load jpg
		file = "hubble_6";
		System.out.println("loading jpg: " + file + ".jpg");
		SDLSurface jpg = SDLImage.load("testdata" + File.separator + file + ".jpg");
		jpg.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch

	    try {
		// load tif
		file = "hubble_7";
		System.out.println("loading tif: " + file + ".tif");
		SDLSurface tiff = SDLImage.load("testdata" + File.separator + file + ".tif");
		tiff.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch
	    
	    try {
		// load png
		file = "hubble_8";
		System.out.println("loading png: " + file + ".png");
		SDLSurface png = SDLImage.load("testdata" + File.separator + file + ".png");
		png.blitSurface(null, framebuffer, null);
		framebuffer.updateRect();
		Thread.currentThread().sleep(2000);
	    } catch (SDLException e) {
		System.out.print(e.getMessage());
	    } // try-catch


	    try {
		// load tga
		file = "hubble_9";
		System.out.println("loading tga: " + file + ".tga");
		SDLSurface tga = SDLImage.load("testdata" + File.separator + file + ".tga");
		tga.blitSurface(null, framebuffer, null);
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