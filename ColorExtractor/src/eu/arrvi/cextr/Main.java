package eu.arrvi.cextr;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	public final static String VERSION = "v. 0.1.1";
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Controller();
			}
		});
	}
}
