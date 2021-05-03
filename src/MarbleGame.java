import java.util.Random;
import java.util.Scanner;

public class MarbleGame {
    private static int randomNumber;
    private Scanner input;
    private boolean playerTurn;
    private MarbleBag marbleBag;
    private boolean turnSelection = true;
    private boolean validMarbles = true;
    String command = null;

    public MarbleGame() {
        init();

        System.out.println("To go first, enter 1.");
        System.out.println("To let Marble Master go first, enter 2.");
        selectWhoGoesFirst();

        System.out.println("Select 1, 2, or 3 marbles to take from the bag");
        startGame();
        gameOver();
    }

    // EFFECTS: end game and show who won the game
    private void gameOver() {
        if (playerTurn) {
            System.out.println("Congratulation!! You won.");
        } else {
            System.out.println("Sorry, you did not beat Marble Master.");
        }
    }

    //EFFECTS: process user command for first turn
    private void selectWhoGoesFirst() {
        String command = null;
        while (turnSelection) {
            command = input.next();
            processWhoGoesFirst(command);
        }
        System.out.println();
    }

    // EFFECTS: computer calculates optimal marbles to take
    private void computerTurn() {
        int numberOfMarbles = marbleBag.getNumberOfMarbles();
        for (int i = 0; i < 3; i++) {
            int takenMarble = marbleBag.getNumberOfMarbles() - (i + 1);
            if (takenMarble % 4 == 1) {
                marbleBag.removeMarbles(i + 1);
                System.out.println("Marble Master removed " + (i + 1) + " marbles, "
                        + marbleBag.getNumberOfMarbles() + " remain in the bag.");
            }
        }

        if (numberOfMarbles == 1) {
            marbleBag.removeMarbles(1);
            System.out.println("Marble Master removed 1 marble "
                    + marbleBag.getNumberOfMarbles() + " remain in the bag.");
        }
        if (numberOfMarbles == marbleBag.getNumberOfMarbles()) {
            Random rand = new Random();
            int upperbound = 3;
            int randomMarbles = rand.nextInt(upperbound);
            marbleBag.removeMarbles(randomMarbles + 1);
            System.out.println("Marble Master removed " + (randomMarbles + 1) + " marbles "
                    + marbleBag.getNumberOfMarbles() + " remain in the bag.");
        }
    }


    // EFFECTS: process user input for number of marbles to remove
    private void processUserInput(String command) {
        switch (command) {
            case "1":
                marbleBag.removeMarbles(1);
                this.validMarbles = false;
                System.out.println("\nYou removed 1 marble, " + marbleBag.getNumberOfMarbles() + " remain in the bag");
                break;
            case "2":
                marbleBag.removeMarbles(2);
                this.validMarbles = false;
                System.out.println("\nYou removed 2 marble, " + marbleBag.getNumberOfMarbles() + " remain in the bag");
                break;
            case "3":
                marbleBag.removeMarbles(3);
                this.validMarbles = false;
                System.out.println("\nYou removed 3 marble, " + marbleBag.getNumberOfMarbles() + " remain in the bag");
                break;
            default:
                System.out.println("Selection not valid. Please select again.");
                break;
        }
    }

    // EFFECTS: initialize variables
    public void init() {
        Random rand = new Random();
        int upperbound = 6;
        randomNumber = rand.nextInt(upperbound);

        marbleBag = new MarbleBag(randomNumber + 15);
        System.out.println("There are " + marbleBag.getNumberOfMarbles() + " marbles in the bag.");

        input = new Scanner(System.in);
    }

    //EFFECTS: process whether player or computer starts first
    public void processWhoGoesFirst(String input) {
        if (input.equals("1")) {
            this.playerTurn = true;
            this.turnSelection = false;
        } else if(input.equals("2")) {
            this.playerTurn = false;
            this.turnSelection = false;
        } else {
            System.out.println("Selection not valid. Please select again.");
        }
    }

    //EFFECTS: begin the game. Player and computer take turn removing marbles
    public void startGame() {
        while (!marbleBag.isThereNoMoreMarbles()) {
            if (this.playerTurn) {
                playerMove();
                computerMove();
            } else {
                computerMove();
                playerMove();
            }
        }
    }

    //EFFECTS: allow player to remove marbles if there are marbles remaining
    public void playerMove() {
        if (!marbleBag.isThereNoMoreMarbles()) {
            System.out.println("Your turn!");
            while (this.validMarbles) {
                command = input.next();
                processUserInput(command);
            }
            this.validMarbles = true;
            setComputerTurn();
        }
    }

    //EFFECTS: allow computer to remove marbles if there are marbles remaining
    public void computerMove() {
        if (!marbleBag.isThereNoMoreMarbles()) {
            computerTurn();
            setPlayerTurn();
        }
    }

    //EFFECTS: set to computer's turn
    private void setComputerTurn() {
        this.playerTurn = false;
    }

    //EFFECTS: set to player's turn
    private void setPlayerTurn() {
        this.playerTurn = true;
    }

    public static void main(String[] args) {
        new MarbleGame();
    }
}
