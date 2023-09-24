package GUI;
import java.awt.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class PCVSConsole {
	
		static Scanner kbd;
		static PCVS pcvs;
		static User loginUser;
		static DateTimeFormatter formatter;
		
		static final int MAX_PASSWORD_TRIES = 3;
		
		/**
		 * Application starts here
		 * @param args
		 */
		public static void main(String[] args) {
			formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			kbd = new Scanner(System.in);
			pcvs = new PCVS();
			initialised();

			boolean done = false;
	        do {
	            String choice = mainMenu();
	            switch (choice) {
		            case "0":
		            	System.out.println("Done for the day...");
		            	done = true;
		            	break;
		            case "1":
		            	signUp();
		            	break;
		            case "2":
		                login();
		                break;
		            case "3":
		                listUsers();
		                break;
		            case "4":
		            	displayAllAppointments();
		            	break;
		            default:
		                System.out.println("Invalid choice");       
	            }
	            System.out.println();
	        } while (!done);
		}

		/**
		 * Display the main menu, and get the user's response
		 * @return The menu option chosen by user
		 */
		public static String mainMenu() {
			System.out.println("Private Covid Vaccine System");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
			System.out.println("1. Sign Up");
			System.out.println("2. Log In");
			System.out.println("3. Display all users");
			System.out.println("4. Display all appointments");
			System.out.println();       	
			System.out.println("0. Quit");
		    System.out.print("Your choice? ");
		    String choice = kbd.nextLine();
		    return choice;
		}
		
	    /**
	     * Register either Patient or Administrator
	     */
		public static void signUp() {
			System.out.println("Register as NEW PCVS user...");
			String userType = "";
			do {
				userType = getText("Sign up as <A>dministrator, " + 
					"or <P>atient: ");
				if (!"PApa".contains(userType))
					System.out.println("Please enter either 'A' or 'P' only!");
			} while (!"PApa".contains(userType));
	 
			signUp(userType.equalsIgnoreCase("A")? "Administrator": "Patient");		
		}
		
	    /**
	     * Helper method that create either Patient or Administrator
	     * @param userType the type of user that is being created.
	     */
	    public static void signUp(String userType) {
	        System.out.println("Create " + userType + " ..."); 
	        if (userType.equalsIgnoreCase("Patient")) {
	            String username = getUsername();
	            String password = getText("Password: "); 
	            String email = getText("Email: ");
	            String fullName = getText("Full name:");
	            String icPassport = getText("IC/Passport: ");
	            pcvs.add(new Patient(username, password, email, fullName, 
	            	icPassport));
	        }
	        else {
	        	System.out.println("List of Healthcare Centres:");
	        	pcvs.getHealthcareCentres().stream()
	        		.forEach(System.out::println);
	        	System.out.print("Select centre name: ");
	        	String centreName = kbd.nextLine();
	        	HealthcareCentre centre = pcvs.findHealthcareCentre(centreName);
	        	if (centre == null) {
	        		System.out.println("Wrong centre name!");
	        		return ;
	        	}
	        	else {
	                String username = getUsername();
	                String password = getText("Password: "); 
	                String email = getText("Email: ");
	                String fullName = getText("Full name:");
	                Administrator administrator = new Administrator(username, 
	                	password, email, fullName, centre);
	                centre.add(administrator);
	                pcvs.add(administrator);
	        	}
	        }
	        System.out.println("New user signed up successfully!");
	    }
	    
	    /**
	     * Method that initiate user login
	     * Once login, a different menu options will be displayed, 
	     * depends on the type of login user.
	     */
	    public static void login() {   		
	        System.out.println("\nLogin");
	        String username = getText("Username: ");
	        loginUser = pcvs.findUser(username);

	        if (loginUser != null) {
		        String inPassword = getText("Password: ");
		        
		        int maxTries = 1;
		        while (!loginUser.getPassword().equals(inPassword)) {
		            System.out.println("Invalid password!");
		            if (maxTries++ == MAX_PASSWORD_TRIES) {
		            	System.out.println("Please try again later!");
		            	return ;
		            }
		            System.out.println();
		            inPassword = getText("Password: ");
		        }
		        System.out.println("Sign in successfully!");
		        System.out.println(loginUser);
		        System.out.println();
		        
		        if (loginUser instanceof Administrator)
		            administratorTasks();
		        else
		        	patientTasks();
	        }
	        else
	        	System.out.println("Username '" + username + 
	        		"' does not exist!");
	    }
	    
		/**
		 * List all users, either in original order or 
		 * sorted according to full name
		 */
		public static void listUsers() {
			if (pcvs.numOfUsers() == 0)
				System.out.println("Please add users first");
			else {
				String criteria = "";
				do {		
					criteria = getText("<O>riginal order " + 
						"or sorted according to <F>ullname? ");
					if (!"OFof".contains(criteria))
						System.out.println("Please enter " + 
							"either 'O' or 'F' only!");
				} while (!"OFof".contains(criteria));			
				
				String allUsers = pcvs.allUsers(criteria.equalsIgnoreCase("F")? 
					"fullname": "original");
				System.out.println("All users:");
				System.out.println(allUsers);
				System.out.println();
			}
		}

		/**
		 * Perform administrator task
		 */
		public static void administratorTasks() {
			Administrator admin = (Administrator) loginUser;
			boolean done = false;
	        do {
	            String choice = adminMenu();
	            switch (choice) {
		            case "0":
		            	System.out.println("Logout...");
		            	done = true;
		            	break;
		            case "1":
		            	recordNewBatches(admin);
		            	break;
		            case "2":
		                viewVaccineBatchInfo(admin);
		                break;
		            default:
		                System.out.println("Invalid choice");       
	            }
	            System.out.println();
	        } while (!done);		
		}

		/**
		 * Menu option for administrator
		 * @return Menu option chosen by admin
		 */
		public static String adminMenu() {
			System.out.println("Administrator: " + loginUser.getFullName());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
			System.out.println("1. Record New Vaccine Batch");
			System.out.println("2. View Vaccine Batch Information");
			System.out.println();       	
			System.out.println("0. Logout");
		    System.out.print("Your choice? ");
		    String choice = kbd.nextLine();
		    return choice;		
		}
		
		/**
		 * Administrator to record new vaccine batches
		 * @param admin
		 */
		public static void recordNewBatches(Administrator admin) {
			HealthcareCentre centre = admin.getHealthcareCentre();
			System.out.println(centre.getCentreName());
			System.out.println();
			pcvs.getVaccines().stream()
				.forEach(System.out::println);
			System.out.print("Enter Vaccine ID: ");
			int vaccineID = kbd.nextInt();
			kbd.nextLine();
			Vaccine vaccine = pcvs.findVaccine(vaccineID);
			if (vaccine == null) {
				System.out.println("Invalid vaccine ID!");
				return ;
			}
			else {
				System.out.println("New batch for " + 
					vaccine.getVaccineName() + " by " + 
					vaccine.getManufacturer());
				String batchNo = getText("Vaccine batch number: ");
				String dateStr = getText("Vaccine expiry date (dd/mm/yyyy): ");
				LocalDate expiryDate = LocalDate.parse(dateStr, formatter);
				int quantity = getNumericValue("Quantity: ");
				kbd.nextLine();
				Batch newBatch = new Batch(batchNo, expiryDate, quantity, vaccine);
				centre.add(newBatch);
				vaccine.add(newBatch);
				
				System.out.println("New vaccine batch added successfully.");
			}
		}

		/**
		 * Method to view vaccination batch by an admin.
		 * From this method, admin may choose to
		 * - confirmVaccinationAppointment 
		 * - recordVaccinationAdministered
		 * @param admin
		 */
		public static void viewVaccineBatchInfo(Administrator admin) {
			HealthcareCentre hcCentre = admin.getHealthcareCentre();
			System.out.println(hcCentre.getCentreName());
			String vaccineBatchesInfo = hcCentre.batchesOfVaccines();
			if (vaccineBatchesInfo.isEmpty())
				System.out.println("No vaccine batches yet...");
			else {
				System.out.println(vaccineBatchesInfo);
				String batchNo = getText("Enter batch number to view: ");
				Batch selectedBatch = hcCentre.findBatch(batchNo);
				if (selectedBatch == null) {
					System.out.println("Invalid batch number!");
					return ;
				}
				System.out.println(selectedBatch);
				System.out.println("Pending vaccinations:");
				System.out.println(selectedBatch.allPendingVaccinations());
				
				String option = getText("<P>rocess vaccinations or " +
					"<V>iew different batch (P/V)? ");
				if (option.equalsIgnoreCase("V")) {
					return ;
				}
				System.out.print("Enter vaccination ID to process: ");
				int vaccinationID = kbd.nextInt();
				kbd.nextLine();
				Vaccination vaccination = selectedBatch.find(vaccinationID);
				if (vaccination == null) {
					System.out.println("Invalid vaccination id");
					return ;
				}
				System.out.println("Vaccination detail:");
				System.out.println(vaccination.getBatch().getVaccine().
					getVaccineName() + " " + 
					vaccination.getBatch().getBatchNo() + " " +
					vaccination.getPatient().getFullName());
				System.out.println();
				if (vaccination.getStatus() == Vaccination.Status.PENDING) {
					String response = getText("Proceed to confirm/reject this " + 
						"vaccination (Y/N)? ");
					if (response.equalsIgnoreCase("Y"))
						confirmVaccinationAppointment(admin, vaccination);
				}
				else if (vaccination.getStatus() == Vaccination.Status.CONFIRMED) {
					String response = getText("Proceed to administer this vaccination (Y/N)? ");
					if (response.equalsIgnoreCase("Y"))
						recordVaccinationAdministered(admin, vaccination);
				}
			}
		}

		/**
		 * Helper method for confirm or reject a vaccination by administrator
		 * @param admin
		 * @param vaccination
		 */
		public static void confirmVaccinationAppointment(Administrator admin, 
			Vaccination vaccination) {
			System.out.println("Confirm/Reject vaccination...");
			Patient pat = vaccination.getPatient();
			Batch selectedBatch = vaccination.getBatch();
			Vaccine vaccine = selectedBatch.getVaccine();
			System.out.println(pat.getFullName() + " " + pat.getICPassport());
			System.out.println(selectedBatch.getBatchNo() + " " +
				selectedBatch.getExpiryDate());
			System.out.println(vaccine);
			String response = getText("<C>onfirm or <R>eject? ");
			if (response.equalsIgnoreCase("C")) {
				vaccination.setStatus(Vaccination.Status.CONFIRMED);
				
			} else {
				String remarks = getText("Remark for rejection: ");
				vaccination.setRemarks(remarks);
				vaccination.setStatus(Vaccination.Status.REJECTED);
			}
			System.out.println("Email has been sent to " + pat.getEmail());
		}

		/**
		 * Administrator record a vaccination as being administered
		 * @param admin
		 * @param vaccination
		 */
		public static void recordVaccinationAdministered(Administrator admin, 
			Vaccination vaccination) {
			System.out.println("Record Vaccination as Administered");
			Patient pat = vaccination.getPatient();
			Batch selectedBatch = vaccination.getBatch();
			Vaccine vaccine = selectedBatch.getVaccine();
			System.out.println(pat.getFullName() + " " + pat.getICPassport());
			System.out.println(selectedBatch.getBatchNo() + " " +
				selectedBatch.getExpiryDate());
			System.out.println(vaccine);
			String remarks = getText("Remark: ");
			vaccination.setRemarks(remarks);
			vaccination.setStatus(Vaccination.Status.ADMINISTERED);
			selectedBatch.setQuantityAdministered(
				selectedBatch.getQuantityAdministered()+1);
			System.out.println("Vaccination administered....");
		}
		
		/**
		 * Performing patinet's task
		 */
		public static void patientTasks() {
			Patient patient = (Patient) loginUser;
			boolean done = false;
	        do {
	            String choice = patientMenu();
	            switch (choice) {
		            case "0":
		            	System.out.println("Logout...");
		            	done = true;
		            	break;
		            case "1":
		            	requestVaccinationAppointment(patient);
		            	break;
		            case "2":
		                viewVaccinationStatus(patient);
		                break;
		            default:
		                System.out.println("Invalid choice");       
	            }
	            System.out.println();
	        } while (!done);		
		}

		/**
		 * Menu display patient's option
		 * @return Option chosen by patient
		 */
		public static String patientMenu() {
			System.out.println("Patient: " + loginUser.getFullName());
			System.out.println("~~~~~~~~~~~~~~~~~~~~"); 
			System.out.println("1. Request Vaccination Appointment");
			System.out.println("2. View Vaccination Appointment Status");
			System.out.println();       	
			System.out.println("0. Logout");
		    System.out.print("Your choice? ");
		    String choice = kbd.nextLine();
		    return choice;		
		}

		/**
		 * Patient requesting for vaccination appointment
		 * @param patient
		 */
		public static void requestVaccinationAppointment(Patient patient) {
			if (patient.hasPendingVaccination()) {
				System.out.println("You have a pending vaccination. " + 
					"Cannot request anymore!");
				return ;
			}
			System.out.println("Available vaccines:");
			pcvs.getVaccines().stream()
				.forEach(System.out::println);
			System.out.println();
			String vaccineName = getText("Type in vaccine name: ");
			Vaccine vaccine = pcvs.findVaccine(vaccineName);

			if (vaccine == null) {
				System.out.println("Invalid vaccine name!");
				return ;
			}
			else {
				HashSet<HealthcareCentre> centres = 
					pcvs.findCentreWithVaccine(vaccine);
				if (centres.isEmpty()) {
					System.out.println("No centres with such vaccine");
					return ;
				}
				else {
					centres.stream().forEach(System.out::println);
					String centreName = getText("Enter centre name: ");
					HealthcareCentre centre = 
						pcvs.findHealthcareCentre(centreName);
					if (centre == null) {
						System.out.println("Invalid centre name!");
						return ;
					}
					ArrayList<Batch> batches = 
						centre.getBatchesOfVaccine(vaccine);
					if (batches.isEmpty()) {
						System.out.println("No vaccine batches " + 
							"available any more.");
						return ;
					}
					batches.forEach(
							(Batch b) -> System.out.println(b.getBatchNo() + 
								" with " + b.getAvailable() + " available"));
					String batchNo = getText("Enter batch no: ");
					Batch selectedBatch = centre.findBatch(batchNo);
					if (selectedBatch == null) {
						System.out.println("Invalid batch number!");
						return ;
					}
					System.out.println("Batch expires on " + 
						selectedBatch.getExpiryDate() + 
						" with " + selectedBatch.getAvailable() + " available");
					LocalDate date = null;
					boolean validDate = false;
					do {
						String dateStr = getText("Enter vaccination appoinment" + 
							" date (dd/mm/yyy): ");
						date = LocalDate.parse(dateStr, formatter);
						if (date.isAfter(selectedBatch.getExpiryDate()))
							System.out.println("Date cannot be " + 
								"after the expiry date!");
						else
							validDate = true;
					} while (!validDate);
					Vaccination vaccination = new Vaccination(date, 
						patient, selectedBatch);
					selectedBatch.add(vaccination);
					patient.add(vaccination);

					System.out.println("Vaccination appointment fixed.");
				}
			}
		}
		
		/**
		 * Display vaccination status by a patient
		 * @param patient
		 */
		public static void viewVaccinationStatus(Patient patient) {
			if (patient.numOfVaccinations() != 0) {
				System.out.println("\n===================");
				System.out.println(patient.allVaccinations());
				System.out.println("\n===================\n");		
				System.out.println("Status of latest vaccination:");
				Vaccination latestVac = patient.getLastVaccination();
				System.out.println(latestVac);
			}
			else
				System.out.println("You do not have any pending " + 
					"vaccination appointment yet.");		
		}
		
		/**
		 * Displaying all vaccination appointments
		 */
		public static void displayAllAppointments() {
			String allVAP = pcvs.allVaccinationAppointments();
			if (allVAP.isEmpty())
				System.out.println("No pending appointments");
			else {
				System.out.println("All vaccination appointments:");
				System.out.println(pcvs.allVaccinationAppointments());
			}
		}
		
		/**
		 * Printing invalid choice.
		 */
		public static void error() {
			System.out.println("Invalid choice! Try again...");
		}
		
	    // The following methods are used in SignUp for getting user input, 
	    // and also performing validation checks
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    /**
	     * Get a valid username
	     * @return String username
	     */
	    public static String getUsername() {
	    	String username = getText("Username: ");
	    	while (pcvs.findUser(username) != null) {
	            System.out.println("Username already exists. "
	                    + "Please enter another username!\n");
	            username = getText("Username: ");
	    	}
	    	return username;
	    }
	    
	    /**
	     * Getting text input from user, with validation for empty string. 
	     * Value of input depends on the prompt.
	     * @param prompt The prompt showing what information is
	     * being read.
	     * @return text The textual value entered by user.
	     */
	    public static String getText(String prompt) {
	        System.out.print(prompt);
	        String text = kbd.nextLine().trim();
	        while (text.equals("")) {
	            System.out.println("Entry cannot be blank! "
	                    + "Please enter again!\n");
	            System.out.print(prompt);
	            text = kbd.nextLine();
	        }
	        return text;
	    }
	    
	    /**
	     * Method to get a positive integer
	     * @param prompt Text that telling user what to enter
	     * @return a positive integer
	     */
	    public static int getNumericValue(String prompt) {
	    	System.out.print(prompt);
	    	int number = kbd.nextInt();
	    	while (number <= 0) {
	            System.out.println("Value must be positive! "
	                    + "Please enter again!\n");
	            System.out.print(prompt);
	            number = kbd.nextInt();
	    	}
	    	return number;
	    }
	    
	    /**
	     * Initialise pcvs with some required objects.
	     */
	    public static void initialised() {
	    	pcvs.add(new Vaccine("Serum Institute", "AstraZeneca"));
	    	pcvs.add(new Vaccine("Sinovac Biotech", "CoronaVac"));
	    	pcvs.add(new Vaccine("BioNTech SE", "Pfizer-BioNTech"));
	    	
	    	HealthcareCentre centre = new HealthcareCentre(
	    		"ABC Medical Centre", "Kelana Jaya");
	    	pcvs.add(centre);

	    	Administrator admin = new Administrator("j", "j", "j", "j", centre);
	    	centre.add(admin);
	    	pcvs.add(admin);

	    	pcvs.add(new Patient("f", "f", "f", "f", "A1236"));
	    	pcvs.add(new Patient("a", "a", "a", "a", "A1234"));

	    	centre = new HealthcareCentre("GT Hospital", 
	    		"Green Lane, Penang");
	    	pcvs.add(centre);
	    	
	    	admin = new Administrator("k", "k", "k", "k", centre);
	    	centre.add(admin);
	    	pcvs.add(admin);
	    	
	    	pcvs.add(new Patient("c", "c", "c", "c", "A1235"));

	    	Vaccine vaccine = pcvs.findVaccine(1);
	    	centre = admin.getHealthcareCentre();
	    	
	    	Batch newBatch = new Batch("az001", LocalDate.of(2022, 2, 22), 2, 
	    		vaccine);
			centre.add(newBatch);
			vaccine.add(newBatch); 
	    }
	}


