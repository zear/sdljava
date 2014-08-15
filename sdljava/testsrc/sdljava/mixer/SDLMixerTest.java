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

public class SDLMixerTest extends SDLTest {

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
	SDLMixerTest t = null;
	try {
	    System.out.println("SDLMixerTest");
	    System.out.println("        (ESC) : Exit");
	    System.out.println("        (q)   : play magnum");
	    System.out.println("        (w)   : play machine gun");
	    System.out.println("        (e)   : play airnuke");
	    System.out.println("        (r)   : play ak");
	    System.out.println("        (a)   : play auto");
	    System.out.println("        (s)   : play bang");
	    System.out.println("        (d)   : play barrage");
	    System.out.println("        (f)   : play bazooka");
	    System.out.println("        (x)   : play cannon");
	    System.out.println("        (SPC) : halt music/fade in music");
	    
	    t = new SDLMixerTest();

	    t.init();

	    boolean symphonyPlaying = true;
	    boolean paused = false;
	    int volume = 128;

	    SDLMixer.openAudio(44100, SDLAudio.AUDIO_S16LSB, 2, 8192);
	    MixerSpec spec = SDLMixer.querySpec();
	    System.out.println(spec);
	    int result = SDLMixer.allocateChannels(32);
	    if (result != 32) {
		System.err.println("Failed to allocate 32 channels!!");
	    }

	    MixChunk magnum  = SDLMixer.loadWAV("testdata" + File.separator + "44magnum.wav");
	    MixChunk mm      = SDLMixer.loadWAV("testdata" + File.separator + "9mm.wav");
	    MixChunk airnuke = SDLMixer.loadWAV("testdata" + File.separator + "airnuke.wav");
	    MixChunk ak      = SDLMixer.loadWAV("testdata" + File.separator + "ak47.wav");
	    MixChunk auto    = SDLMixer.loadWAV("testdata" + File.separator + "auto.wav");
	    MixChunk bang    = SDLMixer.loadWAV("testdata" + File.separator + "bang.wav");
	    MixChunk barrage = SDLMixer.loadWAV("testdata" + File.separator + "barrage.wav");
	    MixChunk bazooka = SDLMixer.loadWAV("testdata" + File.separator + "bazooka.wav");
	    MixChunk cannon  = SDLMixer.loadWAV("testdata" + File.separator + "cannon.wav");
	    
	    MixMusic symphony  = SDLMixer.loadMUS("testdata" + File.separator + "01-symphony_no40_in_g_minor_molto_allegro.ogg");

	    SDLMixer.playMusic(symphony, 0);

	    boolean loop = true;
	    
	    while (loop) {
		SDLEvent event = SDLEvent.waitEvent();
		if (event instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyEvent = (SDLKeyboardEvent)event;

		    if (keyEvent.getType() != SDLEventType.KEYDOWN) continue;
		    
		    switch (keyEvent.getSym()) {
			case SDLKey.SDLK_ESCAPE:
			    loop = false;
			    break;
			    
			case SDLKey.SDLK_q:
			    SDLMixer.playChannel(-1, magnum, 0);
			    break;
			case SDLKey.SDLK_w:
			    SDLMixer.playChannel(-1, mm, 0);
			    break;
			case SDLKey.SDLK_e:
			    SDLMixer.playChannel(-1, airnuke, 0);
			    break;
			case SDLKey.SDLK_r:
			    SDLMixer.playChannel(-1, ak, 0);
			    break;
			case SDLKey.SDLK_a:
			    SDLMixer.playChannel(-1, auto, 0);
			    break;
			case SDLKey.SDLK_s:
			    SDLMixer.playChannel(-1, bang, 0);
			    break;
			case SDLKey.SDLK_d:
			    SDLMixer.playChannel(-1, barrage, 0);
			    break;
			case SDLKey.SDLK_f:
			    SDLMixer.playChannel(-1, bazooka, 0);
			    break;
			case SDLKey.SDLK_x:
			    SDLMixer.playChannel(-1, cannon, 0);
			    break;

			case SDLKey.SDLK_SPACE:
			    if (symphonyPlaying) {
				symphonyPlaying = false;
				SDLMixer.haltMusic();
			    }
			    else {
				symphonyPlaying = true;
				SDLMixer.fadeInMusic(symphony, 0, 9000);
			    }
			    break;

			case SDLKey.SDLK_RIGHT:
			    System.out.println("Volume UP: volume=" + volume);
			    volume = SDLMixer.volume(-1, volume+5);
			    break;

			case SDLKey.SDLK_LEFT:
			    System.out.println("Volume DOWN: volume="+ volume);
			    volume = SDLMixer.volume(-1, volume-5);
			    break;

			case SDLKey.SDLK_p:
			    if (paused) {
				paused = false;
				SDLMixer.resume(-1);
			    }
			    else {
				paused = true;
				SDLMixer.pause(-1);
			    }
			    break;

			case SDLKey.SDLK_BACKSPACE:
			    SDLMixer.haltChannel(-1);
			    break;
		    }
		}
	    }
		
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}