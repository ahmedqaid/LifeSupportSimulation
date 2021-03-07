public class Main {
    public static void main(String args[]) {
        UrineProcessor up = new UrineProcessor();
        WaterProcessor wp = new WaterProcessor(up);
        OxygenGenerator og = new OxygenGenerator(wp);


        System.out.print(up.takeUrine());
        System.out.print(up.provideProcessedUrine());

    }

}
