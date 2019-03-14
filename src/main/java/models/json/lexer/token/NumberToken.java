package models.json.lexer.token;

/*
 * Number token will be saved as string because of the complex syntax of numbers in json.
 */
public class NumberToken extends Token<String> {
    public NumberToken(String tokenValue) {
        super(tokenValue);
    }
}
