package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.lang.invoke.SwitchPoint;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private int moves;
        private SearchNode prev;
        private Integer priority;

        private SearchNode(WorldState state, SearchNode prev) {
            this.state = state;
            this.moves = prev == null ? 0 : prev.moves + 1;
            this.prev = prev;
            if (edCaches.containsKey(this.state)) {
                int ed = edCaches.get(this.state);
                priority = this.moves + ed;
            } else {
                int ed = this.state.estimatedDistanceToGoal();
                edCaches.put(this.state, ed);
                priority = this.moves + ed;
            }
        }
    }

    private class SearchNodeCMP implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode left, SearchNode right) {
            return left.priority.compareTo(right.priority);
        }
    }

    private Map<WorldState, Integer> edCaches = new HashMap<>();
    private Stack<WorldState> path = new Stack<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeCMP());
        SearchNode now = new SearchNode(initial, null);
        pq.insert(now);

        while (!pq.isEmpty()) {
            now = pq.delMin();
            if (now.state.isGoal()) {
                break;
            }
            for (WorldState x : now.state.neighbors()) {
                if (now.prev != null && x.equals(now.prev.state)) {
                    continue;
                }
                SearchNode newNode = new SearchNode(x, now);
                pq.insert(newNode);
            }
        }
        for (SearchNode Node = now; Node != null; Node = Node.prev) {
            path.push(Node.state);
        }
    }

    public int moves() {
        return path.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return path;
    }
}
