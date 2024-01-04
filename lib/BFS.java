import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public HashSet<Node> Nodes = new HashSet<>();

    public Queue<Node> QueueNodes = new LinkedList<>();

    public Node FinalNode;

    public BFS(Node node) {
        QueueNodes.add(node);
    }

    public void SolveBFS() {

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

            QueueNodes.addAll(temp.Kids);

        }
    }
}
