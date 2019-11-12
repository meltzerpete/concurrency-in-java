package meltzerpete.github.io.triangles;

import java.util.stream.IntStream;

public class SynchronizedTriangle implements Triangle {

    private int[] accumulator = {0};

    @Override
    public int calculate(int n) {

        IntStream.rangeClosed(1, n)
                .parallel()
                .forEach(value -> new Worker(accumulator, value).run());

        return accumulator[0];
    }

    static class Worker implements Runnable {
        int[] accumulator;
        int value;

        Worker(int[] accumulator, int value) {
            this.accumulator = accumulator;
            this.value = value;
        }

        @Override
        public void run() {

            synchronized (Worker.class) {
                // critical region
                accumulator[0] += value;
            }

        }
    }
}
