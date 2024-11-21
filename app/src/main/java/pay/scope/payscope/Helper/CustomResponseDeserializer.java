package pay.scope.payscope.Helper;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import pay.scope.payscope.Model.ManualRequestResponse;

public class CustomResponseDeserializer implements JsonDeserializer<ManualRequestResponse> {
    @Override
    public ManualRequestResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            return new Gson().fromJson(json, ManualRequestResponse.class);
        } else if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            ManualRequestResponse response = new ManualRequestResponse();
            response.setMessage(json.getAsString());
            return response;
        }
        throw new JsonParseException("Unexpected JSON type: " + json.getClass());
    }

}
