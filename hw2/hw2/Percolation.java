package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private boolean[] world;
    private WeightedQuickUnionUF map1;
    private WeightedQuickUnionUF map2;
    private int NumOfOpenSite;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    private int calcPos(int row, int col) {
        return N * row + col + 1;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        this.N = N;
        world = new boolean[N * N + 2];
        for (int i = 1; i <= N * N; i ++ ) {
            world[i] = false;
        }

        map1 = new WeightedQuickUnionUF(N * N + 1);
        map2 = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 1; i <= N; i ++ ) {
            map1.union(0, i);
            map2.union(0, i);
            map2.union(N * N + 1, N * N - i + 1);
        }
    }

    private boolean isValidIdx(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        }
        return true;
    }

    public void open(int row, int col) {
        if (!isValidIdx(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        int pos = calcPos(row, col);
        world[pos] = true;
        NumOfOpenSite += 1;

        for (int i = 0; i < 4; i ++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (isValidIdx(x, y) && isOpen(x, y)) {
                int NewPos = calcPos(x, y);
                map1.union(NewPos, pos);
                map2.union(NewPos, pos);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (!isValidIdx(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return world[calcPos(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (!isValidIdx(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, col)) {
            return false;
        }

        return map1.connected(0, calcPos(row, col));
    }

    public int numberOfOpenSites() {
        return NumOfOpenSite;
    }

    public boolean percolates() {
        return map2.connected(0, N * N + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(0, 0);
        System.out.println(p.numberOfOpenSites());
        p.open(1, 0);
        p.open(2, 0);
        System.out.println(p.numberOfOpenSites());
        p.open(3, 0);
        p.open(3, 0);
        p.open(3, 0);
        System.out.println(p.numberOfOpenSites());
        p.open(4, 0);
        p.open(3, 1);
        System.out.println(p.isFull(1, 3));
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());

    }

}
