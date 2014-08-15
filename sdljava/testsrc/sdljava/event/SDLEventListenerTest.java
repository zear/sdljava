package sdljava.event;

public class SDLEventListenerTest implements SDLEventListener {

	private SDLEventManagerTest _manager;
	
	public SDLEventListenerTest(SDLEventManagerTest manager) {
		_manager = manager;
	}
	
	public void handleEvent(SDLEvent event) {
		System.out.println("SDLEventListenerTest::handleEvent receive["+event+"]");
		SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent)event;
		if (keyboardEvent.getSym() == SDLKey.SDLK_ESCAPE) _manager.stopIt();		
	}
}