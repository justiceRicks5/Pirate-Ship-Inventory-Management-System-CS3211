package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel;

import java.util.Optional;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * login model view data bind the view with the model
 * 
 * @author jr00381
 * @version fall 2025
 */
public class LoginViewModel {

	private final CredentialStore store;

	private final StringProperty username = new SimpleStringProperty("");
	private final StringProperty password = new SimpleStringProperty("");
	private final StringProperty status = new SimpleStringProperty("");

	private final BooleanProperty inputsValid = new SimpleBooleanProperty(false);
	private final BooleanProperty loginDisabled = new SimpleBooleanProperty(true);

	private Users currentUser;

	/**
	 * login view model consttor
	 * 
	 * @param store a temp crential storage
	 */
	public LoginViewModel(CredentialStore store) {
		if (store == null) {
			throw new IllegalArgumentException("CredentialStore required");
		}
		this.store = store;

		this.inputsValid.bind(Bindings.createBooleanBinding(
				() -> !this.getUsername().isBlank() && !this.getPassword().isBlank(), this.username, this.password));

		this.loginDisabled.bind(this.inputsValid.not());
	}

	/**
	 * Called by the controller when the Login button is pressed.
	 * 
	 * @return login status
	 */
	public boolean login() {
		boolean ok = this.store.verifyCrenditals(this.getUsername(), this.getPassword());
		if (ok) {
			Optional<Users> user = this.store.authenticate(this.getUsername(), this.getPassword());
			this.currentUser = user.orElse(null);
			this.status.set("Welcome, " + this.getUsername() + "!");
		} else {
			this.currentUser = null;
			this.status.set("Invalid username or password.");
		}
		return ok;
	}
	

	/**
	 * Gets the current user role.
	 *
	 * @return the current user role
	 */
	public Role getCurrentUserRole() {
		if (this.currentUser == null) {
			return null;
		}
		return this.currentUser.getRole();
	}

	/**
	 * username Property
	 *
	 * @return returns a property of the username
	 */
	public StringProperty usernameProperty() {
		return this.username;
	}

	/**
	 * String property for the password
	 * 
	 * @return password propertys
	 */
	public StringProperty passwordProperty() {
		return this.password;
	}

	/**
	 * String property for the status
	 * 
	 * @return status of the string
	 */
	public StringProperty statusProperty() {
		return this.status;
	}

	/**
	 * disable property for the login button
	 * 
	 * @return the value to see if we disable the login button
	 */
	public BooleanProperty loginDisabledProperty() {
		return this.loginDisabled;
	}

	/**
	 * getter for the username
	 * 
	 * @return username for the player that has a user
	 */
	public String getUsername() {
		return this.username.get();
	}

	/**
	 * sets username for the system so user can create a username
	 * 
	 * @param name a varialbe username
	 */
	public void setUsername(String name) {
		this.username.set(name);
	}

	/**
	 * getter for the password
	 * 
	 * @return fetches the password
	 */
	public String getPassword() {
		return this.password.get();
	}

	/**
	 * setter for the password
	 * 
	 * @param password a variable for the password
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}

	/**
	 * gets the status of the system
	 * 
	 * @return status of the system
	 */
	public String getStatus() {
		return this.status.get();
	}

	/**
	 * current user of the system
	 * 
	 * @return get current system
	 */
	public Users getCurrentUser() {
		return this.currentUser;
	}
}
