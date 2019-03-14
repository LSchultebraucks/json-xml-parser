import models.json.lexer.JsonLexer;
import models.json.lexer.Lexer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonLexerTest {

    private Lexer lexer;

    @Before
    public void initializeTests() {
        lexer = new JsonLexer();
    }


    @Test
    public void testJsonLexer_lex_ShouldLexSingleStringCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"";
        List<String> expected = Collections.singletonList("actual string");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexIntegerNumberCorrectly() throws Exception {
        // Arrange
        String testString = "42";
        List<String> expected = Arrays.asList("42");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexFloatNumberCorrectly() throws Exception {
        // Arrange
        String testString = "4.2";
        List<String> expected = Arrays.asList("4.2");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeIntegerNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        List<String> expected = Arrays.asList("-42");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-4.2";
        List<String> expected = Arrays.asList("-4.2");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersWithEpsilonCorrectly() throws Exception {
        // Arrange
        String testString = "-42e2";
        List<String> expected = Arrays.asList("-42e2");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        List<String> expected = Arrays.asList("-42");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42";
        List<String> expected = Arrays.asList("actual string", "42");

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    private void assertLexerResult(List<String> expected, List<String> actual) {
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
