package eu.arrvi.cextr.common;

import javax.swing.AbstractAction;

import eu.arrvi.cextr.Controller;

public abstract class ControlledAction extends AbstractAction {
	private final Controller controller;
	
	public ControlledAction(Controller contr) {
		controller = contr;
	}
	
	public Controller getController() {
		return controller;
	}
}
