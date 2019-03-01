package models.json.builder;

import models.json.ValuePair;

import java.util.ArrayList;
import java.util.List;

public class ValuePairListBuilder {
    private List<ValuePair> valuePairList;

    public ValuePairListBuilder() {
        this.valuePairList = new ArrayList<>();
    }

    public List<ValuePair> build() {
        return valuePairList;
    }

    public ValuePairListBuilder addProperty(ValuePair valuePair) {
        this.valuePairList.add(valuePair);
        return this;
    }
}
