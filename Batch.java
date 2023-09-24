package GUI;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Batch implements Serializable {
	private String batchNo;
	private LocalDate expiryDate;
	private int quantityAvailable;
	private int quantityAdministered;
	
	private List<Vaccination> vaccinations;
	private HealthcareCentre healthcareCentre;
	private Vaccine vaccine;
	
	public Batch(String batchNo, LocalDate expiryDate, int quantityAvailable, Vaccine vaccine) {
		setBatchNo(batchNo);
		setExpiryDate(expiryDate);
		setQuantityAvailable(quantityAvailable);
		setVaccinations(new ArrayList<>());
		setVaccine(vaccine);
	}
	
	public void add(Vaccination v) {
		getVaccinations().add(v);
		v.setBatch(this);
	}
	
	public Vaccination find(int vaccinationID) {
		return getVaccinations().stream()
				.filter(v -> v.getVaccinationID() == vaccinationID)
				.findAny()
				.orElse(null);
	}
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the expiryDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the quantityAvailable
	 */
	public int getQuantityAvailable() {
		return quantityAvailable;
	}
	/**
	 * @param quantityAvailable the quantityAvailable to set
	 */
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	/**
	 * @return the quantityAdministered
	 */
	public int getQuantityAdministered() {
		return quantityAdministered;
	}
	/**
	 * @param quantityAdministered the quantityAdministered to set
	 */
	public void setQuantityAdministered(int quantityAdministered) {
		this.quantityAdministered = quantityAdministered;
	}
	/**
	 * @return the vaccinations
	 */
	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}
	/**
	 * @param vaccinations the vaccinations to set
	 */
	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}
	/**
	 * @return the hcCentre
	 */
	public HealthcareCentre getHealthcareCentre() {
		return healthcareCentre;
	}
	/**
	 * @param healthcareCentre the healthcareCentre to set
	 */
	public void setHealthcareCentre(HealthcareCentre healthcareCentre) {
		this.healthcareCentre = healthcareCentre;
	}
	/**
	 * @return the vaccine
	 */
	public Vaccine getVaccine() {
		return vaccine;
	}
	/**
	 * @param vaccine the vaccine to set
	 */
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	
	public String toString() {
		//return getVaccine().getVaccineName() + " " + getBatchNo() + "         " 
		//	+ getExpiryDate() + " " + getQuantityAvailable();
		
		return String.format("Vaccine %s (Batch no: %s) expire on: %s" +
			"\n  quantity available: %d, pending: %d, administered: %d " +
			" = %d (total)\n  Number of vaccinations: %d", 
			getVaccine().getVaccineName(), getBatchNo(), getExpiryDate(),
			getAvailable(), getPending(), getQuantityAdministered(),
			getQuantityAvailable(), numOfVaccinations());
			
	}
	
	public int numOfVaccinations() {
		return getVaccinations().size();
	}
	
	public int getPending() {
		return (int) getVaccinations().stream()
			//.filter(v -> v.getStatus() == Vaccination.Status.CONFIRMED ||
			//	v.getStatus()== Vaccination.Status.PENDING)
			.filter(Vaccination::isPendingStatus)
			.count();
	}
	
	public int getAvailable() {
		return getQuantityAvailable() - getPending() - 
			getQuantityAdministered();
	}
	
	public String allVaccinations() {
		if (numOfVaccinations() == 0)
			return "";
		return getVaccinations().stream()
			.map(Vaccination::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public String allPendingVaccinations() {
		if (numOfVaccinations() == 0)
			return "";
		return getVaccinations().stream()
			.filter(Vaccination::isPendingStatus)
			.map(Vaccination::toString)
			.collect(Collectors.joining("\n"));
	}
	
}
