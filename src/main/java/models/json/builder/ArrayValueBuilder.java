package models.json.builder;

import models.json.ArrayValue;
import models.json.Value;

public class ArrayValueBuilder {
    private ArrayValue arrayValue;

    public ArrayValueBuilder() {
        this.arrayValue = new ArrayValue();
    }

    public ArrayValue build() {
        return arrayValue;
    }

    public ArrayValueBuilder addValue(Value value) {
        this.arrayValue.getValue().add(value);
        return this;
    }
}
