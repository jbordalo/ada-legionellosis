import java.util.LinkedList;
import java.util.List;

public class Legionellosis {

    private final int numNodes;
    private final List<Integer>[] nodeAdjacencies;
    private final int[] perilousLocations;

    @SuppressWarnings("unchecked")
    public Legionellosis(int locations) {
        this.numNodes = locations;
        this.nodeAdjacencies = new List[this.numNodes];
        this.perilousLocations = new int[this.numNodes];

        for (int i = 0; i < this.numNodes; i++) {
            this.nodeAdjacencies[i] = new LinkedList<>();
        }
    }

    public void addConnection(int l1, int l2) {
        // l1 -> l2; l2 -> l1
        this.nodeAdjacencies[l1].add(l2);
        this.nodeAdjacencies[l2].add(l1);
    }

    public void addInterview(int home, int distance) {

    }

    public int[] solution() {
        return this.perilousLocations;
    }

}
