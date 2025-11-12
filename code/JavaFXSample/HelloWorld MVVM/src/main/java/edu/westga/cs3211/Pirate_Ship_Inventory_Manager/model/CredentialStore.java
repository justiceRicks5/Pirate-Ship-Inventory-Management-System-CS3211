package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CredentialStore {
	private final Map<String, Users> UserCrenditals = new HashMap<>();

	private static String norm(String username) {
		if (username == null) {
			return null;
		} else {
			return username.trim().toLowerCase();
		}
	}

	/**
	 * add users to the system
	 * 
	 * @param user a user being added to the system
	 */
	public void addUser(Users user) {
		String key = this.norm(user.getName());
		if (key == null || key.isEmpty())
			throw new IllegalArgumentException("username required");
		if (this.UserCrenditals.containsKey(key)) {
			throw new IllegalArgumentException("username already taken");
		}
		this.UserCrenditals.put(key, user);
	}

	public boolean VerifyCrenditals(String username, String password) {
		Users user1 = this.UserCrenditals.get(username);

		if (user1 == null) {
			return false;
		}

		return user1.verifyPassword(password);

	}

	public Optional<Users> authenticate(String username, String password) {
		if (this.VerifyCrenditals(username, password)) {
			String key = norm(username);
			Users user = this.UserCrenditals.get(key);
			return Optional.of(user);
		}
		return Optional.empty();
	}

}
