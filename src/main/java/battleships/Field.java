package battleships;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class Field {

    private static final String BATTLESHIP_ID = "Battleship";
    private static final String DESTROYER_ID = "Destroyers";
    private static final int BATTLESHIP_LENGTH = 5;
    private static final int DESTROYER_LENGTH = 4;
    private static final int FIELD_SIZE = 10;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_UP = 3;

    private Map<String, Integer> ships;
    private Random random;
    private int[][] field;

    public Field(Random random, int battleshipsCount, int destroyersCount) {
        this.random = checkNotNull(random);

        this.field = new int[FIELD_SIZE][FIELD_SIZE];
        this.ships = Maps.newHashMap();
        ships.put(BATTLESHIP_ID, battleshipsCount);
        ships.put(DESTROYER_ID, destroyersCount);
    }

    public int[][] generateNewField() {
        for (Map.Entry<String, Integer> ship : ships.entrySet()) {
            if (ship.getKey().equals(BATTLESHIP_ID)) {
                placeShip(BATTLESHIP_LENGTH);
            } else if (ship.getKey().equals(DESTROYER_ID)) {
                placeShip(DESTROYER_LENGTH);
            }
        }
        return field;
    }

    public void placeShip(int battleShipSize) {
        boolean shipNotPlaced = true;
        while (shipNotPlaced) {
            int rowStart = random.nextInt(FIELD_SIZE);
            int columnStart = random.nextInt(FIELD_SIZE);
            if (field[rowStart][columnStart] == 0) {
                int direction = random.nextInt(4);
                shipNotPlaced = !attemptToPlaceShip(battleShipSize, rowStart, columnStart, direction);
            }
        }
    }

    private boolean attemptToPlaceShip(
            int battleShipSize,
            int rowStart,
            int columnStart,
            int direction
    ) {
        boolean validPlacement = false;
        if (direction == DIRECTION_LEFT) {
            int rowEnd = rowStart + (battleShipSize * -1) + 1;
            if (isWithinBounds(rowEnd) && isValidPlacement(rowStart, rowEnd, columnStart, columnStart)) {
                for (int rowIndex = rowEnd; rowIndex <= rowStart; rowIndex++) {
                    field[rowIndex][columnStart] = battleShipSize;
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_RIGHT) {
            int rowEnd = rowStart + battleShipSize - 1;
            if (isWithinBounds(rowEnd) && rowEnd >= 0 && isValidPlacement(rowEnd, rowStart, columnStart, columnStart)) {
                for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
                    field[rowIndex][columnStart] = battleShipSize;
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_UP) {
            int columnEnd = columnStart + (battleShipSize * -1) + 1;
            if (isWithinBounds(columnEnd) && isValidPlacement(rowStart, rowStart, columnEnd, columnStart)) {
                for (int columnIndex = columnEnd; columnIndex <= columnStart; columnIndex++) {
                    field[rowStart][columnIndex] = battleShipSize;
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_DOWN) {
            int columnEnd = columnStart + battleShipSize - 1;
            if (isWithinBounds(columnEnd) && isValidPlacement(rowStart, rowStart, columnStart, columnEnd)) {
                for (int columnIndex = columnStart; columnIndex <= columnEnd; columnIndex++) {
                    field[rowStart][columnIndex] = battleShipSize;
                }
                validPlacement = true;
            }
        }
        return validPlacement;
    }

    private boolean isWithinBounds(int index) {
        return index < FIELD_SIZE && index >= 0;
    }

    public boolean isValidPlacement(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if (columnStart == columnEnd) {
            for (int rowIndex = rowEnd; rowIndex <= rowStart; rowIndex++) {
                if (field[rowIndex][columnStart] != 0) {
                    return false;
                }
            }
        } else if (rowStart == rowEnd) {
            for (int columnIndex = columnEnd; columnIndex <= columnStart; columnIndex++) {
                if (field[rowStart][columnIndex] != 0) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean attack(String coordinates) {
        // TODO : Implement logic for executing an attack. Return true/false if the attack has been successful.
        return false;
    }

    public void printBattlefield() {
        // TODO : Used to print the battlefield in the console.
    }

    public boolean battleIsOver() {
        // TODO : Returns true if the battle is over and all ships have been destroyed.
        return false;
    }

    public int[][] getField() {
        return field;
    }
}
