package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryHolder
 * 
 * @version fall 2025
 * @author jr00381
 */
public final class InventoryHolder {

	/** The Constant INVENTORY. */
	private static final Inventory INVENTORY = InventoryPersistenceManager.load();

	/**
	 * Instantiates a new inventory holder.
	 */
	private InventoryHolder() {
	}

	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	public static Inventory getInventory() {
		return INVENTORY;
	}

	/**
	 * Save.
	 */
	public static void save() {
		InventoryPersistenceManager.save(INVENTORY);
	}
}
