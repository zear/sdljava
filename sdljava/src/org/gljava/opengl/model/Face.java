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
/**
 * One face of a Mesh.  vertex1, vertex2 and vertex3 are indexes into an array of vertices
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Face.java,v 1.1 2005/02/10 04:18:42 ivan_ganza Exp $
 */
public class Face {

    public int vertex1;
    public int vertex2;
    public int vertex3;

    public Face(int v1, int v2, int v3) {
	this.vertex1 = v1;
	this.vertex2 = v2;
	this.vertex3 = v3;
    }

    /**
     * Gets the value of vertex1
     *
     * @return the value of vertex1
     */
    public int getVertex1()  {
	return this.vertex1;
    }

    /**
     * Sets the value of vertex1
     *
     * @param argVertex1 Value to assign to this.vertex1
     */
    public void setVertex1(int argVertex1) {
	this.vertex1 = argVertex1;
    }

    /**
     * Gets the value of vertex2
     *
     * @return the value of vertex2
     */
    public int getVertex2()  {
	return this.vertex2;
    }

    /**
     * Sets the value of vertex2
     *
     * @param argVertex2 Value to assign to this.vertex2
     */
    public void setVertex2(int argVertex2) {
	this.vertex2 = argVertex2;
    }

    /**
     * Gets the value of vertex3
     *
     * @return the value of vertex3
     */
    public int getVertex3()  {
	return this.vertex3;
    }

    /**
     * Sets the value of vertex3
     *
     * @param argVertex3 Value to assign to this.vertex3
     */
    public void setVertex3(int argVertex3) {
	this.vertex3 = argVertex3;
    }

}