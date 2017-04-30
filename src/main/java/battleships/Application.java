package battleships;

import battleships.logic.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class Application {

    private static final int BATTLESHIPS_COUNT = 1;
    private static final int DESTROYERS_COUNT = 2;

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        Random random = new Random();

        Field field = new Field(random , BATTLESHIPS_COUNT, DESTROYERS_COUNT);
        field.generateNewField();

        Scanner scanner = new Scanner(System.in);
        while (!field.battleIsOver()) {
            System.out.print("Enter coordinates to attack: ");

            String userInput = scanner.nextLine();
            if (userInput != null && isValidUserInput(userInput)) {
                int[] coordinates = field.getCoordinates(userInput);
                if (field.attack(coordinates)) {
                    System.out.printf("Attack on coordinates %s has been successful!\n", userInput);
                }

                field.printBattlefield();
            } else {
                System.out.printf("Invalid coordinates %s\n", userInput);
            }
        }
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
