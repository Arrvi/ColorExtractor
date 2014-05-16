package eu.arrvi.cextr.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Action: "+event.getActionCommand());
	}
	
}
