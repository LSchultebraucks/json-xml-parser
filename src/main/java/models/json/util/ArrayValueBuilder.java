package models.json.util;

import models.json.*;

public class ArrayValueBuilder {
    private ArrayValue arrayValue;

    private ValuePairBuilder valuePairBuilder;

    public ArrayValueBuilder() {
        this.arrayValue = new ArrayValue();
    }

    public ArrayValue build() {
        return arrayValue;
    }

    public ArrayValueBuilder addValue(String val) {
        this.arrayValue.getValue().add(new StringValue(val));
        return this;
    }

    public ArrayValueBuilder addValue(int val) {
        this.arrayValue.getValue().add(new NumberValue(val));
        return this;
    }

    public ArrayValueBuilder addValue(boolean val) {
        if (val) {
            this.arrayValue.getValue().add(new TrueValue());
        } else {
            this.arrayValue.getValue().add(new FalseValue());
        }
        return this;
    }

    public ArrayValueBuilder addValue(Object value) {
        if (value != null) {
            try {
                this.arrayValue.getValue().add((ArrayValue) value);
            } catch (Exception e) {
                e.printStackTrace();
                this.arrayValue.getValue().add(new NullValue());
            }
        }
        return this;
    }

    public ValuePairBuilder addArrayValueToObject() {
        this.valuePairBuilder.setValue(this.build());
        return this.valuePairBuilder;
    }

    public void setValuePairBuilder(ValuePairBuilder valuePairBuilder) {
        this.valuePairBuilder  = valuePairBuilder;
    }
}
