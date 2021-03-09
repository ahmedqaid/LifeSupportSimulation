import java.util.Random;

public class Astronaut extends Thread {
    UrineProcessor up;
    WaterProcessor wp;
    OxygenGenerator og;
    int id;

    public Astronaut(UrineProcessor up, WaterProcessor wp, OxygenGenerator og, int id) {
        this.up = up;
        this.wp = wp;
        this.og = og;
        this.id = id;
    }

    public void run() {
        while (SpaceStation.on) {
            try {
                Thread.sleep(new Random().nextInt(4000 - 1000) + 1000);
            } catch (Exception e) {
            }
            int probability = new Random().nextInt(6);
            if (probability == 0) {
                System.out.println(id + " | Urinating: " + up.takeUrine()); //
            }
            int probability2 = new Random().nextInt(6);
            if (probability2 == 0) {
                System.out.println(id + " | Drinking: " + wp.provideCleanWater(1));
            }

        }
    }
}
