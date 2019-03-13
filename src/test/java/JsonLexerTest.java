import models.json.lexer.JsonLexer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonLexerTest {

    @Test
    public void testJsonLexer_lex_ShouldLexSingleStringCorrectly() throws Exception {
        // Arrange
        String testString = "\"actual string\"";
        List<String> expected = Collections.singletonList("actual string");
        JsonLexer lexer = new JsonLexer();

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    @Test
    public void testJsonLexer_lex_Should_LexNumberCorrectly() throws Exception {
        // Arrange
        String testString = "42";
        List<String> expected = Arrays.asList("42");
        JsonLexer lexer = new JsonLexer();

        // Act
        List<String> actual = lexer.lex(testString);

        // Assert
        assertLexerResult(expected, actual);
    }

    private void assertLexerResult(List<String> expected, List<String> actual) {
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
