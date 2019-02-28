package models.json;

public class ValuePair {
    private StringValue key;
    private Value value;

    public ValuePair(StringValue key, Value value) {
        this.key = key;
        this.value = value;
    }

    public StringValue getKey() {
        return key;
    }

    public void setKey(StringValue key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
