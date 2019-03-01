package models.json.builder;

import models.json.*;

public class ValuePairBuilder {
    private ValuePair valuePair;

    private JsonObjectBuilder jsonObjectBuilder;

    public ValuePairBuilder() {
        this.valuePair = new ValuePair(new StringValue(""), new NullValue());
    }

    public ValuePair build() {
        return valuePair;
    }

    public ValuePairBuilder setKey(StringValue key) {
        this.valuePair.setKey(key);
        return this;
    }

    public ValuePairBuilder setValue(Value value) {
        this.valuePair.setValue(value);
        return this;
    }

    public JsonObjectBuilder addValuePairToProperties() {
        this.jsonObjectBuilder.addProperty(this.build());
        return this.jsonObjectBuilder;
    }

    public void setJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder) {
        this.jsonObjectBuilder = jsonObjectBuilder;
    }
}
