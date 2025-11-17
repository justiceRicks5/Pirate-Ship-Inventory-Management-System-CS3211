package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.Test.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class TestVerifyPassword {
	@Test
	void testVerifyPasswordReturnsFalseWhenPasswordDoesNotMatch() {
		// Arrange
		Users user = new Users("Justice", "Secret123", Role.QUATERMASTER);

		// Act
		boolean result = user.verifyPassword("WrongPass");

		// Assert
		assertFalse(result);
	}

	@Test
	void testVerifyPasswordReturnsFalseWhenInputIsNull() {
		// Arrange
		Users user = new Users("Justice", "Secret123", Role.QUATERMASTER);

		// Act
		boolean result = user.verifyPassword(null);

		// Assert
		assertFalse(result);
	}
}