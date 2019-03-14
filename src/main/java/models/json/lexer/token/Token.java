package models.json.lexer.token;

public abstract class Token<T> {
    private T tokenValue;

    public Token(T tokenValue) {
        this.tokenValue = tokenValue;
    }

    public T getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(T tokenValue) {
        this.tokenValue = tokenValue;
    }
}
