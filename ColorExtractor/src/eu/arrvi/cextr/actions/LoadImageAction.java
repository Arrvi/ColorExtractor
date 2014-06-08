package eu.arrvi.cextr.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;


/**
 * Action for image loading. It contains file chooser and loading process handling.
 * Modifies image bean.
 * 
 * @author Kris
 *
 */
public class LoadImageAction extends AbstractAction {
	private JFileChooser fc = new JFileChooser();
	private File selectedFile;
	private BufferedImage loadedImage;
	private final Controller controller;
	
	/**
	 * Creates action with filtered file chooser that is shown on actionPerformed call.
	 */
	public LoadImageAction(Controller contr){
		controller = contr;
		
		FileFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
		fc.setFileFilter(filter);
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);
		putValue(NAME, "Load image...");
		putValue(LARGE_ICON_KEY, new ImageIcon("res/icons/normal/Open file.png"));
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
		controller.getImageBean().setStatus(ImageBean.LOADING);
		controller.setStatus("Loadin image...");
		
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
						controller.getImageBean().setImage(loadedImage);
						controller.getImageBean().setStatus(ImageBean.LOADED);
						controller.setStatus("Image loaded", 5000);
						controller.setStatusResolution(new Dimension(
							loadedImage.getWidth(), 
							loadedImage.getHeight()
						));
					}
					else {
						controller.getImageBean().setStatus(ImageBean.ERROR_NOT_AN_IMAGE);
						controller.setStatus("File is not readable or not an image");
					}
				} catch (Exception e) {
					controller.setStatus("Error occured: "+e.getMessage());
					e.printStackTrace();
				}
			};
		}.execute();	
	}
	
}
