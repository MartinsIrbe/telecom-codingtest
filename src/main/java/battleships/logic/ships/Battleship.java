package battleships.logic.ships;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Used to hold details about the ship type Battleship.
 */
public class Battleship implements Ship {

    private String name;
    private int length;
    private Map<Integer, List<Integer>> coordinates;

    public Battleship() {
        this.name = "Battleship";
        this.length = 5;
        this.coordinates = Maps.newHashMap();
    }

    /**
     * Used to determine if the ship has sunk by
     * checking coordinates that hasn't been yet attacked.
     * @return true if the ship is sunk, otherwise return false.
     */
    public boolean isSunk() {
        return coordinates.size() == 0;
    }

    /**
     * The ship length in squares.
     * @return ship length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Used to add ship part coordinates which is used
     * to keep a track of uncompromised coordinates.
     * @param rowIndex The ship row index.
     * @param columnIndex The ship column index.
     */
    public void addCoordinates(int rowIndex, int columnIndex) {
        coordinates.put(coordinates.size() + 1, Lists.newArrayList(rowIndex, columnIndex));
    }

    /**
     * Used to check if valid/uncompromised ship coordinates.
     * @param rowIndex The ship row index.
     * @param columnIndex The ship column index.
     * @return true if uncompromised ship coordinates, otherwise false.
     */
    public boolean validCoordinates(int rowIndex, int columnIndex) {
        for (Map.Entry<Integer, List<Integer>> coordinate : coordinates.entrySet()) {
            if (coordinate.getValue().get(0) == rowIndex && coordinate.getValue().get(1) == columnIndex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to perform an attack on the ship on given coordinates.
     * @param rowIndex The ship row index.
     * @param columnIndex The ship column index.
     */
    public void attack(int rowIndex, int columnIndex) {
        coordinates.values().removeIf(coordinate ->
                coordinate.get(0) == rowIndex && coordinate.get(1) == columnIndex
        );
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battleship that = (Battleship) o;
        return length == that.length &&
                Objects.equal(name, that.name) &&
                Objects.equal(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, length, coordinates);
    }
}
