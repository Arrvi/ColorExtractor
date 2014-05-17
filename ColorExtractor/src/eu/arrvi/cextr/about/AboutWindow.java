package eu.arrvi.cextr.about;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.arrvi.cextr.Main;
import eu.arrvi.cextr.MainWindow;

/**
 * Information dialog about application. Shown when clicked on <tt>Help &rarr; About</tt> menu.
 * 
 * @author Kris
 * @see eu.arrvi.cexrt.menu.MenuBar.createHelpMenu
 */
public class AboutWindow extends JDialog {
	
	/**
	 * Creates fully functional information dialog and displays it.
	 * @param owner Owner of modal dialog
	 */
	public AboutWindow(Frame owner) {
		super(owner, "About Color Extractor", true);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		JLabel about = new JLabel(
			"<html><body><center>"+
			"<h1>Color Extractor</h1>"+
			"<p><font color=gray>"+Main.VERSION+"</font></p>"+
			"<p>by Kristian Kann</p>"+
			"<p>Color Extractor analyses given image and extracts color samples from it.</p>"+
			"<hr>"+
			"Icons by www.aha-soft.com licenced under Creative Commons 3.0"+
			"</center></body></html>"
		);
		about.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(about);
		
		pane.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JButton reflink;
		
		reflink = new JButton("Programmer's home page", new ImageIcon("res/icons/normal/Mouse.png"));
		reflink.addActionListener(new LinkOpener("http://arrvi.eu/"));
		reflink.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(reflink);

		pane.add(Box.createRigidArea(new Dimension(0, 20)));
		
		reflink = new JButton("Icon creator", new ImageIcon("res/icons/normal/Cat.png"));
		reflink.addActionListener(new LinkOpener("http://www.aha-soft.com/"));
		reflink.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(reflink);

		pane.add(Box.createRigidArea(new Dimension(0, 20)));
				
		JButton close = new JButton("Close", new ImageIcon("res/icons/normal/Delete.png"));
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutWindow.this.dispose();
			}
		});
		close.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(close);
		
		this.setContentPane(pane);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

/**
 * Link opener action listener for button links.
 * 
 * @author Kris
 *
 */
class LinkOpener implements ActionListener {
	private URI uri;
	
	/**
	 * Creates new action listener for opening links
	 * 
	 * @param uri URI to open on action
	 */
	public LinkOpener(String uri) {
		try {
			this.uri = new URI(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}