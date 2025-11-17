package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testCredentialStore;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class testAuthenicator {
	@Test
    void testAuthenticateReturnsUserWhenCredentialsAreValid() {
        // Arrange
        CredentialStore store = new CredentialStore();
        Users user = new Users("justice", "Secret123", Role.QUATERMASTER);
        store.addUser(user);

        // Act
        Optional<Users> result = store.authenticate("justice", "Secret123");

        // Assert
        assertTrue(result.isPresent());
        assertSame(user, result.get());
    }

    @Test
    void testAuthenticateReturnsEmptyWhenPasswordIsWrong() {
        // Arrange
        CredentialStore store = new CredentialStore();
        Users user = new Users("justice", "CorrectPass", Role.QUATERMASTER);
        store.addUser(user);

        // Act
        Optional<Users> result = store.authenticate("justice", "WrongPass");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testAuthenticateReturnsEmptyWhenUserDoesNotExist() {
        // Arrange
        CredentialStore store = new CredentialStore();

        // Act
        Optional<Users> result = store.authenticate("unknown", "AnyPass");

        // Assert
        assertTrue(result.isEmpty());
    }
}
