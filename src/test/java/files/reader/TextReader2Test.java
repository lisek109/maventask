package files.reader;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static files.reader.IsStringWithoutSpaces.isAStringWithoutSpaces;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TextReader2Test {

    private final TextReader2 textReader2 = new TextReader2();

    @ParameterizedTest
    @MethodSource("provideStringsForParseInput")
    void shouldReturnSameStringWithoutSpaces(String input, String expected) throws IOException {
        //when & then
        assertEquals(textReader2.parseInput(input), expected); //junit
        assertThat(textReader2.parseInput(input)).isEqualTo(expected); //assertj
    }

    private static Stream<Arguments> provideStringsForParseInput() {
        return Stream.of(
                Arguments.of("Text1.txt", "Tojesttestnr1"),
                Arguments.of("Text2.txt", "Tojesttestnr2zwiekszailoscialiniijednapodrugiej.Dodanesarownieztabulatory."),
                Arguments.of("Json.json", "{\"title\":\"ThisIsWhatYouCameFor\"}"),
                Arguments.of("CsvFile.csv", "eruid,opisbatman,używatechnologiisuperman,fruwawpowietrzuspiderman,wykorzystujepajęcząsieć"),
                Arguments.of("EmptyText.txt", "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsToScanner")
    public void shouldReturnSameStringIfValid(String input, String expected) {
        //when
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //then
        assertEquals(expected, textReader2.getString());
    }

    private static Stream<Arguments> provideStringsToScanner() {
        return Stream.of(
                Arguments.of("Text1.txt", "Text1.txt"),
                Arguments.of("Text2.txt", "Text2.txt"),
                Arguments.of("Json.json", "Json.json"),
                Arguments.of("CsvFile.csv", "CsvFile.csv"),
                Arguments.of("EmptyText.txt", "EmptyText.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("provideWrongStringsToScanner")
    public void shouldThrowExceptionIfEnteredValueIsIncorrectAndNextValueNotEntered(String input) {
        //when
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //then
        assertThrows(NoSuchElementException.class,
                textReader2::getString);
    }

    private static Stream<Arguments> provideWrongStringsToScanner() {
        return Stream.of(
                Arguments.of("Text1.tx"),
                Arguments.of("Jsonjson"),
                Arguments.of(""),
                Arguments.of("EmptyText.jpg")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd.txt", "someName.json", "file.csv"})
    void shouldThrowExceptionWhenWrongFileNameEntered(String input) {
        //when & then
        assertThrows(NullPointerException.class,
                () -> textReader2.parseInput(input)); //junit
        assertThatThrownBy(() -> textReader2.parseInput(input))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Text1.txt", "Text2.txt", "Text3.txt", "Json.json", "EmptyText.txt", "CsvFile.csv"})
    void checkIfStringHasWhitespacesAfterParsing(String input) throws IOException {
        //when
        String res = textReader2.parseInput(input);
        //then
        assertThat(res, isAStringWithoutSpaces());
    }
}