import lombok.Getter;
import lombok.Setter;

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
            checkVerticalWin();

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
        if (this.checkWinCondition()) {

            if (this.getPlayerOne().isWinStatus()) {
                System.out.println("Player " + this.getPlayerOne().getUserName() + " has won by connecting 4 "
                        + this.getPlayerOne().getColor() + " pieces.");
            }
            else {
                System.out.println("Player " + this.getPlayerTwo().getUserName() + " has won by connecting 4 "
                        + this.getPlayerTwo().getColor() + " pieces.");
            }

            return true;
        }
        return false;
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
        
        int maxDepth = 0;

        Player currentPlayer;

        if (this.getPlayerOne().getColorSymbol() == symbol) {
            currentPlayer = this.getPlayerOne();
        }
        else {
            currentPlayer = this.getPlayerTwo();
        }

        for (int row = 0; row < this.getMAX_BOARD_LENGTH(); ++row) {

            if (this.getGameBoard()[row][currentPlayer.getColumnChoice()].equals(this.getEMPTY_SYMBOL())  && row > maxDepth) {
                maxDepth = row;
            }
        }

        this.getGameBoard()[maxDepth][currentPlayer.getColumnChoice()] = "" + currentPlayer.getColorSymbol();
    }

    private boolean checkWinCondition() {
        if (this.checkHorizontalWin()) {
            return true;
        }
        else if (this.checkVerticalWin()) {
            return true;
        }
        else {
            return checkDiagonalWin();
        }
    }

    private boolean checkHorizontalWin() {
        Stack<Character> characterStack = new Stack<Character>();

        // Look at every cell in game board row-wise.
        for (int row = 0; row < this.getMAX_BOARD_LENGTH(); ++row) {
            for (int column = 0; column < this.getMAX_BOARD_WIDTH(); ++column) {
                if (manageStackForAdjacentPieces(characterStack, row, column)) {
                    return true;
                }
            }

            // Check if on the last iteration, we hit 4 or more elements.
            if (characterStack.size() >= 4) {
                this.determineWinner(characterStack);
                return true;
            }
            // otherwise, empty the stack for the next row of horizontal checks
            else {
                characterStack.removeAllElements();
            }
        }

        // No wins currently present in board.
        return false;
    }

    private boolean checkVerticalWin() {
        Stack<Character> characterStack = new Stack<Character>();

        // Look at every cell in the game board column-wise.
        for (int column = 0; column < this.getMAX_BOARD_WIDTH(); ++column) {
            for (int row = 0; row < this.getMAX_BOARD_LENGTH(); ++row) {
                if (manageStackForAdjacentPieces(characterStack, row, column)) {
                    return true;
                }
            }
            // Check if on the last iteration, we hit 4 or more elements.
            if (characterStack.size() >= 4) {
                this.determineWinner(characterStack);
                return true;
            }
            // otherwise, empty the stack for the next row of horizontal checks
            else {
                characterStack.removeAllElements();
            }
        }
        return false;
    }

    private boolean manageStackForAdjacentPieces(Stack<Character> characterStack, int row, int column) {
        // if mid-loop, we hit 4 or more in a row, end.
        if (characterStack.size() >= 4) {
            this.determineWinner(characterStack);
            return true;
        }

        // if space isn't empty and stack is empty, add item to stack.
        else if ((!this.getGameBoard()[row][column].equals(this.getEMPTY_SYMBOL())
                && characterStack.isEmpty())) {
            characterStack.push(this.getGameBoard()[row][column].charAt(0));
        }

        // if stack isn't empty, examine further.
        else if (!characterStack.isEmpty()) {

            // if the top item of the stack isn't equal to the current cell, empty it.
            if (!this.getGameBoard()[row][column].equals("" + characterStack.peek())) {
                characterStack.removeAllElements();

                // However, start the stack over if it's a player piece.
                if (this.getGameBoard()[row][column].equals("" + this.getPlayerOne().getColorSymbol())
                        || this.getGameBoard()[row][column].equals("" + this.getPlayerTwo().getColorSymbol())) {
                    characterStack.push(this.getGameBoard()[row][column].charAt(0));
                }
            }

            // if the top of the stack and the current cell match, put it on the stack
            else if (this.getGameBoard()[row][column].equals("" + characterStack.peek())) {
                characterStack.push(this.getGameBoard()[row][column].charAt(0));
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        // FIXME: 7/1/2022 create logic for diagonal win
        return false;
    }

    private void determineWinner(Stack<Character> winnerStack) {
        if ((winnerStack.peek() == this.getPlayerOne().getColorSymbol())) {
            this.getPlayerOne().setWinStatus(true);
        }
        else if (winnerStack.peek() == this.getPlayerTwo().getColorSymbol()) {
            this.getPlayerTwo().setWinStatus(true);
        }
    }

}
