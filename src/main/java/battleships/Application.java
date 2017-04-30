package battleships;

import battleships.logic.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

/**
 * The main application used to run the Battleships game.
 */
public class Application {

    private static final int BATTLESHIPS_COUNT = 1;
    private static final int DESTROYERS_COUNT = 2;
    private static final int TOTAL_MOVES = 20;

    public static void main(String[] args) {
        new Application().run();
    }

    /**
     * Used to execute the Battleships game.
     */
    public void run() {
        Random random = new Random();

        Field field = new Field(random);
        field.generateNewField(BATTLESHIPS_COUNT, DESTROYERS_COUNT);

        field.printBattlefield();

        int moves = TOTAL_MOVES;
        Scanner scanner = new Scanner(System.in);
        while (!field.battleIsOver() && moves != 0) {
            System.out.printf("You have %d moves left.\n", moves);
            System.out.print("Enter coordinates to attack: ");

            String userInput = scanner.nextLine();
            if (userInput != null && isValidUserInput(userInput)) {
                int[] coordinates = field.getCoordinates(userInput);
                if (field.isMoveDoneOnCoordinates(coordinates)) {
                    field.printBattlefield();
                    System.out.println("Ahoy captain, you already attacked on these coordinates!");
                } else if (field.attack(coordinates)) {
                    field.printBattlefield();
                    if (!field.checkIfShipSunk(coordinates)) {
                        System.out.println("Attack was successful.");
                    }
                } else {
                    field.printBattlefield();
                    System.out.println("This one was a miss.");
                    moves--;
                }
            } else {
                System.out.printf("Invalid coordinates %s\n", userInput);
            }

            if (field.battleIsOver()) {
                System.out.println("Congratulations, you just won the game!");
                moves = 0;
            }

            if (moves == 0 && restart(scanner)) {
                field = new Field(random);
                field.reset(BATTLESHIPS_COUNT, DESTROYERS_COUNT);
                moves = TOTAL_MOVES;
                System.out.println("\nStarting new game, good luck!");
                field.printBattlefield();
            }
        }
    }

    /**
     * Used to check if the user wants to restart the game.
     * @param scanner Input from console.
     * @return true if user wants to restart the game, else false.
     */
    public boolean restart(Scanner scanner) {
        System.out.println("Enter \"Y\" to restart the game.");
        String option = scanner.nextLine();
        return option.toLowerCase().equals("y");
    }

    /**
     * Used to validate coordinates that the user has provided.
     * @param coordinates Coordinates acquired from the console.
     * @return true if the coordinates are valid, else false.
     */
    public boolean isValidUserInput(String coordinates) {
        return isValidFormat(coordinates)
                && isValidColumn(coordinates)
                && isValidRow(coordinates);
    }

    /**
     * Used to validate the given coordinates format,
     * so that the coordinates are two to three characters
     * in length and the string is alphanumeric.
     * @param coordinates Coordinates acquired from the console.
     * @return true if the coordinates are valid format, else false.
     */
    private boolean isValidFormat(String coordinates) {
        return !coordinates.isEmpty()
                && coordinates.length() <= 3
                && coordinates.length() > 1
                && StringUtils.isAlphanumeric(coordinates);
    }

    /**
     * Used to validate if the given row number is of a valid length.
     * @param coordinates Coordinates acquired from the console.
     * @return true if the valid row number, else false.
     */
    private boolean isValidRow(String coordinates) {
        return StringUtils.isNumeric(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1)))
                && Integer.parseInt(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1))) <= 10
                && Integer.parseInt(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1))) > 0;
    }

    /**
     * Used to validate if the given column letter is of valid length.
     * @param coordinates Coordinates acquired from the console.
     * @return true if the valid column letter, else false.
     */
    private boolean isValidColumn(String coordinates) {
        return StringUtils.isAlpha(coordinates.substring(0, 1))
                && coordinates.substring(0, 1).toLowerCase().charAt(0) - 'a' < 10;
    }
}
