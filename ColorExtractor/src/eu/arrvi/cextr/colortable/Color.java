package eu.arrvi.cextr.colortable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;


public class Color extends java.awt.Color {
	public final static int RGB_DISTANCE=1;
	public final static int HSV_DISTANCE=2;
	
	private double h,s,v;
	
	public Color(int color) {
		super(color);
		
		float[] hsv = java.awt.Color.RGBtoHSB(
				this.getRed(), 
				this.getGreen(), 
				this.getBlue(), 
				null
		);
		
		h = hsv[0];
		s = hsv[1];
		v = hsv[2];
	}
	
	public Color(int r, int g, int b) {
		super(r,g,b);
	}
	
	public double getHue() {
		return h;
	}
	
	public double getSaturation() {
		return s;
	}
	
	public double getValue() {
		return v;
	}
	public double getBrightness() {
		return v;
	}
	
	public Color invert() {
		return new Color(255-getRed(), 255-getGreen(), 255-getBlue());
	}
	
	public double distance(Color color, int mode) {
		if (mode == RGB_DISTANCE) {
			int red = this.getRed()-color.getRed();
			int green = this.getGreen()-color.getGreen();
			int blue = this.getBlue()-color.getBlue();
			return Math.sqrt(red*red + green*green + blue*blue);
		}
		else if (mode == HSV_DISTANCE) {
			return 1000;
		}
		else {
			throw new IllegalArgumentException("Unknown distance calculation mode");
		}
	}
	
	public String toRGBString() {
		return String.format(Locale.ENGLISH, "rgb(%d, %d, %d)", getRed(), getGreen(), getBlue());
	}
	public String toHSBString() {
		return String.format(Locale.ENGLISH, "hsb(%.2f, %.2f, %.2f)", h, s, v);
	}
	public String toHSVString() {
		return String.format(Locale.ENGLISH, "hsv(%.2f, %.2f, %.2f)", h, s, v);
	}
	public String toHTMLString() {
		return String.format(Locale.ENGLISH, "#%02X%02X%02X", getRed(), getGreen(), getBlue());
	}
	
	public static Color avgColor(HashSet<Color> colors) {
		long red=0, green=0, blue=0;
		for (Iterator iterator = colors.iterator(); iterator.hasNext();) {
			Color color = (Color) iterator.next();
			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
		}
		return new Color(
			(int)(red/colors.size()),
			(int)(green/colors.size()),
			(int)(blue/colors.size())
		);
	}
}
