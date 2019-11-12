package meltzerpete.github.io.threesAndFives;

import org.junit.Test;

/**
 * Problem: Print "Three" every 3 seconds and "Five" every 5 seconds for a given duration.
 */
public class ThreesAndFivesTest {

    @Test
    public void naiveThreesAndFives() {
        new NaiveThreesAndFives().printThreesAndFives(10);
    }

    @Test
    public void threadRun() {
        new ThreadedRunThreesAndFives().printThreesAndFives(10);
    }

    @Test
    public void threadStart() {
        new ThreadedStartThreesAndFives().printThreesAndFives(10);
    }

    @Test
    public void continuingApp() {
        new ContinuingAppThreesAndFives().printThreesAndFives(10);
    }

    @Test
    public void join() throws InterruptedException {
        new JoinThreesAndFives().printThreesAndFives(10);
    }

    @Test
    public void interrupt() throws InterruptedException {
        new InterruptThreesAndFives().printThreesAndFives(10);
    }
}
