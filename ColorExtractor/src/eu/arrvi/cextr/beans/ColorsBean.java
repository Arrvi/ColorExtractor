package eu.arrvi.cextr.beans;

import java.io.Serializable;
import java.util.ArrayList;

import eu.arrvi.cextr.common.ColorPatch;

public class ColorsBean extends AbstractBean implements Serializable {
	private ArrayList<ColorPatch> patches;
	
	public ColorsBean() {
	}

	
	/**
	 * @return the patches
	 */
	public ArrayList<ColorPatch> getPatches() {
		return patches;
	}
	public ColorPatch getPatches(int index) {
		return patches.get(index);
	}

	/**
	 * @param patches the patches to set
	 */
	public void setPatches(ArrayList<ColorPatch> patches) {
		this.patches = patches;
		
		firePropertyChange("patches", null, patches);
	}
	public void setPatches(int index, ColorPatch patch) {
		try {
			patches.set(index, patch);
		} catch(IndexOutOfBoundsException e) {
			patches.add(index, patch);
		}
		fireIndexedPropertyChange("patches", index, null, patch);
	}
	
	
}
