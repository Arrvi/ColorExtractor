package eu.arrvi.cextr.actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;
import eu.arrvi.cextr.common.ColorPatch;

public class AnalyzeAction extends AbstractAction implements PropertyChangeListener {
	private final Controller controller;
	private final JDialog dialog;
	private final JProgressBar progressBar = new JProgressBar(0, 1000);
	private final JLabel progressLabel = new JLabel("Progress");
	private SwingWorker<ArrayList<ColorPatch>, Double> worker;

	public AnalyzeAction(Controller contr) {
		controller = contr;
		dialog = new JDialog(controller.getMainJFrame(), "Progress", true);
		JPanel pane = (JPanel)dialog.getContentPane();
		pane.setBorder(new EmptyBorder(10, 10, 10, 10));
		dialog.add(progressLabel, BorderLayout.NORTH);
		
		JPanel progressbarContainer = new JPanel(new GridBagLayout());
		progressbarContainer.add(progressBar, new GridBagConstraints());
		dialog.add(progressbarContainer, BorderLayout.CENTER);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setSize(350, 130);
		dialog.setLocationRelativeTo(controller.getMainJFrame());
		
		controller.getImageBean().addPropertyChangeListener("status", this);
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(NAME, "Analyze");
		setEnabled(false);
	}
	
	private void setProgress(String message, double progress) {
		progressBar.setIndeterminate(false);
		progressBar.setValue((int) (progress*1000));
		progressLabel.setText(message);
		controller.setStatus(message);
	}
	private void setProgress(String message) {
		progressBar.setIndeterminate(true);
		progressLabel.setText(message);
		controller.setStatus(message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getImageBean().setStatus(ImageBean.ANALYZING);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				dialog.setVisible(true);
			}
		}).start();
		
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
		setProgress("Scaling...");
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
		setProgress("Analyzing...");
		worker = new PatchFinder(
				image, 
				controller.getParametersBean().getTolerance(), 
				40 // TODO parameter
		);
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ( evt.getPropertyName().equals("progress") ) {
					setProgress(
						"Analyzing: "+(double)Math.round((double)evt.getNewValue()*10000)/100+"%",
						(double)evt.getNewValue()
					);
				}
				if ( evt.getPropertyName().equals("done") && (boolean)evt.getNewValue() ) {
					try {
						sortPatches(worker.get());
					} catch (InterruptedException e) {
						controller.getImageBean().setStatus(ImageBean.LOADED);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
		worker.execute();
	}
	
	private void sortPatches(ArrayList<ColorPatch> patches) {
		
		setProgress("Sorting...", 0);
		worker = new ColorSorter(patches, controller.getParametersBean());
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ( evt.getPropertyName().equals("progress")) {
					setProgress("Sorting: "+(double)evt.getNewValue()*100+"%", (double)evt.getNewValue());
				}
				else if ( evt.getPropertyName().equals("done") && (boolean)evt.getNewValue() ) {
					try {
						dialog.setVisible(false);
						controller.setStatus("Done");
						controller.getColorsBean().setPatches(worker.get());
						controller.getImageBean().setStatus(ImageBean.LOADED);
					} catch (InterruptedException e) {
						controller.getImageBean().setStatus(ImageBean.LOADED);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		worker.execute();
	}
}