package models.json.lexer;

import models.json.lexer.token.Token;

import java.util.List;

public interface Lexer {
    List<Token> lex(String jsonString) throws Exception;
}
