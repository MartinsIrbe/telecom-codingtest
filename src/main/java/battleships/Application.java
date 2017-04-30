package battleships;

import java.util.Random;

public class Application {

    private static final int FIELD_SIZE = 10;
    private static final int BATTLESHIPS_COUNT = 1;
    private static final int DESTROYERS_COUNT = 2;

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        Random random = new Random();

        Field field = new Field(random , BATTLESHIPS_COUNT, DESTROYERS_COUNT);
        field.generateNewField();

        while (!field.battleIsOver()) {
            // TODO : Main application logic for retrieving user input and performing attack.

            String coordinates = "";
            boolean attackStatus = field.attack(coordinates);
            if (attackStatus) {
                System.out.printf("Attack on coordinates %s has been successful!", coordinates);
            }

            field.printBattlefield();
        }
    }

}
