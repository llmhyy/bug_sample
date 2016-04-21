package test;

import junit.framework.Assert;

import org.junit.Test;

import com.simplecalculator.SimpleCalculator;

public class SimpleCalculatorTest {

	@Test
	public void test() {
		SimpleCalculator calculator = new SimpleCalculator();
		int value = calculator.calculate("(((1 + ((1 + 2) + (2 - 1)) - (1 - 3)) + 1) + 1) + 1");
		
		Assert.assertEquals(6, value);
		
		System.out.println(value);
	}

}
