import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}