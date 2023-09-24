package GUI;

import java.io.Serializable;
import java.time.LocalDate;

public class Vaccination implements Serializable {

	public static enum Status {
		PENDING, CONFIRMED, REJECTED, ADMINISTERED
	};
	
	static int genVaccinationID = 1;
	private int vaccinationID;
	private LocalDate appointmentDate;
	private Status status;
	private String remarks;
	
	private Patient patient;
	private Batch batch;
	
	/**
	 * Constructor to initialise a Vaccination object.
	 * @param appointmentDate Date of the vaccincation appointment
	 * @param patient Patient who requested the vaccination appointment
	 * @param batch The vaccine batch requested by patient
	 */
	public Vaccination(LocalDate appointmentDate, 
		Patient patient, Batch batch) {
		setVaccinationID(genVaccinationID++);
		setAppointmentDate(appointmentDate);
		setStatus(Status.PENDING); 
		setPatient(patient);
		setBatch(batch);
	}
	
	/**
	 * @return the getVaccinatioinID
	 */
	public static int getGenVaccinationID() {
		return genVaccinationID;
	}
	
	/**
	 * @param getVaccinatioinID the getVaccinatioinID to set
	 */
	public static void setGetVaccinatioinID(int genVaccinationID) {
		Vaccination.genVaccinationID = genVaccinationID;
	}
	
	/**
	 * @return the vaccinationID
	 */
	public int getVaccinationID() {
		return vaccinationID;
	}
	
	/**
	 * @param vaccinationID the vaccinationID to set
	 */
	public void setVaccinationID(int vaccinationID) {
		this.vaccinationID = vaccinationID;
	}
	
	/**
	 * @return the appointmentDate
	 */
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	
	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	/**
	 * @return the batch
	 */
	public Batch getBatch() {
		return batch;
	}
	
	/**
	 * @param batch the batch to set
	 */
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	
	@Override
	/**
	 * Checking for equality of two Vaccination objects.
	 * Two users are deemed to be equal if their vaccinationID
	 * are the same
	 * @param obj Vaccination object to check for equality
	 * #return true if both vaccinations are the same, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Vaccination) {
			return getVaccinationID() == 
				((Vaccination) obj).getVaccinationID();
		}
		return false;
	}
	
	@Override
	/**
	 * Returns a string representation of a vaccination
	 * @return A string representing details of a vaccination
	 */	
	public String toString() {
		return String.format("Vaccination (ID: %d) for %s " + 
			"on %s at %s\nStatus: %s %s", getVaccinationID(), 
			getPatient().getFullName(), getAppointmentDate(), 
			getBatch().getHealthcareCentre().getCentreName(),
			getStatus(), getRemarks()==null? "": "Remarks: " + 
			getRemarks());
	}
	
	/**
	 * Method to check whether a vaccination appointment is of
	 * pending status, that is whether it is PENDING or CONFIRMED
	 * @return true if it is of pending status, false otherwise
	 */
	public boolean isPendingStatus() {
		return getStatus() == Status.PENDING ||
			getStatus() == Status.CONFIRMED;
	}
}
