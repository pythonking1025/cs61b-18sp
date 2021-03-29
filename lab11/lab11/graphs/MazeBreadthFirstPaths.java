package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private Queue<Integer> fringe;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        fringe = new LinkedList<>();
        fringe.add(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        while (!fringe.isEmpty()) {
            int now = fringe.poll();
            for (int e : maze.adj(now)) {
                if (!marked[e]) {
                    marked[e] = true;
                    announce();
                    distTo[e] = distTo[now] + 1;
                    edgeTo[e] = now;
                    announce();
                    if (e == t) {
                        return;
                    } else {
                        fringe.add(e);
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

