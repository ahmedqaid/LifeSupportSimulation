import java.util.Random;

public class WaterProcessor extends Thread{
    public int water = 0;
    public UrineProcessor up;

    public WaterProcessor(UrineProcessor up) {
        this.up = up;
    }

    // public int takeCondensatedWater() {
    //     water++;
    //     return water;
    // }

    // public int takeUrineWater() {
    //     up.takeUrine();
    //     water++;
    //     return water;
    // }

    public int takeWater(int amount) {
        water += amount;
        return water;
    }

    public int provideCleanWater() {
        // wait
        water--;
        return water;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(5000-2000) + 2000);
            } catch(Exception e) {}
            // System.out.println("Water Added (Condensated): " + takeWater(1));
            int urineAmount = up.urine;
            synchronized (up) {
                try {
                    up.wait();
                } catch(Exception e) { e.printStackTrace();}
                // if (urineAmount > 0) {
                System.out.println("Water Added (Purified): " + takeWater(up.urine));
                up.urine -= urineAmount;
                synchronized (this) {
                    notify();
                }
                // }
            }
        }
    }
}
