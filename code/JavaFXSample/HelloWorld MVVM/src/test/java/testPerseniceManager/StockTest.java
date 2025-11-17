package testPerseniceManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Compartment;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;

public class StockTest {

    private Stock stock;
    private Compartment compartment;
    private Date expirationDate;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        SpecialQualitys quality = SpecialQualitys.REGULAR;
        Condtion condition = Condtion.USEABLE;
        String name = "Test Stock";
        expirationDate = new Date(0, 0, 0);
        stock = new Stock(10, quality, condition, name, expirationDate); // 10 units of stock
   
        compartment = new Compartment(null, SpecialQualitys.REGULAR, 100);
        stock.setCompartment(compartment);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Stock", stock.getName());
    }

    @Test
    public void testSetName() {
        stock.setName("Updated Stock");
        assertEquals("Updated Stock", stock.getName());
    }

    @Test
    public void testGetSize() {
        assertEquals(10, stock.getSize());
    }

    @Test
    public void testSetSize() {
        stock.setSize(20);
        assertEquals(20, stock.getSize());
    }

    @Test
    public void testGetCondition() {
        assertEquals(Condtion.USEABLE, stock.getCondition());
    }

    @Test
    public void testSetCondition() {
        stock.setCondition(Condtion.PERFECT);
        assertEquals(Condtion.PERFECT, stock.getCondition());
    }

    @Test
    public void testGetSpecialQuality() {
        assertEquals(SpecialQualitys.REGULAR, stock.getSpecialQuality());
    }

    @Test
    public void testSetSpecialQuality() {
        stock.setSpecialQuality(SpecialQualitys.PERSHIABLE);
        assertEquals(SpecialQualitys.PERSHIABLE, stock.getSpecialQuality());
    }

    @Test
    public void testGetExpirationDate() {
        assertEquals(expirationDate, stock.getExpirationDate());
    }

    @Test
    public void testSetExpirationDate() {
        @SuppressWarnings("deprecation")
		Date newExpirationDate = new Date(0, 0, 0);
        stock.setExpirationDate(newExpirationDate);
        assertEquals(newExpirationDate, stock.getExpirationDate());
    }

    @Test
    public void testGetExpirationDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String expectedDate = sdf.format(expirationDate);
        assertEquals(expectedDate, stock.getExpirationDateString());
    }

    @Test
    public void testGetCompartmentName() {
        assertEquals("REGULAR compartment (free: 100)", stock.getCompartmentName());
    }

    @Test
    public void testSetCompartment() {
        Compartment newCompartment = new Compartment(null, SpecialQualitys.LIQUID, 50);
        stock.setCompartment(newCompartment);
        assertEquals(newCompartment, stock.getCompartment());
    }

    @Test
    public void testGetAddedTimeString() {
        String addedTimeString = stock.getAddedTimeString();
        assertNotNull(addedTimeString); 
        assertTrue(addedTimeString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testSetAddedTime() {
        LocalDateTime newTime = LocalDateTime.of(2025, 11, 16, 10, 30, 0);
        stock.setAddedTime(newTime);
        assertEquals(newTime, stock.getAddedTime());
    }

    @Test
    public void testGetAddedBy() {
        stock.setAddedBy("John Doe");
        assertEquals("John Doe", stock.getAddedBy());
    }

    @Test
    public void testSetAddedBy() {
        stock.setAddedBy("Jane Doe");
        assertEquals("Jane Doe", stock.getAddedBy());
    }

 

    @Test
    public void testGetCompartmentNameWhenNotAssigned() {
        stock.setCompartment(null);
        assertEquals("Not assigned", stock.getCompartmentName());
    }

    @Test
    public void testCanStore() {
        assertTrue(compartment.canStore(stock)); // stock size is less than the compartment's free space
    }

    @Test
    public void testCannotStore() {
        Stock largeStock = new Stock(200, SpecialQualitys.REGULAR, Condtion.USEABLE, "Large Stock", null);
        assertFalse(compartment.canStore(largeStock)); // large stock doesn't fit in the compartment
    }

   

    @Test
    public void testInvalidStockInNullName() {
        stock.setName(null);
        assertNull(stock.getName());
    }

    @AfterEach
    public void tearDown() {
        stock = null;
        compartment = null;
        expirationDate = null;
    }
}
