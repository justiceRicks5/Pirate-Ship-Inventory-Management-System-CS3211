package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.InventoryHolder;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewStockCodeBehind.
 * 
 * @author jr00381
 * @version fall 2025
 */
public class ViewStockCodeBehind {

	/** The stock table view. */
	@FXML
	private TableView<Stock> stockTableView;

	/** The view name. */
	@FXML
	private TableColumn<Stock, String> viewName;

	/** The view size. */
	@FXML
	private TableColumn<Stock, Integer> viewSize;

	/** The view condtion. */
	@FXML
	private TableColumn<Stock, Condtion> viewCondtion;

	/** The view special qualitys. */
	@FXML
	private TableColumn<Stock, SpecialQualitys> viewSpecialQualitys;

	/** The view expiration date. */
	@FXML
	private TableColumn<Stock, String> viewExpirationDate;

	/** The view compartment. */
	@FXML
	private TableColumn<Stock, String> viewCompartment;
	@FXML
	private TableColumn<Stock, String> timeAdded;
	@FXML
	private CheckBox filterRegularCheck;
	@FXML
	private CheckBox filterPerishableCheck;
	@FXML
	private CheckBox filterLiquidCheck;
	@FXML
	private CheckBox filterFlammableCheck;

	@FXML
	private ListView<String> filterCrewListView;

	@FXML
	private DatePicker filterStartDate;
	@FXML
	private DatePicker filterEndDate;

	/** The inventory. */
	private final Inventory inventory = InventoryHolder.getInventory();

	/** The home role. */
	private Role homeRole;

	/** The current user. */
	private Users currentUser;
	private ObservableList<Stock> allStockList;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		this.viewName.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.viewSize.setCellValueFactory(new PropertyValueFactory<>("size"));
		this.viewCondtion.setCellValueFactory(new PropertyValueFactory<>("condition"));
		this.viewSpecialQualitys.setCellValueFactory(new PropertyValueFactory<>("specialQuality"));
		this.viewExpirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDateString"));
		this.viewCompartment.setCellValueFactory(new PropertyValueFactory<>("compartmentName"));
		this.timeAdded.setCellValueFactory(new PropertyValueFactory<>("addedTimeString"));
		this.refreshTable();
		this.populateCrewFilterOptions();
	}

	/**
	 * sets the inventory .
	 *
	 * @param inventory a inventroty
	 */
	public void setInventory(Inventory inventory) {
		this.refreshTable();
	}

	/**
	 * Sets the home context.
	 *
	 * @param role the role
	 * @param user the user
	 */
	public void setHomeContext(Role role, Users user) {
		this.homeRole = role;
		this.currentUser = user;
	}

	private void populateCrewFilterOptions() {
		if (this.filterCrewListView == null) {
			return;
		}

		List<String> names = this.inventory.getAllStock().stream().map(Stock::getAddedBy).filter(Objects::nonNull)
				.filter(s -> !s.isBlank()).distinct().sorted().collect(Collectors.toList());

		this.filterCrewListView.setItems(FXCollections.observableArrayList(names));
		this.filterCrewListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
	}

	private void loadAllStockNewestFirst() {

		List<Stock> allStock = new ArrayList<>(this.inventory.getAllStock());

		Collections.reverse(allStock);

		this.allStockList = FXCollections.observableArrayList(allStock);

		this.stockTableView.setItems(this.allStockList);
	}

	/**
	 * Refresh table.
	 */
	private void refreshTable() {
		this.loadAllStockNewestFirst();
	}

	private Set<SpecialQualitys> getSelectedQualities() {
		Set<SpecialQualitys> selected = EnumSet.noneOf(SpecialQualitys.class);

		if (this.filterRegularCheck != null && this.filterRegularCheck.isSelected()) {
			selected.add(SpecialQualitys.REGULAR);
		}
		if (this.filterPerishableCheck != null && this.filterPerishableCheck.isSelected()) {
			selected.add(SpecialQualitys.PERSHIABLE);
		}
		if (this.filterLiquidCheck != null && this.filterLiquidCheck.isSelected()) {
			selected.add(SpecialQualitys.LIQUID);
		}
		if (this.filterFlammableCheck != null && this.filterFlammableCheck.isSelected()) {
			selected.add(SpecialQualitys.FLAMMABLE);
		}

		return selected;
	}

	/**
	 * Adds the stock.
	 *
	 * @param event the event
	 */
	@FXML
	void addStock(ActionEvent event) {
		try {
			String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/AddStockPage.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			AddStockPageCodeBehind controller = loader.getController();
			controller.setHomeContext(this.homeRole, this.currentUser);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
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
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Return home.
	 *
	 * @param event the event
	 */
	@FXML
	void returnHome(ActionEvent event) {
		try {
			String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/LandingPageQuarterMaster.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	@FXML
	void handleApplyFilters(ActionEvent event) {
		if (this.allStockList == null) {
			return;
		}

		List<Stock> filtered = new ArrayList<>(this.allStockList);

		Set<SpecialQualitys> qualities = this.getSelectedQualities();
		if (!qualities.isEmpty()) {
			filtered = filtered.stream().filter(stock -> qualities.contains(stock.getSpecialQuality()))
					.collect(Collectors.toList());
		}

		List<String> selectedCrew = new ArrayList<>();
		if (this.filterCrewListView != null) {
			selectedCrew.addAll(this.filterCrewListView.getSelectionModel().getSelectedItems());
		}

		if (!selectedCrew.isEmpty()) {
			filtered = filtered.stream().filter(stock -> {
				String addedBy = stock.getAddedBy();
				return addedBy != null && selectedCrew.contains(addedBy);
			}).collect(Collectors.toList());
		}

		final Optional<LocalDate> startDateOpt = Optional.ofNullable(this.filterStartDate)
				.map(datePicker -> datePicker.getValue());

		final Optional<LocalDate> endDateOpt = Optional.ofNullable(this.filterEndDate)
				.map(datePicker -> datePicker.getValue());

		LocalDate startDate = startDateOpt.orElse(null);
		LocalDate endDate = endDateOpt.orElse(null);

		if (startDate != null && endDate != null && !endDate.isAfter(startDate)) {
			System.out.println("Invalid time range: end must be after start.");
			return;
		}

		if (startDate != null || endDate != null) {
			filtered = filtered.stream().filter(stock -> {
				LocalDateTime added = stock.getAddedTime();
				if (added == null) {
					return false;
				}
				LocalDate addedDate = added.toLocalDate();

				if (startDate != null && addedDate.isBefore(startDate)) {
					return false;
				}
				if (endDate != null && addedDate.isAfter(endDate)) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
		}

		this.stockTableView.setItems(FXCollections.observableArrayList(filtered));
	}

	@FXML
	void handleClearFilters(ActionEvent event) {
		if (this.filterRegularCheck != null) {
			this.filterRegularCheck.setSelected(false);
		}
		if (this.filterPerishableCheck != null) {
			this.filterPerishableCheck.setSelected(false);
		}
		if (this.filterLiquidCheck != null) {
			this.filterLiquidCheck.setSelected(false);
		}
		if (this.filterFlammableCheck != null) {
			this.filterFlammableCheck.setSelected(false);
		}

		if (this.filterCrewListView != null) {
			this.filterCrewListView.getSelectionModel().clearSelection();
		}

		if (this.filterStartDate != null) {
			this.filterStartDate.setValue(null);
		}
		if (this.filterEndDate != null) {
			this.filterEndDate.setValue(null);
		}

		this.loadAllStockNewestFirst();
	}

}
