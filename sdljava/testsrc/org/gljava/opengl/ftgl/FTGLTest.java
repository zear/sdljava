package org.gljava.opengl.ftgl;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.event.SDLEvent;
import sdljava.event.SDLEventType;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLQuitEvent;
import sdljava.event.SDLKey;

import sdljava.util.BufferUtil;

import org.gljava.opengl.GL;

import java.io.File;

import java.util.List;
import java.util.ArrayList;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ByteOrder;

public class FTGLTest extends SDLTest {

    static SDLSurface framebuffer;
    static FTFont     currentFont;
    static FTFont     infoFont;
    static List       fontList = new ArrayList();

    static int textureID;

    static float texture[] = { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
			       1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
			       0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
			       0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

    static String currentText = "A";
    static String fontFile = "arial.ttf";

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, SDLVideo.SDL_OPENGL|SDLVideo.SDL_DOUBLEBUF);

	GL gl = framebuffer.getGL();

	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	gl.glClearColor( 0.13f, 0.17f, 0.32f, 0.0f);
	gl.glColor3f( 1.0f, 1.0f, 1.0f);
	
	gl.glEnable( GL.GL_CULL_FACE);
	gl.glFrontFace( GL.GL_CCW);
	
	gl.glEnable( GL.GL_DEPTH_TEST);
	gl.glEnable(GL.GL_CULL_FACE);
	gl.glShadeModel(GL.GL_SMOOTH);

	gl.glEnable( GL.GL_POLYGON_OFFSET_LINE);
	gl.glPolygonOffset( 1.0f, 1.0f); // ????

	IntBuffer buf = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
	gl.glGenTextures(1, buf);
	textureID = buf.get(0);
	
	gl.glBindTexture(GL.GL_TEXTURE_2D, textureID);
	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
	gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 4, 4, 0, GL.GL_RGB, GL.GL_FLOAT, BufferUtil.createFloatBuffer(texture));
	 	
	setCamera(gl);
    }

    public void initLighting(GL gl) {
	// Set up lighting.
	float light1_ambient[]  = { 1.0f, 1.0f, 1.0f, 1.0f };
	float light1_diffuse[]  = { 1.0f, 0.9f, 0.9f, 1.0f };
	float light1_specular[] = { 1.0f, 0.7f, 0.7f, 1.0f };
	float light1_position[] = { -1.0f, 1.0f, 1.0f, 0.f };
	gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT,  BufferUtil.createFloatBuffer(light1_ambient));
	gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE,  BufferUtil.createFloatBuffer(light1_diffuse));
	gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, BufferUtil.createFloatBuffer(light1_specular));
	gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, BufferUtil.createFloatBuffer(light1_position));
	gl.glEnable(GL.GL_LIGHT1);

	float light2_ambient[]  = { 0.2f, 0.2f, 0.2f, 1.0f };
	float light2_diffuse[]  = { 0.9f, 0.9f, 0.9f, 1.0f };
	float light2_specular[] = { 0.7f, 0.7f, 0.7f, 1.0f };
	float light2_position[] = { 1.0f, -1.0f, -1.0f, 0.0f };
	gl.glLightfv(GL.GL_LIGHT2, GL.GL_AMBIENT,  BufferUtil.createFloatBuffer(light2_ambient));
	gl.glLightfv(GL.GL_LIGHT2, GL.GL_DIFFUSE,  BufferUtil.createFloatBuffer(light2_diffuse));
	gl.glLightfv(GL.GL_LIGHT2, GL.GL_SPECULAR, BufferUtil.createFloatBuffer(light2_specular));
	gl.glLightfv(GL.GL_LIGHT2, GL.GL_POSITION, BufferUtil.createFloatBuffer(light2_position));
	gl.glEnable(GL.GL_LIGHT2);

	float front_emission[] = { 0.3f, 0.2f, 0.1f, 0.0f };
	float front_ambient[]  = { 0.2f, 0.2f, 0.2f, 0.0f };
	float front_diffuse[]  = { 0.95f, 0.95f, 0.8f, 0.0f };
	float front_specular[] = { 0.6f, 0.6f, 0.6f, 0.0f };
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, BufferUtil.createFloatBuffer(front_emission));
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT,  BufferUtil.createFloatBuffer(front_ambient));
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE,  BufferUtil.createFloatBuffer(front_diffuse));
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, BufferUtil.createFloatBuffer(front_specular));
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 16.0f);
	gl.glColor4fv(BufferUtil.createFloatBuffer(front_diffuse));

	gl.glLightModeli(GL.GL_LIGHT_MODEL_TWO_SIDE, GL.GL_FALSE);
	gl.glColorMaterial(GL.GL_FRONT, GL.GL_DIFFUSE);
	gl.glEnable(GL.GL_COLOR_MATERIAL);

	gl.glEnable(GL.GL_LIGHTING);
    }

    public void initFonts(String f) throws FTGLException {
	fontFile = f;
	
	FTFont font = null;

	font = new FTGLBitmapFont (fontFile);
	font.faceSize(144,72);
	fontList.add(font);

	font = new FTGLPixmapFont(fontFile);
	font.faceSize(144,72);
	fontList.add(font);

	font = new FTGLOutlineFont(fontFile);
	font.faceSize(144,72);
	fontList.add(font);

	font = new FTGLPolygonFont(fontFile);
	font.faceSize(144,72);
	fontList.add(font);

	font = new FTGLExtrdFont(fontFile);
	font.faceSize(144,72);
	font.depth(20);
	fontList.add(font);

	currentFont = font;

	font = new FTGLTextureFont(fontFile);
	font.faceSize(144,72);
	fontList.add(font);

	infoFont = new FTGLPixmapFont(fontFile);
	infoFont.faceSize(18, 72);


    }

    public void renderFontMetrics(GL gl) {
	BoundingBox box = currentFont.getBoundingBox(currentText);

	float x1 = box.getLlx();
	float y1 = box.getLly();
	float z1 = box.getLlz();

	float x2 = box.getUrx();
	float y2 = box.getUry();
	float z2 = box.getUrz();

	// Draw the bounding box
	gl.glDisable( GL.GL_LIGHTING);
	gl.glDisable( GL.GL_TEXTURE_2D);
	gl.glEnable ( GL.GL_LINE_SMOOTH);
	gl.glEnable ( GL.GL_BLEND);
	gl.glBlendFunc( GL.GL_SRC_ALPHA, GL.GL_ONE); // GL.GL_ONE_MINUS_SRC_ALPHA

	gl.glColor3f( 0.0f, 1.0f, 0.0f);
	// Draw the front face
	gl.glBegin( GL.GL_LINE_LOOP);
	gl.glVertex3f( x1, y1, z1);
	gl.glVertex3f( x1, y2, z1);
	gl.glVertex3f( x2, y2, z1);
	gl.glVertex3f( x2, y1, z1);
	gl.glEnd();
	// Draw the back face
	if( currentFont instanceof FTGLExtrdFont && z1 != z2)
	    {
		gl.glBegin( GL.GL_LINE_LOOP);
		gl.glVertex3f( x1, y1, z2);
		gl.glVertex3f( x1, y2, z2);
		gl.glVertex3f( x2, y2, z2);
		gl.glVertex3f( x2, y1, z2);
		gl.glEnd();
		// Join the faces
		gl.glBegin( GL.GL_LINES);
		gl.glVertex3f( x1, y1, z1);
		gl.glVertex3f( x1, y1, z2);
			
		gl.glVertex3f( x1, y2, z1);
		gl.glVertex3f( x1, y2, z2);
			
		gl.glVertex3f( x2, y2, z1);
		gl.glVertex3f( x2, y2, z2);
			
		gl.glVertex3f( x2, y1, z1);
		gl.glVertex3f( x2, y1, z2);
		gl.glEnd();
	    }
		
	// Draw the baseline, ascender and descender
	gl.glBegin( GL.GL_LINES);
	{
	    gl.glColor3f( 0.0f, 0.0f, 1.0f);
	    gl.glVertex3f( 0.0f, 0.0f, 0.0f);
	    gl.glVertex3f( currentFont.advance( currentText), 0.0f, 0.0f);
	    gl.glVertex3f( 0.0f, currentFont.ascender(), 0.0f);
	    gl.glVertex3f( 0.0f, currentFont.descender(), 0.0f);
	}
	gl.glEnd();
	
	// Draw the origin
	gl.glColor3f( 1.0f, 0.0f, 0.0f);
	gl.glPointSize( 5.0f);
	gl.glBegin( GL.GL_POINTS);
	gl.glVertex3f( 0.0f, 0.0f, 0.0f);
	gl.glEnd();
    }

    public void renderFontInfo(GL gl ) {
	gl.glMatrixMode( gl.GL_PROJECTION);
	gl.glLoadIdentity();
	gl.gluOrtho2D(0, framebuffer.getWidth(), 0, framebuffer.getHeight());
	gl.glMatrixMode(gl.GL_MODELVIEW);
	gl.glLoadIdentity();

	// draw mode
	gl.glColor3f( 1.0f, 1.0f, 1.0f);
	gl.glRasterPos2f( 20.0f , framebuffer.getHeight() - ( 20.0f + infoFont.ascender()));

	//infoFont.render("Edit Mode");

	// draw font type
	gl.glRasterPos2i( 20 , (int)infoFont.lineHeight()*6);
	if (currentFont instanceof FTGLBitmapFont) {
	    infoFont.render("Bitmap Font");
	}
	else if (currentFont instanceof FTGLPixmapFont) {
	    infoFont.render("Pixmap Font");
	}

	else if (currentFont instanceof FTGLOutlineFont) {
	    infoFont.render("Outline Font");
	}

	else if (currentFont instanceof FTGLPolygonFont) {
	    infoFont.render("Polygon Font");
	}

	else if (currentFont instanceof FTGLExtrdFont) {
	    infoFont.render("Extruded Font");
	}

	else if (currentFont instanceof FTGLTextureFont) {
	    infoFont.render("Texture Font");
	}

	gl.glRasterPos2f( 20.0f , 140f + infoFont.lineHeight());
	infoFont.render(fontFile);
    }

    public void doDisplay(GL gl) {
	if (currentFont instanceof FTGLBitmapFont ||
	    currentFont instanceof FTGLPixmapFont ||
	    currentFont instanceof FTGLOutlineFont) {
	}

	else if (currentFont instanceof FTGLPolygonFont) {
	    gl.glEnable( gl.GL_TEXTURE_2D);
	    gl.glBindTexture(gl.GL_TEXTURE_2D, textureID);
	    gl.glDisable( gl.GL_BLEND);
	    initLighting(gl);
	}

	else if (currentFont instanceof FTGLExtrdFont) {
	    gl.glEnable( GL.GL_DEPTH_TEST);
	    gl.glDisable( GL.GL_BLEND);
            gl.glEnable( GL.GL_TEXTURE_2D);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureID);
	    initLighting(gl);
	}

	else if (currentFont instanceof FTGLTextureFont) {
	    gl.glEnable( GL.GL_TEXTURE_2D);
	    gl.glDisable( GL.GL_DEPTH_TEST);
	    initLighting(gl);
	    gl.glNormal3f( 0.0f, 0.0f, 1.0f);
	}

	gl.glColor3f( 1.0f, 1.0f, 1.0f);

	// If you do want to switch the color of bitmaps rendered with glBitmap,
	// you will need to explicitly call glRasterPos3f (or its ilk) to lock
	// in a changed current color.

	gl.glPushMatrix();
        currentFont.render( currentText);
	gl.glPopMatrix();

	gl.glPushMatrix();
        renderFontMetrics(gl);
	gl.glPopMatrix();

	renderFontInfo(gl);
    }

    public void display(GL gl) {
	gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

	setCamera(gl);

	if (currentFont instanceof FTGLBitmapFont ||
	    currentFont instanceof FTGLPixmapFont) {
	    
	    gl.glRasterPos2i(framebuffer.getWidth() / 2,framebuffer.getHeight()/ 2);
	    gl.glTranslatef ( (float)framebuffer.getWidth() / 2,(float)framebuffer.getHeight()/ 2, 0.0f);
	}

	else {
	    // tbMatrix
	}
	
	gl.glPushMatrix();

	doDisplay(gl);

	gl.glPopMatrix();

	framebuffer.glSwapBuffers();
    }

    public void setCamera(GL gl) {
	if (currentFont instanceof FTGLBitmapFont ||
	    currentFont instanceof FTGLPixmapFont) {
	    gl.glMatrixMode( GL.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.gluOrtho2D(0, framebuffer.getWidth(), 0, framebuffer.getHeight());
	    gl.glMatrixMode(GL.GL_MODELVIEW);
	    gl.glLoadIdentity();
	}

	if (currentFont instanceof FTGLOutlineFont ||
	    currentFont instanceof FTGLPixmapFont  ||
	    currentFont instanceof FTGLExtrdFont   ||
	    currentFont instanceof FTGLTextureFont ) {

	    gl.glMatrixMode (GL.GL_PROJECTION);
	    gl.glLoadIdentity ();
	    gl.gluPerspective( 90, (float)framebuffer.getWidth() / (float)framebuffer.getHeight(), 1, 1000);
	    gl.glMatrixMode(GL.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.gluLookAt( 0.0, 0.0, (float)framebuffer.getHeight() / 2.0f, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	    
	    }
    }

    

    void handleKeyboardEvent(SDLKeyboardEvent keyEvent) {
	//System.out.println("handleKeyboardEvent: " + event);
	if (keyEvent.getType() != SDLEventType.KEYDOWN) return;
	
	switch (keyEvent.getSym()) {
	    case SDLKey.SDLK_SPACE:
		int index = fontList.indexOf(currentFont);
		if (index == fontList.size()-1) {
		    currentFont = (FTFont) fontList.get(0);
		}
		else {
		    currentFont = (FTFont) fontList.get(index+1);
		}
		break;
	    case SDLKey.SDLK_ESCAPE:
		System.exit(1);
		
	    default:
		currentText = SDLEvent.getKeyName(keyEvent.getSym());
	}
    }

    void handleEvents(GL gl) throws SDLException, InterruptedException {
	while (true) {
	    display(gl);

	    SDLEvent event = SDLEvent.pollEvent();
	    if(event instanceof SDLQuitEvent) {
		return;
	    }

	    else if (event instanceof SDLKeyboardEvent) {
		handleKeyboardEvent((SDLKeyboardEvent)event);
	    }
		
	    Thread.currentThread().sleep(20);
	}
    }

    public void destroy() {
	try {
	    SDLMain.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
    }

    public static void main(String[] args) {
	FTGLTest t = null;
	try {
	    t = new FTGLTest();
	    t.init();

	    System.out.println("space - switches font");

	    t.initFonts("testdata" + File.separator + "arial.ttf");

	    GL gl = framebuffer.getGL();

	    t.handleEvents(gl);
	}
	catch (Exception e) {
	    e.printStackTrace();
	} // catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}