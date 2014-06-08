package eu.arrvi.cextr.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.about.AboutWindow;

/**
 * Menu bar for Color Extractor.
 * 
 * @author Kris
 *
 */
public class MenuBar extends JMenuBar {
	private final Controller controller;
	
	/**
	 * Creates menu bar with 3 menus - File, Edit, Help
	 */
	public MenuBar(Controller contr) {
		super();
		
		controller = contr;
		
		add(createFileMenu());
		add(createHelpMenu());
	}
	
	/**
	 * Creates file menu.<br>
	 * Contents:
	 * <ul>
	 * 	<li>Open
	 * 	<li>Close
	 * 	<li>Exit
	 * </ul>
	 * 
	 * @return ready to use "file menu"
	 */
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		
		JMenuItem item;
		
		item = new JMenuItem(controller.getLoadAction());
		menu.add(item);
		
		item = new JMenuItem(controller.getCloseImageAction());
		menu.add(item);
		
		menu.addSeparator();
		
		item = new JMenuItem(controller.getExitAction());
		menu.add(item);
		
		return menu;
	}
	
	/**
	 * Creates help menu.<br>
	 * Contents:
	 * <ul>
	 * 	<li>About
	 * </ul>
	 * 
	 * @return ready to use "help menu"
	 */
	private JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About...");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutWindow(controller.getMainJFrame());
			}
		});
		menu.add(about);
		
		return menu;
	}
	
	
}
