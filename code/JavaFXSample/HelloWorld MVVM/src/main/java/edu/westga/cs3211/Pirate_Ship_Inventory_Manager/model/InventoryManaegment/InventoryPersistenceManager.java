package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryPersistenceManager.
 * 
 * @author jr00381
 * @version fall 2025
 */
public final class InventoryPersistenceManager {

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "inventory.tsv";

	/**
	 * Instantiates a new inventory persistence manager.
	 */
	private InventoryPersistenceManager() {
	}

	/**
	 * Save.
	 *
	 * @param inventory the inventory
	 */
	public static void save(Inventory inventory) {
		if (inventory == null) {
			return;
		}

		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
			for (Stock stock : inventory.getAllStock()) {
				int size = stock.getSize();
				String quality = stock.getSpecialQuality().name();
				String condition = stock.getCondition().name();

				String name = stock.getName();
				if (name == null) {
					name = "";
				} else {
					
					name = name.replace("\t", " ");
				}

				Date exp = stock.getExpirationDate();

				long millis;
				if (exp == null) {
					millis = -1L;
				} else {
					millis = exp.getTime();
				}

				String compartmentQuality = "";
				Compartment comp = stock.getCompartment();
				if (comp != null && comp.getSpecialQualitys() != null) {
					compartmentQuality = comp.getSpecialQualitys().name();
				}

				out.printf("%d\t%s\t%s\t%s\t%d\t%s%n", size, quality, condition, name, millis, compartmentQuality);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Load.
	 *
	 * @return the inventory
	 */
	public static Inventory load() {
		Inventory inventory = new Inventory();

		File file = new File(FILE_NAME);
		if (!file.exists()) {
			return inventory;
		}

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] parts = line.split("\t");

				if (parts.length < 5) {
					continue;
				}

				int size = Integer.parseInt(parts[0]);
				SpecialQualitys quality = SpecialQualitys.valueOf(parts[1]);
				Condtion condition = Condtion.valueOf(parts[2]);
				String name = parts[3];

				long millis = Long.parseLong(parts[4]);

				Date exp = null;
				if (millis >= 0) {
				    exp = new Date(millis);
				}

				String compartmentQualityText = "";
				if (parts.length >= 6) {
				    compartmentQualityText = parts[5].trim();
				}

				Stock stock = new Stock(size, quality, condition, name, exp);

				if (!compartmentQualityText.isEmpty()) {
					try {
						SpecialQualitys compQuality = SpecialQualitys.valueOf(compartmentQualityText);

						List<Compartment> compartments = inventory.getCompartments();

						Compartment match = null;
						for (Compartment comp : compartments) {
							if (comp.getSpecialQualitys() == compQuality) {
								match = comp;
								break;
							}
						}

						if (match != null) {
							stock.setCompartment(match);
							inventory.addStockToCompartment(stock, match);
						}

					} catch (IllegalArgumentException ex) {

					}
				}

				inventory.addStock(stock);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return inventory;
	}
}
