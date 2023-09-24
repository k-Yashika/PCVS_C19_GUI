package GUI;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class HealthcareTM implements ComboBoxModel {

	private PCVS pcvs;
	private String[] hctitle = {"Centre Name", "Centre Address"};
	
	public HealthcareTM(PCVS p) {
		pcvs = p;
	}
	
	public void add(HealthcareCentre hc) {
		pcvs.add(hc);
	}
	
	public int getColumnCount() {
		return hctitle.length;
	}
	
	public int getRowCount() {
		return pcvs.numOfHealthcareCentres();
	}

	@Override
	public int getSize() {
		return getRowCount();
	}

	@Override
	public Object getElementAt(int index) {
		HealthcareCentre hc = pcvs.getHealthcareCentres().get(index);		
		return "";
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		return null;
	}
}

