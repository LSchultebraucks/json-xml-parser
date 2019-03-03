package parser;

import models.json.JsonObject;
import models.json.builder.JsonObjectBuilder;

import java.io.IOException;

public class JsonParser implements Parser<JsonObject> {
    public JsonObject parseString(String json) throws IOException {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();



        return jsonObjectBuilder.build();
    }
}
