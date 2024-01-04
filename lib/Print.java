import java.util.ArrayList;
import java.util.Collections;

public class Print {
    public Node FinalNode;

    public Print(Node finalNode) {
        FinalNode = finalNode;
    }

    public void print(boolean printCost) {
        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(FinalNode);
        Node temp = FinalNode.Mom;

        // int totalCost = 0;
        while (temp != null) {
            // totalCost += temp.Cost;
            nodes.add(temp);
            temp = temp.Mom;
        }

        Collections.reverse(nodes);

        nodes.forEach(System.out::println);

        if (printCost) {
            System.out.println("totalCost: " + nodes.size());
        }
    }
}
