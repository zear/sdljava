package sdljava.audio;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import java.io.File;

public class SDLAudioTest extends SDLTest {

//    SDLSurface framebuffer;
//
//    public void init() throws SDLException {
//	int result = SDLMain.SDL_Init(SDLMainConstants.SDL_INIT_EVERYTHING);
//	if (result == -1) {
//	    System.out.println("Failed to init: " + SDLError.SDL_GetError());
//	    System.exit(-1);
//	}
//
//	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SWIG_SDLVideoConstants.SDL_HWSURFACE|SWIG_SDLVideoConstants.SDL_DOUBLEBUF);
//    }
//
//    public void destroy() {
//	SDLMain.SDL_Quit();
//    }
//    
//    public static void main(String[] args) {
//	SDLAudioTest t = null;
//	try {
//	    t = new SDLAudioTest();
//
//	    t.init();
//
//	    SDLAudioSpec desired = new SDLAudioSpec();
//	    desired.setFreq(44100);
//	    desired.setChannels(2);
//	    desired.setFormat(SWIG_SDLAudioConstants.AUDIO_S16LSB);
//	    desired.setSamples(8192);
//
//	    SDLAudioSpec obtained = new SDLAudioSpec();
//
//	    SDLAudio.openAudio(desired, obtained);
//	    System.out.println(obtained);
//
//	    SDLAudioSpec wavSpec = new SDLAudioSpec();
//	    short[] buf = SDLAudio.loadWAV("testdata" + File.separator + "test.wav", wavSpec);
//	    System.out.println("wavSpec.length=" + buf.length);
//
//	} catch (Exception e) {
//	    e.printStackTrace();
//	} // try-catch
//	finally {
//	    if (t != null) t.destroy();
//	} // finally
//    }
}