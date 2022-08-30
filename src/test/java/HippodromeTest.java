import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;

public class HippodromeTest {

    private Hippodrome hippodrome;

    @Test
    void testConstructorWithNullParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            hippodrome = new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", actualException.getMessage());
    }

    @Test
    void testConstructorWithEmptyParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            hippodrome = new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", actualException.getMessage());
    }

    @Test
    void methodReturnsListThatContainsTheSameObjectsAsTheListThatWasPassedToConstructor() {
        List<Horse> expectedHorses = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse(i + "", anyDouble()));
        }
        hippodrome = new Hippodrome(expectedHorses);
        List<Horse> actualHorses = hippodrome.getHorses();
        assertEquals(expectedHorses, actualHorses);
    }
}
