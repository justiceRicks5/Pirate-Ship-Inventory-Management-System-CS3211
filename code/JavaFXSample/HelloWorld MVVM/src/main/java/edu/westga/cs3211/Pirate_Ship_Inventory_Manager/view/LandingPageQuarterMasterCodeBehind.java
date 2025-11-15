package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel.LandPageQuarterMasterViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LandingPageQuarterMasterCodeBehind {

    @FXML
    private Button addStockButton;

    @FXML
    private Button viewButton;

    @FXML
    private Label welcomeMessage;

    private Users username;

    private final LandPageQuarterMasterViewModel viewModel;

    public LandingPageQuarterMasterCodeBehind() {
        this.viewModel = new LandPageQuarterMasterViewModel();
    }

    @FXML
    private void initialize() {
        // Bind label to ViewModel property
        this.welcomeMessage.textProperty().bind(this.viewModel.welcomeMessageProperty());
    }

    /**
     * Called by LoginCodeBehind after FXML is loaded.
     */
    public void setLoggedInUser(Users user) {
        this.username = user;

        System.out.println("LandingPageQuarterMaster: setLoggedInUser called with "
                           + (user != null ? user.getName() : "null"));

        
        this.viewModel.setCurrentUser(user);
    }

    @FXML
    void AddStock(ActionEvent event) {
        try {
            String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/addStockPage.fxml";
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

    @FXML
    void viewStock(ActionEvent event) {
        
    }

    @FXML
    void Logout(ActionEvent event) {
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
