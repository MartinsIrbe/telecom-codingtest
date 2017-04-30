package battleships;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {

    @Mock
    private Random random;

    private int fieldSize = 10;
    private int battleshipsCount;
    private int destroyersCount;

    private Field field;

    @Before
    public void setUp() throws Exception {
        this.field = new Field(random, battleshipsCount, destroyersCount);
    }

    @Test
    public void successfullyAddedBattleshipToFieldInRowToLeft() {
        when(random.nextInt(10)).thenReturn(4, 0);
        when(random.nextInt(4)).thenReturn(0);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][0], is(5));
        assertThat(battlefield[1][0], is(5));
        assertThat(battlefield[2][0], is(5));
        assertThat(battlefield[3][0], is(5));
        assertThat(battlefield[4][0], is(5));
    }

    @Test
    public void addingBattleshipToFieldInRowToLeftFailed() {
        when(random.nextInt(10)).thenReturn(3, 0, 4, 1);
        when(random.nextInt(4)).thenReturn(0);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][0], is(0));
        assertThat(battlefield[0][1], is(5));
    }

    @Test
    public void successfullyAddedBattleshipToFieldInRowToRight() {
        when(random.nextInt(10)).thenReturn(5, 0);
        when(random.nextInt(4)).thenReturn(1);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[5][0], is(5));
        assertThat(battlefield[6][0], is(5));
        assertThat(battlefield[7][0], is(5));
        assertThat(battlefield[8][0], is(5));
        assertThat(battlefield[9][0], is(5));
    }

    @Test
    public void addingBattleshipToFieldInRowToRightFailed() {
        when(random.nextInt(10)).thenReturn(6, 0, 5, 1);
        when(random.nextInt(4)).thenReturn(1, 1);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[5][0], is(0));
        assertThat(battlefield[5][1], is(5));
    }

    @Test
    public void successfullyAddedBattleshipToFieldInColumnUpwards() {
        when(random.nextInt(10)).thenReturn(0, 4);
        when(random.nextInt(4)).thenReturn(3);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][0], is(5));
        assertThat(battlefield[0][1], is(5));
        assertThat(battlefield[0][2], is(5));
        assertThat(battlefield[0][3], is(5));
        assertThat(battlefield[0][4], is(5));
    }

    @Test
    public void addingBattleshipToFieldInColumnUpwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 3, 1, 4);
        when(random.nextInt(4)).thenReturn(3);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][0], is(0));
        assertThat(battlefield[1][0], is(5));
    }

    @Test
    public void successfullyAddedBattleshipToFieldInColumnDownwards() {
        when(random.nextInt(10)).thenReturn(0, 5);
        when(random.nextInt(4)).thenReturn(2);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][5], is(5));
        assertThat(battlefield[0][6], is(5));
        assertThat(battlefield[0][7], is(5));
        assertThat(battlefield[0][8], is(5));
        assertThat(battlefield[0][9], is(5));
    }

    @Test
    public void addingBattleshipToFieldInColumnDownwardsFailed() {
        when(random.nextInt(10)).thenReturn(0, 6, 1, 5);
        when(random.nextInt(4)).thenReturn(2);

        field.placeShip(5);

        int[][] battlefield = field.getField();
        assertThat(battlefield[0][9], is(0));
        assertThat(battlefield[1][9], is(5));
    }

    @Test
    public void successfullyPerformedAttack() {
        assertTrue(field.attack("A5"));
    }

    @Test
    public void battleIsOverWhenAllShipsDestroyed() {
        assertTrue(field.battleIsOver());
    }

}