package org.gljava.opengl;

import org.gljava.opengl.glu.GLUnurbs;
import org.gljava.opengl.glu.GLUtesselator;
import org.gljava.opengl.glu.GLUquadric;

import org.gljava.opengl.x.swig.*;

import java.nio.*;
/**
 * <P> A <I>Composable</I> pipeline which wraps an underlying {@link GL} implementation.
 * Provides error checking after each OpenGL method call. If an error occurs,
 * causes a {@link GLException} to be thrown at exactly the point of failure.
 * To install this wrap an existing GL instance: </P>
 * <PRE>
 *     gl = new DebugGL(framebuffer.getGL());
 * </PRE>
 * @author  Ivan Z. Ganza
 * @version $Id: DebugGL.java,v 1.6 2005/09/25 17:47:18 ivan_ganza Exp $
 */
public class DebugGL implements GL {

        boolean insideBeginEndPair = false;
        GL      gl;

        public DebugGL(GL gl) {
            this.gl = gl;
        }

	public void glClearIndex(float c) {
		gl.glClearIndex(c);
	checkGLError("glClearIndex");

	}

	public void glClearColor(float red, float green, float blue, float alpha) {
		gl.glClearColor(red,green,blue,alpha);
	checkGLError("glClearColor");

	}

	public void glClear(long mask) {
		gl.glClear(mask);
	checkGLError("glClear");

	}

	public void glIndexMask(long mask) {
		gl.glIndexMask(mask);
	checkGLError("glIndexMask");

	}

	public void glColorMask(short red, short green, short blue, short alpha) {
		gl.glColorMask(red,green,blue,alpha);
	checkGLError("glColorMask");

	}

	public void glAlphaFunc(long func, float ref) {
		gl.glAlphaFunc(func,ref);
	checkGLError("glAlphaFunc");

	}

	public void glBlendFunc(long sfactor, long dfactor) {
		gl.glBlendFunc(sfactor,dfactor);
	checkGLError("glBlendFunc");

	}

	public void glLogicOp(long opcode) {
		gl.glLogicOp(opcode);
	checkGLError("glLogicOp");

	}

	public void glCullFace(long mode) {
		gl.glCullFace(mode);
	checkGLError("glCullFace");

	}

	public void glFrontFace(long mode) {
		gl.glFrontFace(mode);
	checkGLError("glFrontFace");

	}

	public void glPointSize(float size) {
		gl.glPointSize(size);
	checkGLError("glPointSize");

	}

	public void glLineWidth(float width) {
		gl.glLineWidth(width);
	checkGLError("glLineWidth");

	}

	public void glLineStipple(int factor, int pattern) {
		gl.glLineStipple(factor,pattern);
	checkGLError("glLineStipple");

	}

	public void glPolygonMode(long face, long mode) {
		gl.glPolygonMode(face,mode);
	checkGLError("glPolygonMode");

	}

	public void glPolygonOffset(float factor, float units) {
		gl.glPolygonOffset(factor,units);
	checkGLError("glPolygonOffset");

	}

	public void glPolygonStipple(ShortBuffer mask) {
		gl.glPolygonStipple(mask);
	checkGLError("glPolygonStipple");

	}

	public void glGetPolygonStipple(ShortBuffer mask) {
		gl.glGetPolygonStipple(mask);
	checkGLError("glGetPolygonStipple");

	}

	public void glEdgeFlag(short flag) {
		gl.glEdgeFlag(flag);
	checkGLError("glEdgeFlag");

	}

	public void glEdgeFlagv(ShortBuffer flag) {
		gl.glEdgeFlagv(flag);
	checkGLError("glEdgeFlagv");

	}

	public void glScissor(int x, int y, int width, int height) {
		gl.glScissor(x,y,width,height);
	checkGLError("glScissor");

	}

	public void glClipPlane(long plane, DoubleBuffer equation) {
		gl.glClipPlane(plane,equation);
	checkGLError("glClipPlane");

	}

	public void glGetClipPlane(long plane, DoubleBuffer equation) {
		gl.glGetClipPlane(plane,equation);
	checkGLError("glGetClipPlane");

	}

	public void glDrawBuffer(long mode) {
		gl.glDrawBuffer(mode);
	checkGLError("glDrawBuffer");

	}

	public void glReadBuffer(long mode) {
		gl.glReadBuffer(mode);
	checkGLError("glReadBuffer");

	}

	public void glEnable(long cap) {
		gl.glEnable(cap);
	checkGLError("glEnable");

	}

	public void glDisable(long cap) {
		gl.glDisable(cap);
	checkGLError("glDisable");

	}

	public short glIsEnabled(long cap) {
	short value = 	gl.glIsEnabled(cap);
	checkGLError("glIsEnabled");
		return value;

	}

	public void glEnableClientState(long cap) {
		gl.glEnableClientState(cap);
	checkGLError("glEnableClientState");

	}

	public void glDisableClientState(long cap) {
		gl.glDisableClientState(cap);
	checkGLError("glDisableClientState");

	}

	public void glGetBooleanv(long pname, ShortBuffer params) {
		gl.glGetBooleanv(pname,params);
	checkGLError("glGetBooleanv");

	}

	public void glGetDoublev(long pname, DoubleBuffer params) {
		gl.glGetDoublev(pname,params);
	checkGLError("glGetDoublev");

	}

	public void glGetFloatv(long pname, FloatBuffer params) {
		gl.glGetFloatv(pname,params);
	checkGLError("glGetFloatv");

	}

	public void glGetIntegerv(long pname, IntBuffer params) {
		gl.glGetIntegerv(pname,params);
	checkGLError("glGetIntegerv");

	}

	public void glPushAttrib(long mask) {
		gl.glPushAttrib(mask);
	checkGLError("glPushAttrib");

	}

	public void glPopAttrib() {
		gl.glPopAttrib();
	checkGLError("glPopAttrib");

	}

	public void glPushClientAttrib(long mask) {
		gl.glPushClientAttrib(mask);
	checkGLError("glPushClientAttrib");

	}

	public void glPopClientAttrib() {
		gl.glPopClientAttrib();
	checkGLError("glPopClientAttrib");

	}

	public int glRenderMode(long mode) {
	int value = 	gl.glRenderMode(mode);
	checkGLError("glRenderMode");
		return value;

	}

	public ShortBuffer glGetString(long name) {
	ShortBuffer value = 	gl.glGetString(name);
	checkGLError("glGetString");
		return value;

	}

	public void glFinish() {
		gl.glFinish();
	checkGLError("glFinish");

	}

	public void glFlush() {
		gl.glFlush();
	checkGLError("glFlush");

	}

	public void glHint(long target, long mode) {
		gl.glHint(target,mode);
	checkGLError("glHint");

	}

	public void glClearDepth(double depth) {
		gl.glClearDepth(depth);
	checkGLError("glClearDepth");

	}

	public void glDepthFunc(long func) {
		gl.glDepthFunc(func);
	checkGLError("glDepthFunc");

	}

	public void glDepthMask(short flag) {
		gl.glDepthMask(flag);
	checkGLError("glDepthMask");

	}

	public void glDepthRange(double near_val, double far_val) {
		gl.glDepthRange(near_val,far_val);
	checkGLError("glDepthRange");

	}

	public void glClearAccum(float red, float green, float blue, float alpha) {
		gl.glClearAccum(red,green,blue,alpha);
	checkGLError("glClearAccum");

	}

	public void glAccum(long op, float value) {
		gl.glAccum(op,value);
	checkGLError("glAccum");

	}

	public void glMatrixMode(long mode) {
		gl.glMatrixMode(mode);
	checkGLError("glMatrixMode");

	}

	public void glOrtho(double left, double right, double bottom, double top, double near_val, double far_val) {
		gl.glOrtho(left,right,bottom,top,near_val,far_val);
	checkGLError("glOrtho");

	}

	public void glFrustum(double left, double right, double bottom, double top, double near_val, double far_val) {
		gl.glFrustum(left,right,bottom,top,near_val,far_val);
	checkGLError("glFrustum");

	}

	public void glViewport(int x, int y, int width, int height) {
		gl.glViewport(x,y,width,height);
	checkGLError("glViewport");

	}

	public void glPushMatrix() {
		gl.glPushMatrix();
	checkGLError("glPushMatrix");

	}

	public void glPopMatrix() {
		gl.glPopMatrix();
	checkGLError("glPopMatrix");

	}

	public void glLoadIdentity() {
		gl.glLoadIdentity();
	checkGLError("glLoadIdentity");

	}

	public void glLoadMatrixd(DoubleBuffer m) {
		gl.glLoadMatrixd(m);
	checkGLError("glLoadMatrixd");

	}

	public void glLoadMatrixf(FloatBuffer m) {
		gl.glLoadMatrixf(m);
	checkGLError("glLoadMatrixf");

	}

	public void glMultMatrixd(DoubleBuffer m) {
		gl.glMultMatrixd(m);
	checkGLError("glMultMatrixd");

	}

	public void glMultMatrixf(FloatBuffer m) {
		gl.glMultMatrixf(m);
	checkGLError("glMultMatrixf");

	}

	public void glRotated(double angle, double x, double y, double z) {
		gl.glRotated(angle,x,y,z);
	checkGLError("glRotated");

	}

	public void glRotatef(float angle, float x, float y, float z) {
		gl.glRotatef(angle,x,y,z);
	checkGLError("glRotatef");

	}

	public void glScaled(double x, double y, double z) {
		gl.glScaled(x,y,z);
	checkGLError("glScaled");

	}

	public void glScalef(float x, float y, float z) {
		gl.glScalef(x,y,z);
	checkGLError("glScalef");

	}

	public void glTranslated(double x, double y, double z) {
		gl.glTranslated(x,y,z);
	checkGLError("glTranslated");

	}

	public void glTranslatef(float x, float y, float z) {
		gl.glTranslatef(x,y,z);
	checkGLError("glTranslatef");

	}

	public short glIsList(long list) {
	short value = 	gl.glIsList(list);
	checkGLError("glIsList");
		return value;

	}

	public void glDeleteLists(long list, int range) {
		gl.glDeleteLists(list,range);
	checkGLError("glDeleteLists");

	}

	public long glGenLists(int range) {
	long value = 	gl.glGenLists(range);
	checkGLError("glGenLists");
		return value;

	}

	public void glNewList(long list, long mode) {
		gl.glNewList(list,mode);
	checkGLError("glNewList");

	}

	public void glEndList() {
		gl.glEndList();
	insideBeginEndPair = false;

	checkGLError("glEndList");

	}

	public void glCallList(long list) {
		gl.glCallList(list);
	checkGLError("glCallList");

	}

	public void glCallLists(int n, long type, Buffer lists) {
		gl.glCallLists(n,type,lists);
	checkGLError("glCallLists");

	}

	public void glListBase(long base) {
		gl.glListBase(base);
	checkGLError("glListBase");

	}

	public void glBegin(long mode) {
		gl.glBegin(mode);
	insideBeginEndPair = true;

	checkGLError("glBegin");

	}

	public void glEnd() {
		gl.glEnd();
	insideBeginEndPair = false;

	checkGLError("glEnd");

	}

	public void glVertex2d(double x, double y) {
		gl.glVertex2d(x,y);
	checkGLError("glVertex2d");

	}

	public void glVertex2f(float x, float y) {
		gl.glVertex2f(x,y);
	checkGLError("glVertex2f");

	}

	public void glVertex2i(int x, int y) {
		gl.glVertex2i(x,y);
	checkGLError("glVertex2i");

	}

	public void glVertex2s(short x, short y) {
		gl.glVertex2s(x,y);
	checkGLError("glVertex2s");

	}

	public void glVertex3d(double x, double y, double z) {
		gl.glVertex3d(x,y,z);
	checkGLError("glVertex3d");

	}

	public void glVertex3f(float x, float y, float z) {
		gl.glVertex3f(x,y,z);
	checkGLError("glVertex3f");

	}

	public void glVertex3i(int x, int y, int z) {
		gl.glVertex3i(x,y,z);
	checkGLError("glVertex3i");

	}

	public void glVertex3s(short x, short y, short z) {
		gl.glVertex3s(x,y,z);
	checkGLError("glVertex3s");

	}

	public void glVertex4d(double x, double y, double z, double w) {
		gl.glVertex4d(x,y,z,w);
	checkGLError("glVertex4d");

	}

	public void glVertex4f(float x, float y, float z, float w) {
		gl.glVertex4f(x,y,z,w);
	checkGLError("glVertex4f");

	}

	public void glVertex4i(int x, int y, int z, int w) {
		gl.glVertex4i(x,y,z,w);
	checkGLError("glVertex4i");

	}

	public void glVertex4s(short x, short y, short z, short w) {
		gl.glVertex4s(x,y,z,w);
	checkGLError("glVertex4s");

	}

	public void glVertex2dv(DoubleBuffer v) {
		gl.glVertex2dv(v);
	checkGLError("glVertex2dv");

	}

	public void glVertex2fv(FloatBuffer v) {
		gl.glVertex2fv(v);
	checkGLError("glVertex2fv");

	}

	public void glVertex2iv(IntBuffer v) {
		gl.glVertex2iv(v);
	checkGLError("glVertex2iv");

	}

	public void glVertex2sv(ShortBuffer v) {
		gl.glVertex2sv(v);
	checkGLError("glVertex2sv");

	}

	public void glVertex3dv(DoubleBuffer v) {
		gl.glVertex3dv(v);
	checkGLError("glVertex3dv");

	}

	public void glVertex3fv(FloatBuffer v) {
		gl.glVertex3fv(v);
	checkGLError("glVertex3fv");

	}

	public void glVertex3iv(IntBuffer v) {
		gl.glVertex3iv(v);
	checkGLError("glVertex3iv");

	}

	public void glVertex3sv(ShortBuffer v) {
		gl.glVertex3sv(v);
	checkGLError("glVertex3sv");

	}

	public void glVertex4dv(DoubleBuffer v) {
		gl.glVertex4dv(v);
	checkGLError("glVertex4dv");

	}

	public void glVertex4fv(FloatBuffer v) {
		gl.glVertex4fv(v);
	checkGLError("glVertex4fv");

	}

	public void glVertex4iv(IntBuffer v) {
		gl.glVertex4iv(v);
	checkGLError("glVertex4iv");

	}

	public void glVertex4sv(ShortBuffer v) {
		gl.glVertex4sv(v);
	checkGLError("glVertex4sv");

	}

	public void glNormal3b(byte nx, byte ny, byte nz) {
		gl.glNormal3b(nx,ny,nz);
	checkGLError("glNormal3b");

	}

	public void glNormal3d(double nx, double ny, double nz) {
		gl.glNormal3d(nx,ny,nz);
	checkGLError("glNormal3d");

	}

	public void glNormal3f(float nx, float ny, float nz) {
		gl.glNormal3f(nx,ny,nz);
	checkGLError("glNormal3f");

	}

	public void glNormal3i(int nx, int ny, int nz) {
		gl.glNormal3i(nx,ny,nz);
	checkGLError("glNormal3i");

	}

	public void glNormal3s(short nx, short ny, short nz) {
		gl.glNormal3s(nx,ny,nz);
	checkGLError("glNormal3s");

	}

	public void glNormal3bv(ByteBuffer v) {
		gl.glNormal3bv(v);
	checkGLError("glNormal3bv");

	}

	public void glNormal3dv(DoubleBuffer v) {
		gl.glNormal3dv(v);
	checkGLError("glNormal3dv");

	}

	public void glNormal3fv(FloatBuffer v) {
		gl.glNormal3fv(v);
	checkGLError("glNormal3fv");

	}

	public void glNormal3iv(IntBuffer v) {
		gl.glNormal3iv(v);
	checkGLError("glNormal3iv");

	}

	public void glNormal3sv(ShortBuffer v) {
		gl.glNormal3sv(v);
	checkGLError("glNormal3sv");

	}

	public void glIndexd(double c) {
		gl.glIndexd(c);
	checkGLError("glIndexd");

	}

	public void glIndexf(float c) {
		gl.glIndexf(c);
	checkGLError("glIndexf");

	}

	public void glIndexi(int c) {
		gl.glIndexi(c);
	checkGLError("glIndexi");

	}

	public void glIndexs(short c) {
		gl.glIndexs(c);
	checkGLError("glIndexs");

	}

	public void glIndexub(short c) {
		gl.glIndexub(c);
	checkGLError("glIndexub");

	}

	public void glIndexdv(DoubleBuffer c) {
		gl.glIndexdv(c);
	checkGLError("glIndexdv");

	}

	public void glIndexfv(FloatBuffer c) {
		gl.glIndexfv(c);
	checkGLError("glIndexfv");

	}

	public void glIndexiv(IntBuffer c) {
		gl.glIndexiv(c);
	checkGLError("glIndexiv");

	}

	public void glIndexsv(ShortBuffer c) {
		gl.glIndexsv(c);
	checkGLError("glIndexsv");

	}

	public void glIndexubv(ShortBuffer c) {
		gl.glIndexubv(c);
	checkGLError("glIndexubv");

	}

	public void glColor3b(byte red, byte green, byte blue) {
		gl.glColor3b(red,green,blue);
	checkGLError("glColor3b");

	}

	public void glColor3d(double red, double green, double blue) {
		gl.glColor3d(red,green,blue);
	checkGLError("glColor3d");

	}

	public void glColor3f(float red, float green, float blue) {
		gl.glColor3f(red,green,blue);
	checkGLError("glColor3f");

	}

	public void glColor3i(int red, int green, int blue) {
		gl.glColor3i(red,green,blue);
	checkGLError("glColor3i");

	}

	public void glColor3s(short red, short green, short blue) {
		gl.glColor3s(red,green,blue);
	checkGLError("glColor3s");

	}

	public void glColor3ub(short red, short green, short blue) {
		gl.glColor3ub(red,green,blue);
	checkGLError("glColor3ub");

	}

	public void glColor3ui(long red, long green, long blue) {
		gl.glColor3ui(red,green,blue);
	checkGLError("glColor3ui");

	}

	public void glColor3us(int red, int green, int blue) {
		gl.glColor3us(red,green,blue);
	checkGLError("glColor3us");

	}

	public void glColor4b(byte red, byte green, byte blue, byte alpha) {
		gl.glColor4b(red,green,blue,alpha);
	checkGLError("glColor4b");

	}

	public void glColor4d(double red, double green, double blue, double alpha) {
		gl.glColor4d(red,green,blue,alpha);
	checkGLError("glColor4d");

	}

	public void glColor4f(float red, float green, float blue, float alpha) {
		gl.glColor4f(red,green,blue,alpha);
	checkGLError("glColor4f");

	}

	public void glColor4i(int red, int green, int blue, int alpha) {
		gl.glColor4i(red,green,blue,alpha);
	checkGLError("glColor4i");

	}

	public void glColor4s(short red, short green, short blue, short alpha) {
		gl.glColor4s(red,green,blue,alpha);
	checkGLError("glColor4s");

	}

	public void glColor4ub(short red, short green, short blue, short alpha) {
		gl.glColor4ub(red,green,blue,alpha);
	checkGLError("glColor4ub");

	}

	public void glColor4ui(long red, long green, long blue, long alpha) {
		gl.glColor4ui(red,green,blue,alpha);
	checkGLError("glColor4ui");

	}

	public void glColor4us(int red, int green, int blue, int alpha) {
		gl.glColor4us(red,green,blue,alpha);
	checkGLError("glColor4us");

	}

	public void glColor3bv(ByteBuffer v) {
		gl.glColor3bv(v);
	checkGLError("glColor3bv");

	}

	public void glColor3dv(DoubleBuffer v) {
		gl.glColor3dv(v);
	checkGLError("glColor3dv");

	}

	public void glColor3fv(FloatBuffer v) {
		gl.glColor3fv(v);
	checkGLError("glColor3fv");

	}

	public void glColor3iv(IntBuffer v) {
		gl.glColor3iv(v);
	checkGLError("glColor3iv");

	}

	public void glColor3sv(ShortBuffer v) {
		gl.glColor3sv(v);
	checkGLError("glColor3sv");

	}

	public void glColor3ubv(ShortBuffer v) {
		gl.glColor3ubv(v);
	checkGLError("glColor3ubv");

	}

	public void glColor3uiv(IntBuffer v) {
		gl.glColor3uiv(v);
	checkGLError("glColor3uiv");

	}

	public void glColor3usv(IntBuffer v) {
		gl.glColor3usv(v);
	checkGLError("glColor3usv");

	}

	public void glColor4bv(ByteBuffer v) {
		gl.glColor4bv(v);
	checkGLError("glColor4bv");

	}

	public void glColor4dv(DoubleBuffer v) {
		gl.glColor4dv(v);
	checkGLError("glColor4dv");

	}

	public void glColor4fv(FloatBuffer v) {
		gl.glColor4fv(v);
	checkGLError("glColor4fv");

	}

	public void glColor4iv(IntBuffer v) {
		gl.glColor4iv(v);
	checkGLError("glColor4iv");

	}

	public void glColor4sv(ShortBuffer v) {
		gl.glColor4sv(v);
	checkGLError("glColor4sv");

	}

	public void glColor4ubv(ShortBuffer v) {
		gl.glColor4ubv(v);
	checkGLError("glColor4ubv");

	}

	public void glColor4uiv(IntBuffer v) {
		gl.glColor4uiv(v);
	checkGLError("glColor4uiv");

	}

	public void glColor4usv(IntBuffer v) {
		gl.glColor4usv(v);
	checkGLError("glColor4usv");

	}

	public void glTexCoord1d(double s) {
		gl.glTexCoord1d(s);
	checkGLError("glTexCoord1d");

	}

	public void glTexCoord1f(float s) {
		gl.glTexCoord1f(s);
	checkGLError("glTexCoord1f");

	}

	public void glTexCoord1i(int s) {
		gl.glTexCoord1i(s);
	checkGLError("glTexCoord1i");

	}

	public void glTexCoord1s(short s) {
		gl.glTexCoord1s(s);
	checkGLError("glTexCoord1s");

	}

	public void glTexCoord2d(double s, double t) {
		gl.glTexCoord2d(s,t);
	checkGLError("glTexCoord2d");

	}

	public void glTexCoord2f(float s, float t) {
		gl.glTexCoord2f(s,t);
	checkGLError("glTexCoord2f");

	}

	public void glTexCoord2i(int s, int t) {
		gl.glTexCoord2i(s,t);
	checkGLError("glTexCoord2i");

	}

	public void glTexCoord2s(short s, short t) {
		gl.glTexCoord2s(s,t);
	checkGLError("glTexCoord2s");

	}

	public void glTexCoord3d(double s, double t, double r) {
		gl.glTexCoord3d(s,t,r);
	checkGLError("glTexCoord3d");

	}

	public void glTexCoord3f(float s, float t, float r) {
		gl.glTexCoord3f(s,t,r);
	checkGLError("glTexCoord3f");

	}

	public void glTexCoord3i(int s, int t, int r) {
		gl.glTexCoord3i(s,t,r);
	checkGLError("glTexCoord3i");

	}

	public void glTexCoord3s(short s, short t, short r) {
		gl.glTexCoord3s(s,t,r);
	checkGLError("glTexCoord3s");

	}

	public void glTexCoord4d(double s, double t, double r, double q) {
		gl.glTexCoord4d(s,t,r,q);
	checkGLError("glTexCoord4d");

	}

	public void glTexCoord4f(float s, float t, float r, float q) {
		gl.glTexCoord4f(s,t,r,q);
	checkGLError("glTexCoord4f");

	}

	public void glTexCoord4i(int s, int t, int r, int q) {
		gl.glTexCoord4i(s,t,r,q);
	checkGLError("glTexCoord4i");

	}

	public void glTexCoord4s(short s, short t, short r, short q) {
		gl.glTexCoord4s(s,t,r,q);
	checkGLError("glTexCoord4s");

	}

	public void glTexCoord1dv(DoubleBuffer v) {
		gl.glTexCoord1dv(v);
	checkGLError("glTexCoord1dv");

	}

	public void glTexCoord1fv(FloatBuffer v) {
		gl.glTexCoord1fv(v);
	checkGLError("glTexCoord1fv");

	}

	public void glTexCoord1iv(IntBuffer v) {
		gl.glTexCoord1iv(v);
	checkGLError("glTexCoord1iv");

	}

	public void glTexCoord1sv(ShortBuffer v) {
		gl.glTexCoord1sv(v);
	checkGLError("glTexCoord1sv");

	}

	public void glTexCoord2dv(DoubleBuffer v) {
		gl.glTexCoord2dv(v);
	checkGLError("glTexCoord2dv");

	}

	public void glTexCoord2fv(FloatBuffer v) {
		gl.glTexCoord2fv(v);
	checkGLError("glTexCoord2fv");

	}

	public void glTexCoord2iv(IntBuffer v) {
		gl.glTexCoord2iv(v);
	checkGLError("glTexCoord2iv");

	}

	public void glTexCoord2sv(ShortBuffer v) {
		gl.glTexCoord2sv(v);
	checkGLError("glTexCoord2sv");

	}

	public void glTexCoord3dv(DoubleBuffer v) {
		gl.glTexCoord3dv(v);
	checkGLError("glTexCoord3dv");

	}

	public void glTexCoord3fv(FloatBuffer v) {
		gl.glTexCoord3fv(v);
	checkGLError("glTexCoord3fv");

	}

	public void glTexCoord3iv(IntBuffer v) {
		gl.glTexCoord3iv(v);
	checkGLError("glTexCoord3iv");

	}

	public void glTexCoord3sv(ShortBuffer v) {
		gl.glTexCoord3sv(v);
	checkGLError("glTexCoord3sv");

	}

	public void glTexCoord4dv(DoubleBuffer v) {
		gl.glTexCoord4dv(v);
	checkGLError("glTexCoord4dv");

	}

	public void glTexCoord4fv(FloatBuffer v) {
		gl.glTexCoord4fv(v);
	checkGLError("glTexCoord4fv");

	}

	public void glTexCoord4iv(IntBuffer v) {
		gl.glTexCoord4iv(v);
	checkGLError("glTexCoord4iv");

	}

	public void glTexCoord4sv(ShortBuffer v) {
		gl.glTexCoord4sv(v);
	checkGLError("glTexCoord4sv");

	}

	public void glRasterPos2d(double x, double y) {
		gl.glRasterPos2d(x,y);
	checkGLError("glRasterPos2d");

	}

	public void glRasterPos2f(float x, float y) {
		gl.glRasterPos2f(x,y);
	checkGLError("glRasterPos2f");

	}

	public void glRasterPos2i(int x, int y) {
		gl.glRasterPos2i(x,y);
	checkGLError("glRasterPos2i");

	}

	public void glRasterPos2s(short x, short y) {
		gl.glRasterPos2s(x,y);
	checkGLError("glRasterPos2s");

	}

	public void glRasterPos3d(double x, double y, double z) {
		gl.glRasterPos3d(x,y,z);
	checkGLError("glRasterPos3d");

	}

	public void glRasterPos3f(float x, float y, float z) {
		gl.glRasterPos3f(x,y,z);
	checkGLError("glRasterPos3f");

	}

	public void glRasterPos3i(int x, int y, int z) {
		gl.glRasterPos3i(x,y,z);
	checkGLError("glRasterPos3i");

	}

	public void glRasterPos3s(short x, short y, short z) {
		gl.glRasterPos3s(x,y,z);
	checkGLError("glRasterPos3s");

	}

	public void glRasterPos4d(double x, double y, double z, double w) {
		gl.glRasterPos4d(x,y,z,w);
	checkGLError("glRasterPos4d");

	}

	public void glRasterPos4f(float x, float y, float z, float w) {
		gl.glRasterPos4f(x,y,z,w);
	checkGLError("glRasterPos4f");

	}

	public void glRasterPos4i(int x, int y, int z, int w) {
		gl.glRasterPos4i(x,y,z,w);
	checkGLError("glRasterPos4i");

	}

	public void glRasterPos4s(short x, short y, short z, short w) {
		gl.glRasterPos4s(x,y,z,w);
	checkGLError("glRasterPos4s");

	}

	public void glRasterPos2dv(DoubleBuffer v) {
		gl.glRasterPos2dv(v);
	checkGLError("glRasterPos2dv");

	}

	public void glRasterPos2fv(FloatBuffer v) {
		gl.glRasterPos2fv(v);
	checkGLError("glRasterPos2fv");

	}

	public void glRasterPos2iv(IntBuffer v) {
		gl.glRasterPos2iv(v);
	checkGLError("glRasterPos2iv");

	}

	public void glRasterPos2sv(ShortBuffer v) {
		gl.glRasterPos2sv(v);
	checkGLError("glRasterPos2sv");

	}

	public void glRasterPos3dv(DoubleBuffer v) {
		gl.glRasterPos3dv(v);
	checkGLError("glRasterPos3dv");

	}

	public void glRasterPos3fv(FloatBuffer v) {
		gl.glRasterPos3fv(v);
	checkGLError("glRasterPos3fv");

	}

	public void glRasterPos3iv(IntBuffer v) {
		gl.glRasterPos3iv(v);
	checkGLError("glRasterPos3iv");

	}

	public void glRasterPos3sv(ShortBuffer v) {
		gl.glRasterPos3sv(v);
	checkGLError("glRasterPos3sv");

	}

	public void glRasterPos4dv(DoubleBuffer v) {
		gl.glRasterPos4dv(v);
	checkGLError("glRasterPos4dv");

	}

	public void glRasterPos4fv(FloatBuffer v) {
		gl.glRasterPos4fv(v);
	checkGLError("glRasterPos4fv");

	}

	public void glRasterPos4iv(IntBuffer v) {
		gl.glRasterPos4iv(v);
	checkGLError("glRasterPos4iv");

	}

	public void glRasterPos4sv(ShortBuffer v) {
		gl.glRasterPos4sv(v);
	checkGLError("glRasterPos4sv");

	}

	public void glRectd(double x1, double y1, double x2, double y2) {
		gl.glRectd(x1,y1,x2,y2);
	checkGLError("glRectd");

	}

	public void glRectf(float x1, float y1, float x2, float y2) {
		gl.glRectf(x1,y1,x2,y2);
	checkGLError("glRectf");

	}

	public void glRecti(int x1, int y1, int x2, int y2) {
		gl.glRecti(x1,y1,x2,y2);
	checkGLError("glRecti");

	}

	public void glRects(short x1, short y1, short x2, short y2) {
		gl.glRects(x1,y1,x2,y2);
	checkGLError("glRects");

	}

	public void glRectdv(DoubleBuffer v1, DoubleBuffer v2) {
		gl.glRectdv(v1,v2);
	checkGLError("glRectdv");

	}

	public void glRectfv(FloatBuffer v1, FloatBuffer v2) {
		gl.glRectfv(v1,v2);
	checkGLError("glRectfv");

	}

	public void glRectiv(IntBuffer v1, IntBuffer v2) {
		gl.glRectiv(v1,v2);
	checkGLError("glRectiv");

	}

	public void glRectsv(ShortBuffer v1, ShortBuffer v2) {
		gl.glRectsv(v1,v2);
	checkGLError("glRectsv");

	}

	public void glVertexPointer(int size, long type, int stride, Buffer ptr) {
		gl.glVertexPointer(size,type,stride,ptr);
	checkGLError("glVertexPointer");

	}

	public void glNormalPointer(long type, int stride, Buffer ptr) {
		gl.glNormalPointer(type,stride,ptr);
	checkGLError("glNormalPointer");

	}

	public void glColorPointer(int size, long type, int stride, Buffer ptr) {
		gl.glColorPointer(size,type,stride,ptr);
	checkGLError("glColorPointer");

	}

	public void glIndexPointer(long type, int stride, Buffer ptr) {
		gl.glIndexPointer(type,stride,ptr);
	checkGLError("glIndexPointer");

	}

	public void glTexCoordPointer(int size, long type, int stride, Buffer ptr) {
		gl.glTexCoordPointer(size,type,stride,ptr);
	checkGLError("glTexCoordPointer");

	}

	public void glEdgeFlagPointer(int stride, Buffer ptr) {
		gl.glEdgeFlagPointer(stride,ptr);
	checkGLError("glEdgeFlagPointer");

	}

	public void glArrayElement(int i) {
		gl.glArrayElement(i);
	checkGLError("glArrayElement");

	}

	public void glDrawArrays(long mode, int first, int count) {
		gl.glDrawArrays(mode,first,count);
	checkGLError("glDrawArrays");

	}

	public void glDrawElements(long mode, int count, long type, Buffer indices) {
		gl.glDrawElements(mode,count,type,indices);
	checkGLError("glDrawElements");

	}

	public void glInterleavedArrays(long format, int stride, Buffer pointer) {
		gl.glInterleavedArrays(format,stride,pointer);
	checkGLError("glInterleavedArrays");

	}

	public void glShadeModel(long mode) {
		gl.glShadeModel(mode);
	checkGLError("glShadeModel");

	}

	public void glLightf(long light, long pname, float param) {
		gl.glLightf(light,pname,param);
	checkGLError("glLightf");

	}

	public void glLighti(long light, long pname, int param) {
		gl.glLighti(light,pname,param);
	checkGLError("glLighti");

	}

	public void glLightfv(long light, long pname, FloatBuffer params) {
		gl.glLightfv(light,pname,params);
	checkGLError("glLightfv");

	}

	public void glLightiv(long light, long pname, IntBuffer params) {
		gl.glLightiv(light,pname,params);
	checkGLError("glLightiv");

	}

	public void glGetLightfv(long light, long pname, FloatBuffer params) {
		gl.glGetLightfv(light,pname,params);
	checkGLError("glGetLightfv");

	}

	public void glGetLightiv(long light, long pname, IntBuffer params) {
		gl.glGetLightiv(light,pname,params);
	checkGLError("glGetLightiv");

	}

	public void glLightModelf(long pname, float param) {
		gl.glLightModelf(pname,param);
	checkGLError("glLightModelf");

	}

	public void glLightModeli(long pname, int param) {
		gl.glLightModeli(pname,param);
	checkGLError("glLightModeli");

	}

	public void glLightModelfv(long pname, FloatBuffer params) {
		gl.glLightModelfv(pname,params);
	checkGLError("glLightModelfv");

	}

	public void glLightModeliv(long pname, IntBuffer params) {
		gl.glLightModeliv(pname,params);
	checkGLError("glLightModeliv");

	}

	public void glMaterialf(long face, long pname, float param) {
		gl.glMaterialf(face,pname,param);
	checkGLError("glMaterialf");

	}

	public void glMateriali(long face, long pname, int param) {
		gl.glMateriali(face,pname,param);
	checkGLError("glMateriali");

	}

	public void glMaterialfv(long face, long pname, FloatBuffer params) {
		gl.glMaterialfv(face,pname,params);
	checkGLError("glMaterialfv");

	}

	public void glMaterialiv(long face, long pname, IntBuffer params) {
		gl.glMaterialiv(face,pname,params);
	checkGLError("glMaterialiv");

	}

	public void glGetMaterialfv(long face, long pname, FloatBuffer params) {
		gl.glGetMaterialfv(face,pname,params);
	checkGLError("glGetMaterialfv");

	}

	public void glGetMaterialiv(long face, long pname, IntBuffer params) {
		gl.glGetMaterialiv(face,pname,params);
	checkGLError("glGetMaterialiv");

	}

	public void glColorMaterial(long face, long mode) {
		gl.glColorMaterial(face,mode);
	checkGLError("glColorMaterial");

	}

	public void glPixelZoom(float xfactor, float yfactor) {
		gl.glPixelZoom(xfactor,yfactor);
	checkGLError("glPixelZoom");

	}

	public void glPixelStoref(long pname, float param) {
		gl.glPixelStoref(pname,param);
	checkGLError("glPixelStoref");

	}

	public void glPixelStorei(long pname, int param) {
		gl.glPixelStorei(pname,param);
	checkGLError("glPixelStorei");

	}

	public void glPixelTransferf(long pname, float param) {
		gl.glPixelTransferf(pname,param);
	checkGLError("glPixelTransferf");

	}

	public void glPixelTransferi(long pname, int param) {
		gl.glPixelTransferi(pname,param);
	checkGLError("glPixelTransferi");

	}

	public void glPixelMapfv(long map, int mapsize, FloatBuffer values) {
		gl.glPixelMapfv(map,mapsize,values);
	checkGLError("glPixelMapfv");

	}

	public void glPixelMapuiv(long map, int mapsize, IntBuffer values) {
		gl.glPixelMapuiv(map,mapsize,values);
	checkGLError("glPixelMapuiv");

	}

	public void glPixelMapusv(long map, int mapsize, IntBuffer values) {
		gl.glPixelMapusv(map,mapsize,values);
	checkGLError("glPixelMapusv");

	}

	public void glGetPixelMapfv(long map, FloatBuffer values) {
		gl.glGetPixelMapfv(map,values);
	checkGLError("glGetPixelMapfv");

	}

	public void glGetPixelMapuiv(long map, IntBuffer values) {
		gl.glGetPixelMapuiv(map,values);
	checkGLError("glGetPixelMapuiv");

	}

	public void glGetPixelMapusv(long map, IntBuffer values) {
		gl.glGetPixelMapusv(map,values);
	checkGLError("glGetPixelMapusv");

	}

	public void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ShortBuffer bitmap) {
		gl.glBitmap(width,height,xorig,yorig,xmove,ymove,bitmap);
	checkGLError("glBitmap");

	}

	public void glReadPixels(int x, int y, int width, int height, long format, long type, Buffer pixels) {
		gl.glReadPixels(x,y,width,height,format,type,pixels);
	checkGLError("glReadPixels");

	}

	public void glDrawPixels(int width, int height, long format, long type, Buffer pixels) {
		gl.glDrawPixels(width,height,format,type,pixels);
	checkGLError("glDrawPixels");

	}

	public void glCopyPixels(int x, int y, int width, int height, long type) {
		gl.glCopyPixels(x,y,width,height,type);
	checkGLError("glCopyPixels");

	}

	public void glStencilFunc(long func, int ref, long mask) {
		gl.glStencilFunc(func,ref,mask);
	checkGLError("glStencilFunc");

	}

	public void glStencilMask(long mask) {
		gl.glStencilMask(mask);
	checkGLError("glStencilMask");

	}

	public void glStencilOp(long fail, long zfail, long zpass) {
		gl.glStencilOp(fail,zfail,zpass);
	checkGLError("glStencilOp");

	}

	public void glClearStencil(int s) {
		gl.glClearStencil(s);
	checkGLError("glClearStencil");

	}

	public void glTexGend(long coord, long pname, double param) {
		gl.glTexGend(coord,pname,param);
	checkGLError("glTexGend");

	}

	public void glTexGenf(long coord, long pname, float param) {
		gl.glTexGenf(coord,pname,param);
	checkGLError("glTexGenf");

	}

	public void glTexGeni(long coord, long pname, int param) {
		gl.glTexGeni(coord,pname,param);
	checkGLError("glTexGeni");

	}

	public void glTexGendv(long coord, long pname, DoubleBuffer params) {
		gl.glTexGendv(coord,pname,params);
	checkGLError("glTexGendv");

	}

	public void glTexGenfv(long coord, long pname, FloatBuffer params) {
		gl.glTexGenfv(coord,pname,params);
	checkGLError("glTexGenfv");

	}

	public void glTexGeniv(long coord, long pname, IntBuffer params) {
		gl.glTexGeniv(coord,pname,params);
	checkGLError("glTexGeniv");

	}

	public void glGetTexGendv(long coord, long pname, DoubleBuffer params) {
		gl.glGetTexGendv(coord,pname,params);
	checkGLError("glGetTexGendv");

	}

	public void glGetTexGenfv(long coord, long pname, FloatBuffer params) {
		gl.glGetTexGenfv(coord,pname,params);
	checkGLError("glGetTexGenfv");

	}

	public void glGetTexGeniv(long coord, long pname, IntBuffer params) {
		gl.glGetTexGeniv(coord,pname,params);
	checkGLError("glGetTexGeniv");

	}

	public void glTexEnvf(long target, long pname, float param) {
		gl.glTexEnvf(target,pname,param);
	checkGLError("glTexEnvf");

	}

	public void glTexEnvi(long target, long pname, int param) {
		gl.glTexEnvi(target,pname,param);
	checkGLError("glTexEnvi");

	}

	public void glTexEnvfv(long target, long pname, FloatBuffer params) {
		gl.glTexEnvfv(target,pname,params);
	checkGLError("glTexEnvfv");

	}

	public void glTexEnviv(long target, long pname, IntBuffer params) {
		gl.glTexEnviv(target,pname,params);
	checkGLError("glTexEnviv");

	}

	public void glGetTexEnvfv(long target, long pname, FloatBuffer params) {
		gl.glGetTexEnvfv(target,pname,params);
	checkGLError("glGetTexEnvfv");

	}

	public void glGetTexEnviv(long target, long pname, IntBuffer params) {
		gl.glGetTexEnviv(target,pname,params);
	checkGLError("glGetTexEnviv");

	}

	public void glTexParameterf(long target, long pname, float param) {
		gl.glTexParameterf(target,pname,param);
	checkGLError("glTexParameterf");

	}

	public void glTexParameteri(long target, long pname, int param) {
		gl.glTexParameteri(target,pname,param);
	checkGLError("glTexParameteri");

	}

	public void glTexParameterfv(long target, long pname, FloatBuffer params) {
		gl.glTexParameterfv(target,pname,params);
	checkGLError("glTexParameterfv");

	}

	public void glTexParameteriv(long target, long pname, IntBuffer params) {
		gl.glTexParameteriv(target,pname,params);
	checkGLError("glTexParameteriv");

	}

	public void glGetTexParameterfv(long target, long pname, FloatBuffer params) {
		gl.glGetTexParameterfv(target,pname,params);
	checkGLError("glGetTexParameterfv");

	}

	public void glGetTexParameteriv(long target, long pname, IntBuffer params) {
		gl.glGetTexParameteriv(target,pname,params);
	checkGLError("glGetTexParameteriv");

	}

	public void glGetTexLevelParameterfv(long target, int level, long pname, FloatBuffer params) {
		gl.glGetTexLevelParameterfv(target,level,pname,params);
	checkGLError("glGetTexLevelParameterfv");

	}

	public void glGetTexLevelParameteriv(long target, int level, long pname, IntBuffer params) {
		gl.glGetTexLevelParameteriv(target,level,pname,params);
	checkGLError("glGetTexLevelParameteriv");

	}

	public void glTexImage1D(long target, int level, int internalFormat, int width, int border, long format, long type, Buffer pixels) {
		gl.glTexImage1D(target,level,internalFormat,width,border,format,type,pixels);
	checkGLError("glTexImage1D");

	}

	public void glTexImage2D(long target, int level, int internalFormat, int width, int height, int border, long format, long type, Buffer pixels) {
		gl.glTexImage2D(target,level,internalFormat,width,height,border,format,type,pixels);
	checkGLError("glTexImage2D");

	}

	public void glGetTexImage(long target, int level, long format, long type, Buffer pixels) {
		gl.glGetTexImage(target,level,format,type,pixels);
	checkGLError("glGetTexImage");

	}

	public void glGenTextures(int n, IntBuffer textures) {
		gl.glGenTextures(n,textures);
	checkGLError("glGenTextures");

	}

	public void glDeleteTextures(int n, IntBuffer textures) {
		gl.glDeleteTextures(n,textures);
	checkGLError("glDeleteTextures");

	}

	public void glBindTexture(long target, long texture) {
		gl.glBindTexture(target,texture);
	checkGLError("glBindTexture");

	}

	public void glPrioritizeTextures(int n, IntBuffer textures, FloatBuffer priorities) {
		gl.glPrioritizeTextures(n,textures,priorities);
	checkGLError("glPrioritizeTextures");

	}

	public short glAreTexturesResident(int n, IntBuffer textures, ShortBuffer residences) {
	short value = 	gl.glAreTexturesResident(n,textures,residences);
	checkGLError("glAreTexturesResident");
		return value;

	}

	public short glIsTexture(long texture) {
	short value = 	gl.glIsTexture(texture);
	checkGLError("glIsTexture");
		return value;

	}

	public void glTexSubImage1D(long target, int level, int xoffset, int width, long format, long type, Buffer pixels) {
		gl.glTexSubImage1D(target,level,xoffset,width,format,type,pixels);
	checkGLError("glTexSubImage1D");

	}

	public void glTexSubImage2D(long target, int level, int xoffset, int yoffset, int width, int height, long format, long type, Buffer pixels) {
		gl.glTexSubImage2D(target,level,xoffset,yoffset,width,height,format,type,pixels);
	checkGLError("glTexSubImage2D");

	}

	public void glCopyTexImage1D(long target, int level, long internalformat, int x, int y, int width, int border) {
		gl.glCopyTexImage1D(target,level,internalformat,x,y,width,border);
	checkGLError("glCopyTexImage1D");

	}

	public void glCopyTexImage2D(long target, int level, long internalformat, int x, int y, int width, int height, int border) {
		gl.glCopyTexImage2D(target,level,internalformat,x,y,width,height,border);
	checkGLError("glCopyTexImage2D");

	}

	public void glCopyTexSubImage1D(long target, int level, int xoffset, int x, int y, int width) {
		gl.glCopyTexSubImage1D(target,level,xoffset,x,y,width);
	checkGLError("glCopyTexSubImage1D");

	}

	public void glCopyTexSubImage2D(long target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
		gl.glCopyTexSubImage2D(target,level,xoffset,yoffset,x,y,width,height);
	checkGLError("glCopyTexSubImage2D");

	}

	public void glMap1d(long target, double u1, double u2, int stride, int order, DoubleBuffer points) {
		gl.glMap1d(target,u1,u2,stride,order,points);
	checkGLError("glMap1d");

	}

	public void glMap1f(long target, float u1, float u2, int stride, int order, FloatBuffer points) {
		gl.glMap1f(target,u1,u2,stride,order,points);
	checkGLError("glMap1f");

	}

	public void glMap2d(long target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) {
		gl.glMap2d(target,u1,u2,ustride,uorder,v1,v2,vstride,vorder,points);
	checkGLError("glMap2d");

	}

	public void glMap2f(long target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		gl.glMap2f(target,u1,u2,ustride,uorder,v1,v2,vstride,vorder,points);
	checkGLError("glMap2f");

	}

	public void glGetMapdv(long target, long query, DoubleBuffer v) {
		gl.glGetMapdv(target,query,v);
	checkGLError("glGetMapdv");

	}

	public void glGetMapfv(long target, long query, FloatBuffer v) {
		gl.glGetMapfv(target,query,v);
	checkGLError("glGetMapfv");

	}

	public void glGetMapiv(long target, long query, IntBuffer v) {
		gl.glGetMapiv(target,query,v);
	checkGLError("glGetMapiv");

	}

	public void glEvalCoord1d(double u) {
		gl.glEvalCoord1d(u);
	checkGLError("glEvalCoord1d");

	}

	public void glEvalCoord1f(float u) {
		gl.glEvalCoord1f(u);
	checkGLError("glEvalCoord1f");

	}

	public void glEvalCoord1dv(DoubleBuffer u) {
		gl.glEvalCoord1dv(u);
	checkGLError("glEvalCoord1dv");

	}

	public void glEvalCoord1fv(FloatBuffer u) {
		gl.glEvalCoord1fv(u);
	checkGLError("glEvalCoord1fv");

	}

	public void glEvalCoord2d(double u, double v) {
		gl.glEvalCoord2d(u,v);
	checkGLError("glEvalCoord2d");

	}

	public void glEvalCoord2f(float u, float v) {
		gl.glEvalCoord2f(u,v);
	checkGLError("glEvalCoord2f");

	}

	public void glEvalCoord2dv(DoubleBuffer u) {
		gl.glEvalCoord2dv(u);
	checkGLError("glEvalCoord2dv");

	}

	public void glEvalCoord2fv(FloatBuffer u) {
		gl.glEvalCoord2fv(u);
	checkGLError("glEvalCoord2fv");

	}

	public void glMapGrid1d(int un, double u1, double u2) {
		gl.glMapGrid1d(un,u1,u2);
	checkGLError("glMapGrid1d");

	}

	public void glMapGrid1f(int un, float u1, float u2) {
		gl.glMapGrid1f(un,u1,u2);
	checkGLError("glMapGrid1f");

	}

	public void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2) {
		gl.glMapGrid2d(un,u1,u2,vn,v1,v2);
	checkGLError("glMapGrid2d");

	}

	public void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
		gl.glMapGrid2f(un,u1,u2,vn,v1,v2);
	checkGLError("glMapGrid2f");

	}

	public void glEvalPoint1(int i) {
		gl.glEvalPoint1(i);
	checkGLError("glEvalPoint1");

	}

	public void glEvalPoint2(int i, int j) {
		gl.glEvalPoint2(i,j);
	checkGLError("glEvalPoint2");

	}

	public void glEvalMesh1(long mode, int i1, int i2) {
		gl.glEvalMesh1(mode,i1,i2);
	checkGLError("glEvalMesh1");

	}

	public void glEvalMesh2(long mode, int i1, int i2, int j1, int j2) {
		gl.glEvalMesh2(mode,i1,i2,j1,j2);
	checkGLError("glEvalMesh2");

	}

	public void glFogf(long pname, float param) {
		gl.glFogf(pname,param);
	checkGLError("glFogf");

	}

	public void glFogi(long pname, int param) {
		gl.glFogi(pname,param);
	checkGLError("glFogi");

	}

	public void glFogfv(long pname, FloatBuffer params) {
		gl.glFogfv(pname,params);
	checkGLError("glFogfv");

	}

	public void glFogiv(long pname, IntBuffer params) {
		gl.glFogiv(pname,params);
	checkGLError("glFogiv");

	}

	public void glFeedbackBuffer(int size, long type, FloatBuffer buffer) {
		gl.glFeedbackBuffer(size,type,buffer);
	checkGLError("glFeedbackBuffer");

	}

	public void glPassThrough(float token) {
		gl.glPassThrough(token);
	checkGLError("glPassThrough");

	}

	public void glSelectBuffer(int size, IntBuffer buffer) {
		gl.glSelectBuffer(size,buffer);
	checkGLError("glSelectBuffer");

	}

	public void glInitNames() {
		gl.glInitNames();
	checkGLError("glInitNames");

	}

	public void glLoadName(long name) {
		gl.glLoadName(name);
	checkGLError("glLoadName");

	}

	public void glPushName(long name) {
		gl.glPushName(name);
	checkGLError("glPushName");

	}

	public void glPopName() {
		gl.glPopName();
	checkGLError("glPopName");

	}

	public void glPolygonStipple(short[] jarg1) {
		gl.glPolygonStipple(jarg1);
	checkGLError("glPolygonStipple");

	}

	public void glGetPolygonStipple(short[] jarg1) {
		gl.glGetPolygonStipple(jarg1);
	checkGLError("glGetPolygonStipple");

	}

	public void glEdgeFlagv(short[] jarg1) {
		gl.glEdgeFlagv(jarg1);
	checkGLError("glEdgeFlagv");

	}

	public void glClipPlane(long jarg1, double[] jarg2) {
		gl.glClipPlane(jarg1,jarg2);
	checkGLError("glClipPlane");

	}

	public void glGetClipPlane(long jarg1, double[] jarg2) {
		gl.glGetClipPlane(jarg1,jarg2);
	checkGLError("glGetClipPlane");

	}

	public void glGetBooleanv(long jarg1, short[] jarg2) {
		gl.glGetBooleanv(jarg1,jarg2);
	checkGLError("glGetBooleanv");

	}

	public void glGetDoublev(long jarg1, double[] jarg2) {
		gl.glGetDoublev(jarg1,jarg2);
	checkGLError("glGetDoublev");

	}

	public void glGetFloatv(long jarg1, float[] jarg2) {
		gl.glGetFloatv(jarg1,jarg2);
	checkGLError("glGetFloatv");

	}

	public void glGetIntegerv(long jarg1, int[] jarg2) {
		gl.glGetIntegerv(jarg1,jarg2);
	checkGLError("glGetIntegerv");

	}

	public void glLoadMatrixd(double[] jarg1) {
		gl.glLoadMatrixd(jarg1);
	checkGLError("glLoadMatrixd");

	}

	public void glLoadMatrixf(float[] jarg1) {
		gl.glLoadMatrixf(jarg1);
	checkGLError("glLoadMatrixf");

	}

	public void glMultMatrixd(double[] jarg1) {
		gl.glMultMatrixd(jarg1);
	checkGLError("glMultMatrixd");

	}

	public void glMultMatrixf(float[] jarg1) {
		gl.glMultMatrixf(jarg1);
	checkGLError("glMultMatrixf");

	}

	public void glVertex2dv(double[] jarg1) {
		gl.glVertex2dv(jarg1);
	checkGLError("glVertex2dv");

	}

	public void glVertex2fv(float[] jarg1) {
		gl.glVertex2fv(jarg1);
	checkGLError("glVertex2fv");

	}

	public void glVertex2iv(int[] jarg1) {
		gl.glVertex2iv(jarg1);
	checkGLError("glVertex2iv");

	}

	public void glVertex2sv(short[] jarg1) {
		gl.glVertex2sv(jarg1);
	checkGLError("glVertex2sv");

	}

	public void glVertex3dv(double[] jarg1) {
		gl.glVertex3dv(jarg1);
	checkGLError("glVertex3dv");

	}

	public void glVertex3fv(float[] jarg1) {
		gl.glVertex3fv(jarg1);
	checkGLError("glVertex3fv");

	}

	public void glVertex3iv(int[] jarg1) {
		gl.glVertex3iv(jarg1);
	checkGLError("glVertex3iv");

	}

	public void glVertex3sv(short[] jarg1) {
		gl.glVertex3sv(jarg1);
	checkGLError("glVertex3sv");

	}

	public void glVertex4dv(double[] jarg1) {
		gl.glVertex4dv(jarg1);
	checkGLError("glVertex4dv");

	}

	public void glVertex4fv(float[] jarg1) {
		gl.glVertex4fv(jarg1);
	checkGLError("glVertex4fv");

	}

	public void glVertex4iv(int[] jarg1) {
		gl.glVertex4iv(jarg1);
	checkGLError("glVertex4iv");

	}

	public void glVertex4sv(short[] jarg1) {
		gl.glVertex4sv(jarg1);
	checkGLError("glVertex4sv");

	}

	public void glNormal3bv(byte[] jarg1) {
		gl.glNormal3bv(jarg1);
	checkGLError("glNormal3bv");

	}

	public void glNormal3dv(double[] jarg1) {
		gl.glNormal3dv(jarg1);
	checkGLError("glNormal3dv");

	}

	public void glNormal3fv(float[] jarg1) {
		gl.glNormal3fv(jarg1);
	checkGLError("glNormal3fv");

	}

	public void glNormal3iv(int[] jarg1) {
		gl.glNormal3iv(jarg1);
	checkGLError("glNormal3iv");

	}

	public void glNormal3sv(short[] jarg1) {
		gl.glNormal3sv(jarg1);
	checkGLError("glNormal3sv");

	}

	public void glIndexdv(double[] jarg1) {
		gl.glIndexdv(jarg1);
	checkGLError("glIndexdv");

	}

	public void glIndexfv(float[] jarg1) {
		gl.glIndexfv(jarg1);
	checkGLError("glIndexfv");

	}

	public void glIndexiv(int[] jarg1) {
		gl.glIndexiv(jarg1);
	checkGLError("glIndexiv");

	}

	public void glIndexsv(short[] jarg1) {
		gl.glIndexsv(jarg1);
	checkGLError("glIndexsv");

	}

	public void glIndexubv(short[] jarg1) {
		gl.glIndexubv(jarg1);
	checkGLError("glIndexubv");

	}

	public void glColor3bv(byte[] jarg1) {
		gl.glColor3bv(jarg1);
	checkGLError("glColor3bv");

	}

	public void glColor3dv(double[] jarg1) {
		gl.glColor3dv(jarg1);
	checkGLError("glColor3dv");

	}

	public void glColor3fv(float[] jarg1) {
		gl.glColor3fv(jarg1);
	checkGLError("glColor3fv");

	}

	public void glColor3iv(int[] jarg1) {
		gl.glColor3iv(jarg1);
	checkGLError("glColor3iv");

	}

	public void glColor3sv(short[] jarg1) {
		gl.glColor3sv(jarg1);
	checkGLError("glColor3sv");

	}

	public void glColor3ubv(short[] jarg1) {
		gl.glColor3ubv(jarg1);
	checkGLError("glColor3ubv");

	}

	public void glColor3uiv(long[] jarg1) {
		gl.glColor3uiv(jarg1);
	checkGLError("glColor3uiv");

	}

	public void glColor3usv(int[] jarg1) {
		gl.glColor3usv(jarg1);
	checkGLError("glColor3usv");

	}

	public void glColor4bv(byte[] jarg1) {
		gl.glColor4bv(jarg1);
	checkGLError("glColor4bv");

	}

	public void glColor4dv(double[] jarg1) {
		gl.glColor4dv(jarg1);
	checkGLError("glColor4dv");

	}

	public void glColor4fv(float[] jarg1) {
		gl.glColor4fv(jarg1);
	checkGLError("glColor4fv");

	}

	public void glColor4iv(int[] jarg1) {
		gl.glColor4iv(jarg1);
	checkGLError("glColor4iv");

	}

	public void glColor4sv(short[] jarg1) {
		gl.glColor4sv(jarg1);
	checkGLError("glColor4sv");

	}

	public void glColor4ubv(short[] jarg1) {
		gl.glColor4ubv(jarg1);
	checkGLError("glColor4ubv");

	}

	public void glColor4uiv(long[] jarg1) {
		gl.glColor4uiv(jarg1);
	checkGLError("glColor4uiv");

	}

	public void glColor4usv(int[] jarg1) {
		gl.glColor4usv(jarg1);
	checkGLError("glColor4usv");

	}

	public void glTexCoord1dv(double[] jarg1) {
		gl.glTexCoord1dv(jarg1);
	checkGLError("glTexCoord1dv");

	}

	public void glTexCoord1fv(float[] jarg1) {
		gl.glTexCoord1fv(jarg1);
	checkGLError("glTexCoord1fv");

	}

	public void glTexCoord1iv(int[] jarg1) {
		gl.glTexCoord1iv(jarg1);
	checkGLError("glTexCoord1iv");

	}

	public void glTexCoord1sv(short[] jarg1) {
		gl.glTexCoord1sv(jarg1);
	checkGLError("glTexCoord1sv");

	}

	public void glTexCoord2dv(double[] jarg1) {
		gl.glTexCoord2dv(jarg1);
	checkGLError("glTexCoord2dv");

	}

	public void glTexCoord2fv(float[] jarg1) {
		gl.glTexCoord2fv(jarg1);
	checkGLError("glTexCoord2fv");

	}

	public void glTexCoord2iv(int[] jarg1) {
		gl.glTexCoord2iv(jarg1);
	checkGLError("glTexCoord2iv");

	}

	public void glTexCoord2sv(short[] jarg1) {
		gl.glTexCoord2sv(jarg1);
	checkGLError("glTexCoord2sv");

	}

	public void glTexCoord3dv(double[] jarg1) {
		gl.glTexCoord3dv(jarg1);
	checkGLError("glTexCoord3dv");

	}

	public void glTexCoord3fv(float[] jarg1) {
		gl.glTexCoord3fv(jarg1);
	checkGLError("glTexCoord3fv");

	}

	public void glTexCoord3iv(int[] jarg1) {
		gl.glTexCoord3iv(jarg1);
	checkGLError("glTexCoord3iv");

	}

	public void glTexCoord3sv(short[] jarg1) {
		gl.glTexCoord3sv(jarg1);
	checkGLError("glTexCoord3sv");

	}

	public void glTexCoord4dv(double[] jarg1) {
		gl.glTexCoord4dv(jarg1);
	checkGLError("glTexCoord4dv");

	}

	public void glTexCoord4fv(float[] jarg1) {
		gl.glTexCoord4fv(jarg1);
	checkGLError("glTexCoord4fv");

	}

	public void glTexCoord4iv(int[] jarg1) {
		gl.glTexCoord4iv(jarg1);
	checkGLError("glTexCoord4iv");

	}

	public void glTexCoord4sv(short[] jarg1) {
		gl.glTexCoord4sv(jarg1);
	checkGLError("glTexCoord4sv");

	}

	public void glRasterPos2dv(double[] jarg1) {
		gl.glRasterPos2dv(jarg1);
	checkGLError("glRasterPos2dv");

	}

	public void glRasterPos2fv(float[] jarg1) {
		gl.glRasterPos2fv(jarg1);
	checkGLError("glRasterPos2fv");

	}

	public void glRasterPos2iv(int[] jarg1) {
		gl.glRasterPos2iv(jarg1);
	checkGLError("glRasterPos2iv");

	}

	public void glRasterPos2sv(short[] jarg1) {
		gl.glRasterPos2sv(jarg1);
	checkGLError("glRasterPos2sv");

	}

	public void glRasterPos3dv(double[] jarg1) {
		gl.glRasterPos3dv(jarg1);
	checkGLError("glRasterPos3dv");

	}

	public void glRasterPos3fv(float[] jarg1) {
		gl.glRasterPos3fv(jarg1);
	checkGLError("glRasterPos3fv");

	}

	public void glRasterPos3iv(int[] jarg1) {
		gl.glRasterPos3iv(jarg1);
	checkGLError("glRasterPos3iv");

	}

	public void glRasterPos3sv(short[] jarg1) {
		gl.glRasterPos3sv(jarg1);
	checkGLError("glRasterPos3sv");

	}

	public void glRasterPos4dv(double[] jarg1) {
		gl.glRasterPos4dv(jarg1);
	checkGLError("glRasterPos4dv");

	}

	public void glRasterPos4fv(float[] jarg1) {
		gl.glRasterPos4fv(jarg1);
	checkGLError("glRasterPos4fv");

	}

	public void glRasterPos4iv(int[] jarg1) {
		gl.glRasterPos4iv(jarg1);
	checkGLError("glRasterPos4iv");

	}

	public void glRasterPos4sv(short[] jarg1) {
		gl.glRasterPos4sv(jarg1);
	checkGLError("glRasterPos4sv");

	}

	public void glRectdv(double[] jarg1, double[] jarg2) {
		gl.glRectdv(jarg1,jarg2);
	checkGLError("glRectdv");

	}

	public void glRectfv(float[] jarg1, float[] jarg2) {
		gl.glRectfv(jarg1,jarg2);
	checkGLError("glRectfv");

	}

	public void glRectiv(int[] jarg1, int[] jarg2) {
		gl.glRectiv(jarg1,jarg2);
	checkGLError("glRectiv");

	}

	public void glRectsv(short[] jarg1, short[] jarg2) {
		gl.glRectsv(jarg1,jarg2);
	checkGLError("glRectsv");

	}

	public void glLightfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glLightfv(jarg1,jarg2,jarg3);
	checkGLError("glLightfv");

	}

	public void glLightiv(long jarg1, long jarg2, int[] jarg3) {
		gl.glLightiv(jarg1,jarg2,jarg3);
	checkGLError("glLightiv");

	}

	public void glGetLightfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetLightfv(jarg1,jarg2,jarg3);
	checkGLError("glGetLightfv");

	}

	public void glGetLightiv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetLightiv(jarg1,jarg2,jarg3);
	checkGLError("glGetLightiv");

	}

	public void glLightModelfv(long jarg1, float[] jarg2) {
		gl.glLightModelfv(jarg1,jarg2);
	checkGLError("glLightModelfv");

	}

	public void glLightModeliv(long jarg1, int[] jarg2) {
		gl.glLightModeliv(jarg1,jarg2);
	checkGLError("glLightModeliv");

	}

	public void glMaterialfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glMaterialfv(jarg1,jarg2,jarg3);
	checkGLError("glMaterialfv");

	}

	public void glMaterialiv(long jarg1, long jarg2, int[] jarg3) {
		gl.glMaterialiv(jarg1,jarg2,jarg3);
	checkGLError("glMaterialiv");

	}

	public void glGetMaterialfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetMaterialfv(jarg1,jarg2,jarg3);
	checkGLError("glGetMaterialfv");

	}

	public void glGetMaterialiv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetMaterialiv(jarg1,jarg2,jarg3);
	checkGLError("glGetMaterialiv");

	}

	public void glPixelMapfv(long jarg1, int jarg2, float[] jarg3) {
		gl.glPixelMapfv(jarg1,jarg2,jarg3);
	checkGLError("glPixelMapfv");

	}

	public void glPixelMapuiv(long jarg1, int jarg2, long[] jarg3) {
		gl.glPixelMapuiv(jarg1,jarg2,jarg3);
	checkGLError("glPixelMapuiv");

	}

	public void glPixelMapusv(long jarg1, int jarg2, int[] jarg3) {
		gl.glPixelMapusv(jarg1,jarg2,jarg3);
	checkGLError("glPixelMapusv");

	}

	public void glGetPixelMapfv(long jarg1, float[] jarg2) {
		gl.glGetPixelMapfv(jarg1,jarg2);
	checkGLError("glGetPixelMapfv");

	}

	public void glGetPixelMapuiv(long jarg1, long[] jarg2) {
		gl.glGetPixelMapuiv(jarg1,jarg2);
	checkGLError("glGetPixelMapuiv");

	}

	public void glGetPixelMapusv(long jarg1, int[] jarg2) {
		gl.glGetPixelMapusv(jarg1,jarg2);
	checkGLError("glGetPixelMapusv");

	}

	public void glBitmap(int jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, short[] jarg7) {
		gl.glBitmap(jarg1,jarg2,jarg3,jarg4,jarg5,jarg6,jarg7);
	checkGLError("glBitmap");

	}

	public void glTexGendv(long jarg1, long jarg2, double[] jarg3) {
		gl.glTexGendv(jarg1,jarg2,jarg3);
	checkGLError("glTexGendv");

	}

	public void glTexGenfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glTexGenfv(jarg1,jarg2,jarg3);
	checkGLError("glTexGenfv");

	}

	public void glTexGeniv(long jarg1, long jarg2, int[] jarg3) {
		gl.glTexGeniv(jarg1,jarg2,jarg3);
	checkGLError("glTexGeniv");

	}

	public void glGetTexGendv(long jarg1, long jarg2, double[] jarg3) {
		gl.glGetTexGendv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexGendv");

	}

	public void glGetTexGenfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetTexGenfv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexGenfv");

	}

	public void glGetTexGeniv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetTexGeniv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexGeniv");

	}

	public void glTexEnvfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glTexEnvfv(jarg1,jarg2,jarg3);
	checkGLError("glTexEnvfv");

	}

	public void glTexEnviv(long jarg1, long jarg2, int[] jarg3) {
		gl.glTexEnviv(jarg1,jarg2,jarg3);
	checkGLError("glTexEnviv");

	}

	public void glGetTexEnvfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetTexEnvfv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexEnvfv");

	}

	public void glGetTexEnviv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetTexEnviv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexEnviv");

	}

	public void glTexParameterfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glTexParameterfv(jarg1,jarg2,jarg3);
	checkGLError("glTexParameterfv");

	}

	public void glTexParameteriv(long jarg1, long jarg2, int[] jarg3) {
		gl.glTexParameteriv(jarg1,jarg2,jarg3);
	checkGLError("glTexParameteriv");

	}

	public void glGetTexParameterfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetTexParameterfv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexParameterfv");

	}

	public void glGetTexParameteriv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetTexParameteriv(jarg1,jarg2,jarg3);
	checkGLError("glGetTexParameteriv");

	}

	public void glGetTexLevelParameterfv(long jarg1, int jarg2, long jarg3, float[] jarg4) {
		gl.glGetTexLevelParameterfv(jarg1,jarg2,jarg3,jarg4);
	checkGLError("glGetTexLevelParameterfv");

	}

	public void glGetTexLevelParameteriv(long jarg1, int jarg2, long jarg3, int[] jarg4) {
		gl.glGetTexLevelParameteriv(jarg1,jarg2,jarg3,jarg4);
	checkGLError("glGetTexLevelParameteriv");

	}

	public void glGenTextures(int jarg1, long[] jarg2) {
		gl.glGenTextures(jarg1,jarg2);
	checkGLError("glGenTextures");

	}

	public void glDeleteTextures(int jarg1, long[] jarg2) {
		gl.glDeleteTextures(jarg1,jarg2);
	checkGLError("glDeleteTextures");

	}

	public void glPrioritizeTextures(int jarg1, long[] jarg2, float[] jarg3) {
		gl.glPrioritizeTextures(jarg1,jarg2,jarg3);
	checkGLError("glPrioritizeTextures");

	}

	public short glAreTexturesResident(int jarg1, long[] jarg2, short[] jarg3) {
	short value = 	gl.glAreTexturesResident(jarg1,jarg2,jarg3);
	checkGLError("glAreTexturesResident");
		return value;

	}

	public void glMap1d(long jarg1, double jarg2, double jarg3, int jarg4, int jarg5, double[] jarg6) {
		gl.glMap1d(jarg1,jarg2,jarg3,jarg4,jarg5,jarg6);
	checkGLError("glMap1d");

	}

	public void glMap1f(long jarg1, float jarg2, float jarg3, int jarg4, int jarg5, float[] jarg6) {
		gl.glMap1f(jarg1,jarg2,jarg3,jarg4,jarg5,jarg6);
	checkGLError("glMap1f");

	}

	public void glMap2d(long jarg1, double jarg2, double jarg3, int jarg4, int jarg5, double jarg6, double jarg7, int jarg8, int jarg9, double[] jarg10) {
		gl.glMap2d(jarg1,jarg2,jarg3,jarg4,jarg5,jarg6,jarg7,jarg8,jarg9,jarg10);
	checkGLError("glMap2d");

	}

	public void glMap2f(long jarg1, float jarg2, float jarg3, int jarg4, int jarg5, float jarg6, float jarg7, int jarg8, int jarg9, float[] jarg10) {
		gl.glMap2f(jarg1,jarg2,jarg3,jarg4,jarg5,jarg6,jarg7,jarg8,jarg9,jarg10);
	checkGLError("glMap2f");

	}

	public void glGetMapdv(long jarg1, long jarg2, double[] jarg3) {
		gl.glGetMapdv(jarg1,jarg2,jarg3);
	checkGLError("glGetMapdv");

	}

	public void glGetMapfv(long jarg1, long jarg2, float[] jarg3) {
		gl.glGetMapfv(jarg1,jarg2,jarg3);
	checkGLError("glGetMapfv");

	}

	public void glGetMapiv(long jarg1, long jarg2, int[] jarg3) {
		gl.glGetMapiv(jarg1,jarg2,jarg3);
	checkGLError("glGetMapiv");

	}

	public void glEvalCoord1dv(double[] jarg1) {
		gl.glEvalCoord1dv(jarg1);
	checkGLError("glEvalCoord1dv");

	}

	public void glEvalCoord1fv(float[] jarg1) {
		gl.glEvalCoord1fv(jarg1);
	checkGLError("glEvalCoord1fv");

	}

	public void glEvalCoord2dv(double[] jarg1) {
		gl.glEvalCoord2dv(jarg1);
	checkGLError("glEvalCoord2dv");

	}

	public void glEvalCoord2fv(float[] jarg1) {
		gl.glEvalCoord2fv(jarg1);
	checkGLError("glEvalCoord2fv");

	}

	public void glFogfv(long jarg1, float[] jarg2) {
		gl.glFogfv(jarg1,jarg2);
	checkGLError("glFogfv");

	}

	public void glFogiv(long jarg1, int[] jarg2) {
		gl.glFogiv(jarg1,jarg2);
	checkGLError("glFogiv");

	}

	public void glFeedbackBuffer(int jarg1, long jarg2, float[] jarg3) {
		gl.glFeedbackBuffer(jarg1,jarg2,jarg3);
	checkGLError("glFeedbackBuffer");

	}

	public void glSelectBuffer(int jarg1, long[] jarg2) {
		gl.glSelectBuffer(jarg1,jarg2);
	checkGLError("glSelectBuffer");

	}

	public void gluBeginCurve(GLUnurbs nurb) {
		gl.gluBeginCurve(nurb);
	checkGLError("gluBeginCurve");

	}

	public void gluBeginPolygon(GLUtesselator tess) {
		gl.gluBeginPolygon(tess);
	checkGLError("gluBeginPolygon");

	}

	public void gluBeginSurface(GLUnurbs nurb) {
		gl.gluBeginSurface(nurb);
	checkGLError("gluBeginSurface");

	}

	public void gluBeginTrim(GLUnurbs nurb) {
		gl.gluBeginTrim(nurb);
	checkGLError("gluBeginTrim");

	}

	public int gluBuild1DMipmaps(long target, int internalFormat, int width, long format, long type, Buffer data) {
	int value = 	gl.gluBuild1DMipmaps(target,internalFormat,width,format,type,data);
	checkGLError("gluBuild1DMipmaps");
		return value;

	}

	public int gluBuild2DMipmaps(long target, int internalFormat, int width, int height, long format, long type, Buffer data) {
	int value = 	gl.gluBuild2DMipmaps(target,internalFormat,width,height,format,type,data);
	checkGLError("gluBuild2DMipmaps");
		return value;

	}

	public void gluCylinder(GLUquadric quad, double base, double top, double height, int slices, int stacks) {
		gl.gluCylinder(quad,base,top,height,slices,stacks);
	checkGLError("gluCylinder");

	}

	public void gluDeleteNurbsRenderer(GLUnurbs nurb) {
		gl.gluDeleteNurbsRenderer(nurb);
	checkGLError("gluDeleteNurbsRenderer");

	}

	public void gluDeleteQuadric(GLUquadric quad) {
		gl.gluDeleteQuadric(quad);
	checkGLError("gluDeleteQuadric");

	}

	public void gluDeleteTess(GLUtesselator tess) {
		gl.gluDeleteTess(tess);
	checkGLError("gluDeleteTess");

	}

	public void gluDisk(GLUquadric quad, double inner, double outer, int slices, int loops) {
		gl.gluDisk(quad,inner,outer,slices,loops);
	checkGLError("gluDisk");

	}

	public void gluEndCurve(GLUnurbs nurb) {
		gl.gluEndCurve(nurb);
	checkGLError("gluEndCurve");

	}

	public void gluEndPolygon(GLUtesselator tess) {
		gl.gluEndPolygon(tess);
	checkGLError("gluEndPolygon");

	}

	public void gluEndSurface(GLUnurbs nurb) {
		gl.gluEndSurface(nurb);
	checkGLError("gluEndSurface");

	}

	public void gluEndTrim(GLUnurbs nurb) {
		gl.gluEndTrim(nurb);
	checkGLError("gluEndTrim");

	}

	public ShortBuffer gluErrorString(long error) {
	ShortBuffer value = 	gl.gluErrorString(error);
	checkGLError("gluErrorString");
		return value;

	}

	public void gluGetNurbsProperty(GLUnurbs nurb, long property, FloatBuffer data) {
		gl.gluGetNurbsProperty(nurb,property,data);
	checkGLError("gluGetNurbsProperty");

	}

	public ShortBuffer gluGetString(long name) {
	ShortBuffer value = 	gl.gluGetString(name);
	checkGLError("gluGetString");
		return value;

	}

	public void gluGetTessProperty(GLUtesselator tess, long which, DoubleBuffer data) {
		gl.gluGetTessProperty(tess,which,data);
	checkGLError("gluGetTessProperty");

	}

	public void gluLoadSamplingMatrices(GLUnurbs nurb, FloatBuffer model, FloatBuffer perspective, IntBuffer view) {
		gl.gluLoadSamplingMatrices(nurb,model,perspective,view);
	checkGLError("gluLoadSamplingMatrices");

	}

	public void gluLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
		gl.gluLookAt(eyeX,eyeY,eyeZ,centerX,centerY,centerZ,upX,upY,upZ);
	checkGLError("gluLookAt");

	}

	public GLUnurbs gluNewNurbsRenderer() {
	GLUnurbs value = 	gl.gluNewNurbsRenderer();
	checkGLError("gluNewNurbsRenderer");
		return value;

	}

	public GLUquadric gluNewQuadric() {
	GLUquadric value = 	gl.gluNewQuadric();
	checkGLError("gluNewQuadric");
		return value;

	}

	public GLUtesselator gluNewTess() {
	GLUtesselator value = 	gl.gluNewTess();
	checkGLError("gluNewTess");
		return value;

	}

	public void gluNextContour(GLUtesselator tess, long type) {
		gl.gluNextContour(tess,type);
	checkGLError("gluNextContour");

	}

	public void gluNurbsCurve(GLUnurbs nurb, int knotCount, FloatBuffer knots, int stride, FloatBuffer control, int order, long type) {
		gl.gluNurbsCurve(nurb,knotCount,knots,stride,control,order,type);
	checkGLError("gluNurbsCurve");

	}

	public void gluNurbsProperty(GLUnurbs nurb, long property, float value) {
		gl.gluNurbsProperty(nurb,property,value);
	checkGLError("gluNurbsProperty");

	}

	public void gluNurbsSurface(GLUnurbs nurb, int sKnotCount, FloatBuffer sKnots, int tKnotCount, FloatBuffer tKnots, int sStride, int tStride, FloatBuffer control, int sOrder, int tOrder, long type) {
		gl.gluNurbsSurface(nurb,sKnotCount,sKnots,tKnotCount,tKnots,sStride,tStride,control,sOrder,tOrder,type);
	checkGLError("gluNurbsSurface");

	}

	public void gluOrtho2D(double left, double right, double bottom, double top) {
		gl.gluOrtho2D(left,right,bottom,top);
	checkGLError("gluOrtho2D");

	}

	public void gluPartialDisk(GLUquadric quad, double inner, double outer, int slices, int loops, double start, double sweep) {
		gl.gluPartialDisk(quad,inner,outer,slices,loops,start,sweep);
	checkGLError("gluPartialDisk");

	}

	public void gluPerspective(double fovy, double aspect, double zNear, double zFar) {
		gl.gluPerspective(fovy,aspect,zNear,zFar);
	checkGLError("gluPerspective");

	}

	public void gluPickMatrix(double x, double y, double delX, double delY, IntBuffer viewport) {
		gl.gluPickMatrix(x,y,delX,delY,viewport);
	checkGLError("gluPickMatrix");

	}

	public int gluProject(double objX, double objY, double objZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer winX, DoubleBuffer winY, DoubleBuffer winZ) {
	int value = 	gl.gluProject(objX,objY,objZ,model,proj,view,winX,winY,winZ);
	checkGLError("gluProject");
		return value;

	}

	public void gluPwlCurve(GLUnurbs nurb, int count, FloatBuffer data, int stride, long type) {
		gl.gluPwlCurve(nurb,count,data,stride,type);
	checkGLError("gluPwlCurve");

	}

	public void gluQuadricDrawStyle(GLUquadric quad, long draw) {
		gl.gluQuadricDrawStyle(quad,draw);
	checkGLError("gluQuadricDrawStyle");

	}

	public void gluQuadricNormals(GLUquadric quad, long normal) {
		gl.gluQuadricNormals(quad,normal);
	checkGLError("gluQuadricNormals");

	}

	public void gluQuadricOrientation(GLUquadric quad, long orientation) {
		gl.gluQuadricOrientation(quad,orientation);
	checkGLError("gluQuadricOrientation");

	}

	public void gluQuadricTexture(GLUquadric quad, short texture) {
		gl.gluQuadricTexture(quad,texture);
	checkGLError("gluQuadricTexture");

	}

	public int gluScaleImage(long format, int wIn, int hIn, long typeIn, Buffer dataIn, int wOut, int hOut, long typeOut, Buffer dataOut) {
	int value = 	gl.gluScaleImage(format,wIn,hIn,typeIn,dataIn,wOut,hOut,typeOut,dataOut);
	checkGLError("gluScaleImage");
		return value;

	}

	public void gluSphere(GLUquadric quad, double radius, int slices, int stacks) {
		gl.gluSphere(quad,radius,slices,stacks);
	checkGLError("gluSphere");

	}

	public void gluTessBeginContour(GLUtesselator tess) {
		gl.gluTessBeginContour(tess);
	checkGLError("gluTessBeginContour");

	}

	public void gluTessBeginPolygon(GLUtesselator tess, Buffer data) {
		gl.gluTessBeginPolygon(tess,data);
	checkGLError("gluTessBeginPolygon");

	}

	public void gluTessEndContour(GLUtesselator tess) {
		gl.gluTessEndContour(tess);
	checkGLError("gluTessEndContour");

	}

	public void gluTessEndPolygon(GLUtesselator tess) {
		gl.gluTessEndPolygon(tess);
	checkGLError("gluTessEndPolygon");

	}

	public void gluTessNormal(GLUtesselator tess, double valueX, double valueY, double valueZ) {
		gl.gluTessNormal(tess,valueX,valueY,valueZ);
	checkGLError("gluTessNormal");

	}

	public void gluTessProperty(GLUtesselator tess, long which, double data) {
		gl.gluTessProperty(tess,which,data);
	checkGLError("gluTessProperty");

	}

	public void gluTessVertex(GLUtesselator tess, DoubleBuffer location, Buffer data) {
		gl.gluTessVertex(tess,location,data);
	checkGLError("gluTessVertex");

	}

	public int gluUnProject(double winX, double winY, double winZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer objX, DoubleBuffer objY, DoubleBuffer objZ) {
	int value = 	gl.gluUnProject(winX,winY,winZ,model,proj,view,objX,objY,objZ);
	checkGLError("gluUnProject");
		return value;

	}

    public long glGetError() {
        return gl.glGetError();
    }

    void checkGLError(String caller) {
      if (insideBeginEndPair) {
          return;
      }
    
      int error = (int) gl.glGetError();
      if (error == gl.GL_NO_ERROR) return;
    
      StringBuffer buf = new StringBuffer("glGetError() return the following error codes after call to " + caller + "(): ");
    
        // Loop repeatedly to allow for distributed GL implementations,
        // as detailed in the glGetError() specification
        do {
          switch (error) {
            case GL.GL_INVALID_ENUM: buf.append("GL_INVALID_ENUM "); break;
            case GL.GL_INVALID_VALUE: buf.append("GL_INVALID_VALUE "); break;
            case GL.GL_INVALID_OPERATION: buf.append("GL_INVALID_OPERATION "); break;
            case GL.GL_STACK_OVERFLOW: buf.append("GL_STACK_OVERFLOW "); break;
            case GL.GL_STACK_UNDERFLOW: buf.append("GL_STACK_UNDERFLOW "); break;
            case GL.GL_OUT_OF_MEMORY: buf.append("GL_OUT_OF_MEMORY "); break;
            case GL.GL_NO_ERROR: throw new InternalError("Should not be treating GL_NO_ERROR as error");
            default: throw new InternalError("Unknown glGetError() return value: " + error);
          }
        }while ((error = (int)gl.glGetError()) != gl.GL_NO_ERROR);
    
        throw new GLException(buf.toString());
        }

}
