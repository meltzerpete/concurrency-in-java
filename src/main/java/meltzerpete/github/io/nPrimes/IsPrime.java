package meltzerpete.github.io.nPrimes;

import java.util.function.IntPredicate;

/**
 * This implementation is deliberately slow for the purpose of demonstrating concurrency
 * and parallelisation techniques.
 */
public class IsPrime implements IntPredicate {

    @Override
    public boolean test(int value) {
        if (value == 0 || value == 1) return false;

        for (double i = 2; i < value; i++) {
            if (value % i == 0) return false;
        }

        return true;
    }
}
