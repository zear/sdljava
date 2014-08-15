%module SWIG_SDLImage
%{
  #include "SDL_image.h"

  SDL_Surface * SWIG_IMG_Load_Buffer(void* buf, int size) {
    return IMG_Load_RW(SDL_RWFromMem(buf, size), 1);
  }
%}

%javaconst(1);
%import "common.i"
%import "SDL_types.h"
%import "SDLVideo.i"

%pragma(java) jniclassimports=%{
import java.nio.*;
%}

%pragma(java) moduleimports=%{
import java.nio.*;
%}

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
        System.loadLibrary("sdljava_image");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

extern SDL_Surface * IMG_Load(const char *file);
extern SDL_Surface * SWIG_IMG_Load_Buffer(void* buf, int size);
