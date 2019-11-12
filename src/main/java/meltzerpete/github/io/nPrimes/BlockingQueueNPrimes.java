package meltzerpete.github.io.nPrimes;

import org.apache.commons.lang3.ArrayUtils;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueNPrimes implements NPrimes {

    @Override
    public int[] getNPrimes(int n, int[] candidates) throws InterruptedException {

        final Thread[] threads = new Thread[candidates.length];
        final ArrayBlockingQueue<Integer> primes = new ArrayBlockingQueue<>(n);

        for (int i = 0; i < candidates.length; i++) {
            final int value = candidates[i];

            threads[i] = new Thread(() -> {

                final boolean valueIsPrime = new IsPrime().test(value);

                // offer primes to the results queue if there's space
                if (valueIsPrime) primes.offer(value);
            });
        }

        for (Thread thread : threads) {
            // stop submitting jobs is n primes already found
            if (primes.size() == n) break;
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return ArrayUtils.toPrimitive(primes.toArray(new Integer[0]));
    }
}
