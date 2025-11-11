package edu.westga.cs3211.helloworld.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrendtialStore {
	private final Map<String, Users> UserCrenditals = new HashMap<>();

	private static String norm(String username) {
		if (username == null) {
			return null;
		} else {
			return username.trim().toLowerCase();
		}
	}

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

}
