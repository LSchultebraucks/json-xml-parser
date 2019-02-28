package models.json;

public class TrueValue implements Value<Boolean> {

    public TrueValue() {
    }

    public Boolean getValue() {
        return true;
    }

    // No behaviour
    @Deprecated
    public void setValue(Boolean value) {
    }
}
