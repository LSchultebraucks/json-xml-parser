package models.json;

public class FalseValue implements Value<Boolean> {

    public FalseValue() {
    }

    public Boolean getValue() {
        return false;
    }

    /**
     * No behavior.
     * @param value is redundant.
     */
    @Deprecated
    public void setValue(Boolean value) {
    }
}
