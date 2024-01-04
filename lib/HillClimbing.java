import java.util.HashSet;
import java.util.PriorityQueue;

public class HillClimbing {
    public HashSet<Node> Nodes = new HashSet<>();

    public PriorityQueue<Node> QueueNodes = new PriorityQueue<>();

    public Node FinalNode;

    public HillClimbing(Node node) {
        node.makeCost();
        QueueNodes.add(node);
    }

    public void SolveHillClimbing() {

        while (!QueueNodes.isEmpty()) {
            Node temp = QueueNodes.poll();

            if (Nodes.contains(temp))
                continue;

            Nodes.add(temp);

            if (temp.isFinal()) {
                FinalNode = temp;
                return;
            }

            temp.getMyKids();
            if (temp.Kids == null)
                continue;

            temp.Kids.forEach((kid) -> {
                kid.makeCost();
                QueueNodes.add(kid);
            });

        }
    }
}
