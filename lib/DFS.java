import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public HashSet<Node> Nodes = new HashSet<>();

    public Stack<Node> StackNodes = new Stack<>();

    public Node FinalNode;

    public DFS(Node node) {
        StackNodes.add(node);
    }

    public void SolveDFS() {

        while (!StackNodes.isEmpty()) {
            Node temp = StackNodes.pop();

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

//            temp.Kids.forEach((kid) -> StackNodes.push(kid));
            StackNodes.addAll(temp.Kids);

        }
    }
}
