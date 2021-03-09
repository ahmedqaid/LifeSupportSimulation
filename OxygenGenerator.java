import java.util.Random;

public class OxygenGenerator extends Thread {
    volatile public int oxygen;
    public WaterProcessor wp;

    public OxygenGenerator(WaterProcessor wp) {
        this.wp = wp;
    }

    public int makeOxygen() {
        wp.provideCleanWater();
        oxygen++;
        return oxygen;
    }

    public int provideOxygen() {
        oxygen--;
        return oxygen;
    }

    public void run() {
        while (true) {
            // if (oxygen > 0) {
            //     System.out.println("Oxygen released: " + provideOxygen());
            // }
            try {
                Thread.sleep(new Random().nextInt(2000 - 1000) + 1000);
            } catch (Exception e) {
            }

            synchronized (wp) {
                try {
                    wp.wait();
                } catch (Exception e) {e.printStackTrace();}
            }

            if (wp.water > 0) {
                makeOxygen();
                System.out.println("Oxygen Added: " + makeOxygen());
            }
        }
    }
}
