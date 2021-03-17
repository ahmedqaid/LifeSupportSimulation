import java.util.Random;

public class WaterProcessor implements Runnable {
    volatile public int water = 10; // initial amount of 10
    public UrineProcessor up;

    public WaterProcessor(UrineProcessor up) {
        this.up = up;
    }

    synchronized public int takeWater(int amount) {
        water += amount;
        return water;
    }

    synchronized public int provideCleanWater(int amount) {

        if (water - amount <= 0) {
            System.out.println(SpaceStation.ANSI_RED + "Water levels critical!" + SpaceStation.ANSI_RESET);
            water = water - amount + (amount - water);
        } else if (water < 10) { // Asynchronous event
            System.out.println(SpaceStation.ANSI_YELLOW + "Water levels declining!" + SpaceStation.ANSI_RESET);
            int humidity = SpaceStation.oxygenInAir;
            takeWater(humidity); // Water will be made from moisture in air
            System.out
            .println(SpaceStation.ANSI_GREEN + "Water Added (Condensated): " + water + SpaceStation.ANSI_RESET);
            SpaceStation.oxygenInAir -= humidity;
            water -= amount;
        } else {
            water -= amount;
        }
        return water;
    }

    @Override
    public void run() {
        while (SpaceStation.on) {
            try {
                Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            synchronized (up) {
                try {
                    up.wait();
                } catch (Exception e) {
                    // e.printStackTrace();
                }
                System.out.println("Water Added (Purified): " + takeWater(up.provideProcessedUrine()));
                synchronized (this) {
                    notify();
                }
            }
        }
    }
}
