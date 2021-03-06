package meltzerpete.github.io.threesAndFives;

public class InterruptThreesAndFives implements ThreesAndFives {

    private boolean running = true;

    public void printThreesAndFives(int seconds) throws InterruptedException {

        Thread threesThread = new Thread(new Threes());
        Thread fivesThread = new Thread(new Fives());

        threesThread.start();
        fivesThread.start();
        
        try {
            // sleep the current thread
            Thread.sleep((long) (seconds * 1e3));
        } catch (InterruptedException e) {
            // do nothing
        }

        running = false;

        threesThread.interrupt();
        fivesThread.interrupt();

        threesThread.join();
        fivesThread.join();

        try {
            // sleep the current thread for 5 more seconds
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    class Threes implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Threes was interrupted");
                    // break loop when interrupted
                    break;
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
                    // OR move print into try block
                    System.out.println("Five");
                } catch (InterruptedException e) {
                    System.out.println("Fives was interrupted");
                }
            }
        }
    }
}
