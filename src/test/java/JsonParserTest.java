import models.json.JsonObject;
import models.json.ValuePair;
import models.json.util.JsonObjectBuilderFactory;
import org.junit.Assert;
import org.junit.Test;
import parser.JsonParser;

public class JsonParserTest {

    @Test
    public void parseStringWithKeyValuePairsCorrectly() {
        // Arrange
        String jsonString = "{\n" +
                "  \"foo\": \"bar\",\n" +
                "  \"bar\": 123,\n" +
                "  \"isNull\": null,\n" +
                "  \"isTrue\": true,\n" +
                "  \"isFalse\": false\n" +
                "}";

        JsonObjectBuilderFactory factory = new JsonObjectBuilderFactory();
        JsonObject expected = factory.createJsonObjectBuilder()
                .addValuePair().key("foo").value("bar").addValuePairToObject()
                .addValuePair().key("bar").value(123).addValuePairToObject()
                .addValuePair().key("isNull").value(null).addValuePairToObject()
                .addValuePair().key("isTrue").value(true).addValuePairToObject()
                .addValuePair().key("isFalse").value(false).addValuePairToObject()
            .build();

        //Act

        JsonParser jsonParser = new JsonParser();
        JsonObject actual = jsonParser.parseString(jsonString);

        // Assert
        Assert.assertEquals(expected.getValue().size(), actual.getValue().size());
        for (int index = 0; index < expected.getValue().size(); index++) {
            assertValuePair(expected.getValue().get(index), actual.getValue().get(0));
        }
    }

    private void assertValuePair(ValuePair expected, ValuePair actual) {
        Assert.assertEquals(expected.getKey().getValue(), actual.getKey().getValue());
        Assert.assertEquals(expected.getValue().getValue(), actual.getValue().getValue());
    }

}
