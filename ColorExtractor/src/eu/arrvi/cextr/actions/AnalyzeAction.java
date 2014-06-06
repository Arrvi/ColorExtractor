package eu.arrvi.cextr.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;
import eu.arrvi.cextr.common.ColorPatch;
import eu.arrvi.cextr.imagepane.ImagePane;

public class AnalyzeAction extends AbstractAction implements PropertyChangeListener {
	private final Controller controller;

	public AnalyzeAction(Controller contr) {
		controller = contr;
		
		controller.getImageBean().addPropertyChangeListener("status", this);
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(NAME, "Analyze");
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getImageBean().setStatus(ImageBean.ANALYZING);
		startProcessing();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setEnabled( evt.getNewValue().equals(ImageBean.LOADED) );
	}
	
	private void startProcessing() {
		scaleImage();
	}
	
	private void scaleImage() {
		controller.setStatus("Scaling...");
		new SwingWorker<BufferedImage, Object>() {
			BufferedImage image = controller.getImageBean().getImage();
			double scale = (double)controller.getParametersBean().getResolution()/image.getWidth();
			
			@Override
			protected BufferedImage doInBackground() throws Exception {
				AffineTransform at = new AffineTransform();
				at.scale(scale, scale);
				AffineTransformOp scaleOp = 
				   new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
				BufferedImage scaled = new BufferedImage((int)(image.getWidth()*scale), (int)(image.getHeight()*scale), image.getType());
				scaled = scaleOp.filter(image, scaled);
				return scaled;
			}
			
			@Override
			protected void done() {
				controller.setStatus("Finding patches");
				try {
					analyzeImage(get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.execute();
	}
	
	private void analyzeImage(BufferedImage image) {
//		ImagePane ip = new ImagePane(image);
//		ip.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
//		JOptionPane.showMessageDialog(null, ip, "Image", 0);
		
		PatchFinder worker = new PatchFinder(
				image, 
				controller.getParametersBean().getTolerance(), 
				40 // TODO parameter
		);
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ( evt.getPropertyName().equals("progress") ) {
					controller.setStatus(
						"Analyzing: "+(double)Math.round((double)evt.getNewValue()*10000)/100
					);
				}
				controller.getMainJFrame().repaint();
			}
		});
		worker.execute();
	}
}
