package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] res;
    private int size;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        res = new double[T];
        size = T;

        for (int i = 0; i < T; i ++ ) {
            Percolation tmp = pf.make(N);
            while (!tmp.percolates()) {
                int x = StdRandom.uniform(0, N);
                int y = StdRandom.uniform(0, N);
                tmp.open(x, y);
            }
            res[i] = (double) tmp.numberOfOpenSites() / (double) (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(res);
    }

    public double stddev() {
        return StdStats.stddev(res);
    }

    public double confidenceLow() {
        return this.mean() - 1.96 * (this.stddev() / Math.sqrt(size));
    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * (this.stddev() / Math.sqrt(size));
    }

}
