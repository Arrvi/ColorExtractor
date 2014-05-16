package eu.arrvi.cextr.statusbar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 * Status bar for main window. Displays runtime messages and loaded image resolution.
 * 
 * @author Kris
 *
 */
public class StatusBar extends JPanel {
	final private JLabel status;
	final private JLabel resolution;
	private Dimension res;
	
	/**
	 * Creates status bar with 2 fields and upper separator (acting as top etched border).
	 */
	public StatusBar() {
		super();
		this.setLayout(new BorderLayout());
		
		status = new JLabel(" ");
		status.setBorder(new EmptyBorder(3, 6, 3, 10));
		
		resolution = new JLabel(" ");
		resolution.setPreferredSize(new Dimension(100, 0));
		resolution.setHorizontalAlignment(JLabel.RIGHT);
		resolution.setBorder(new EmptyBorder(3, 10, 3, 6));
		
		this.add(status, BorderLayout.CENTER);
		this.add(resolution, BorderLayout.EAST);
		this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
	}

	/**
	 * Returns currently displayed message
	 * 
	 * @return Message from status bar
	 */
	public String getStatus() {
		return status.getText();
	}
	
	/**
	 * Overrides message on status bar. 
	 * 
	 * @param str New message
	 */
	public void setStatus(String str) {
		status.setText(str);
	}
	
	/**
	 * Returns currently stored image resolution.
	 * 
	 * @return Image resolution from status bar
	 */
	public Dimension getResolution() {
		return res;
	}
	
	/**
	 * Overrides resolution display on status bar. Will be displayed as [width]x[height].
	 * If <tt>res</tt> is null, field will be set to blank. This is only informative object
	 * - does not affect processed image resolution nor any other application logic.
	 * 
	 * @param res New resolution
	 */
	public void setResolution(Dimension res) {
		this.res = res;
		if (res != null)
			resolution.setText(res.width+"x"+res.height);
		else
			resolution.setText(" ");
	}
}
