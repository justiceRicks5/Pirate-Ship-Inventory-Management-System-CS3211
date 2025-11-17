package testPerseniceManager;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryPersistenceManagerTest {

    private static final String FILE_NAME = "inventory.tsv";
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
    	
        // Setup a new inventory before each test
        inventory = new Inventory();

        // Add some test stock items
        SpecialQualitys quality = SpecialQualitys.REGULAR;
        Condtion condition = Condtion.USEABLE;
        String name = "Test Stock";
        Date expiration = new Date(System.currentTimeMillis() + 1000000);  // Some future date
        Compartment compartment = new Compartment(null, SpecialQualitys.REGULAR, 100);

        Stock stock = new Stock(10, quality, condition, name, expiration);
        stock.setCompartment(compartment);
        inventory.addStock(stock);
    }

    @Test
    public void testSaveAndLoad() {
        // Save the inventory to the file
        InventoryPersistenceManager.save(inventory);

        // Load the inventory from the file
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory is not null
        assertNotNull(loadedInventory);

        // Verify that we loaded the same number of stock items
        assertEquals(1, loadedInventory.getAllStock().size());

        // Verify the loaded stock item's properties
        Stock loadedStock = loadedInventory.getAllStock().get(0);
        assertEquals(10, loadedStock.getSize());
        assertEquals(SpecialQualitys.REGULAR, loadedStock.getSpecialQuality());
        assertEquals(Condtion.USEABLE, loadedStock.getCondition());
        assertEquals("Test Stock", loadedStock.getName());
        assertNotNull(loadedStock.getExpirationDate());
    }

    @Test
    public void testLoadEmptyInventory() {
        // Make sure the file is empty (or doesn't exist)
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        // Load the inventory from the file
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory is empty
        assertNotNull(loadedInventory);
        assertTrue(loadedInventory.getAllStock().isEmpty());
    }

    @Test
    public void testSaveEmptyInventory() {
        // Save an empty inventory
        Inventory emptyInventory = new Inventory();
        InventoryPersistenceManager.save(emptyInventory);

        // Load the inventory from the file
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory is empty
        assertNotNull(loadedInventory);
        assertTrue(loadedInventory.getAllStock().isEmpty());
    }

    @Test
    public void testLoadCorruptedFile() {
        // Create a corrupted file
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            out.println("This is corrupted data!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the inventory from the corrupted file
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory is empty due to corrupted data
        assertNotNull(loadedInventory);
        assertTrue(loadedInventory.getAllStock().isEmpty());
    }

    @Test
    public void testSaveWithNullStock() {
        // Save an inventory with null stock (edge case)
        Inventory nullStockInventory = new Inventory();
        nullStockInventory.addStock(null); // Add null stock
        InventoryPersistenceManager.save(nullStockInventory);

        // Load the inventory from the file
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory doesn't contain null stock
        assertNotNull(loadedInventory);
        assertTrue(loadedInventory.getAllStock().isEmpty());
    }

    @Test
    public void testLoadWithPartialData() {
        // Create a file with incomplete stock data (missing fields)
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            out.println("10\tREGULAR\tUSEABLE\tTest Stock");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the inventory from the file with partial data
        Inventory loadedInventory = InventoryPersistenceManager.load();

        // Verify that the loaded inventory contains no stock or correctly handles the error
        assertNotNull(loadedInventory);
        assertTrue(loadedInventory.getAllStock().isEmpty());
    }

    @AfterEach
    public void tearDown() {
        // Clean up by deleting the file after each test to ensure a clean state for the next test
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
