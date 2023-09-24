package GUI;

import javax.swing.table.AbstractTableModel;

public class BatchTM extends AbstractTableModel {
	
	private Vaccine vaccines;
	private String[] title = {"Batch No", "Expiry Date", "Quantity Administered", 
			"Quantity Available"};
	
	public BatchTM(Vaccine v) {
		vaccines = v;
	}
	
	public void add(Batch b) {
		vaccines.add(b);
	}
	
	public int getColumnCount() {
		return title.length;
	}
	
	public int getRowCount() {
		return vaccines.numOfBatches();
	}
	
	public Object getValueAt(int row, int column) {
		Batch b = vaccines.getBatches().get(row);
		return "";
	}
		
	public Batch get(int i) {
		return vaccines.getBatches().get(i);
	}

	public void setVaccines(Vaccine v) {
		vaccines = v;
	}
		
	public String getColumnName(int column) {
		return title[column];
	}
}
