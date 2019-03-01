package models.json;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements Value<List<ValuePair>> {
    private List<ValuePair> valuePairs;

    public JsonObject() {
        this.valuePairs = new ArrayList<>();
    }

    public List<ValuePair> getValue() {
        return valuePairs;
    }

    public void setValue(List<ValuePair> valuePairs) {
        this.valuePairs = valuePairs;
    }

    public void addValuePair(ValuePair valuePair) {
        this.valuePairs.add(valuePair);
    }
}
