import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public void addConnection(int l1, int l2) {
        // l1 -> l2
        this.nodeAdjacencies[l1 - 1].add(l2 - 1);
        // l2 -> l1
        this.nodeAdjacencies[l2 - 1].add(l1 - 1);
    }

    public void addInterview(int home, int distance) {
        this.bfsExplore(home - 1, distance);
    }

    public void setSickPeople(int sickPeople) {
        this.sickPeople = sickPeople;
    }

    private void bfsExplore(int root, int maxDepth) {
        Queue<Integer> waiting = new LinkedList<>();
        boolean[] found = new boolean[this.numNodes];

        // Depth control
        int depth = 0;
        int currentLevelNodes = 1;
        int nextLevelNodes = 0;

        // Treat the root
        waiting.add(root);
        found[root] = true;
        this.perilousLocations[root]++;

        do {
            // Pop a node
            int node = waiting.remove();
            currentLevelNodes--;

            // Explore its adjacencies
            for (Integer neighbour : this.nodeAdjacencies[node]) {
                if (!found[neighbour]) {
                    this.perilousLocations[neighbour]++;
                    nextLevelNodes++;
                    waiting.add(neighbour);
                    found[neighbour] = true;
                }
            }

            // Depth control
            if (currentLevelNodes == 0) {
                depth += 1;
                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;
            }
        }
        while (!waiting.isEmpty() && depth < maxDepth);
    }

    public String solution() {
        StringBuilder sol = new StringBuilder();

        for (int i = 0; i < this.numNodes; i++) {
            if (this.perilousLocations[i] == this.sickPeople) {
                sol.append(i + 1);
                sol.append(" ");
            }
        }

        if (sol.length() == 0) {
            sol.append(0);
        } else {
            // Removes trailing white space
            sol.setLength(sol.length() - 1);
        }

        return sol.toString();
    }
}
