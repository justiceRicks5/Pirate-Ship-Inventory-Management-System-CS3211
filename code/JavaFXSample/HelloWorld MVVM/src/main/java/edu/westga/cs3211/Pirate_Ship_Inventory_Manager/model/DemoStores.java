package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model;

public class DemoStores {
	private DemoStores() {}

    public static CredentialStore sampleStore() {
        CredentialStore store = new CredentialStore();
        store.addUser(new Users("alice", "Password123!", Role.COOK));
        store.addUser(new Users("bob", "letmein", Role.QUATERMASTER));
        return store;
    }
}
