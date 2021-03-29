package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int x2, y2;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> fringe;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        x2 = targetX;
        y2 = targetY;
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        fringe = new LinkedList<>();
        fringe.add(s);
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x1 = maze.toX(v);
        int y1 = maze.toY(v);
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        while (!fringe.isEmpty()) {
            int now = fringe.poll();
            int minDis = 0x7f7f7f7f;
            int minEdge = -1;
            for (int e : maze.adj(now)) {
                if (!marked[e] && (minEdge == -1 || minDis >= h(e))) {
                    minEdge = e;
                }
            }
            marked[minEdge] = true;
            announce();
            distTo[minEdge] = distTo[now] + 1;
            edgeTo[minEdge] = now;
            announce();
            if (minEdge == t) {
                return;
            } else {
                fringe.add(minEdge);
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

