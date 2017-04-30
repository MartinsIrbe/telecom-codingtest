package battleships.logic;

import battleships.logic.ships.Battleship;
import battleships.logic.ships.Destroyer;
import battleships.logic.ships.Ship;

import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class is used to store all logic for the game field.
 */
public class Field {

    private static final String BATTLESHIP_ID = "Battleship";
    private static final String DESTROYER_ID = "Destroyers";
    private static final int FIELD_SIZE = 10;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_UP = 3;

    private Random random;
    private Ship[][] field;

    private boolean[][] moves;

    private int totalShips = 0;

    /**
     * Used to create a new instance of the game field.
     * @param random Used to get ship direction and random row/column indexes.
     */
    public Field(Random random) {
        this.random = checkNotNull(random);
        this.field = new Ship[FIELD_SIZE][FIELD_SIZE];
        this.moves = new boolean[FIELD_SIZE][FIELD_SIZE];
    }

    /**
     * Used to generate a 2-dimensional field that contains ships.
     * @param battleshipsCount Number of battleships that needs to be placed on the field.
     * @param destroyersCount Number of destroyers that needs to be placed on the field.
     * @return Generated field that contains placed ships.
     */
    public Ship[][] generateNewField(int battleshipsCount, int destroyersCount) {
        int battleshipsPlaces = 0;
        while (battleshipsPlaces < battleshipsCount) {
            placeShip(new Battleship());
            totalShips++;
            battleshipsPlaces++;
        }

        int destroyersPlaced = 0;
        while (destroyersPlaced < destroyersCount) {
            placeShip(new Destroyer());
            totalShips++;
            destroyersPlaced++;
        }

        return field;
    }

    /**
     * Used to place the given ship on the field.
     * @param ship The ship that needs to be added to the field.
     */
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

    /**
     * This method is used to attempt to place a ship on
     * the field in given direction from given coordinates.
     * @param ship The ship that needs to be added to the field.
     * @param rowStart The row start index.
     * @param columnStart The column start index.
     * @param direction The direction of where the ship should be placed.
     * @return
     */
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

    /**
     * Check if the given index is within the field boundaries.
     * @param index Index which needs to be checked.
     * @return true if the field is within the field boundaries, else return false.
     */
    private boolean isWithinBounds(int index) {
        return index < FIELD_SIZE && index >= 0;
    }

    /**
     * Used to check if it's possible to place the ship
     * based on the given coordinates.
     * @param rowStart The row start index.
     * @param rowEnd The row end index.
     * @param columnStart The column start index.
     * @param columnEnd The column end index.
     * @return true if possible to place the ship based on the given coordinates, else return false.
     */
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

    /**
     * Used to perform an attack on given coordinates.
     * @param coordinates The coordinates which needs to be attacked.
     * @return true if the attack has been successful, else return false.
     */
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

    /**
     * Used to check if the user has performed a move to
     * given coordinates to make sure that it's not possible
     * to attack to the same coordinates.
     * @param coordinates Coordinates that needs to be checked.
     * @return true if the user has already performed a move to the given coordinates,
     *          else return false.
     */
    public boolean isMoveDoneOnCoordinates(int[] coordinates) {
        int rowIndex = coordinates[0];
        int columnIndex = coordinates[1];
        return moves[rowIndex][columnIndex];
    }

    /**
     * Used to verify if the ship has sunk.
     * @param coordinates The ship coordinates that needs to be checked.
     * @return true if the ship has sunken.
     */
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

    /**
     * Prints the Battleships field on the console by creating
     * a representation of the 2-dimensional array.
     */
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

    /**
     * Used to reset the current game state.
     * @param battleshipsCount The number of battleships that needs to be placed on the new field.
     * @param destroyersCount The number of destroyers that needs to be placed on the new field.
     */
    public void reset(int battleshipsCount, int destroyersCount) {
        generateNewField(battleshipsCount, destroyersCount);
        moves = new boolean[FIELD_SIZE][FIELD_SIZE];
    }

    /**
     * Used to check how many ships are left on the field.
     * If no more ships left, battle is over.
     * @return true if no more ships left, otherwise returns false.
     */
    public boolean battleIsOver() {
        return totalShips == 0;
    }

    /**
     * Used to retrieve the game field.
     * @return The current game field.
     */
    public Ship[][] getField() {
        return field;
    }

    /**
     * Used to retrieve user performed moves.
     * @return a 2-dimensional array of all moves that the user has performed.
     */
    public boolean[][] getMoves() {
        return moves;
    }
}
