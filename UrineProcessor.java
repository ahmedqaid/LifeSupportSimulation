public class UrineProcessor {
    public Integer urine = 0;

    public Integer takeUrine() {
        urine++;
        return urine;
    }

    public Integer provideProcessedUrine() {
        // wait
        urine--;
        return urine;
    }

}
