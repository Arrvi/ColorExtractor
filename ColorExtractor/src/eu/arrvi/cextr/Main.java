package eu.arrvi.cextr;

import javax.swing.SwingUtilities;

public class Main {
	public final static String VERSION = "v. 0.1.1";
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Controller();
			}
		});
	}
}
