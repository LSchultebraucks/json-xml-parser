package parser;

import models.json.JsonObject;
import models.json.lexer.token.Token;
import models.json.util.JsonObjectBuilder;

import java.util.List;

public class JsonParser implements Parser<JsonObject> {
    public JsonObject parse(List<Token> tokens) throws Exception {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();

        if ((Character) tokens.get(0).getTokenValue() != '{') {
            throw new Exception("JSON object must be begin with '{', got: " + tokens.get(0).getTokenValue());
        }

        return jsonObjectBuilder.build();
    }
}
