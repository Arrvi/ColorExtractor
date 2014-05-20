package eu.arrvi.cextr.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import eu.arrvi.cextr.Controller;

public class ExitAction extends AbstractAction {
	private final Controller controller;
	
	public ExitAction(Controller contr){
		controller = contr;
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		putValue(NAME, "Exit...");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int decision = JOptionPane.showConfirmDialog(
				controller.getMainJFrame(), 
				"Do you really want to exit?", 
				"Exit the application", 
				JOptionPane.YES_NO_OPTION
		);
		if ( decision == JOptionPane.YES_OPTION ) {
			controller.getMainJFrame().dispose();
		}
	}
	
}
