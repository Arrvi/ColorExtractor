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
		add(createEditMenu());
		add(createHelpMenu());
	}
	
	/**
	 * Creates file menu.<br>
	 * Contents:
	 * <ul>
	 * 	<li>Open
	 * 	<li>Close
	 * 	<li>Export
	 * 	<li>Exit
	 * </ul>
	 * 
	 * TODO menu items
	 * TODO actions
	 * 
	 * @return ready to use "file menu"
	 */
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		
		return menu;
	}
	
	/**
	 * Creates edit menu.<br>
	 * Contents:
	 * <ul>
	 * 	<li>Undo
	 * 	<li>Redo
	 * 	<li>Preferences
	 * </ul>
	 * 
	 * TODO menu items
	 * TODO actions
	 * 
	 * @return ready to use "edit menu"
	 */
	private JMenu createEditMenu() {
		JMenu menu = new JMenu("Edit");
		
		return menu;
	}
	
	/**
	 * Creates help menu.<br>
	 * Contents:
	 * <ul>
	 * 	<li>Help (not sure)
	 * 	<li>About
	 * </ul>
	 * 
	 * TODO help
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
