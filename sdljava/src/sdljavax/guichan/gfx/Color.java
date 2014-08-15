/*
 * Created on Mar 5, 2005 (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.gfx;

/**
 * Class that represents a color.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class Color {

	/**
	 * Color alpha, used for transparency. A value of 0 means totaly transparent, 255 is totaly
	 * opaque (the default)
	 */
	public int	a;
	/**
	 * Blue color component (range 0-255).
	 */
	public int	b;
	/**
	 * Green color component (range 0-255).
	 */
	public int	g;
	/**
	 * Red color component (range 0-255).
	 */
	public int	r;

	/**
	 * Constructor. Gives the color black.
	 */
	public Color() {
		r = 0;
		g = 0;
		b = 0;
		a = 255;
	}

	/**
	 * Constructor that constructs a color from the bytes in an integer. Call it with a hexadecimal
	 * constant for HTML-style color representation. For example, Color(0xff50a0) constructs
	 * Gui-chan's favourite color. The alpha component will always be set to 255 by this
	 * constructor. NOTE: Because of this constructor, integers will be automatically casted to a
	 * color by your compiler.
	 * 
	 * @param color
	 *            the color.
	 */
	public Color(int color) {
		r = (color >> 16) & 0xFF;
		g = (color >> 8) & 0xFF;
		b = color & 0xFF;
		a = 255;
	}

	/**
	 * Constructor.
	 * 
	 * @param r
	 *            Red color component (range 0-255).
	 * @param g
	 *            Green color component (range 0-255).
	 * @param b
	 *            Blue color component (range 0-255).
	 * @param a
	 *            Color alpha, used for transparency. A value of 0 means totaly transparent, 255 is
	 *            totaly opaque (the default).
	 */
	public Color(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	/**
	 * Subtracts the RGB values of one color from another. The values will be clamped if they go out
	 * of range. The returned color will always have alpha of 255.
	 * 
	 * @param color
	 *            a color to subtract from this color.
	 * @return the resulting color.
	 */
	public Color subtract(final Color color) {
		Color result = new Color();
		result.r = r - color.r;
		result.g = g - color.g;
		result.b = b - color.b;
		result.a = 255;

		result.r = (result.r > 255 ? 255 : (result.r < 0 ? 0 : result.r));
		result.g = (result.g > 255 ? 255 : (result.g < 0 ? 0 : result.g));
		result.b = (result.b > 255 ? 255 : (result.b < 0 ? 0 : result.b));

		return result;
	}

	/**
	 * Subtracts the RGB values of one color from another. The values will be clamped if they go out
	 * of range. The returned color will always have alpha of 255.
	 * 
	 * @param nValue
	 *            a color value to subtract from this color.
	 * @return the resulting color.
	 */
	public Color subtract(int nValue) {
		return subtract(new Color(nValue));
	}

	/**
	 * Adds the RGB values of two colors together. The values will be clamped if they go out of
	 * range. The returned color will always have alpha of 255.
	 * 
	 * @param color
	 *            a color to add to this color.
	 * @return the resulting color.
	 */
	public Color add(final Color color) {
		Color result = new Color();
		result.r = r + color.r;
		result.g = g + color.g;
		result.b = b + color.b;
		result.a = 255;

		result.r = (result.r > 255 ? 255 : (result.r < 0 ? 0 : result.r));
		result.g = (result.g > 255 ? 255 : (result.g < 0 ? 0 : result.g));
		result.b = (result.b > 255 ? 255 : (result.b < 0 ? 0 : result.b));

		return result;
	}

	/**
	 * Adds the RGB values of two colors together. The values will be clamped if they go out of
	 * range. The returned color will always have alpha of 255.
	 * 
	 * @param nValue
	 *            a color value to add to this color.
	 * @return the resulting color.
	 */
	public Color add(int nValue) {
		return add(new Color(nValue));
	}

	/**
	 * Multiplies the RGB values of a color with a double value. The values will be clamped if they
	 * go out of range. The alpha component will be left untouched.
	 * 
	 * @param value
	 *            the value to multiply the color with.
	 * @return the resulting color.
	 */
	public Color multiply(double value) {
		Color result = new Color();
		result.r = (int) (r * value);
		result.g = (int) (g * value);
		result.b = (int) (b * value);
		result.a = a;

		result.r = (result.r > 255 ? 255 : (result.r < 0 ? 0 : result.r));
		result.g = (result.g > 255 ? 255 : (result.g < 0 ? 0 : result.g));
		result.b = (result.b > 255 ? 255 : (result.b < 0 ? 0 : result.b));

		return result;
	}

	/**
	 * Compares two colors.
	 * 
	 * @param obj
	 *            The Color to compare this object with
	 * @return true if the two colors have the same RGBA components.
	 */
	public boolean equals(Object obj) {
		Color color = (Color) obj;
		return color.r == r && color.b == b && color.g == g && color.a == a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a;
	}

	/**
	 * Create a copy of this Object
	 * 
	 * @return a newly created copy of this Object
	 */
	public Object clone() {
		return new Color(r, g, b, a);
	}
}
