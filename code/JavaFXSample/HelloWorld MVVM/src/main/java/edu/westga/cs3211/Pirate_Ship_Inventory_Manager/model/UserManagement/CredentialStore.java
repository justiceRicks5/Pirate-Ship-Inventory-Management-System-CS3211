package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Credential store place to store creditanials.
 *
 * @author jr00381
 * @version fall 2025
 */
public class CredentialStore {

	/** The user crenditals. */
	private final Map<String, Users> userCrenditals = new HashMap<>();

	/**
	 * Norm.
	 *
	 * @param username the username
	 * @return the string
	 */
	private static String norm(String username) {
		if (username == null) {
			return null;
		} else {
			return username.trim().toLowerCase();
		}
	}

	/**
	 * add users to the system.
	 *
	 * @param user a user being added to the system
	 */
	public void addUser(Users user) {
		String key = this.norm(user.getName());
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException("username required");
		}
		if (this.userCrenditals.containsKey(key)) {
			throw new IllegalArgumentException("username already taken");
		}
		this.userCrenditals.put(key, user);
	}

	/**
	 * Verify crenditals.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean verifyCrenditals(String username, String password) {
		Users user1 = this.userCrenditals.get(username);

		if (user1 == null) {
			return false;
		}

		return user1.verifyPassword(password);

	}

	/**
	 * Authenticate.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the optional
	 */
	public Optional<Users> authenticate(String username, String password) {
		if (this.verifyCrenditals(username, password)) {
			String key = norm(username);
			Users user = this.userCrenditals.get(key);
			return Optional.of(user);
		}
		return Optional.empty();
	}

}
