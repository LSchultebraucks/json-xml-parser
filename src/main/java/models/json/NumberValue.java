package models.json;

public class NumberValue implements Value<Integer> {
    private Integer value;

    public NumberValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
