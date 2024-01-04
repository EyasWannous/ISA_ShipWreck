public class Point {
    public int Row;
    public int Column;

    Point(int row, int column) {
        this.Row = row;
        this.Column = column;
    }

    public Point add(Point other) {
        this.Row += other.Row;
        this.Column += other.Column;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Row;
        hash = 31 * hash + Column;
        return hash;
    }
}