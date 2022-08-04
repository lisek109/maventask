package files.reader;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    private final FileValidator<String> validator = new Validator();

    @ParameterizedTest
    @ValueSource(strings = {"Text.jpg", "Text.pdf", "Text.mp3"})
    void shouldReturnFalseIfWrongExtension(String input) {
        // when & then
        assertFalse(validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Textjpg", "123"})
    void shouldReturnFalseIfFileNameWithoutDot(String input) {
        // when & then
        assertFalse(validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Text.txt", "Text.csv", "Text.json"})
    void shouldReturnTrueIfCorrectNameAndExtension(String input) {
        // when & then
        assertTrue(validator.validate(input));
    }

    @Test
    void shouldReturnFalseIfFileNameEmpty() {
        // when & then
        assertFalse(validator.validate(""));
    }
}