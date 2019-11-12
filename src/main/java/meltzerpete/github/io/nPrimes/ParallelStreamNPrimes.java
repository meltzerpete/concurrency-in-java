package meltzerpete.github.io.nPrimes;

import java.util.Arrays;

public class ParallelStreamNPrimes implements NPrimes {

    @Override
    public int[] getNPrimes(int n, int[] candidates) {

        return Arrays.stream(candidates)
                .parallel()
                .filter(new IsPrime())
                .limit(n)
                .toArray();
    }
}
