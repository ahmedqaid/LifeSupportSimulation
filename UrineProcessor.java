public class UrineProcessor {
    public int urine = 0;

    public int takeUrine() {
        urine++;
        return urine;
    }

    public int provideProcessedUrine() {
        // wait
        urine--;
        return urine;
    }

}
