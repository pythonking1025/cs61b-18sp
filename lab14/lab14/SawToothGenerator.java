package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {

    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next() {
        state ++;
        return normalize(state % period);
    }

    private double normalize(int x){
        return (double) 2 * x / period - 1;
    }
}
