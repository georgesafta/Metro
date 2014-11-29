package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import classes.Adresa;
import classes.Linie;
import classes.Statie;

public class TestLinie {
	
	Linie l;
	String expectedNume = "M1";
	Statie s1, s2;
	String expectedStatie1 = "Statie 1";
	String expectedStatie2 = "statie 1";
	String expectedStatie3 = "StaTiE 1";
	Adresa a = new Adresa("strada", "10bis");

	@Before
	public void setUp() {

		s1 = new Statie(expectedStatie1, "Statie 2", 22, null, 0, a);
		s2 = new Statie("Statie 2", null, 0, "Statie 1", 22, a);
		ArrayList<Statie> al = new ArrayList<Statie>();
		
		al.add(s1);
		al.add(s2);
		
		l = new Linie(al, expectedNume);
	}

	@Test
	public void testName() {
		assertEquals("Numele nu este acelasi", expectedNume, l.getNume());
	}

	@Test
	public void testCautareStatie() {
		Statie s = l.cautareStatie(expectedStatie1);
		assertEquals("Statia nu este aceeasi", s1, s);
		
		s = l.cautareStatie(expectedStatie2);
		assertEquals("Statia nu este aceeasi", s1, s);
		
		s = l.cautareStatie(expectedStatie3);
		assertEquals("Statia nu este aceeasi", s1, s);
	}
	
	@Test
	public void testNumarStatii() {
		assertEquals("Numarul de stattii nu este acelasi", 2, l.getNumarStatii());
	}
	
	@Test
	public void testAddStatie() {
		String expected = "Statie 3";
		Statie s3 = new Statie(expected, null, 0, null, 0, a);
		l.addStatie(s3);
		Statie s = l.cautareStatie(expected);
		assertEquals("Statia nu este aceeasi", s3, s);
	}

}
