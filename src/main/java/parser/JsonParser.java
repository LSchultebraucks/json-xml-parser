package parser;

import models.json.JsonObject;
import models.json.util.JsonObjectBuilder;

import java.io.IOException;

public class JsonParser implements Parser<JsonObject> {
    public JsonObject parseString(String json) throws IOException {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();



        return jsonObjectBuilder.build();
    }
}
