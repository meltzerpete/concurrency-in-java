package meltzerpete.github.io.threesAndFives;

public class ThreadedRunThreesAndFives implements ThreesAndFives {

    private boolean running = true;

    public void printThreesAndFives(int seconds) {

        Thread threesThread = new Thread(new Threes());
        Thread fivesThread = new Thread(new Fives());

        threesThread.run();
        fivesThread.run();
        
        try {
            // sleep the current thread
            Thread.sleep((long) (seconds * 1e3));
        } catch (InterruptedException e) {
            // do nothing
        }

        running = false;
    }

    class Threes implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Threes was interrupted");
                }
                System.out.println("Three");
            }
        }
    }

    class Fives implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Fives was interrupted");
                }
                System.out.println("Five");
            }
        }
    }
}
