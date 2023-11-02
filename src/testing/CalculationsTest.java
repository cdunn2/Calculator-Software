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
		Fractions positive = new Fractions(true, 4, 3, 5);

		Fractions newFraction = Calculations.improper(positive);
		assertEquals(newFraction.getWholeNumber(), 0);
		assertEquals(newFraction.getNumerator(), -23);
		assertEquals(newFraction.getDenominator(), 5);
	}
	
	@Test
	public void simplifyTest()
	{
		Fractions improper = new Fractions(true, 192, 52);
		
		Fractions newFraction = Calculations.simplify(Calculations.improper(improper));
		assertEquals(newFraction.getIsNegative(), true);
		assertEquals(newFraction.getWholeNumber(), 3);
		assertEquals(newFraction.getNumerator(), 9);
		assertEquals(newFraction.getDenominator(), 13);
		
		Fractions normal = new Fractions(52, 192);

		Fractions newFraction3= Calculations.simplify(normal);
		assertEquals(newFraction3.getIsNegative(), false);
		assertEquals(newFraction3.getWholeNumber(), 0);
		assertEquals(newFraction3.getNumerator(), 13);
		assertEquals(newFraction3.getDenominator(), 48);
	}
	
	@Test
	public void multTest()
	{
		Fractions one = new Fractions(false, 3, 1, 8);
		Fractions two = new Fractions(true, 5, 2, 6);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.multiplication(null, null);});
		
		Fractions result = Calculations.multiplication(one, two);
		
		assertEquals(result.getIsNegative(), true);
		assertEquals(result.getWholeNumber(), 16);
		assertEquals(result.getNumerator(), 2);
		assertEquals(result.getDenominator(), 3);
	}
	
	@Test
	public void addTest()
	{
		Fractions one = new Fractions(true, 14, 17, 20);
		Fractions two = new Fractions(false, 6, 6, 7);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.addition(null, null);});
		
		Fractions result = Calculations.addition(one, two);
		
		assertEquals(result.getIsNegative(), true);
		assertEquals(result.getWholeNumber(), 7);
		assertEquals(result.getNumerator(), 139);
		assertEquals(result.getDenominator(), 140);
	}
	
	@Test
	public void subTest()
	{
		Fractions one = new Fractions(false, 2, 5, 8);
		Fractions two = new Fractions(false, 2, 5, 8);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.subtraction(null, null);});
		
		Fractions result = Calculations.subtraction(one, two);
		
		assertEquals(result.getIsNegative(), false);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 0);
		assertEquals(result.getDenominator(), 1);
	}
	
	@Test
	public void divTest()
	{
		Fractions one = new Fractions(true, 0, 1, 6303);
		Fractions two = new Fractions(false, 0, 4607, 4);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, null);});
		
		Fractions result = Calculations.division(one, two);
		
		assertEquals(result.getIsNegative(), true);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 4);
		assertEquals(result.getDenominator(), 29037921);
	}
}
