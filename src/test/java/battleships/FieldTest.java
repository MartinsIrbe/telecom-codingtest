package battleships;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {

    @Mock
    private Random random;

    private int fieldSize;
    private int battleshipsCount;
    private int destroyersCount;

    private Field field;

    @Before
    public void setUp() throws Exception {
        this.field = new Field(random, fieldSize, battleshipsCount, destroyersCount);
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