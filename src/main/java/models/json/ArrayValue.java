package models.json;

import java.util.List;

public class ArrayValue implements Value<List<Value>> {
    private List<Value> values;

    public ArrayValue(List<Value> values) {
        this.values = values;
    }

    public List<Value> getValue() {
        return values;
    }

    public void setValue(List<Value> value) {
        this.values = value;
    }
}
