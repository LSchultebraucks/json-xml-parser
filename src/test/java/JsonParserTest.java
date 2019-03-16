import models.json.JsonObject;
import models.json.ValuePair;
import models.json.lexer.token.NumberToken;
import models.json.lexer.token.Token;
import models.json.util.JsonObjectBuilderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.JsonParser;

import java.util.List;

public class JsonParserTest {

    private JsonParser parser;
    private JsonObjectBuilderFactory factory;
    private JsonObject expected;
    private List<Token> tokens;

    @Before
    public void initializeTests() {
        parser = new JsonParser();
        factory = new JsonObjectBuilderFactory();
    }

    @Test(expected = Exception.class)
    public void parseNoLeftBraceExceptException() throws Exception {
        // Arrange
        tokens.add(new NumberToken("42"));

        // Act
        parser.parse(tokens);
    }

//    @Test
//    public void parseStringWithKeyValuePairsCorrectly() {
//        // Arrange
//        String jsonString = "{\n" +
//                "  \"foo\": \"bar\",\n" +
//                "  \"bar\": 123,\n" +
//                "  \"isNull\": null,\n" +
//                "  \"isTrue\": true,\n" +
//                "  \"isFalse\": false\n" +
//                "}";
//
//        JsonObjectBuilderFactory factory = new JsonObjectBuilderFactory();
//        JsonObject expected = factory.createJsonObjectBuilder()
//                .addValuePair().key("foo").value("bar").addValuePairToObject()
//                .addValuePair().key("bar").value(123).addValuePairToObject()
//                .addValuePair().key("isNull").value(null).addValuePairToObject()
//                .addValuePair().key("isTrue").value(true).addValuePairToObject()
//                .addValuePair().key("isFalse").value(false).addValuePairToObject()
//            .build();
//
//        //Act
//
//        JsonParser jsonParser = new JsonParser();
//        JsonObject actual = jsonParser.parseString(jsonString);
//
//        // Assert
//        Assert.assertEquals(expected.getValue().size(), actual.getValue().size());
//        for (int index = 0; index < expected.getValue().size(); index++) {
//            assertValuePair(expected.getValue().get(index), actual.getValue().get(0));
//        }
//    }

    private void assertValuePair(ValuePair expected, ValuePair actual) {
        Assert.assertEquals(expected.getKey().getValue(), actual.getKey().getValue());
        Assert.assertEquals(expected.getValue().getValue(), actual.getValue().getValue());
    }

}
