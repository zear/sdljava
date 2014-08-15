package sdljava.mixer;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.audio.SDLAudioSpec;
import sdljava.audio.SDLAudio;

import sdljava.event.SDLEvent;
import sdljava.event.SDLEventType;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLQuitEvent;

import sdljava.mixer.SDLMixer;
import sdljava.mixer.MixChunk;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class SDLMixerTestBuffer extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void main(String[] args) {
	SDLMixerTestBuffer t = null;
	try {
	    if (args[0] == null) {
		printUsage();
		return;
	    }
	    
	    System.out.println("SDLMixerTestBuffer");
	    System.out.println("        (ESC) : Exit");
	    System.out.println("        (q)   : play sound");

	    t = new SDLMixerTestBuffer();

	    t.init();

	    SDLMixer.openAudio(44100, SDLAudio.AUDIO_S16LSB, 2, 8192);
	    MixerSpec spec = SDLMixer.querySpec();
	    System.out.println(spec);
	    int result = SDLMixer.allocateChannels(32);
	    if (result != 32) {
		System.err.println("Failed to allocate 32 channels!!");
	    }

	    MixChunk sample= SDLMixer.loadWAV(new URL(args[0]));
	    boolean loop = true;
	    
	    while (loop) {
		SDLEvent event = SDLEvent.waitEvent();
		if (event instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyEvent = (SDLKeyboardEvent)event;

		    if (keyEvent.getType() != SDLEventType.KEYDOWN) continue;

		    if (keyEvent.getSym() == SDLKey.SDLK_ESCAPE) {
			loop = false;
		    }

		    else if (keyEvent.getSym() == SDLKey.SDLK_q) {
			SDLMixer.playChannel(-1, sample, 0);
		    }
		    
		}
	    }
		
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    printUsage();
	}

	catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }

    public static void printUsage() {
	System.out.println("usage: SDLMixerTestBuffer <URL>");
    }
}