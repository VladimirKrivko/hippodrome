import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    private Horse horse;

    @Test
    void testConstructorWithFirstNullParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(null, 0, 0);
        });
        assertEquals("Name cannot be null.", actualException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            StringUtils.EMPTY,
            StringUtils.SPACE,
            StringUtils.CR,
            StringUtils.LF,
    })
    void testConstructorWithFirstEmptyOrOnlyWhiteSpaceParameter(String whiteSpace) {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(whiteSpace, 0, 0);
        });
        assertEquals("Name cannot be blank.", actualException.getMessage());
    }

    @Test
    void testConstructorWithSecondNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("testName", -1, 0);
        });
        assertEquals("Speed cannot be negative.", actualException.getMessage());
    }

    @Test
    void testConstructorWithThirdNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("testName", 0, -1);
        });
        assertEquals("Distance cannot be negative.", actualException.getMessage());
    }

    @Test
    void getNameReturnsTheStringThatWasPassedByTheFirstParameterToTheConstructor() {
        String expected = "testName";
        horse = new Horse(expected, 0, 0);
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getSpeedReturnsTheNumberThatWasPassedByTheSecondParameterToTheConstructor() {
        double expected = 0.123456789;
        horse = new Horse("testName", expected, 0);
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceReturnsTheNumberThatWasPassedByTheThirdParameterToTheConstructor() {
        double expected = 0.123456789;
        horse = new Horse("testName", 0, expected);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceReturnsZeroIfTheObjectWasCreatedUsingConstructorWithTwoParameters() {
        double expected = 0;
        horse = new Horse("testName", 0);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void methodCallsTheGetRandomDoubleMethodInsideWithTheNecessaryParameters() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
//            mockedStatic.when(Horse::move)
        }
//        horse = new Horse("testName", 1, 1);
//        horse.move();
//        Mockito.verify(horse).move();
    }

}