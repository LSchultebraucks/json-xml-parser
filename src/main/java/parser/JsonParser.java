package parser;

import models.json.JsonObject;
import models.json.lexer.token.Token;
import models.json.util.JsonObjectBuilder;

import java.util.List;

import static parser.JsonConstants.LEFT_BRACE;
import static parser.JsonConstants.LEFT_BRACKET;

public class JsonParser implements Parser<JsonObject> {

    public JsonObject parse(List<Token> tokens) throws Exception {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();

        boolean isFirstToken = true;

        while (tokens.size() > 0) {
            Token currentToken = tokens.get(0);

            if (isFirstToken && currentToken.getTokenValue() != LEFT_BRACE) {
                throw new Exception("JSON object must be begin with '{', got: " + tokens.get(0).getTokenValue());
            } else if (currentToken.getTokenValue() == LEFT_BRACKET) {
                // Start of Array
                continue;
            } else if (currentToken.getTokenValue() == LEFT_BRACE) {
                // Start of Object
                continue;
            }

            isFirstToken = false;
        }

        return jsonObjectBuilder.build();
    }
}
