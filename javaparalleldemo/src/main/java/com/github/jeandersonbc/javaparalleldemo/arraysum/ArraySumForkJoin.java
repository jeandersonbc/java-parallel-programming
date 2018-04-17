package com.github.jeandersonbc.javaparalleldemo.arraysum;

import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArraySumForkJoin implements ArraySumProblem {

	class ParallelArraySum extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 8348136001125722839L;

		private int[] values;
		private int left;
		private int right;

		public ParallelArraySum(int[] values, int left, int right) {
			this.left = left;
			this.right = right;
			this.values = values;
		}

		@Override
		protected Integer compute() {
			if (left > right)
				return 0;
			if (left == right)
				return values[left];

			int mid = (left + right) >> 1;
			ParallelArraySum leftSum = new ParallelArraySum(values, left, mid);
			leftSum.fork();
			ParallelArraySum rightSum = new ParallelArraySum(values, mid + 1, right);
			return rightSum.compute() + leftSum.join();
		}

	}

	@Override
	public int sum(int[] values) {
		if (values.length == 0) {
			throw new NoSuchElementException("Shouldn't be an empty array");
		}
		ForkJoinTask<Integer> task = new ParallelArraySum(values, 0, values.length - 1);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}

}
