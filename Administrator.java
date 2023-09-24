package GUI;

public class Administrator extends User{
	static int genStaffID = 1;
	private int staffID;
	private HealthcareCentre healthcareCentre;

	/**
	 * Constructor with arguments that instantiate an Administrator 
	 * @param username Unique login username of an Administrator
	 * @param password Password of an Administrator
	 * @param email Email address of an Administrator
	 * @param fullName Full name of an Administrator
	 * @param healthcareCentre HealthcareCentre that an Administrator
	 * works for
	 */
	public Administrator(String username, String password, String email,
		String fullName, HealthcareCentre healthcareCentre) {
		super(username, password, email, fullName);
		setStaffID(genStaffID++);
		setHealthcareCentre(healthcareCentre);
	}

	/**
	 * @return the getStaffID
	 */
	public static int getGenStaffID() {
		return genStaffID;
	}

	/**
	 * @param getStaffID the getStaffID to set
	 */
	public static void setGenStaffID(int genStaffID) {
		Administrator.genStaffID = genStaffID;
	}

	/**
	 * @return the staffID
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
	/**
	 * @return the healthcareCentre
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

	@Override
	/**
	 * Returns a string representation of an Administrator
	 * @return A string representing details of an Administrator
	 */
	public String toString() {
		return "Administrator: " + super.toString() + "\n  Staff ID:" +
			getStaffID() + " works for " + 
			getHealthcareCentre().getCentreName();
	}
}
