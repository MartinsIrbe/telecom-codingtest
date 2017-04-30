package battleships;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    public Application application;

    @Before
    public void setUp() throws Exception {
        this.application = new Application();
    }

    @Test
    public void passWhenValidInputProvided() {
        for (String letter : "ABCDEFGHIJ".split("(?!^)")) {
            for (int index = 1; index <= 10; index++) {
                assertTrue(application.isValidUserInput(String.format("%s%s", letter, index)));
            }
        }
    }

    @Test
    public void failIfInvalidInputLengthHasBeenEntered() {
        assertFalse(application.isValidUserInput("A100"));
    }

    @Test
    public void passIfValidInputLengthHasBeenEntered() {
        assertTrue(application.isValidUserInput("A10"));
    }

    @Test
    public void failIfFirstCharacterFromTheInputIsNotLetter() {
        assertFalse(application.isValidUserInput("310"));
    }

    @Test
    public void failIfInputContainsInvalidAlphanumericCharacters() {
        assertFalse(application.isValidUserInput("---"));
    }

    @Test
    public void failIfInputIsEmpty() {
        assertFalse(application.isValidUserInput(""));
    }

    @Test
    public void failIfColumnOutOfBounds() {
        assertFalse(application.isValidUserInput("K10"));
    }

    @Test
    public void failIfRowOutOfBounds() {
        assertFalse(application.isValidUserInput("A11"));
    }
}