package models.json.lexer;

import java.util.List;

public interface Lexer {
    List<String> lex(String jsonString) throws Exception;
}
