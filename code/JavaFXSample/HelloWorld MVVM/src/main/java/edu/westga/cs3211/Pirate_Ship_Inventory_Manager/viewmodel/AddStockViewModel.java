package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AddStockViewModel.
 * 
 * @author jr00381
 * @version fall 2025
 */
public class AddStockViewModel {

	/** The inventory. */
	private final Inventory inventory;

	/** The current user. */
	private final Users currentUser;

	/** The name. */
	private final SimpleStringProperty name = new SimpleStringProperty("");

	/** The size text. */
	private final SimpleStringProperty sizeText = new SimpleStringProperty("");

	/** The condition. */
	private final ObjectProperty<Condtion> condition = new SimpleObjectProperty<>(null);

	/** The quality. */
	private final ObjectProperty<SpecialQualitys> quality = new SimpleObjectProperty<>(null);

	/** The expiration date. */
	private final ObjectProperty<LocalDate> expirationDate = new SimpleObjectProperty<>(null);

	/** The message. */
	private final SimpleStringProperty message = new SimpleStringProperty("");

	/**
	 * Instantiates a new adds the stock view model.
	 *
	 * @param inventory   the inventory
	 * @param currentUser the current user
	 */
	public AddStockViewModel(Inventory inventory, Users currentUser) {
		if (inventory == null) {
			throw new IllegalArgumentException("inventory cannot be null");
		}
		if (currentUser == null) {
			throw new IllegalArgumentException("currentUser cannot be null");
		}
		this.inventory = inventory;
		this.currentUser = currentUser;
	}

	/**
	 * Name property.
	 *
	 * @return the string property
	 */
	public SimpleStringProperty nameProperty() {
		return this.name;
	}

	/**
	 * Size text property.
	 *
	 * @return the string property
	 */
	public SimpleStringProperty sizeTextProperty() {
		return this.sizeText;
	}

	/**
	 * Condition property.
	 *
	 * @return the object property
	 */
	public ObjectProperty<Condtion> conditionProperty() {
		return this.condition;
	}

	/**
	 * Quality property.
	 *
	 * @return the object property
	 */
	public ObjectProperty<SpecialQualitys> qualityProperty() {
		return this.quality;
	}

	/**
	 * Expiration date property.
	 *
	 * @return the object property
	 */
	public ObjectProperty<LocalDate> expirationDateProperty() {
		return this.expirationDate;
	}

	/**
	 * Message property.
	 *
	 * @return the string property
	 */
	public SimpleStringProperty messageProperty() {
		return this.message;
	}

	/**
	 * Validates all inputs and, if valid, adds stock to the inventory.
	 *
	 * @return true if stock was successfully added; false otherwise
	 */
	public boolean submit() {
		this.message.set("");

		int size;
		try {
			size = Integer.parseInt(this.sizeText.get());
			if (size <= 0) {
				this.message.set("Size must be a positive number.");
				return false;
			}
		} catch (NumberFormatException ex) {
			this.message.set("Please enter a valid number for size.");
			return false;
		}

		if (this.name.get() == null || this.name.get().isBlank()) {
			this.message.set("Please enter a name for the stock.");
			return false;
		}

		if (this.condition.get() == null) {
			this.message.set("Please select a condition.");
			return false;
		}

		if (this.quality.get() == null) {
			this.message.set("Please select a special quality.");
			return false;
		}

		Date expiration = null;
		if (this.quality.get() == SpecialQualitys.PERSHIABLE) {
			LocalDate local = this.expirationDate.get();
			if (local == null) {
				this.message.set("Perishable stock must have an expiration date.");
				return false;
			}
			expiration = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}

		Stock stock = new Stock(size, this.quality.get(), this.condition.get(), this.name.get(), expiration);

		if (!this.inventory.hasFreeSpaceFor(stock)) {
			this.message.set("No suitable storage compartment for that stock.");
			return false;
		}

		this.message.set("Stock added successfully!");
		return true;
	}
}
