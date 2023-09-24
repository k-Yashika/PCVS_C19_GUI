package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/*
 * A class to hold all appointment details in table
 */
public class AppointmentsTM extends AbstractTableModel{

	private Patient patient;
	private List<Vaccination> vaccinations;
	private String[] title = {"No", "Vaccination ID", "Appointment Date", "Status", "Remarks"};
	
	public AppointmentsTM(List<Vaccination> vaccinations) {
		setVaccinations(vaccinations);
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}
	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}
	
	public AppointmentsTM(Patient p) {
		patient = p;
	}
	
	public void add(Vaccination v) {
		patient.add(v);
	}
	
	public int getColumnCount() {
		return title.length;
	}
	
	public int getRowCount() {
		return patient.numOfVaccinations();
	}
	
	public Object getValueAt(int row, int column) {
		Vaccination v = patient.getVaccinations().get(row);
		switch(column) {
		case 0:
			return row + 1;
		case 1: 
			return v.getVaccinationID();
		case 2: 
			return v.getAppointmentDate();
		case 3:
			return v.getStatus();
		case 4:
			return v.getRemarks();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return title[column];
	}
	
	
	
	
}
