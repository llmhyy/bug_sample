package com.insertinterval;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification in https://leetcode.com/problems/insert-interval/
 * 
 * @author Yun Lin
 *
 */
public class InsertInterval {

	public List<Interval> insert(List<Interval> intervals, Interval range) {
		
		if(intervals.isEmpty()){
			intervals.add(range);
			return intervals;
		}
		
		/**
		 * The <code>startIntervalIndex</code> and <code>endIntervalIndex</code> 
		 * indicates the range to be merged of the original intervals.
		 */
		int startIntervalIndex = -1;
		int endIntervalIndex = intervals.size();
		
		for(int i=0; i<intervals.size(); i++){
			Interval interval = intervals.get(i);
			
			if(isContain(interval, range)){
				return intervals;
			}
			else if(isContainedBy(interval, range)){
				endIntervalIndex = i + 1;
			}
			else if(isLarger(interval, range)){
				if(i > endIntervalIndex){
					endIntervalIndex = i;					
				}
			}
			else if(isSmaller(interval, range)){
				if(i < startIntervalIndex){
					startIntervalIndex = i;					
				}
			}
			
			if(isSmallerIntersected(interval, range)){
				startIntervalIndex = i - 1;
			}
			if(isLargerIntersected(interval, range)){
				endIntervalIndex = i + 1;
			}
		}
		
		int start = (startIntervalIndex+1 < intervals.size() && range.start > intervals.get(startIntervalIndex+1).start) ? 
				intervals.get(startIntervalIndex+1).start : range.start;
				
		int end = (endIntervalIndex-1 >= 0 && range.end < intervals.get(endIntervalIndex-1).end) ?
				intervals.get(endIntervalIndex-1).end : range.end;
		Interval newInterval = new Interval(start, end);
		
		
		List<Interval> newIntervaList = new ArrayList<>();
		
		if(endIntervalIndex - 1 < 0){
			newIntervaList.add(newInterval);
		}
		
		/**
		 * Then, we do the merge or insert.
		 */
		boolean isSuccessive = (startIntervalIndex + 1 == endIntervalIndex);
		boolean isFirstTime = true;
		for(int i=0; i<intervals.size(); i++){
			Interval interval = intervals.get(i);
			if(i <= startIntervalIndex){
				newIntervaList.add(interval);
				
				if(i == startIntervalIndex && isSuccessive 
						&& startIntervalIndex < intervals.size() && endIntervalIndex < intervals.size()
						&& endIntervalIndex >= 0){
					newIntervaList.add(newInterval);
				}
			}
			else if(i >= endIntervalIndex){
				newIntervaList.add(interval);
			}
			else{
				if(isFirstTime){
					newIntervaList.add(newInterval);
					isFirstTime = false;
				}
			}
		}
		
		if(startIntervalIndex + 1 >= intervals.size()){
			newIntervaList.add(newInterval);
		}
		
		return newIntervaList;
	}
	
	public boolean isContain(Interval interval, Interval range){
		return interval.start <= range.start && interval.end >= range.end;
	}
	
	public boolean isContainedBy(Interval interval, Interval range){
		return interval.start >= range.start && interval.end <= range.end;
	}
	
	public boolean isSmallerIntersected(Interval interval, Interval range){
		return interval.start <= range.start && interval.end >= range.start;
	}
	
	public boolean isLargerIntersected(Interval interval, Interval range){
		return interval.end >= range.end && interval.start <= range.end;
	}
	
	public boolean isSmaller(Interval interval, Interval range){
		return interval.end < range.start;
	}
	
	public boolean isLarger(Interval interval, Interval range){
		return interval.start > range.end;
	}

	/**
	 * expected result is: [1, 2][3, 7][8, 10][12, 16]
	 * @param args
	 */
	public static void main(String[] args) {
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
		
		for(Interval interval: newList){
			System.out.print(interval);
		}
		System.out.println();
	}

}
