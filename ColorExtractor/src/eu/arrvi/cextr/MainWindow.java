package eu.arrvi.cextr;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import eu.arrvi.cextr.colortable.ColorTablePane;
import eu.arrvi.cextr.imagepane.ImageLoaderPane;
import eu.arrvi.cextr.menu.MenuBar;
import eu.arrvi.cextr.parameters.ParametersPane;
import eu.arrvi.cextr.statusbar.StatusBar;

/**
 * Main window singleton of Color Extractor application. Has 4 panes and a status bar.<br>
 * Panes:
 * <ul>
 * 	<li>WEST - preset tree
 * 	<li>CENTER - image preview
 * 	<li>EAST - algorithm parameters
 * 	<li>SOUTH - color table
 * </ul>
 * 
 * @author Kris
 */
public final class MainWindow {
	private final static double VERSION = 0.1; 
	
	private static final MainWindow INSTANCE = new MainWindow();
	private JFrame frame;
	private StatusBar status;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				INSTANCE.init();
			}
		});
	}
	
	/**
	 * Returns readable version string.
	 * 
	 * @return version string
	 */
	public static String versionString() {
		return "v."+VERSION;
	}
	
	/**
	 * Returns instance of MainWindow singleton
	 * 
	 * @return MainWindow singleton
	 */
	public static MainWindow getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Returns JFrame of main window. May be usable as dialog parent.
	 * 
	 * @return main window frame
	 */
	public static JFrame getJFrame() {
		return INSTANCE.frame;
	}
	
	/**
	 * Returns StatusBar object for message modifications.<br>
	 * Example:<br>
	 * <code>MainWindow.getStatusBar().setStatus("Ready");</code>
	 *
	 * @return status bar object
	 */
	public static StatusBar getStatusBar() {
		return INSTANCE.status;
	}
	
	/**
	 * Initiates main frame. Must be executed in EDT!
	 */
	private void init() {
		frame = new JFrame("Color Extractor "+versionString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new MenuBar());
		frame.setLayout(new BorderLayout());
		
		JPanel mainPane = new JPanel(new BorderLayout(10,10));
		mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPane.add(new ImageLoaderPane(), BorderLayout.CENTER);
		mainPane.add(new ParametersPane(), BorderLayout.EAST);
		mainPane.add(new ColorTablePane(), BorderLayout.SOUTH);
		
		frame.add(mainPane, BorderLayout.CENTER);

		status = new StatusBar();
		frame.add(status, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		status.setStatus("Ready");
	}
}
