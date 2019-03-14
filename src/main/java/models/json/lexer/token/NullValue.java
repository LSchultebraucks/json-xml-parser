package models.json.lexer.token;

public class NullValue extends Token<Object> {
    public NullValue() {
        super(null);
    }

    public NullValue(Object ignored) {
        super(null);
    }

    @Override
    public Object getTokenValue() {
        return null;
    }

    @Override
    public void setTokenValue(Object ignored) {
        super.setTokenValue(null);
    }
}
