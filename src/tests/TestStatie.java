package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Adresa;
import classes.Statie;

public class TestStatie {
	Statie s;
	String expectedNume = "Statie";
	String expectedVecinStanga = "Vecin stanga";
	String expectedVecinDreapta = "Vecin dreapta";

	@Before
	public void setUp() {
		Adresa a = new Adresa("strada", "10bis");
		s = new Statie(expectedNume, expectedVecinStanga, 22,
				expectedVecinDreapta, 33, a);
	}

	@Test
	public void testName() {
		assertEquals("Numele nu e aceeasi", expectedNume, s.getNume());
	}

	@Test
	public void testVecinStanga() {
		assertEquals("Vecinul stanga nu este acelasi", expectedVecinStanga,
				s.getNumeVecinStanga());
	}

	@Test
	public void testVecinDreapta() {
		assertEquals("VEcinul din dreapta nu este acelasi",
				expectedVecinDreapta, s.getNumeVecinDreapta());
	}

	@Test
	public void testDistantaStanga() {
		assertEquals("Distanta din stanga nu este aceeasi", 22,
				s.getDistantaVecinStanga());
	}

	@Test
	public void testDistantaDreapta() {
		assertEquals("Distanta din dreapta nu este aceeasi", 33,
				s.getDistantaVecinDreapta());
	}

}
