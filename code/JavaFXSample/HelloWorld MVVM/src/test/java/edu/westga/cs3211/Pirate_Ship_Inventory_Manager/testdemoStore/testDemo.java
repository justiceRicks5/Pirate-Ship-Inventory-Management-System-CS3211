package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.testdemoStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.CredentialStore;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.DemoStores;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

public class testDemo {
	 @Test
	    void testSampleStoreReturnsNonNullStore() {
	        // Arrange & Act
	        CredentialStore store = DemoStores.sampleStore();

	        // Assert
	        assertNotNull(store);
	    }

	    @Test
	    void testSampleStoreContainsExpectedUsers() {
	        // Arrange
	        CredentialStore store = DemoStores.sampleStore();

	        // Act
	        Optional<Users> aliceResult = store.authenticate("alice", "Password123!");
	        Optional<Users> bobResult = store.authenticate("bob", "letmein");

	        // Assert
	        assertTrue(aliceResult.isPresent(), "Alice should be present in the sample store");
	        assertEquals(Role.CREWMATE, aliceResult.get().getRole());

	        assertTrue(bobResult.isPresent(), "Bob should be present in the sample store");
	        assertEquals(Role.QUATERMASTER, bobResult.get().getRole());
	    }

	    @Test
	    void testPrivateConstructorIsReachableViaReflection() throws Exception {
	        // Arrange
	        Constructor<DemoStores> constructor = DemoStores.class.getDeclaredConstructor();
	        constructor.setAccessible(true);

	        // Act
	        DemoStores instance = constructor.newInstance();

	        // Assert
	        assertNotNull(instance);
	    }
}
