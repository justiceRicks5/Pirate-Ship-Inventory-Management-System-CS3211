package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement;

/**
 * intialze and create attriubute of the user .
 *
 * @author jr00381
 * @version fall 2025
 */
public class Users {

	/** The name. */
	private String name;

	/** The password. */
	private String password;

	/** The role. */
	private Role role;

	/**
	 * attriubets each user need intialztzed.
	 *
	 * @param name         the name of each user
	 * @param password     the password to get into the system
	 * @param quatermaster secondary role for the user
	 */
	public Users(String name, String password, Role quatermaster) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (quatermaster == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		this.name = name;
		this.password = password;
		this.role = quatermaster;
	}

	/**
	 * verify the pass word .
	 *
	 * @param inputPassword temp password
	 * @return true if the password is the same same as the password for the system
	 */
	public boolean verifyPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}

	/**
	 * getter for the name .
	 *
	 * @return the name of the user
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set the name .
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
