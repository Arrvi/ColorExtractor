package eu.arrvi.cextr;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.arrvi.cextr.colortable.ColorTablePane;
import eu.arrvi.cextr.imagepane.ImagePreviewPane;
import eu.arrvi.cextr.menu.MenuBar;
import eu.arrvi.cextr.parameters.ParametersPane;
import eu.arrvi.cextr.statusbar.StatusBar;

/**
 * Main window singleton of Color Extractor application. Has 4 panes and a status bar.<br>
 * 
 * Panes:
 * <table border=1  style="text-align:center;">
 * <tr>
 *  <td colspan=3>Menu
 * <tr>
 * 	<td>WEST<br>preset tree
 * 	<td>CENTER<br>image preview
 * 	<td>EAST<br>algorithm parameters
 * <tr>
 * 	<td colspan=3>SOUTH<br>color table
 * <tr>
 *  <td colspan=3>StatusBar
 * </table>
 * 
 * @author Kris
 */
public final class MainWindow extends JFrame {
	
	private final Controller controller;
	private StatusBar status;

	MainWindow(Controller contr) {
		super("Color Extractor "+Main.VERSION);
		
		controller = contr;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar(contr));
		this.setLayout(new BorderLayout());
		
		JPanel mainPane = new JPanel(new BorderLayout(10,10));
		mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPane.add(new ImagePreviewPane(contr), BorderLayout.CENTER);
		mainPane.add(new ParametersPane(contr), BorderLayout.EAST);
		mainPane.add(new ColorTablePane(contr), BorderLayout.SOUTH);
		
		this.add(mainPane, BorderLayout.CENTER);

		status = new StatusBar(controller);
		this.add(status, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Controller getController() {
		return controller;
	}
	
	public StatusBar getStatusBar() {
		return status;
	}
}
