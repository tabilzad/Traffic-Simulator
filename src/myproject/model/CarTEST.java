package myproject.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarTEST {
	Car c = new Car();
	Road r = ObjectFactory.createRoad(ObjectFactory.sink());

	@Test
	public void GetSetTest() {

		c.setFrontPosition((float) 50.0);
		double b = c.getFrontPosition();
		assertEquals(50.0, b, 0.0f);

	}

	@Test
	public void RunTest() {
		assertEquals((float) 0.0, c.getFrontPosition(), 0.0f);
		assertTrue(r.receive(c, c.getFrontPosition()));
		double position = c.getFrontPosition();
		c.run();
		System.out.println("Position: "+ position);
		assertTrue(c.getFrontPosition() > position);
		position = c.getFrontPosition();
		System.out.println("Position: "+ position);
		c.run();
		assertTrue(c.getFrontPosition() > position);

	}
}
