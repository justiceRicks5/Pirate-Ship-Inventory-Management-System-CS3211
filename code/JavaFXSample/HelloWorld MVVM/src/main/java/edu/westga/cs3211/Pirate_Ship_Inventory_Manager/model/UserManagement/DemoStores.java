package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement;

/**
 * demo store class a demo of the store.
 * @author jr00381
 * @version Fall 2025
 */
public final class DemoStores {

	/**
	 * Instantiates a new demo stores.
	 */
	private DemoStores() {

	}

	/**
	 * sample crentiale storage.
	 *
	 * @return stores sample passwords that are accepted in the system
	 */
	public static CredentialStore sampleStore() {
		CredentialStore store = new CredentialStore();
		store.addUser(new Users("alice", "Password123!", Role.CREWMATE));
		store.addUser(new Users("bob", "letmein", Role.QUATERMASTER));
		return store;
	}
}
