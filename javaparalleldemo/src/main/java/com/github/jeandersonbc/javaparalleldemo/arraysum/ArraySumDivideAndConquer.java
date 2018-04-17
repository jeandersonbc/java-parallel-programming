package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;

public class ArraySumDivideAndConquer implements ArraySumProblem {

	@Override
	public int sum(int[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		return sum(values, 0, values.length - 1);
	}

	private int sum(int[] values, int i, int j) {
		if (i == j) {
			return values[i];
		}
		if (i > j) {
			return 0;
		}
		int mid = (i + j) >> 1;

		return sum(values, i, mid) + sum(values, mid + 1, j);
	}

}
