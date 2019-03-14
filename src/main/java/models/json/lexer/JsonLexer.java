package models.json.lexer;

import models.json.lexer.token.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonLexer implements Lexer {
    private final char JSON_QUOTE = '"';
    private final List<Character> JSON_WHITESPACE = Arrays.asList(' ', '\t', '\b', '\n', 'r');
    private final List<Character> JSON_SYNTAX = Arrays.asList(',', ':', '{', '}', '[', ']');
    private final int TRUE_LENGTH = "true".length();
    private final int FALSE_LENGTH = "false".length();
    private final int NULL_LENGTH = "null".length();

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
                continue;
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
                continue;
            } else if (!numberTokenBuilder.toString().equals("")) {
                tokens.add(new NumberToken(numberTokenBuilder.toString()));
                continue;
            }

            // lex bool
            if (jsonString.startsWith("true")) {
                jsonString = jsonString.substring(TRUE_LENGTH);
                tokens.add(new BooleanToken(true));
                continue;
            } else if (jsonString.startsWith("false")) {
                jsonString = jsonString.substring(FALSE_LENGTH);
                tokens.add(new BooleanToken(false));
                continue;
            }

            // lex null
            if (jsonString.startsWith("null")) {
                jsonString = jsonString.substring(NULL_LENGTH);
                tokens.add(new NullValue());
                continue;
            }

            // lex whitespace -> ignore
            Character firstChar = jsonString.charAt(0);
            if (JSON_WHITESPACE.stream().anyMatch(whitespace -> whitespace.equals(firstChar))) {
                jsonString = jsonString.substring(1);
            } else if (JSON_SYNTAX.stream().anyMatch(whitespace -> whitespace.equals(firstChar))) { // lex syntax
                jsonString = jsonString.substring(1);
                tokens.add(new JsonSyntaxToken(firstChar));
            } else {
                throw new Exception("Error while parsing json: " + jsonString);
            }
        }

        return tokens;
    }
}
