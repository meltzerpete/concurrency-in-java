package meltzerpete.github.io.triangles;

import java.util.stream.IntStream;

public class NaiveWorkerTriangle implements Triangle {

    int accumulator = 0;

    @Override
    public int calculate(int n) {
        IntStream.rangeClosed(1, n)
                .forEach(value -> new Worker(accumulator, value).run());

        return accumulator;
    }

    static class Worker implements Runnable {

        int accumulator;
        int value;

        Worker(int accumulator, int value) {
            this.accumulator = accumulator;
            this.value = value;
        }

        @Override
        public void run() {
            accumulator += value;
        }

    }
}
