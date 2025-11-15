package edu.westga.cs3211.Pirate_Ship_Inventory_Manager.Test.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Role;
import edu.westga.cs3211.Pirate_Ship_Inventory_Manager.model.UserManagement.Users;

class TestConstructor {

	@Test
	void testConstrutorIntializeFields() {
		String name = "Justice";
		String password = "SecurePassword123";
		Role role = Role.QUATERMASTER;
		
		Users users = new Users(name,password,role);
		
		assertEquals(name, users.getName(),"name should be intialzed ");
		assertEquals(password, users.getPassword(),"name should be intialzed ");
		assertEquals(role, users.getRole(),"name should be intialzed ");
	}

}
