package eu.arrvi.cextr.common;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import eu.arrvi.cextr.colortable.Color;

public class ColorPatch {
	private HashSet<Color> colorSet = new HashSet<>();
	private Color color;
	
	public ColorPatch(Color color) {
		this.color = color;
		colorSet.add(color);
	}
	
	public void add(Color color) {
		colorSet.add(color);
		this.color = Color.avgColor(colorSet);
	}
	public int getSize() {
		return colorSet.size();
	}
	public HashSet<Color> getColorSet() {
		return colorSet;
	}
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return String.format("ColorPatch[%d] #%02x%02x%02x", getSize(), color.getRed(), color.getGreen(), color.getBlue());
	}
}
