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
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            if (og.oxygen > 0) { //
                System.out.println(id + "|Oxygen Used: " + og.provideOxygen());
                System.out.println(id + "|Water Vapor Exhaled: " + wp.takeWater(1)); // should be less
            }
            int probability = new Random().nextInt(4);
            if (probability == 0) {
                System.out.println(id + "|Urinating: " + up.takeUrine()); //
                if (wp.water > 0) {
                    System.out.println(id + "|Drinking: " + wp.provideCleanWater());
                }
            }
        }
    }
}
