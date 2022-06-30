import lombok.Getter;

import java.util.Arrays;

@Getter
public class Game {
    private String[][] gameBoard = new String[6][7];

    public Game() {
        this.fillBoard();
    }

    public void fillBoard() {
        for (String[] row: this.gameBoard) {
            Arrays.fill(row, " ");
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
    }

}
