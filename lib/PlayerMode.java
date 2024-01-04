import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayerMode {
    public PlayBoard PlayBoard;

    public PlayerMode(PlayBoard playBoard) {
        PlayBoard = playBoard;
    }

    public void Play() throws IOException {
        char input = ' ';

        System.out.println("Press {'W' 'S' 'A' 'D'} To Move.");
        System.out.println("Press 'M' To See Possible Moves.");
        System.out.println("Current State :\n");
        System.out.println(PlayBoard);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (input != 'q' && input != 'Q' && !PlayBoard.isFinal()) {

            input = reader.readLine().charAt(0);
            System.out.println();

            if (input == 'M' || input == 'm') {
                System.out.println(PlayBoard.checkNextStates());
                ArrayList<PlayBoard> tempPlayBoards = PlayBoard.getNextStates();
                for (PlayBoard playBoard : tempPlayBoards) {
                    System.out.println(playBoard);
                }
            } else {
                PlayBoard.move(input);
                System.out.println("Current State :\n");
                System.out.println(PlayBoard);
            }
        }
        reader.close();
    }
}
