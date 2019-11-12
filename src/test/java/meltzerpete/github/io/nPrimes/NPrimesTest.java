package meltzerpete.github.io.nPrimes;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

/**
 * Problem: Find n prime no.s from a given list of candidates as quickly as possible.
 *
 * i.e. for n = 2 and candidates = [2, 3, 4, 5, 6, 7], possible solutions include:
 *  - [2, 3]
 *  - [7, 5]
 *  - [3, 7] etc.
 */
public class NPrimesTest {

    @Test
    public void isPrime() {
        final int[] primes = IntStream.rangeClosed(0, 100)
                .filter(new IsPrime())
                .toArray();

        System.out.println("primes = " + Arrays.toString(primes));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97}, primes);
    }

    private static void calculateAndTime(Class<? extends NPrimes> nPrimesClass) throws IllegalAccessException, InstantiationException, InterruptedException, ExecutionException {

        int nTrials = 10;
        int[][] results = new int[nTrials][];
        long[] times = new long[nTrials];

        int[] candidates = IntStream
                .iterate((int) 1e7, i -> i - 1)
                .limit((long) 1e3)
                .toArray();

        for (int i = 0; i < nTrials; i++) {

            System.out.println("running trial: " + i);

            // instantiate NPrimes class
            final NPrimes nPrimes = nPrimesClass.newInstance();

            // start timing
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            // perform calculation
            final int[] result = nPrimes.getNPrimes(2, candidates);

            // stop timing
            stopWatch.stop();

            // save results
            results[i] = result;
            times[i] = stopWatch.getTime();
        }

        // print results and mean time
        System.out.println("results = " + Arrays.deepToString(results));
        System.out.println("mean time = " + Arrays.stream(times).average().getAsDouble() + " ms");

        // assert each result contains 2 values
        Arrays.stream(results)
                .forEach(ints -> Assert.assertEquals(ints.length, 2));
    }

    @Test
    public void serialStream() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(SerialStreamNPrimes.class);
    }

    @Test
    public void parallelStream() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(ParallelStreamNPrimes.class);
    }

    @Test
    public void threads() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(ThreadsNPrimes.class);
    }

    @Test
    public void blockingQueue() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(BlockingQueueNPrimes.class);
    }

    @Test
    public void completionService() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(CompletionServiceNPrimes.class);
    }

    @Test
    public void completableFutures() throws IllegalAccessException, InterruptedException, InstantiationException, ExecutionException {
        calculateAndTime(CompletableFutureNPrimes.class);
    }

}
