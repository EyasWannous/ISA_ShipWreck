import java.util.ArrayList;

public class Node extends PlayBoard implements Comparable<Node> {
    public Node Mom;

    public int Cost;

    public ArrayList<Node> Kids;

    public Node(PlayBoard playBoard) {
        super(playBoard);
    }

    public Node(PlayBoard playBoard, Node mom) {
        super(playBoard);
        this.Mom = mom;
    }

    public void getMyKids() {
        Kids = new ArrayList<>();

        ArrayList<PlayBoard> temp = super.getNextStates();
        for (PlayBoard playBoard : temp) {
            Kids.add(new Node(playBoard, this));
        }

    }

    public void makeCost() {
        int counter = 0;
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'k')
                    counter++;
            }
        }
        Cost = counter;
    }

    public void makeHamming() {
        int cost = 0;
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'k'){
                    cost += distance(i,j);
                }
            }
        }
        Cost = cost;
    }

    private int distance(int keyRow, int keyColumn) {
        int distance = 100000;
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'L'){
                    distance = Math.min(distance, Math.abs(j - keyColumn) + Math.abs(i - keyRow));
                }
            }
        }
        return distance;
    }

    public boolean whatIsTheMove(){
        Node Mom = this.Mom;

        Node playBoard = new Node(Mom);
        playBoard.moveUp();
        if (this.equals(playBoard))
            return true;

        playBoard = new Node(Mom);
        playBoard.moveRight();
        if (this.equals(playBoard))
            return true;

        playBoard = new Node(Mom);
        playBoard.moveDown();
        if (this.equals(playBoard))
            return false;

        playBoard = new Node(Mom);
        playBoard.moveLeft();
        if (this.equals(playBoard))
            return false;

        return true;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(Cost, o.Cost);
    }

}
