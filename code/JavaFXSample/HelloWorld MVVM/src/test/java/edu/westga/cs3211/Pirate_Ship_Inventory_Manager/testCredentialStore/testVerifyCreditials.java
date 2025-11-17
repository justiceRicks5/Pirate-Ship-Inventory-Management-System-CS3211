package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testCredentialStore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class testVerifyCreditials {
	  @Test
	    void testVerifyCrenditalsReturnsTrueForValidUserAndPassword() {
	        CredentialStore store = new CredentialStore();
	        Users user = new Users("justice", "Secret123", Role.QUATERMASTER);
	        store.addUser(user);

	        boolean result = store.verifyCrenditals("justice", "Secret123");

	        assertTrue(result);
	    }

	    @Test
	    void testVerifyCrenditalsReturnsFalseForNonExistingUser() {
	        CredentialStore store = new CredentialStore();

	        boolean result = store.verifyCrenditals("unknown", "AnyPass");

	        assertFalse(result);
	    }

	    @Test
	    void testVerifyCrenditalsReturnsFalseForWrongPassword() {
	        CredentialStore store = new CredentialStore();
	        Users user = new Users("justice", "CorrectPass", Role.QUATERMASTER);
	        store.addUser(user);

	        boolean result = store.verifyCrenditals("justice", "WrongPass");

	        assertFalse(result);
	    }
}
