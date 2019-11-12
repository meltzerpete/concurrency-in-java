package meltzerpete.github.io.nPrimes;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureNPrimes implements NPrimes {

    private ArrayBlockingQueue<Integer> primesQueue;
    private ExecutorService executor;

    @Override
    public int[] getNPrimes(int n, int[] candidates) throws InterruptedException {

        primesQueue = new ArrayBlockingQueue<>(n);
        executor  = Executors.newFixedThreadPool(2);

        // submit the asynchronous jobs
        Arrays.stream(candidates)
                .forEach(this::submitAndProcess);

        // collect n results
        final int[] primes = new int[n];
        for (int i = 0; i < n; i++) {
            // block until a prime is found
            primes[i] = primesQueue.take();
        }

        // stop executing unfinished jobs
        executor.shutdownNow();

        return primes;
    }

    /**
     * defines an asynchronous pipeline from submitting the value, testing it,
     * and queueing it as a result if appropriate
     * @param value value to test
     */
    private void submitAndProcess(int value) {
        CompletableFuture
                .supplyAsync(() -> value, executor)
                // here we use thenApply() rather than thenApplyAsync()
                .thenApply(v -> {
                    if (new IsPrime().test(v)) {
                        return v;
                    } else {
                        // this result will be ignored at the next stage
                        throw new NoSuchElementException();
                    }
                })
                // also thenAccept() rather than thenAcceptAsync()
                .thenAccept(v -> primesQueue.offer(v));
    }
}
