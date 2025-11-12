package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.DemoStores;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginCodeBehind {

	@FXML
	private Label LoginGettingLabel; 
	@FXML
	private Label errorLabel; 

	@FXML
	private TextField nameTextField; 
	@FXML
	private TextField nameTextField1; 
	@FXML
	private Button submitButton;

	private final LoginViewModel viewModel;

	public LoginCodeBehind() {
		
		CredentialStore store = DemoStores.sampleStore(); 
		this.viewModel = new LoginViewModel(store);
	}

	@FXML
	void initialize() {
		// Bind UI <-> ViewModel inputs
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
		this.nameTextField1.textProperty().bindBidirectional(this.viewModel.passwordProperty());

		// Bind status line and disable state
		this.LoginGettingLabel.textProperty().bind(this.viewModel.statusProperty());
		this.submitButton.disableProperty().bind(this.viewModel.loginDisabledProperty());

		// Start with no error text
		this.errorLabel.setText("");
	}

	@FXML
	void handleSubmit(ActionEvent event) {
		// Clear any previous error
		this.errorLabel.setText("");

		// Try to log in via the VM
		boolean ok = this.viewModel.login();

		// If failed, show an error; success message is already set by VM statusProperty
		if (!ok) {
			this.errorLabel.setText("Invalid username or password.");
		}
	}
}
