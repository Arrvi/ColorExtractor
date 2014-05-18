package eu.arrvi.cextr;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Controller {
	private MainWindow mainWindow;
	
	public Controller() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainWindow = new MainWindow(this);
	}

	public JFrame getMainJFrame() {
		return mainWindow;
	}
}
