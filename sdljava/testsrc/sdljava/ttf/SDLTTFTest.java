package sdljava.ttf;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLColor;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.video.SDLRect;

import java.io.File;

public class SDLTTFTest extends SDLTest {

    static SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
	SDLTTF.init();
    }

    public void destroy() {
	try {
	    SDLTTF.quit();
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public static void main(String[] args) {
	SDLTTFTest t = null;
	try {
	    t = new SDLTTFTest();
		
	    t.init();

	    long start, end;

	    SDLTrueTypeFont    font = SDLTTF.openFont("testdata" + File.separator + "arial.ttf", 24);
	    System.out.println(font);

	    TextSize size = font.sizeText("how long is this?");
	    System.out.println(size);
	    
	    start = System.currentTimeMillis();
	    SDLSurface solid = font.renderTextSolid("This is Solid text", new SDLColor(255, 255,255,255));
	    end = System.currentTimeMillis();

	    //System.out.println("Time to create SOLID font surface:[" + (end - start) + "]ms");
	    solid.blitSurface(null, framebuffer, null);

	    start = System.currentTimeMillis();
	    String s = new String("TUn long peuple il y a temps a parlé cette langue.  his is shaded text");
	    
	    SDLSurface shaded = font.renderTextShaded(s, new SDLColor(255, 255,255,255), new SDLColor(55, 55,55,255));
	    end = System.currentTimeMillis();

	    //System.out.println("Time to create SHADED font surface:[" + (end - start) + "]ms");
	    shaded.blitSurface(framebuffer, new SDLRect(0, 300));

	    start = System.currentTimeMillis();
	    SDLSurface text = font.renderTextBlended("A long time ago when the ancients roamed the galaxy...", new SDLColor(255, 255, 200));
	    end = System.currentTimeMillis();

	    text.blitSurface(framebuffer, new SDLRect(0, 100));
	    //System.out.println("Time to create BLENDED font surface:[" + (end - start) + "]ms");

//	    GlyphMetrics metrics = font.glyphMetrics('c');
//	    System.out.println(metrics);
	    
	    framebuffer.flip();

	    Thread.currentThread().sleep(5000);

	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}