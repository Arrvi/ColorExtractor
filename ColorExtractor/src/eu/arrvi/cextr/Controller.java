package eu.arrvi.cextr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import eu.arrvi.cextr.beans.ColorsBean;
import eu.arrvi.cextr.beans.ImageBean;
import eu.arrvi.cextr.beans.ParametersBean;

/**
 * MVC Controller for application. It stores data beans as model. Also it contains public inner classes
 * for specific actions.
 * 
 * @author Kris
 *
 */
public class Controller {
	private MainWindow mainWindow;
	private final ImageBean image;
	private final ParametersBean parameters;
	private final ColorsBean colors;
	
	/**
	 * Creates controller for application. Initiates data beans and frame with GUI. <b>It has to be called in EDT.</b>  
	 */
	public Controller() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		image = new ImageBean();
		parameters = new ParametersBean();
		colors = new ColorsBean();
		
		mainWindow = new MainWindow(this);
	}

	/**
	 * Returns main frame of application. May be useful as parent component for dialogs.
	 * 
	 * @return application main frame
	 */
	public JFrame getMainJFrame() {
		return mainWindow;
	}
	
	/**
	 * Action for image loading. It contains file chooser and loading process handling.
	 * Modifies image bean.
	 * 
	 * @author Kris
	 *
	 */
	public class LoadImageAction implements ActionListener {
		private JFileChooser fc = new JFileChooser();
		private File selectedFile;
		private BufferedImage loadedImage;
		
		/**
		 * Creates action with filtered file chooser that is shown on actionPerformed call.
		 */
		public LoadImageAction() {
			FileFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
			fc.setFileFilter(filter);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = fc.showOpenDialog((JComponent)e.getSource());
			
			if ( result == JFileChooser.APPROVE_OPTION ) {
				selectedFile = fc.getSelectedFile();
				
				loadImage(selectedFile);
			}
		}
		

		/**
		 * Loads image with SwingWorker. Calls displayImage when loaded.
		 * 
		 * @param fileToLoad File to load via ImageIO.read(). Must be image.
		 */
		public void loadImage(final File fileToLoad) {
			image.setStatus(ImageBean.LOADING);
			
			new SwingWorker<Boolean, Void>() {
				@Override
				protected Boolean doInBackground() throws Exception {
					try {
						loadedImage = ImageIO.read(fileToLoad);
						return loadedImage != null;
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				}
				
				protected void done() {
					try {
						if (get()) {
							image.setImage(loadedImage);
							image.setStatus(ImageBean.LOADED);
						}
						else {
							image.setStatus(ImageBean.ERROR_NOT_AN_IMAGE);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.execute();	
		}
		
	}
	
	public class AnalyzeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class CloseImageAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			image.setStatus(ImageBean.NOT_LOADED);
			image.setImage(null);
		}
	}
	
	/**
	 * Returns image data bean for adding listeners and other stuff.
	 * 
	 * @return image data bean
	 */
	public ImageBean getImageBean() {
		return image;
	}

	public ParametersBean getParametersBean() {
		return parameters;
	}

	public ColorsBean getColorsBean() {
		return colors;
	}
}
