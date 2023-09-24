package GUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vaccine implements Serializable{
	static int genVaccineID = 1;
	private int vaccineID;
	private String manufacturer;
	private String vaccineName;
	
	private List<Batch> batches;

	/**
	 * Constructor for Vaccine
	 * @param manufacturer Vaccine manufacturer
	 * @param vaccineName Vaccine name
	 */
	public Vaccine(String manufacturer, String vaccineName) {
		setVaccineID(genVaccineID++);
		setManufacturer(manufacturer);
		setVaccineName(vaccineName);
		setBatches(new ArrayList<>());
	}
	
	/**
	 * Adding a new vaccine Batch to the vaccine.
	 * @param batch The vaccine Batch to be added to the vaccine
	 */
	public void add(Batch batch) {
		getBatches().add(batch);
	}
	
	/**
	 * Finding a specific vaccine Batch based on a given batchNo
	 * @param batchNo The batch number of a vaccine batch
	 * @return The vaccine batch whose number matches the parametric
	 * batchNo
	 */
	public Batch findBatch(String batchNo) {
		return getBatches().stream()
			.filter(b -> b.getBatchNo().equalsIgnoreCase(batchNo))
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Returns the number of vaccine batch in the vaccine
	 * @return the number of vaccine batch in the vaccine
	 */
	public int numOfBatches() {
		return getBatches().size();
	}
	
	/**
	 * @return the genVaccineID
	 */
	public static int getGenVaccineID() {
		return genVaccineID;
	}

	/**
	 * @param genVaccineID the genVaccineID to set
	 */
	public static void setGenVaccineID(int genVaccineID) {
		Vaccine.genVaccineID = genVaccineID;
	}

	/**
	 * @return the vaccineID
	 */
	public int getVaccineID() {
		return vaccineID;
	}

	/**
	 * @param vaccineID the vaccineID to set
	 */
	public void setVaccineID(int vaccineID) {
		this.vaccineID = vaccineID;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the vaccineName
	 */
	public String getVaccineName() {
		return vaccineName;
	}

	/**
	 * @param vaccineName the vaccineName to set
	 */
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	/**
	 * @return the batches
	 */
	public List<Batch> getBatches() {
		return batches;
	}

	/**
	 * @param batches the batches to set
	 */
	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	
	@Override
	/**
	 * Checking for equality of two Vaccine objects.
	 * Two Vaccine objects are deemed to be equal if their 
	 * vaccineID are the same
	 * @return true if both Vaccine objects have the same vaccineID,
	 * false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Vaccine)
			return getVaccineID() == ((Vaccine) obj).getVaccineID();
		return false;
	}
	
	@Override
	/**
	 * @return The string containing the detail of a vaccine, 
	 * including the vaccineID, vaccine name, and the manufacturer
	 */
	public String toString() {
		return getVaccineID() + ": " + getVaccineName() + " by " +
			getManufacturer();
	}
}
