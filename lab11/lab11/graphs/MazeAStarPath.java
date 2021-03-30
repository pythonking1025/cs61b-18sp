package lab11.graphs;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int x2, y2;
    private Maze maze;
    private PriorityQueue<Node> fringe;

    private class Node {
        private int v;
        private int priority;

        public Node(int v) {
            this.v = v;
            this.priority = distTo[v] + h(v);
        }
    }

    private class NodeCmp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.priority - o2.priority;
        }
    }

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
        fringe = new PriorityQueue<>(new NodeCmp());
        fringe.add(new Node(s));
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
            Node curNode = fringe.poll();
            int now = curNode.v;
            for (int e : maze.adj(now)) {
                if (!marked[e]) {
                    marked[e] = true;
                    distTo[e] = distTo[now] + 1;
                    edgeTo[e] = now;
                    announce();
                    if (e == t) {
                        return;
                    } else {
                        Node tmp = new Node(e);
                        fringe.add(tmp);
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

