package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Compartment;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.InventoryHolder;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class AddStockPageCodeBehind.
 * 
 * @author jr00381
 * @version Fall 2025
 */
public class AddStockPageCodeBehind {

	/** The Date selector. */
	@FXML
	private DatePicker dateSelector;

	/** The compartment id combo box. */
	@FXML
	private ComboBox<Compartment> compartmentIdComboBox;

	/** The condition label. */
	@FXML
	private Label conditionLabel;

	/** The condtion combo box. */
	@FXML
	private ComboBox<Condtion> condtionComboBox;

	/** The date label. */
	@FXML
	private Label dateLabel;

	/** The flammable check. */
	@FXML
	private CheckBox flammableCheck;

	/** The liquid check. */
	@FXML
	private CheckBox liquidCheck;

	/** The name label. */
	@FXML
	private Label nameLabel;

	/** The perishable. */
	@FXML
	private CheckBox perishable;

	/** The size field. */
	@FXML
	private TextField sizeField;

	/** The size label. */
	@FXML
	private Label sizeLabel;

	/** The special quality label. */
	@FXML
	private Label specialQualityLabel;

	/** The view stock button. */
	@FXML
	private Button viewStockButton;

	/** The compartment label. */
	@FXML
	private Label compartmentLabel;

	/** The name text field. */
	@FXML
	private TextField nameTextField;

	/** The home role. */
	private Role homeRole;

	/** The current logged-in user. */
	private Users currentUser;

	/** The ship's inventory (with 4 compartments inside). */
	private final Inventory inventory = InventoryHolder.getInventory();

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {

		this.dateLabel.setVisible(false);
		this.dateLabel.setManaged(false);

		this.dateSelector.setVisible(false);
		this.dateSelector.setManaged(false);

		if (this.viewStockButton != null) {
			this.viewStockButton.setVisible(false);
			this.viewStockButton.setManaged(false);
		}

		if (this.compartmentLabel != null) {
			this.compartmentLabel.setVisible(false);
			this.compartmentLabel.setManaged(false);
		}

		if (this.compartmentIdComboBox != null) {
			this.compartmentIdComboBox.setVisible(false);
			this.compartmentIdComboBox.setManaged(false);
			this.compartmentIdComboBox.setPromptText("Select a compartment");
		}

		if (this.condtionComboBox != null) {
			this.condtionComboBox.setItems(FXCollections.observableArrayList(Condtion.values()));
			this.condtionComboBox.setPromptText("Select condition");
		}

		if (this.compartmentIdComboBox != null) {
			this.compartmentIdComboBox.setPromptText("Select a compartment");
		}
	}

	/**
	 * Called by landing page before showing AddStock.
	 *
	 * @param role the role
	 * @param user the user
	 */
	public void setHomeContext(Role role, Users user) {
		this.homeRole = role;
		this.currentUser = user;

		boolean isQuartermaster = (role == Role.QUATERMASTER);

		if (this.viewStockButton != null) {
			this.viewStockButton.setVisible(isQuartermaster);
			this.viewStockButton.setManaged(isQuartermaster);
		}
	}

	/**
	 * Gets the selected special quality.
	 *
	 * @return the selected special quality
	 */
	private SpecialQualitys getSelectedSpecialQuality() {
		if (this.perishable.isSelected()) {
			return SpecialQualitys.PERSHIABLE;
		} else if (this.liquidCheck.isSelected()) {
			return SpecialQualitys.LIQUID;
		} else if (this.flammableCheck.isSelected()) {
			return SpecialQualitys.FLAMMABLE;
		} else {
			return SpecialQualitys.REGULAR;
		}
	}

	/**
	 * Populate compartment combo box.
	 *
	 * @param size    the size
	 * @param quality the quality
	 */
	private void populateCompartmentComboBox(int size, SpecialQualitys quality) {
		if (this.compartmentIdComboBox == null) {
			System.out.println("compartmentIdComboBox is null (FXML wiring issue).");
			return;
		}

		System.out.println("populateCompartmentComboBox called with size = " + size + ", quality = " + quality);

		List<Compartment> allCompartments = this.inventory.getCompartments();
		System.out.println("Inventory has " + allCompartments.size() + " compartments:");

		List<Compartment> suitable = new ArrayList<>();

		for (Compartment compartment : allCompartments) {
			SpecialQualitys compQuality = compartment.getSpecialQualitys();
			int freeSpace = compartment.getFreeSpace();

			boolean qualityOk = (compQuality == quality);
			boolean spaceOk = (freeSpace >= size);

			System.out.println("  Compartment -> quality=" + compQuality + ", freeSpace=" + freeSpace + " | qualityOk="
					+ qualityOk + ", spaceOk=" + spaceOk);

			if (qualityOk && spaceOk) {
				suitable.add(compartment);
			}
		}

		System.out.println("Suitable compartments found: " + suitable.size());
		this.compartmentIdComboBox.getItems().setAll(suitable);
	}

	/**
	 * Setup compartments for current input.
	 *
	 * @return true, if successful
	 */
	private boolean setupCompartmentsForCurrentInput() {
		String sizeText = this.sizeField.getText();
		if (sizeText == null || sizeText.isBlank()) {
			System.out.println("Size is required before finding compartments.");
			return false;
		}

		int size;
		try {
			size = Integer.parseInt(sizeText.trim());
			if (size <= 0) {
				System.out.println("Size must be positive.");
				return false;
			}
		} catch (NumberFormatException ex) {
			System.out.println("Invalid size: " + sizeText);
			return false;
		}

		SpecialQualitys quality = this.getSelectedSpecialQuality();

		this.populateCompartmentComboBox(size, quality);

		if (this.compartmentLabel != null) {
			this.compartmentLabel.setVisible(true);
			this.compartmentLabel.setManaged(true);
		}
		if (this.compartmentIdComboBox != null) {
			this.compartmentIdComboBox.setVisible(true);
			this.compartmentIdComboBox.setManaged(true);
		}

		return true;
	}

	private Compartment prepareOrGetSelectedCompartment() {
		Compartment selected = this.compartmentIdComboBox.getValue();
		if (selected != null) {
			return selected;
		}

		boolean ok = this.setupCompartmentsForCurrentInput();
		if (!ok) {
			System.out.println("Cannot prepare compartments – check size and special quality.");
			return null;
		}

		System.out.println("Compartments prepared. Select a compartment, then press Submit again.");
		return null;
	}

	private Integer readSize() {
		String sizeText = this.sizeField.getText();

		if (sizeText == null || sizeText.isBlank()) {
			System.out.println("Size is required.");
			return null;
		}

		try {
			int size = Integer.parseInt(sizeText.trim());
			if (size <= 0) {
				System.out.println("Size must be positive.");
				return null;
			}
			return size;
		} catch (NumberFormatException ex) {
			System.out.println("Invalid size: " + sizeText);
			return null;
		}
	}

	private Condtion readCondition() {
		Condtion condition = this.condtionComboBox.getValue();
		if (condition == null) {
			System.out.println("Please select a condition.");
			return null;
		}
		return condition;
	}

	private SpecialQualitys readSpecialQuality() {
		SpecialQualitys quality = this.getSelectedSpecialQuality();
		if (quality == null) {
			System.out.println("Please select a special quality.");
			return null;
		}
		return quality;
	}

	private String readName() {
		String name = this.nameTextField.getText();
		if (name == null) {
			System.out.println("Please enter a name for the stock.");
			return null;
		}

		String trimmed = name.trim();
		if (trimmed.isEmpty()) {
			System.out.println("Please enter a name for the stock.");
			return null;
		}

		return trimmed;
	}

	private Date readExpirationDate() {
		if (!this.perishable.isSelected()) {
			return null;
		}

		java.time.LocalDate expirationDate = this.dateSelector.getValue();
		if (expirationDate == null) {
			System.out.println("Perishable stock must have an expiration date.");
			return null;
		}

		return java.sql.Date.valueOf(expirationDate);
	}

	private void resetForm() {
		this.sizeField.clear();
		this.nameTextField.clear();
		this.condtionComboBox.setValue(null);
		this.perishable.setSelected(false);
		this.dateSelector.setValue(null);

		this.dateLabel.setVisible(false);
		this.dateLabel.setManaged(false);
		this.dateSelector.setVisible(false);
		this.dateSelector.setManaged(false);

		this.compartmentIdComboBox.getItems().clear();
		this.compartmentIdComboBox.setValue(null);
		this.compartmentIdComboBox.setVisible(false);
		this.compartmentIdComboBox.setManaged(false);
		this.compartmentLabel.setVisible(false);
		this.compartmentLabel.setManaged(false);
	}

	/**
	 * Handle add stock.
	 *
	 * @param event the event
	 */
	@FXML
	void handleAddStock(ActionEvent event) {
		this.setupCompartmentsForCurrentInput();
	}

	/**
	 * Handle compartment.
	 *
	 * @param event the event
	 */
	@FXML
	void handleCompartment(ActionEvent event) {
		Compartment selected = this.compartmentIdComboBox.getValue();
		if (selected != null) {
			System.out.println("Compartment selected: " + selected.getSpecialQualitys() + ", free space: "
					+ selected.getFreeSpace());
		}
	}

	/**
	 * Handle condtions.
	 *
	 * @param event the event
	 */
	@FXML
	void handleCondtions(ActionEvent event) {
		Condtion condition = this.condtionComboBox.getValue();
		System.out.println("Condition selected: " + condition);
	}

	/**
	 * Handle submit.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSubmit(ActionEvent event) {
		Compartment selectedCompartment = this.prepareOrGetSelectedCompartment();
		if (selectedCompartment == null) {
			
			return;
		}

		Integer size = this.readSize();
		if (size == null) {
			return;
		}

		Condtion condition = this.readCondition();
		if (condition == null) {
			return;
		}

		SpecialQualitys quality = this.readSpecialQuality();
		if (quality == null) {
			return;
		}

		String name = this.readName();
		if (name == null) {
			return;
		}

		Date expirationDate = this.readExpirationDate();
		if (this.perishable.isSelected() && expirationDate == null) {
			
			return;
		}

		Stock newStock = new Stock(size, quality, condition, name, expirationDate);
		newStock.setCompartment(selectedCompartment);

		if (!selectedCompartment.canStore(newStock)) {
			System.out.println("Selected compartment no longer has enough space or wrong quality.");
			return;
		}

		this.inventory.addStock(newStock);
		this.inventory.addStockToCompartment(newStock, selectedCompartment);

		System.out.println("Stock added successfully: " + name);

		InventoryHolder.save();

		this.resetForm();
	}

	/**
	 * Logout.
	 *
	 * @param event the event
	 */
	@FXML
	void logout(ActionEvent event) {
		try {
			String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/loginPage.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Perishable date.
	 *
	 * @param event the event
	 */
	@FXML
	void perishableDate(ActionEvent event) {
		boolean selected = this.perishable.isSelected();

		this.dateLabel.setVisible(selected);
		this.dateSelector.setVisible(selected);

		this.dateLabel.setManaged(selected);
		this.dateSelector.setManaged(selected);
	}

	/**
	 * Return home.
	 *
	 * @param event the event
	 */
	@FXML
	void returnHome(ActionEvent event) {
		try {
			String fxmlPath;

			if (this.homeRole == Role.QUATERMASTER) {
				fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/LandingPageQuarterMaster.fxml";
			} else {
				fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/LandingPageCrewmate.fxml";
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			if (this.homeRole == Role.QUATERMASTER) {
				LandingPageQuarterMasterCodeBehind controller = loader.getController();
				controller.setLoggedInUser(this.currentUser);
			} else {
				LandingPageCrewmateCodeBehind controller = loader.getController();
				controller.setLoggedInUser(this.currentUser);
			}

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	/**
	 * View stock.
	 *
	 * @param event the event
	 */
	@FXML
	void viewStock(ActionEvent event) {
		try {
			String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/viewStock.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
