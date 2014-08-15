/*
 * Created on Mar 9, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany All right reserved.
 */

package sdljavax.guichan.test.openglwidgets;

import org.gljava.opengl.GL;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTimer;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljavax.guichan.GUI;
import sdljavax.guichan.GUIException;
import sdljavax.guichan.font.ImageFont;
import sdljavax.guichan.gfx.Color;
import sdljavax.guichan.gfx.Image;
import sdljavax.guichan.gfx.Rectangle;
import sdljavax.guichan.opengl.OpenGLGraphics;
import sdljavax.guichan.opengl.OpenGLImageLoader;
import sdljavax.guichan.sdl.SDLImageLoader;
import sdljavax.guichan.sdl.SDLInput;
import sdljavax.guichan.widgets.Button;
import sdljavax.guichan.widgets.CheckBox;
import sdljavax.guichan.widgets.Container;
import sdljavax.guichan.widgets.DropDown;
import sdljavax.guichan.widgets.Icon;
import sdljavax.guichan.widgets.Label;
import sdljavax.guichan.widgets.ListBox;
import sdljavax.guichan.widgets.RadioButton;
import sdljavax.guichan.widgets.ScrollArea;
import sdljavax.guichan.widgets.Slider;
import sdljavax.guichan.widgets.TextBox;
import sdljavax.guichan.widgets.TextField;
import sdljavax.guichan.widgets.Widget;
import sdljavax.guichan.widgets.Window;

public class OpenGLWidgets {
	private boolean				running			= true;

	private SDLSurface			screen;
	private SDLEvent			event;

	private SDLInput			input;									// Input driver
	private OpenGLGraphics		graphics;								// Graphics driver
	private OpenGLImageLoader	imageLoader;							// For loading images
	private SDLImageLoader		hostImageLoader;						// For loading images

	private GUI					gui;									// A Gui object - binds it
																		// all
	// together
	private ImageFont			font;									// A font

	private Container			top;									// A top container
	private Label				label;									// A label
	private Icon				icon;									// An icon (image)
	private Button				button;								// A button
	private TextField			textField;								// One-line text field
	private TextBox				textBox;								// Multi-line text box
	private ScrollArea			textBoxScrollArea;						// Scroll area for the text
																		// box
	private ListBox				listBox;								// A list box
	private DropDown			dropDown;								// Drop down
	private CheckBox			checkBox1;								// Two checkboxes
	private CheckBox			checkBox2;
	private RadioButton			radioButton1;							// Three radio buttons
	private RadioButton			radioButton2;
	private RadioButton			radioButton3;
	private Slider				slider;								// A slider
	private Image				image;									// An image for the icon
	private DemoListModel		demoListModel	= new DemoListModel();

	private Window				window;

	private Image				darkbitsImage;

	private Icon				darkbitsIcon;

	private GL	m_gl;

	public OpenGLWidgets() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void initWidgets() throws GUIException {
		/*
		 * Create all the widgets
		 */
		label = new Label("Label");

		image = new Image("testdata/gui-chan.bmp");
		icon = new Icon(image);

		button = new Button("Button");

		textField = new TextField("Text field");

		textBox = new TextBox("Multiline\nText box");
		textBoxScrollArea = new ScrollArea(textBox);
		textBoxScrollArea.setWidth(200);
		textBoxScrollArea.setHeight(100);
		textBoxScrollArea.setBorderSize(1);

		listBox = new ListBox(demoListModel);
		listBox.setBorderSize(1);

		dropDown = new DropDown(demoListModel);

		checkBox1 = new CheckBox("Checkbox 1");
		checkBox2 = new CheckBox("Checkbox 2");

		radioButton1 = new RadioButton("RadioButton 1", "radiogroup", true);
		radioButton2 = new RadioButton("RadioButton 2", "radiogroup");
		radioButton3 = new RadioButton("RadioButton 3", "radiogroup");

		slider = new Slider(0, 10);
		slider.setSize(100, 10);

		window = new Window("I am a window  Drag me");
		window.setBaseColor(new Color(255, 150, 200, 190));

		darkbitsImage = new Image("testdata/darkbitslogo_by_haiko.bmp");
		darkbitsIcon = new Icon(darkbitsImage);
		window.setContent(darkbitsIcon);
		window.resizeToContent();

		/*
		 * Add them to the top container
		 */
		top.add(label, 10, 10);
		top.add(icon, 10, 30);
		top.add(button, 200, 10);
		top.add(textField, 250, 10);
		top.add(textBoxScrollArea, 200, 50);
		top.add(listBox, 200, 200);
		top.add(dropDown, 500, 10);
		top.add(checkBox1, 500, 130);
		top.add(checkBox2, 500, 150);
		top.add(radioButton1, 500, 200);
		top.add(radioButton2, 500, 220);
		top.add(radioButton3, 500, 240);
		top.add(slider, 500, 300);
		top.add(window, 100, 350);
	}

	public void init() throws SDLException, GUIException {
		/*
		 * Here we initialize SDL as we would do with any SDL application.
		 */
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		screen = SDLVideo.setVideoMode(640, 480, 32, SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_HWACCEL | SDLVideo.SDL_OPENGL);
		
		m_gl = screen.getGL();
		
		// Setup OpenGL
		m_gl.glViewport(0, 0, 640, 480);
		m_gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		// We want unicode
		SDLEvent.enableUNICODE(1);
		// We want to enable key repeat
		SDLEvent.enableKeyRepeat(SDLEvent.SDL_DEFAULT_REPEAT_DELAY, SDLEvent.SDL_DEFAULT_REPEAT_INTERVAL);

		/*
		 * Now it's time for Guichan SDL stuff
		 */
		hostImageLoader = new SDLImageLoader();
		// The OpenGL imageloader cant load images by itself, it needs
		// a host imageloader. So we give it an SDL imageloader.
		imageLoader = new OpenGLImageLoader(m_gl, hostImageLoader);
		// The ImageLoader in use is static and must be set to be
		// able to load images
		Image.setImageLoader(imageLoader);
		graphics = new OpenGLGraphics(m_gl);
		// Set the target for the graphics object to be the screen.
		// In other words, we will draw to the screen.
		// Note, any surface will do, it doesn't have to be the screen.
		graphics.setTargetPlane(640, 480);
		input = new SDLInput();

		/*
		 * Last but not least it's time to initialize and create the gui with Guichan stuff.
		 */
		top = new Container();
		// Set the dimension of the top container to match the screen.
		top.setDimension(new Rectangle(0, 0, 640, 480));
		gui = new GUI();
		// Set gui to use the SDLGraphics object.
		gui.setGraphics(graphics);
		// Set gui to use the SDLInput object
		gui.setInput(input);
		// Set the top container
		gui.setTop(top);
		// Load the image font.
		font = new ImageFont("testdata/fixedfont.bmp",
			" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		// The global font is static and must be set.
		Widget.setGlobalFont(font);

		initWidgets();
	}

	private void checkInput() throws SDLException {
		/*
		 * Poll SDL events
		 */
		while (null != (event = SDLEvent.pollEvent())) {
			if (event.getType() == SDLEvent.SDL_KEYDOWN) {
				if (((SDLKeyboardEvent) event).getSym() == SDLKey.SDLK_ESCAPE) {
					running = false;
				}
				if (((SDLKeyboardEvent) event).getSym() == SDLKey.SDLK_f) {
					if (((SDLKeyboardEvent) event).getMod().ctrl()) {
						// Works with X11 only
						// SDL_WM_ToggleFullScreen(screen);
					}
				}
			} else if (event.getType() == SDLEvent.SDL_QUIT) {
				running = false;
			}

			/*
			 * Now that we are done polling and using SDL events we pass the leftovers to the
			 * SDLInput object to later be handled by the Gui.
			 */
			input.pushInput(event);
		}
	}

	public void run() throws SDLException, GUIException {
		while (running) {
			// Poll input
			checkInput();
			// Let the gui perform it's logic (like handle input)
			gui.logic();
			// Draw the gui
			gui.draw();
			// Update the screen
			screen.glSwapBuffers();
			// Make this less CPU consumptive ;)
			try {
				SDLTimer.delay(50);
			} catch (InterruptedException e) {
			}
		}

	}

	public void delete() throws GUIException {
		/*
		 * Destroy Guichan stuff
		 */
		font.delete();
		gui.delete();

		/*
		 * Widgets
		 */
		top.delete();
		label.delete();
		icon.delete();
		button.delete();
		textField.delete();
		textBox.delete();
		textBoxScrollArea.delete();
		listBox.delete();
		dropDown.delete();
		checkBox1.delete();
		checkBox2.delete();
		radioButton1.delete();
		radioButton2.delete();
		radioButton3.delete();
		slider.delete();
		window.delete();
		darkbitsIcon.delete();

		/*
		 * Destroy Guichan SDL stuff
		 */
		// input.delete();
		// graphics.delete();
		// imageLoader.delete();

		/*
		 * Destroy SDL stuff
		 */
		SDLMain.quit();
	}

	public static void main(String[] args) {
		try {
			OpenGLWidgets demo = new OpenGLWidgets();
			demo.init();
			demo.run();
			demo.delete();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
