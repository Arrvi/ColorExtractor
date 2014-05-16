package eu.arrvi.cextr.colortable;

import eu.arrvi.cextr.colortable.Color;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.table.AbstractTableModel;

public class ColorTableModel extends AbstractTableModel {
	private final static String[] columnNames = {
		"PV",
		"RGB",
		"HSV",
		"HTML"
	};
	
	// TODO clear testing data
	private ArrayList<Color> data;
	
	public ColorTableModel() {
		super();
		
		data = new ArrayList<Color>();
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++) {
			data.add(new Color(rand.nextInt()));
		}
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
		
		Color color = data.get(row);
		
		switch (col) {
			case 1: return color.toRGBString();
			case 2: return color.toHSVString();
			case 3: return color.toHTMLString();
				
			default: return color;
		}
	}

}
