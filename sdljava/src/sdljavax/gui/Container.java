package sdljavax.gui;
/**
 *  sdljava - a java binding to the SDL API
 *
 *  Copyright (C) 2004  Ivan Z. Ganza
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA
 *
 *  Ivan Z. Ganza (ivan_ganza@yahoo.com)
 */
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import sdljava.video.SDLRect;
import sdljava.event.SDLEvent;
import sdljava.event.SDLMouseMotionEvent;
import org.gljava.opengl.GL;
/**
 * A Container is a Widget that can contain other Widgets.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Container.java,v 1.4 2005/09/03 23:07:00 ivan_ganza Exp $
 */
public abstract class Container extends Widget {

    protected List    children = new ArrayList();
    protected Widget  mouseFocus;
    protected SDLRect screenDimensions;

    public Container(GL gl, String name, SDLRect rect, SDLRect screenDimensions) throws IOException {
	super(gl, name, rect, null);
	this.screenDimensions = screenDimensions;
    }

    public void add(Widget w) {
	if (w == null) throw new NullPointerException("tried to add null Widget");
	w.container = this;
	children.add(w);
    }

    public void add(int index, Widget w) {
	if (w == null) throw new NullPointerException("tried to add null Widget");
	w.container = this;
	children.add(index, w);
    }

    public void remove(Widget w) {
	int index = children.indexOf(w);
	if(index != -1) children.remove(index);
    }

    public void draw(GL gl) {
	super.draw(gl);
	
	Iterator i = children.iterator();
	while (i.hasNext()) {
	    Widget w = (Widget)i.next();
	    w.draw(gl);
	}
    }

    public void handleEvent(SDLEvent event) {
	if (event == null) return;
	
	if (event instanceof SDLMouseMotionEvent) {
	    SDLMouseMotionEvent mouseMotion = (SDLMouseMotionEvent)event;
	    int x = mouseMotion.getX();
	    int y = mouseMotion.getY();

	    // adjust for OpenGL co-ordinate system
	    y = screenDimensions.height - y;
	    mouseMotion.setY(y);

	    //System.out.println("handleEvent: x=" + x + ", y=" + y);

	    // check children first
	    for(int i = children.size(); i > 0; i--) {
		Widget w = (Widget)children.get(i - 1);
		//System.out.println("Container Processing Widget:" + w.toString());

		if(!w.isVisible()) continue;
		
		if (w.contains(x, y)) {
		    if (w != mouseFocus) {
			if (mouseFocus != null) mouseFocus.mouseExit();

			mouseFocus = w;

			w.mouseEntered();

		    }
		    //System.out.println("Sending event to: " + w.toString());

		    if (w instanceof Container) {
			//w.processEvent(w.normalize(mouseMotion));
			w.handleEvent(mouseMotion);
		    }
		    else {
			w.handleEvent(mouseMotion);
		    }
    
		    return;
		}
	    }
	    
	    if(!isVisible()) return;

	    // do we care if the container(Window) gets the focus but not a child which can actually do something??
	    
	    // if mouse didn't fall within any children does it fall anywhere within container bounds?
	    if(this.contains(x, y)) {
	        if (mouseFocus != this) {
	            if (mouseFocus != null) mouseFocus.mouseExit();
	            
	            mouseFocus = this;
	            mouseEntered();
	        }
	        return;
	    }

	    // mouse moved out of something but not into anything
	    if (mouseFocus != null) {
		mouseFocus.mouseExit();
		mouseFocus = null;
	    }
	}

	else {
	    if(mouseFocus != null && mouseFocus != this) mouseFocus.handleEvent(event);
	}
    }

//    public boolean processEvent(SDLCustomEvent event) throws SDLEventException {
//	try {
//	    if (event instanceof SDLMouseMotionEvent) {
//		SDLMouseMotionEvent mouseMotion = (SDLMouseMotionEvent) event;
//
//		int x = mouseMotion.m_X;
//		int y = mouseMotion.m_Y;
//
//		for(int i = children.size(); i > 0; i--) {
//		    Widget w = children.get(i - 1);
//		    //for(Widget w : children) {
//		    //System.out.println("Container Processing Widget:" + w.toString());
//		
//		    if(!w.isVisible()) continue;
//
//
//		    if (w.contains(x, y)) {
//			if (w != mouseFocus) {
//			    if (mouseFocus != null) mouseFocus.mouseExit();
//
//			    mouseFocus = w;
//
//			    w.mouseEntered();
//
//			}
//			//System.out.println("Sending event to: " + w.toString());
//
//			if (w instanceof Container) {
//			    //w.processEvent(w.normalize(mouseMotion));
//			    w.processEvent(mouseMotion);
//			}
//			else {
//			    w.processEvent(mouseMotion);
//			}
//			
//			return true;
//		    }
//		}
//		
////		if (this.contains(x, y)) {
////		    if (this != mouseFocus) {
////			if (mouseFocus != null) mouseFocus.mouseExit();
////
////			mouseFocus = this;
////
////			this.mouseEntered();
////
////		    }
////
////		    return true;
////		}
////		
//		// mouse moved out of something but not into anything
//		if (mouseFocus != null) mouseFocus.mouseExit();
//		
//		mouseFocus = null;
//	    }
//
//	    if (event instanceof SDLMouseButtonEvent) {
//		SDLMouseButtonEvent mouseButton = (SDLMouseButtonEvent) event;
//	    
//		if (mouseFocus != null) {
//		    // HMMMM - WHAT OUT THIS MAY HAVE BROKEN SOMETHING
//		    //mouseFocus.processEvent(mouseFocus.normalize(mouseButton));
//		    mouseFocus.processEvent(mouseButton);
//		    return true;
//		}
//	    }
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//	
//	return false;
//    }

    public void setMouseFocus(Widget w) { this.mouseFocus = w ;}
}