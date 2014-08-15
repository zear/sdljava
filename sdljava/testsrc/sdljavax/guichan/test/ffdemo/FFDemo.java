/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.ffdemo;

import java.io.File;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTimer;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.mixer.MixChunk;
import sdljava.mixer.SDLMixer;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljava.x.swig.SWIG_SDLEvent;
import sdljavax.guichan.GUI;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.ActionListener;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.font.Font;
import sdljavax.guichan.font.ImageFont;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.sdl.SDLGraphics;
import sdljavax.guichan.sdl.SDLImageLoader;
import sdljavax.guichan.sdl.SDLInput;
import sdljavax.guichan.widgets.Container;
import sdljavax.guichan.widgets.Icon;
import sdljavax.guichan.widgets.Label;
import sdljavax.guichan.widgets.TextBox;
import sdljavax.guichan.widgets.Widget;

public class FFDemo implements ActionListener, KeyListener {

    public static final String dataDir = "testdata" + File.separator + "guichan" + File.separator + "ffdemo" + File.separator;
	
	public static void main(String[] args) {
		try {
			FFDemo demo = new FFDemo();
			demo.run();
			demo.delete();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	private FFContainer			mAbout;

	private TextBox				mAboutInfo;
	private FFScrollArea		mAboutScrollArea;

	private FFCharacterChooser	mCharacterChooser;
	private MixChunk			mChooseSound;
	private MixChunk			mEscapeSound;
	private SDLEvent			mEvent;
	private Font				mFontCyan;
	private Font				mFontWhite;

	private boolean				mFullScreen	= false;
	private FFContainer			mGoldFootsteps;

	private TextBox				mGoldFootstepsInfo1;
	private TextBox				mGoldFootstepsInfo2;
	private GUI					mGui;
	private FFContainer			mItems;
	private FFContainer			mItemsInfo;
	private TextBox				mItemsInfoInfo;
	private StringListModel		mItemsInfoListModel;

	private FFListBox			mItemsList;
	private StringListModel		mItemsListModel;
	private FFScrollArea		mItemsScrollArea;
	private FFContainer			mMagicSkills;

	private FFListBox			mMagicSkillsList;
	private FFScrollArea		mMagicSkillsScroll;
	private FFContainer			mMain;
	private FFContainer			mMenu;

	private FFListBox			mMenuList;
	private StringListModel		mMenuListModel;

	private Label				mNavigationLabel;
	private Icon				mOlofIcon;
	private Image				mOlofImage;
	private TextBox				mOlofInfo1;
	private TextBox				mOlofInfo2;
	private StringListModel		mOlofMagic;
	private StringListModel		mOlofSkills;
	private TextBox				mOlofStatus1;
	private TextBox				mOlofStatus2;

	private Icon				mPerIcon;
	private Image				mPerImage;

	private TextBox				mPerInfo1;
	private TextBox				mPerInfo2;
	private StringListModel		mPerMagic;

	private StringListModel		mPerSkills;
	private TextBox				mPerStatus1;
	private TextBox				mPerStatus2;
	private boolean				mRunning;

	private SDLSurface			mScreen;

	private SDLGraphics			mSDLGraphics;
	private SDLImageLoader		mSDLImageLoader;
	private SDLInput			mSDLInput;
	private Image				mSplashImage;
	private FFContainer			mStatus;
	private FFContainer			mTime;
	private Label				mTimeLabel1;
	private Label				mTimeLabel2;
	private Icon				mTomasIcon;
	private Image				mTomasImage;
	private TextBox				mTomasInfo1;
	private TextBox				mTomasInfo2;
	private StringListModel		mTomasMagic;
	private StringListModel		mTomasSkills;
	private TextBox				mTomasStatus1;
	private TextBox				mTomasStatus2;

	private Container			mTop;

	public FFDemo() throws GUIException, SDLException {
		super();
		mRunning = true;
		SDLMain.init(SDLMain.SDL_INIT_VIDEO | SDLMain.SDL_INIT_AUDIO);

		mScreen = SDLVideo.setVideoMode(320, 240, 32, SDLVideo.SDL_HWSURFACE
			| SDLVideo.SDL_DOUBLEBUF
			| SDLVideo.SDL_HWACCEL
			| (mFullScreen ? SDLVideo.SDL_FULLSCREEN : 0));
		SDLEvent.enableUNICODE(1);
		SDLEvent.enableKeyRepeat(SDLEvent.SDL_DEFAULT_REPEAT_DELAY, SDLEvent.SDL_DEFAULT_REPEAT_INTERVAL);

		// TODO check SDL_ShowCursor
		SWIG_SDLEvent.SDL_ShowCursor(0);

		SDLVideo.wmSetCaption("sdlJavaGUI FF demo", null);

		SDLMixer.openAudio(22050, SDLMixer.AUDIO_S16SYS, 2, 1024);

		mChooseSound = SDLMixer.loadWAV(dataDir + "sound1.wav");
		mEscapeSound = SDLMixer.loadWAV(dataDir + "sound2.wav");

		mSDLImageLoader = new SDLImageLoader();
		Image.setImageLoader(mSDLImageLoader);
		mSDLGraphics = new SDLGraphics();
		mSDLGraphics.setTarget(mScreen);
		mSDLInput = new SDLInput();

		mSplashImage = new Image(dataDir + "splash.png");

		mTop = new Container();
		mTop.setBaseColor(new Color(0x000000));
		mTop.setDimension(new Rectangle(0, 0, 320, 240));
		mGui = new GUI();
		mGui.setTabbingEnabled(false);
		mGui.setGraphics(mSDLGraphics);
		mGui.setInput(mSDLInput);
		mGui.setTop(mTop);
		mFontWhite = new ImageFont(dataDir + "rpgfont.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		mFontCyan = new ImageFont(dataDir + "rpgfont2.png",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?-+/():;%&`'*#=[]\"");
		Widget.setGlobalFont(mFontWhite);

		initMain();

		mMenu = new FFContainer();
		mMenu.setDimension(new Rectangle(230, 0, 90, 130));
		mMenu.setOpaque(false);
		mTop.add(mMenu);

		mGoldFootsteps = new FFContainer();
		mGoldFootsteps.setDimension(new Rectangle(210, 170, 110, 70));
		mGoldFootsteps.setOpaque(false);
		mGoldFootsteps.setBorderSize(0);
		mTop.add(mGoldFootsteps);

		mTime = new FFContainer();
		mTime.setDimension(new Rectangle(230, 130, 90, 40));
		mTime.setOpaque(false);
		mTop.add(mTime);

		mGoldFootstepsInfo1 = new TextBox("Steps\n\nGP");
		mGoldFootstepsInfo1.setFont(mFontCyan);
		mGoldFootstepsInfo1.setOpaque(false);
		mGoldFootstepsInfo1.setEditable(false);
		mGoldFootstepsInfo1.setFocusable(false);
		mGoldFootstepsInfo1.setBorderSize(0);

		mGoldFootstepsInfo2 = new TextBox("\n    9119092\n\n    1009213");
		mGoldFootstepsInfo2.setOpaque(false);
		mGoldFootstepsInfo2.setEditable(false);
		mGoldFootstepsInfo2.setFocusable(false);
		mGoldFootstepsInfo2.setBorderSize(0);

		mTimeLabel1 = new Label("Time");
		mTimeLabel1.setFont(mFontCyan);
		mTimeLabel2 = new Label();
		mTime.add(mTimeLabel1, 5, 5);
		mTime.add(mTimeLabel2, 22, 20);

		mGoldFootsteps.add(mGoldFootstepsInfo2, 5, 0);
		mGoldFootsteps.add(mGoldFootstepsInfo1, 5, 5);

		mMenuListModel = new StringListModel();
		mMenuListModel.add("Items");
		mMenuListModel.add("Status");
		mMenuListModel.add("Skills");
		mMenuListModel.add("Magic");
		mMenuListModel.add("About");
		mMenuListModel.add("");
		mMenuListModel.add("Quit");

		mMenuList = new FFListBox();
		mMenuList.setEventId("menu");
		mMenuList.addActionListener(this);
		mMenuList.setListModel(mMenuListModel);
		mMenu.add(mMenuList, 5, 5);
		mMenuList.setSelected(0);
		mMenuList.requestFocus();

		initStatus();
		initAbout();
		initItems();
		initMagicSkills();
	}

	public void action(String eventId) throws GUIException {
		if (eventId.equals("menu")) {
			switch (mMenuList.getSelected()) {
				case 0 :
					mItems.setVisible(true);
					mItemsList.setSelected(0);
					mItemsList.requestFocus();
					mItemsInfo.setVisible(true);
					mItemsInfoInfo.setText(mItemsInfoListModel.getElementAt(mItemsList.getSelected()));
					break;

				case 1 :
				case 2 :
				case 3 :
					mCharacterChooser.setSelected(0);
					mCharacterChooser.requestFocus();
					break;
				case 4 :
					mAbout.setVisible(true);
					mAboutScrollArea.setVerticalScrollAmount(0);
					mAboutScrollArea.requestFocus();
					break;

				case 6 :
					mRunning = false;
					break;

				default :
					break;
			}
		}

		if (eventId.equals("escape")) {
			mAbout.setVisible(false);
			mItems.setVisible(false);
			mItemsInfo.setVisible(false);
			mMenu.setVisible(true);
			mTime.setVisible(true);
			mGoldFootsteps.setVisible(true);
			mMenuList.requestFocus();
			mMain.slideContentTo(0);
			mStatus.setVisible(false);
			mPerStatus1.setVisible(false);
			mPerStatus2.setVisible(false);
			mOlofStatus1.setVisible(false);
			mOlofStatus2.setVisible(false);
			mTomasStatus1.setVisible(false);
			mTomasStatus2.setVisible(false);
			mMagicSkills.setVisible(false);
			mNavigationLabel.setVisible(false);
		}

		if (eventId.equals("character")) {
			mMain.slideContentTo(-76 * mCharacterChooser.getSelected());
			mMenu.setVisible(false);
			mTime.setVisible(false);
			mGoldFootsteps.setVisible(false);

			mGui.focusNone();

			mNavigationLabel.setVisible(true);
			mNavigationLabel.setY(mCharacterChooser.getSelected() * 76 + 30);

			switch (mMenuList.getSelected()) {
				case 1 :
					mNavigationLabel.setCaption("STATUS");

					if (mCharacterChooser.getSelected() == 0) {
						mPerStatus1.setVisible(true);
						mPerStatus2.setVisible(true);
					} else if (mCharacterChooser.getSelected() == 1) {
						mOlofStatus1.setVisible(true);
						mOlofStatus2.setVisible(true);
					} else if (mCharacterChooser.getSelected() == 2) {
						mTomasStatus1.setVisible(true);
						mTomasStatus2.setVisible(true);
					}
					mStatus.setVisible(true);
					break;

				case 2 :
					mNavigationLabel.setCaption("SKILLS");

					if (mCharacterChooser.getSelected() == 0) {
						mMagicSkillsList.setListModel(mPerSkills);
					} else if (mCharacterChooser.getSelected() == 1) {
						mMagicSkillsList.setListModel(mOlofSkills);
					} else if (mCharacterChooser.getSelected() == 2) {
						mMagicSkillsList.setListModel(mTomasSkills);
					}
					mMagicSkillsList.setSelected(0);
					mMagicSkills.setVisible(true);
					mMagicSkillsList.requestFocus();
					break;

				case 3 :
					mNavigationLabel.setCaption("MAGIC");

					if (mCharacterChooser.getSelected() == 0) {
						mMagicSkillsList.setListModel(mPerMagic);
					} else if (mCharacterChooser.getSelected() == 1) {
						mMagicSkillsList.setListModel(mOlofMagic);
					} else if (mCharacterChooser.getSelected() == 2) {
						mMagicSkillsList.setListModel(mTomasMagic);
					}
					mMagicSkillsList.setSelected(0);
					mMagicSkills.setVisible(true);
					mMagicSkillsList.requestFocus();
					break;
			}
		}
	}

	private void cleanAbout() throws GUIException {
		mAboutInfo.delete();
		mAboutScrollArea.delete();
		mAbout.delete();
	}

	private void cleanItems() throws GUIException {
		mItems.delete();
		mItemsInfo.delete();
		mItemsInfoInfo.delete();
		mItemsList.delete();
		mItemsScrollArea.delete();
	}

	private void cleanMagicSkills() throws GUIException {
		mMagicSkills.delete();
		mMagicSkillsList.delete();
		mMagicSkillsScroll.delete();
		mPerSkills.delete();
		mPerMagic.delete();
		mOlofSkills.delete();
		mOlofMagic.delete();
		mTomasSkills.delete();
		mTomasMagic.delete();
	}

	private void cleanMain() throws GUIException {
		mNavigationLabel.delete();
		mCharacterChooser.delete();

		mPerInfo1.delete();
		mOlofInfo1.delete();
		mTomasInfo1.delete();

		mPerInfo2.delete();
		mOlofInfo2.delete();
		mTomasInfo2.delete();

		mPerIcon.delete();
		mOlofIcon.delete();
		mTomasIcon.delete();

		mPerImage.delete();
		mOlofImage.delete();
		mTomasImage.delete();
	}

	private void cleanStatus() throws GUIException {
		mStatus.delete();
		mPerStatus1.delete();
		mPerStatus2.delete();
		mOlofStatus1.delete();
		mOlofStatus2.delete();
		mTomasStatus1.delete();
		mTomasStatus2.delete();
	}

	public void delete() throws GUIException, SDLException {
		cleanStatus();
		cleanAbout();
		cleanItems();
		cleanMagicSkills();
		cleanMain();

		mSplashImage.delete();

		mTimeLabel1.delete();
		mTimeLabel2.delete();
		mTime.delete();

		mGoldFootstepsInfo1.delete();
		mGoldFootstepsInfo2.delete();
		mGoldFootsteps.delete();

		mMenuList.delete();
		mMenuListModel.delete();
		mMenu.delete();

		mMain.delete();

		mFontWhite.delete();
		mFontCyan.delete();
		mTop.delete();
		mGui.delete();

		mSDLImageLoader.delete();

		SDLMixer.freeChunk(mChooseSound);
		SDLMixer.freeChunk(mEscapeSound);
		SDLMixer.close();

		SDLMain.quit();
	}

	private void initAbout() throws GUIException {
		mAbout = new FFContainer();
		mAbout.setDimension(new Rectangle(0, 0, 320, 240));
		mAbout.setVisible(false);
		mTop.add(mAbout);

		mAboutInfo = new TextBox();
		mAboutInfo.setOpaque(false);
		mAboutInfo.setEditable(false);
		mAboutInfo.setFocusable(false);
		mAboutInfo.setText("Welcome to Guichan FF Demo!\n\n"
			+ "What is this, you wonder?\n"
			+ "Well, this is a little proof of\n"
			+ "concept (proof of l33tness) demo\n"
			+ "for the Guichan GUI library.\n"
			+ "It demonstrates the\n"
			+ "flexibility of the library,\n"
			+ "how to overload widgets to get a\n"
			+ "custom look and feel.\n\n"
			+ "Guichan is a GUI library\n"
			+ "especially made with games in\n"
			+ "mind. It has a modular, object\n"
			+ "oriented API. The back-end is\n"
			+ "replaceable, so it can work\n"
			+ "on any platform. It is bundled\n"
			+ "with graphics back-ends for\n"
			+ "SDL, OpenGL and Allegro, and\n"
			+ "input-backends for SDL and\n"
			+ "Allegro. And don't be fooled\n"
			+ "by this demo, it does support\n"
			+ "mouse input!\n\n"
			+ "Read more about Guichan on:\n"
			+ "http://guichan.darkbits.org/\n\n\n"
			+ "Guichan developed by:\n"
			+ " - Per Larsson (finalman)\n"
			+ " - Olof Naessen (yakslem)\n\n"
			+ "Demo developed by:\n"
			+ " - Per Larsson (finalman)\n"
			+ "       code\n\n"
			+ " - Olof Naessen (yakslem)\n"
			+ "       code, character art\n\n"
			+ " - Tomas Almgren (peak)\n"
			+ "       font\n\n"
			+ " - Henrik Vahlgren (haiko)\n"
			+ "       Darkbits logo\n");
		mAboutInfo.setBorderSize(0);

		mAboutScrollArea = new FFScrollArea();
		mAboutScrollArea.setContent(mAboutInfo);
		mAboutScrollArea.setFocusable(true);
		mAboutScrollArea.setDimension(new Rectangle(5, 5, 310, 230));
		mAbout.add(mAboutScrollArea);
	}

	private void initItems() throws GUIException {
		mItems = new FFContainer();

		mItemsListModel = new StringListModel();
		mItemsInfoListModel = new StringListModel();
		mItemsListModel.add("23 x Potion");
		mItemsInfoListModel.add("Restores 100 HP");
		mItemsListModel.add("12 x Ether");
		mItemsInfoListModel.add("Restores 50 MP");
		mItemsListModel.add(" 8 x Elixir");
		mItemsInfoListModel.add("Restores all HP/MP");
		mItemsListModel.add("16 x Fenix Up");
		mItemsInfoListModel.add("Kills a party member");
		mItemsListModel.add(" 1 x Brass Key");
		mItemsInfoListModel.add("No idea...");
		mItemsListModel.add(" 1 x Atma Weapon");
		mItemsInfoListModel.add("Grows with it's user");
		mItemsListModel.add(" 1 x Converse Allstars");
		mItemsInfoListModel.add("Yakslems red shoes");
		mItemsListModel.add(" 1 x Oil Canister");
		mItemsInfoListModel.add("Get greasy!");
		mItemsListModel.add(" 1 x Geeky t-shirt");
		mItemsInfoListModel.add("Belongs to finalman");
		mItemsListModel.add(" 1 x Synthesizer");
		mItemsInfoListModel.add("Yakslems mega cool Ensoniq EPS 16+");
		mItemsListModel.add(" 1 x Graphic Pen");
		mItemsInfoListModel.add("Someone left it here. Maybe\nNodajo?");
		mItemsListModel.add(" 1 x Floppy Disk");
		mItemsInfoListModel.add("Stores your important data");
		mItemsListModel.add(" 1 x Gui-chan Plush Doll");
		mItemsInfoListModel.add("Soooo cute and soooo plushy!!!");
		mItemsListModel.add(" 1 x Fenix Blade");
		mItemsInfoListModel.add("We are waiting for Demo3");
		mItemsListModel.add(" 2 x Joy Division LP");
		mItemsInfoListModel.add("Unknown Pleasures and Closer");

		mItemsInfo = new FFContainer();
		mItemsInfo.setDimension(new Rectangle(0, 0, 320, 50));
		mItemsInfo.setVisible(false);

		mItemsInfoInfo = new TextBox("Hej");
		mItemsInfoInfo.setOpaque(false);
		mItemsInfoInfo.setEditable(false);
		mItemsInfoInfo.setFocusable(false);
		mItemsInfoInfo.setDimension(new Rectangle(5, 5, 310, 40));
		mItemsInfoInfo.setBorderSize(0);
		mItemsInfo.add(mItemsInfoInfo);

		mItemsList = new FFListBox();
		mItemsList.setEventId("items");
		mItemsList.addKeyListener(this);
		mItemsList.setWidth(300);
		mItemsList.setListModel(mItemsListModel);
		mItemsScrollArea = new FFScrollArea();
		mItemsScrollArea.setContent(mItemsList);
		mItemsScrollArea.setDimension(new Rectangle(5, 5, 310, 180));
		mItems = new FFContainer();
		mItems.setDimension(new Rectangle(0, 50, 320, 190));
		mItems.setVisible(false);
		mItems.add(mItemsScrollArea);
		mTop.add(mItems);
		mTop.add(mItemsInfo);
	}

	private void initMagicSkills() throws GUIException {
		mMagicSkills = new FFContainer();
		mMagicSkills.setDimension(new Rectangle(0, 80, 320, 160));
		mMagicSkills.setVisible(false);

		mMagicSkillsScroll = new FFScrollArea();
		mMagicSkillsScroll.setDimension(new Rectangle(5, 5, 310, 150));

		mMagicSkillsList = new FFListBox();
		mMagicSkillsList.setWidth(300);
		mMagicSkillsScroll.setContent(mMagicSkillsList);
		mMagicSkills.add(mMagicSkillsScroll);
		mTop.add(mMagicSkills);

		mPerSkills = new StringListModel();
		mPerMagic = new StringListModel();
		mOlofSkills = new StringListModel();
		mOlofMagic = new StringListModel();
		mTomasSkills = new StringListModel();
		mTomasMagic = new StringListModel();

		mPerSkills.add("Use");
		mPerSkills.add("Steal");
		mPerSkills.add("Disassemble");
		mPerSkills.add("Tech-Talk");
		mPerSkills.add("Double Compile");

		mPerMagic.add("Fire");
		mPerMagic.add("Fire 2");
		mPerMagic.add("Bio");
		mPerMagic.add("Magic Missile");

		mOlofSkills.add("Annoy");
		mOlofSkills.add("Juggle");
		mOlofSkills.add("Somersault");
		mOlofSkills.add("Evil Laughter");
		mOlofSkills.add("Meta-circular Evaluation");
		mOlofSkills.add("Lisp");
		mOlofSkills.add("Cursing PHP");
		mOlofSkills.add("Paint");
		mOlofSkills.add("Compose obscure music");

		mOlofMagic.add("Ultima");
		mOlofMagic.add("Sonic Blast");

		mTomasSkills.add("Precision Throw");
		mTomasSkills.add("Jump");
		mTomasSkills.add("Dance");
		mTomasSkills.add("Much talk and little factory");
		mTomasSkills.add("Cheat");
		mTomasSkills.add("Wear hotpants");
		mTomasSkills.add("Programming Pong games");
		mTomasSkills.add("Eat meat pie");

		mTomasMagic.add("Slow");
		mTomasMagic.add("Sleep");
		mTomasMagic.add("Doom");
	}

	private void initMain() throws GUIException {
		mMain = new FFContainer();
		mMain.setDimension(new Rectangle(0, 0, 320, 240));
		mTop.add(mMain);

		mPerImage = new Image(dataDir + "finalman.png");
		mOlofImage = new Image(dataDir + "yakslem.png");
		mTomasImage = new Image(dataDir + "peak.png");

		mPerIcon = new Icon(mPerImage);
		mOlofIcon = new Icon(mOlofImage);
		mTomasIcon = new Icon(mTomasImage);

		mPerInfo1 = new TextBox("\n  LV\n  HP\n  MP");
		mPerInfo1.setFont(mFontCyan);
		mPerInfo1.setOpaque(false);
		mPerInfo1.setEditable(false);
		mPerInfo1.setFocusable(false);
		mPerInfo1.setBorderSize(0);

		mPerInfo2 = new TextBox("FINALMAN\n     13\n       12/ 336\n       33/  40");
		mPerInfo2.setOpaque(false);
		mPerInfo2.setEditable(false);
		mPerInfo2.setFocusable(false);
		mPerInfo2.setBorderSize(0);

		mOlofInfo1 = new TextBox("\n  LV\n  HP\n  MP");
		mOlofInfo1.setFont(mFontCyan);
		mOlofInfo1.setOpaque(false);
		mOlofInfo1.setEditable(false);
		mOlofInfo1.setFocusable(false);
		mOlofInfo1.setBorderSize(0);

		mOlofInfo2 = new TextBox("YAKSLEM\n     41\n     1304/2932\n      298/ 300");
		mOlofInfo2.setOpaque(false);
		mOlofInfo2.setEditable(false);
		mOlofInfo2.setFocusable(false);
		mOlofInfo2.setBorderSize(0);

		mTomasInfo1 = new TextBox("\n  LV\n  HP\n  MP");
		mTomasInfo1.setFont(mFontCyan);
		mTomasInfo1.setOpaque(false);
		mTomasInfo1.setEditable(false);
		mTomasInfo1.setFocusable(false);
		mTomasInfo1.setBorderSize(0);

		mTomasInfo2 = new TextBox("PEAK\n      6\n      101/ 101\n        0/   0");
		mTomasInfo2.setOpaque(false);
		mTomasInfo2.setEditable(false);
		mTomasInfo2.setFocusable(false);
		mTomasInfo2.setBorderSize(0);

		int offset = 6;
		mMain.add(mPerIcon, 10, offset);
		mMain.add(mPerInfo2, 60, offset);
		mMain.add(mPerInfo1, 60, offset);
		offset += 76;
		mMain.add(mOlofIcon, 10, offset);
		mMain.add(mOlofInfo2, 60, offset);
		mMain.add(mOlofInfo1, 60, offset);
		offset += 76;
		mMain.add(mTomasIcon, 10, offset);
		mMain.add(mTomasInfo2, 60, offset);
		mMain.add(mTomasInfo1, 60, offset);

		mCharacterChooser = new FFCharacterChooser();
		mCharacterChooser.setEventId("character");
		mCharacterChooser.addActionListener(this);

		mMain.add(mCharacterChooser, 5, 25);

		mNavigationLabel = new Label("STATUS ");
		mNavigationLabel.setVisible(false);
		mMain.add(mNavigationLabel, 230, 20);
	}

	private void initStatus() throws GUIException {
		mStatus = new FFContainer();
		mStatus.setDimension(new Rectangle(0, 80, 320, 160));
		mStatus.setVisible(false);
		mTop.add(mStatus);

		mPerStatus1 = new TextBox("  STR           EXP\n" + "  INT           NEXT\n" + "  DEF\n" + "  MAGDEF\n");
		mPerStatus1.setFont(mFontCyan);
		mPerStatus1.setOpaque(false);
		mPerStatus1.setEditable(false);
		mPerStatus1.setFocusable(false);
		mPerStatus1.setVisible(false);
		mPerStatus1.setBorderSize(0);

		mPerStatus2 = new TextBox("         32          12382\n"
			+ "         56          13872\n"
			+ "         12\n"
			+ "         11\n\n"
			+ " FINALMAN is immune against\n"
			+ " poisinous attacks, thanks to his\n"
			+ " face mask.");
		mPerStatus2.setOpaque(false);
		mPerStatus2.setEditable(false);
		mPerStatus2.setFocusable(false);
		mPerStatus2.setVisible(false);
		mPerStatus2.setBorderSize(0);

		mOlofStatus1 = new TextBox("  STR           EXP\n" + "  INT           NEXT\n" + "  DEF\n" + "  MAGDEF\n");
		mOlofStatus1.setFont(mFontCyan);
		mOlofStatus1.setOpaque(false);
		mOlofStatus1.setEditable(false);
		mOlofStatus1.setFocusable(false);
		mOlofStatus1.setVisible(false);
		mOlofStatus1.setBorderSize(0);

		mOlofStatus2 = new TextBox("          2          412382\n"
			+ "         72          513872\n"
			+ "          4\n"
			+ "         34\n\n"
			+ " YAKSLEM has one passion in life,\n"
			+ " to annoy other people...\n"
			+ " especially FINALMAN.");
		mOlofStatus2.setOpaque(false);
		mOlofStatus2.setEditable(false);
		mOlofStatus2.setFocusable(false);
		mOlofStatus2.setVisible(false);
		mOlofStatus2.setBorderSize(0);

		mTomasStatus1 = new TextBox("  STR           EXP\n" + "  INT           NEXT\n" + "  DEF\n" + "  MAGDEF\n");
		mTomasStatus1.setFont(mFontCyan);
		mTomasStatus1.setOpaque(false);
		mTomasStatus1.setEditable(false);
		mTomasStatus1.setFocusable(false);
		mTomasStatus1.setVisible(false);
		mTomasStatus1.setBorderSize(0);

		mTomasStatus2 = new TextBox("          1          412382\n"
			+ "          3          513872\n"
			+ "          9\n"
			+ "         24\n\n"
			+ " PEAK is very weak but so cute!\n"
			+ " He has a tendency of answering\n"
			+ " any question with \"KUPO!\"");
		mTomasStatus2.setOpaque(false);
		mTomasStatus2.setEditable(false);
		mTomasStatus2.setFocusable(false);
		mTomasStatus2.setVisible(false);
		mTomasStatus2.setBorderSize(0);

		mStatus.add(mPerStatus2, 5, 10);
		mStatus.add(mPerStatus1, 5, 10);
		mStatus.add(mOlofStatus2, 5, 10);
		mStatus.add(mOlofStatus1, 5, 10);
		mStatus.add(mTomasStatus2, 5, 10);
		mStatus.add(mTomasStatus1, 5, 10);
	}

	private void input() throws SDLException, GUIException {
		while (null != (mEvent = SDLEvent.pollEvent())) {
			if (mEvent.getType() == SDLEvent.SDL_KEYDOWN) {
				int nKeySym = ((SDLKeyboardEvent) mEvent).getSym();
				if (nKeySym == SDLKey.SDLK_ESCAPE) {
					SDLMixer.playChannel(-1, mEscapeSound, 0);
					action("escape");
				} else if (nKeySym == SDLKey.SDLK_RETURN || nKeySym == SDLKey.SDLK_UP || nKeySym == SDLKey.SDLK_DOWN) {
					SDLMixer.playChannel(-1, mChooseSound, 0);
				} else if (nKeySym == SDLKey.SDLK_f) {
					// Works with X11 only
					// mScreen.wmToggleFullScreen();
					mFullScreen = !mFullScreen;
					mScreen = SDLVideo.setVideoMode(320, 240, 32, SDLVideo.SDL_HWSURFACE
						| SDLVideo.SDL_DOUBLEBUF
						| SDLVideo.SDL_HWACCEL
						| (mFullScreen ? SDLVideo.SDL_FULLSCREEN : 0));

				}
				mSDLInput.pushInput(mEvent);
			} else if (mEvent.getType() == SDLEvent.SDL_KEYUP) {
				mSDLInput.pushInput(mEvent);
			} else if (mEvent.getType() == SDLEvent.SDL_QUIT) {
				mRunning = false;
			}
		}
	}

	public void keyPress(Key key) {
		mItemsInfoInfo.setText(mItemsInfoListModel.getElementAt(mItemsList.getSelected()));
	}

	public void keyRelease(Key key) {}

	public void run() throws SDLException, GUIException {
		while (mRunning) {
			input();

			int sec = (int) (SDLTimer.getTicks() / 1000);
			int min = sec / 60;
			sec = sec % 60;
			String str = "";

			if (min < 10) {
				str += " " + min + ":";
			} else {
				str += min + ":";
			}

			if (sec < 10) {
				str += "0" + sec;
			} else {
				str += sec;
			}

			mTimeLabel2.setCaption(str);
			mTimeLabel2.adjustSize();

			if (SDLTimer.getTicks() < 3000) {
				SDLRect src = new SDLRect();
				SDLRect dst = new SDLRect();
				src.x = src.y = 0;
				src.width = dst.width = mSplashImage.getWidth();
				src.height = dst.height = mSplashImage.getHeight();
				dst.x = 10;
				dst.y = 50;
				((SDLSurface) mSplashImage.getData()).blitSurface(src, mScreen, dst);
			} else {
				mGui.logic();
				mGui.draw();
			}

			mScreen.flip();
			try {
				SDLTimer.delay(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
