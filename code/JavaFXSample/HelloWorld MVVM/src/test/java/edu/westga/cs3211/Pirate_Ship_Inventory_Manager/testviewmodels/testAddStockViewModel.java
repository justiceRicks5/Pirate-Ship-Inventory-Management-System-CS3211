package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testviewmodels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.AddStockViewModel;

public class testAddStockViewModel {
	private Inventory mockInventory;
    private Users mockUser;
    private AddStockViewModel viewModel;

    @BeforeEach
    public void setup() {
        mockInventory = new Inventory();
        mockUser = new Users("testUser", "just", Role.QUATERMASTER);
        viewModel = new AddStockViewModel(mockInventory, mockUser);
    }

    @Test
    public void testSubmit_Success() {
        viewModel.nameProperty().set("Test Stock");
        viewModel.sizeTextProperty().set("10");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(SpecialQualitys.REGULAR);
        viewModel.expirationDateProperty().set(null);

        boolean result = viewModel.submit();

        assertTrue(result);
        assertEquals("Stock added successfully!", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_InvalidSize() {
        viewModel.nameProperty().set("Test Stock");
        viewModel.sizeTextProperty().set("-10");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(SpecialQualitys.REGULAR);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("Size must be a positive number.", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_MissingName() {
        viewModel.nameProperty().set("");
        viewModel.sizeTextProperty().set("10");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(SpecialQualitys.REGULAR);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("Please enter a name for the stock.", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_MissingCondition() {
        viewModel.nameProperty().set("Test Stock");
        viewModel.sizeTextProperty().set("10");
        viewModel.conditionProperty().set(null);
        viewModel.qualityProperty().set(SpecialQualitys.REGULAR);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("Please select a condition.", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_MissingQuality() {
        viewModel.nameProperty().set("Test Stock");
        viewModel.sizeTextProperty().set("10");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(null);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("Please select a special quality.", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_PerishableWithNoExpirationDate() {
        viewModel.nameProperty().set("Test Perishable Stock");
        viewModel.sizeTextProperty().set("10");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(SpecialQualitys.PERSHIABLE);
        viewModel.expirationDateProperty().set(null);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("Perishable stock must have an expiration date.", viewModel.messageProperty().get());
    }

    @Test
    public void testSubmit_NoSpaceForStock() {
        viewModel.nameProperty().set("Large Stock");
        viewModel.sizeTextProperty().set("10000");
        viewModel.conditionProperty().set(Condtion.USEABLE);
        viewModel.qualityProperty().set(SpecialQualitys.REGULAR);

        boolean result = viewModel.submit();

        assertFalse(result);
        assertEquals("No suitable storage compartment for that stock.", viewModel.messageProperty().get());
    }
}
