package sdljava;

public class SDLMainTest {

    static {
	System.loadLibrary("sdljava");
    }

    public static void main(String[] args) {
	try {
	    SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);

	    System.out.println("INIT OKAY!");
	    
	} catch (Exception e) {
	    System.out.println("INIT FAILED!");
	    e.printStackTrace();
	} // try-catch
    }
}