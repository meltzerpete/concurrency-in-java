package meltzerpete.github.io.triangles;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Problem: Calculate the nth Triangular number.
 *
 * i.e. the 5th Triangular no. is 1 + 2 + 3 + 4 + 5 = 15
 */
public class TriangleTest {


    /**
     * Calculate nth triangular number 100 times, time calculation and report results
     * @param triangleClass - specific Triangle implementation to test
     */
    private static void calculateAndTime(Class<? extends Triangle> triangleClass) throws IllegalAccessException, InstantiationException {

        int nTrials = 100;
        int[] results = new int[nTrials];
        long[] times = new long[nTrials];

        for (int i = 0; i < nTrials; i++) {

            // instantiate Triangle class
            final Triangle triangle = triangleClass.newInstance();

            // start timing
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            // perform calculation
            final int result = triangle.calculate(100);

            // stop timing
            stopWatch.stop();

            // save results
            results[i] = result;
            times[i] = stopWatch.getTime();
        }

        // print results and mean time
        System.out.println("results = " + Arrays.toString(results));
        System.out.println("mean time = " + Arrays.stream(times).average().getAsDouble() + " ms");

        // assert all results are correct
        Assert.assertArrayEquals(IntStream.range(0, nTrials).map(i -> 5050).toArray(), results);
    }

    @Test
    public void simpleSerial() throws InstantiationException, IllegalAccessException {
        calculateAndTime(SimpleSerialTriangle.class);
    }

    @Test
    public void naiveWorker() throws InstantiationException, IllegalAccessException {
        calculateAndTime(NaiveWorkerTriangle.class);
    }

    @Test
    public void integerObject() throws InstantiationException, IllegalAccessException {
        calculateAndTime(IntegerObjectTriangle.class);
    }

    @Test
    public void serialArray() throws InstantiationException, IllegalAccessException {
        calculateAndTime(SerialArrayTriangle.class);
    }

    @Test
    public void naiveParallelArray() throws InstantiationException, IllegalAccessException {
        calculateAndTime(NaiveParallelArrayTriangle.class);
    }

    @Test
    public void synchronizedBlock() throws InstantiationException, IllegalAccessException {
        calculateAndTime(SynchronizedTriangle.class);
    }

    @Test
    public void spinlockSemaphore() throws InstantiationException, IllegalAccessException {
        calculateAndTime(SpinlockSemaphoreTriangle.class);
    }

    @Test
    public void blockingSemaphore() throws InstantiationException, IllegalAccessException {
        calculateAndTime(BlockingSemaphoreTriangle.class);
    }

    @Test
    public void atomicInteger() throws InstantiationException, IllegalAccessException {
        calculateAndTime(AtomicIntegerTriangle.class);
    }

    @Test
    public void functional() throws InstantiationException, IllegalAccessException {
        calculateAndTime(FunctionalTriangle.class);
    }

}
