package eu.arrvi.cextr.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import eu.arrvi.cextr.Controller;

public class AnalyzeAction extends AbstractAction {
	private final Controller controller;

	public AnalyzeAction(Controller contr) {
		controller = contr;
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(NAME, "Analyze");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
