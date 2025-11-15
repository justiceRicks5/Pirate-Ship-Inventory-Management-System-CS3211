package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.DemoStores;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The Class LoginCodeBehind.
 * 
 * @author jr00381
 * @version Fall 2025
 */
public class LoginCodeBehind {

	/** The Login getting label. */
	@FXML
	private Label LoginGettingLabel;

	/** The error label. */
	@FXML
	private Label errorLabel;

	/** The name text field. */
	@FXML
	private TextField nameTextField;

	/** The name text field 1. */
	@FXML
	private TextField nameTextField1;

	/** The submit button. */
	@FXML
	private Button submitButton;

	/** The view model. */
	private final LoginViewModel viewModel;

	/**
	 * Instantiates a new login code behind.
	 */
	public LoginCodeBehind() {

		CredentialStore store = DemoStores.sampleStore();
		this.viewModel = new LoginViewModel(store);
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
		this.nameTextField1.textProperty().bindBidirectional(this.viewModel.passwordProperty());

		this.LoginGettingLabel.textProperty().bind(this.viewModel.statusProperty());
		this.submitButton.disableProperty().bind(this.viewModel.loginDisabledProperty());

		this.errorLabel.setText("");
	}

	/**
	 * Handle submit.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSubmit(ActionEvent event) {
		this.errorLabel.setText("");

		boolean ok = this.viewModel.login();

		if (!ok) {
			this.errorLabel.setText("Invalid username or password.");
			return;
		}

		try {
			var user = this.viewModel.getCurrentUser();
			var role = user.getRole();

			String fxmlName;
			switch (role) {
			case QUATERMASTER:
				fxmlName = "LandingPageQuarterMaster.fxml";
				break;
			case CREWMATE:
			default:
				fxmlName = "LandingPageCrewmate.fxml";
				break;
			}

			java.net.URL fxmlUrl = getClass().getResource(fxmlName);
			System.out.println("Loading landing page: " + fxmlUrl);

			if (fxmlUrl == null) {
				throw new IllegalStateException("FXML not found: " + fxmlName);
			}

			FXMLLoader loader = new FXMLLoader(fxmlUrl);
			javafx.scene.Parent root = loader.load();

			if (role == edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role.QUATERMASTER) {
				LandingPageQuarterMasterCodeBehind controller = loader.getController();
				controller.setLoggedInUser(user);
			} else {

			}

			javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene()
					.getWindow();
			stage.setScene(new javafx.scene.Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			this.errorLabel.setText("Error loading landing page: " + e.getClass().getSimpleName());
		}
	}
}
