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
 * A Vector for describing a point in 3D space.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Vector.java,v 1.1 2005/02/10 04:18:42 ivan_ganza Exp $
 */
public class Vector {

    public float x;
    public float y;
    public float z;

    public Vector(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    float norm() {
	return (float)Math.sqrt((double)(x*x + y*y + z*z));
    }

    void normalize() {
	float f = norm();

	x /= f;
	y /= f;
	z /= f;
    }

    /**
     * Gets the value of x
     *
     * @return the value of x
     */
    public float getX()  {
	return this.x;
    }

    /**
     * Sets the value of x
     *
     * @param argX Value to assign to this.x
     */
    public void setX(float argX) {
	this.x = argX;
    }

    /**
     * Gets the value of y
     *
     * @return the value of y
     */
    public float getY()  {
	return this.y;
    }

    /**
     * Sets the value of y
     *
     * @param argY Value to assign to this.y
     */
    public void setY(float argY) {
	this.y = argY;
    }

    /**
     * Gets the value of z
     *
     * @return the value of z
     */
    public float getZ()  {
	return this.z;
    }

    /**
     * Sets the value of z
     *
     * @param argZ Value to assign to this.z
     */
    public void setZ(float argZ) {
	this.z = argZ;
    }

}