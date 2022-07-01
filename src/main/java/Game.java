import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Scanner;

@Getter
@Setter
public class Game {
    private int MAX_BOARD_WIDTH = 7;
    private int MAX_BOARD_LENGTH = 6;
    private String EMPTY_SYMBOL = " ";
    private String[][] gameBoard = new String[MAX_BOARD_LENGTH][MAX_BOARD_WIDTH];
    private Player playerOne;
    private Player PlayerTwo;

    public Game() {
        this.fillBoard();
        this.setPlayerOne(new Player('R', "RED", "user 1"));
        this.setPlayerTwo(new Player('Y', "YELLOW", "user 2"));
    }

    public void fillBoard() {
        for (String[] row: this.gameBoard) {
            Arrays.fill(row, EMPTY_SYMBOL);
        }
    }

    public void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6 ");
        for (String[] row: this.gameBoard) {
            for (String gamePiece: row) {
                System.out.print("|" + gamePiece);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (!getPlayerOne().isWinStatus() && !getPlayerTwo().isWinStatus()) {
            System.out.print("Please choose a column (0-" + this.getGameBoard().length + ") to drop the "
                    + getPlayerOne().getColor() + " disk: ");
            getPlayerOne().setWinStatus(true);
        }
        
    }

    public void makeMove(int playerNumber) {
        // FIXME: 6/30/2022 
    }

}
