import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpaceStation {
    public static boolean on = true;
    static final int ASTRONAUTS = 5;
    public static int oxygenInAir = 10;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String args[]) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        UrineProcessor up = new UrineProcessor();
        WaterProcessor wp = new WaterProcessor(up);
        OxygenGenerator og = new OxygenGenerator(wp);

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
        exec.scheduleAtFixedRate(wp, 0, 30, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(og, 0, 30, TimeUnit.SECONDS);
        
        ExecutorService pool = Executors.newFixedThreadPool(ASTRONAUTS);
        for (int i = 1; i <= ASTRONAUTS; i++) {
            pool.submit(new Astronaut(up, wp, og, i));
        }

        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdown();
        pool.shutdown();
        on = false;
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        System.out.println("Memory Usage: " + actualMemUsed);
    }
}
