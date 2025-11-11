package edu.westga.cs3211.helloworld.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.helloworld.model.People;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new People(null);
		});
	}

	@Test
	void testValidName() {
		People result = new People("Bob");

		assertEquals("Bob", result.getName(), "checking the name of the person");
	}

}
