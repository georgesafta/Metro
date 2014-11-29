package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import classes.Adresa;
import classes.Harta;
import classes.Linie;
import classes.Statie;

public class TestHarta {

	Linie l;
	String expectedNume = "M1";
	Statie s1, s2;
	String expectedStatie1 = "Statie 1";
	Adresa a = new Adresa("strada", "10bis");
	Harta h;

	@Before
	public void setUp() {

		s1 = new Statie(expectedStatie1, "Statie 2", 22, null, 0, a);
		s2 = new Statie("Statie 2", null, 0, "Statie 1", 22, a);
		ArrayList<Statie> al = new ArrayList<Statie>();

		al.add(s1);
		al.add(s2);
		l = new Linie(al, expectedNume);

		ArrayList<Linie> all = new ArrayList<Linie>();
		all.add(l);
		h = new Harta(all);
	}

	@Test
	public void testCautareStatie() {
		Statie s = h.cautareStatie(expectedStatie1);
		assertEquals("Statia nu este aceeasi", s1, s);
	}

	
	@Test
	public void testGasireDrum() {
		int distance = h.gasireDrum(s1.getNume(), s2.getNume());
		assertEquals("Statia nu este aceeasi", 22, distance);
	}
	
	@Test
	public void testAddLinie() {
		Linie l2 = new Linie(null, "M2");
		h.adaugareLinie(l2);
		Linie s = h.cautareLinie("M2");
		assertEquals("Statia nu este aceeasi", l2, s);
	}
}
