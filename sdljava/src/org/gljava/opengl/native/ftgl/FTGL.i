%module SWIG_FTGL
%{
    #include "FTFont.h"
    #include "FTFace.h"
    #include "FTGLBitmapFont.h"
    #include "FTGLExtrdFont.h"
    #include "FTGLOutlineFont.h"
    #include "FTGLPixmapFont.h"
    #include "FTGLPolygonFont.h"
    #include "FTGLTextureFont.h"

  void SWIG_BBox(const char* string, FTFont* font, float* llx, float* lly, float* llz, float* urx, float* ury, float* urz) {
    font->BBox(string, *llx, *lly, *llz, *urx, *ury, *urz);
  }
%}

%import "typemaps.i"

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null &&
	  System.getProperty("gljava.bootclasspath") == null) {

        System.loadLibrary("ftgljava");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("(ftgljava) Native code library failed to load. \n" + e);
      System.err.println("ftgljava methods will not be available.  Calling them will produce unwanted results.");
    }
  }
%}

typedef int FT_Error;

%include "FTFont.i"
%include "FTFace.i"
%include "FTGLBitmapFont.i"
%include "FTGLExtrdFont.i"
%include "FTGLOutlineFont.i"
%include "FTGLPixmapFont.i"
%include "FTGLPolygonFont.i"
%include "FTGLTextureFont.i"







