package eu.arrvi.cextr.colortable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author Kris
 *
 */
public class ColorTablePane extends JPanel {
	private JTable table;
	
	public ColorTablePane() {
		super();
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Output"));
		
		this.setPreferredSize(new Dimension(0, 150));
		
		table = new JTable(new ColorTableModel());
		table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		TableColumn preview = table.getColumn("PV");
		preview.setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			protected void setValue(Object value) {
				this.setLayout(new GridBagLayout());
				JLabel label = new JLabel();
				label.setSize(new Dimension(10, 10));
				label.setOpaque(true);
				label.setBackground((Color)value);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(label);
			}
		});
		
		this.add(new JScrollPane(table));
	}
}
