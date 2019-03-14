import models.json.lexer.JsonLexer;
import models.json.lexer.Lexer;
import models.json.lexer.token.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("actual string"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexIntegerNumberCorrectly() throws Exception {
        // Arrange
        String testString = "42";
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("42"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexFloatNumberCorrectly() throws Exception {
        // Arrange
        String testString = "4.2";
        List<Token> expected = new ArrayList<>();
        expected.add(new NumberToken("4.2"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeIntegerNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("-42"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-4.2";
        List<Token> expected = new ArrayList<>();
        expected.add(new NumberToken("-4.2"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersWithEpsilonCorrectly() throws Exception {
        // Arrange
        String testString = "-42e2";
        List<Token> expected = new ArrayList<>();
        expected.add(new NumberToken("-42e2"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        List<Token> expected = new ArrayList<>();
        expected.add(new NumberToken("-42"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42";
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexBooleanValueTrueCorrectly() throws Exception {
        // Arrange
        String testString = "true";
        List<Token> expected = new ArrayList<>();
        expected.add(new BooleanToken(true));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexBooleanValueFalseCorrectly() throws Exception {
        // Arrange
        String testString = "false";
        List<Token> expected = new ArrayList<>();
        expected.add(new BooleanToken(false));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42truefalse";
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));
        expected.add(new BooleanToken(true));
        expected.add(new BooleanToken(false));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNullCorrectly() throws Exception {
        // Arrange
        String testString = "null";
        List<Token> expected = new ArrayList<>();
        expected.add(new NullValue());

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesAndNullCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42truefalsenull";
        List<Token> expected = new ArrayList<>();
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));
        expected.add(new BooleanToken(true));
        expected.add(new BooleanToken(false));
        expected.add(new NullValue());

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesAndNullReversedCorrectly() throws Exception {
        // Arrange
        String testString = "nullfalsetrue42\"actual string\"";
        List<Token> expected = new ArrayList<>();
        expected.add(new NullValue());
        expected.add(new BooleanToken(false));
        expected.add(new BooleanToken(true));
        expected.add(new NumberToken("42"));
        expected.add(new StringToken("actual string"));

        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    // TODO: tests for whitespace, json syntax and simulation with full correct json

    private void assertLexerResult(List<Token> expected, List<Token> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        for (int index = 0; index < expected.size(); index++) {
            Assert.assertEquals(expected.get(index).getTokenValue(), actual.get(index).getTokenValue());
        }
    }
}
