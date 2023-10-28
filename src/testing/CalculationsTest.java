package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import utilities.Calculations;
import utilities.Fractions;

public class CalculationsTest 
{
	
	@Test
	public void improperTest()
	{
		Fractions positive = new Fractions(-4, 3, 5);

		Fractions newFraction = Calculations.improper(positive);
		assertEquals(newFraction.getWholeNumber(), 0);
		assertEquals(newFraction.getNumerator(), -17);
		assertEquals(newFraction.getDenominator(), 5);
	}
	
	@Test
	public void simplifyTest()
	{
		Fractions improper = new Fractions(-192, 52);

		Fractions newFraction = Calculations.simplify(improper);
		assertEquals(newFraction.getWholeNumber(), -3);
		assertEquals(newFraction.getNumerator(), -9);
		assertEquals(newFraction.getDenominator(), 13);
		
		Fractions improper2 = new Fractions(192, -52);

		Fractions newFraction2 = Calculations.simplify(improper2);
		assertEquals(newFraction2.getWholeNumber(), -3);
		assertEquals(newFraction2.getNumerator(), 9);
		assertEquals(newFraction2.getDenominator(), -13);
		
		Fractions normal = new Fractions(52, 192);

		Fractions newFraction3= Calculations.simplify(normal);
		assertEquals(newFraction3.getWholeNumber(), 0);
		assertEquals(newFraction3.getNumerator(), 13);
		assertEquals(newFraction3.getDenominator(), 48);
	}
	
	@Test
	public void multTest()
	{
		Fractions one = new Fractions(4, 5, 8);
		Fractions two = new Fractions(6, 3, 5);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(null, null);});
		
		Fractions result = Calculations.multiplication(one, two);
		
		assertEquals(result.getWholeNumber(), 30);
		assertEquals(result.getNumerator(), 21);
		assertEquals(result.getDenominator(), 40);
	}
	
	@Test
	public void addTest()
	{
		Fractions one = new Fractions(4, 5, 8);
		Fractions two = new Fractions(6, 3, 5);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(null, null);});
		
		Fractions result = Calculations.addition(one, two);
		
		assertEquals(result.getWholeNumber(), 11);
		assertEquals(result.getNumerator(), 9);
		assertEquals(result.getDenominator(), 40);
	}
	
	@Test
	public void subTest()
	{
		Fractions one = new Fractions(4, 5, 8);
		Fractions two = new Fractions(6, 3, 5);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(null, null);});
		
		Fractions result = Calculations.subtraction(one, two);
		
		assertEquals(result.getWholeNumber(), -1);
		assertEquals(result.getNumerator(), -39);
		assertEquals(result.getDenominator(), 40);
	}
	
	@Test
	public void divTest()
	{
		Fractions one = new Fractions(6, 3, 5);
		Fractions two = new Fractions(4, 5, 8);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, null);});
		
		Fractions result = Calculations.division(one, two);
		
		assertEquals(result.getWholeNumber(), 1);
		assertEquals(result.getNumerator(), 79);
		assertEquals(result.getDenominator(), 185);
	}
}
