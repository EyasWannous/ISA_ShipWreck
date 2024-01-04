import java.util.HashSet;
import java.util.PriorityQueue;

public class ManhattanDistance {
    public HashSet<Node> Nodes = new HashSet<>();

    public PriorityQueue<Node> QueueNodes = new PriorityQueue<>();

    public Node FinalNode;

    public ManhattanDistance(Node node) {
        node.makeHamming();
        QueueNodes.add(node);
    }

    public void SolveHamming() {

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
                kid.makeHamming();

                if(kid.whatIsTheMove())
                    kid.Cost *=  2;

                QueueNodes.add(kid);
            });

        }
    }

}
