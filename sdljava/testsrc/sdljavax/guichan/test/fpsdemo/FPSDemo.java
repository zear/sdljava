/*
 * Created on Mar 10, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.fpsdemo;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.gljava.opengl.DebugGL;
import org.gljava.opengl.GL;
import org.gljava.opengl.glu.GLUquadric;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTimer;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.mixer.MixChunk;
import sdljava.mixer.SDLMixer;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljavax.guichan.GUI;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.ActionListener;
import sdljavax.guichan.font.ImageFont;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.opengl.OpenGLGraphics;
import sdljavax.guichan.opengl.OpenGLImageLoader;
import sdljavax.guichan.sdl.SDLImageLoader;
import sdljavax.guichan.sdl.SDLInput;
import sdljavax.guichan.widgets.Container;
import sdljavax.guichan.widgets.DropDown;
import sdljavax.guichan.widgets.Icon;
import sdljavax.guichan.widgets.Label;
import sdljavax.guichan.widgets.ListBox;
import sdljavax.guichan.widgets.ScrollArea;
import sdljavax.guichan.widgets.TextBox;
import sdljavax.guichan.widgets.Widget;

/**
 * @author Rainer Koschnick <a href="mailto:rainer.koschnick@ebuconnect.de"><rainer.koschnick@ebuconnect.de></a>
 * 
 */
public class FPSDemo implements ActionListener {
	private final static int	MIX_MAX_VOLUME	= 128;
	public final static String	dataDir			= "testdata"
													+ File.separator
													+ "guichan"
													+ File.separator
													+ "fpsdemo"
													+ File.separator;

	public static void main(String[] args) {
		FPSDemo demo = null;
		try {
			demo = new FPSDemo();
			demo.run();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		} finally {
			if (null != demo) {
				try {
					demo.delete();
				} catch (Exception e) {
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
	}

	private FloatBuffer			lightAmbient		= ByteBuffer.allocateDirect(4 * 4)
														.order(ByteOrder.nativeOrder())
														.asFloatBuffer();
	private FloatBuffer			lightDiffuse		= ByteBuffer.allocateDirect(4 * 4)
														.order(ByteOrder.nativeOrder())
														.asFloatBuffer();
	private FloatBuffer			lightPosition		= ByteBuffer.allocateDirect(4 * 4)
														.order(ByteOrder.nativeOrder())
														.asFloatBuffer();
	private FloatBuffer			lightSpotDirection	= ByteBuffer.allocateDirect(3 * 4)
														.order(ByteOrder.nativeOrder())
														.asFloatBuffer();

	private Image				mBoxImage;

	private MixChunk			mChooseSound;
	private Image				mCloudImage;
	private GLUquadric			mClouds2;
	private IntBuffer			mCloudTexture;
	private float				mDeltaTime;
	private TextBox				mDemoInfo;
	private MixChunk			mEscapeSound;
	private SDLEvent			mEvent;
	private ImageFont			mFont;

	private FPSCheckBox			mFullScreen;
	private GL					mGL;
	private GUI					mGui;
	private boolean				mHaveFullscreen;
	private int					mHeight;
	private ImageFont			mHighLightFont;
	private boolean				mInit;

	private Container			mMain;
	private GLUquadric			mMoon;
	private Image				mMoonImage;
	private GLUquadric			mMoonRed;
	private Image				mMoonRedImage;
	private IntBuffer			mMoonRedTexture;
	private IntBuffer			mMoonTexture;
	private Container			mMultiplay;
	private FPSButton			mMultiplayBackButton;
	private Icon				mMultiplayBoxIcon;
	private FPSButton			mMultiplayButton;
	private Label				mMultiplayLabel;
	private TextBox				mMultiplayText;
	private MixChunk			mMusic;

	private OpenGLGraphics		mOpenGLGraphics;
	private OpenGLImageLoader	mOpenGLImageLoader;
	private Container			mOptions;
	private FPSButton			mOptionsBackButton;
	private Icon				mOptionsBoxIcon;
	private FPSButton			mOptionsButton;
	private Label				mOptionsLabel;
	private MixChunk			mOptionsSound;
	private Image				mPlanetImage;

	private IntBuffer			mPlanetTexture;

	private GLUquadric			mQuad1;
	private GLUquadric			mQuad2;
	private FPSButton			mQuitButton;
	private DropDown			mResolution;
	private boolean				mResolutionChange;
	private Label				mResolutionLabel;
	private ListBox				mResolutionListBox;
	private ResolutionListModel	mResolutionListModel;
	private ScrollArea			mResolutionScrollArea;

	private float				mRotation;
	private boolean				mRunning;

	private SDLSurface			mScreen;
	private SDLImageLoader		mSDLImageLoader;
	private SDLInput			mSDLInput;
	private Container			mSingleplay;
	private FPSButton			mSingleplayBackButton;
	private Icon				mSingleplayBoxIcon;

	private FPSButton			mSingleplayButton;
	private Label				mSingleplayLabel;
	private TextBox				mSingleplayText;
	private ImageFont			mSmallBlackFont;
	private Icon				mSplashIcon;
	private Image				mSplashImage;
	private Image				mStarsImage;
	private IntBuffer			mStarsTexture;
	private float				mTime;
	private Icon				mTitle;
	private Image				mTitleImage;
	private Container			mTop;

	private Label				mVersionLabel;

	private FPSSlider			mVolume;
	private Label				mVolumeLabel;
	private Label				mVolumePercent;
	private ImageFont			mWhiteFont;

	private int					mWidth;

	/**
	 * @throws SDLException
	 * @throws GUIException
	 * 
	 */
	public FPSDemo() throws SDLException, GUIException {
		super();
		mRotation = 0;
		mRunning = true;
		mWidth = 800;
		mHeight = 600;
		mTime = -1;
		mDeltaTime = 0;
		mInit = true;
		mResolutionChange = false;
		mHaveFullscreen = false;

		// Init SDL
		SDLMain.init(SDLMain.SDL_INIT_VIDEO | SDLMain.SDL_INIT_AUDIO);
		// SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);

		mScreen = SDLVideo.setVideoMode(800, 600, 32, SDLVideo.SDL_HWSURFACE
			| SDLVideo.SDL_OPENGL
			| SDLVideo.SDL_HWACCEL);
		mGL = new DebugGL(mScreen.getGL());

		SDLEvent.enableUNICODE(1);
		SDLEvent.enableKeyRepeat(SDLEvent.SDL_DEFAULT_REPEAT_DELAY, SDLEvent.SDL_DEFAULT_REPEAT_INTERVAL);

		SDLVideo.wmSetCaption("SDLJavaUI FPS demo", null);

		// Init SDL_Mixer
		SDLMixer.openAudio(22050, SDLMixer.AUDIO_S16SYS, 2, 1024);

		// Load sounds and music
		mChooseSound = SDLMixer.loadWAV(dataDir + "sound4.wav");
		mEscapeSound = SDLMixer.loadWAV(dataDir + "sound3.wav");
		mOptionsSound = SDLMixer.loadWAV(dataDir + "sound2.wav");
		mMusic = SDLMixer.loadWAV(dataDir + "space.ogg");

		// Set the mixer volume
		double m = MIX_MAX_VOLUME;
		double p = 70;
		SDLMixer.volume(-1, (int) (m * (p / 100)));

		// Create some GLU quadrics
		mQuad1 = mGL.gluNewQuadric();
		mQuad2 = mGL.gluNewQuadric();
		mMoon = mGL.gluNewQuadric();
		mMoonRed = mGL.gluNewQuadric();
		mClouds2 = mGL.gluNewQuadric();
		mGL.gluQuadricNormals(mQuad1, GL.GLU_SMOOTH);
		mGL.gluQuadricTexture(mQuad1, (short) GL.GL_TRUE);
		mGL.gluQuadricNormals(mQuad2, GL.GLU_SMOOTH);
		mGL.gluQuadricTexture(mQuad2, (short) GL.GL_TRUE);
		mGL.gluQuadricNormals(mMoon, GL.GLU_SMOOTH);
		mGL.gluQuadricTexture(mMoon, (short) GL.GL_TRUE);
		mGL.gluQuadricNormals(mMoonRed, GL.GLU_SMOOTH);
		mGL.gluQuadricTexture(mMoonRed, (short) GL.GL_TRUE);
		mGL.gluQuadricNormals(mClouds2, GL.GLU_SMOOTH);
		mGL.gluQuadricTexture(mClouds2, (short) GL.GL_TRUE);

		initOpenGL();
		initGui();
		resize();

		mInit = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.arkay.sdljava.evt.ActionListener#action(java.lang.String)
	 */
	public void action(String strEventID) throws GUIException {
		try {
			if (strEventID.equals("quit")) {
				SDLMixer.playChannel(-1, mEscapeSound, 0);
				mRunning = false;
			} else if (strEventID.equals("singleplay")) {
				SDLMixer.playChannel(-1, mChooseSound, 0);
				mMain.setVisible(false);
				mSingleplay.setVisible(true);
			} else if (strEventID.equals("multiplay")) {
				SDLMixer.playChannel(-1, mChooseSound, 0);
				mMain.setVisible(false);
				mMultiplay.setVisible(true);
			} else if (strEventID.equals("options")) {
				SDLMixer.playChannel(-1, mChooseSound, 0);
				mMain.setVisible(false);
				mOptions.setVisible(true);
			} else if (strEventID.equals("back")) {
				SDLMixer.playChannel(-1, mEscapeSound, 0);
				mMain.setVisible(true);
				mSingleplay.setVisible(false);
				mMultiplay.setVisible(false);
				mOptions.setVisible(false);
			} else if (strEventID.equals("fullscreen")) {
				SDLMixer.playChannel(-1, mOptionsSound, 0);
				initVideo();
			} else if (strEventID.equals("resolution")) {
				SDLMixer.playChannel(-1, mOptionsSound, 0);
				initVideo();
			} else if (strEventID.equals("volume")) {
				String text = mVolume.getPercentMarked() + "%";
				mVolumePercent.setCaption(text);
				mVolumePercent.adjustSize();
				double m = MIX_MAX_VOLUME;
				double p = mVolume.getPercentMarked();
				SDLMixer.volume(-1, (int) (m * (p / 100)));
			}
		} catch (SDLException e) {
			throw new GUIException(e);
		}
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanGui() throws GUIException {
		cleanTextures();
		cleanMain();
		cleanSingleplay();
		cleanMultiplay();
		cleanOptions();
		mBoxImage.delete();
		mFont.delete();
		mHighLightFont.delete();
		mSmallBlackFont.delete();
		mWhiteFont.delete();
		mTop.delete();
		mGui.delete();
		mSplashIcon.delete();
		mSplashImage.delete();
		// mSDLInput.delete();
		// mOpenGLGraphics.delete();
		// mOpenGLImageLoader.discard();
		mSDLImageLoader.delete();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanMain() throws GUIException {
		mSingleplayButton.delete();
		mMultiplayButton.delete();
		mOptionsButton.delete();
		mQuitButton.delete();
		mVersionLabel.delete();
		mTitle.delete();
		mTitleImage.delete();
		mDemoInfo.delete();
		mMain.delete();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanMultiplay() throws GUIException {
		mMultiplayBoxIcon.delete();
		mMultiplayBackButton.delete();
		mMultiplayText.delete();
		mMultiplayLabel.delete();
		mMultiplay.delete();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanOptions() throws GUIException {
		mOptionsBoxIcon.delete();
		mOptionsBackButton.delete();
		mResolutionLabel.delete();
		mVolumeLabel.delete();
		mVolumePercent.delete();
		mVolume.delete();
		mResolutionListBox.delete();
		mResolutionScrollArea.delete();
		mResolution.delete();
		mFullScreen.delete();
		mOptionsLabel.delete();
		mOptions.delete();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanSingleplay() throws GUIException {
		mSingleplayBoxIcon.delete();
		mSingleplayText.delete();
		mSingleplayLabel.delete();
		mSingleplay.delete();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void cleanTextures() throws GUIException {
		mPlanetImage.delete();
		mCloudImage.delete();
		mStarsImage.delete();
		mMoonImage.delete();
		mMoonRedImage.delete();
	}

	public void delete() throws SDLException, GUIException {
		cleanGui();
		SDLMixer.freeChunk(mChooseSound);
		SDLMixer.freeChunk(mEscapeSound);
		SDLMixer.freeChunk(mOptionsSound);
		SDLMixer.freeChunk(mMusic);
		SDLMixer.close();
		SDLMain.quit();
	}

	private void drawBackground() {
		mGL.glEnable(GL.GL_LIGHTING);
		mRotation += mDeltaTime / 2000;

		mGL.glPushMatrix();
		mGL.glTranslatef(0.0f, 0.0f, 1.0f);
		mGL.glRotatef(80f, 0.0f, 0.0f, 1.0f);
		mGL.glRotatef(mRotation * 7f, 0.0f, -1.0f, 0.0f);
		mGL.glTranslatef(0f, 0.0f, 2.1f);
		mGL.glRotatef(mRotation * 50f, 1.0f, 0.0f, 0.0f);
		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mMoonTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		mGL.gluSphere(mMoon, .07, 10, 10);
		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();

		mGL.glPushMatrix();
		mGL.glTranslatef(0.0f, 0.0f, 1.0f);
		mGL.glRotatef(110f, 0.0f, 1.0f, 0.0f);
		mGL.glRotatef(mRotation * 5f, 0.0f, 0.0f, 1.0f);
		mGL.glTranslatef(2.2f, 0.0f, 0.0f);
		mGL.glRotatef(mRotation * 30f, 1.0f, 0.0f, 0.0f);
		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mMoonRedTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		mGL.gluSphere(mMoonRed, 0.15, 10, 10);
		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();

		mGL.glPushMatrix();
		mGL.glTranslatef(0.0f, 0.0f, 1.0f);
		mGL.glRotatef(mRotation * 3f, 1.0f, 0.0f, 0.0f);
		mGL.glRotatef(77f, 0.0f, 1.0f, 0.0f);
		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mPlanetTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		mGL.gluSphere(mQuad1, 1.93, 60, 60);
		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();

		mGL.glPushMatrix();
		mGL.glDepthMask((short) GL.GL_FALSE);
		mGL.glEnable(GL.GL_BLEND);
		mGL.glBlendFunc(GL.GL_ONE, GL.GL_ONE);
		mGL.glTranslatef(0.0f, 0.0f, 1.0f);
		mGL.glRotatef(mRotation * 6f, 1.0f, 0.0f, 0.0f);
		mGL.glRotatef(90f, 0.0f, 1.0f, 0.0f);
		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mCloudTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		mGL.gluSphere(mQuad2, 2.0, 60, 60);
		mGL.glDisable(GL.GL_BLEND);
		mGL.glDepthMask((short) GL.GL_TRUE);
		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();

		mGL.glPushMatrix();
		mGL.glDepthMask((short) GL.GL_FALSE);
		mGL.glEnable(GL.GL_BLEND);
		mGL.glBlendFunc(GL.GL_ONE, GL.GL_ONE);
		mGL.glTranslatef(0.0f, 0.0f, 1.0f);
		mGL.glRotatef(mRotation * 5f, 1.0f, 0.0f, 0.0f);
		mGL.glRotatef(90f, 0.0f, 1.0f, 0.0f);
		mGL.glRotatef(90f, 0.0f, 0.0f, 1.0f);
		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mCloudTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		mGL.gluSphere(mQuad2, 1.98, 60, 60);
		mGL.glDisable(GL.GL_BLEND);
		mGL.glDepthMask((short) GL.GL_TRUE);
		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();

		mGL.glDisable(GL.GL_LIGHTING);
	}

	private void drawSpace() {
		int y = -200;
		mGL.glMatrixMode(GL.GL_PROJECTION);
		mGL.glPushMatrix();
		mGL.glLoadIdentity();
		mGL.glOrtho(0.0, 800, 600, 0.0, 1, 0.0);

		mGL.glEnable(GL.GL_TEXTURE_2D);
		mGL.glBindTexture(GL.GL_TEXTURE_2D, mStarsTexture.get(0));
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		mGL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

		mGL.glScaled(2, 1.5, 1);

		mGL.glBegin(GL.GL_QUADS);
		mGL.glTexCoord2f(0.0f, 0.0f);
		mGL.glVertex3i(0, y, 0);
		mGL.glTexCoord2f(1.0f, 0.0f);
		mGL.glVertex3i(mStarsImage.getWidth(), y, 0);
		mGL.glTexCoord2f(1.0f, 1.0f);
		mGL.glVertex3i(mStarsImage.getWidth(), mStarsImage.getHeight() + y, 0);
		mGL.glTexCoord2f(0.0f, 1.0f);
		mGL.glVertex3i(0, mStarsImage.getHeight() + y, 0);
		mGL.glEnd();

		mGL.glDisable(GL.GL_TEXTURE_2D);
		mGL.glPopMatrix();
		mGL.glMatrixMode(GL.GL_MODELVIEW);
	}

	/**
	 * @throws GUIException
	 * @throws SDLException
	 * 
	 */
	private void initGui() throws GUIException, SDLException {
		mSDLImageLoader = new SDLImageLoader();
		mOpenGLImageLoader = new OpenGLImageLoader(mGL, mSDLImageLoader);
		Image.setImageLoader(mOpenGLImageLoader);
		mOpenGLGraphics = new OpenGLGraphics(mGL);
		mOpenGLGraphics.setTargetPlane(mWidth, mHeight);
		mSDLInput = new SDLInput();

		mTop = new Container();
		mTop.setOpaque(false);
		mGui = new GUI();
		mGui.setTabbingEnabled(false);
		mGui.setGraphics(mOpenGLGraphics);
		mGui.setInput(mSDLInput);

		mFont = new ImageFont(dataDir + "techyfontbig2.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		mHighLightFont = new ImageFont(dataDir + "techyfontbighighlight.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		mSmallBlackFont = new ImageFont(dataDir + "techyfontblack.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		mWhiteFont = new ImageFont(dataDir + "techyfontwhite.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		Widget.setGlobalFont(mWhiteFont);

		mTitleImage = new Image(dataDir + "title2.png");
		mTitle = new Icon(mTitleImage);
		mTop.add(mTitle);

		mDemoInfo = new TextBox(
			"              Copyright 2004 (c) Darkbits. This is a Demo demonstrating Guichan with SDL and OpenGL.\n"
				+ "  Guichan is licensed under BSD. For more information about Guichan and visit http://guichan.darkbits.org.\n"
				+ "            Code Yakslem (Olof Nassen). Art Finalman (Per Larsson). Darkbits logo Haiko (Henrik Vahlgren).");
		mDemoInfo.setFont(mSmallBlackFont);
		mDemoInfo.setOpaque(false);
		mDemoInfo.setBorderSize(0);
		mDemoInfo.setEditable(false);
		mDemoInfo.setFocusable(false);
		mTop.add(mDemoInfo);

		mVersionLabel = new Label("Version 1.00");
		mVersionLabel.setFont(mSmallBlackFont);
		mTop.add(mVersionLabel);

		mBoxImage = new Image(dataDir + "box.png");

		mSplashImage = new Image(dataDir + "splash.png");
		mSplashIcon = new Icon(mSplashImage);

		if (mInit) {
			mGui.setTop(mSplashIcon);
			mSplashIcon.setPosition(mWidth / 2 - mSplashImage.getWidth() / 2, mHeight
				/ 2
				- mSplashImage.getHeight()
				/ 2);
		} else {
			mGui.setTop(mTop);
		}

		loadTextures();
		initMain();
		initSingleplay();
		initMultiplay();
		initOptions();
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void initMain() throws GUIException {
		mMain = new Container();
		mMain.setOpaque(false);
		mTop.add(mMain);

		mSingleplayButton = new FPSButton("Singleplayer");
		mSingleplayButton.setHighLightFont(mHighLightFont);
		mSingleplayButton.setEventId("singleplay");
		mSingleplayButton.addActionListener(this);
		mSingleplayButton.setFont(mFont);
		mSingleplayButton.adjustSize();
		mMain.add(mSingleplayButton);

		mMultiplayButton = new FPSButton("Multiplayer");
		mMultiplayButton.setHighLightFont(mHighLightFont);
		mMultiplayButton.setEventId("multiplay");
		mMultiplayButton.addActionListener(this);
		mMultiplayButton.setFont(mFont);
		mMultiplayButton.adjustSize();
		mMain.add(mMultiplayButton);

		mOptionsButton = new FPSButton("Options");
		mOptionsButton.setHighLightFont(mHighLightFont);
		mOptionsButton.setEventId("options");
		mOptionsButton.addActionListener(this);
		mOptionsButton.setFont(mFont);
		mOptionsButton.adjustSize();
		mMain.add(mOptionsButton);

		mQuitButton = new FPSButton("Quit");
		mQuitButton.setHighLightFont(mHighLightFont);
		mQuitButton.setEventId("quit");
		mQuitButton.addActionListener(this);
		mQuitButton.setFont(mFont);
		mQuitButton.adjustSize();
		mMain.add(mQuitButton);
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void initMultiplay() throws GUIException {
		mMultiplay = new Container();
		mMultiplay.setVisible(false);
		mMultiplay.setOpaque(false);
		mTop.add(mMultiplay);

		mMultiplayBoxIcon = new Icon(mBoxImage);
		mMultiplay.add(mMultiplayBoxIcon);

		mMultiplayLabel = new Label("Multiplayer");
		mMultiplayLabel.setFont(mWhiteFont);
		mMultiplayLabel.adjustSize();
		mMultiplay.add(mMultiplayLabel);

		mMultiplayText = new TextBox("I'm verry sorry but this is not an actuall game.\n"
			+ "It's a demonstration of the GUI library Guichan.\n"
			+ "But who knows...\n"
			+ "Maybe it will be a game here someday.\n");
		mMultiplayText.setFont(mWhiteFont);
		mMultiplayText.setOpaque(false);
		mMultiplayText.setEditable(false);
		mMultiplayText.setBorderSize(0);
		mMultiplay.add(mMultiplayText);

		mMultiplayBackButton = new FPSButton("Back");
		mMultiplayBackButton.setHighLightFont(mHighLightFont);
		mMultiplayBackButton.adjustSize();
		mMultiplayBackButton.setEventId("back");
		mMultiplayBackButton.addActionListener(this);
		mMultiplayBackButton.setFont(mFont);

		mMultiplayBackButton.adjustSize();

		mMultiplay.add(mMultiplayBackButton);
	}

	/**
	 * 
	 */
	private void initOpenGL() {
		// Init OpenGL
		mGL.glViewport(0, 0, mWidth, mHeight);
		mGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		mGL.glShadeModel(GL.GL_SMOOTH);
		mGL.glClearDepth(1.0f);
		mGL.glEnable(GL.GL_DEPTH_TEST);
		mGL.glDepthFunc(GL.GL_LEQUAL);
		mGL.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		mGL.glMatrixMode(GL.GL_PROJECTION);
		mGL.glLoadIdentity();
		mGL.gluPerspective(50.0, mWidth / mHeight, 1.0, 10.0);

		mGL.glMatrixMode(GL.GL_MODELVIEW);
		mGL.glLoadIdentity();

		// Init Light
		lightAmbient.rewind();
		lightAmbient.put(0.5f).put(0.4f).put(0.7f).put(1.0f).rewind();

		lightDiffuse.rewind();
		lightDiffuse.put(1.0f).put(1.0f).put(1.0f).put(1.0f).rewind();

		lightPosition.rewind();
		lightPosition.put(10.0f).put(.5f).put(0.0f).put(1.0f).rewind();

		lightSpotDirection.rewind();
		lightSpotDirection.put(-1.0f).put(0.0f).put(0.0f).rewind();

		mGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, lightAmbient);
		mGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightDiffuse);
		mGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition);
		mGL.glLightfv(GL.GL_LIGHT0, GL.GL_SPOT_DIRECTION, lightSpotDirection);
		mGL.glLightf(GL.GL_LIGHT0, GL.GL_SPOT_CUTOFF, 45.0f);

		mGL.glEnable(GL.GL_LIGHTING);
		mGL.glEnable(GL.GL_LIGHT0);
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void initOptions() throws GUIException {
		mOptions = new Container();
		mOptions.setVisible(false);
		mOptions.setOpaque(false);
		mTop.add(mOptions);

		mOptionsBoxIcon = new Icon(mBoxImage);
		mOptions.add(mOptionsBoxIcon);

		mOptionsLabel = new Label("Options");
		mOptionsLabel.setFont(mWhiteFont);
		mOptionsLabel.adjustSize();
		mOptions.add(mOptionsLabel);

		mFullScreen = new FPSCheckBox("Fullscreen");
		mFullScreen.setFont(mWhiteFont);
		mFullScreen.adjustSize();
		mFullScreen.setBackgroundColor(0x331010);
		mFullScreen.setForegroundColor(0xffffff);
		mFullScreen.setBaseColor(0x771010);
		mFullScreen.setEventId("fullscreen");
		mFullScreen.addActionListener(this);
		mFullScreen.setMarked(mHaveFullscreen);
		mOptions.add(mFullScreen);

		mResolutionScrollArea = new ScrollArea();
		mResolutionScrollArea.setBackgroundColor(0x331010);
		mResolutionScrollArea.setForegroundColor(0x331010);
		mResolutionScrollArea.setBaseColor(0x771010);

		mResolutionListBox = new ListBox();
		mResolutionListBox.setBackgroundColor(0x331010);
		mResolutionListBox.setForegroundColor(0x331010);
		mResolutionListBox.setBaseColor(0x771010);

		mResolutionListModel = new ResolutionListModel();
		mResolution = new DropDown(mResolutionListModel, mResolutionScrollArea, mResolutionListBox);
		mResolution.setWidth(200);
		mResolution.setBackgroundColor(0x331010);
		mResolution.setForegroundColor(0x331010);
		mResolution.setBaseColor(0x771010);

		if (mWidth == 800) {
			mResolution.setSelected(1);
		} else {
			mResolution.setSelected(0);
		}
		mResolution.setEventId("resolution");
		mResolution.addActionListener(this);
		mOptions.add(mResolution);

		mVolume = new FPSSlider();
		mVolume.setWidth(200);
		mVolume.setHeight(20);
		mVolume.setPercentMarked(70);
		mVolume.setBackgroundColor(0x331010);
		mVolume.setForegroundColor(0x331010);
		mVolume.setBaseColor(0x771010);
		mVolume.setEventId("volume");
		mVolume.addActionListener(this);
		mOptions.add(mVolume);

		mVolumePercent = new Label("70%");
		mOptions.add(mVolumePercent);

		mVolumeLabel = new Label("Volume");
		mOptions.add(mVolumeLabel);

		mResolutionLabel = new Label("Resolution");
		mOptions.add(mResolutionLabel);

		mOptionsBackButton = new FPSButton("Back");
		mOptionsBackButton.setHighLightFont(mHighLightFont);
		mOptionsBackButton.adjustSize();
		mOptionsBackButton.setEventId("back");
		mOptionsBackButton.setFont(mFont);
		mOptionsBackButton.adjustSize();
		mOptionsBackButton.addActionListener(this);
		mOptions.add(mOptionsBackButton);
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void initSingleplay() throws GUIException {
		mSingleplay = new Container();
		mSingleplay.setVisible(false);
		mSingleplay.setOpaque(false);
		mTop.add(mSingleplay);

		mSingleplayBoxIcon = new Icon(mBoxImage);
		mSingleplay.add(mSingleplayBoxIcon);

		mSingleplayLabel = new Label("Singleplayer");
		mSingleplayLabel.setFont(mWhiteFont);
		mSingleplayLabel.adjustSize();
		mSingleplay.add(mSingleplayLabel);

		mSingleplayText = new TextBox("I'm verry sorry but this is not an actual game.\n"
			+ "It's a demonstration of the GUI library Guichan.\n"
			+ "But who knows...\n"
			+ "Maybe it will be a game here someday.\n");
		mSingleplayText.setFont(mWhiteFont);
		mSingleplayText.setOpaque(false);
		mSingleplayText.setEditable(false);
		mSingleplayText.setBorderSize(0);
		mSingleplay.add(mSingleplayText);

		mSingleplayBackButton = new FPSButton("Back");
		mSingleplayBackButton.setHighLightFont(mHighLightFont);
		mSingleplayBackButton.adjustSize();
		mSingleplayBackButton.setEventId("back");
		mSingleplayBackButton.addActionListener(this);
		mSingleplayBackButton.setFont(mFont);
		mSingleplayBackButton.adjustSize();

		mSingleplay.add(mSingleplayBackButton);
	}

	/**
	 * @throws SDLException
	 * @throws GUIException
	 * 
	 */
	private void initVideo() throws SDLException, GUIException {
		if (mResolution.getSelected() == 0) {
			mWidth = 1024;
			mHeight = 768;
			mResolutionChange = true;
		} else if (mResolution.getSelected() == 1) {
			mWidth = 800;
			mHeight = 600;
			mResolutionChange = true;
		}
		if (mFullScreen.isMarked()) {
			mScreen = SDLVideo.setVideoMode(mWidth, mHeight, 32, SDLVideo.SDL_HWSURFACE
				| SDLVideo.SDL_OPENGL
				| SDLVideo.SDL_HWACCEL
				| SDLVideo.SDL_FULLSCREEN);
			mHaveFullscreen = true;
		} else {
			mHaveFullscreen = false;
			mScreen = SDLVideo.setVideoMode(mWidth, mHeight, 32, SDLVideo.SDL_HWSURFACE
				| SDLVideo.SDL_OPENGL
				| SDLVideo.SDL_HWACCEL);
		}
		mOpenGLGraphics.setTargetPlane(mWidth, mHeight);
		initOpenGL();
	}

	/**
	 * @throws GUIException
	 * @throws SDLException
	 * 
	 */
	private void input() throws GUIException, SDLException {
		while (null != (mEvent = SDLEvent.pollEvent())) {
			if (mEvent.getType() == SDLEvent.SDL_KEYDOWN) {
				if (((SDLKeyboardEvent) mEvent).getSym() == SDLKey.SDLK_ESCAPE) {
					mMain.setVisible(true);
					mSingleplay.setVisible(false);
					mMultiplay.setVisible(false);
					mOptions.setVisible(false);
				}
			} else if (mEvent.getType() == SDLEvent.SDL_QUIT) {
				mRunning = false;
			}
			// We ignore keyboard input and just sends mouse input to Guichan
			else if (mEvent.getType() == SDLEvent.SDL_MOUSEMOTION
				|| mEvent.getType() == SDLEvent.SDL_MOUSEBUTTONDOWN
				|| mEvent.getType() == SDLEvent.SDL_MOUSEBUTTONUP) {
				mSDLInput.pushInput(mEvent);
			}
		}
	}

	/**
	 * @throws GUIException
	 * 
	 */
	private void loadTextures() throws GUIException {
		// Load textures with the OpenGLImageLoader from Guichan
		mCloudImage = new Image(dataDir + "cloudsblackwhite.png");
		mCloudTexture = (IntBuffer) mCloudImage.getData();

		mPlanetImage = new Image(dataDir + "planet.png");
		mPlanetTexture = (IntBuffer) mPlanetImage.getData();

		mStarsImage = new Image(dataDir + "background.png");
		mStarsTexture = (IntBuffer) mStarsImage.getData();

		mMoonImage = new Image(dataDir + "moon.png");
		mMoonTexture = (IntBuffer) mMoonImage.getData();

		mMoonRedImage = new Image(dataDir + "moonred.png");
		mMoonRedTexture = (IntBuffer) mMoonRedImage.getData();
	}

	/**
	 * 
	 */
	private void resize() {
		mTitle.setPosition(mWidth / 2 - 330, mHeight / 2 - 290);
		mDemoInfo.setPosition(mWidth / 2 - 390, mHeight - 50);
		mVersionLabel.setPosition(mWidth - 100, mHeight - 80);

		mTop.setDimension(new Rectangle(0, 0, mWidth, mHeight));
		mMain.setDimension(new Rectangle(0, 0, mWidth, mHeight));
		mSingleplay.setDimension(new Rectangle(0, 0, mWidth, mHeight));
		mMultiplay.setDimension(new Rectangle(0, 0, mWidth, mHeight));
		mOptions.setDimension(new Rectangle(0, 0, mWidth, mHeight));

		mSingleplayButton.setPosition(mWidth / 2 - 100, mHeight / 2 - 100);
		mMultiplayButton.setPosition(mWidth / 2 - 100, mHeight / 2 - 60);
		mOptionsButton.setPosition(mWidth / 2 - 100, mHeight / 2 - 20);
		mQuitButton.setPosition(mWidth / 2 - 100, mHeight / 2 + 60);

		mSingleplayBackButton.setPosition(mWidth / 2 - 290, mHeight / 2 + 180);
		mMultiplayBackButton.setPosition(mWidth / 2 - 290, mHeight / 2 + 180);
		mOptionsBackButton.setPosition(mWidth / 2 - 290, mHeight / 2 + 180);

		mSingleplayBoxIcon.setPosition(mWidth / 2 - 300, mHeight / 2 - 150);
		mMultiplayBoxIcon.setPosition(mWidth / 2 - 300, mHeight / 2 - 150);
		mOptionsBoxIcon.setPosition(mWidth / 2 - 300, mHeight / 2 - 150);

		mSingleplayText.setPosition(mWidth / 2 - 285, mHeight / 2 - 120);
		mSingleplayLabel.setPosition(mWidth / 2 + 150, mHeight / 2 - 145);

		mMultiplayText.setPosition(mWidth / 2 - 285, mHeight / 2 - 120);
		mMultiplayLabel.setPosition(mWidth / 2 + 150, mHeight / 2 - 145);

		mOptionsLabel.setPosition(mWidth / 2 + 150, mHeight / 2 - 145);
		mFullScreen.setPosition(mWidth / 2 - 200, mHeight / 2 - 100);
		mResolution.setPosition(mWidth / 2 - 90, mHeight / 2 - 70);
		mResolutionLabel.setPosition(mWidth / 2 - 200, mHeight / 2 - 70);
		mVolume.setPosition(mWidth / 2 - 90, mHeight / 2 - 40);
		mVolumePercent.setPosition(mWidth / 2 + 120, mHeight / 2 - 40);
		mVolumeLabel.setPosition(mWidth / 2 - 200, mHeight / 2 - 40);
	}

	private void run() throws GUIException, SDLException {
		runIntro();
		runMain();
	}

	/**
	 * @throws GUIException
	 * @throws SDLException
	 * 
	 */
	private void runIntro() throws GUIException, SDLException {
		while (SDLTimer.getTicks() < 3000) {
			if (mTime < 0) {
				mTime = SDLTimer.getTicks();
			}
			mDeltaTime = SDLTimer.getTicks() - mTime;
			mTime = SDLTimer.getTicks();

			input();

			mGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			mGui.draw();
			mScreen.glSwapBuffers();
			try {
				SDLTimer.delay(10);
			} catch (InterruptedException e) {
			}
		}
		mGui.setTop(mTop);
		if (null != mMusic) {
			SDLMixer.playChannel(-1, mMusic, -1);
		}
	}

	/**
	 * @throws GUIException
	 * @throws SDLException
	 * 
	 */
	private void runMain() throws GUIException, SDLException {
		while (mRunning) {
			if (mResolutionChange) {
				// Clear the screen before remaking the Gui
				mGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
				mScreen.glSwapBuffers();

				cleanGui();
				initGui();
				resize();
				mResolutionChange = false;
			}

			if (mTime < 0) {
				mTime = SDLTimer.getTicks();
			}

			mDeltaTime = SDLTimer.getTicks() - mTime;
			mTime = SDLTimer.getTicks();

			input();
			mGui.logic();

			mGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

			mGL.glLoadIdentity();

			drawSpace();
			mGL.gluLookAt(0.0, -1.8, -2.9, 0.0, -1.2, 1.0, 0.0, -1.0, 0.0);

			drawBackground();
			mGL.glPushMatrix();
			mGui.draw();
			mGL.glPopMatrix();

			mScreen.glSwapBuffers();
			try {
				SDLTimer.delay(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
