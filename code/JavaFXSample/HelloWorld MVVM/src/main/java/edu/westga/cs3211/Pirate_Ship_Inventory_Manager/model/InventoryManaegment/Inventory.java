package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

public class Inventory {
	private Compartment compartments;

	public Inventory(Compartment compartments) {
		this.compartments = compartments;
	}

	 /**
     * Determines whether the ship has enough free space in its compartment
     * for the given stock.
     * 
     * @param stock the stock we want to add
     * @return true if there is enough free space; false otherwise
     * @pre stock != null
     */
    public boolean hasFreeSpaceFor(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("stock cannot be null");
        }
        if (this.compartments == null) {
            return false; 
        }

        int freeSpace = this.compartments.getFreeSpace();      
        int amountToAdd = (int) stock.getSize(); 

        return freeSpace >= amountToAdd;
    }
}
