package meltzerpete.github.io.triangles;

import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class FunctionalTriangle implements Triangle {

    @Override
    public int calculate(int n) {

        int initialValue = 0;

        return IntStream.rangeClosed(1, n)
                .parallel()
                .reduce(initialValue, new Worker());
        /*
        EQUIVALENT SIMPLIFICATIONS

        # INLINE
        return IntStream.rangeClosed(1, 100)
                .parallel()
                .reduce(initialValue, (left, right) -> left + right);

        # BUILT IN
        return IntStream.rangeClosed(1, 100)
                .parallel()
                .sum();
         */
    }

    static class Worker implements IntBinaryOperator {

        @Override
        public int applyAsInt(int left, int right) {
            return left + right;
        }
    }
}
