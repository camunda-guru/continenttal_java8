package com.ge.forkjoinrandom;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a ForkJoinPool to run the task
		ForkJoinPool pool = new ForkJoinPool();
		// Create an instance of the task
		RandomIntSum task = new RandomIntSum(100);
		// Run the task
		long sum = pool.invoke(task);
		System.out.println("Sum is " + sum);
	}

}
