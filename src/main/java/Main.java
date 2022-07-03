import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean playing = true;
        Scanner scanner = new Scanner(System.in);

        while (playing) {
            Game connectFour = new Game();
            connectFour.gameLoop();

            System.out.println("Press Q to quit. Press anything else to play again: ");
            String userInput = scanner.nextLine();

            if (userInput.equals("Q") || userInput.equals("q")) {
                playing = false;
            }

        }

        System.out.println("Exiting program.");

    }
}
