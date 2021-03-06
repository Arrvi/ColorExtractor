package eu.arrvi.cextr.colortable;

import eu.arrvi.cextr.common.Color;
import eu.arrvi.cextr.common.ColorPatch;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ColorTableModel extends AbstractTableModel {
	private final static String[] columnNames = {
		"PV",
		"#",
		"RGB",
		"HSV",
		"HTML",
		"Temp"
	};
	
	// TODO clear testing data
	private ArrayList<ColorPatch> data;
	
	public ColorTableModel() {
		super();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		if (data == null)
			return 0;
		else
			return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if ( data == null )
			return null;
		
		ColorPatch patch = data.get(row);
		Color color = patch.getColor();
		
		switch (col) {
			case 1: return patch.getSize();
			case 2: return color.toRGBString();
			case 3: return color.toHSVString();
			case 4: return color.toHTMLString();
			case 5: return color.getTemperature();
			case 6: return patch.getScore();
				
			default: return patch;
		}
	}

	public void setPatches(ArrayList<ColorPatch> patches) {
		data = patches;
		fireTableDataChanged();
	}

}
