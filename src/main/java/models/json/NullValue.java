package models.json;

public class NullValue implements Value<Object> {

    public NullValue() {
    }

    public Object getValue() {
        return null;
    }

    public void setValue(Object value) {
    }
}
