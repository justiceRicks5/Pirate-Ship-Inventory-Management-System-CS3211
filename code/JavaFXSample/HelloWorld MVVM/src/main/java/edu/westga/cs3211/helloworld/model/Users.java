package edu.westga.cs3211.helloworld.model;

public class Users {
	private String name;
	private String password;
	private String role;

	public Users(String name, String password, String role) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (role == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		this.name = name;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
