import java.util.Random;

public class WaterProcessor extends Thread {
    volatile public int water = 10;
    public UrineProcessor up;

    public WaterProcessor(UrineProcessor up) {
        this.up = up;
    }

    synchronized public int takeWater(int amount) {
        water += amount;
        return water;
    }

    synchronized public int provideCleanWater(int amount) {
        if (water < 10) {
            System.out.println(SpaceStation.ANSI_YELLOW + "Water levels declining!" + SpaceStation.ANSI_RESET);
            water -= amount;
            int humidity = SpaceStation.oxygenInAir;
            takeWater(humidity); //////////////
            SpaceStation.oxygenInAir -= humidity; ///////////
        }
        if (water - amount < 0) {
            System.out.println(SpaceStation.ANSI_RED + "Water levels critical!" + SpaceStation.ANSI_RESET);
            water = water - amount + (amount - water);
        } else {
            water -= amount;
        }
        return water;
    }

    public void run() {
        while (SpaceStation.on) {

            try {
                Thread.sleep(new Random().nextInt(3000 - 1000) + 1000);
            } catch (Exception e) {
            }
            synchronized (up) {
                try {
                    up.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Water Added (Purified): " + takeWater(up.provideProcessedUrine()));
                synchronized (this) {
                    notify();
                }
            }
        }
    }
}
