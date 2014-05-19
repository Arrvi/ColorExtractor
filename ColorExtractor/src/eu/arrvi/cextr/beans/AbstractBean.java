package eu.arrvi.cextr.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract bean to be extended by data beans in application.
 * It has all delegated methods from PropertyChangeSupport.
 * 
 * @author Kris
 *
 */
public abstract class AbstractBean {
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Add listener for all properties.
	 * 
	 * @param listener new listener for bean
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	/**
	 * Remove listener from all properties.
	 * 
	 * @param listener listener to be removed
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Returns all listeners added to bean
	 * 
	 * @return Array of listeners from bean
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners
	 */
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return pcs.getPropertyChangeListeners();
	}
	
	/**
	 * Adds listener for specific property in bean
	 * 
	 * @param propertyName name of property to be observed
	 * @param listener listener to add
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * Removes listener from property.
	 * 
	 * @param propertyName property name
	 * @param listener listener to remove
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Returns all listeners for property
	 * 
	 * @param propertyName name of property from which listeners will be returned
	 * @return Array of listeners on given property
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(String)
	 */
	public PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		return pcs.getPropertyChangeListeners(propertyName);
	}
	
	
	// Arsenal

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(String propertyName, int oldValue,
			int newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(PropertyChangeEvent event) {
		pcs.firePropertyChange(event);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index,
			Object oldValue, Object newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index,
			int oldValue, int newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index,
			boolean oldValue, boolean newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}
	
}
