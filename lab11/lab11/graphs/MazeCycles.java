package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int beg;
    private boolean cycleFound = false;
    private boolean flag = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        for (int i = 0; i < maze.N() * maze.N(); i ++ )
        {
            if (!marked[i]) {
                dfs(i, i);
                if (cycleFound){
                    return;
                }
            }
        }
    }

    // Helper methods go here
    void dfs(int v, int father) {
        marked[v] = true;
        announce();
        for (int e : maze.adj(v)) {
            if (cycleFound) return;
            if (e == father) {
                continue;
            }
            if (marked[e]) {
                beg = e;
                edgeTo[e] = v;
                cycleFound = true;
                flag = true;
                return;
            } else {
                dfs(e, v);
                if (flag) {
                    edgeTo[e] = v;
                    announce();
                    if (v == beg) {
                        flag = false;
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}

