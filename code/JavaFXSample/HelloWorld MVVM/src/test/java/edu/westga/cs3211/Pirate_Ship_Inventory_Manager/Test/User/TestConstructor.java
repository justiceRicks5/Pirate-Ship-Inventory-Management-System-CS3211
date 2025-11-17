package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.Test.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

class TestConstructor {

	@Test
    void testConstructorWithValidArgumentsInitializesFields() {
        // Arrange
        String name = "Justice";
        String password = "Secret123";
        Role role = Role.QUATERMASTER;

        // Act
        Users user = new Users(name, password, role);

        // Assert
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    void testConstructorWithNullNameThrowsException() {
        // Arrange
        String name = null;
        String password = "Secret123";
        Role role = Role.QUATERMASTER;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Users(name, password, role);
        });
    }

    @Test
    void testConstructorWithNullPasswordThrowsException() {
        // Arrange
        String name = "Justice";
        String password = null;
        Role role = Role.QUATERMASTER;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Users(name, password, role);
        });
    }

    @Test
    void testConstructorWithNullRoleThrowsException() {
        // Arrange
        String name = "Justice";
        String password = "Secret123";
        Role role = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Users(name, password, role);
        });
    }

}
