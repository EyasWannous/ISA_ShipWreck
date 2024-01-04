import java.util.HashSet;
import java.util.PriorityQueue;

public class AstarDistance {
    public HashSet<Node> Nodes = new HashSet<>();

    public PriorityQueue<Node> QueueNodes = new PriorityQueue<>();

    public Node FinalNode;

    public AstarDistance(Node node) {
        node.makeHamming();
        QueueNodes.add(node);
    }

    public void SolveAstarDistance() {

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

            for (Node node : temp.Kids) {
                node.makeHamming();

                if(node.whatIsTheMove())
                    node.Cost += 2;
                else
                    node.Cost += 1;

                QueueNodes.add(node);
            }
        }
    }

}
