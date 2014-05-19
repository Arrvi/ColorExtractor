package eu.arrvi.cextr.beans;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ColorsBean implements Serializable {
	
	private PropertyChangeSupport pcs;
	
	public ColorsBean() {
		pcs = new PropertyChangeSupport(this);
	}
}
