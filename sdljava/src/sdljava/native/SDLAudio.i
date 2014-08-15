%module SWIG_SDLAudio
%{
#include "SDL_audio.h"

  //extern void (*callback)(void *userdata, Uint8 *stream, int len);
  //extern void * SDL_audioCallback;

  //  extern void SDL_audioCallback(void *user_data, Uint8 * audio, int length);
//  {
//    //    jclass cls = NULL;
//    //    jmethodID mid = NULL;
//    // 
//    //    JNIEnv *env = NULL;
//    //
//    //    (*cached_jvm)->AttachCurrentThread(cached_jvm,(void **)&env,NULL);
//    //    (*env)->CallVoidMethod(env,objCallback,midCallback,(int)audio,length);
//  }

  int SWIG_SDL_OpenAudio(SDL_AudioSpec * desired, SDL_AudioSpec * obtained) {
    //desired->callback=SDL_audioCallback;
    
    return SDL_OpenAudio(desired, obtained);
  }

//  SWIG_SDL_AudioObject * SWIG_SDL_LoadWAV(const char *file, SDL_AudioSpec *spec) {
//    SWIG_SDL_AudioObject * audioObject = malloc(sizeof(SWIG_SDL_AudioObject));
//
//    if (SDL_LoadWAV(file, spec, &audioObject->audioBuffer, &audioObject->audioLength) == NULL) return NULL;
//    
//    printf("SWIG_SDL_LoadWAV: audio_len=%i\n", audioObject->audioLength);
//
//    return audioObject;
//  }
%}

%javaconst(1);
%import "SDL_types.h"
%include "typemaps.i"

//typedef struct {
//  Uint8 * audioBuffer;
//  Uint32  audioLength;
//} SWIG_SDL_AudioObject;

/* The calculated values in this structure are calculated by SDL_OpenAudio() */
typedef struct {
	int freq;		/* DSP frequency -- samples per second */
	Uint16 format;		/* Audio data format */
	Uint8  channels;	/* Number of channels: 1 mono, 2 stereo */
	Uint8  silence;		/* Audio buffer silence value (calculated) */
	Uint16 samples;		/* Audio buffer size in samples (power of 2) */
	Uint16 padding;		/* Necessary for some compile environments */
	Uint32 size;		/* Audio buffer size in bytes (calculated) */
	/* This function is called when the audio device needs more data.
	   'stream' is a pointer to the audio data buffer
	   'len' is the length of that buffer in bytes.
	   Once the callback returns, the buffer will no longer be valid.
	   Stereo samples are stored in a LRLRLR ordering.
	*/
	void (*callback)(void *userdata, Uint8 *stream, int len);
	void  *userdata;
} SDL_AudioSpec;

/* Audio format flags (defaults to LSB byte order) */
#define AUDIO_U8	0x0008	/* Unsigned 8-bit samples */
#define AUDIO_S8	0x8008	/* Signed 8-bit samples */
#define AUDIO_U16LSB	0x0010	/* Unsigned 16-bit samples */
#define AUDIO_S16LSB	0x8010	/* Signed 16-bit samples */
#define AUDIO_U16MSB	0x1010	/* As above, but big-endian byte order */
#define AUDIO_S16MSB	0x9010	/* As above, but big-endian byte order */
#define AUDIO_U16	AUDIO_U16LSB
#define AUDIO_S16	AUDIO_S16LSB

#if SDL_BYTEORDER == SDL_LIL_ENDIAN
#define AUDIO_U16SYS	AUDIO_U16LSB
#define AUDIO_S16SYS	AUDIO_S16LSB
#else
#define AUDIO_U16SYS	AUDIO_U16MSB
#define AUDIO_S16SYS	AUDIO_S16MSB
#endif

//
///* A structure to hold a set of audio conversion filters and buffers */
//typedef struct SDL_AudioCVT {
//	int needed;			/* Set to 1 if conversion possible */
//	Uint16 src_format;		/* Source audio format */
//	Uint16 dst_format;		/* Target audio format */
//	double rate_incr;		/* Rate conversion increment */
//	Uint8 *buf;			/* Buffer to hold entire audio data */
//	int    len;			/* Length of original audio buffer */
//	int    len_cvt;			/* Length of converted audio buffer */
//	int    len_mult;		/* buffer must be len*len_mult big */
//	double len_ratio; 	/* Given len, final size is len*len_ratio */
//	void (*filters[10])(struct SDL_AudioCVT *cvt, Uint16 format);
//	int filter_index;		/* Current audio conversion function */
//} SDL_AudioCVT;
//
//extern char * SDL_AudioDriverName(char *namebuf, int maxlen);
//

extern int SWIG_SDL_OpenAudio(SDL_AudioSpec *desired, SDL_AudioSpec *obtained);
//extern SWIG_SDL_AudioObject *  SWIG_SDL_LoadWAV(const char *file, SDL_AudioSpec *spec);
extern void SDL_PauseAudio(int pause_on);

//
//typedef enum {
//	SDL_AUDIO_STOPPED = 0,
//	SDL_AUDIO_PLAYING,
//	SDL_AUDIO_PAUSED
//} SDL_audiostatus;
//extern SDL_audiostatus SDL_GetAudioStatus(void);
//
//extern void SDL_PauseAudio(int pause_on);
//
//extern void SDL_FreeWAV(Uint8 *audio_buf);
//
//extern int SDL_BuildAudioCVT(SDL_AudioCVT *cvt,
//		Uint16 src_format, Uint8 src_channels, int src_rate,
//		Uint16 dst_format, Uint8 dst_channels, int dst_rate);
//
//extern int SDL_ConvertAudio(SDL_AudioCVT *cvt);
//
//#define SDL_MIX_MAXVOLUME 128
//extern void SDL_MixAudio(Uint8 *dst, const Uint8 *src, Uint32 len, int volume);
//
//extern void SDL_LockAudio(void);
//extern void SDL_UnlockAudio(void);
//
//extern void SDL_CloseAudio(void);
