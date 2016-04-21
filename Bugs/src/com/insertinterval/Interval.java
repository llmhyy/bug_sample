package com.insertinterval;

public class Interval {
	int start;
	int end;

	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(start);
		buffer.append(", ");
		buffer.append(end);
		buffer.append("]");
		
		String msg = buffer.toString();
		return msg;
	}
}
