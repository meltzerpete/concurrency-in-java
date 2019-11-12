package meltzerpete.github.io.nPrimes;

import org.apache.commons.lang3.ArrayUtils;

import java.util.concurrent.*;

public class CompletionServiceNPrimes implements NPrimes {

    @Override
    public int[] getNPrimes(int n, int[] candidates) throws InterruptedException, ExecutionException {

        // here we can observe the trade-off between thread creation and calculation costs
        final ExecutorService executor = Executors.newFixedThreadPool(2);

        final ExecutorCompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executor);

        final ArrayBlockingQueue<Integer> primesQueue = new ArrayBlockingQueue<>(n);

        final Thread supplierThread = new Thread(() -> {

            for (int value : candidates) {

                // stop submitting jobs if n primes are found already
                if (primesQueue.size() == n) return;

                completionService.submit(() -> {
                    if (new IsPrime().test(value)) {
                        return value;
                    } else {
                        return null;
                    }
                });
            }
        });

        supplierThread.start();

        // collect n results
        while (primesQueue.size() < n) {
            // block until a prime is found
            final Future<Integer> completedValue = completionService.take();
            // only allow nonnull results into the final results queue
            if (completedValue.get() != null) primesQueue.add(completedValue.get());
        }

        // kill the supplier thread
        supplierThread.join();

        // stop executing all unfinished jobs
        executor.shutdownNow();

        return ArrayUtils.toPrimitive(primesQueue.toArray(new Integer[0]));
    }
}
