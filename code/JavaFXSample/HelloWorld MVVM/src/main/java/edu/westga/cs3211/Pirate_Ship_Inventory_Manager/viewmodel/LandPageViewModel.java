package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.viewmodel;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 * ViewModel for the Quartermaster landing page.
 * Holds the current user and exposes a welcome message.
 *
 * @author jr00381
 * @version Fall 2025
 */
public class LandPageViewModel {

    private Users currentUser;

    private final ReadOnlyStringWrapper welcomeMessage =
            new ReadOnlyStringWrapper("Welcome aboard!");

    /**
     * Sets the logged-in user and updates the welcome message.
     *
     * @param user the logged-in user
     */
    public void setCurrentUser(Users user) {
        this.currentUser = user;

        if (this.currentUser != null) {
            this.welcomeMessage.set(
                "Welcome aboard, " + this.currentUser.getName() + "!"
            );
        } else {
            this.welcomeMessage.set("Welcome aboard!");
        }
    }

    /**
     * Gets the current user.
     *
     * @return the current user, or null if none is set
     */
    public Users getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Read-only property for the welcome message.
     *
     * @return the welcome message property
     */
    public ReadOnlyStringProperty welcomeMessageProperty() {
        return this.welcomeMessage.getReadOnlyProperty();
    }

    /**
     * Convenience getter for the welcome message text.
     *
     * @return the welcome message string
     */
    public String getWelcomeMessage() {
        return this.welcomeMessage.get();
    }
}
