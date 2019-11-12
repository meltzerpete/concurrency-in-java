package meltzerpete.github.io.triangles;

import java.util.stream.IntStream;

public class IntegerObjectTriangle implements Triangle {

    private Integer accumulator = 0;

    public int calculate(int n) {

        IntStream.rangeClosed(1, n)
                .forEach(value -> new Worker(accumulator, value).run());

        return accumulator;
    }

    static class Worker implements Runnable {

        Integer accumulator;
        int value;

        Worker(Integer accumulator, int value) {
            this.accumulator = accumulator;
            this.value = value;
        }

        @Override
        public void run() {
            accumulator += value;
        }
    }
}
