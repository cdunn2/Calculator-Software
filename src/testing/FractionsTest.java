package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.Fractions;

public class FractionsTest {
	Fractions wholePos;
	Fractions wholeNeg;
	Fractions positive;
	Fractions posWhole;
	Fractions negative;
	Fractions negWhole;
	
	@BeforeEach
	public void setUp()
	{
		wholePos = new Fractions(5);
		wholeNeg = new Fractions(true, 6);
		positive = new Fractions(5, 7);
		posWhole = new Fractions(4, 8, 9);
		negative = new Fractions(true, 4, 7);
		negWhole = new Fractions(true, 3, 6, 11);
	}
	
	@Test
	public void testConstructor()
	{
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions((Integer) null, 5);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions((Boolean) null, 5);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(5, null);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(null, 4, 3, 5);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(2, 0);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(0);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(true, 0);});
		
		assertEquals(wholePos.getIsNegative(), false);
		assertEquals(wholePos.getWholeNumber(), 0);
		assertEquals(wholePos.getNumerator(), 25);
		assertEquals(wholePos.getDenominator(), 5);
		
		assertEquals(wholeNeg.getIsNegative(), true);
		assertEquals(wholeNeg.getWholeNumber(), 0);
		assertEquals(wholeNeg.getNumerator(), 36);
		assertEquals(wholeNeg.getDenominator(), 6);
		
		assertEquals(positive.getIsNegative(), false);
		assertEquals(positive.getWholeNumber(), 0);
		assertEquals(positive.getNumerator(), 5);
		assertEquals(positive.getDenominator(), 7);
		
		assertEquals(posWhole.getIsNegative(), false);
		assertEquals(posWhole.getWholeNumber(), 4);
		assertEquals(posWhole.getNumerator(), 8);
		assertEquals(posWhole.getDenominator(), 9);
		
		assertEquals(negative.getIsNegative(), true);
		assertEquals(negative.getWholeNumber(), 0);
		assertEquals(negative.getNumerator(), 4);
		assertEquals(negative.getDenominator(), 7);
		
		assertEquals(negWhole.getIsNegative(), true);
		assertEquals(negWhole.getWholeNumber(), 3);
		assertEquals(negWhole.getNumerator(), 6);
		assertEquals(negWhole.getDenominator(), 11);
		
		Fractions bothNeg = new Fractions(-5, -6);
		assertEquals(bothNeg.getIsNegative(), false);
		assertEquals(bothNeg.getWholeNumber(), 0);
		assertEquals(bothNeg.getNumerator(), 5);
		assertEquals(bothNeg.getDenominator(), 6);
	}
	
	@Test
	public void changeSignTest()
	{
		assertThrows(IllegalArgumentException.class, () -> 
		{Fractions.changeSign(null);});
		
		Fractions.changeSign(positive);
		assertEquals(positive.getIsNegative(), true);
		
		Fractions.changeSign(posWhole);
		assertEquals(posWhole.getIsNegative(), true);
		
		Fractions.changeSign(negative);
		assertEquals(negative.getIsNegative(), false);
		
		Fractions.changeSign(negWhole);
		assertEquals(negWhole.getIsNegative(), false);
	}
}
