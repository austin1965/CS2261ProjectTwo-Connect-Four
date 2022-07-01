import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Scanner;

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

        while (!getPlayerOne().isWinStatus() && !getPlayerTwo().isWinStatus()) {
            this.printBoard();

            System.out.print("Please choose a column (0-" + this.getGameBoard().length + ") to drop the "
                    + this.getPlayerOne().getColor() + " disk: ");
            // FIXME: 6/30/2022 add error handling for user input.
            this.getPlayerOne().setColumnChoice(Integer.parseInt(scanner.next()));

            this.makeMove(this.getPlayerOne().getColorSymbol());
            this.printBoard();
            this.checkWinCondition();


            System.out.print("Please choose a column (0-" + this.getGameBoard().length + ") to drop the "
                    + this.getPlayerTwo().getColor() + " disk: ");
            this.getPlayerTwo().setColumnChoice(Integer.parseInt(scanner.next()));

            this.makeMove(this.getPlayerTwo().getColorSymbol());
            this.printBoard();
            this.checkWinCondition();

            this.getPlayerOne().setWinStatus(true);
        }
    }

    private void fillBoard() {
        for (String[] row: this.getGameBoard()) {
            Arrays.fill(row, EMPTY_SYMBOL);
        }
    }

    private void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6 ");
        for (String[] row: this.getGameBoard()) {
            for (String gamePiece: row) {
                System.out.print("|" + gamePiece);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    private void makeMove(char symbol) {
        // FIXME: 6/30/2022 add check for full column.
        int maxDepth = 0;

        Player currentPlayer;

        if (this.getPlayerOne().getColorSymbol() == symbol) {
            currentPlayer = this.getPlayerOne();
        }
        else {
            currentPlayer = this.getPlayerTwo();
        }

        for (int row = 0; row < this.getMAX_BOARD_LENGTH(); ++row) {

            if (this.getGameBoard()[row][currentPlayer.getColumnChoice()] == this.getEMPTY_SYMBOL() && row > maxDepth) {
                maxDepth = row;
            }
        }

        this.getGameBoard()[maxDepth][currentPlayer.getColumnChoice()] = "" + currentPlayer.getColorSymbol();
    }

    private void checkWinCondition() {
        // FIXME: 6/30/2022 create logic for checking win condition.
        System.out.println();
    }

}
