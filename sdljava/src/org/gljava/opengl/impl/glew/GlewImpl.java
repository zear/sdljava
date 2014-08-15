package org.gljava.opengl.impl.glew;

import org.gljava.opengl.GL;
import org.gljava.opengl.x.swig.GlewJNI;
import org.gljava.opengl.x.swig.GlewArraysJNI;

import org.gljava.opengl.glu.GLUnurbs;
import org.gljava.opengl.glu.GLUtesselator;
import org.gljava.opengl.glu.GLUquadric;

import java.nio.*;

/**
 * Implementation of GL via GLEW.
 *
 * SDLSurface will call GlewJNI.SWIG_glew_init() so there is no need to call the init() method
 * if you are using an SDLSurface.
 * <p>If you are using the library on its own make sure to call init <I>after</I> the GL
 * context is initialized but <I>before</I> doing anything with it.
 * <P>
 * 	Also see this <a href="http://sourceforge.net/mailarchive/forum.php?thread_id=8076809&forum_id=43388">thread</a> from the mailing list.</B>
 *
 * @author  Ivan Z. Ganza
 * @version $Id: GlewImpl.java,v 1.11 2005/09/25 17:47:18 ivan_ganza Exp $
 */
public final class GlewImpl implements GL {
	
    public final void init() {
        GlewJNI.SWIG_glew_init();
    }

    public final void glClearIndex(float c) {
	GlewJNI.glClearIndex(c);
    }

    public final void glClearColor(float red, float green, float blue, float alpha) {
	GlewJNI.glClearColor(red,green,blue,alpha);
    }

    public final void glClear(long mask) {
	GlewJNI.glClear(mask);
    }

    public final void glIndexMask(long mask) {
	GlewJNI.glIndexMask(mask);
    }

    public final void glColorMask(short red, short green, short blue, short alpha) {
	GlewJNI.glColorMask(red,green,blue,alpha);
    }

    public final void glAlphaFunc(long func, float ref) {
	GlewJNI.glAlphaFunc(func,ref);
    }

    public final void glBlendFunc(long sfactor, long dfactor) {
	GlewJNI.glBlendFunc(sfactor,dfactor);
    }

    public final void glLogicOp(long opcode) {
	GlewJNI.glLogicOp(opcode);
    }

    public final void glCullFace(long mode) {
	GlewJNI.glCullFace(mode);
    }

    public final void glFrontFace(long mode) {
	GlewJNI.glFrontFace(mode);
    }

    public final void glPointSize(float size) {
	GlewJNI.glPointSize(size);
    }

    public final void glLineWidth(float width) {
	GlewJNI.glLineWidth(width);
    }

    public final void glLineStipple(int factor, int pattern) {
	GlewJNI.glLineStipple(factor,pattern);
    }

    public final void glPolygonMode(long face, long mode) {
	GlewJNI.glPolygonMode(face,mode);
    }

    public final void glPolygonOffset(float factor, float units) {
	GlewJNI.glPolygonOffset(factor,units);
    }

    public final void glPolygonStipple(ShortBuffer mask) {
	GlewJNI.glPolygonStipple(mask);
    }

    public final void glGetPolygonStipple(ShortBuffer mask) {
	GlewJNI.glGetPolygonStipple(mask);
    }

    public final void glEdgeFlag(short flag) {
	GlewJNI.glEdgeFlag(flag);
    }

    public final void glEdgeFlagv(ShortBuffer flag) {
	GlewJNI.glEdgeFlagv(flag);
    }

    public final void glScissor(int x, int y, int width, int height) {
	GlewJNI.glScissor(x,y,width,height);
    }

    public final void glClipPlane(long plane, DoubleBuffer equation) {
	GlewJNI.glClipPlane(plane,equation);
    }

    public final void glGetClipPlane(long plane, DoubleBuffer equation) {
	GlewJNI.glGetClipPlane(plane,equation);
    }

    public final void glDrawBuffer(long mode) {
	GlewJNI.glDrawBuffer(mode);
    }

    public final void glReadBuffer(long mode) {
	GlewJNI.glReadBuffer(mode);
    }

    public final void glEnable(long cap) {
	GlewJNI.glEnable(cap);
    }

    public final void glDisable(long cap) {
	GlewJNI.glDisable(cap);
    }

    public final short glIsEnabled(long cap) {
	return GlewJNI.glIsEnabled(cap);
    }

    public final void glEnableClientState(long cap) {
	GlewJNI.glEnableClientState(cap);
    }

    public final void glDisableClientState(long cap) {
	GlewJNI.glDisableClientState(cap);
    }

    public final void glGetBooleanv(long pname, ShortBuffer params) {
	GlewJNI.glGetBooleanv(pname,params);
    }

    public final void glGetDoublev(long pname, DoubleBuffer params) {
	GlewJNI.glGetDoublev(pname,params);
    }

    public final void glGetFloatv(long pname, FloatBuffer params) {
	GlewJNI.glGetFloatv(pname,params);
    }

    public final void glGetIntegerv(long pname, IntBuffer params) {
	GlewJNI.glGetIntegerv(pname,params);
    }

    public final void glPushAttrib(long mask) {
	GlewJNI.glPushAttrib(mask);
    }

    public final void glPopAttrib() {
	GlewJNI.glPopAttrib();
    }
    
    public final void glPushClientAttrib(long mask) {
	GlewJNI.glPushClientAttrib(mask);
    }

    public final void glPopClientAttrib() {
	GlewJNI.glPopClientAttrib();
    }

    public final int glRenderMode(long mode) {
	return GlewJNI.glRenderMode(mode);
    }

    public final long glGetError() {
	return GlewJNI.glGetError();
    }

    public final ShortBuffer glGetString(long name) {
	return GlewJNI.glGetString(name);
    }
    
    public final void glFinish() {
	GlewJNI.glFinish();
    }
    public final void glFlush() {
	GlewJNI.glFlush();
    }
    
    public final void glHint(long target, long mode) {
	GlewJNI.glHint(target,mode);
    }

    public final void glClearDepth(double depth) {
	GlewJNI.glClearDepth(depth);
    }

    public final void glDepthFunc(long func) {
	GlewJNI.glDepthFunc(func);
    }

    public final void glDepthMask(short flag) {
	GlewJNI.glDepthMask(flag);
    }

    public final void glDepthRange(double near_val, double far_val) {
	GlewJNI.glDepthRange(near_val,far_val);
    }

    public final void glClearAccum(float red, float green, float blue, float alpha) {
	GlewJNI.glClearAccum(red,green,blue,alpha);
    }

    public final void glAccum(long op, float value) {
	GlewJNI.glAccum(op,value);
    }

    public final void glMatrixMode(long mode) {
	GlewJNI.glMatrixMode(mode);
    }

    public final void glOrtho(double left, double right, double bottom, double top, double near_val, double far_val) {
	GlewJNI.glOrtho(left,right,bottom,top,near_val,far_val);
    }

    public final void glFrustum(double left, double right, double bottom, double top, double near_val, double far_val) {
	GlewJNI.glFrustum(left,right,bottom,top,near_val,far_val);
    }

    public final void glViewport(int x, int y, int width, int height) {
	GlewJNI.glViewport(x,y,width,height);
    }

    public final void glPushMatrix() {
	GlewJNI.glPushMatrix();
    }
    
    public final void glPopMatrix() {
	GlewJNI.glPopMatrix();
    }
    public final void glLoadIdentity() {
	GlewJNI.glLoadIdentity();
    }
    
    public final void glLoadMatrixd(DoubleBuffer m) {
	GlewJNI.glLoadMatrixd(m);
    }

    public final void glLoadMatrixf(FloatBuffer m) {
	GlewJNI.glLoadMatrixf(m);
    }

    public final void glMultMatrixd(DoubleBuffer m) {
	GlewJNI.glMultMatrixd(m);
    }

    public final void glMultMatrixf(FloatBuffer m) {
	GlewJNI.glMultMatrixf(m);
    }

    public final void glRotated(double angle, double x, double y, double z) {
	GlewJNI.glRotated(angle,x,y,z);
    }

    public final void glRotatef(float angle, float x, float y, float z) {
	GlewJNI.glRotatef(angle,x,y,z);
    }

    public final void glScaled(double x, double y, double z) {
	GlewJNI.glScaled(x,y,z);
    }

    public final void glScalef(float x, float y, float z) {
	GlewJNI.glScalef(x,y,z);
    }

    public final void glTranslated(double x, double y, double z) {
	GlewJNI.glTranslated(x,y,z);
    }

    public final void glTranslatef(float x, float y, float z) {
	GlewJNI.glTranslatef(x,y,z);
    }

    public final short glIsList(long list) {
	return GlewJNI.glIsList(list);
    }

    public final void glDeleteLists(long list, int range) {
	GlewJNI.glDeleteLists(list,range);
    }

    public final long glGenLists(int range) {
	return GlewJNI.glGenLists(range);
    }

    public final void glNewList(long list, long mode) {
	GlewJNI.glNewList(list,mode);
    }

    public final void glEndList() {
	GlewJNI.glEndList();
    }
    
    public final void glCallList(long list) {
	GlewJNI.glCallList(list);
    }

    public final void glCallLists(int n, long type, Buffer lists) {
	GlewJNI.glCallLists(n,type,lists);
    }

    public final void glListBase(long base) {
	GlewJNI.glListBase(base);
    }

    public final void glBegin(long mode) {
	GlewJNI.glBegin(mode);
    }

    public final void glEnd() {
	GlewJNI.glEnd();
    }
    
    public final void glVertex2d(double x, double y) {
	GlewJNI.glVertex2d(x,y);
    }

    public final void glVertex2f(float x, float y) {
	GlewJNI.glVertex2f(x,y);
    }

    public final void glVertex2i(int x, int y) {
	GlewJNI.glVertex2i(x,y);
    }

    public final void glVertex2s(short x, short y) {
	GlewJNI.glVertex2s(x,y);
    }

    public final void glVertex3d(double x, double y, double z) {
	GlewJNI.glVertex3d(x,y,z);
    }

    public final void glVertex3f(float x, float y, float z) {
	GlewJNI.glVertex3f(x,y,z);
    }

    public final void glVertex3i(int x, int y, int z) {
	GlewJNI.glVertex3i(x,y,z);
    }

    public final void glVertex3s(short x, short y, short z) {
	GlewJNI.glVertex3s(x,y,z);
    }

    public final void glVertex4d(double x, double y, double z, double w) {
	GlewJNI.glVertex4d(x,y,z,w);
    }

    public final void glVertex4f(float x, float y, float z, float w) {
	GlewJNI.glVertex4f(x,y,z,w);
    }

    public final void glVertex4i(int x, int y, int z, int w) {
	GlewJNI.glVertex4i(x,y,z,w);
    }

    public final void glVertex4s(short x, short y, short z, short w) {
	GlewJNI.glVertex4s(x,y,z,w);
    }

    public final void glVertex2dv(DoubleBuffer v) {
	GlewJNI.glVertex2dv(v);
    }

    public final void glVertex2fv(FloatBuffer v) {
	GlewJNI.glVertex2fv(v);
    }

    public final void glVertex2iv(IntBuffer v) {
	GlewJNI.glVertex2iv(v);
    }

    public final void glVertex2sv(ShortBuffer v) {
	GlewJNI.glVertex2sv(v);
    }

    public final void glVertex3dv(DoubleBuffer v) {
	GlewJNI.glVertex3dv(v);
    }

    public final void glVertex3fv(FloatBuffer v) {
	GlewJNI.glVertex3fv(v);
    }

    public final void glVertex3iv(IntBuffer v) {
	GlewJNI.glVertex3iv(v);
    }

    public final void glVertex3sv(ShortBuffer v) {
	GlewJNI.glVertex3sv(v);
    }

    public final void glVertex4dv(DoubleBuffer v) {
	GlewJNI.glVertex4dv(v);
    }

    public final void glVertex4fv(FloatBuffer v) {
	GlewJNI.glVertex4fv(v);
    }

    public final void glVertex4iv(IntBuffer v) {
	GlewJNI.glVertex4iv(v);
    }

    public final void glVertex4sv(ShortBuffer v) {
	GlewJNI.glVertex4sv(v);
    }

    public final void glNormal3b(byte nx, byte ny, byte nz) {
	GlewJNI.glNormal3b(nx,ny,nz);
    }

    public final void glNormal3d(double nx, double ny, double nz) {
	GlewJNI.glNormal3d(nx,ny,nz);
    }

    public final void glNormal3f(float nx, float ny, float nz) {
	GlewJNI.glNormal3f(nx,ny,nz);
    }

    public final void glNormal3i(int nx, int ny, int nz) {
	GlewJNI.glNormal3i(nx,ny,nz);
    }

    public final void glNormal3s(short nx, short ny, short nz) {
	GlewJNI.glNormal3s(nx,ny,nz);
    }

    public final void glNormal3bv(ByteBuffer v) {
	GlewJNI.glNormal3bv(v);
    }

    public final void glNormal3dv(DoubleBuffer v) {
	GlewJNI.glNormal3dv(v);
    }

    public final void glNormal3fv(FloatBuffer v) {
	GlewJNI.glNormal3fv(v);
    }

    public final void glNormal3iv(IntBuffer v) {
	GlewJNI.glNormal3iv(v);
    }

    public final void glNormal3sv(ShortBuffer v) {
	GlewJNI.glNormal3sv(v);
    }

    public final void glIndexd(double c) {
	GlewJNI.glIndexd(c);
    }

    public final void glIndexf(float c) {
	GlewJNI.glIndexf(c);
    }

    public final void glIndexi(int c) {
	GlewJNI.glIndexi(c);
    }

    public final void glIndexs(short c) {
	GlewJNI.glIndexs(c);
    }

    public final void glIndexub(short c) {
	GlewJNI.glIndexub(c);
    }

    public final void glIndexdv(DoubleBuffer c) {
	GlewJNI.glIndexdv(c);
    }

    public final void glIndexfv(FloatBuffer c) {
	GlewJNI.glIndexfv(c);
    }

    public final void glIndexiv(IntBuffer c) {
	GlewJNI.glIndexiv(c);
    }

    public final void glIndexsv(ShortBuffer c) {
	GlewJNI.glIndexsv(c);
    }

    public final void glIndexubv(ShortBuffer c) {
	GlewJNI.glIndexubv(c);
    }

    public final void glColor3b(byte red, byte green, byte blue) {
	GlewJNI.glColor3b(red,green,blue);
    }

    public final void glColor3d(double red, double green, double blue) {
	GlewJNI.glColor3d(red,green,blue);
    }

    public final void glColor3f(float red, float green, float blue) {
	GlewJNI.glColor3f(red,green,blue);
    }

    public final void glColor3i(int red, int green, int blue) {
	GlewJNI.glColor3i(red,green,blue);
    }

    public final void glColor3s(short red, short green, short blue) {
	GlewJNI.glColor3s(red,green,blue);
    }

    public final void glColor3ub(short red, short green, short blue) {
	GlewJNI.glColor3ub(red,green,blue);
    }

    public final void glColor3ui(long red, long green, long blue) {
	GlewJNI.glColor3ui(red,green,blue);
    }

    public final void glColor3us(int red, int green, int blue) {
	GlewJNI.glColor3us(red,green,blue);
    }

    public final void glColor4b(byte red, byte green, byte blue, byte alpha) {
	GlewJNI.glColor4b(red,green,blue,alpha);
    }

    public final void glColor4d(double red, double green, double blue, double alpha) {
	GlewJNI.glColor4d(red,green,blue,alpha);
    }

    public final void glColor4f(float red, float green, float blue, float alpha) {
	GlewJNI.glColor4f(red,green,blue,alpha);
    }

    public final void glColor4i(int red, int green, int blue, int alpha) {
	GlewJNI.glColor4i(red,green,blue,alpha);
    }

    public final void glColor4s(short red, short green, short blue, short alpha) {
	GlewJNI.glColor4s(red,green,blue,alpha);
    }

    public final void glColor4ub(short red, short green, short blue, short alpha) {
	GlewJNI.glColor4ub(red,green,blue,alpha);
    }

    public final void glColor4ui(long red, long green, long blue, long alpha) {
	GlewJNI.glColor4ui(red,green,blue,alpha);
    }

    public final void glColor4us(int red, int green, int blue, int alpha) {
	GlewJNI.glColor4us(red,green,blue,alpha);
    }

    public final void glColor3bv(ByteBuffer v) {
	GlewJNI.glColor3bv(v);
    }

    public final void glColor3dv(DoubleBuffer v) {
	GlewJNI.glColor3dv(v);
    }

    public final void glColor3fv(FloatBuffer v) {
	GlewJNI.glColor3fv(v);
    }

    public final void glColor3iv(IntBuffer v) {
	GlewJNI.glColor3iv(v);
    }

    public final void glColor3sv(ShortBuffer v) {
	GlewJNI.glColor3sv(v);
    }

    public final void glColor3ubv(ShortBuffer v) {
	GlewJNI.glColor3ubv(v);
    }

    public final void glColor3uiv(IntBuffer v) {
	GlewJNI.glColor3uiv(v);
    }

    public final void glColor3usv(IntBuffer v) {
	GlewJNI.glColor3usv(v);
    }

    public final void glColor4bv(ByteBuffer v) {
	GlewJNI.glColor4bv(v);
    }

    public final void glColor4dv(DoubleBuffer v) {
	GlewJNI.glColor4dv(v);
    }

    public final void glColor4fv(FloatBuffer v) {
	GlewJNI.glColor4fv(v);
    }

    public final void glColor4iv(IntBuffer v) {
	GlewJNI.glColor4iv(v);
    }

    public final void glColor4sv(ShortBuffer v) {
	GlewJNI.glColor4sv(v);
    }

    public final void glColor4ubv(ShortBuffer v) {
	GlewJNI.glColor4ubv(v);
    }

    public final void glColor4uiv(IntBuffer v) {
	GlewJNI.glColor4uiv(v);
    }

    public final void glColor4usv(IntBuffer v) {
	GlewJNI.glColor4usv(v);
    }

    public final void glTexCoord1d(double s) {
	GlewJNI.glTexCoord1d(s);
    }

    public final void glTexCoord1f(float s) {
	GlewJNI.glTexCoord1f(s);
    }

    public final void glTexCoord1i(int s) {
	GlewJNI.glTexCoord1i(s);
    }

    public final void glTexCoord1s(short s) {
	GlewJNI.glTexCoord1s(s);
    }

    public final void glTexCoord2d(double s, double t) {
	GlewJNI.glTexCoord2d(s,t);
    }

    public final void glTexCoord2f(float s, float t) {
	GlewJNI.glTexCoord2f(s,t);
    }

    public final void glTexCoord2i(int s, int t) {
	GlewJNI.glTexCoord2i(s,t);
    }

    public final void glTexCoord2s(short s, short t) {
	GlewJNI.glTexCoord2s(s,t);
    }

    public final void glTexCoord3d(double s, double t, double r) {
	GlewJNI.glTexCoord3d(s,t,r);
    }

    public final void glTexCoord3f(float s, float t, float r) {
	GlewJNI.glTexCoord3f(s,t,r);
    }

    public final void glTexCoord3i(int s, int t, int r) {
	GlewJNI.glTexCoord3i(s,t,r);
    }

    public final void glTexCoord3s(short s, short t, short r) {
	GlewJNI.glTexCoord3s(s,t,r);
    }

    public final void glTexCoord4d(double s, double t, double r, double q) {
	GlewJNI.glTexCoord4d(s,t,r,q);
    }

    public final void glTexCoord4f(float s, float t, float r, float q) {
	GlewJNI.glTexCoord4f(s,t,r,q);
    }

    public final void glTexCoord4i(int s, int t, int r, int q) {
	GlewJNI.glTexCoord4i(s,t,r,q);
    }

    public final void glTexCoord4s(short s, short t, short r, short q) {
	GlewJNI.glTexCoord4s(s,t,r,q);
    }

    public final void glTexCoord1dv(DoubleBuffer v) {
	GlewJNI.glTexCoord1dv(v);
    }

    public final void glTexCoord1fv(FloatBuffer v) {
	GlewJNI.glTexCoord1fv(v);
    }

    public final void glTexCoord1iv(IntBuffer v) {
	GlewJNI.glTexCoord1iv(v);
    }

    public final void glTexCoord1sv(ShortBuffer v) {
	GlewJNI.glTexCoord1sv(v);
    }

    public final void glTexCoord2dv(DoubleBuffer v) {
	GlewJNI.glTexCoord2dv(v);
    }

    public final void glTexCoord2fv(FloatBuffer v) {
	GlewJNI.glTexCoord2fv(v);
    }

    public final void glTexCoord2iv(IntBuffer v) {
	GlewJNI.glTexCoord2iv(v);
    }

    public final void glTexCoord2sv(ShortBuffer v) {
	GlewJNI.glTexCoord2sv(v);
    }

    public final void glTexCoord3dv(DoubleBuffer v) {
	GlewJNI.glTexCoord3dv(v);
    }

    public final void glTexCoord3fv(FloatBuffer v) {
	GlewJNI.glTexCoord3fv(v);
    }

    public final void glTexCoord3iv(IntBuffer v) {
	GlewJNI.glTexCoord3iv(v);
    }

    public final void glTexCoord3sv(ShortBuffer v) {
	GlewJNI.glTexCoord3sv(v);
    }

    public final void glTexCoord4dv(DoubleBuffer v) {
	GlewJNI.glTexCoord4dv(v);
    }

    public final void glTexCoord4fv(FloatBuffer v) {
	GlewJNI.glTexCoord4fv(v);
    }

    public final void glTexCoord4iv(IntBuffer v) {
	GlewJNI.glTexCoord4iv(v);
    }

    public final void glTexCoord4sv(ShortBuffer v) {
	GlewJNI.glTexCoord4sv(v);
    }

    public final void glRasterPos2d(double x, double y) {
	GlewJNI.glRasterPos2d(x,y);
    }

    public final void glRasterPos2f(float x, float y) {
	GlewJNI.glRasterPos2f(x,y);
    }

    public final void glRasterPos2i(int x, int y) {
	GlewJNI.glRasterPos2i(x,y);
    }

    public final void glRasterPos2s(short x, short y) {
	GlewJNI.glRasterPos2s(x,y);
    }

    public final void glRasterPos3d(double x, double y, double z) {
	GlewJNI.glRasterPos3d(x,y,z);
    }

    public final void glRasterPos3f(float x, float y, float z) {
	GlewJNI.glRasterPos3f(x,y,z);
    }

    public final void glRasterPos3i(int x, int y, int z) {
	GlewJNI.glRasterPos3i(x,y,z);
    }

    public final void glRasterPos3s(short x, short y, short z) {
	GlewJNI.glRasterPos3s(x,y,z);
    }

    public final void glRasterPos4d(double x, double y, double z, double w) {
	GlewJNI.glRasterPos4d(x,y,z,w);
    }

    public final void glRasterPos4f(float x, float y, float z, float w) {
	GlewJNI.glRasterPos4f(x,y,z,w);
    }

    public final void glRasterPos4i(int x, int y, int z, int w) {
	GlewJNI.glRasterPos4i(x,y,z,w);
    }

    public final void glRasterPos4s(short x, short y, short z, short w) {
	GlewJNI.glRasterPos4s(x,y,z,w);
    }

    public final void glRasterPos2dv(DoubleBuffer v) {
	GlewJNI.glRasterPos2dv(v);
    }

    public final void glRasterPos2fv(FloatBuffer v) {
	GlewJNI.glRasterPos2fv(v);
    }

    public final void glRasterPos2iv(IntBuffer v) {
	GlewJNI.glRasterPos2iv(v);
    }

    public final void glRasterPos2sv(ShortBuffer v) {
	GlewJNI.glRasterPos2sv(v);
    }

    public final void glRasterPos3dv(DoubleBuffer v) {
	GlewJNI.glRasterPos3dv(v);
    }

    public final void glRasterPos3fv(FloatBuffer v) {
	GlewJNI.glRasterPos3fv(v);
    }

    public final void glRasterPos3iv(IntBuffer v) {
	GlewJNI.glRasterPos3iv(v);
    }

    public final void glRasterPos3sv(ShortBuffer v) {
	GlewJNI.glRasterPos3sv(v);
    }

    public final void glRasterPos4dv(DoubleBuffer v) {
	GlewJNI.glRasterPos4dv(v);
    }

    public final void glRasterPos4fv(FloatBuffer v) {
	GlewJNI.glRasterPos4fv(v);
    }

    public final void glRasterPos4iv(IntBuffer v) {
	GlewJNI.glRasterPos4iv(v);
    }

    public final void glRasterPos4sv(ShortBuffer v) {
	GlewJNI.glRasterPos4sv(v);
    }

    public final void glRectd(double x1, double y1, double x2, double y2) {
	GlewJNI.glRectd(x1,y1,x2,y2);
    }

    public final void glRectf(float x1, float y1, float x2, float y2) {
	GlewJNI.glRectf(x1,y1,x2,y2);
    }

    public final void glRecti(int x1, int y1, int x2, int y2) {
	GlewJNI.glRecti(x1,y1,x2,y2);
    }

    public final void glRects(short x1, short y1, short x2, short y2) {
	GlewJNI.glRects(x1,y1,x2,y2);
    }

    public final void glRectdv(DoubleBuffer v1, DoubleBuffer v2) {
	GlewJNI.glRectdv(v1,v2);
    }

    public final void glRectfv(FloatBuffer v1, FloatBuffer v2) {
	GlewJNI.glRectfv(v1,v2);
    }

    public final void glRectiv(IntBuffer v1, IntBuffer v2) {
	GlewJNI.glRectiv(v1,v2);
    }

    public final void glRectsv(ShortBuffer v1, ShortBuffer v2) {
	GlewJNI.glRectsv(v1,v2);
    }

    public final void glVertexPointer(int size, long type, int stride, Buffer ptr) {
	GlewJNI.glVertexPointer(size,type,stride,ptr);
    }

    public final void glNormalPointer(long type, int stride, Buffer ptr) {
	GlewJNI.glNormalPointer(type,stride,ptr);
    }

    public final void glColorPointer(int size, long type, int stride, Buffer ptr) {
	GlewJNI.glColorPointer(size,type,stride,ptr);
    }

    public final void glIndexPointer(long type, int stride, Buffer ptr) {
	GlewJNI.glIndexPointer(type,stride,ptr);
    }

    public final void glTexCoordPointer(int size, long type, int stride, Buffer ptr) {
	GlewJNI.glTexCoordPointer(size,type,stride,ptr);
    }

    public final void glEdgeFlagPointer(int stride, Buffer ptr) {
	GlewJNI.glEdgeFlagPointer(stride,ptr);
    }

    public final void glArrayElement(int i) {
	GlewJNI.glArrayElement(i);
    }

    public final void glDrawArrays(long mode, int first, int count) {
	GlewJNI.glDrawArrays(mode,first,count);
    }

    public final void glDrawElements(long mode, int count, long type, Buffer indices) {
	GlewJNI.glDrawElements(mode,count,type,indices);
    }

    public final void glInterleavedArrays(long format, int stride, Buffer pointer) {
	GlewJNI.glInterleavedArrays(format,stride,pointer);
    }

    public final void glShadeModel(long mode) {
	GlewJNI.glShadeModel(mode);
    }

    public final void glLightf(long light, long pname, float param) {
	GlewJNI.glLightf(light,pname,param);
    }

    public final void glLighti(long light, long pname, int param) {
	GlewJNI.glLighti(light,pname,param);
    }

    public final void glLightfv(long light, long pname, FloatBuffer params) {
	GlewJNI.glLightfv(light,pname,params);
    }

    public final void glLightiv(long light, long pname, IntBuffer params) {
	GlewJNI.glLightiv(light,pname,params);
    }

    public final void glGetLightfv(long light, long pname, FloatBuffer params) {
	GlewJNI.glGetLightfv(light,pname,params);
    }

    public final void glGetLightiv(long light, long pname, IntBuffer params) {
	GlewJNI.glGetLightiv(light,pname,params);
    }

    public final void glLightModelf(long pname, float param) {
	GlewJNI.glLightModelf(pname,param);
    }

    public final void glLightModeli(long pname, int param) {
	GlewJNI.glLightModeli(pname,param);
    }

    public final void glLightModelfv(long pname, FloatBuffer params) {
	GlewJNI.glLightModelfv(pname,params);
    }

    public final void glLightModeliv(long pname, IntBuffer params) {
	GlewJNI.glLightModeliv(pname,params);
    }

    public final void glMaterialf(long face, long pname, float param) {
	GlewJNI.glMaterialf(face,pname,param);
    }

    public final void glMateriali(long face, long pname, int param) {
	GlewJNI.glMateriali(face,pname,param);
    }

    public final void glMaterialfv(long face, long pname, FloatBuffer params) {
	GlewJNI.glMaterialfv(face,pname,params);
    }

    public final void glMaterialiv(long face, long pname, IntBuffer params) {
	GlewJNI.glMaterialiv(face,pname,params);
    }

    public final void glGetMaterialfv(long face, long pname, FloatBuffer params) {
	GlewJNI.glGetMaterialfv(face,pname,params);
    }

    public final void glGetMaterialiv(long face, long pname, IntBuffer params) {
	GlewJNI.glGetMaterialiv(face,pname,params);
    }

    public final void glColorMaterial(long face, long mode) {
	GlewJNI.glColorMaterial(face,mode);
    }

    public final void glPixelZoom(float xfactor, float yfactor) {
	GlewJNI.glPixelZoom(xfactor,yfactor);
    }

    public final void glPixelStoref(long pname, float param) {
	GlewJNI.glPixelStoref(pname,param);
    }

    public final void glPixelStorei(long pname, int param) {
	GlewJNI.glPixelStorei(pname,param);
    }

    public final void glPixelTransferf(long pname, float param) {
	GlewJNI.glPixelTransferf(pname,param);
    }

    public final void glPixelTransferi(long pname, int param) {
	GlewJNI.glPixelTransferi(pname,param);
    }

    public final void glPixelMapfv(long map, int mapsize, FloatBuffer values) {
	GlewJNI.glPixelMapfv(map,mapsize,values);
    }

    public final void glPixelMapuiv(long map, int mapsize, IntBuffer values) {
	GlewJNI.glPixelMapuiv(map,mapsize,values);
    }

    public final void glPixelMapusv(long map, int mapsize, IntBuffer values) {
	GlewJNI.glPixelMapusv(map,mapsize,values);
    }

    public final void glGetPixelMapfv(long map, FloatBuffer values) {
	GlewJNI.glGetPixelMapfv(map,values);
    }

    public final void glGetPixelMapuiv(long map, IntBuffer values) {
	GlewJNI.glGetPixelMapuiv(map,values);
    }

    public final void glGetPixelMapusv(long map, IntBuffer values) {
	GlewJNI.glGetPixelMapusv(map,values);
    }

    public final void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ShortBuffer bitmap) {
	GlewJNI.glBitmap(width,height,xorig,yorig,xmove,ymove,bitmap);
    }

    public final void glReadPixels(int x, int y, int width, int height, long format, long type, Buffer pixels) {
	GlewJNI.glReadPixels(x,y,width,height,format,type,pixels);
    }

    public final void glDrawPixels(int width, int height, long format, long type, Buffer pixels) {
	GlewJNI.glDrawPixels(width,height,format,type,pixels);
    }

    public final void glCopyPixels(int x, int y, int width, int height, long type) {
	GlewJNI.glCopyPixels(x,y,width,height,type);
    }

    public final void glStencilFunc(long func, int ref, long mask) {
	GlewJNI.glStencilFunc(func,ref,mask);
    }

    public final void glStencilMask(long mask) {
	GlewJNI.glStencilMask(mask);
    }

    public final void glStencilOp(long fail, long zfail, long zpass) {
	GlewJNI.glStencilOp(fail,zfail,zpass);
    }

    public final void glClearStencil(int s) {
	GlewJNI.glClearStencil(s);
    }

    public final void glTexGend(long coord, long pname, double param) {
	GlewJNI.glTexGend(coord,pname,param);
    }

    public final void glTexGenf(long coord, long pname, float param) {
	GlewJNI.glTexGenf(coord,pname,param);
    }

    public final void glTexGeni(long coord, long pname, int param) {
	GlewJNI.glTexGeni(coord,pname,param);
    }

    public final void glTexGendv(long coord, long pname, DoubleBuffer params) {
	GlewJNI.glTexGendv(coord,pname,params);
    }

    public final void glTexGenfv(long coord, long pname, FloatBuffer params) {
	GlewJNI.glTexGenfv(coord,pname,params);
    }

    public final void glTexGeniv(long coord, long pname, IntBuffer params) {
	GlewJNI.glTexGeniv(coord,pname,params);
    }

    public final void glGetTexGendv(long coord, long pname, DoubleBuffer params) {
	GlewJNI.glGetTexGendv(coord,pname,params);
    }

    public final void glGetTexGenfv(long coord, long pname, FloatBuffer params) {
	GlewJNI.glGetTexGenfv(coord,pname,params);
    }

    public final void glGetTexGeniv(long coord, long pname, IntBuffer params) {
	GlewJNI.glGetTexGeniv(coord,pname,params);
    }

    public final void glTexEnvf(long target, long pname, float param) {
	GlewJNI.glTexEnvf(target,pname,param);
    }

    public final void glTexEnvi(long target, long pname, int param) {
	GlewJNI.glTexEnvi(target,pname,param);
    }

    public final void glTexEnvfv(long target, long pname, FloatBuffer params) {
	GlewJNI.glTexEnvfv(target,pname,params);
    }

    public final void glTexEnviv(long target, long pname, IntBuffer params) {
	GlewJNI.glTexEnviv(target,pname,params);
    }

    public final void glGetTexEnvfv(long target, long pname, FloatBuffer params) {
	GlewJNI.glGetTexEnvfv(target,pname,params);
    }

    public final void glGetTexEnviv(long target, long pname, IntBuffer params) {
	GlewJNI.glGetTexEnviv(target,pname,params);
    }

    public final void glTexParameterf(long target, long pname, float param) {
	GlewJNI.glTexParameterf(target,pname,param);
    }

    public final void glTexParameteri(long target, long pname, int param) {
	GlewJNI.glTexParameteri(target,pname,param);
    }

    public final void glTexParameterfv(long target, long pname, FloatBuffer params) {
	GlewJNI.glTexParameterfv(target,pname,params);
    }

    public final void glTexParameteriv(long target, long pname, IntBuffer params) {
	GlewJNI.glTexParameteriv(target,pname,params);
    }

    public final void glGetTexParameterfv(long target, long pname, FloatBuffer params) {
	GlewJNI.glGetTexParameterfv(target,pname,params);
    }

    public final void glGetTexParameteriv(long target, long pname, IntBuffer params) {
	GlewJNI.glGetTexParameteriv(target,pname,params);
    }

    public final void glGetTexLevelParameterfv(long target, int level, long pname, FloatBuffer params) {
	GlewJNI.glGetTexLevelParameterfv(target,level,pname,params);
    }

    public final void glGetTexLevelParameteriv(long target, int level, long pname, IntBuffer params) {
	GlewJNI.glGetTexLevelParameteriv(target,level,pname,params);
    }

    public final void glTexImage1D(long target, int level, int internalFormat, int width, int border, long format, long type, Buffer pixels) {
	GlewJNI.glTexImage1D(target,level,internalFormat,width,border,format,type,pixels);
    }

    public final void glTexImage2D(long target, int level, int internalFormat, int width, int height, int border, long format, long type, Buffer pixels) {
	GlewJNI.glTexImage2D(target,level,internalFormat,width,height,border,format,type,pixels);
    }

    public final void glGetTexImage(long target, int level, long format, long type, Buffer pixels) {
	GlewJNI.glGetTexImage(target,level,format,type,pixels);
    }

    public final void glGenTextures(int n, IntBuffer textures) {
	GlewJNI.glGenTextures(n,textures);
    }

    public final void glDeleteTextures(int n, IntBuffer textures) {
	GlewJNI.glDeleteTextures(n,textures);
    }

    public final void glBindTexture(long target, long texture) {
	GlewJNI.glBindTexture(target,texture);
    }

    public final void glPrioritizeTextures(int n, IntBuffer textures, FloatBuffer priorities) {
	GlewJNI.glPrioritizeTextures(n,textures,priorities);
    }

    public final short glIsTexture(long texture) {
	return GlewJNI.glIsTexture(texture);
    }

    public final short glAreTexturesResident(int n, IntBuffer textures, ShortBuffer residences) {
	return GlewJNI.glAreTexturesResident(n, textures, residences);
    }

    public final void glTexSubImage1D(long target, int level, int xoffset, int width, long format, long type, Buffer pixels) {
	GlewJNI.glTexSubImage1D(target,level,xoffset,width,format,type,pixels);
    }

    public final void glTexSubImage2D(long target, int level, int xoffset, int yoffset, int width, int height, long format, long type, Buffer pixels) {
	GlewJNI.glTexSubImage2D(target,level,xoffset,yoffset,width,height,format,type,pixels);
    }

    public final void glCopyTexImage1D(long target, int level, long internalformat, int x, int y, int width, int border) {
	GlewJNI.glCopyTexImage1D(target,level,internalformat,x,y,width,border);
    }

    public final void glCopyTexImage2D(long target, int level, long internalformat, int x, int y, int width, int height, int border) {
	GlewJNI.glCopyTexImage2D(target,level,internalformat,x,y,width,height,border);
    }

    public final void glCopyTexSubImage1D(long target, int level, int xoffset, int x, int y, int width) {
	GlewJNI.glCopyTexSubImage1D(target,level,xoffset,x,y,width);
    }

    public final void glCopyTexSubImage2D(long target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
	GlewJNI.glCopyTexSubImage2D(target,level,xoffset,yoffset,x,y,width,height);
    }

    public final void glMap1d(long target, double u1, double u2, int stride, int order, DoubleBuffer points) {
	GlewJNI.glMap1d(target,u1,u2,stride,order,points);
    }

    public final void glMap1f(long target, float u1, float u2, int stride, int order, FloatBuffer points) {
	GlewJNI.glMap1f(target,u1,u2,stride,order,points);
    }

    public final void glMap2d(long target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) {
	GlewJNI.glMap2d(target,u1,u2,ustride,uorder,v1,v2,vstride,vorder,points);
    }

    public final void glMap2f(long target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
	GlewJNI.glMap2f(target,u1,u2,ustride,uorder,v1,v2,vstride,vorder,points);
    }

    public final void glGetMapdv(long target, long query, DoubleBuffer v) {
	GlewJNI.glGetMapdv(target,query,v);
    }

    public final void glGetMapfv(long target, long query, FloatBuffer v) {
	GlewJNI.glGetMapfv(target,query,v);
    }

    public final void glGetMapiv(long target, long query, IntBuffer v) {
	GlewJNI.glGetMapiv(target,query,v);
    }

    public final void glEvalCoord1d(double u) {
	GlewJNI.glEvalCoord1d(u);
    }

    public final void glEvalCoord1f(float u) {
	GlewJNI.glEvalCoord1f(u);
    }

    public final void glEvalCoord1dv(DoubleBuffer u) {
	GlewJNI.glEvalCoord1dv(u);
    }

    public final void glEvalCoord1fv(FloatBuffer u) {
	GlewJNI.glEvalCoord1fv(u);
    }

    public final void glEvalCoord2d(double u, double v) {
	GlewJNI.glEvalCoord2d(u,v);
    }

    public final void glEvalCoord2f(float u, float v) {
	GlewJNI.glEvalCoord2f(u,v);
    }

    public final void glEvalCoord2dv(DoubleBuffer u) {
	GlewJNI.glEvalCoord2dv(u);
    }

    public final void glEvalCoord2fv(FloatBuffer u) {
	GlewJNI.glEvalCoord2fv(u);
    }

    public final void glMapGrid1d(int un, double u1, double u2) {
	GlewJNI.glMapGrid1d(un,u1,u2);
    }

    public final void glMapGrid1f(int un, float u1, float u2) {
	GlewJNI.glMapGrid1f(un,u1,u2);
    }

    public final void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2) {
	GlewJNI.glMapGrid2d(un,u1,u2,vn,v1,v2);
    }

    public final void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
	GlewJNI.glMapGrid2f(un,u1,u2,vn,v1,v2);
    }

    public final void glEvalPoint1(int i) {
	GlewJNI.glEvalPoint1(i);
    }

    public final void glEvalPoint2(int i, int j) {
	GlewJNI.glEvalPoint2(i,j);
    }

    public final void glEvalMesh1(long mode, int i1, int i2) {
	GlewJNI.glEvalMesh1(mode,i1,i2);
    }

    public final void glEvalMesh2(long mode, int i1, int i2, int j1, int j2) {
	GlewJNI.glEvalMesh2(mode,i1,i2,j1,j2);
    }

    public final void glFogf(long pname, float param) {
	GlewJNI.glFogf(pname,param);
    }

    public final void glFogi(long pname, int param) {
	GlewJNI.glFogi(pname,param);
    }

    public final void glFogfv(long pname, FloatBuffer params) {
	GlewJNI.glFogfv(pname,params);
    }

    public final void glFogiv(long pname, IntBuffer params) {
	GlewJNI.glFogiv(pname,params);
    }

    public final void glFeedbackBuffer(int size, long type, FloatBuffer buffer) {
	GlewJNI.glFeedbackBuffer(size,type,buffer);
    }

    public final void glPassThrough(float token) {
	GlewJNI.glPassThrough(token);
    }

    public final void glSelectBuffer(int size, IntBuffer buffer) {
	GlewJNI.glSelectBuffer(size,buffer);
    }

    public final void glInitNames() {
	GlewJNI.glInitNames();
    }
    public final void glLoadName(long name) {
	GlewJNI.glLoadName(name);
    }

    public final void glPushName(long name) {
	GlewJNI.glPushName(name);
    }

    public final void glPopName() {
	GlewJNI.glPopName();
    }

    // 
    // NATIVE ARRAY ACCESS METHODS

    public final void glPolygonStipple(short[] mask) {
	GlewArraysJNI.glPolygonStipple(mask);
    }

    public final void glGetPolygonStipple(short[] mask) {
	GlewArraysJNI.glGetPolygonStipple(mask);
    }

    public final void glEdgeFlagv(short[] flag) {
	GlewArraysJNI.glEdgeFlagv(flag);
    }

    public final void glClipPlane(long plane, double[] equation) {
	GlewArraysJNI.glClipPlane(plane, equation);
    }

    public final void glGetClipPlane(long plane, double[] equation) {
	GlewArraysJNI.glGetClipPlane(plane, equation);
    }

    public final void glGetBooleanv(long pname, short[] params) {
	GlewArraysJNI.glGetBooleanv(pname, params);
    }

    public final void glGetDoublev(long pname, double[] params) {
	GlewArraysJNI.glGetDoublev(pname, params);
    }

    public final void glGetFloatv(long pname, float[] params) {
	GlewArraysJNI.glGetFloatv(pname, params);
    }

    public final void glGetIntegerv(long pname, int[] params) {
	GlewArraysJNI.glGetIntegerv(pname, params);
    }

    public final void glLoadMatrixd(double[] m) {
	GlewArraysJNI.glLoadMatrixd(m);
    }

    public final void glLoadMatrixf(float[] m) {
	GlewArraysJNI.glLoadMatrixf(m);
    }

    public final void glMultMatrixd(double[] m) {
	GlewArraysJNI.glMultMatrixd(m);
    }

    public final void glMultMatrixf(float[] m) {
	GlewArraysJNI.glMultMatrixf(m);
    }

    public final void glVertex2dv(double[] v) {
	GlewArraysJNI.glVertex2dv(v);
    }

    public final void glVertex2fv(float[] v) {
	GlewArraysJNI.glVertex2fv(v);
    }

    public final void glVertex2iv(int[] v) {
	GlewArraysJNI.glVertex2iv(v);
    }

    public final void glVertex2sv(short[] v) {
	GlewArraysJNI.glVertex2sv(v);
    }

    public final void glVertex3dv(double[] v) {
	GlewArraysJNI.glVertex3dv(v);
    }

    public final void glVertex3fv(float[] v) {
	GlewArraysJNI.glVertex3fv(v);
    }

    public final void glVertex3iv(int[] v) {
	GlewArraysJNI.glVertex3iv(v);
    }

    public final void glVertex3sv(short[] v) {
	GlewArraysJNI.glVertex3sv(v);
    }

    public final void glVertex4dv(double[] v) {
	GlewArraysJNI.glVertex4dv(v);
    }

    public final void glVertex4fv(float[] v) {
	GlewArraysJNI.glVertex4fv(v);
    }

    public final void glVertex4iv(int[] v) {
	GlewArraysJNI.glVertex4iv(v);
    }

    public final void glVertex4sv(short[] v) {
	GlewArraysJNI.glVertex4sv(v);
    }

    public final void glNormal3bv(byte[] v) {
	GlewArraysJNI.glNormal3bv(v);
    }

    public final void glNormal3dv(double[] v) {
	GlewArraysJNI.glNormal3dv(v);
    }

    public final void glNormal3fv(float[] v) {
	GlewArraysJNI.glNormal3fv(v);
    }

    public final void glNormal3iv(int[] v) {
	GlewArraysJNI.glNormal3iv(v);
    }

    public final void glNormal3sv(short[] v) {
	GlewArraysJNI.glNormal3sv(v);
    }

    public final void glIndexdv(double[] c) {
	GlewArraysJNI.glIndexdv(c);
    }

    public final void glIndexfv(float[] c) {
	GlewArraysJNI.glIndexfv(c);
    }

    public final void glIndexiv(int[] c) {
	GlewArraysJNI.glIndexiv(c);
    }

    public final void glIndexsv(short[] c) {
	GlewArraysJNI.glIndexsv(c);
    }

    public final void glIndexubv(short[] c) {
	GlewArraysJNI.glIndexubv(c);
    }

    public final void glColor3bv(byte[] v) {
	GlewArraysJNI.glColor3bv(v);
    }

    public final void glColor3dv(double[] v) {
	GlewArraysJNI.glColor3dv(v);
    }

    public final void glColor3fv(float[] v) {
	GlewArraysJNI.glColor3fv(v);
    }

    public final void glColor3iv(int[] v) {
	GlewArraysJNI.glColor3iv(v);
    }

    public final void glColor3sv(short[] v) {
	GlewArraysJNI.glColor3sv(v);
    }

    public final void glColor3ubv(short[] v) {
	GlewArraysJNI.glColor3ubv(v);
    }

    public final void glColor3uiv(long[] v) {
	GlewArraysJNI.glColor3uiv(v);
    }

    public final void glColor3usv(int[] v) {
	GlewArraysJNI.glColor3usv(v);
    }

    public final void glColor4bv(byte[] v) {
	GlewArraysJNI.glColor4bv(v);
    }

    public final void glColor4dv(double[] v) {
	GlewArraysJNI.glColor4dv(v);
    }

    public final void glColor4fv(float[] v) {
	GlewArraysJNI.glColor4fv(v);
    }

    public final void glColor4iv(int[] v) {
	GlewArraysJNI.glColor4iv(v);
    }

    public final void glColor4sv(short[] v) {
	GlewArraysJNI.glColor4sv(v);
    }

    public final void glColor4ubv(short[] v) {
	GlewArraysJNI.glColor4ubv(v);
    }

    public final void glColor4uiv(long[] v) {
	GlewArraysJNI.glColor4uiv(v);
    }

    public final void glColor4usv(int[] v) {
	GlewArraysJNI.glColor4usv(v);
    }

    public final void glTexCoord1dv(double[] v) {
	GlewArraysJNI.glTexCoord1dv(v);
    }

    public final void glTexCoord1fv(float[] v) {
	GlewArraysJNI.glTexCoord1fv(v);
    }

    public final void glTexCoord1iv(int[] v) {
	GlewArraysJNI.glTexCoord1iv(v);
    }

    public final void glTexCoord1sv(short[] v) {
	GlewArraysJNI.glTexCoord1sv(v);
    }

    public final void glTexCoord2dv(double[] v) {
	GlewArraysJNI.glTexCoord2dv(v);
    }

    public final void glTexCoord2fv(float[] v) {
	GlewArraysJNI.glTexCoord2fv(v);
    }

    public final void glTexCoord2iv(int[] v) {
	GlewArraysJNI.glTexCoord2iv(v);
    }

    public final void glTexCoord2sv(short[] v) {
	GlewArraysJNI.glTexCoord2sv(v);
    }

    public final void glTexCoord3dv(double[] v) {
	GlewArraysJNI.glTexCoord3dv(v);
    }

    public final void glTexCoord3fv(float[] v) {
	GlewArraysJNI.glTexCoord3fv(v);
    }

    public final void glTexCoord3iv(int[] v) {
	GlewArraysJNI.glTexCoord3iv(v);
    }

    public final void glTexCoord3sv(short[] v) {
	GlewArraysJNI.glTexCoord3sv(v);
    }

    public final void glTexCoord4dv(double[] v) {
	GlewArraysJNI.glTexCoord4dv(v);
    }

    public final void glTexCoord4fv(float[] v) {
	GlewArraysJNI.glTexCoord4fv(v);
    }

    public final void glTexCoord4iv(int[] v) {
	GlewArraysJNI.glTexCoord4iv(v);
    }

    public final void glTexCoord4sv(short[] v) {
	GlewArraysJNI.glTexCoord4sv(v);
    }

    public final void glRasterPos2dv(double[] v) {
	GlewArraysJNI.glRasterPos2dv(v);
    }

    public final void glRasterPos2fv(float[] v) {
	GlewArraysJNI.glRasterPos2fv(v);
    }

    public final void glRasterPos2iv(int[] v) {
	GlewArraysJNI.glRasterPos2iv(v);
    }

    public final void glRasterPos2sv(short[] v) {
	GlewArraysJNI.glRasterPos2sv(v);
    }

    public final void glRasterPos3dv(double[] v) {
	GlewArraysJNI.glRasterPos3dv(v);
    }

    public final void glRasterPos3fv(float[] v) {
	GlewArraysJNI.glRasterPos3fv(v);
    }

    public final void glRasterPos3iv(int[] v) {
	GlewArraysJNI.glRasterPos3iv(v);
    }

    public final void glRasterPos3sv(short[] v) {
	GlewArraysJNI.glRasterPos3sv(v);
    }

    public final void glRasterPos4dv(double[] v) {
	GlewArraysJNI.glRasterPos4dv(v);
    }

    public final void glRasterPos4fv(float[] v) {
	GlewArraysJNI.glRasterPos4fv(v);
    }

    public final void glRasterPos4iv(int[] v) {
	GlewArraysJNI.glRasterPos4iv(v);
    }

    public final void glRasterPos4sv(short[] v) {
	GlewArraysJNI.glRasterPos4sv(v);
    }

    public final void glRectdv(double[] v1, double[] v2) {
	GlewArraysJNI.glRectdv(v1, v2);
    }

    public final void glRectfv(float[] v1, float[] v2) {
	GlewArraysJNI.glRectfv(v1, v2);
    }

    public final void glRectiv(int[] v1, int[] v2) {
	GlewArraysJNI.glRectiv(v1, v2);
    }

    public final void glRectsv(short[] v1, short[] v2) {
	GlewArraysJNI.glRectsv(v1, v2);
    }

    public final void glLightfv(long light, long pname, float[] params) {
	GlewArraysJNI.glLightfv(light, pname, params);
    }

    public final void glLightiv(long light, long pname, int[] params) {
	GlewArraysJNI.glLightiv(light, pname, params);
    }

    public final void glGetLightfv(long light, long pname, float[] params) {
	GlewArraysJNI.glGetLightfv(light, pname, params);
    }

    public final void glGetLightiv(long light, long pname, int[] params) {
	GlewArraysJNI.glGetLightiv(light, pname, params);
    }

    public final void glLightModelfv(long pname, float[] params) {
	GlewArraysJNI.glLightModelfv(pname, params);
    }

    public final void glLightModeliv(long pname, int[] params) {
	GlewArraysJNI.glLightModeliv(pname, params);
    }

    public final void glMaterialfv(long face, long pname, float[] params) {
	GlewArraysJNI.glMaterialfv(face, pname, params);
    }

    public final void glMaterialiv(long face, long pname, int[] params) {
	GlewArraysJNI.glMaterialiv(face, pname, params);
    }

    public final void glGetMaterialfv(long face, long pname, float[] params) {
	GlewArraysJNI.glGetMaterialfv(face, pname, params);
    }

    public final void glGetMaterialiv(long face, long pname, int[] params) {
	GlewArraysJNI.glGetMaterialiv(face, pname, params);
    }

    public final void glPixelMapfv(long map, int mapsize, float[] values) {
	GlewArraysJNI.glPixelMapfv(map, mapsize, values);
    }

    public final void glPixelMapuiv(long map, int mapsize, long[] values) {
	GlewArraysJNI.glPixelMapuiv(map, mapsize, values);
    }

    public final void glPixelMapusv(long map, int mapsize, int[] values) {
	GlewArraysJNI.glPixelMapusv(map, mapsize, values);
    }

    public final void glGetPixelMapfv(long map, float[] values) {
	GlewArraysJNI.glGetPixelMapfv(map, values);
    }

    public final void glGetPixelMapuiv(long map, long[] values) {
	GlewArraysJNI.glGetPixelMapuiv(map, values);
    }

    public final void glGetPixelMapusv(long map, int[] values) {
	GlewArraysJNI.glGetPixelMapusv(map, values);
    }

    public final void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, short[] bitmap) {
	GlewArraysJNI.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
    }

    public final void glTexGendv(long coord, long pname, double[] params) {
	GlewArraysJNI.glTexGendv(coord, pname, params);
    }

    public final void glTexGenfv(long coord, long pname, float[] params) {
	GlewArraysJNI.glTexGenfv(coord, pname, params);
    }

    public final void glTexGeniv(long coord, long pname, int[] params) {
	GlewArraysJNI.glTexGeniv(coord, pname, params);
    }

    public final void glGetTexGendv(long coord, long pname, double[] params) {
	GlewArraysJNI.glGetTexGendv(coord, pname, params);
    }

    public final void glGetTexGenfv(long coord, long pname, float[] params) {
	GlewArraysJNI.glGetTexGenfv(coord, pname, params);
    }

    public final void glGetTexGeniv(long coord, long pname, int[] params) {
	GlewArraysJNI.glGetTexGeniv(coord, pname, params);
    }

    public final void glTexEnvfv(long target, long pname, float[] params) {
	GlewArraysJNI.glTexEnvfv(target, pname, params);
    }

    public final void glTexEnviv(long target, long pname, int[] params) {
	GlewArraysJNI.glTexEnviv(target, pname, params);
    }

    public final void glGetTexEnvfv(long target, long pname, float[] params) {
	GlewArraysJNI.glGetTexEnvfv(target, pname, params);
    }

    public final void glGetTexEnviv(long target, long pname, int[] params) {
	GlewArraysJNI.glGetTexEnviv(target, pname, params);
    }

    public final void glTexParameterfv(long target, long pname, float[] params) {
	GlewArraysJNI.glTexParameterfv(target, pname, params);
    }

    public final void glTexParameteriv(long target, long pname, int[] params) {
	GlewArraysJNI.glTexParameteriv(target, pname, params);
    }

    public final void glGetTexParameterfv(long target, long pname, float[] params) {
	GlewArraysJNI.glGetTexParameterfv(target, pname, params);
    }

    public final void glGetTexParameteriv(long target, long pname, int[] params) {
	GlewArraysJNI.glGetTexParameteriv(target, pname, params);
    }

    public final void glGetTexLevelParameterfv(long target, int level, long pname, float[] params) {
	GlewArraysJNI.glGetTexLevelParameterfv(target, level, pname, params);
    }

    public final void glGetTexLevelParameteriv(long target, int level, long pname, int[] params) {
	GlewArraysJNI.glGetTexLevelParameteriv(target, level, pname, params);
    }

    public final void glGenTextures(int n, long[] textures) {
	GlewArraysJNI.glGenTextures(n, textures);
    }

    public final void glDeleteTextures(int n, long[] textures) {
	GlewArraysJNI.glDeleteTextures(n, textures);
    }

    public final void glPrioritizeTextures(int n, long[] textures, float[] priorities) {
	GlewArraysJNI.glPrioritizeTextures(n, textures, priorities);
    }

    public final short glAreTexturesResident(int n, long[] textures, short[] residences) {
	return GlewArraysJNI.glAreTexturesResident(n, textures, residences);
    }

    public final void glMap1d(long target, double u1, double u2, int stride, int order, double[] points) {
	GlewArraysJNI.glMap1d(target, u1, u2, stride, order, points);
    }

    public final void glMap1f(long target, float u1, float u2, int stride, int order, float[] points) {
	GlewArraysJNI.glMap1f(target, u1, u2, stride, order, points);
    }

    public final void glMap2d(long target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, double[] points) {
	GlewArraysJNI.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    public final void glMap2f(long target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, float[] points) {
	GlewArraysJNI.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    public final void glGetMapdv(long target, long query, double[] v) {
	GlewArraysJNI.glGetMapdv(target, query, v);
    }

    public final void glGetMapfv(long target, long query, float[] v) {
	GlewArraysJNI.glGetMapfv(target, query, v);
    }

    public final void glGetMapiv(long target, long query, int[] v) {
	GlewArraysJNI.glGetMapiv(target, query, v);
    }

    public final void glEvalCoord1dv(double[] u) {
	GlewArraysJNI.glEvalCoord1dv(u);
    }

    public final void glEvalCoord1fv(float[] u) {
	GlewArraysJNI.glEvalCoord1fv(u);
    }

    public final void glEvalCoord2dv(double[] u) {
	GlewArraysJNI.glEvalCoord2dv(u);
    }

    public final void glEvalCoord2fv(float[] u) {
	GlewArraysJNI.glEvalCoord2fv(u);
    }

    public final void glFogfv(long pname, float[] params) {
	GlewArraysJNI.glFogfv(pname, params);
    }

    public final void glFogiv(long pname, int[] params) {
	GlewArraysJNI.glFogiv(pname, params);
    }

    public final void glFeedbackBuffer(int size, long type, float[] buffer) {
	GlewArraysJNI.glFeedbackBuffer(size, type, buffer);
    }

    public final void glSelectBuffer(int size, long[] buffer) {
	GlewArraysJNI.glSelectBuffer(size, buffer);
    }

    

    //////////////////////////////////////////////////////////////////////
    // GLU
    public final void gluBeginCurve(GLUnurbs nurb) {
	GlewJNI.gluBeginCurve(GLUnurbs.getCPtr(nurb));
    }

    public final void gluBeginPolygon(GLUtesselator tess) {
	GlewJNI.gluBeginPolygon(GLUtesselator.getCPtr(tess));
    }

    public final void gluBeginSurface(GLUnurbs nurb) {
	GlewJNI.gluBeginSurface(GLUnurbs.getCPtr(nurb));
    }

    public final void gluBeginTrim(GLUnurbs nurb) {
	GlewJNI.gluBeginTrim(GLUnurbs.getCPtr(nurb));
    }

    public final int gluBuild1DMipmaps(long target, int internalFormat, int width, long format, long type, Buffer data) {
	return GlewJNI.gluBuild1DMipmaps(target, internalFormat, width, format, type, data);
    }

    public final int gluBuild2DMipmaps(long target, int internalFormat, int width, int height, long format, long type, Buffer data) {
	return GlewJNI.gluBuild2DMipmaps(target, internalFormat, width, height, format, type, data);
    }

    public final void gluCylinder(GLUquadric quad, double base, double top, double height, int slices, int stacks) {
	GlewJNI.gluCylinder(GLUquadric.getCPtr(quad), base, top, height, slices, stacks);
    }

    public final void gluDeleteNurbsRenderer(GLUnurbs nurb) {
	GlewJNI.gluDeleteNurbsRenderer(GLUnurbs.getCPtr(nurb));
    }

    public final void gluDeleteQuadric(GLUquadric quad) {
	GlewJNI.gluDeleteQuadric(GLUquadric.getCPtr(quad));
    }

    public final void gluDeleteTess(GLUtesselator tess) {
	GlewJNI.gluDeleteTess(GLUtesselator.getCPtr(tess));
    }

    public final void gluDisk(GLUquadric quad, double inner, double outer, int slices, int loops) {
	GlewJNI.gluDisk(GLUquadric.getCPtr(quad), inner, outer, slices, loops);
    }

    public final void gluEndCurve(GLUnurbs nurb) {
	GlewJNI.gluEndCurve(GLUnurbs.getCPtr(nurb));
    }

    public final void gluEndPolygon(GLUtesselator tess) {
	GlewJNI.gluEndPolygon(GLUtesselator.getCPtr(tess));
    }

    public final void gluEndSurface(GLUnurbs nurb) {
	GlewJNI.gluEndSurface(GLUnurbs.getCPtr(nurb));
    }

    public final void gluEndTrim(GLUnurbs nurb) {
	GlewJNI.gluEndTrim(GLUnurbs.getCPtr(nurb));
    }

    public final ShortBuffer gluErrorString(long error) {
	return GlewJNI.gluErrorString(error);
    }

    public final void gluGetNurbsProperty(GLUnurbs nurb, long property, FloatBuffer data) {
	GlewJNI.gluGetNurbsProperty(GLUnurbs.getCPtr(nurb), property, data);
    }

    public final ShortBuffer gluGetString(long name) {
	return GlewJNI.gluGetString(name);
    }

    public final void gluGetTessProperty(GLUtesselator tess, long which, DoubleBuffer data) {
	GlewJNI.gluGetTessProperty(GLUtesselator.getCPtr(tess), which, data);
    }

    public final void gluLoadSamplingMatrices(GLUnurbs nurb, FloatBuffer model, FloatBuffer perspective, IntBuffer view) {
	GlewJNI.gluLoadSamplingMatrices(GLUnurbs.getCPtr(nurb), model, perspective, view);
    }

    public final void gluLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
	GlewJNI.gluLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public final GLUnurbs gluNewNurbsRenderer() {
	long cPtr = GlewJNI.gluNewNurbsRenderer();
	return (cPtr == 0) ? null : new GLUnurbs(cPtr, false);
    }

    public final GLUquadric gluNewQuadric() {
	long cPtr = GlewJNI.gluNewQuadric();
	return (cPtr == 0) ? null : new GLUquadric(cPtr, false);
    }

    public final GLUtesselator gluNewTess() {
	long cPtr = GlewJNI.gluNewTess();
	return (cPtr == 0) ? null : new GLUtesselator(cPtr, false);
    }

    public final void gluNextContour(GLUtesselator tess, long type) {
	GlewJNI.gluNextContour(GLUtesselator.getCPtr(tess), type);
    }

    //public final void gluNurbsCallback(GLUnurbs nurb, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc) {
    //    GlewJNI.gluNurbsCallback(GLUnurbs.getCPtr(nurb), which, SWIGTYPE_p__GLUfuncptr.getCPtr(CallBackFunc));
    //}

//    public final void gluNurbsCallbackData(GLUnurbs nurb, Buffer userData) {
//	GlewJNI.gluNurbsCallbackData(GLUnurbs.getCPtr(nurb), userData);
//    }
//
//    public final void gluNurbsCallbackDataEXT(GLUnurbs nurb, Buffer userData) {
//	GlewJNI.gluNurbsCallbackDataEXT(GLUnurbs.getCPtr(nurb), userData);
//    }

    public final void gluNurbsCurve(GLUnurbs nurb, int knotCount, FloatBuffer knots, int stride, FloatBuffer control, int order, long type) {
	GlewJNI.gluNurbsCurve(GLUnurbs.getCPtr(nurb), knotCount, knots, stride, control, order, type);
    }

    public final void gluNurbsProperty(GLUnurbs nurb, long property, float value) {
	GlewJNI.gluNurbsProperty(GLUnurbs.getCPtr(nurb), property, value);
    }

    public final void gluNurbsSurface(GLUnurbs nurb, int sKnotCount, FloatBuffer sKnots, int tKnotCount, FloatBuffer tKnots, int sStride, int tStride, FloatBuffer control, int sOrder, int tOrder, long type) {
	GlewJNI.gluNurbsSurface(GLUnurbs.getCPtr(nurb), sKnotCount, sKnots, tKnotCount, tKnots, sStride, tStride, control, sOrder, tOrder, type);
    }

    public final void gluOrtho2D(double left, double right, double bottom, double top) {
	GlewJNI.gluOrtho2D(left, right, bottom, top);
    }

    public final void gluPartialDisk(GLUquadric quad, double inner, double outer, int slices, int loops, double start, double sweep) {
	GlewJNI.gluPartialDisk(GLUquadric.getCPtr(quad), inner, outer, slices, loops, start, sweep);
    }

    public final void gluPerspective(double fovy, double aspect, double zNear, double zFar) {
	GlewJNI.gluPerspective(fovy, aspect, zNear, zFar);
    }

    public final void gluPickMatrix(double x, double y, double delX, double delY, IntBuffer viewport) {
	GlewJNI.gluPickMatrix(x, y, delX, delY, viewport);
    }

    public final int gluProject(double objX, double objY, double objZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer winX, DoubleBuffer winY, DoubleBuffer winZ) {
	return GlewJNI.gluProject(objX, objY, objZ, model, proj, view, winX, winY, winZ);
    }

    public final void gluPwlCurve(GLUnurbs nurb, int count, FloatBuffer data, int stride, long type) {
	GlewJNI.gluPwlCurve(GLUnurbs.getCPtr(nurb), count, data, stride, type);
    }

    //public final void gluQuadricCallback(GLUquadric quad, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc) {
    //    GlewJNI.gluQuadricCallback(GLUquadric.getCPtr(quad), which, SWIGTYPE_p__GLUfuncptr.getCPtr(CallBackFunc));
    //}

    public final void gluQuadricDrawStyle(GLUquadric quad, long draw) {
	GlewJNI.gluQuadricDrawStyle(GLUquadric.getCPtr(quad), draw);
    }

    public final void gluQuadricNormals(GLUquadric quad, long normal) {
	GlewJNI.gluQuadricNormals(GLUquadric.getCPtr(quad), normal);
    }

    public final void gluQuadricOrientation(GLUquadric quad, long orientation) {
	GlewJNI.gluQuadricOrientation(GLUquadric.getCPtr(quad), orientation);
    }

    public final void gluQuadricTexture(GLUquadric quad, short texture) {
	GlewJNI.gluQuadricTexture(GLUquadric.getCPtr(quad), texture);
    }

    public final int gluScaleImage(long format, int wIn, int hIn, long typeIn, Buffer dataIn, int wOut, int hOut, long typeOut, Buffer dataOut) {
	return GlewJNI.gluScaleImage(format, wIn, hIn, typeIn, dataIn, wOut, hOut, typeOut, dataOut);
    }

    public final void gluSphere(GLUquadric quad, double radius, int slices, int stacks) {
	GlewJNI.gluSphere(GLUquadric.getCPtr(quad), radius, slices, stacks);
    }

    public final void gluTessBeginContour(GLUtesselator tess) {
	GlewJNI.gluTessBeginContour(GLUtesselator.getCPtr(tess));
    }

    public final void gluTessBeginPolygon(GLUtesselator tess, Buffer data) {
	GlewJNI.gluTessBeginPolygon(GLUtesselator.getCPtr(tess), data);
    }

    //public final void gluTessCallback(GLUtesselator tess, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc) {
    //    GlewJNI.gluTessCallback(GLUtesselator.getCPtr(tess), which, SWIGTYPE_p__GLUfuncptr.getCPtr(CallBackFunc));
    //}

    public final void gluTessEndContour(GLUtesselator tess) {
	GlewJNI.gluTessEndContour(GLUtesselator.getCPtr(tess));
    }

    public final void gluTessEndPolygon(GLUtesselator tess) {
	GlewJNI.gluTessEndPolygon(GLUtesselator.getCPtr(tess));
    }

    public final void gluTessNormal(GLUtesselator tess, double valueX, double valueY, double valueZ) {
	GlewJNI.gluTessNormal(GLUtesselator.getCPtr(tess), valueX, valueY, valueZ);
    }

    public final void gluTessProperty(GLUtesselator tess, long which, double data) {
	GlewJNI.gluTessProperty(GLUtesselator.getCPtr(tess), which, data);
    }

    public final void gluTessVertex(GLUtesselator tess, DoubleBuffer location, Buffer data) {
	GlewJNI.gluTessVertex(GLUtesselator.getCPtr(tess), location, data);
    }

    public final int gluUnProject(double winX, double winY, double winZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer objX, DoubleBuffer objY, DoubleBuffer objZ) {
	return GlewJNI.gluUnProject(winX, winY, winZ, model, proj, view, objX, objY, objZ);
    }
}