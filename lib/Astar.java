import java.util.HashSet;
import java.util.PriorityQueue;

public class Astar {

    public HashSet<Node> Nodes = new HashSet<>();

    public PriorityQueue<Node> QueueNodes = new PriorityQueue<>();

    public Node FinalNode;

    public Astar(Node node) {
        node.makeCost();
        QueueNodes.add(node);
    }

    public void SolveAstar() {

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
                node.makeCost();

                if(node.whatIsTheMove())
                    node.Cost += 2;
                else
                    node.Cost += 1;

                QueueNodes.add(node);
            }
        }
    }



}
