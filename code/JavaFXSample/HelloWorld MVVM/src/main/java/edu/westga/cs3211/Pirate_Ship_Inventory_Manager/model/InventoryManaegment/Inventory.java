package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Inventory.
 * @author jr00381
 * @version fall 2025
 */
public class Inventory {

	/** The compartments. */
	private List<Compartment> compartments;

	/** The all stock. */
	private final List<Stock> allStock;

	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		this.compartments = new ArrayList<>();
		this.allStock = new ArrayList<>();

		this.compartments.add(new Compartment(null, SpecialQualitys.LIQUID, 100));
		this.compartments.add(new Compartment(null, SpecialQualitys.FLAMMABLE, 100));
		this.compartments.add(new Compartment(null, SpecialQualitys.PERSHIABLE, 100));
		this.compartments.add(new Compartment(null, SpecialQualitys.REGULAR, 100));
	}

	/**
	 * Adds the compartment.
	 *
	 * @param compartment the compartment
	 */
	public void addCompartment(Compartment compartment) {
		this.compartments.add(compartment);
	}

	/**
	 * Gets the compartments.
	 *
	 * @return the compartments
	 */
	public List<Compartment> getCompartments() {
		return this.compartments;
	}

	/**
	 * Checks for free space for.
	 * 
	 * @precondtion if stock null throw illegal argument exception
	 * @param stock the stock
	 * @return true, if successful
	 */
	public boolean hasFreeSpaceFor(Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("stock cannot be null");
		}

		for (Compartment compartment : this.compartments) {
			if (compartment.canStore(stock)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find compartments that can store.
	 * 
	 * @precondtion if stock null throw illegal argument exception
	 * @param stock the stock
	 * @return the list
	 */
	public List<Compartment> findCompartmentsThatCanStore(Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("stock cannot be null");
		}

		List<Compartment> result = new ArrayList<>();
		for (Compartment compartment : this.compartments) {
			if (compartment.canStore(stock)) {
				result.add(compartment);
			}
		}
		return result;
	}

	/**
	 * Adds the stock.
	 * 
	 * 
	 * @param stock the stock
	 */

	public void addStock(Stock stock) {
		if (stock != null) {
			this.allStock.add(stock);
		}
	}

	/**
	 * Gets the all stock.
	 *
	 * @return the all stock
	 */
	public List<Stock> getAllStock() {
		return this.allStock;
	}

	/**
	 * Adds the stock to compartment.
	 * 
	 * @precondtion if stock null throw illegal argument exception
	 * @precondtion if compartment is null throw a illegal argument exception
	 * @param stock       the stock
	 * @param compartment the compartment
	 */
	public void addStockToCompartment(Stock stock, Compartment compartment) {
		if (stock == null) {
			throw new IllegalArgumentException("stock cannot be null");
		}
		if (compartment == null) {
			throw new IllegalArgumentException("compartment cannot be null");
		}
		if (!compartment.canStore(stock)) {
			throw new IllegalArgumentException("Compartment cannot store this stock.");
		}

		compartment.setStock(stock);
	}
}
