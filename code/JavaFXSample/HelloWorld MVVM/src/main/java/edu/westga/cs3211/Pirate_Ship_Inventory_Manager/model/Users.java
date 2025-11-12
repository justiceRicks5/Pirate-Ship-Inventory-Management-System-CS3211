package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model;

public class Users {
	private String name;
	private String password;
	private Role role;

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

	public boolean verifyPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
