/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.gljava.opengl.ftgl;

public class FTGLPolygonFont extends FTFont {
  private long swigCPtr;

  protected FTGLPolygonFont(long cPtr, boolean cMemoryOwn) {
    super(SWIG_FTGLJNI.SWIGFTGLPolygonFontUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(FTGLPolygonFont obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected FTGLPolygonFont() {
    this(0, false);
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      SWIG_FTGLJNI.delete_FTGLPolygonFont(swigCPtr);
    }
    swigCPtr = 0;
    super.delete();
  }

  public FTGLPolygonFont(String fontFilePath) {
    this(SWIG_FTGLJNI.new_FTGLPolygonFont__SWIG_0(fontFilePath), true);
  }

  public FTGLPolygonFont(SWIGTYPE_p_unsigned_char pBufferBytes, int bufferSizeInBytes) {
    this(SWIG_FTGLJNI.new_FTGLPolygonFont__SWIG_1(SWIGTYPE_p_unsigned_char.getCPtr(pBufferBytes), bufferSizeInBytes), true);
  }


}