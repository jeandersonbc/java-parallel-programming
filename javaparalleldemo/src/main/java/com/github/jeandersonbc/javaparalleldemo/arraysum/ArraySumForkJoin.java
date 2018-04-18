package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArraySumForkJoin implements ArraySumProblem {

	class ParallelArraySum extends RecursiveTask<Double> {

		private static final long serialVersionUID = 8348136001125722839L;

		private static final int THRESHOLD = 1000;

		private Double[] values;
		private int left;
		private int right;

		public ParallelArraySum(Double[] values, int left, int right) {
			this.left = left;
			this.right = right;
			this.values = values;
		}

		@Override
		protected Double compute() {
			if (right - left <= THRESHOLD) {
				double sum = 0.0;
				for (int i = left; i <= right; i++) {
					sum += 1 / values[i];
				}
				return sum;
			}
			int mid = (left + right) >> 1;
			ParallelArraySum leftSum = new ParallelArraySum(values, left, mid);
			leftSum.fork();
			ParallelArraySum rightSum = new ParallelArraySum(values, mid + 1, right);

			return rightSum.compute() + leftSum.join();
		}

	}

	@Override
	public Double sum(Double[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		ForkJoinTask<Double> task = new ParallelArraySum(values, 0, values.length - 1);
		return ForkJoinPool.commonPool().invoke(task);
	}

}
