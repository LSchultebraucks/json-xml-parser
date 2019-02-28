package models.json;

public class StringValue implements Value<java.lang.String> {
    private java.lang.String value;

    public StringValue(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }
}
