import java.util.Random;

public class OxygenGenerator extends Thread {
    volatile public int oxygen = 10;
    public WaterProcessor wp;

    public OxygenGenerator(WaterProcessor wp) {
        this.wp = wp;
    }

    public int makeOxygen(double perc) {
        int water = (int) (wp.water * perc);
        wp.provideCleanWater(water);
        oxygen += water;
        return oxygen;
    }

    synchronized public int provideOxygen(int amount) {
        if (oxygen - amount <= 0) {
            System.out.println(SpaceStation.ANSI_RED + "Oxygen levels critical!" + SpaceStation.ANSI_RESET);
            oxygen = oxygen - amount + (amount - oxygen);
        } else if (oxygen - amount < 10) { // Asynchronous event
            System.out.println(SpaceStation.ANSI_YELLOW + "Oxygen levels declining!" + SpaceStation.ANSI_RESET);
            oxygen -= amount;
            makeOxygen(1); // More Oxygen to be made urgently
            System.out.println(SpaceStation.ANSI_GREEN + "Oxygen Added (Recovered): " + oxygen + SpaceStation.ANSI_RESET);
        } else {
            oxygen -= amount;
        }
        return oxygen;
    }

    public void run() {
        while (SpaceStation.on) {

            if (wp.water < 10) {
                synchronized (wp) {
                    try {
                        wp.wait();
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(new Random().nextInt(2000 - 1000) + 1000);
            } catch (Exception e) {
                // e.printStackTrace();
            }

            if (wp.water > 0) {
                System.out.println("Oxygen Added: " + makeOxygen(0.5));
            }
            System.out.println("Oxygen Released: " + provideOxygen(SpaceStation.ASTRONAUTS));
            SpaceStation.oxygenInAir += SpaceStation.ASTRONAUTS;
        }
    }
}
