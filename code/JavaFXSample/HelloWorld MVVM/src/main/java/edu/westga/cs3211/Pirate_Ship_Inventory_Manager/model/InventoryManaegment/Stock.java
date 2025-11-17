package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Stock.
 * 
 * @author jr00381
 * @version fall 2025
 */
public class Stock {
	/** The Constant ADDED_TIME_FORMATTER. */
	private static final DateTimeFormatter ADDED_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	/** The name. */
	private String name;

	/** The size. */
	private int size;

	/** The condition. */
	private Condtion condition;

	/** The special quality. */
	private SpecialQualitys specialQuality;

	/** The expiration date. */
	private Date expirationDate;

	/** The compartment. */
	private Compartment compartment;

	/** The added by. */
	private String addedBy;

	/** The added time. */
	private LocalDateTime addedTime;

	/**
	 * Instantiates a new stock.
	 *
	 * @param size           the size
	 * @param specialQuality the special quality
	 * @param condition      the condition
	 * @param name           the name
	 * @param expirationDate the expiration date
	 */
	public Stock(int size, SpecialQualitys specialQuality, Condtion condition, String name, Date expirationDate) {
		super();
		this.size = size;
		this.specialQuality = specialQuality;
		this.condition = condition;
		this.name = name;
		this.expirationDate = expirationDate;
		this.addedTime = LocalDateTime.now();
	}

	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public Condtion getCondition() {
		return this.condition;
	}

	/**
	 * Sets the condition.
	 *
	 * @param condition the new condition
	 */
	public void setCondition(Condtion condition) {
		this.condition = condition;
	}

	/**
	 * Gets the compartment.
	 *
	 * @return the compartment
	 */
	public Compartment getCompartment() {
		return this.compartment;
	}

	/**
	 * Sets the compartment.
	 *
	 * @param compartment the new compartment
	 */
	public void setCompartment(Compartment compartment) {
		this.compartment = compartment;
	}

	/**
	 * Gets expiration date as formatted string for TableView.
	 *
	 * @return formatted expiration date or "N/A"
	 */
	public String getExpirationDateString() {
		if (this.expirationDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(this.expirationDate);
		}
		return "N/A";
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the new expiration date
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	 * Sets the added time.
	 *
	 * @param addedTime the new added time
	 */
	public void setAddedTime(LocalDateTime addedTime) {
		this.addedTime = addedTime;
	}

	/**
	 * Gets compartment name for TableView.
	 *
	 * @return compartment name or "Not assigned"
	 */
	public String getCompartmentName() {
		if (this.compartment != null) {
			return this.compartment.toString();
		}
		return "Not assigned";
	}

	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public Condtion getCondtion() {
		return this.condition;
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
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
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
	 * Gets the added time.
	 *
	 * @return the added time
	 */
	public String getAddedTimeString() {
		if (this.addedTime == null) {
			return "";
		}
		return this.addedTime.format(ADDED_TIME_FORMATTER);
	}

	/**
	 * Gets the added by.
	 *
	 * @return the added by
	 */
	public String getAddedBy() {
		return this.addedBy;
	}

	/**
	 * Sets the added by.
	 *
	 * @param addedBy the new added by
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * Gets the added time.
	 *
	 * @return the added time
	 */
	public LocalDateTime getAddedTime() {
		return this.addedTime;
	}

	/**
	 * Gets the added time formatter.
	 *
	 * @return the added time formatter
	 */
	public static DateTimeFormatter getAddedTimeFormatter() {
		return ADDED_TIME_FORMATTER;
	}

}