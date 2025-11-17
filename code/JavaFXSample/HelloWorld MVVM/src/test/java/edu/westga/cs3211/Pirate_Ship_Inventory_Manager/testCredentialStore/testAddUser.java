package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testCredentialStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class testAddUser {
	 @Test
	    void testAddUserStoresUserWithNormalizedUsername() {
	        CredentialStore store = new CredentialStore();
	        Users user = new Users("  Justice  ", "Secret123", Role.QUATERMASTER);

	        store.addUser(user);
	        boolean result = store.verifyCrenditals("justice", "Secret123");

	        assertTrue(result);
	    }

	    @Test
	    void testAddUserWithWhitespaceOnlyNameThrowsException() {
	        CredentialStore store = new CredentialStore();
	        Users user = new Users("   ", "Secret123", Role.QUATERMASTER);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
	            store.addUser(user);
	        });
	        assertEquals("username required", ex.getMessage());
	    }

	    @Test
	    void testAddUserWithNullNameFromOverriddenGetterThrowsException() {
	        CredentialStore store = new CredentialStore();
	        Users user = new Users("TempName", "Secret123", Role.QUATERMASTER) {
	            @Override
	            public String getName() {
	                return null;
	            }
	        };

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
	            store.addUser(user);
	        });
	        assertEquals("username required", ex.getMessage());
	    }

	    @Test
	    void testAddUserWithDuplicateUsernameThrowsException() {
	        CredentialStore store = new CredentialStore();
	        Users first = new Users("Justice", "Secret123", Role.QUATERMASTER);
	        Users second = new Users("justice", "OtherPass", Role.CREWMATE);

	        store.addUser(first);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
	            store.addUser(second);
	        });
	        assertEquals("username already taken", ex.getMessage());
	    }
	    
}
