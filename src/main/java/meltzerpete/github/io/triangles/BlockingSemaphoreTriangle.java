package meltzerpete.github.io.triangles;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class BlockingSemaphoreTriangle implements Triangle {

    private int[] accumulator = {0};
    private final Semaphore semaphore = new Semaphore(1);

    @Override
    public int calculate(int n) {

        IntStream.rangeClosed(1, n)
                .parallel()
                .forEach(value -> new Worker(semaphore, accumulator, value).run());

        return accumulator[0];
    }

    static class Worker implements Runnable {
        Semaphore semaphore;
        int[] accumulator;
        int value;

        Worker(Semaphore semaphore, int[] accumulator, int value) {
            this.semaphore = semaphore;
            this.accumulator = accumulator;
            this.value = value;
        }

        @Override
        public void run() {

            try {
                // block until semaphore is acquired
                semaphore.acquire();

                // critical region
                accumulator[0] += value;

                // release semaphore
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
