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

public class PlayURL extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 0, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void main(String[] args) {
	PlayURL t = null;
	try {
	    if (args[0] == null) {
		printUsage();
		return;
	    }
	    
	    t = new PlayURL();
	    t.init();

	    SDLMixer.openAudio(44100, SDLAudio.AUDIO_S16LSB, 2, 8192);
	    MixerSpec spec = SDLMixer.querySpec();
	    System.out.println(spec);
	    int result = SDLMixer.allocateChannels(32);
	    if (result != 32) {
		System.err.println("Failed to allocate 32 channels!!");
	    }

	    System.out.println("loading URL: " + args[0]);
	    MixChunk sample= SDLMixer.loadWAV(new URL(args[0]));
	    System.out.println("competed, hit any key to quit");
	    SDLMixer.playChannel(-1, sample, 0);

	    while (true) {
		SDLEvent event = SDLEvent.waitEvent();
		if (event instanceof SDLKeyboardEvent) {
		    return;
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
	System.out.println("usage: PlayURL <URL>");
    }
}