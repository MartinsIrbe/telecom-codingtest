package battleships;

import battleships.logic.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class Application {

    private static final int BATTLESHIPS_COUNT = 1;
    private static final int DESTROYERS_COUNT = 0;

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        Random random = new Random();

        Field field = new Field(random , BATTLESHIPS_COUNT, DESTROYERS_COUNT);
        field.generateNewField();

        field.printBattlefield();

        int moves = 10000000;
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
                field = new Field(random, BATTLESHIPS_COUNT, DESTROYERS_COUNT);
                field.reset();
                moves = 10;
                System.out.println("\nStarting new game, good luck!");
                field.printBattlefield();
            }
        }
    }

    public boolean restart(Scanner scanner) {
        System.out.println("Enter \"Y\" to restart the game.");
        String option = scanner.nextLine();
        return option.toLowerCase().equals("y");
    }

    public boolean isValidUserInput(String coordinates) {
        return isValidFormat(coordinates)
                && isValidColumn(coordinates)
                && isValidRow(coordinates);
    }

    private boolean isValidFormat(String coordinates) {
        return !coordinates.isEmpty()
                && coordinates.length() <= 3
                && coordinates.length() > 1
                && StringUtils.isAlphanumeric(coordinates);
    }

    private boolean isValidRow(String coordinates) {
        return StringUtils.isNumeric(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1)))
                && Integer.parseInt(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1))) <= 10
                && Integer.parseInt(StringUtils.substringAfterLast(coordinates, coordinates.substring(0, 1))) > 0;
    }

    private boolean isValidColumn(String coordinates) {
        return StringUtils.isAlpha(coordinates.substring(0, 1))
                && coordinates.substring(0, 1).toLowerCase().charAt(0) - 'a' < 10;
    }
}
