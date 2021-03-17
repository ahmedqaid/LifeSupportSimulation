
public class SpaceStation {
    public static boolean on = true;
    static final int ASTRONAUTS = 5;
    public static int oxygenInAir = 0;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String args[]) {
        // long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        UrineProcessor up = new UrineProcessor();
        WaterProcessor wp = new WaterProcessor(up);
        OxygenGenerator og = new OxygenGenerator(wp);
        wp.start();
        og.start();
        for (int i = 1; i <= ASTRONAUTS; i++) {
            Astronaut a = new Astronaut(up, wp, og, i);
            a.start();
        }
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        on = false;
        wp.interrupt();
        og.interrupt();

        // try {
        //     Thread.sleep(10000);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // long actualMemUsed = afterUsedMem - beforeUsedMem;
        // System.out.println("Memory Usage: " + actualMemUsed);
    }
}
