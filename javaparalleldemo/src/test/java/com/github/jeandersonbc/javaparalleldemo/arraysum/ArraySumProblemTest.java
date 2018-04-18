package com.github.jeandersonbc.javaparalleldemo.arraysum;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.jeandersonbc.javaparalleldemo.utils.DataGen;

@RunWith(Parameterized.class)
public class ArraySumProblemTest {

	private static final ArraySumProblem ORACLE = new ArraySumSequential();
	private static final Double[] INPUT = DataGen.randomDoubleArray(100);

	private ArraySumProblem underTest;

	public ArraySumProblemTest(ArraySumProblem underTest) {
		this.underTest = underTest;
	}

	@Parameters(name = "{index}: {0}")
	public static Collection<ArraySumProblem> provider() throws Exception {
		return Arrays.asList(new ArraySumForkJoin());
	}

	@Test
	public void shouldSumRandomValues() {
		assertEquals(ORACLE.sum(INPUT), underTest.sum(INPUT));
	}

}
