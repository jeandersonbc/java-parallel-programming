package com.github.jeandersonbc.javaparalleldemo.utils;

import java.util.Random;

public class DataGen {

	private DataGen() {
	}

	public static Integer[] randomIntArray(int size) {
		Random generator = new Random(1235);
		Integer[] input = new Integer[size];
		for (int i = 0; i < input.length; i++) {
			input[i] = generator.nextInt();
		}
		return input;
	}

	public static Double[] randomDoubleArray(int size) {
		Random generator = new Random(1235);
		Double[] input = new Double[size];
		for (int i = 0; i < input.length; i++) {
			input[i] = generator.nextDouble();
		}
		return input;
	}

}
