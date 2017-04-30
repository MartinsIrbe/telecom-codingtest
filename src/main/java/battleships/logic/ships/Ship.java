package battleships.logic.ships;

public interface Ship {

    String getName();

    boolean isSunk();

    int getLength();

    void addCoordinates(int rowIndex, int columnIndex);

    boolean validCoordinates(int rowIndex, int columnIndex);

    void attack(int rowIndex, int columnIndex);

}
