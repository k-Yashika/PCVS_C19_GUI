package GUI;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
		private String username;
		private String password;
		private String email;
		private String fullName;
		
		/**
		 * Constructor for User.
		 * @param username User's unique username
		 * @param password User's login password
		 * @param email User's email address
		 * @param fullName User's full name
		 */
		public User(String username, String password, String email,
			String fullName) {
			setUsername(username);
			setPassword(password);
			setEmail(email);
			setFullName(fullName);
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the fullName
		 */
		public String getFullName() {
			return fullName;
		}

		/**
		 * @param fullName the fullName to set
		 */
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		
		@Override
		/**
		 * Checking for equality of two User objects.
		 * Two users are deemed to be equal if their usernames
		 * are the same
		 * @param obj User object to check for equality
		 * #return true if both users are the same, false otherwise
		 */
		public boolean equals(Object obj) {
			if (obj instanceof User) {
				return getUsername().equalsIgnoreCase(
					((User) obj).getUsername());
			}
			return false;
		}

		@Override
		/**
		 * Returns a string representation of a user
		 * @return A string representing details of a user
		 */
		public String toString() {
			return String.format("%s (username: %s, password: %s email: %s)",
				getFullName(), getUsername(), getPassword(), getEmail());
		}

		/**
		 * Compare two uses according to their full names, lexicographically.
		 * @return the value 0 if name of parametric user is equal to this 
		 * user's name; a value greater than 0 if name of parametric 
		 * user is lexicographically less than the name of this user; and a 
		 * value less than 0 if name of parametric user is lexicographically
		 * greater than name of this user.
		 */
		public int compareTo(User rhs) {
			return getFullName().compareToIgnoreCase(rhs.getFullName());
		}
	
}
