import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        char[][] tempBoard = createNewBoard();
        int tempRows = tempBoard.length;
        int tempColumns = tempBoard[0].length;

        PlayBoard temPlayBoard = new PlayBoard(tempBoard, tempRows, tempColumns);

        char input = ' ';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (input != 'q') {

            System.out.println();
            System.out.println("[1]: Manual Mode (User)");
            System.out.println("[2]: DFS");
            System.out.println("[3]: BFS");
            System.out.println("[4]: Uniform cost");
            System.out.println("[5]: HillClimbing");
            System.out.println("[6]: A*");
            System.out.println("[7]: ManhattanDistance");
            System.out.println("[8]: A* with ManhattanDistance");
            System.out.println("Please choose one or 'q' to quit");
            System.out.println();

            input = reader.readLine().charAt(0);
            System.out.println();

            if (input == 'q')
                continue;

            if (input == '1') {
                Play play = new Play("User", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '2') {
                Play play = new Play("DFS", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '3') {
                Play play = new Play("BFS", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '4') {
                Play play = new Play("Uni", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '5') {
                Play play = new Play("Hill", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '6') {
                Play play = new Play("A*", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '7') {
                Play play = new Play("Hamming", new Node(temPlayBoard));
                play.managePlay();
            }

            else if (input == '8') {
                Play play = new Play("AH", new Node(temPlayBoard));
                play.managePlay();
            }


            System.out.println();
            System.out.println("To Repeat the game press any Key or 'q' to quit");
            System.out.println();
            input = reader.readLine().charAt(0);
        }
    }

    public static char[][] createNewBoard() throws IOException {

        int rows,columns;
        boolean end = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Rows:");
        rows = Character.getNumericValue(reader.readLine().charAt(0));
        System.out.println("Columns:");
        columns = Character.getNumericValue(reader.readLine().charAt(0));

//        char[] letters = {
//                'A', 'B', 'C', 'D', 'E', 'F', 'G',
//                'H', 'I', 'J', 'K', 'L', 'M', 'N',
//                'O', 'P', 'Q', 'R', 'S', 'T', 'U',
//                'V', 'W', 'X', 'Y', 'Z'
//        };
//        int counterOfKey = 0;

        char[][] board = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }


        while (end) {
            int row, column;

            System.out.println("Enter Character:");
            System.out.println("X: add Wall");
            System.out.println("K: add Key and Lock of it");
            System.out.println("S: Stop");
            char key = reader.readLine().charAt(0);
            key = Character.toLowerCase(key);
            switch (key) {
                case 'x':
                    System.out.println("Enter Row of the Wall");
                    row = Character.getNumericValue(reader.readLine().charAt(0));
                    System.out.println("Enter Column of the Wall");
                    column = Character.getNumericValue(reader.readLine().charAt(0));
                    board[row][column] = 'X';
                    break;

                case 'k':
                    System.out.println("Enter Row of the Key");
                    row = Character.getNumericValue(reader.readLine().charAt(0));
                    System.out.println("Enter Column of the Key");
                    column = Character.getNumericValue(reader.readLine().charAt(0));
                    board[row][column] = 'k';

                    System.out.println("Enter Row of the Lock ");
                    row = Character.getNumericValue(reader.readLine().charAt(0));
                    System.out.println("Enter Column of the Lock");
                    column = Character.getNumericValue(reader.readLine().charAt(0));
                    board[row][column] = 'L';

//                    counterOfKey++;
                    break;

                case 's':
                    end = false;
                    break;

                default:
                    System.out.println("Invalid Character");
                    System.out.println("Enter Character again");
//                    key = reader.readLine().charAt(0);
            }
        }

        return board;

    }
}
