package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testviewmodels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LandPageViewModel;

public class LandPageViewModelTest {

    private LandPageViewModel viewModel;
    private Users mockUser;

    @BeforeEach
    public void setup() {
        viewModel = new LandPageViewModel();
        mockUser = new Users("Test User", "jsut", Role.QUATERMASTER);
    }

    @Test
    public void testSetCurrentUser_Null() {
        viewModel.setCurrentUser(null);
        
        assertNull(viewModel.getCurrentUser());
        assertEquals("Welcome aboard!", viewModel.getWelcomeMessage());
    }

    @Test
    public void testSetCurrentUser_ValidUser() {
        viewModel.setCurrentUser(mockUser);
        
        assertNotNull(viewModel.getCurrentUser());
        assertEquals("Test User", viewModel.getCurrentUser().getName());
        assertEquals("Welcome aboard, Test User!", viewModel.getWelcomeMessage());
    }

    @Test
    public void testWelcomeMessageProperty() {
        viewModel.setCurrentUser(mockUser);
        assertEquals("Welcome aboard, Test User!", viewModel.welcomeMessageProperty().get());
    }

    @Test
    public void testGetWelcomeMessage() {
        viewModel.setCurrentUser(mockUser);
        assertEquals("Welcome aboard, Test User!", viewModel.getWelcomeMessage());
    }
}
