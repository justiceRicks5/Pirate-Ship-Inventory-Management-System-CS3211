package testPerseniceManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Compartment;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;

public class InventoryTest {
	private Inventory inventory;
	private Stock stock;
	private Compartment compartment;

	@BeforeEach
	public void setUp() {

		inventory = new Inventory();

		SpecialQualitys quality = SpecialQualitys.REGULAR;
		Condtion condition = Condtion.USEABLE;
		String name = "Test Stock";
		stock = new Stock(10, quality, condition, name, null);

		compartment = new Compartment(null, SpecialQualitys.REGULAR, 100);
		inventory.addCompartment(compartment);
	}

	@Test
	public void testAddCompartment() {

		Compartment newCompartment = new Compartment(null, SpecialQualitys.PERSHIABLE, 150);

		inventory.addCompartment(newCompartment);

		assertTrue(inventory.getCompartments().contains(newCompartment));
	}

	@Test
	public void testHasFreeSpaceFor() {

		assertTrue(inventory.hasFreeSpaceFor(stock));
	}

	@Test
	public void testHasNoFreeSpaceFor() {

		Stock largeStock = new Stock(200, SpecialQualitys.REGULAR, Condtion.USEABLE, "Large Stock", null);

		assertFalse(inventory.hasFreeSpaceFor(largeStock));
	}

	@Test
	public void testAddStockToCompartment() {
		inventory.addStockToCompartment(stock, compartment);

		assertEquals(stock, compartment.getStock());
	}

	@Test
	public void testAddStockToCompartmentWithInsufficientSpace() {
		Stock largeStock = new Stock(200, SpecialQualitys.REGULAR, Condtion.USEABLE, "Large Stock", null);
		assertThrows(IllegalArgumentException.class, () -> inventory.addStockToCompartment(largeStock, compartment));
	}

	@Test
	public void testAddStockWithNull() {
		assertThrows(IllegalArgumentException.class, () -> inventory.addStockToCompartment(null, compartment));
	}

	@Test
	public void testAddCompartmentWithNull() {
		assertThrows(IllegalArgumentException.class, () -> inventory.addStockToCompartment(stock, null));
	}

	@Test
	public void testGetAllStock() {
		inventory.addStock(stock);

		assertTrue(inventory.getAllStock().contains(stock));
	}

	@AfterEach
	public void tearDown() {
		inventory = null;
		stock = null;
		compartment = null;
	}

}
