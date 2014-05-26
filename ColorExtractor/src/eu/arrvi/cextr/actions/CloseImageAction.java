package eu.arrvi.cextr.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;

public class CloseImageAction extends AbstractAction {
	private final Controller controller;
	
	public CloseImageAction(Controller contr) {
		controller = contr;
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control W"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		putValue(NAME, "Close image");
		setEnabled(false);
		
		controller.getImageBean().addPropertyChangeListener("status", imageListener);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getImageBean().setStatus(ImageBean.NOT_LOADED);
		controller.getImageBean().setImage(null);
		controller.setStatus("Image closed", 3000);
		controller.setStatusResolution(null);
	}
	
	private PropertyChangeListener imageListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if ((int)evt.getNewValue() == ImageBean.LOADED) {
				setEnabled(true);
			}
			else {
				setEnabled(false);
			}
		}
	};
}
