
public class SpaceStation {
    public static boolean on = true;
    static final int ASTRONAUTS = 5;
    public static int oxygenInAir = 0;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String args[]) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

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
        
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        try {
            Thread.sleep(1000); //
        } catch (Exception e) {}
        System.out.println("Memory Usage: " + actualMemUsed);
    }
}
