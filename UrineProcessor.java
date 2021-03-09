import java.util.Random;
import java.util.concurrent.Semaphore;

public class UrineProcessor {
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
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (Exception e) {
        }
        int ur = urine;
        urine = 0;
        return ur;
    }
}
