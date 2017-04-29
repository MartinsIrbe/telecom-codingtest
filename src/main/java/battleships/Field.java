package battleships;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class Field {

    private Map<String, Integer> ships;
    private Random random;
    private int[][] field;

    public Field(Random random, int fieldSize, int battleshipsCount, int destroyersCount) {
        this.random = checkNotNull(random);
        this.field = new int[fieldSize][fieldSize];

        this.ships = Maps.newHashMap();
        ships.put("Battleship", battleshipsCount);
        ships.put("Destroyers", destroyersCount);
    }

    public int[][] generateNewField() {
        // TODO : Implements logic for populating the field with battleships.
        return field;
    }
    public void placeBattleShip(int battleShipSize) {
        // TODO : Implements logic for placing a specified size battleship on the field.
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

}
