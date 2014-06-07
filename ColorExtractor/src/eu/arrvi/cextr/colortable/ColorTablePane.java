package eu.arrvi.cextr.colortable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.common.Color;
import eu.arrvi.cextr.common.ColorPatch;

/**
 * @author Kris
 *
 */
public class ColorTablePane extends JPanel {
	private final Controller controller;
	
	private JTable table;

	private ColorTableModel model = new ColorTableModel();
	
	public ColorTablePane(Controller contr) {
		super();
		controller = contr;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Output"));
		
		this.setPreferredSize(new Dimension(0, 150));
		
		table = new JTable(model);
		table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		
		TableColumn preview = table.getColumn("PV");
		preview.setCellRenderer(new ColorSampleRenderer());
		preview.setPreferredWidth(1);

		table.getColumnModel().getColumn(1).setPreferredWidth(1);
		
		controller.getColorsBean().addPropertyChangeListener("patches", new ColorBeanListener());
		
		this.add(new JScrollPane(table));
	}
	
	private class ColorBeanListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			model.setPatches((ArrayList<ColorPatch>)evt.getNewValue());
		}
		
	}
	
	private class ColorSampleRenderer extends JPanel implements TableCellRenderer {
	    public ColorSampleRenderer() {
			setLayout(new GridBagLayout());
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

//			JLabel label = new JLabel();
//			label.setOpaque(true);
//			label.setPreferredSize(new Dimension(8, 8));
//			removeAll();
//			add(label);
//			
//			Color newColor = (Color)value;
//	        label.setBackground(newColor);
//		        
//            if (isSelected) {
//                setBackground(table.getSelectionBackground());
//            } else {
//                setBackground(table.getBackground());
//            }
			
			ColorPatch patch = (ColorPatch)value;
			
			setBackground(patch.getColor());
            
			return this;
		}
		
	}
}
