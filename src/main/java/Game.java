import javafx.scene.control.Cell;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

@Getter
public class Game {
    private final int MAX_BOARD_WIDTH = 7;
    private final int MAX_BOARD_LENGTH = 6;
    private final String EMPTY_SYMBOL = " ";
    private String[][] gameBoard = new String[MAX_BOARD_LENGTH][MAX_BOARD_WIDTH];

    @Setter
    private Player playerOne;

    @Setter
    private Player PlayerTwo;

    public Game() {
        this.fillBoard();
        this.setPlayerOne(new Player('R', "RED", "user 1"));
        this.setPlayerTwo(new Player('Y', "YELLOW", "user 2"));
    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        this.printBoard();
        while (!getPlayerOne().isWinStatus() && !getPlayerTwo().isWinStatus()) {

            System.out.print("Please choose a column (0-" + this.getGameBoard().length + ") to drop the "
                    + this.getPlayerOne().getColor() + " disk: ");
            // FIXME: 6/30/2022 add error handling for user input.
            // FIXME: 6/30/2022 add check for full column.
            this.getPlayerOne().setColumnChoice(Integer.parseInt(scanner.next()));

            this.makeMove(this.getPlayerOne().getColorSymbol());
            this.printBoard();

            if (reviewGameStatus()) {
                break;
            }

            System.out.print("Please choose a column (0-" + this.getGameBoard().length + ") to drop the "
                    + this.getPlayerTwo().getColor() + " disk: ");
            this.getPlayerTwo().setColumnChoice(Integer.parseInt(scanner.next()));

            this.makeMove(this.getPlayerTwo().getColorSymbol());
            this.printBoard();
            if (reviewGameStatus()) {
                break;
            }
            // FIXME: 7/1/2022 Add check for full board tie scenario.
        }
    }

    private boolean reviewGameStatus() {
        if (this.winner()) {

            if (this.getPlayerOne().isWinStatus()) {
                System.out.println("Player " + this.getPlayerOne().getUserName() + " has won by connecting 4 "
                        + this.getPlayerOne().getColor() + " pieces.");
            } else {
                System.out.println("Player " + this.getPlayerTwo().getUserName() + " has won by connecting 4 "
                        + this.getPlayerTwo().getColor() + " pieces.");
            }

            return true;
        }
        return false;
    }

    private void fillBoard() {
        for (String[] row : this.getGameBoard()) {
            Arrays.fill(row, EMPTY_SYMBOL);
        }
    }

    private void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6 ");
        for (String[] row : this.getGameBoard()) {
            for (String gamePiece : row) {
                System.out.print("|" + gamePiece);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    private void makeMove(char symbol) {

        int maxDepth = 0;

        Player currentPlayer;

        if (this.getPlayerOne().getColorSymbol() == symbol) {
            currentPlayer = this.getPlayerOne();
        } else {
            currentPlayer = this.getPlayerTwo();
        }

        for (int row = 0; row < this.getMAX_BOARD_LENGTH(); ++row) {

            if (this.getGameBoard()[row][currentPlayer.getColumnChoice()].equals(this.getEMPTY_SYMBOL()) && row > maxDepth) {
                maxDepth = row;
            }
        }

        this.getGameBoard()[maxDepth][currentPlayer.getColumnChoice()] = "" + currentPlayer.getColorSymbol();
    }


    private boolean winner() {
        // Potential directions
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};

        // Iterate through each direction
        for (int[] d : directions) {
            int dRow = d[0];
            int dColumn = d[1];

            // Iterate through Each cell
            for (int row = 0; row < this.getMAX_BOARD_LENGTH(); row++) {
                for (int column = 0; column < this.getMAX_BOARD_WIDTH(); column++) {
                    // Determine last rows
                    int lastRow = row + 3 * dRow;
                    int lastColumn = column + 3 * dColumn;

                    // If the calculated cell is in bounds, investigate further
                    if (0 <= lastRow && lastRow < this.getMAX_BOARD_LENGTH()
                            && 0 <= lastColumn && lastColumn < this.getMAX_BOARD_WIDTH()) {

                        String w = this.getGameBoard()[row][column];

                        // If field not empty and all cells homogenous, then we have a winner.
                        if (!w.equals(this.getEMPTY_SYMBOL()) && w.equals(this.getGameBoard()[row + dRow][column + dColumn])
                                && w.equals(this.getGameBoard()[row + 2 * dRow][column + 2 * dColumn])
                                && w.equals(this.getGameBoard()[lastRow][lastColumn])) {

                            // Determine winner
                            if (w.equals("" + this.getPlayerOne().getColorSymbol())) {
                                this.getPlayerOne().setWinStatus(true);
                            } else if (w.equals("" + this.getPlayerTwo().getColorSymbol())) {
                                this.getPlayerTwo().setWinStatus(true);
                            }

                            return true; // Winner
                        }
                    }
                }
            }
        }
        return false; // no winner
    }
}