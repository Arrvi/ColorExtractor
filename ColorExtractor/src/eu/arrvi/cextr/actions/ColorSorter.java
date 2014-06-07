package eu.arrvi.cextr.actions;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.SwingWorker;

import eu.arrvi.cextr.beans.ParametersBean;
import eu.arrvi.cextr.common.Color;
import eu.arrvi.cextr.common.ColorPatch;

public class ColorSorter extends SwingWorker<ArrayList<ColorPatch>, Double> {
	
	ArrayList<ColorPatch> patches;
	ParametersBean params;
	
	public ColorSorter(ArrayList<ColorPatch> patches, ParametersBean params) {
		this.patches = patches;
		this.params = params;
	}
		
	@Override
	protected ArrayList<ColorPatch> doInBackground() throws Exception {

		ColorPatch sum = new ColorPatch();
		
		for (ColorPatch patch : patches) {
			sum.add(patch.getColor(), false);
		}
		
		sum.recalculate();
		
		int i=0, n=patches.size();
		for (ColorPatch patch : patches) {
			double score = 0;
			Color color = patch.getColor();
			score += (1d/color.getTemperature())*params.getTemperature();
			score += color.getBrightness()*params.getBrightness();
			score += color.getSaturation()*params.getSaturation();
			score += (1-1d/color.distance(sum.getColor(), Color.RGB_DISTANCE))*params.getDifference();
			score += (patch.getSize()/patch.getMaxSize())*params.getPatchSize();
			
			patch.setScore(score);
			i++;
			
			publish((double)i/n/2);
		}
		
		final int estimatedComparitions = (int) (patches.size()*Math.log(patches.size()));
		
		Collections.sort(patches, new Comparator<ColorPatch>() {
			int comparitions=0;
			
			@Override
			public int compare(ColorPatch o1, ColorPatch o2) {
				comparitions++;
				publish(0.5+(double)(comparitions/estimatedComparitions)/2);
				return -o1.compareTo(o2);
			}
		});
		
		return patches;
	}
	
	double lastProgress = 0;
	@Override
	protected void process(List<Double> chunks) {
		double progress = Math.min(chunks.get(chunks.size()-1), 0.999);
		System.out.println("Sorting: "+progress*100);
		
		firePropertyChange("progress", lastProgress, progress);
		lastProgress = progress;
	}
	
	@Override
	protected void done() {
		
		firePropertyChange("done", false, true);
	}

}
