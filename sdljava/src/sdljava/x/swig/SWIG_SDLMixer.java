/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

import java.nio.*;

public class SWIG_SDLMixer implements SWIG_SDLMixerConstants {
  public static int Mix_OpenAudio(int frequency, int format, int channels, int chunksize) {
    return SWIG_SDLMixerJNI.Mix_OpenAudio(frequency, format, channels, chunksize);
  }

  public static void Mix_CloseAudio() {
    SWIG_SDLMixerJNI.Mix_CloseAudio();
  }

  public static int Mix_QuerySpec(int[] arg0, int[] arg1, int[] arg2) {
    return SWIG_SDLMixerJNI.Mix_QuerySpec(arg0, arg1, arg2);
  }

  public static Mix_Chunk SWIG_Mix_LoadWAV(String file) {
    long cPtr = SWIG_SDLMixerJNI.SWIG_Mix_LoadWAV(file);
    return (cPtr == 0) ? null : new Mix_Chunk(cPtr, false);
  }

  public static Mix_Chunk SWIG_Mix_LoadWAV_Buffer(Buffer buf, int size) {
    long cPtr = SWIG_SDLMixerJNI.SWIG_Mix_LoadWAV_Buffer(buf, size);
    return (cPtr == 0) ? null : new Mix_Chunk(cPtr, false);
  }

  public static int Mix_VolumeChunk(Mix_Chunk chunk, int volume) {
    return SWIG_SDLMixerJNI.Mix_VolumeChunk(Mix_Chunk.getCPtr(chunk), volume);
  }

  public static void Mix_FreeChunk(Mix_Chunk chunk) {
    SWIG_SDLMixerJNI.Mix_FreeChunk(Mix_Chunk.getCPtr(chunk));
  }

  public static int Mix_AllocateChannels(int numchans) {
    return SWIG_SDLMixerJNI.Mix_AllocateChannels(numchans);
  }

  public static int Mix_Volume(int channel, int volume) {
    return SWIG_SDLMixerJNI.Mix_Volume(channel, volume);
  }

  public static int SWIG_Mix_PlayChannel(int channel, Mix_Chunk chunk, int loops) {
    return SWIG_SDLMixerJNI.SWIG_Mix_PlayChannel(channel, Mix_Chunk.getCPtr(chunk), loops);
  }

  public static int Mix_PlayChannelTimed(int channel, Mix_Chunk chunk, int loops, int ticks) {
    return SWIG_SDLMixerJNI.Mix_PlayChannelTimed(channel, Mix_Chunk.getCPtr(chunk), loops, ticks);
  }

  public static int SWIG_Mix_FadeInChannel(int arg0, Mix_Chunk arg1, int loops, int ms) {
    return SWIG_SDLMixerJNI.SWIG_Mix_FadeInChannel(arg0, Mix_Chunk.getCPtr(arg1), loops, ms);
  }

  public static int Mix_FadeInChannelTimed(int channel, Mix_Chunk chunk, int loops, int ms, int ticks) {
    return SWIG_SDLMixerJNI.Mix_FadeInChannelTimed(channel, Mix_Chunk.getCPtr(chunk), loops, ms, ticks);
  }

  public static void Mix_Pause(int channel) {
    SWIG_SDLMixerJNI.Mix_Pause(channel);
  }

  public static void Mix_Resume(int channel) {
    SWIG_SDLMixerJNI.Mix_Resume(channel);
  }

  public static int Mix_HaltChannel(int channel) {
    return SWIG_SDLMixerJNI.Mix_HaltChannel(channel);
  }

  public static int Mix_ExpireChannel(int channel, int ticks) {
    return SWIG_SDLMixerJNI.Mix_ExpireChannel(channel, ticks);
  }

  public static int Mix_FadeOutChannel(int which, int ms) {
    return SWIG_SDLMixerJNI.Mix_FadeOutChannel(which, ms);
  }

  public static int Mix_Playing(int channel) {
    return SWIG_SDLMixerJNI.Mix_Playing(channel);
  }

  public static int Mix_Paused(int channel) {
    return SWIG_SDLMixerJNI.Mix_Paused(channel);
  }

  public static Mix_Fading Mix_FadingChannel(int which) {
    return Mix_Fading.swigToEnum(SWIG_SDLMixerJNI.Mix_FadingChannel(which));
  }

  public static Mix_Chunk Mix_GetChunk(int channel) {
    long cPtr = SWIG_SDLMixerJNI.Mix_GetChunk(channel);
    return (cPtr == 0) ? null : new Mix_Chunk(cPtr, false);
  }

  public static int Mix_ReserveChannels(int num) {
    return SWIG_SDLMixerJNI.Mix_ReserveChannels(num);
  }

  public static int Mix_GroupChannel(int which, int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupChannel(which, tag);
  }

  public static int Mix_GroupChannels(int from, int to, int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupChannels(from, to, tag);
  }

  public static int Mix_GroupCount(int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupCount(tag);
  }

  public static int Mix_GroupAvailable(int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupAvailable(tag);
  }

  public static int Mix_GroupOldest(int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupOldest(tag);
  }

  public static int Mix_GroupNewer(int tag) {
    return SWIG_SDLMixerJNI.Mix_GroupNewer(tag);
  }

  public static int Mix_FadeOutGroup(int tag, int ms) {
    return SWIG_SDLMixerJNI.Mix_FadeOutGroup(tag, ms);
  }

  public static int Mix_HaltGroup(int tag) {
    return SWIG_SDLMixerJNI.Mix_HaltGroup(tag);
  }

  public static SWIGTYPE_p__Mix_Music Mix_LoadMUS(String file) {
    long cPtr = SWIG_SDLMixerJNI.Mix_LoadMUS(file);
    return (cPtr == 0) ? null : new SWIGTYPE_p__Mix_Music(cPtr, false);
  }

  public static void Mix_FreeMusic(SWIGTYPE_p__Mix_Music music) {
    SWIG_SDLMixerJNI.Mix_FreeMusic(SWIGTYPE_p__Mix_Music.getCPtr(music));
  }

  public static int Mix_PlayMusic(SWIGTYPE_p__Mix_Music music, int loops) {
    return SWIG_SDLMixerJNI.Mix_PlayMusic(SWIGTYPE_p__Mix_Music.getCPtr(music), loops);
  }

  public static int Mix_FadeInMusic(SWIGTYPE_p__Mix_Music music, int loops, int ms) {
    return SWIG_SDLMixerJNI.Mix_FadeInMusic(SWIGTYPE_p__Mix_Music.getCPtr(music), loops, ms);
  }

  public static int Mix_FadeInMusicPos(SWIGTYPE_p__Mix_Music music, int loops, int ms, double position) {
    return SWIG_SDLMixerJNI.Mix_FadeInMusicPos(SWIGTYPE_p__Mix_Music.getCPtr(music), loops, ms, position);
  }

  public static int Mix_VolumeMusic(int volume) {
    return SWIG_SDLMixerJNI.Mix_VolumeMusic(volume);
  }

  public static void Mix_PauseMusic() {
    SWIG_SDLMixerJNI.Mix_PauseMusic();
  }

  public static void Mix_ResumeMusic() {
    SWIG_SDLMixerJNI.Mix_ResumeMusic();
  }

  public static void Mix_RewindMusic() {
    SWIG_SDLMixerJNI.Mix_RewindMusic();
  }

  public static int Mix_SetMusicPosition(double position) {
    return SWIG_SDLMixerJNI.Mix_SetMusicPosition(position);
  }

  public static int Mix_SetMusicCMD(String command) {
    return SWIG_SDLMixerJNI.Mix_SetMusicCMD(command);
  }

  public static int Mix_HaltMusic() {
    return SWIG_SDLMixerJNI.Mix_HaltMusic();
  }

  public static int Mix_FadeOutMusic(int ms) {
    return SWIG_SDLMixerJNI.Mix_FadeOutMusic(ms);
  }

  public static Mix_MusicType Mix_GetMusicType(SWIGTYPE_p__Mix_Music music) {
    return Mix_MusicType.swigToEnum(SWIG_SDLMixerJNI.Mix_GetMusicType(SWIGTYPE_p__Mix_Music.getCPtr(music)));
  }

  public static int Mix_PlayingMusic() {
    return SWIG_SDLMixerJNI.Mix_PlayingMusic();
  }

  public static int Mix_PausedMusic() {
    return SWIG_SDLMixerJNI.Mix_PausedMusic();
  }

  public static Mix_Fading Mix_FadingMusic() {
    return Mix_Fading.swigToEnum(SWIG_SDLMixerJNI.Mix_FadingMusic());
  }

  public static int Mix_SetPanning(int channel, short left, short right) {
    return SWIG_SDLMixerJNI.Mix_SetPanning(channel, left, right);
  }

  public static int Mix_SetDistance(int channel, short distance) {
    return SWIG_SDLMixerJNI.Mix_SetDistance(channel, distance);
  }

  public static int Mix_SetPosition(int channel, short angle, short distance) {
    return SWIG_SDLMixerJNI.Mix_SetPosition(channel, angle, distance);
  }

  public static int Mix_SetReverseStereo(int channel, int flip) {
    return SWIG_SDLMixerJNI.Mix_SetReverseStereo(channel, flip);
  }

  public static SDL_version SWIG_MIX_VERSION() {
    return new SDL_version(SWIG_SDLMixerJNI.SWIG_MIX_VERSION(), true);
  }

}
