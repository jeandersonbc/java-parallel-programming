package com.github.jeandersonbc.javaparalleldemo.arraysum;

import com.github.jeandersonbc.javaparalleldemo.utils.DataGen;

public class Benchmark {

	public static void main(String[] args) {

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

		System.out.print("Generating data...");
		long init = System.nanoTime();
		Double[] INPUT = DataGen.randomDoubleArray(100_000_000);
		long delta = System.nanoTime() - init;
		System.out.printf("Done! %dms%n", delta);

		ArraySumProblem sequential = new ArraySumSequential();
		ArraySumProblem parallel = new ArraySumForkJoin();
		String stringTemplate = "\t%-15s%dms\t%2f%n";

		int runs = 10;
		for (int run = 1; run <= runs; run++) {
			System.out.printf("RUN %d\n", run);

			init = System.nanoTime();
			double result = sequential.sum(INPUT);
			delta = System.nanoTime() - init;
			System.out.printf(stringTemplate, "Sequential", delta, result);

			init = System.nanoTime();
			result = parallel.sum(INPUT);
			delta = System.nanoTime() - init;
			System.out.printf(stringTemplate, "Parallel", delta, result);

		}

	}
}
