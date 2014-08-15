package sdljavax.gfx;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLColor;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLRect;

import sdljavax.gfx.SDLGfx;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

public class SDLGfxTest extends SDLTest {

    SDLSurface framebuffer;
    
    Random r = new Random(System.currentTimeMillis());

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    // draw some random pixels for a while
    void testPixelColor() throws SDLException {
	framebuffer.fillRect(0);
	
	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();
	    
	for (int i = 0; i < 100000; i++) {
	    int x = r.nextInt(width);
	    int y = r.nextInt(height);
		
	    SDLGfx.pixelColor(framebuffer, x, y, r.nextLong());
	    framebuffer.updateRect(new SDLRect(x, y, 1, 1));
	}
    }

    void testHLineColor() throws SDLException {
	framebuffer.fillRect(0);
	
	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();

	for (int i = 0; i < 100000; i++) {
	    int x = r.nextInt(width);
	    int y = r.nextInt(height);
		
	    SDLGfx.hlineColor(framebuffer, 0, x, y, r.nextLong());
	    framebuffer.updateRect(new SDLRect(0, y, x, 1));
	}
    }

    void testVLineColor() throws SDLException {
	framebuffer.fillRect(0);
	
	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();

	for (int i = 0; i < 100000; i++) {
	    int x = r.nextInt(width);
	    int y = r.nextInt(height);
		
	    SDLGfx.vlineColor(framebuffer, x, 0, y, r.nextLong());
	    framebuffer.updateRect(new SDLRect(x, 0, 1, y));
	}
    }

    void testAACircleColor() throws SDLException {
	framebuffer.fillRect(0);

	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();
	
	for (int i = 0; i < 10000; i++) {
	    int x = r.nextInt(width);
	    int y = r.nextInt(height);
	    int rad = r.nextInt(width / 4);

	    int updateX = x - (rad / 2);
	    int updateY = y - (rad / 2);
		
	    SDLGfx.aacircleColor(framebuffer, x, y, rad, r.nextLong());
	    framebuffer.updateRect(new SDLRect(x < 0 ? 0 : x, y < 0 ? 0 : y, rad, rad));
	}
    }

    void testRotoZoom() throws SDLException {
	SDLSurface sdljavaLogo = SDLVideo.loadBMP("testdata" + java.io.File.separator + "sdljava_logo.bmp");

	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();

	double zoom = 1.0;

	for (int i = 0; i < 100; i++) {
	    framebuffer.fillRect(0);
	    
	    int logoWidth  = sdljavaLogo.getWidth();
	    int logoHeight = sdljavaLogo.getHeight();

	    int x = (width / 2) -  (logoWidth / 2);
	    int y = (height / 2) - (logoHeight / 2);
	    
	    sdljavaLogo.blitSurface(framebuffer, new SDLRect(x, y, logoWidth, logoHeight));
	    framebuffer.updateRect();

	    sdljavaLogo = SDLGfx.rotozoomSurface(sdljavaLogo, 90, zoom, true);
	}
    }

    void testRotoZoom2() throws SDLException {
	SDLSurface sdljavaLogo = SDLVideo.loadBMP("testdata" + java.io.File.separator + "sdljava_logo.bmp");

	int width  = framebuffer.getWidth();
	int height = framebuffer.getHeight();

	double zoom = 1.1;

	for (int i = 0; i < 1000; i++) {
	    framebuffer.fillRect(0);
	    
	    int logoWidth  = sdljavaLogo.getWidth();
	    int logoHeight = sdljavaLogo.getHeight();

	    int x = (width / 2) -  (logoWidth / 2);
	    int y = (height / 2) - (logoHeight / 2);
	    
	    sdljavaLogo.blitSurface(framebuffer, new SDLRect(x, y, logoWidth, logoHeight));
	    framebuffer.updateRect();

	    sdljavaLogo = SDLGfx.rotozoomSurface(sdljavaLogo, 0, zoom, true);
	    
	    if(sdljavaLogo.getWidth() >= width) zoom = .9;
	    if(sdljavaLogo.getWidth() <= 25) zoom = 1.1;
	}
    }

    public static void main(String[] args) {
	SDLGfxTest t = null;
	try {
	    t = new SDLGfxTest();
		
	    t.init();
	    t.testPixelColor();
	    t.testHLineColor();
	    t.testVLineColor();
	    t.testAACircleColor();
	    //t.testRotoZoom();

	    Thread.currentThread().sleep(5000);

	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}