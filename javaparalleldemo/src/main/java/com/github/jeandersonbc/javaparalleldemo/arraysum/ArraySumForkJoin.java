package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArraySumForkJoin implements ArraySumProblem {

	class ParallelArraySum extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 8348136001125722839L;

		private static final int THRESHOLD = 100;

		private Integer[] values;
		private int left;
		private int right;

		public ParallelArraySum(Integer[] values, int left, int right) {
			this.left = left;
			this.right = right;
			this.values = values;
		}

		@Override
		protected Integer compute() {
			if (left > right)
				return 0;
			if (right - left <= THRESHOLD) {
				int sum = 0;
				for (int i = left; i <= right; i++) {
					sum += values[i];
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
	public int sum(Integer[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		ForkJoinTask<Integer> task = new ParallelArraySum(values, 0, values.length - 1);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}

}
