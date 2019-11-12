package meltzerpete.github.io.triangles;

import java.util.stream.IntStream;

public class SimpleSerialTriangle implements Triangle {

    @Override
    public int calculate(int n) {

        return IntStream.rangeClosed(1, n).sum();
    }
}
