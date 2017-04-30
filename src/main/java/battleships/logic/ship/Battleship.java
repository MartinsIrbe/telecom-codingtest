package battleships.logic.ship;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Battleship implements Ship {

    private String name;
    private int length;
    private Map<Integer, List<Integer>> coordinates;

    public Battleship() {
        this.name = "Battleship";
        this.length = 5;
        this.coordinates = Maps.newHashMap();
    }

    public String getName() {
        return name;
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
}
