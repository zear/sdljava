package org.gljava.opengl.model;
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
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.*;
import org.jdom.input.*;
/**
 * Utility class to parse gljava (.xml) format files and produce the Model object instances described.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: XMLModelLoader.java,v 1.2 2005/09/04 00:10:55 ivan_ganza Exp $
 */
public class XMLModelLoader {

    public static List loadModel(String path) throws IOException, FileNotFoundException, JDOMException {
	List l = new ArrayList();
	
	FileReader reader = new FileReader(new File(path));
	
	SAXBuilder      builder     = new SAXBuilder();
	Document        document    = builder.build( reader );
	Element         root        = document.getRootElement();

	return parse(l, root);
    }

    public static List parse(List l, Element e) throws JDOMException {
	Iterator i = e.getChildren().iterator();
	while (i.hasNext()) {
	    e = (Element) i.next();
	    if (e.getName().equals("mesh")) {
		l.add( parseMesh(e));
	    }
	}
	
	return l;
    }

    public static Mesh parseMesh(Element e) throws JDOMException {
	Mesh m = new Mesh();

	Iterator i = e.getChildren().iterator();

	while (i.hasNext()) {
	    e = (Element)i.next();

	    if (e.getName().equals("vertices")) {
		List vertices = parseVertices(new ArrayList(), e);
		m.vertices = new Vector[vertices.size()];
		for (int x = 0; x < vertices.size(); x++) {
		    m.vertices[x] = (Vector) vertices.get(x);
		}
	    }

	    else if (e.getName().equals("faces")) {
		List faces = parseFaces(new ArrayList(), e);
		m.faces = new Face[faces.size()];
		for (int x = 0; x < faces.size(); x++) {
		    m.faces[x] = (Face) faces.get(x);
		}
	    }
	}

	System.out.println(m);
	return m;
    }

    public static List parseVertices(List l, Element e) throws JDOMException{
	Iterator i = e.getChildren().iterator();
	while (i.hasNext()) {
	    Element v = (Element) i.next();

	    String x = (String) v.getAttribute("x").getValue();
	    String y = (String) v.getAttribute("y").getValue();
	    String z = (String) v.getAttribute("z").getValue();

	    l.add(new Vector(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(z)));
	}

	return l;
    }

    public static List parseFaces(List l, Element e) throws JDOMException {
	Iterator i = e.getChildren().iterator();
	while (i.hasNext()) {
	    Element f = (Element) i.next();

	    String v1 = (String) f.getAttribute("v1").getValue();
	    String v2 = (String) f.getAttribute("v2").getValue();
	    String v3 = (String) f.getAttribute("v3").getValue();

	    l.add(new Face(Integer.parseInt(v1), Integer.parseInt(v2), Integer.parseInt(v3)));
	}

	return l;
    }

    public static void main(String[] args) {
	try {
	    loadModel(args[0]);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}