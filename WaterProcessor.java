public class WaterProcessor {
    public int water = 0;
    public UrineProcessor up;

    public WaterProcessor(UrineProcessor up) {
        this.up = up;
    }

    public int takeCondensatedWater() {
        water++;
        return water;
    }

    public int takeUrineWater() {
        up.takeUrine();
        water++;
        return water;
    }

    public int provideCleanWater() {
        // wait
        water--;
        return water;
    }
}
