package models.json.lexer.token;

public class NullToken extends Token<Object> {
    public NullToken() {
        super(null);
    }

    public NullToken(Object ignored) {
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
