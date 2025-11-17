module edu.westga.cs3211.helloworld {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.sql;

	opens edu.westga.cs3211.Pirate_Ship_Inventory_Manager.view to javafx.fxml;
	opens edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.InventoryManaegment to javafx.base;

	exports edu.westga.cs3211.Pirate_Ship_Inventory_Manager;
}
