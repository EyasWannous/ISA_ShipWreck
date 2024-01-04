import java.util.HashSet;
import java.util.PriorityQueue;

public class UniformCost {
    public HashSet<Node> Nodes = new HashSet<>();

    public PriorityQueue<Node> QueueNodes = new PriorityQueue<>();

    public Node FinalNode;

    public UniformCost(Node node) {
        node.Cost = 0;
        QueueNodes.add(node);
    }

    public void SolveUniformCost() {

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

                if(node.whatIsTheMove())
                    node.Cost = node.Mom.Cost + 2;
                else
                    node.Cost = node.Mom.Cost + 1;

                QueueNodes.add(node);
            }
        }
    }

}
