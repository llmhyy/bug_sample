package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.insertinterval.InsertInterval;
import com.insertinterval.Interval;
import com.simplecalculator.SimpleCalculator;

public class SimpleCalculatorTest {

	@Test
	public void testCalculator() {
		SimpleCalculator calculator = new SimpleCalculator();
		int value = calculator.calculate("(((1 + ((1 + 2) + (2 - 1)) - (1 - 3)) + 1) + 1) + 1");
		
		Assert.assertEquals(6, value);
		
		System.out.println(value);
	}
	
	@Test
	public void testInterval() {
		InsertInterval insert = new InsertInterval();
		
		Interval i1 = new Interval(1, 2);
		Interval i2 = new Interval(3, 4);
		Interval i3 = new Interval(6, 7);
		Interval i4 = new Interval(8, 10);
		Interval i5 = new Interval(12, 16);
		
		List<Interval> list = new ArrayList<>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		list.add(i5);
		
		Interval insertInterval = new Interval(4, 6);
		
		List<Interval> newList = insert.insert(list, insertInterval);
		
		String str = "";
		for(Interval interval: newList){
			str += interval.toString();
		}
		Assert.assertEquals("[1, 2][3, 10][12, 16]", str);
	}

}
