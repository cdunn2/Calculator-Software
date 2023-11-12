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
	public void reduceTest()
	{
		Fractions improper = new Fractions(true, 192, 52);
		
		Fractions newFraction = Calculations.reduce(improper);
		assertEquals(newFraction.getIsNegative(), true);
		assertEquals(newFraction.getWholeNumber(), 0);
		assertEquals(newFraction.getNumerator(), 48);
		assertEquals(newFraction.getDenominator(), 13);
		
		Fractions normal = new Fractions(52, 192);

		Fractions newFraction2= Calculations.reduce(normal);
		assertEquals(newFraction2.getIsNegative(), false);
		assertEquals(newFraction2.getWholeNumber(), 0);
		assertEquals(newFraction2.getNumerator(), 13);
		assertEquals(newFraction2.getDenominator(), 48);
		
		Fractions zero = new Fractions(0, 192);

		Fractions newFraction3= Calculations.reduce(zero);
		assertEquals(newFraction3.getIsNegative(), false);
		assertEquals(newFraction3.getWholeNumber(), 0);
		assertEquals(newFraction3.getNumerator(), 0);
		assertEquals(newFraction3.getDenominator(), 1);
	}
	
	@Test
	public void properTest()
	{
		Fractions improper = new Fractions(false, 48,13);
		Fractions newFraction = Calculations.proper(improper);

		assertEquals(newFraction.getIsNegative(), false);
		assertEquals(newFraction.getWholeNumber(), 3);
		assertEquals(newFraction.getNumerator(), 9);
		assertEquals(newFraction.getDenominator(), 13);
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
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 800);
		assertEquals(result.getDenominator(), 48);
		
		Fractions one2 = new Fractions(false, 3, 1, 8);
		Fractions two2 = new Fractions(false, 5, 2, 6);
		Fractions result2 = Calculations.multiplication(one2, two2);
		
		assertEquals(result2.getIsNegative(), false);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 800);
		assertEquals(result2.getDenominator(), 48);
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
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 1119);
		assertEquals(result.getDenominator(), 140);
		
		Fractions one2 = new Fractions(false, 14, 17, 20);
		Fractions two2 = new Fractions(false, 6, 6, 7);
		
		Fractions result2 = Calculations.addition(one2, two2);
		
		assertEquals(result2.getIsNegative(), false);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 3039);
		assertEquals(result2.getDenominator(), 140);
	}
	
	@Test
	public void subTest()
	{
		Fractions one = new Fractions(false, 4, 5, 16);
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
		assertEquals(result.getNumerator(), 216);
		assertEquals(result.getDenominator(), 128);
		
		Fractions one2 = new Fractions(true, 4, 5, 16);
		Fractions two2 = new Fractions(false, 2, 5, 8);
		
		Fractions result2 = Calculations.subtraction(one2, two2);
		
		assertEquals(result2.getIsNegative(), true);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 888);
		assertEquals(result2.getDenominator(), 128);
	}
	
	@Test
	public void divTest()
	{
		Fractions one = new Fractions(true, 2, 5, 8);
		Fractions two = new Fractions(false, 0, 3, 4);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.division(null, null);});
		
		Fractions result = Calculations.division(one, two);
		
		assertEquals(result.getIsNegative(), true);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 84);
		assertEquals(result.getDenominator(), 24);
		
		Fractions one2 = new Fractions(true, 2, 5, 8);
		Fractions two2 = new Fractions(true, 0, 3, 4);
		Fractions result2 = Calculations.division(one2, two2);
		
		assertEquals(result2.getIsNegative(), false);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 84);
		assertEquals(result2.getDenominator(), 24);
	}
	
	@Test
	public void inverseTest()
	{
		Fractions one = new Fractions(false, 2, 5, 8);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.inverse(null);});
		
		Fractions result = Calculations.inverse(one);
		
		assertEquals(result.getIsNegative(), false);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 8);
		assertEquals(result.getDenominator(), 21);
		
		Fractions two = new Fractions(true, 2, 5, 8);
		Fractions result2 = Calculations.inverse(two);
		
		assertEquals(result2.getIsNegative(), true);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 8);
		assertEquals(result2.getDenominator(), 21);
	}
	
	@Test
	public void mediantTest()
	{
		Fractions one = new Fractions(true, 4, 3, 5);
		Fractions two = new Fractions(false, 2, 9, 11);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.mediant(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.mediant(null, two);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.mediant(null, null);});
		
		Fractions result = Calculations.mediant(one, two);
		
		assertEquals(result.getIsNegative(), false);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 8);
		assertEquals(result.getDenominator(), 16);
		
		Fractions one2 = new Fractions(false, 4, 3, 5);
		Fractions two2 = new Fractions(true, 2, 9, 11);
		Fractions result2 = Calculations.mediant(one2, two2);
		
		assertEquals(result2.getIsNegative(), true);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 8);
		assertEquals(result2.getDenominator(), 16);
	}
	
	@Test
	public void powerTest()
	{
		Fractions one = new Fractions(true, 1, 3, 4);
		
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.power(null, 2);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.power(one, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{Calculations.power(null, null);});
		
		Fractions result = Calculations.power(one, 3);
		
		assertEquals(result.getIsNegative(), true);
		assertEquals(result.getWholeNumber(), 0);
		assertEquals(result.getNumerator(), 343);
		assertEquals(result.getDenominator(), 64);
		
		Fractions two = new Fractions(true, 2, 5, 8);
		Fractions result2 = Calculations.power(two, 2);
		
		assertEquals(result2.getIsNegative(), false);
		assertEquals(result2.getWholeNumber(), 0);
		assertEquals(result2.getNumerator(), 441);
		assertEquals(result2.getDenominator(), 64);
	}
}
