import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {
    private Horse horse;

    @Test
    void testConstructorWithNameNullParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            horse = new Horse(null, 0, 0)
        );
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
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            horse = new Horse(name, 0, 0)
        );
        assertEquals("Name cannot be blank.", actualException.getMessage());
    }

    @Test
    void testConstructorWithSpeedNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            horse = new Horse("testName", -1, 0)
        );
        assertEquals("Speed cannot be negative.", actualException.getMessage());
    }

    @Test
    void testConstructorWithDistanceNegativeParameter() {
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () ->
            horse = new Horse("testName", 0, -1)
        );
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

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1.5",
            "1, 2, 2.5",
            "2.2, 3.3, 4.4",
            "1.1, 2.2, 2.75",
            "3.3, 4.4, 6.05"    // без DecimalFormat получается 6.050000000000001 !???
    })
    void methodAssignsDistanceValueCalculatedUsingTheFormulaFromTheMoveMethod(double speed, double distance, double expectedDistance) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse = new Horse("testName", speed, distance);
            horse.move();
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
            double actualDistance = horse.getDistance();
            double result = Double.parseDouble(df.format(actualDistance));
            assertEquals(expectedDistance, result);
        }
    }

}