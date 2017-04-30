package battleships.logic.ships;

/**
 * A ship interface used to hold common methods
 * that needs to be implemented by different ship types.
 */
public interface Ship {

    boolean isSunk();

    int getLength();

    void addCoordinates(int rowIndex, int columnIndex);

    boolean validCoordinates(int rowIndex, int columnIndex);

    void attack(int rowIndex, int columnIndex);

}
