package com.github.jeandersonbc.javaparalleldemo.arraysum;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ArraySumProblemTest {

	private static final ArraySumProblem ORACLE = new ArraySumSequential();
	private static final Integer[] INPUT = randomIntArray(10_000_000);

	private ArraySumProblem underTest;

	public ArraySumProblemTest(ArraySumProblem underTest) {
		this.underTest = underTest;
	}

	@Parameters(name = "{index}: {0}")
	public static Collection<ArraySumProblem> provider() throws Exception {
		return Arrays.asList(new ArraySumDivideAndConquer(), new ArraySumForkJoin());
	}

	@Test
	public void shouldSumRandomValues() {
		assertEquals(ORACLE.sum(INPUT), underTest.sum(INPUT));
	}

	private static Integer[] randomIntArray(int size) {
		Random generator = new Random(1235);
		Integer[] input = new Integer[size];
		for (int i = 0; i < input.length; i++) {
			input[i] = generator.nextInt();
		}
		return input;
	}

}
