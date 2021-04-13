package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {

    int period;
    int state;
    double scale;

    public AcceleratingSawToothGenerator(int period,  double scale) {
        this.period = period;
        this.scale = scale;
        state = 0;
    }

    public double next() {
        state ++;
        if (state == period) {
            period *= scale;
            state = 0;
        }
        return normalize(state);
    }

    private double normalize(int x) {
        return (double) 2 * x / period - 1;
    }
}
