import models.json.lexer.JsonLexer;
import models.json.lexer.Lexer;
import models.json.lexer.token.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonLexerTest {

    private Lexer lexer;
    private List<Token> expected;

    @Before
    public void initializeTests() {
        lexer = new JsonLexer();
        expected = new ArrayList<>();
    }


    @Test
    public void testJsonLexer_lex_ShouldLexSingleStringCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"";
        expected.add(new StringToken("actual string"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexIntegerNumberCorrectly() throws Exception {
        // Arrange
        String testString = "42";
        expected.add(new StringToken("42"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexFloatNumberCorrectly() throws Exception {
        // Arrange
        String testString = "4.2";
        expected.add(new NumberToken("4.2"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeIntegerNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        expected.add(new StringToken("-42"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-4.2";
        expected.add(new NumberToken("-4.2"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeFloatNumbersWithEpsilonCorrectly() throws Exception {
        // Arrange
        String testString = "-42e2";
        expected.add(new NumberToken("-42e2"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNegativeNumbersCorrectly() throws Exception {
        // Arrange
        String testString = "-42";
        expected.add(new NumberToken("-42"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42";
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexBooleanValueTrueCorrectly() throws Exception {
        // Arrange
        String testString = "true";
        expected.add(new BooleanToken(true));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexBooleanValueFalseCorrectly() throws Exception {
        // Arrange
        String testString = "false";
        expected.add(new BooleanToken(false));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42truefalse";
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));
        expected.add(new BooleanToken(true));
        expected.add(new BooleanToken(false));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexNullCorrectly() throws Exception {
        // Arrange
        String testString = "null";
        expected.add(new NullToken());

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesAndNullCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"42truefalsenull";
        expected.add(new StringToken("actual string"));
        expected.add(new NumberToken("42"));
        expected.add(new BooleanToken(true));
        expected.add(new BooleanToken(false));
        expected.add(new NullToken());

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringAndNumberAndBooleanValuesAndNullReversedCorrectly() throws Exception {
        // Arrange
        String testString = "nullfalsetrue42\"actual string\"";
        expected.add(new NullToken());
        expected.add(new BooleanToken(false));
        expected.add(new BooleanToken(true));
        expected.add(new NumberToken("42"));
        expected.add(new StringToken("actual string"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexStringsAndIgnoreWhitespaces() throws Exception {
        // Arrange
        String testString = "    \"foo\"   \"bar\"   ";
        expected.add(new StringToken("foo"));
        expected.add(new StringToken("bar"));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexJsonSyntax() throws Exception {
        // Arrange
        String testString = ",:[]{}";
        expected.add(new JsonSyntaxToken(','));
        expected.add(new JsonSyntaxToken(':'));
        expected.add(new JsonSyntaxToken('['));
        expected.add(new JsonSyntaxToken(']'));
        expected.add(new JsonSyntaxToken('{'));
        expected.add(new JsonSyntaxToken('}'));

        actAndAssert(testString);
    }

    @Test
    public void testJsonLexer_lex_ShouldLexJsonStringCorrectly() throws Exception {
        String testString = "{\n" +
                "    \"foo\": 42,\n" +
                "    \"bar\": [true, false, null]\n" +
                "}";
        expected.add(new JsonSyntaxToken('{'));
        expected.add(new StringToken("foo"));
        expected.add(new JsonSyntaxToken(':'));
        expected.add(new NumberToken("42"));
        expected.add(new JsonSyntaxToken(','));
        expected.add(new StringToken("bar"));
        expected.add(new JsonSyntaxToken(':'));
        expected.add(new JsonSyntaxToken('['));
        expected.add(new BooleanToken(true));
        expected.add(new JsonSyntaxToken(','));
        expected.add(new BooleanToken(false));
        expected.add(new JsonSyntaxToken(','));
        expected.add(new NullToken());
        expected.add(new JsonSyntaxToken(']'));
        expected.add(new JsonSyntaxToken('}'));

        actAndAssert(testString);
    }

    private void actAndAssert(String testString) throws Exception {
        // Act
        List<Token> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }


    private void assertLexerResult(List<Token> expected, List<Token> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        for (int index = 0; index < expected.size(); index++) {
            Assert.assertEquals(expected.getClass().getName(), actual.getClass().getName());
            Assert.assertEquals(expected.get(index).getTokenValue(), actual.get(index).getTokenValue());
        }
    }
}
