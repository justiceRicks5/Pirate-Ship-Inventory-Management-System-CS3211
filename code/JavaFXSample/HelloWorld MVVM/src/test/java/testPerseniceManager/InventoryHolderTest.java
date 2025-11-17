package testPerseniceManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.InventoryHolder;

public class InventoryHolderTest {

    private static Inventory inventory;

    @BeforeAll
    public static void setup() {

        inventory = InventoryHolder.getInventory();
    }

    @Test
    public void testGetInventory() {
        Inventory retrievedInventory = InventoryHolder.getInventory();

        assertNotNull(retrievedInventory, "Inventory should not be null.");
        assertEquals(inventory, retrievedInventory, "The retrieved inventory should be the same as the original.");
    }

    @Test
    public void testSave() {
        InventoryHolder.save();
        assertDoesNotThrow(() -> InventoryHolder.save(), "Saving inventory should not throw any exception.");
    }
}
