package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LandingPageCrewmateCodeBehind {
	
    @FXML
    private Button addStockButtonDefault;

    @FXML
    void AddStockToStockInventory(ActionEvent event) {
    	try {
    		 String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/addStockPage.fxml";
             FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
             Parent root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }

}
