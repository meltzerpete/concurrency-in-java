package meltzerpete.github.io.nPrimes;

import java.util.concurrent.ExecutionException;

public interface NPrimes {

    int[] getNPrimes(int n, int[] candidates) throws InterruptedException, ExecutionException;

}
