import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Legionellosis {

    private final int numNodes;
    private final List<Integer>[] nodeAdjacencies;
    private final int[] perilousLocations;
    private int sickPeople;

    @SuppressWarnings("unchecked")
    public Legionellosis(int locations) {
        this.numNodes = locations;
        this.nodeAdjacencies = new List[this.numNodes];
        this.perilousLocations = new int[this.numNodes];
        this.sickPeople = 0;

        for (int i = 0; i < this.numNodes; i++) {
            this.nodeAdjacencies[i] = new LinkedList<>();
        }
    }

    public void bfsExplore(int root, int maxDepth ) {
        Queue<Integer> waiting = new LinkedList<>();
        boolean[] found = new boolean[this.numNodes];

        int depth = 0;
        int currentLevelNodes = 1;
        int nextLevelNodes = 0;

        waiting.add(root);
        found[root] = true;
        this.perilousLocations[root]++;

        do {
            int node = waiting.remove();
            currentLevelNodes--;

            for (Integer integer : this.nodeAdjacencies[node]) {
                if (!found[integer]) {
                    this.perilousLocations[integer]++;
                    nextLevelNodes++;
                    waiting.add(integer);
                    found[integer] = true;
                }
            }

            if (currentLevelNodes == 0) {
                depth += 1;
                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;
            }

        }
        while ( !waiting.isEmpty() && depth < maxDepth);
    }

    public void bfsTraversal(int root, int maxDepth) {
        bfsExplore(root, maxDepth);
    }

    public void addConnection(int l1, int l2) {
        // l1 -> l2; l2 -> l1
        this.nodeAdjacencies[l1-1].add(l2-1);
        this.nodeAdjacencies[l2-1].add(l1-1);
    }

    public void addInterview(int home, int distance) {
        this.bfsTraversal(home-1, distance);
    }

    public void setSickPeople(int sickPeople) {
        this.sickPeople = sickPeople;
    }

    public List<Integer> solution() {
         List<Integer> sol = new LinkedList<>();

        for (int i = 0; i < this.numNodes; i++) {
            if (this.perilousLocations[i] == this.sickPeople) {
                sol.add(i+1);
            }
        }

        if (sol.size() == 0) {
            sol.add(0);
        }

        return sol;
    }
}
