import java.awt.Color;
import edu.princeton.cs.algs4.Picture;


public class SeamCarver {

    private Picture pic;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        pic = new Picture(picture);
        height = picture.height();
        width = picture.width();
    }

    public Picture picture() {
        return pic;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    private boolean valid(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return true;
        }
        return false;
    }

    public double energy(int x, int y) {
        Color up = x > 0 ? pic.get(x - 1, y) : pic.get(width - 1, y);
        Color down = x < width - 1 ? pic.get(x + 1, y) : pic.get(0, y);
        Color left = y > 0 ? pic.get(x, y - 1) : pic.get(x, height - 1);
        Color right = y < height - 1 ? pic.get(x, y + 1) : pic.get(x, 0);
        int rx = left.getRed() - right.getRed();
        int gx = left.getGreen() - right.getGreen();
        int bx = left.getBlue() - right.getBlue();
        int ry = up.getRed() - down.getRed();
        int gy = up.getGreen() - down.getGreen();
        int by = up.getBlue() - down.getBlue();
        return rx * rx + ry * ry + gx * gx + gy * gy + bx * bx + by * by;
    }

    public int[] findHorizontalSeam() {
        int[] ans = new int[width];
        double[][] f = new double[width][height];
        int[][] father = new int[width][height];

        for (int i = 0; i < width; i ++ ) {
            for (int j = 0; j < height; j ++ ) {
                if (i == 0) {
                    f[i][j] = energy(i, j);
                    father[i][j] = -1;
                } else {
                    f[i][j] = energy(i, j);
                    double min_prev = f[i - 1][j];
                    int prev = j;
                    if (j - 1 >= 0 && f[i - 1][j - 1] < min_prev) {
                        min_prev = f[i - 1][j - 1];
                        prev = j - 1;
                    }
                    if (j + 1 < height && f[i - 1][j + 1] < min_prev) {
                        min_prev = f[i - 1][j + 1];
                        prev = j + 1;
                    }
                    f[i][j] += min_prev;
                    father[i][j] = prev;
                }
            }
        }
        int beg = 0;
        double tot = Double.MAX_VALUE;
        for (int i = 0; i < height; i ++ ) {
            if (f[width - 1][i] < tot) {
                tot = f[width - 1][i];
                beg = i;
            }
        }
        for (int i = width - 1, j = beg; i >= 0; j = father[i][j], i -= 1 ) {
            ans[i] = j;
        }
        return ans;
    }

    public int[] findVerticalSeam() {
        int[] ans = new int[height];
        double[][] f = new double[width][height];
        int[][] father = new int[width][height];

        for (int j = 0; j < height; j ++ ) {
            for (int i = 0; i < width; i ++ ) {
                if (j == 0) {
                    f[i][j] = energy(i, j);
                    father[i][j] = -1;
                } else {
                    f[i][j] = energy(i, j);
                    double min_prev = f[i][j - 1];
                    int prev = i;
                    if (i - 1 >= 0 && f[i - 1][j - 1] < min_prev) {
                        min_prev = f[i - 1][j - 1];
                        prev = i - 1;
                    }
                    if (i + 1 < width && f[i + 1][j - 1] < min_prev) {
                        min_prev = f[i + 1][j - 1];
                        prev = i + 1;
                    }
                    f[i][j] += min_prev;
                    father[i][j] = prev;
                }
            }
        }
        int beg = 0;
        double tot = Double.MAX_VALUE;
        for (int i = 0; i < width; i ++ ) {
            if (f[i][height - 1] < tot) {
                tot = f[i][height - 1];
                beg = i;
            }
        }
        for (int i = beg, j = height - 1; j >= 0; i = father[i][j], j -= 1 ) {
            ans[j] = i;
        }
        return ans;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(pic, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeVerticalSeam(pic, seam);
    }

    private boolean isValidSeam(int[] seam) {
        for (int i = 1; i < seam.length; i ++ ) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) {
                return false;
            }
        }
        return true;
    }
}
