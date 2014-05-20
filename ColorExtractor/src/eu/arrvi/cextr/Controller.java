package eu.arrvi.cextr;

import javax.swing.Action;
import javax.swing.JFrame;

import eu.arrvi.cextr.actions.AnalyzeAction;
import eu.arrvi.cextr.actions.CloseImageAction;
import eu.arrvi.cextr.actions.ExitAction;
import eu.arrvi.cextr.actions.LoadImageAction;
import eu.arrvi.cextr.beans.ColorsBean;
import eu.arrvi.cextr.beans.ImageBean;
import eu.arrvi.cextr.beans.ParametersBean;

/**
 * MVC Controller for application. It stores data beans as model. Also it contains public inner classes
 * for specific actions.
 * 
 * @author Kris
 *
 */
public class Controller {
	private MainWindow mainWindow;
	private final ImageBean image = new ImageBean();
	private final ParametersBean parameters = new ParametersBean();
	private final ColorsBean colors = new ColorsBean();
	
	/**
	 * Creates controller for application. Initiates data beans and frame with GUI. <b>It has to be called in EDT.</b>  
	 */
	public Controller() {
		mainWindow = new MainWindow(this);
	}

	/**
	 * Returns main frame of application. May be useful as parent component for dialogs.
	 * 
	 * @return application main frame
	 */
	public JFrame getMainJFrame() {
		return mainWindow;
	}
	
	
	public Action getLoadAction() {
		return loadImageAction;
	}
	
	private final Action loadImageAction = new LoadImageAction(this);
	
	
	public Action getAnalyzeAction() {
		return analyzeAction;
	}
	
	private final Action analyzeAction = new AnalyzeAction(this);
	
	public Action getCloseImageAction() {
		return closeImageAction;
	}
	
	private final Action closeImageAction = new CloseImageAction(this);
	
	public Action getExitAction() {
		return exitAction;
	}
	
	private final Action exitAction = new ExitAction(this);
	
	/**
	 * Returns image data bean for adding listeners and other stuff.
	 * 
	 * @return image data bean
	 */
	public ImageBean getImageBean() {
		return image;
	}

	public ParametersBean getParametersBean() {
		return parameters;
	}

	public ColorsBean getColorsBean() {
		return colors;
	}
}
