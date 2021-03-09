import java.util.Random;
import java.util.concurrent.Semaphore;

public class UrineProcessor extends Thread {
    public volatile int urine = 0;

    synchronized public int takeUrine() {

        Semaphore s = new Semaphore(1);
        try {
            s.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
        } catch (Exception e) {
        }
        urine++;
        s.release();
        synchronized (this) {
            notify();
        }
        return urine;
    }

    public int provideProcessedUrine() {
        // wait
        try {
            Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
        } catch (Exception e) {
        }
        urine--;
        return urine;
    }

    public void run() {

        // while (true) {
        // // try {
        // // System.out.println("WAITING");
        // // this.wait();
        // // System.out.println("NONWAITING");
        // // } catch (Exception e) {
        // // e.printStackTrace();
        // // }

        // try {
        // Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
        // } catch (Exception e) {
        // }
        // System.out.println("------------Urine Added: " + takeUrine());
        // }
    }
}
