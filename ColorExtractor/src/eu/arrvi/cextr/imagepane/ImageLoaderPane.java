package eu.arrvi.cextr.imagepane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

/**
 * Displays image preview. One of 4 main panes in ColorExtractor.
 * 
 * @author Kris
 *
 */
public final class ImageLoaderPane extends JPanel implements ActionListener {
	private final Controller controller;
	
	private JFileChooser fc = new JFileChooser();
	private File selectedFile;
	private JButton loadImageButton;
	private JPanel loadButtonContainer;
	private BufferedImage image;
	private ImagePane imagePane;

	/**
	 * Creates new ImageLoaderPane with default size of 600x400 and a button.
	 */
	public ImageLoaderPane(Controller contr) {
		super();
		
		controller = contr;
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,400));
		setBorder(BorderFactory.createTitledBorder("Image"));
		
		imagePane = new ImagePane();
		
		loadImageButton = new JButton("Load image", new ImageIcon("res/icons/normal/Open file.png"));
		loadImageButton.setMargin(new Insets(10, 25, 10, 25));
		loadImageButton.addActionListener(this);
		
		loadButtonContainer = new JPanel(new GridBagLayout());
		loadButtonContainer.add(loadImageButton);
		
		
		switchDisplayComponent(loadButtonContainer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == loadImageButton ) {
			int result = fc.showOpenDialog(this);
			
			if ( result == JFileChooser.APPROVE_OPTION ) {
				selectedFile = fc.getSelectedFile();
				
				loadImage(selectedFile);
			}
		}
	}

	/**
	 * Creates ImagePane for loaded image and displays it.
	 */
	private void displayImage() {
		switchDisplayComponent(imagePane);
		imagePane.setImage(image);
	}

	private void switchDisplayComponent(Component comp) {
		removeAll();
		add(comp);
		validate();
		repaint();
	}

	/**
	 * Loads image with SwingWorker. Calls displayImage when loaded.
	 * 
	 * @param fileToLoad File to load via ImageIO.read(). Must be image.
	 */
	public void loadImage(final File fileToLoad) {
		final JLabel loadingLabel = new JLabel("Loading...");
		loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

		switchDisplayComponent(loadingLabel);
		
		new SwingWorker<Boolean, Void>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				try {
					image = ImageIO.read(fileToLoad);
					return image != null;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			
			protected void done() {
				try {
					if (get()) {
						displayImage();
					}
					else {
						JOptionPane.showMessageDialog(
							ImageLoaderPane.this, 
							"File is not an image or not readable.", 
							"Failed to load image", 
							JOptionPane.ERROR_MESSAGE
						);
						switchDisplayComponent(loadButtonContainer);
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
