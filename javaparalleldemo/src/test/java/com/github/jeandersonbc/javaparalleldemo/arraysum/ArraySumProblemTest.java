package com.github.jeandersonbc.javaparalleldemo.arraysum;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ArraySumProblemTest {

	private ArraySumProblem oracle;
	private ArraySumProblem seqDivideAndConquer;
	private ArraySumProblem forkJoinImpl;

	@Before
	public void setUp() throws Exception {
		oracle = new ArraySumSequential();
		seqDivideAndConquer = new ArraySumDivideAndConquer();
		forkJoinImpl = new ArraySumForkJoin();
	}

	@Test
	public void shouldSumRandomValuesFixedSize() {
		int reruns = 5;
		for (int i = 0; i < reruns; i++) {
			int[] input = randomIntArray(10);
			assertEquals(oracle.sum(input), seqDivideAndConquer.sum(input));
			assertEquals(oracle.sum(input), forkJoinImpl.sum(input));
		}
	}

	@Test
	public void shouldSumRandomValues() {
		int reruns = 10;
		int sizeBound = 10_000_000;
		Random generator = new Random();
		for (int i = 0; i < reruns; i++) {
			int[] input = randomIntArray(generator.nextInt(sizeBound));
			assertEquals(oracle.sum(input), seqDivideAndConquer.sum(input));
			assertEquals(oracle.sum(input), forkJoinImpl.sum(input));
		}
	}

	private int[] randomIntArray(int size) {
		Random generator = new Random();
		int[] input = new int[size];
		for (int i = 0; i < input.length; i++) {
			input[i] = generator.nextInt();
		}
		return input;
	}

}
