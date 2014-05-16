package eu.arrvi.cextr.colortable;

import java.util.Locale;


public class Color extends java.awt.Color {
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
}
