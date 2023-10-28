package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.Fractions;

public class FractionsTest {
	Fractions positive;
	Fractions posWhole;
	Fractions negativeNumerator;
	Fractions negativeDenominator;
	Fractions bothNegative;
	Fractions negWhole;
	Fractions negWhole2;
	
	@BeforeEach
	public void setUp()
	{
		positive = new Fractions(5, 7);
		posWhole = new Fractions(4, 8, 9);
		negativeNumerator = new Fractions(-4, 7);
		negativeDenominator = new Fractions(3, -5);
		bothNegative = new Fractions (-7, -9);
		negWhole = new Fractions(3, -6, 11);
		negWhole2 = new Fractions(7, 5, -11);
	}
	
	@Test
	public void testConstructor()
	{
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(null, 5);});
		assertThrows(IllegalArgumentException.class, () -> 
		{new Fractions(5, null);});
		
		assertEquals(positive.getWholeNumber(), 0);
		assertEquals(positive.getNumerator(), 5);
		assertEquals(positive.getDenominator(), 7);
		
		assertEquals(posWhole.getWholeNumber(), 4);
		assertEquals(posWhole.getNumerator(), 8);
		assertEquals(posWhole.getDenominator(), 9);
		
		assertEquals(negativeNumerator.getWholeNumber(), 0);
		assertEquals(negativeNumerator.getNumerator(), -4);
		assertEquals(negativeNumerator.getDenominator(), 7);
		
		assertEquals(negativeDenominator.getWholeNumber(), 0);
		assertEquals(negativeDenominator.getNumerator(), 3);
		assertEquals(negativeDenominator.getDenominator(), -5);
		
		assertEquals(bothNegative.getWholeNumber(), 0);
		assertEquals(bothNegative.getNumerator(), 7);
		assertEquals(bothNegative.getDenominator(), 9);

		assertEquals(negWhole.getWholeNumber(), 3);
		assertEquals(negWhole.getNumerator(), -6);
		assertEquals(negWhole.getDenominator(), 11);
		
		assertEquals(negWhole2.getWholeNumber(), 7);
		assertEquals(negWhole2.getNumerator(), 5);
		assertEquals(negWhole2.getDenominator(), -11);
	}
	
	@Test
	public void changeSignTest()
	{
		assertThrows(IllegalArgumentException.class, () -> 
		{Fractions.changeSign(null);});
		
		posWhole.setWholeNumber(Fractions.changeSign(posWhole.getWholeNumber()));
		assertEquals(posWhole.getWholeNumber(), -4);
		
		negativeNumerator.setNumerator(Fractions.changeSign(negativeNumerator.getNumerator()));
		assertEquals(negativeNumerator.getNumerator(), 4);
		
		positive.setDenominator(Fractions.changeSign(positive.getDenominator()));
		assertEquals(positive.getDenominator(), -7);
	}
}
