package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final int NUMBER_AMOUNT = 100;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Callable<Integer> task = new SumCalculator(NUMBER_AMOUNT);

            System.out.println("Submitting the task...");
            Future<Integer> future = executor.submit(task);
            System.out.println("Task submitted");

            System.out.println("Waiting for the result...");
            Integer result = future.get();
            System.out.println("The sum of first " + NUMBER_AMOUNT + " natural numbers is: " + result);
        } catch (Exception e) {
            System.out.println("Task threw an exception: " + e.getCause().getMessage());
        } finally {
            executor.shutdown();
        }
    }

    static class SumCalculator implements Callable<Integer> {
        private final int n;

        public SumCalculator(int n) {
            this.n = n;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
