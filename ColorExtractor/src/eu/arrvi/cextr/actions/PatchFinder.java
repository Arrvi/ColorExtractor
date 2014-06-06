package eu.arrvi.cextr.actions;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import eu.arrvi.cextr.colortable.Color;
import eu.arrvi.cextr.common.ColorPatch;

public class PatchFinder extends SwingWorker<ArrayList<ColorPatch>, Double> {
	
	private final BufferedImage image;
	private final BufferedImage excludePixels;
	private final double tolerance;
	private final int minSize;
	
	private final static int EXCLUDE = Color.WHITE.getRGB();
	private final static int INCLUDE = Color.BLACK.getRGB();
	private final static int RED = Color.RED.getRGB();
	private final static int GREEN = Color.GREEN.getRGB();
	
	public PatchFinder(BufferedImage image, double tolerance, int minSize) {
		this.image = image;
		excludePixels = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		this.tolerance = tolerance*100;
		this.minSize = minSize;
	}

	@Override
	protected ArrayList<ColorPatch> doInBackground() throws Exception {
		ArrayList<ColorPatch> patches = new ArrayList<>();
		
		publish(new Double(0.0));
		
		for (int y=0; y<image.getHeight(); ++y) {
			for (int x=0; x<image.getWidth(); ++x) {
				if ( isIncluded(x, y) ) {
					ColorPatch patch = followPixel(x, y);
					if ( patch != null ) {
						patches.add(patch);
						System.out.println("Got patch: "+patch);
					}
				}
			}
			publish(new Double((double)(y+1)/image.getHeight()));
		}
		
		return patches;
	}

	private double lastProgress = 0;
	@Override
	protected void process(List<Double> chunks) {
		for (double part : chunks) {
			System.out.println("Done "+(double)Math.round(part*10000)/100+"%");
		}
		firePropertyChange("progress", lastProgress, chunks.get(0));
		lastProgress = chunks.get(0);
	}
	
	@Override
	protected void done() {
		try {
			int patchesCount = get().size();
			System.out.println(patchesCount+" patches");
			firePropertyChange("progress", lastProgress, 1d);
			lastProgress=1d;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ColorPatch followPixel(int x, int y) {
		ColorPatch patch = new ColorPatch(pixel(x, y));
		goRight(patch, x, y);
		splitDown(patch, x, y);
		
		if ( patch.getSize() >= minSize )
			return patch;
		else
			return null;
	}

	private Color pixel(int x, int y) {
		return new Color(image.getRGB(x, y));
	}

	private void splitDown(ColorPatch patch, int x, int y) {
		if ( ++y < image.getHeight() ) {
			Color color = pixel(x, y);
			if ( isIncluded(x, y) ) {
				if ( patch.getColor().distance(color, Color.RGB_DISTANCE) < tolerance ) {
					patch.add(color);
					goLeft(patch, x, y);
					goRight(patch, x, y);
					splitDown(patch, x, y);
					
//					image.setRGB(x, y, GREEN);
				}
				else {
					image.setRGB(x, y, RED);
				}
			}
			exclude(x, y);
		}
	}

	private void goDown(ColorPatch patch, int x, int y) {
		if ( ++y < image.getHeight() ) {
			Color color = pixel(x, y);
			if ( isIncluded(x, y) ) {
				if ( patch.getColor().distance(color, Color.RGB_DISTANCE) < tolerance ) {
					patch.add(color);
					goRight(patch, x, y);
					goDown(patch, x, y);
					
//					image.setRGB(x, y, GREEN);
				}
				else {
					image.setRGB(x, y, RED);
				}
			}
			exclude(x, y);
		}
	}

	private void goRight(ColorPatch patch, int x, int y) {
		if ( ++x < image.getWidth() ) {
			Color color = pixel(x, y);
			if ( isIncluded(x, y) ) {
				if ( patch.getColor().distance(color, Color.RGB_DISTANCE) < tolerance ) {
					patch.add(color);
					goDown(patch, x, y);
					goRight(patch, x, y);
					
//					image.setRGB(x, y, GREEN);
				}
				else {
					image.setRGB(x, y, RED);
				}
			}
			exclude(x, y);
		}
	}

	private void goLeft(ColorPatch patch, int x, int y) {
		if ( --x >= 0 ) {
			Color color = pixel(x, y);
			if ( isIncluded(x, y) ) {
				if ( patch.getColor().distance(color, Color.RGB_DISTANCE) < tolerance ) {
					patch.add(color);
					goLeft(patch, x, y);
					
//					image.setRGB(x, y, GREEN);
				}
				else {
					image.setRGB(x, y, RED);
				}
			}
			exclude(x, y);
		}
	}
	
	private boolean isIncluded(int x, int y) {
		return excludePixels.getRGB(x, y) == INCLUDE;
	}
	
	private void exclude(int x, int y) {
		excludePixels.setRGB(x, y, EXCLUDE);
	}

}
