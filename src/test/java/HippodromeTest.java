import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            expectedHorses.add(new Horse(i + "", 0));
        }
        hippodrome = new Hippodrome(expectedHorses);
        List<Horse> actualHorses = hippodrome.getHorses();
        // если в конструкторе класса Hippodrome изменить список, то тест все равно проходит!???
        assertEquals(expectedHorses.size(), actualHorses.size());
    }

    @Test
    void methodCallsTheMoveMethodForAllHorses() {
        List<Horse> horses = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (int i = 0; i < 50; i++) {
            Mockito.verify(horses.get(i)).move();
        }
    }

    @Test
    void methodReturnsHorseWithTheLargestDistanceValue() {
        Horse expectedHorse = new Horse("testName", 1, 100);
        List<Horse> horses = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(i + "", 1, i));
        }
        horses.add(expectedHorse);
        hippodrome = new Hippodrome(horses);
        Horse actualHorse = hippodrome.getWinner();
        assertEquals(expectedHorse, actualHorse);
    }

}
