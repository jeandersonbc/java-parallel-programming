package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;

public class ArraySumSequential implements ArraySumProblem {

	@Override
	public int sum(int[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		int total = values[0];
		for (int i = 1; i < values.length; i++) {
			total += values[i];
		}
		return total;
	}

}
