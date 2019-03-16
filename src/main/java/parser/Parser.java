package parser;

import models.json.lexer.token.Token;

import java.util.List;

public interface Parser<T> {
    T parse(List<Token> tokens) throws Exception;
}
