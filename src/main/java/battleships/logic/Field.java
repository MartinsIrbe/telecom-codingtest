package battleships.logic;

import battleships.logic.ships.Battleship;
import battleships.logic.ships.Destroyer;
import battleships.logic.ships.Ship;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class Field {

    private static final String BATTLESHIP_ID = "Battleship";
    private static final String DESTROYER_ID = "Destroyers";
    private static final int FIELD_SIZE = 10;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_UP = 3;

    private Map<String, Integer> ships;
    private Random random;
    private Ship[][] field;

    private boolean[][] moves;

    private int totalShips = 0;

    public Field(Random random, int battleshipsCount, int destroyersCount) {
        this.random = checkNotNull(random);

        this.field = new Ship[FIELD_SIZE][FIELD_SIZE];
        this.ships = Maps.newHashMap();
        if (battleshipsCount > 0) {
            ships.put(BATTLESHIP_ID, battleshipsCount);
        }
        if (destroyersCount > 0) {
            ships.put(DESTROYER_ID, destroyersCount);
        }

        this.moves = new boolean[FIELD_SIZE][FIELD_SIZE];
    }

    public Ship[][] generateNewField() {
        for (Map.Entry<String, Integer> ship : ships.entrySet()) {
            if (ship.getKey().equals(BATTLESHIP_ID)) {
                placeShip(new Battleship());
                totalShips++;
            } else if (ship.getKey().equals(DESTROYER_ID)) {
                placeShip(new Destroyer());
                totalShips++;
            }
        }
        return field;
    }

    public void placeShip(Ship ship) {
        boolean shipNotPlaced = true;
        while (shipNotPlaced) {
            int rowStart = random.nextInt(FIELD_SIZE);
            int columnStart = random.nextInt(FIELD_SIZE);
            if (field[rowStart][columnStart] == null) {
                int direction = random.nextInt(4);
                shipNotPlaced = !attemptToPlaceShip(ship, rowStart, columnStart, direction);
            }
        }
    }

    private boolean attemptToPlaceShip(
            Ship ship,
            int rowStart,
            int columnStart,
            int direction
    ) {
        boolean validPlacement = false;
        if (direction == DIRECTION_LEFT) {
            int rowEnd = rowStart + (ship.getLength() * -1) + 1;
            if (isWithinBounds(rowEnd) && isValidPlacement(rowStart, rowEnd, columnStart, columnStart)) {
                for (int rowIndex = rowEnd; rowIndex <= rowStart; rowIndex++) {
                    field[rowIndex][columnStart] = ship;
                    ship.addCoordinates(rowIndex, columnStart);
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_RIGHT) {
            int rowEnd = rowStart + ship.getLength() - 1;
            if (isWithinBounds(rowEnd) && rowEnd >= 0 && isValidPlacement(rowEnd, rowStart, columnStart, columnStart)) {
                for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
                    field[rowIndex][columnStart] = ship;
                    ship.addCoordinates(rowIndex, columnStart);
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_UP) {
            int columnEnd = columnStart + (ship.getLength() * -1) + 1;
            if (isWithinBounds(columnEnd) && isValidPlacement(rowStart, rowStart, columnEnd, columnStart)) {
                for (int columnIndex = columnEnd; columnIndex <= columnStart; columnIndex++) {
                    field[rowStart][columnIndex] = ship;
                    ship.addCoordinates(rowStart, columnIndex);
                }
                validPlacement = true;
            }
        } else if (direction == DIRECTION_DOWN) {
            int columnEnd = columnStart + ship.getLength() - 1;
            if (isWithinBounds(columnEnd) && isValidPlacement(rowStart, rowStart, columnStart, columnEnd)) {
                for (int columnIndex = columnStart; columnIndex <= columnEnd; columnIndex++) {
                    field[rowStart][columnIndex] = ship;
                    ship.addCoordinates(rowStart, columnIndex);
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
                if (field[rowIndex][columnStart] != null) {
                    return false;
                }
            }
        } else if (rowStart == rowEnd) {
            for (int columnIndex = columnEnd; columnIndex <= columnStart; columnIndex++) {
                if (field[rowStart][columnIndex] != null) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean attack(int[] coordinates) {
        int rowIndex = coordinates[0];
        int columnIndex = coordinates[1];

        boolean attackSuccessful = false;
        Ship ship = field[rowIndex][columnIndex];
        if (ship != null && ship.validCoordinates(rowIndex, columnIndex)) {
            ship.attack(rowIndex, columnIndex);
            attackSuccessful = true;
        }

        moves[rowIndex][columnIndex] = true;
        return attackSuccessful;
    }

    public boolean isMoveDoneOnCoordinates(int[] coordinates) {
        int rowIndex = coordinates[0];
        int columnIndex = coordinates[1];
        return moves[rowIndex][columnIndex];
    }

    public boolean checkIfShipSunk(int[] coordinates) {
        int rowIndex = coordinates[0];
        int columnIndex = coordinates[1];

        if (field[rowIndex][columnIndex].isSunk()) {
            System.out.printf("You sunk my %s!\n", field[rowIndex][columnIndex]);
            totalShips--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Used to convert coordinates string to int array
     * that holds coordinate indexes for row and column.
     * @param coordinates String that contains coordinates.
     * @return An array where first element is row index, second is column index.
     */
    public int[] getCoordinates(String coordinates) {
        String[] coordinatesParts = new String[]{coordinates.substring(0, 1), coordinates.substring(1)};

        int[] extractedCoordinates = new int[2];
        extractedCoordinates[0] = Integer.parseInt(coordinatesParts[1]) - 1;
        extractedCoordinates[1] = coordinatesParts[0].toLowerCase().charAt(0) - 'a';

        return extractedCoordinates;
    }

    public void printBattlefield() {
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        for (int rowIndex = 0; rowIndex < FIELD_SIZE; rowIndex++) {
            System.out.printf("%d\t", rowIndex + 1);
            for (int columnIndex = 0; columnIndex < FIELD_SIZE; columnIndex++) {
                if (field[rowIndex][columnIndex] != null
                        && !field[rowIndex][columnIndex].validCoordinates(rowIndex, columnIndex)) {
                    System.out.print("X\t");
                } else if (moves[rowIndex][columnIndex]) {
                    System.out.print(" \t");
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }

    public void reset() {
        generateNewField();
        totalShips = ships.size();
        moves = new boolean[FIELD_SIZE][FIELD_SIZE];
    }

    public boolean battleIsOver() {
        return totalShips == 0;
    }

    public Ship[][] getField() {
        return field;
    }

    public boolean[][] getMoves() {
        return moves;
    }
}
