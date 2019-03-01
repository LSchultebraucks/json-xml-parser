package models.json;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue implements Value<List<Value>> {
    private List<Value> values;

    public ArrayValue() {
        this.values = new ArrayList<>();
    }

    public List<Value> getValue() {
        return values;
    }

    public void setValue(List<Value> value) {
        this.values = value;
    }
}
