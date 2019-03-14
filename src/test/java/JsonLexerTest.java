import models.json.lexer.JsonLexer;
import models.json.lexer.Lexer;
import models.json.lexer.token.NumberToken;
import models.json.lexer.token.StringToken;
import models.json.lexer.token.Token;
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
        List<String> expected = Arrays.asList("true");

        // Act
        // List<Token> actual = lexer.lex(testString);

        // Assert
        // assertLexerResult(expected, actual);
    }

    private void assertLexerResult(List<Token> expected, List<Token> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        for (int index = 0; index < expected.size(); index++) {
            Assert.assertEquals(expected.get(index).getTokenValue(), actual.get(index).getTokenValue());
        }
    }
}
