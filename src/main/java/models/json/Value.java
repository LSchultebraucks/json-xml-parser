package models.json;

public interface Value<T> {
    T getValue();
    void setValue(T value);
}
