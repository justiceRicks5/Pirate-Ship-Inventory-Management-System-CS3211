package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testviewmodels;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LoginViewModel;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private CredentialStore credentialStore;
    private Users user;

    @BeforeEach
    public void setup() {
        
        credentialStore = new CredentialStore();
        user = new Users("testuser", "password", Role.QUATERMASTER); 
        credentialStore.addUser(user);

        loginViewModel = new LoginViewModel(credentialStore);
    }

    @Test
    public void testConstructor_WhenStoreIsNull_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new LoginViewModel(null));
    }

    @Test
    public void testLogin_SuccessfulLogin_UpdatesStatus() {
        loginViewModel.setUsername("testuser");
        loginViewModel.setPassword("password");

        boolean loginResult = loginViewModel.login();

        assertTrue(loginResult);
        assertEquals("Welcome, testuser!", loginViewModel.getStatus());
        assertNotNull(loginViewModel.getCurrentUser());
    }

    @Test
    public void testLogin_FailedLogin_UpdatesStatus() {
        loginViewModel.setUsername("testuser");
        loginViewModel.setPassword("wrongpassword");

        boolean loginResult = loginViewModel.login();

        assertFalse(loginResult);
        assertEquals("Invalid username or password.", loginViewModel.getStatus());
        assertNull(loginViewModel.getCurrentUser());
    }

    @Test
    public void testUsernameBinding() {
        StringProperty usernameProperty = loginViewModel.usernameProperty();
        loginViewModel.setUsername("testuser");

        assertEquals("testuser", usernameProperty.get());
    }

    @Test
    public void testPasswordBinding() {
        StringProperty passwordProperty = loginViewModel.passwordProperty();
        loginViewModel.setPassword("password");

        assertEquals("password", passwordProperty.get());
    }

    @Test
    public void testLoginDisabledBinding_WhenFieldsAreEmpty() {
        loginViewModel.setUsername("");
        loginViewModel.setPassword("");

        assertTrue(loginViewModel.loginDisabledProperty().get());
    }

    @Test
    public void testLoginDisabledBinding_WhenFieldsAreValid() {
        loginViewModel.setUsername("testuser");
        loginViewModel.setPassword("password");

        assertFalse(loginViewModel.loginDisabledProperty().get());
    }

    @Test
    public void testStatusProperty_UpdatesWhenLoginChanges() {
        loginViewModel.setUsername("testuser");
        loginViewModel.setPassword("password");

        loginViewModel.login();

        assertEquals("Welcome, testuser!", loginViewModel.statusProperty().get());
    }

    @Test
    public void testCurrentUserRole() {
        loginViewModel.setUsername("testuser");
        loginViewModel.setPassword("password");

        loginViewModel.login();

        assertNotNull(loginViewModel.getCurrentUserRole());
    }
}
