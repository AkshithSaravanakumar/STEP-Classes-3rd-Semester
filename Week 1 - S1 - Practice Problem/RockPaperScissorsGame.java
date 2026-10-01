import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {

    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    /**
     * Determines the outcome of a single round.
     *
     * @param playerMove   the move made by the player
     * @param computerMove the move made by the computer
     * @return "Player Wins", "Computer Wins", or "Draw"
     */
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== College Coding Arcade: Rock-Paper-Scissors ===");
        System.out.print("Enter number of rounds (suggested 5): ");
        int totalRounds = 5;
        if (scanner.hasNextInt()) {
            totalRounds = scanner.nextInt();
            scanner.nextLine(); // consume newline
        }

        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] roundResults = new String[totalRounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            int roundNum = i + 1;
            String playerMove = "";

            while (true) {
                System.out.print("Round " + roundNum + " - Enter your move (Rock, Paper, Scissors): ");
                playerMove = scanner.nextLine().trim();
                if (playerMove.equalsIgnoreCase("Rock") ||
                    playerMove.equalsIgnoreCase("Paper") ||
                    playerMove.equalsIgnoreCase("Scissors")) {
                    // Standardize casing
                    if (playerMove.equalsIgnoreCase("Rock")) playerMove = "Rock";
                    else if (playerMove.equalsIgnoreCase("Paper")) playerMove = "Paper";
                    else playerMove = "Scissors";
                    break;
                }
                System.out.println("Invalid move! Please enter Rock, Paper, or Scissors.");
            }

            String computerMove = MOVES[random.nextInt(MOVES.length)];
            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            roundResults[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("Round " + roundNum + " — Player: " + playerMove + ", Computer: " + computerMove + " -> " + result);
        }

        // Summary Table
        System.out.println("\n--------------------------------------------------------------");
        System.out.printf("%-8s | %-12s | %-14s | %-15s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8d | %-12s | %-14s | %-15s%n", (i + 1), playerMoves[i], computerMoves[i], roundResults[i]);
        }
        System.out.println("--------------------------------------------------------------");

        double winPercentage = totalRounds > 0 ? ((double) wins / totalRounds) * 100.0 : 0.0;
        System.out.printf("Final Summary (after %d rounds) Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                totalRounds, wins, losses, draws, winPercentage);

        scanner.close();
    }
}
