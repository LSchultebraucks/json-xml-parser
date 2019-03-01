package models.json.builder;

import models.json.JsonObject;
import models.json.ValuePair;

import java.util.List;

public class JsonObjectBuilder {
    protected JsonObject jsonObject;

    public JsonObjectBuilder() {
        this.jsonObject = new JsonObject();
    }

    public JsonObjectBuilder addProperties(List<ValuePair> value) {
        this.jsonObject.setValue(value);
        return this;
    }

    public ValuePairBuilder add() {
        ValuePairBuilder valuePairBuilder = new ValuePairBuilder();
        valuePairBuilder.setJsonObjectBuilder(this);
        return valuePairBuilder;
    }

    public JsonObjectBuilder addProperty(ValuePair valuePair) {
        this.jsonObject.getValue().add(valuePair);
        return this;
    }

    public JsonObject build() {
        return jsonObject;
    }


}
