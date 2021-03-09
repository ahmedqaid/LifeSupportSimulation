import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static boolean on = false;
    static final int ASTRONAUTS = 5;
    public static void main(String args[]) {
        UrineProcessor up = new UrineProcessor();
        WaterProcessor wp = new WaterProcessor(up);
        OxygenGenerator og = new OxygenGenerator(wp);
        // Astronaut a = new Astronaut(wp, og);

        up.start();
        wp.start();
        og.start();
        // a.start();
        // LinkedBlockingQueue<Astronaut> as = new LinkedBlockingQueue<>(5);
        for (int i = 1; i <= ASTRONAUTS; i++) {
            // Astronaut a = new Astronaut(wp, og, i+1);
            // as.add(new Astronaut(wp, og, i+1));
            // as.add(a);
            Astronaut a = new Astronaut(up, wp, og, i);
            a.start();
        }

        // loop

    }
}
