package models.json.lexer;

import models.json.lexer.token.NumberToken;
import models.json.lexer.token.StringToken;
import models.json.lexer.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonLexer implements Lexer {
    private final char JSON_QUOTE = '"';
    private final char[] JSON_WHITESPACE = {' ', '\t', '\b', '\n', 'r'};
    private final char[] JSON_SYNTAX = {',', ':', '{', '}', '[', ']'};

    @Override
    public List<Token> lex(String jsonString) throws Exception {
        List<Token> tokens = new ArrayList<>();

        while (jsonString.length() > 0) {

            // lex string
            if (jsonString.charAt(0) == JSON_QUOTE) {
                StringBuilder stringTokenBuilder = new StringBuilder();
                jsonString = jsonString.substring(1);
                boolean foundEndOfString = false;
                for (int index = 0; index < jsonString.length(); index++) {
                    if (jsonString.charAt(index) == JSON_QUOTE) {
                        foundEndOfString = true;
                        jsonString = jsonString.substring(stringTokenBuilder.length() + 1);
                        break;
                    } else {
                        stringTokenBuilder.append(jsonString.charAt(index));
                    }
                }
                if (!foundEndOfString) {
                    throw new Exception("Error while parsing string - no end of string quote found.");
                }
                tokens.add(new StringToken(stringTokenBuilder.toString()));
            }

            // lex number
            StringBuilder numberTokenBuilder = new StringBuilder();

            List<Character> numberChars = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', 'e', '.');

            for (int index = 0; index < jsonString.length(); index++) {
                if (numberChars.contains(jsonString.charAt(index))) {
                    numberTokenBuilder.append(jsonString.charAt(index));
                } else {
                    break;
                }
            }

            jsonString = jsonString.substring(numberTokenBuilder.length());

            if (numberTokenBuilder.toString().contains(".")) {
                tokens.add(new NumberToken(String.valueOf(Double.parseDouble(numberTokenBuilder.toString()))));
            } else if (!numberTokenBuilder.toString().equals("")) {
                tokens.add(new NumberToken(numberTokenBuilder.toString()));
            }

            // lex bool

            // lex null

            // lex whitespace -> ignore

            // lex syntax
        }

        return tokens;
    }
}
