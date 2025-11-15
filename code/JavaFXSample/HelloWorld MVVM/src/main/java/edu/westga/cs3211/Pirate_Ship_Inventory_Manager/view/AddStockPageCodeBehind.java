package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * The Class AddStockPageCodeBehind.
 * @author jr00381
 * @version Fall 2025
 */
public class AddStockPageCodeBehind {

	/** The Date selector. */
	@FXML
	private DatePicker DateSelector;

	/** The date label. */
	@FXML
	private Label dateLabel;

	/** The perishable. */
	@FXML
	private CheckBox perishable;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		 this.dateLabel.setVisible(false);
	        this.dateLabel.setManaged(false);  

	        this.DateSelector.setVisible(false);
	        this.DateSelector.setManaged(false);

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
	        this.DateSelector.setVisible(selected);

	       
	        this.dateLabel.setManaged(selected);
	        this.DateSelector.setManaged(selected);
	}

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


    @FXML
    void returnHome(ActionEvent event) {
    	 try {
             String fxmlPath = "/edu/westga/cs3211/Pirate_Ship_Inventory_Manager/view/LandingPageQuarterMaster.fxml";
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
}
