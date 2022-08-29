import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(MockitoExtension.class)
class HorseTest {
    private Horse horse;

    @Test
    void testConstructorWithNameNullParameter() {
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
    void testConstructorWithNameEmptyOrOnlyWhiteSpaceParameter(String name) {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(name, 0, 0);
        });
        assertEquals("Name cannot be blank.", actualException.getMessage());
    }

    @Test
    void testConstructorWithSpeedNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("testName", -1, 0);
        });
        assertEquals("Speed cannot be negative.", actualException.getMessage());
    }

    @Test
    void testConstructorWithDistanceNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("testName", 0, -1);
        });
        assertEquals("Distance cannot be negative.", actualException.getMessage());
    }

    @Test
    void getNameReturnsTheStringThatWasPassedByTheNameParameterToTheConstructor() {
        String expectedName = "testName";
        horse = new Horse(expectedName, 0, 0);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeedReturnsTheNumberThatWasPassedByTheSpeedParameterToTheConstructor() {
        double expectedSpeed = 0.123456789;
        horse = new Horse("testName", expectedSpeed, 0);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistanceReturnsTheNumberThatWasPassedByTheDistanceParameterToTheConstructor() {
        double expectedDistance = 0.123456789;
        horse = new Horse("testName", 0, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void getDistanceReturnsZeroIfTheObjectWasCreatedUsingConstructorWithTwoParameters() {
        double expectedDistance = 0;
        horse = new Horse("testName", 0);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void methodCallsTheGetRandomDoubleMethodInsideWithTheNecessaryParameters() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse = new Horse("testName", 1);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "1, 2",
            "3.3, 4.4"
    })
    void methodAssignsDistanceValueCalculatedUsingTheFormulaFromTheMoveMethod(double speed, double distance, double expected) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            //do do do
        }
    }

}