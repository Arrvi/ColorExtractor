package eu.arrvi.cextr.common;

import java.util.HashSet;

public class ColorPatch implements Comparable<ColorPatch> {
	private HashSet<Color> colorSet = new HashSet<>();
	private Color color;
	private double score=0;
	
	private static int maxSize=0;
	
	public ColorPatch() {
		
	}
	
	public ColorPatch(Color color) {
		this.color = color;
		colorSet.add(color);
	}
	
	public void add(Color color) {
		add(color, true);
	}
	
	public void add(Color color, boolean recalculate) {
		colorSet.add(color);
		if ( recalculate ) recalculate();
	}
	
	public void recalculate() {
		this.color = Color.avgColor(colorSet);
		if ( getSize() > maxSize ) maxSize = getSize(); 
	}
	
	public int getMaxSize() {
		return maxSize;
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

	public double getScore() {
		return score;
	}

	public synchronized void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(ColorPatch patch) {
		return (int) ((this.getScore() - patch.getScore())*100000);
	}
}
