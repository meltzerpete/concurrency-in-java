package meltzerpete.github.io.nPrimes;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class ThreadsNPrimes implements NPrimes {

    @Override
    public int[] getNPrimes(int n, int[] candidates) throws InterruptedException {

        final Thread[] threads = new Thread[candidates.length];
        final ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 0; i < candidates.length; i++) {
            final int value = candidates[i];

            threads[i] = new Thread(() -> {

                if (primes.size() >= n) return;

                final boolean valueIsPrime = new IsPrime().test(value);

                if (valueIsPrime) {
                    primes.add(value);
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        return ArrayUtils.toPrimitive(primes.toArray(new Integer[0]));
    }
}
