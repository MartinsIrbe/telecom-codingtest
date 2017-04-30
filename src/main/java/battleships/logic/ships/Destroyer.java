package battleships.logic.ships;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Destroyer implements Ship {

    private String name;
    private int length;
    private Map<Integer, List<Integer>> coordinates;

    public Destroyer() {
        this.name = "Destroyer";
        this.length = 4;
        this.coordinates = Maps.newHashMap();
    }

    public boolean isSunk() {
        return coordinates.size() == 0;
    }

    public int getLength() {
        return length;
    }

    public void addCoordinates(int rowIndex, int columnIndex) {
        coordinates.put(coordinates.size() + 1, Lists.newArrayList(rowIndex, columnIndex));
    }

    public boolean validCoordinates(int rowIndex, int columnIndex) {
        for (Map.Entry<Integer, List<Integer>> coordinate : coordinates.entrySet()) {
            if (coordinate.getValue().get(0) == rowIndex && coordinate.getValue().get(1) == columnIndex) {
                return true;
            }
        }
        return false;
    }

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
        Destroyer destroyer = (Destroyer) o;
        return length == destroyer.length &&
                Objects.equal(name, destroyer.name) &&
                Objects.equal(coordinates, destroyer.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, length, coordinates);
    }
}
