package GUI;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class HealthcareCentre implements Serializable {
	private String centreName;
	private String address;
	
	private List<Administrator> administrators;
	private List<Batch> batches;
	
	/**
	 * Method to initialised a HealthcareCentre
	 * @param centreName Name of the HealthcareCentre
	 * @param address Address of the HealthcareCentre
	 */
	public HealthcareCentre(String centreName, String address) {
		setCentreName(centreName);
		setAddress(address);
		setAdministrators(new ArrayList<>());
		setBatches(new ArrayList<>());
	}
	
	/**
	 * Adding a new Adminstrator to the centre
	 * @param administrator New administrator 
	 */
	public void add(Administrator administrator) {
		getAdministrators().add(administrator);
		//administrator.setHealthcareCentre(this);
	}
	
	/**
	 * Adding a new vaccine batch to the centre
	 * @param batch New vaccine batch to be added to the centre
	 */
	public void add(Batch batch) {
		getBatches().add(batch);
		batch.setHealthcareCentre(this);
	}
	
	/**
	 * Searching for a specific administrator, based on the staffID
	 * @param staffID The staffID of an administrator
	 * @return The Administrator whose staffID matches the paremteric
	 * staffID, null otherwise
	 */
	public Administrator findAdministrator(int staffID) {
		return getAdministrators().stream()
			.filter(a -> a.getStaffID()== staffID)
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Searching for a specific vaccine batch, based on the batch number
	 * @param batchNo Batch number of a vaccine batch
	 * @return The vaccine Batch whose batchNo matches the paremteric
	 * batchNo, null otherwise
	 */
	public Batch findBatch(String batchNo) {
		return getBatches().stream()
			.filter(b -> b.getBatchNo().equalsIgnoreCase(batchNo))
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Returns a collection of vaccine batches whose vaccine matches
	 * the parametric vaccine
	 * @param vaccine Vaccine that needs to be search for the batches
	 * in the collection of batches
	 * @return a collection of vaccine batches whose vaccine matches
	 * the parametric vaccine
	 */
	public ArrayList<Batch> getBatchesOfVaccine(Vaccine vaccine) {
		ArrayList<Batch> vaccineBatches = new ArrayList<>();
		for (Batch batch: getBatches()) {
			if (batch.getVaccine().equals(vaccine) && 
				batch.getAvailable() != 0 && 
				batch.getExpiryDate().isAfter(LocalDate.now()))
				vaccineBatches.add(batch);
		}
		return vaccineBatches;
	}
	
	/**
	 * Returns a string containing the details of all batches of vaccines
	 * in the centre
	 * @return a string containing the details of all batches of vaccines
	 */
	public String batchesOfVaccines() {
		if (numOfBatches() == 0)
			return "";
		HashMap<Vaccine, ArrayList<Batch>> batches = new HashMap<>();
		for (Batch b: getBatches()) {
			if (b.getPending() != 0) {
				Vaccine vacc = b.getVaccine();
				if (batches.containsKey(vacc))
					batches.get(vacc).add(b);
				else {
					batches.put(vacc, new ArrayList<Batch>());
					batches.get(vacc).add(b);
				}
			}
		}
		String batchesOfVaccines = "";
		for (Vaccine vac: batches.keySet()) {
			batchesOfVaccines += vac.getVaccineName() + "\n";
			ArrayList<Batch> vBatch = batches.get(vac);
			for (Batch b: vBatch) {
				batchesOfVaccines += b.getBatchNo() + " " + 
					b.getPending() + " pending/confirmed appointments.\n";
			}
		}
		return batchesOfVaccines;
	}

	/**
	 * Returns the number of administrators working for the centre
	 * @return the number of administrators working for the centre
	 */
	public int numOfAdministrators() {
		return getAdministrators().size();
	}
	
	/**
	 * Returns the number of vaccine batches available in the centre
	 * @return the number of vaccine batches available in the centre
	 */
	public int numOfBatches() {
		return getBatches().size();
	}
	
	/**
	 * @return the centreName
	 */
	public String getCentreName() {
		return centreName;
	}
	
	/**
	 * @param centreName the centreName to set
	 */
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the administrators
	 */
	public List<Administrator> getAdministrators() {
		return administrators;
	}
	
	/**
	 * @param administrators the administrators to set
	 */
	public void setAdministrators(List<Administrator> administrators) {
		this.administrators = administrators;
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
	
	/**
	 * Returns a string containing the details of a centre
	 * @return a string containing the details of a centre
	 */
	public String toString() {
		return getCentreName() + " at " + getAddress() + "\n" +
			" with " + numOfAdministrators() + 
			" administrator and " + numOfBatches() + " vaccine batches";
	}
}
