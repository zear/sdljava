%module Glew
%{
#include "GL/glew.h"


GLenum SWIG_glew_init() {
    GLenum err = glewInit();
    if (GLEW_OK != err) {
      fprintf(stderr, "Error: %s\n", glewGetErrorString(err));
      exit(-1);
    }

    fprintf(stdout, "Status: Using GLEW %s\n", glewGetString(GLEW_VERSION));
    
    return err;
  }
%}

%pragma(java) jniclassclassmodifiers=%{public class%}

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null &&
	  System.getProperty("gljava.bootclasspath") == null) {
	System.loadLibrary("gljava");
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

%javaconst(1);
%import "typemaps.i"
%import "gljava_common_typemaps.i"

typedef unsigned int	GLenum;
typedef unsigned char	GLboolean;
typedef unsigned int	GLbitfield;
typedef void		GLvoid;
typedef signed char	GLbyte;		/* 1-byte signed */
typedef short		GLshort;	/* 2-byte signed */
typedef int		GLint;		/* 4-byte signed */
typedef unsigned char	GLubyte;	/* 1-byte unsigned */
typedef unsigned short	GLushort;	/* 2-byte unsigned */
typedef unsigned int	GLuint;		/* 4-byte unsigned */
typedef int		GLsizei;	/* 4-byte signed */
typedef float		GLfloat;	/* single precision float */
typedef float		GLclampf;	/* single precision float in [0,1] */
typedef double		GLdouble;	/* double precision float */
typedef double		GLclampd;	/* double precision float in [0,1] */

extern GLenum SWIG_glew_init();
 const GLubyte* glewGetErrorString (GLenum error);

////////////////////////////////////////////////////////////////////////////////
// OPENGL 1.0 and 1.1 FUNCTIONS
%include "opengl-1_1.i"

////////////////////////////////////////////////////////////////////////////////
// GLU
%include "glu.i"
