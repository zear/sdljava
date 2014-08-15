%module SWIG_SDLMixer
%{
  #include "SDL_mixer.h"

  SDL_version SWIG_MIX_VERSION() {
    SDL_version version;

    MIX_VERSION(&version);

    return version;
  }

  Mix_Chunk * SWIG_Mix_LoadWAV(const char * file) {
    return Mix_LoadWAV(file);
  }

  Mix_Chunk * SWIG_Mix_LoadWAV_Buffer(void* buf, int size) {
    return Mix_LoadWAV_RW(SDL_RWFromMem(buf, size), 1);
  }

  //Mix_Music * SWIG_Mix_LoadMUS_Buffer(void* buf, int size) {
  //  return Mix_LoadMUS_RW(SDL_RWFromMem(buf, size));
  //}

  int SWIG_Mix_PlayChannel(int channel, Mix_Chunk * chunk, int loops) {
    return Mix_PlayChannel(channel, chunk, loops);
  }

  int SWIG_Mix_FadeInChannel(int channel, Mix_Chunk *chunk, int loops, int ms) {
    return Mix_FadeInChannel(channel, chunk, loops, ms);
  }
%}

%javaconst(1);
%import "SDL_version.h"
%import "SDL_types.h"
%import "typemaps.i"

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
        System.loadLibrary("sdljava_mixer");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

%pragma(java) jniclassimports=%{
import java.nio.*;
%}

%pragma(java) moduleimports=%{
import java.nio.*;
%}

%typemap(in) (void *buf) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) void * "jobject"
%typemap(jtype) void * "Buffer"
%typemap(jstype) void *"Buffer"
%typemap(javain) void * "$javainput"


#if SDL_BYTEORDER == SDL_LIL_ENDIAN
#define MIX_DEFAULT_FORMAT	AUDIO_S16LSB
#else
#define MIX_DEFAULT_FORMAT	AUDIO_S16MSB
#endif
#define MIX_DEFAULT_CHANNELS	2
#define MIX_MAX_VOLUME		128	/* Volume of a chunk */

typedef struct {
	int allocated;
  //Uint8 *abuf;
	Uint32 alen;
	Uint8 volume;		/* Per-sample volume, 0-128 */
} Mix_Chunk;

/* The internal format for a music chunk interpreted via mikmod */
typedef struct _Mix_Music Mix_Music;

enum Mix_Fading {
	MIX_NO_FADING,
	MIX_FADING_OUT,
	MIX_FADING_IN
};

typedef enum {
	MUS_NONE,
	MUS_CMD,
	MUS_WAV,
	MUS_MOD,
	MUS_MID,
	MUS_OGG,
	MUS_MP3
} Mix_MusicType;

typedef struct _Mix_Music Mix_Music;

////////////////////////////////////////////////////////////////////////////////
// General
extern int Mix_OpenAudio(int frequency, Uint16 format, int channels, int chunksize);
extern void Mix_CloseAudio(void);
extern int  Mix_QuerySpec(int *OUTPUT,Uint16 *OUTPUT,int *OUTPUT);

////////////////////////////////////////////////////////////////////////////////
// Samples
extern Mix_Chunk * SWIG_Mix_LoadWAV(const char * file);
extern Mix_Chunk * SWIG_Mix_LoadWAV_Buffer(void* buf, int size);
extern int  Mix_VolumeChunk(Mix_Chunk *chunk, int volume);
extern void Mix_FreeChunk(Mix_Chunk *chunk);

////////////////////////////////////////////////////////////////////////////////
// Channels
extern int Mix_AllocateChannels(int numchans);
extern int Mix_Volume(int channel, int volume);
extern int SWIG_Mix_PlayChannel(int channel, Mix_Chunk * chunk, int loops);
extern int Mix_PlayChannelTimed(int channel, Mix_Chunk *chunk, int loops, int ticks);
extern int SWIG_Mix_FadeInChannel(int chunk, Mix_Chunk *chunk, int loops, int ms);
extern int Mix_FadeInChannelTimed(int channel, Mix_Chunk *chunk, int loops, int ms, int ticks);
extern void Mix_Pause(int channel);
extern void Mix_Resume(int channel);
extern int Mix_HaltChannel(int channel);
extern int Mix_ExpireChannel(int channel, int ticks);
extern int Mix_FadeOutChannel(int which, int ms);
//extern DECLSPEC void SDLCALL Mix_ChannelFinished(void (*channel_finished)(int channel));
extern int Mix_Playing(int channel);
extern int Mix_Paused(int channel);
extern Mix_Fading Mix_FadingChannel(int which);
extern Mix_Chunk * Mix_GetChunk(int channel);

////////////////////////////////////////////////////////////////////////////////
// Groups
extern int Mix_ReserveChannels(int num);
extern int Mix_GroupChannel(int which, int tag);
extern int Mix_GroupChannels(int from, int to, int tag);
extern int Mix_GroupCount(int tag);
extern int Mix_GroupAvailable(int tag);
extern int Mix_GroupOldest(int tag);
extern int Mix_GroupNewer(int tag);
extern int Mix_FadeOutGroup(int tag, int ms);
extern int Mix_HaltGroup(int tag);


////////////////////////////////////////////////////////////////////////////////
// Music
extern Mix_Music * Mix_LoadMUS(const char *file);
//extern Mix_Music * SWIG_Mix_LoadMUS_Buffer(void* buf, int size);
extern void Mix_FreeMusic(Mix_Music *music);
extern int Mix_PlayMusic(Mix_Music *music, int loops);
extern int Mix_FadeInMusic(Mix_Music *music, int loops, int ms);
extern int Mix_FadeInMusicPos(Mix_Music *music, int loops, int ms, double position);
//extern DECLSPEC void SDLCALL Mix_HookMusic(void (*mix_func)
//                          (void *udata, Uint8 *stream, int len), void *arg);
extern int Mix_VolumeMusic(int volume);
extern void Mix_PauseMusic(void);
extern void Mix_ResumeMusic(void);
extern void Mix_RewindMusic(void);
extern int Mix_SetMusicPosition(double position);
extern int Mix_SetMusicCMD(const char *command);
extern int Mix_HaltMusic(void);
extern int Mix_FadeOutMusic(int ms);
//extern DECLSPEC void SDLCALL Mix_HookMusicFinished(void (*music_finished)(void));
extern Mix_MusicType Mix_GetMusicType(const Mix_Music *music);
extern int Mix_PlayingMusic(void);
extern int Mix_PausedMusic(void);
extern Mix_Fading Mix_FadingMusic(void);
//extern DECLSPEC void * SDLCALL Mix_GetMusicHookData(void);

////////////////////////////////////////////////////////////////////////////////
// Effects
//extern int Mix_RegisterEffect(int chan, Mix_EffectFunc_t f, Mix_EffectDone_t d, void *arg);
//extern int Mix_UnregisterEffect(int channel, Mix_EffectFunc_t f);
//extern int Mix_UnregisterAllEffects(int channel);
//extern void Mix_SetPostMix(void (*mix_func) (void *udata, Uint8 *stream, int len), void *arg);
extern int Mix_SetPanning(int channel, Uint8 left, Uint8 right);
extern int Mix_SetDistance(int channel, Uint8 distance);
extern int Mix_SetPosition(int channel, Sint16 angle, Uint8 distance);
extern int Mix_SetReverseStereo(int channel, int flip);

extern SDL_version SWIG_MIX_VERSION();
