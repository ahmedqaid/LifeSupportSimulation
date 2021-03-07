public class WaterProcessor {
    public Integer water = 0;

    public Integer takeWater() {
        water++;
        return water;
    }

    public Integer provideCleanWater() {
        // wait
        water--;
        return water;
    }
}
