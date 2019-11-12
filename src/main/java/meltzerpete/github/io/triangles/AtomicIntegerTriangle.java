package meltzerpete.github.io.triangles;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerTriangle implements Triangle {

    // shared object references should always be final or volatile
    private final AtomicInteger accumulator = new AtomicInteger(0);

    public int calculate(int n) {

        IntStream.rangeClosed(1, n)
                .parallel()
                .forEach(value -> new Worker(accumulator, value).run());

        return accumulator.get();
    }

    static class Worker implements Runnable {

        AtomicInteger accumulator;
        int value;

        Worker(AtomicInteger accumulator, int value) {
            this.accumulator = accumulator;
            this.value = value;
        }

        @Override
        public void run() {
            accumulator.getAndAdd(value);
        }
    }
}
