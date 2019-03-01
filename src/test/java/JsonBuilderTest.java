import models.json.JsonObject;
import models.json.NumberValue;
import models.json.StringValue;
import models.json.ValuePair;
import models.json.builder.JsonObjectBuilder;
import org.junit.Assert;
import org.junit.Test;

public class JsonBuilderTest {

    @Test
    public void testJsonObjectBuilder() {
        // Arrange
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        ValuePair expected1 = new ValuePair(new StringValue("foo"), new NumberValue(42));
        ValuePair expected2 = new ValuePair(new StringValue("bar"), new StringValue("this is bar"));

        //Act
        jsonObjectBuilder
                .add().setKey(new StringValue("foo")).setValue(new NumberValue(42)).addValuePairToProperties()
                .add().setKey(new StringValue("bar")).setValue(new StringValue("this is bar")).addValuePairToProperties();
        JsonObject jsonObject = jsonObjectBuilder.build();

        //Assert
        assertValuePair(expected1, jsonObject.getValue().get(0));
        assertValuePair(expected2, jsonObject.getValue().get(1));
    }

    private void assertValuePair(ValuePair expected, ValuePair actual) {
        Assert.assertEquals(expected.getKey().getValue(), actual.getKey().getValue());
        Assert.assertEquals(expected.getValue().getValue(), actual.getValue().getValue());
    }
}
