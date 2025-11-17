package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Inventory;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.InventoryHolder;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Stock;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.Condtion;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment.SpecialQualitys;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewStockCodeBehind.
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

	/** The inventory. */
	private final Inventory inventory = InventoryHolder.getInventory();

	/** The home role. */
	private Role homeRole;

	/** The current user. */
	private Users currentUser;

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
		this.refreshTable();
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

	/**
	 * Refresh table.
	 */
	private void refreshTable() {
		List<Stock> allStock = new ArrayList<>(this.inventory.getAllStock());

		Collections.reverse(allStock);

		this.stockTableView.setItems(FXCollections.observableArrayList(allStock));
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
}
