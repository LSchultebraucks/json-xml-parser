import models.json.*;
import models.json.util.JsonObjectBuilder;
import models.json.util.JsonObjectBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JsonBuilderTest {

    @Test
    public void testJsonObjectBuilder() {
        // Arrange
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();

        List<ValuePair> expectedValues = Arrays.asList(
                new ValuePair(new StringValue("foo"), new NumberValue(42)),
                new ValuePair(new StringValue("bar"), new StringValue("this is bar")),
                new ValuePair(new StringValue("isNull"), new NullValue()),
                new ValuePair(new StringValue("isTrue"), new TrueValue()),
                new ValuePair(new StringValue("isFalse"), new FalseValue())
        );

        //Act
        jsonObjectBuilder
                .addValuePair().key("foo").value(42).addValuePairToObject()
                .addValuePair().key("bar").value("this is bar").addValuePairToObject()
                .addValuePair().key("isNull").value(null).addValuePairToObject()
                .addValuePair().key("isTrue").value(true).addValuePairToObject()
                .addValuePair().key("isFalse").value(false).addValuePairToObject();
        JsonObject jsonObject = jsonObjectBuilder.build();

        //Assert
        Assert.assertEquals(expectedValues.size(), jsonObject.getValue().size());
        for (int index = 0; index < jsonObject.getValue().size(); index++) {
            assertValuePair(expectedValues.get(index), jsonObject.getValue().get(index));
        }
    }

    @Test
    public void testJsonObjectBuilderWithInnerJsonObject() {
        // Arrange
        JsonObjectBuilderFactory factory = new JsonObjectBuilderFactory();

        String expectedKey = "foo";
        ArrayValue expectedArrayValue = new ArrayValue();
        List<Value> values = Arrays.asList(
                new Value[]{new NumberValue(123), new StringValue("bar"), new NullValue()}
        );
        expectedArrayValue.setValue(values);

        JsonObject jsonObject = factory.createJsonObjectBuilder()
                .addValuePair()
                .key("foo")
                .addArrayValue()
                .addValue(123)
                .addValue("bar")
                .addValue(null)
                .addArrayValueToObject()
                .addValuePairToObject()
                .build();

        Assert.assertEquals(expectedKey, jsonObject.getValue().get(0).getKey().getValue());
        assertArrayValue(expectedArrayValue, (ArrayValue) jsonObject.getValue().get(0).getValue());
    }

    private void assertValuePair(ValuePair expected, ValuePair actual) {
        Assert.assertEquals(expected.getKey().getValue(), actual.getKey().getValue());
        Assert.assertEquals(expected.getValue().getValue(), actual.getValue().getValue());
    }

    private void assertArrayValue(ArrayValue expected, ArrayValue actual) {
        for (int index = 0; index < actual.getValue().size(); index++) {
            Assert.assertEquals(expected.getValue().get(index).getValue(), actual.getValue().get(index).getValue());
        }
    }
}
