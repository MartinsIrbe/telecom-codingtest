package battleships.logic;

import battleships.logic.ships.Battleship;
import battleships.logic.ships.Destroyer;
import battleships.logic.ships.Ship;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {

    @Mock
    private Random random;

    private static final String BATTLESHIP_ID = "Battleship";
    private static final int BATTLESHIP_COUNT = 1;
    private static final int BATTLESHIP_LENGTH = 5;

    private static final String DESTROYER_ID = "Destroyers";
    private static final int DESTROYERS_COUNT = 2;
    private static final int DESTROYER_LENGTH = 4;

    private Field field;

    @Before
    public void setUp() throws Exception {
        this.field = new Field(random);
    }

    @Test
    public void successfullyAddedBattleshipToFieldInRowToLeft() {
        when(random.nextInt(10)).thenReturn(4, 0);
        when(random.nextInt(4)).thenReturn(0);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0].equals(battleship));
        assertTrue(battlefield[1][0].equals(battleship));
        assertTrue(battlefield[2][0].equals(battleship));
        assertTrue(battlefield[3][0].equals(battleship));
        assertTrue(battlefield[4][0].equals(battleship));
    }

    @Test
    public void addingBattleshipToFieldInRowToLeftFailed() {
        when(random.nextInt(10)).thenReturn(3, 0, 4, 1);
        when(random.nextInt(4)).thenReturn(0);

        field.placeShip(new Battleship());

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0] == null);
        assertTrue(battlefield[1][0] == null);
        assertTrue(battlefield[2][0] == null);
        assertTrue(battlefield[3][0] == null);
        assertTrue(battlefield[4][0] == null);
    }

    @Test
    public void successfullyAddedDestroyerToFieldInRowToLeft() {
        when(random.nextInt(10)).thenReturn(3, 0);
        when(random.nextInt(4)).thenReturn(0);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0].equals(destroyer));
        assertTrue(battlefield[1][0].equals(destroyer));
        assertTrue(battlefield[2][0].equals(destroyer));
        assertTrue(battlefield[3][0].equals(destroyer));
    }

    @Test
    public void addingDestroyerToFieldInRowToLeftFailed() {
        when(random.nextInt(10)).thenReturn(2, 0, 3, 1);
        when(random.nextInt(4)).thenReturn(0);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[1][0] == null);
        assertTrue(battlefield[2][0] == null);
        assertTrue(battlefield[3][0] == null);
        assertTrue(battlefield[4][0] == null);
    }

    @Test
    public void successfullyAddedBattleshipToFieldInRowToRight() {
        when(random.nextInt(10)).thenReturn(5, 0);
        when(random.nextInt(4)).thenReturn(1);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[5][0].equals(battleship));
        assertTrue(battlefield[6][0].equals(battleship));
        assertTrue(battlefield[7][0].equals(battleship));
        assertTrue(battlefield[8][0].equals(battleship));
        assertTrue(battlefield[9][0].equals(battleship));
    }

    @Test
    public void addingBattleshipToFieldInRowToRightFailed() {
        when(random.nextInt(10)).thenReturn(6, 0, 5, 1);
        when(random.nextInt(4)).thenReturn(1, 1);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[6][0] == null);
        assertTrue(battlefield[7][0] == null);
        assertTrue(battlefield[8][0] == null);
        assertTrue(battlefield[9][0] == null);
    }

    @Test
    public void successfullyAddedDestroyerToFieldInRowToRight() {
        when(random.nextInt(10)).thenReturn(6, 0);
        when(random.nextInt(4)).thenReturn(1);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[6][0].equals(destroyer));
        assertTrue(battlefield[7][0].equals(destroyer));
        assertTrue(battlefield[8][0].equals(destroyer));
        assertTrue(battlefield[9][0].equals(destroyer));
    }

    @Test
    public void addingDestroyerToFieldInRowToRightFailed() {
        when(random.nextInt(10)).thenReturn(7, 0, 6, 1);
        when(random.nextInt(4)).thenReturn(1, 1);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[7][0] == null);
        assertTrue(battlefield[8][0] == null);
        assertTrue(battlefield[9][0] == null);
    }

    @Test
    public void successfullyAddedBattleshipToFieldInColumnUpwards() {
        when(random.nextInt(10)).thenReturn(0, 4);
        when(random.nextInt(4)).thenReturn(3);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0].equals(battleship));
        assertTrue(battlefield[0][1].equals(battleship));
        assertTrue(battlefield[0][2].equals(battleship));
        assertTrue(battlefield[0][3].equals(battleship));
        assertTrue(battlefield[0][4].equals(battleship));
    }

    @Test
    public void addingBattleshipToFieldInColumnUpwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 3, 1, 4);
        when(random.nextInt(4)).thenReturn(3);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0] == null);
        assertTrue(battlefield[0][1] == null);
        assertTrue(battlefield[0][2] == null);
        assertTrue(battlefield[0][3] == null);
    }

    @Test
    public void successfullyAddedDestroyerToFieldInColumnUpwards() {
        when(random.nextInt(10)).thenReturn(0, 3);
        when(random.nextInt(4)).thenReturn(3);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0].equals(destroyer));
        assertTrue(battlefield[0][1].equals(destroyer));
        assertTrue(battlefield[0][2].equals(destroyer));
        assertTrue(battlefield[0][3].equals(destroyer));
    }

    @Test
    public void addingDestroyerToFieldInColumnUpwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 2, 1, 3);
        when(random.nextInt(4)).thenReturn(3);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][0] == null);
        assertTrue(battlefield[0][1] == null);
        assertTrue(battlefield[0][2] == null);
    }

    @Test
    public void successfullyAddedBattleshipToFieldInColumnDownwards() {
        when(random.nextInt(10)).thenReturn(0, 5);
        when(random.nextInt(4)).thenReturn(2);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][5].equals(battleship));
        assertTrue(battlefield[0][6].equals(battleship));
        assertTrue(battlefield[0][7].equals(battleship));
        assertTrue(battlefield[0][8].equals(battleship));
        assertTrue(battlefield[0][9].equals(battleship));
    }

    @Test
    public void addingBattleshipToFieldInColumnDownwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 6, 1, 5);
        when(random.nextInt(4)).thenReturn(2);

        Battleship battleship = new Battleship();
        field.placeShip(battleship);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][6] == null);
        assertTrue(battlefield[0][7] == null);
        assertTrue(battlefield[0][8] == null);
        assertTrue(battlefield[0][9] == null);
    }

    @Test
    public void successfullyAddedDestroyerToFieldInColumnDownwards() {
        when(random.nextInt(10)).thenReturn(0, 6);
        when(random.nextInt(4)).thenReturn(2);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][6].equals(destroyer));
        assertTrue(battlefield[0][7].equals(destroyer));
        assertTrue(battlefield[0][8].equals(destroyer));
        assertTrue(battlefield[0][9].equals(destroyer));
    }

    @Test
    public void addingDestroyerToFieldInColumnDownwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 7, 1, 6);
        when(random.nextInt(4)).thenReturn(2);

        Destroyer destroyer = new Destroyer();
        field.placeShip(destroyer);

        Ship[][] battlefield = field.getField();
        assertTrue(battlefield[0][7] == null);
        assertTrue(battlefield[0][8] == null);
        assertTrue(battlefield[0][9] == null);
    }

    @Test
    public void successfullyPerformedAttackOnBattleship() {
        successfullyAddedBattleshipToFieldInRowToLeft();

        assertTrue(field.attack(new int[]{0, 0}));
        assertTrue(field.attack(new int[]{1, 0}));
        assertTrue(field.attack(new int[]{2, 0}));
        assertTrue(field.attack(new int[]{3, 0}));
        assertTrue(field.attack(new int[]{4, 0}));
    }

    @Test
    public void successfullyPerformedAttackOnDestroyer() {
        successfullyAddedDestroyerToFieldInRowToLeft();

        assertTrue(field.attack(new int[]{0, 0}));
        assertTrue(field.attack(new int[]{1, 0}));
        assertTrue(field.attack(new int[]{2, 0}));
        assertTrue(field.attack(new int[]{3, 0}));
    }

    @Test
    public void attackWasMissAsThereAreNoShipsAtGivenCoordinates() {
        assertFalse(field.attack(new int[]{0, 0}));
    }

    @Test
    public void afterSuccessfulAttackBattleshipSunk() {
        successfullyPerformedAttackOnBattleship();
        assertTrue(field.checkIfShipSunk(new int[]{0, 0}));
    }

    @Test
    public void afterSuccessfulAttackDestroyerSunk() {
        successfullyPerformedAttackOnBattleship();
        assertTrue(field.checkIfShipSunk(new int[]{0, 0}));
    }

    @Test
    public void battleIsOverWhenThereAreNoShipsOnField() {
        assertTrue(field.battleIsOver());
    }

    @Test
    public void checkForValidBattleshipCoordinatesReturnTrueIfValidCoordinates() {
        Battleship battleship = new Battleship();
        battleship.addCoordinates(0, 0);

        assertTrue(battleship.validCoordinates(0, 0));
    }

    @Test
    public void checkForValidDestroyerCoordinatesReturnTrueIfValidCoordinates() {
        Destroyer destroyer = new Destroyer();
        destroyer.addCoordinates(0, 0);

        assertTrue(destroyer.validCoordinates(0, 0));
    }

    @Test
    public void checkForValidBattleshipCoordinatesReturnFalseWhenInvalidCoordinates() {
        Battleship battleship = new Battleship();
        battleship.addCoordinates(1, 1);

        assertFalse(battleship.validCoordinates(0, 0));
    }

    @Test
    public void checkForValidDestroyerCoordinatesReturnFalseWhenInvalidCoordinates() {
        Destroyer destroyer = new Destroyer();
        destroyer.addCoordinates(1, 1);

        assertFalse(destroyer.validCoordinates(0, 0));
    }

    @Test
    public void whenNoCoordinatesLeftForBattleshipItHasSunk() {
        Battleship battleship = new Battleship();

        assertTrue(battleship.isSunk());
    }

    @Test
    public void whenNoCoordinatesLeftForDestroyerItHasSunk() {
        Destroyer destroyer = new Destroyer();

        assertTrue(destroyer.isSunk());
    }

    @Test
    public void coordinatesAreCorrectlyTranslatedFromString() {
        int[] coordinates = field.getCoordinates("A1");

        assertThat(coordinates[0], is(0));
        assertThat(coordinates[1], is(0));
    }

    @Test
    public void successfullyAddedMovesWhenAttemptingToAttack() {
        successfullyAddedBattleshipToFieldInRowToLeft();

        assertTrue(field.attack(new int[]{0, 0}));

        assertTrue(field.getMoves()[0][0]);
    }

    @Test
    public void whenNoMovePerformedMovesShouldHaveOnlyFalseValues() {
        for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
                assertFalse(field.getMoves()[rowIndex][columnIndex]);
            }
        }
    }

    @Test
    public void whenGeneratingNewFieldBattleshipIsAddedToField() {
        Field tempField = new Field(new Random());
        Ship[][] ships = tempField.generateNewField(1, 0);

        boolean batleshipPresent = false;
        for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
                if (ships[rowIndex][columnIndex] instanceof Battleship) {
                    batleshipPresent = true;
                    break;
                }
            }
        }

        assertTrue(batleshipPresent);
    }

    @Test
    public void whenGeneratingNewFieldDestroyerIsAddedToField() {
        Field tempField = new Field(new Random());
        Ship[][] ships = tempField.generateNewField(0, 1);

        boolean destroyerPresent = false;
        for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
                if (ships[rowIndex][columnIndex] instanceof Destroyer) {
                    destroyerPresent = true;
                    break;
                }
            }
        }

        assertTrue(destroyerPresent);
    }

    @Test
    public void maintainingStateOfUserMoves() {
        successfullyAddedBattleshipToFieldInRowToLeft();
        field.attack(new int[]{0, 0});
        assertTrue(field.isMoveDoneOnCoordinates(new int[]{0, 0}));
    }

    @Test
    public void checkIfShipSunkReturnsFalseWhenShipNotSunken() {
        successfullyAddedBattleshipToFieldInRowToLeft();
        field.attack(new int[]{0, 0});

        assertFalse(field.checkIfShipSunk(new int[]{0, 0}));
    }

    @Test
    public void checkIfShipSunkReturnsTrueWhenShipSunken() {
        successfullyAddedBattleshipToFieldInRowToLeft();
        field.attack(new int[]{0, 0});
        field.attack(new int[]{1, 0});
        field.attack(new int[]{2, 0});
        field.attack(new int[]{3, 0});
        field.attack(new int[]{4, 0});

        assertTrue(field.checkIfShipSunk(new int[]{0, 0}));
    }

    @Test
    public void failWhenPlacementIsNotValid() {
        successfullyAddedBattleshipToFieldInRowToLeft();
        assertFalse(field.isValidPlacement(0, 0, 0, 0));

        successfullyAddedBattleshipToFieldInColumnUpwards();
        assertFalse(field.isValidPlacement(0, 0, 0, 0));
    }

    @Test
    public void passWhenPlacementIsValid() {
        successfullyAddedBattleshipToFieldInRowToLeft();
        assertTrue(field.isValidPlacement(0, 0, 1, 1));

        successfullyAddedBattleshipToFieldInColumnUpwards();
        assertTrue(field.isValidPlacement(1, 1, 1, 1));
    }

    @Test
    public void failWhenEitherNotTheSameColumnOrRow() {
        successfullyAddedBattleshipToFieldInColumnUpwards();

        assertFalse(field.isValidPlacement(1, 2, 1, 2));
    }
}