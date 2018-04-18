package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;

public class ArraySumSequential implements ArraySumProblem {

	@Override
	public Double sum(Double[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		double total = 0.0;
		for (int i = 0; i < values.length; i++) {
			total += 1 / values[i];
		}
		return total;
	}

}
