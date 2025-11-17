package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class LandingPageCrewmateCodeBehind.
 * 
 * @author jr00381
 * @version fall 2025
 */
public class LandingPageCrewmateCodeBehind {

	/** The add stock button default. */
	@FXML
	private Button addStockButtonDefault;

	/** The current user. */
	private Users currentUser;

	/**
	 * Sets the logged in user.
	 *
	 * @param user the new logged in user
	 */
	public void setLoggedInUser(Users user) {
		this.currentUser = user;
	}

	/**
	 * Adds the stock to stock inventory.
	 *
	 * @param event the event
	 */
	@FXML
	void addStockToStockInventory(ActionEvent event) {
		try {
			String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/addStockPage.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			AddStockPageCodeBehind controller = loader.getController();

			Role role = null;
			if (this.currentUser != null) {
				role = this.currentUser.getRole();
			}

			controller.setHomeContext(role, this.currentUser);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

}
