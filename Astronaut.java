import java.security.InvalidParameterException;
import java.util.Random;
import java.util.concurrent.Callable;

public class Astronaut implements Callable<Integer> {
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

    public Integer call() throws InvalidParameterException {
        while (SpaceStation.on) {
            try {
                Thread.sleep(new Random().nextInt(4000 - 1000) + 1000);
            } catch (Exception e) {
            }
            int probability = new Random().nextInt(6); // A probability of 17% of urinating
            if (probability == 0) {
                System.out.println("[Astronaut " + id + "] Urinating: " + up.takeUrine());
            }
            int probability2 = new Random().nextInt(6); // A probability of 17% of drinking water
            if (probability2 == 0) {
                System.out.println("[Astronaut " + id + "] Drinking: " + wp.provideCleanWater(1));
            }

        }
        return 1;
    }
}
