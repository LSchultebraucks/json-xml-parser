package parser;

import models.json.JsonObject;
import models.json.util.JsonObjectBuilder;

public class JsonParser implements Parser<JsonObject> {
    public JsonObject parseString(String json) {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();


        return jsonObjectBuilder.build();
    }
}
