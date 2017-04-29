package battleships;

import java.util.Random;

public class Application {

    private final static int FIELD_SIZE = 10;
    private final static int BATTLESHIPS_COUNT = 1;
    private final static int DESTROYERS_COUNT = 2;

    private Field field;

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        Random random = new Random();

        this.field = new Field(random, FIELD_SIZE, BATTLESHIPS_COUNT, DESTROYERS_COUNT);

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
