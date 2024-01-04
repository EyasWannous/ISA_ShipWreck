import java.util.ArrayList;

public class PlayBoard {

    public int Rows;
    public int Columns;
    public char[][] Board;

    public PlayBoard(char[][] board, int rows, int columns) {
        this.Rows = rows;
        this.Columns = columns;
        this.Board = board;
    }

    public PlayBoard(PlayBoard playBoard) {
        this.Rows = playBoard.Rows;
        this.Columns = playBoard.Columns;

        this.Board = new char[Rows][Columns];
        for (int i = 0; i < Rows; i++) {
            System.arraycopy(playBoard.Board[i], 0, this.Board[i], 0, Columns);
        }

    }

    public void move(char move) {
        move = Character.toLowerCase(move);
        switch (move) {
            case 'w':
                moveUp();
                break;
            case 's':
                moveDown();
                break;
            case 'a':
                moveLeft();
                break;
            case 'd':
                moveRight();
                break;
            default:
                break;
        }
    }

    protected void moveUp() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'X' || Board[i][j] == ' '
                        || Board[i][j] == 'L'
                        || checkOutSide(new Point(i, j).add(MovesPoint._up)))
                    continue;

                if (open(new Point(i, j), new Point(i, j).add(MovesPoint._up))) {
                    Board[i][j] = ' ';
                    Board[i - 1][j] = ' ';
                    continue;
                }

                if (Board[i - 1][j] != ' ')
                    continue;

                char temp = Board[i][j];
                Board[i][j] = ' ';
                Board[i - 1][j] = temp;

            }
        }
    }

    protected void moveDown() {
        for (int i = Rows - 1; i > -1; i--) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'X' || Board[i][j] == ' '
                        || Board[i][j] == 'L'
                        || checkOutSide(new Point(i, j).add(MovesPoint._down)))
                    continue;

                if (open(new Point(i, j), new Point(i, j).add(MovesPoint._down))) {
                    Board[i][j] = ' ';
                    Board[i + 1][j] = ' ';
                    continue;
                }

                if (Board[i + 1][j] != ' ')
                    continue;

                char temp = Board[i][j];
                Board[i][j] = ' ';
                Board[i + 1][j] = temp;

            }
        }
    }

    protected void moveLeft() {
        for (int j = 0; j < Columns; j++) {
            for (int i = 0; i < Rows; i++) {
                if (Board[i][j] == 'X' || Board[i][j] == ' '
                        || Board[i][j] == 'L'
                        || checkOutSide(new Point(i, j).add(MovesPoint._left)))
                    continue;

                if (open(new Point(i, j), new Point(i, j).add(MovesPoint._left))) {
                    Board[i][j] = ' ';
                    Board[i][j - 1] = ' ';
                    continue;
                }

                if (Board[i][j - 1] != ' ')
                    continue;

                char temp = Board[i][j];
                Board[i][j] = ' ';
                Board[i][j - 1] = temp;

            }
        }
    }

    protected void moveRight() {
        for (int j = Columns - 1; j > -1; j--) {
            for (int i = 0; i < Rows; i++) {
                if (Board[i][j] == 'X' || Board[i][j] == ' '
                        || Board[i][j] == 'L'
                        || checkOutSide(new Point(i, j).add(MovesPoint._right)))
                    continue;

                if (open(new Point(i, j), new Point(i, j).add(MovesPoint._right))) {
                    Board[i][j] = ' ';
                    Board[i][j + 1] = ' ';
                    continue;
                }

                if (Board[i][j + 1] != ' ')
                    continue;

                char temp = Board[i][j];
                Board[i][j] = ' ';
                Board[i][j + 1] = temp;

            }
        }
    }

    protected boolean checkOutSide(Point member) {
        return member.Row >= Rows || member.Row < 0
                || member.Column >= Columns || member.Column < 0;
    }

    protected boolean open(Point Key, Point Lock) {
        return Board[Lock.Row][Lock.Column] == 'L' && Board[Key.Row][Key.Column] == 'k';
    }

    public String checkNextStates() {
        String temp = "{";

        PlayBoard playBoard = new PlayBoard(this);
        playBoard.moveUp();
        if (!this.equals(playBoard))
            temp += " W,";

        playBoard = new PlayBoard(this);
        playBoard.moveDown();
        if (!this.equals(playBoard))
            temp += " S,";

        playBoard = new PlayBoard(this);
        playBoard.moveLeft();
        if (!this.equals(playBoard))
            temp += " A,";

        playBoard = new PlayBoard(this);
        playBoard.moveRight();
        if (!this.equals(playBoard))
            temp += " D";

        temp += " }";
        return temp;
    }

    public ArrayList<PlayBoard> getNextStates() {
        ArrayList<PlayBoard> playBoards = new ArrayList<>();

        if (this.isFinal())
            return playBoards;

        String temp;

        temp = checkNextStates();
        char[] moves = temp.toCharArray();

        for (char c : moves) {
            if (c == 'W') {
                PlayBoard board = new PlayBoard(this);
                board.moveUp();
                playBoards.add(board);
            }

            else if (c == 'S') {
                PlayBoard board = new PlayBoard(this);
                board.moveDown();
                playBoards.add(board);
            }

            else if (c == 'D') {
                PlayBoard board = new PlayBoard(this);
                board.moveRight();
                playBoards.add(board);
            }

            else if (c == 'A') {
                PlayBoard board = new PlayBoard(this);
                board.moveLeft();
                playBoards.add(board);
            }
        }
        return playBoards;
    }

    public boolean isFinal() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Board[i][j] == 'L')
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Rows;
        hash = 31 * hash + Columns;

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                hash = 31 * hash + Board[i][j];
            }
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PlayBoard playBoard))
            return false;

        if (obj == this)
            return true;

        if (playBoard.Rows != this.Rows || playBoard.Columns != this.Columns)
            return false;

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (playBoard.Board[i][j] != this.Board[i][j])
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < Rows; i++) {
            temp.append(" [ ");
            for (int j = 0; j < Columns; j++) {
                if (j == Columns - 1) {
                    temp.append(Board[i][j]).append(" ] ");
                    continue;
                }
                temp.append(Board[i][j]).append(" | ");
            }
            temp.append("\n");
        }
        return temp.toString();
    }
}