package GUI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The main domain class PCVS, containing the collection of User, 
 * Vaccine, and HealthcareCentre
 * @author <Name>, <Student ID>
 * @version 1.x, <Date>
 */
public class PCVS implements Serializable{
	private List<User> users;
	private List<HealthcareCentre> healthcareCentres;
	private List<Vaccine> vaccines;
	
	/**
	 * No arguments constructor that initialises the collection of
	 * Users, Vaccine, and HealthcareCentre
	 */
	public PCVS() {
		setUsers(new ArrayList<>());
		setHealthcareCentres(new ArrayList<>());
		setVaccines(new ArrayList<>());
	}
	
	/**
	 * Adding a User (either Patient or Administrator) to the system
	 * @param user Either Patient or Administrator to be added
	 */
	public void add(User user) {
		getUsers().add(user);
	}
	
	/**
	 * Adding a new HealthcareCentre to the system
	 * @param healthcareCentre Centre to be added
	 */
	public void add(HealthcareCentre healthcareCentre) {
		getHealthcareCentres().add(healthcareCentre);
	}
	
	/**
	 * Adding a new Vaccine to the system
	 * @param vaccine New vaccine to be added
	 */
	public void add(Vaccine vaccine) {
		getVaccines().add(vaccine);
	}
	
	/**
	 * Search for a user based on the unique username
	 * @param username The username of a specific User
	 * @return User whose username matches the parametric username,
	 * null otherwise
	 */
	public User findUser(String username) {
		return getUsers().stream()
			.filter(u -> u.getUsername().equalsIgnoreCase(username))
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Search for a HealthcareCentre based on the centre name
	 * @param centreName Centre name of a specifc HealthcareCentre
	 * @return The HealthcareCenter with name matches the parametric
	 * centreName, null otherwise
	 */
	public HealthcareCentre findHealthcareCentre(String centreName) {
		return getHealthcareCentres().stream()
			.filter(hc -> hc.getCentreName().equalsIgnoreCase(centreName))
			.findAny()
			.orElse(null);
	}
	
	/**
	 * Search for a Vaccine based on the vaccineID
	 * @param vaccineID VaccinID of a specific Vaccine
	 * @return The Vaccine whose vaccineID matches the parametric
	 * vaccineID, null otherwise
	 */
	public Vaccine findVaccine(int vaccineID) {
		return getVaccines().stream()
			.filter(v -> v.getVaccineID() == vaccineID)
			.findAny()
			.orElse(null);
	}

	/**
	 * Search for a Vaccine based on the name of the vaccine
	 * @param vaccineName Name of a specific Vaccine
	 * @return The Vaccine whose vaccine name matches the parametric
	 * vaccine name, null otherwise
	 */
	public Vaccine findVaccine(String vaccineName) {
		return getVaccines().stream()
			.filter(v -> v.getVaccineName().equalsIgnoreCase(vaccineName))
			.findAny()
			.orElse(null);
	}

	/**
	 * Returns a collection of different centres that offer a specific
	 * vaccine
	 * @param vaccine Vaccine that is being offered by a specific centre
	 * @return A collection of unique HealthcareCentre that offer the
	 * parametric vaccine
	 */
	public HashSet<HealthcareCentre> findCentreWithVaccine(Vaccine vaccine) {
		HashSet<HealthcareCentre> centres = new HashSet<>();
		for (HealthcareCentre hc: getHealthcareCentres()) {
			for (Batch batch: hc.getBatches())
				if (batch.getVaccine().equals(vaccine))
					centres.add(hc);
		}
		return centres;
	}
	
	/**
	 * Returns the number of Users in the system
	 * @return the number of Users in the system
	 */
	public int numOfUsers() {
		return getUsers().size();
	}
	
	/**
	 * Returns the number of HealthcareCentre in the system
	 * @return the number of HealthcareCentre in the system
	 */
	public int numOfHealthcareCentres() {
		return getHealthcareCentres().size();
	}

	/**
	 * Returns a string consisting of all vaccination appointments
	 * @return a string consisting of all vaccination appointments
	 */
	public String allVaccinationAppointments() {
		String allAppointments = "";
		for (HealthcareCentre hc: getHealthcareCentres())
			for (Batch b: hc.getBatches()) {
					for (Vaccination v: b.getVaccinations())
						allAppointments += v.toString() + "\n";
			}
		return allAppointments;	
	}
	
	/**
	 * Returns a list containing all users, either in original
	 * order or sorted by full name.
	 * @param criteria Criteria for ordering the users
	 * @return list containing all users, either in original 
	 * order or sorted by full name.
	 */	
	public String allUsers(String criteria) {
		if (criteria.equalsIgnoreCase("Fullname")) {
			return getUsers().stream()
				.sorted()
				.map(User::toString)
				.collect(Collectors.joining("\n"));				
		}
		else
			return getUsers().stream()
				.map(User::toString)
				.collect(Collectors.joining("\n"));
	}
	
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	/**
	 * @return the healthcareCentres
	 */
	public List<HealthcareCentre> getHealthcareCentres() {
		return healthcareCentres;
	}
	
	/**
	 * @param healthcareCentres the healthcareCentres to set
	 */
	public void setHealthcareCentres(List<HealthcareCentre> 
		healthcareCentres) {
		this.healthcareCentres = healthcareCentres;
	}
	
	/**
	 * @return the vaccines
	 */
	public List<Vaccine> getVaccines() {
		return vaccines;
	}
	
	/**
	 * @param vaccines the vaccines to set
	 */
	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
}