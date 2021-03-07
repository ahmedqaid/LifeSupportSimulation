import java.util.Random;

public class UrineProcessor extends Thread {
    public int urine = 0;

    public int takeUrine() {
        urine++;
        return urine;
    }

    public int provideProcessedUrine() {
        // wait
        urine--;
        return urine;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
            } catch (Exception e) {
            }
            System.out.println("Urine Added: " + takeUrine());
        }
    }
}
