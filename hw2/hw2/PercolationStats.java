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

    public double stddv() {
        return StdStats.stddev(res);
    }

    public double confidenceLow() {
        return this.mean() - 1.96 * (this.stddv() / Math.sqrt(size));
    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * (this.stddv() / Math.sqrt(size));
    }

    public static void main(String[] args) {
        PercolationStats tmp = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(tmp.mean());
        System.out.println(tmp.confidenceHigh());

    }
}
