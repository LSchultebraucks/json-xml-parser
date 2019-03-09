package models.json.util;

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

    public ValuePairBuilder key(String foo) {
        this.valuePair.setKey(new StringValue(foo));
        return this;
    }

    public ValuePairBuilder value(String val) {
        this.valuePair.setValue(new StringValue(val));
        return this;
    }

    public ValuePairBuilder value(int val) {
        this.valuePair.setValue(new NumberValue(val));
        return this;
    }

    public ValuePairBuilder value(boolean val) {
        if (val) {
            this.valuePair.setValue(new TrueValue());
        } else {
            this.valuePair.setValue(new FalseValue());
        }
        return this;
    }

    public ValuePairBuilder value(Object value) {
        if (value != null) {
            try {
                this.valuePair.setValue((ArrayValue) value);
            } catch (Exception e) {
                e.printStackTrace();
                this.valuePair.setValue(new NullValue());
            }
        }
        return this;
    }

    public ValuePairBuilder setValue(Value value) {
        this.valuePair.setValue(value);
        return this;
    }

    public JsonObjectBuilder addValuePairToObject() {
        this.jsonObjectBuilder.addProperty(this.build());
        return this.jsonObjectBuilder;
    }

    public void setJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder) {
        this.jsonObjectBuilder = jsonObjectBuilder;
    }

    public ArrayValueBuilder addArrayValue() {
        ArrayValueBuilder arrayValueBuilder = new ArrayValueBuilder();
        arrayValueBuilder.setValuePairBuilder(this);
        return arrayValueBuilder;
    }
}
