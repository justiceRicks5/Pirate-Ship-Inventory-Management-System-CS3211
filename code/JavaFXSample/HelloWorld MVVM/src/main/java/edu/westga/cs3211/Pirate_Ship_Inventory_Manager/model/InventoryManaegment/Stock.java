package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

import java.util.Date;

/**
 * The Class Stock.
 * @author jr00381
 * @version fall 2025
 */
public class Stock {

	/** The size. */
	private int size;

	/** The special quality. */
	private SpecialQualitys specialQuality;

	/** The condtion. */
	private Condtion condtion;

	/** The name. */
	private String name;

	/** The expiration date. */
	private Date expirationDate;

	/**
	 * Instantiates a new stock.
	 *
	 * @param size           the size
	 * @param specialQuality the special quality
	 * @param condtion       the condtion
	 * @param name           the name
	 * @param expirationDate the expiration date
	 */
	public Stock(int size, SpecialQualitys specialQuality, Condtion condtion, String name, Date expirationDate) {
		super();
		this.size = size;
		this.specialQuality = specialQuality;
		this.condtion = condtion;
		this.name = name;
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public double getSize() {
		return this.size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the special quality.
	 *
	 * @return the special quality
	 */
	public SpecialQualitys getSpecialQuality() {
		return this.specialQuality;
	}

	/**
	 * Sets the special quality.
	 *
	 * @param specialQuality the new special quality
	 */
	public void setSpecialQuality(SpecialQualitys specialQuality) {
		this.specialQuality = specialQuality;
	}

	/**
	 * Checks if is condtion.
	 *
	 * @return true, if is condtion
	 */
	public Condtion isCondtion() {
		return this.condtion;
	}

	/**
	 * Sets the condtion.
	 *
	 * @param condtion the new condtion
	 */
	public void setCondtion(Condtion condtion) {
		this.condtion = condtion;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the expiration date.
	 *
	 * @return the expiration date
	 */
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the new expiration date
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
