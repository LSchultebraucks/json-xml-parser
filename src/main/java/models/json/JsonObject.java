package models.json;

import java.util.List;

public class JsonObject implements Value<List<ValuePair>> {
    private List<ValuePair> valuePairs;

    public JsonObject(List<ValuePair> valuePairs) {
        this.valuePairs = valuePairs;
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
