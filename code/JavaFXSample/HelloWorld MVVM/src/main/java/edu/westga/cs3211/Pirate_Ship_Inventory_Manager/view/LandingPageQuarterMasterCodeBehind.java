package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LandPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class LandingPageQuarterMasterCodeBehind.
 * 
 * @author jr00381
 * @version fall 2025
 */
public class LandingPageQuarterMasterCodeBehind {

	/** The add stock button. */
	@FXML
	private Button addStockButton;

	/** The view button. */
	@FXML
	private Button viewButton;

	/** The welcome message. */
	@FXML
	private Label welcomeMessage;

	/** The username. */
	private Users username;

	/** The view model. */
	private final LandPageViewModel viewModel;

	/**
	 * Instantiates a new landing page quarter master code behind.
	 */
	public LandingPageQuarterMasterCodeBehind() {
		this.viewModel = new LandPageViewModel();
	}

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		this.welcomeMessage.textProperty().bind(this.viewModel.welcomeMessageProperty());
	}

	/**
	 * Called by LoginCodeBehind after FXML is loaded.
	 *
	 * @param user the new logged in user
	 */
	public void setLoggedInUser(Users user) {
		this.username = user;
		if (user != null) {
			System.out.println("LandingPageQuarterMaster: setLoggedInUser called with " + user.getName());
			this.viewModel.setCurrentUser(user);
		} else {
			System.out.println("LandingPageQuarterMaster: setLoggedInUser called with null");
		}
	}

	@FXML
	void addStock(ActionEvent event) {
		try {
			if (this.username == null) {
				System.out.println("No logged-in user in LandingPageQuarterMaster; sending back to login.");

				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/loginPage.fxml"));
				Parent root = loader.load();
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
				return;
			}

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/AddStockPage.fxml"));
			Parent root = loader.load();

			AddStockPageCodeBehind controller = loader.getController();
			controller.setHomeContext(this.username.getRole(), this.username);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
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

			ViewStockCodeBehind controller = loader.getController();

			Role userRole = null;
			if (this.username != null) {
			    userRole = this.username.getRole();
			}

			controller.setHomeContext(userRole, this.username);

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
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
