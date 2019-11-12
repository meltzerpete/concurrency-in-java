package meltzerpete.github.io.nPrimes;

import java.util.Arrays;

public class SerialStreamNPrimes implements NPrimes {

    @Override
    public int[] getNPrimes(int n, int[] candidates) {

        return Arrays.stream(candidates)
                .filter(new IsPrime())
                .limit(n)
                .toArray();
    }
}
