package models.json.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonLexer implements Lexer {
    private final char JSON_QUOTE = '"';
    private final char[] JSON_WHITESPACE = {' ', '\t', '\b', '\n', 'r'};
    private final char[] JSON_SYNTAX = {',', ':', '{', '}', '[', ']'};

    @Override
    public List<String> lex(String jsonString) throws Exception {
        List<String> tokens = new ArrayList<>();

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
                tokens.add(stringTokenBuilder.toString());
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
                tokens.add(String.valueOf(Double.parseDouble(numberTokenBuilder.toString())));
            } else if (!numberTokenBuilder.toString().equals("")) {
                tokens.add(numberTokenBuilder.toString());
            }

            // lex bool

            // lex null

            // lex whitespace -> ignore

            // lex syntax
        }

        return tokens;
    }
}
