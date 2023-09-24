package GUI;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Subclass of User, with additional attributes: ICPassport, and
 * a list of vaccinations.
 * @author <Name>, <Student ID>
 * @version 1.x, <Date>
 */
public class Patient extends User {
	private String ICPassport;
	private List<Vaccination> vaccinations;
	
	/**
	 * Constructor with arguments that instantiate a Patient
	 * @param username Unique username of a patient
	 * @param password Password of a patient
	 * @param email Email of a patient
	 * @param fullName Full name of a patient
	 * @param icPassport IC or Passport number of a patient
	 */
	public Patient(String username, String password, String email,
		String fullName, String icPassport) {
		super(username, password, email, fullName);
		setICPassport(icPassport);
		setVaccinations(new ArrayList<>());
	}

	/**
	 * Register a new vaccination on patient
	 * @param vaccination The vaccination appointment that a patient
	 * has requested
	 */
	public void add(Vaccination vaccination) {
		getVaccinations().add(vaccination);
	}
	
	/**
	 * Search for a particular vaccination on patient
	 * @param vaccinationID Vaccination ID of a specific vaccination
	 * @return Vaccination on patient matching the parametric 
	 * vaccinationID, null otherwise.
	 */
	public Vaccination findVaccination(int vaccinationID) {
		return getVaccinations().stream()
			.filter(v -> v.getVaccinationID() == vaccinationID)
			.findFirst()
			.orElse(null);
	}

	/**
	 * @return The number of vaccinations being administered on 
	 * the patient
	 */
	public int numOfVaccinations() {
		return getVaccinations().size();
	}
	
	/**
	 * Checking whether the patient has any pending vaccinations, 
	 * i.e. vaccination that is either pending or confirmed.
	 * @return true if there is pending vaccinaton, false otherwise
	 */
	public boolean hasPendingVaccination() {
		return getVaccinations().stream()
			.filter(Vaccination::isPendingStatus)
			.count() != 0;
	}
	
	/**
	 * Returns all vaccinations on patient as a string
	 * @return a string containing all vaccinations on patient
	 */
	public String allVaccinations() {
		if (numOfVaccinations() == 0)
			return "";

		return getVaccinations().stream()
			.map(v -> v.getVaccinationID() + ": " + 
				v.getAppointmentDate() + " " + v.getStatus())
			.collect(Collectors.joining("\n"));
	}
	
	/**
	 * Getting the latest vaccination on patient
	 * @return the latest vaccination on patient
	 */
	public Vaccination getLastVaccination() {
		return getVaccinations().get(numOfVaccinations()-1);
	}
	
	/**
	 * @return the iCPassport
	 */
	public String getICPassport() {
		return ICPassport;
	}

	/**
	 * @param iCPassport the iCPassport to set
	 */
	public void setICPassport(String iCPassport) {
		ICPassport = iCPassport;
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

	@Override
	/**
	 * @return A string containing the details of a patient.
	 */
	public String toString() {
		return "Patient: " + super.toString() + "\n  IC/Passport: " +
			getICPassport();
	}
}
