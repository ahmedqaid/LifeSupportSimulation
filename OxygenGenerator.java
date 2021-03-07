public class OxygenGenerator {
    public int oxygen;
    public WaterProcessor wp;

    public OxygenGenerator(WaterProcessor wp) {
        this.wp = wp;
    }

    public int makeOxygen() {
        // call other class
        wp.provideCleanWater();
        oxygen++;
        return oxygen;
    }

    public int provideOxygen() {
        oxygen++;
        return oxygen;
    }
}
