package org.gljava.opengl.ftgl;

public class BoundingBox {

    float llx;
    float lly;
    float llz;
    float urx;
    float ury;
    float urz;

    public BoundingBox(float llx, float lly, float llz, float urx, float ury, float urz) {
	this.llx = llx;
	this.lly = lly;
	this.llz = llz;

	this.urx = urx;
	this.ury = ury;
	this.urz = urz;
    }

    
    /**
     * Gets the value of llx
     *
     * @return the value of llx
     */
    public float getLlx()  {
	return this.llx;
    }

    /**
     * Sets the value of llx
     *
     * @param argLlx Value to assign to this.llx
     */
    public void setLlx(float argLlx) {
	this.llx = argLlx;
    }

    /**
     * Gets the value of lly
     *
     * @return the value of lly
     */
    public float getLly()  {
	return this.lly;
    }

    /**
     * Sets the value of lly
     *
     * @param argLly Value to assign to this.lly
     */
    public void setLly(float argLly) {
	this.lly = argLly;
    }

    /**
     * Gets the value of llz
     *
     * @return the value of llz
     */
    public float getLlz()  {
	return this.llz;
    }

    /**
     * Sets the value of llz
     *
     * @param argLlz Value to assign to this.llz
     */
    public void setLlz(float argLlz) {
	this.llz = argLlz;
    }

    /**
     * Gets the value of urx
     *
     * @return the value of urx
     */
    public float getUrx()  {
	return this.urx;
    }

    /**
     * Sets the value of urx
     *
     * @param argUrx Value to assign to this.urx
     */
    public void setUrx(float argUrx) {
	this.urx = argUrx;
    }

    /**
     * Gets the value of ury
     *
     * @return the value of ury
     */
    public float getUry()  {
	return this.ury;
    }

    /**
     * Sets the value of ury
     *
     * @param argUry Value to assign to this.ury
     */
    public void setUry(float argUry) {
	this.ury = argUry;
    }

    /**
     * Gets the value of urz
     *
     * @return the value of urz
     */
    public float getUrz()  {
	return this.urz;
    }

    /**
     * Sets the value of urz
     *
     * @param argUrz Value to assign to this.urz
     */
    public void setUrz(float argUrz) {
	this.urz = argUrz;
    }

    public String toString() {
	StringBuffer buf = new StringBuffer();

	buf.append("BoundingBox[llx=").append(llx).
	    append(", lly=").append(lly).
	    append(", llz=").append(llz).
	    append(", urx=").append(urx).
	    append(", ury=").append(ury).
	    append(", urz=").append(urz);

	return buf.toString();
    }

}