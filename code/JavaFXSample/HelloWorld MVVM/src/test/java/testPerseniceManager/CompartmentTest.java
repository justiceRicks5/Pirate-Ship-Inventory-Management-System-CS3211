package testPerseniceManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Compartment;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;

public class CompartmentTest {
	private Stock stock;
    private Compartment compartment;

    @BeforeEach
    public void setUp() {
        
        SpecialQualitys quality = SpecialQualitys.REGULAR;
        Condtion condition = Condtion.USEABLE;
        String name = "Test Stock";
        stock = new Stock(10, quality, condition, name, null); // 10 units of stock
    }

    @Test
    public void testCompartmentCreation() {
        // Create a compartment with a certain capacity and special quality
        SpecialQualitys quality = SpecialQualitys.REGULAR;
        int capacity = 100;
        compartment = new Compartment(stock, quality, capacity);

        // Verify compartment properties
        assertNotNull(compartment);
        assertEquals(quality, compartment.getSpecialQualitys());
        assertEquals(capacity, compartment.getCapacity());
    }

    @Test
    public void testGetFreeSpaceWithEmptyCompartment() {
        // Create a compartment with capacity 100 and no stock
        compartment = new Compartment(null, SpecialQualitys.REGULAR, 100);

        // Verify the free space (should be equal to capacity)
        assertEquals(100, compartment.getFreeSpace());
    }

    @Test
    public void testGetFreeSpaceWithStock() {
        // Create a compartment with stock
        compartment = new Compartment(stock, SpecialQualitys.REGULAR, 100);

        // Verify the free space (should be capacity - stock size)
        assertEquals(90, compartment.getFreeSpace());
    }

    @Test
    public void testCanStoreWhenCompartmentHasSufficientSpace() {
        // Create a compartment with enough space for the stock
        compartment = new Compartment(stock, SpecialQualitys.REGULAR, 100);

        // Verify that the stock can be stored
        assertTrue(compartment.canStore(stock));
    }

    @Test
    public void testCanStoreWhenCompartmentHasInsufficientSpace() {
        // Create a compartment with insufficient space for the stock
        compartment = new Compartment(stock, SpecialQualitys.REGULAR, 5);

        // Verify that the stock cannot be stored due to insufficient space
        assertFalse(compartment.canStore(stock));
    }

    @Test
    public void testCanStoreWithMismatchedQuality() {
        // Create a compartment with a different special quality
        compartment = new Compartment(stock, SpecialQualitys.PERSHIABLE, 100);

        // Verify that the stock cannot be stored due to mismatched quality
        assertFalse(compartment.canStore(stock));
    }

    @Test
    public void testCanStoreWithNullStock() {
        // Verify that passing a null stock throws an IllegalArgumentException
        compartment = new Compartment(stock, SpecialQualitys.REGULAR, 100);

        assertThrows(IllegalArgumentException.class, () -> compartment.canStore(null));
    }

    @Test
    public void testToString() {
        // Create a compartment and verify its string representation
        compartment = new Compartment(stock, SpecialQualitys.REGULAR, 100);
        String expectedString = "REGULAR compartment (free: 90)";
        assertEquals(expectedString, compartment.toString());
    }

    @AfterEach
    public void tearDown() {
        // Cleanup any resources (if needed)
        stock = null;
        compartment = null;
    }
}
