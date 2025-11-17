package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.Test.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class testGettersAndSetters {
	  @Test
	    void testSetNameUpdatesName() {
	        // Arrange
	        Users user = new Users("OldName", "Secret123", Role.QUATERMASTER);

	        // Act
	        user.setName("NewName");

	        // Assert
	        assertEquals("NewName", user.getName());
	    }

	    @Test
	    void testSetPasswordUpdatesPassword() {
	        // Arrange
	        Users user = new Users("Justice", "OldPass", Role.QUATERMASTER);

	        // Act
	        user.setPassword("NewPass123");

	        // Assert
	        assertEquals("NewPass123", user.getPassword());
	        assertTrue(user.verifyPassword("NewPass123"));
	        assertFalse(user.verifyPassword("OldPass"));
	    }

	    @Test
	    void testSetRoleUpdatesRole() {
	        // Arrange
	        Users user = new Users("Justice", "Secret123", Role.CREWMATE);

	        // Act
	        user.setRole(Role.QUATERMASTER);

	        // Assert
	        assertEquals(Role.QUATERMASTER, user.getRole());
	    }

	    @Test
	    void testGetNameReturnsInitialName() {
	        // Arrange
	        Users user = new Users("InitialName", "Secret123", Role.QUATERMASTER);

	        // Act
	        String result = user.getName();

	        // Assert
	        assertEquals("InitialName", result);
	    }

	    @Test
	    void testGetPasswordReturnsInitialPassword() {
	        
	        Users user = new Users("Justice", "InitialPass", Role.QUATERMASTER);

	        // Act
	        String result = user.getPassword();

	        // Assert
	        assertEquals("InitialPass", result);
	    }

	    @Test
	    void testGetRoleReturnsInitialRole() {
	        // Arrange
	        Users user = new Users("Justice", "Secret123", Role.CREWMATE);

	        // Act
	        Role result = user.getRole();

	        // Assert
	        assertEquals(Role.CREWMATE, result);
	    }
	}

