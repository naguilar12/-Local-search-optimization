package graficadora;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableDialog extends JDialog{
	
	JTable table;
	

	public TableDialog(ArrayList<String[]> info) {
		String[] columnNames = {"Iteracion", "Punto", "X", "Y", "¿Factible?", "FO", "Delta FO"};
		Object[][] data = new Object[info.size()][columnNames.length];
		
		for (int i = 0; i < info.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				data[i][j] = info.get(i)[j];
			}
		}
		
		setSize(500, 500);
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll);
	};
}
