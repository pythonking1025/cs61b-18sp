package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void addHexagon(TETile[][] world, int size, int x, int y) {
        int width = 2 * (size - 1) + size;
        for (int i = 1; i <= size; i ++ ) {
            for (int j = 1; j <= width; j ++ ) {
                if ((j > size - i) && (j <= width - size + i)){
                    world[y + j - 1][x + i - 1] = Tileset.FLOWER;
                }
            }
        }
        x = x + size;
        for (int i = 1; i <= size; i ++ ) {
            for (int j = 1; j <= width; j ++ ) {
                if ((j > i - 1) && (j <= width - i + 1)){
                    world[y + j - 1][x + i - 1] = Tileset.FLOWER;
                }
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                randomTiles[x][y] = Tileset.MOUNTAIN;
            }
        }
        addHexagon(randomTiles, 5, 12, 12);

        ter.renderFrame(randomTiles);
    }
}
