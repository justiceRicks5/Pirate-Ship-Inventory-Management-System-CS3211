package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

// TODO: Auto-generated Javadoc
/**
 * The Class Compartment.
 * 
 * @author jr00381
 * @version Fall 2025
 */
public class Compartment {

	/** The stock. */
	private Stock stock;

	/** The special qualitys. */
	private SpecialQualitys specialQualitys;

	/** The capacity. */
	private int capacity;

	/**
	 * Instantiates a new compartment.
	 *
	 * @param stock           the stock
	 * @param specialQualitys the special qualitys
	 * @param capacity        the capacity
	 */
	public Compartment(Stock stock, SpecialQualitys specialQualitys, int capacity) {
		super();
		this.stock = stock;
		this.specialQualitys = specialQualitys;
		this.capacity = capacity;
	}

	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public Stock getStock() {
		return this.stock;
	}

	/**
	 * Sets the stock.
	 *
	 * @param stock the new stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * Gets the special qualitys.
	 *
	 * @return the special qualitys
	 */
	public SpecialQualitys getSpecialQualitys() {
		return this.specialQualitys;
	}

	/**
	 * Sets the special qualitys.
	 *
	 * @param specialQualitys the new special qualitys
	 */
	public void setSpecialQualitys(SpecialQualitys specialQualitys) {
		this.specialQualitys = specialQualitys;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the free space.
	 *
	 * @return the free space
	 */
	public int getFreeSpace() {
		if (this.stock == null) {
			return this.capacity;
		}
		int used = (int) this.stock.getSize();
		int free = this.capacity - used;
		return Math.max(free, 0);

	}
}
