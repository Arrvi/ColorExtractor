package eu.arrvi.cextr.imagepane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.MainWindow;
import eu.arrvi.cextr.beans.ImageBean;

/**
 * Displays image preview. One of 4 main panes in ColorExtractor.
 * 
 * @author Kris
 *
 */
public final class ImagePreviewPane extends JPanel {
	private final Controller controller;

	private JButton loadImageButton;
	private JPanel loadButtonContainer;
	private ImagePane imagePane;

	private JLabel loadingLabel;

	/**
	 * Creates new ImageLoaderPane with default size of 600x400 and a button.
	 */
	public ImagePreviewPane(Controller contr) {
		super();
		
		controller = contr;
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,400));
		setBorder(BorderFactory.createTitledBorder("Image"));
		
		imagePane = new ImagePane();
		
		loadImageButton = new JButton("Load image", new ImageIcon("res/icons/normal/Open file.png"));
		loadImageButton.setMargin(new Insets(10, 25, 10, 25));
		loadImageButton.addActionListener(controller.new LoadImageAction());
		
		loadButtonContainer = new JPanel(new GridBagLayout());
		loadButtonContainer.add(loadImageButton);

		loadingLabel = new JLabel("Loading...");
		loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		controller.getImageBean().addPropertyChangeListener("status", imageListener);;
		
		displayButton();
	}
	


	private void switchDisplayComponent(Component comp) {
		removeAll();
		add(comp);
		validate();
		repaint();
	}

	/**
	 * Creates ImagePane for loaded image and displays it.
	 */
	private void displayImage(BufferedImage image) {
		switchDisplayComponent(imagePane);
		imagePane.setImage(image);
	}
	
	private void displayLoading() {
		switchDisplayComponent(loadingLabel);
	}
	
	private void displayButton() {
		switchDisplayComponent(loadButtonContainer);
	}
	
	private PropertyChangeListener imageListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			switch ( (int)evt.getNewValue() ) {
			
			case ImageBean.LOADING:
				displayLoading();
				break;
				
			case ImageBean.LOADED:
				displayImage(controller.getImageBean().getImage());
				break;
				
			case ImageBean.ERROR_NOT_AN_IMAGE:
				showErrorDialog();
			case ImageBean.NOT_LOADED:
				displayButton();
				break;
				
			default:
				throw new RuntimeException("Unknown image code: " + (int)evt.getNewValue());
			}
		}
	};

	private void showErrorDialog() {
		JOptionPane.showMessageDialog(
			controller.getMainJFrame(), 
			"File is not an image or not readable.", 
			"Failed to load image", 
			JOptionPane.ERROR_MESSAGE
		);
	}
}
