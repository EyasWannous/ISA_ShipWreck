import java.io.IOException;

public class Play {
    public String Mode;

    public Node Node;

    public Play(String mode, Node node) {
        Mode = mode;
        Node = node;
    }

    public void managePlay() throws IOException {
        switch (Mode) {
            case "User" -> {
                PlayerMode playerMode = new PlayerMode(Node);
                playerMode.Play();
            }
            case "DFS" -> {
                DFS dfs = new DFS(Node);
                dfs.SolveDFS();
                printAll(dfs.FinalNode,false);
            }
            case "BFS" -> {
                BFS bfs = new BFS(Node);
                bfs.SolveBFS();
                printAll(bfs.FinalNode,false);
            }
            case "Uni" -> {
                UniformCost uni = new UniformCost(Node);
                uni.SolveUniformCost();
                printAll(uni.FinalNode,true);
            }
            case "Hill" -> {
                HillClimbing hill = new HillClimbing(Node);
                hill.SolveHillClimbing();
                printAll(hill.FinalNode,true);
                System.out.println();
                System.out.println("Number of Visited Nodes: " + hill.Nodes.size());
            }
            case "A*" -> {
                Astar astar = new Astar(Node);
                astar.SolveAstar();
                printAll(astar.FinalNode,true);
                System.out.println();
                System.out.println("Number of Visited Nodes: " + astar.Nodes.size());
            }
            case "Hamming" -> {
                ManhattanDistance hamming = new ManhattanDistance(Node);
                hamming.SolveHamming();
                printAll(hamming.FinalNode,true);
                System.out.println();
                System.out.println("Number of Visited Nodes: " + hamming.Nodes.size());
            }
            case "AH" -> {
                AstarDistance astarH = new AstarDistance(Node);
                astarH.SolveAstarDistance();
                printAll(astarH.FinalNode,true);
                System.out.println();
                System.out.println("Number of Visited Nodes: " + astarH.Nodes.size());
            }
        }
    }

    public void printAll(Node node,boolean printCost) {
        Print print = new Print(node);
        print.print(printCost);
    }
}
