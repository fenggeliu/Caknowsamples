package com.caknow.customer.garage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by junu on 1/10/2017.
 */

public class MMYDeserializer<T> implements JsonDeserializer<T> {
    private final Class mNestedClazz;
    private final Object mNestedDeserializer;

    public MMYDeserializer(Class nestedClazz, Object nestedDeserializer) {
        mNestedClazz = nestedClazz;
        mNestedDeserializer = nestedDeserializer;
    }

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        // Get the "content" element from the parsed JSON
        JsonElement content = je.getAsJsonObject().get("years");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        GsonBuilder builder = new GsonBuilder();
        if (mNestedClazz != null && mNestedDeserializer != null) {
            builder.registerTypeAdapter(mNestedClazz, mNestedDeserializer);
        }
        return builder.create().fromJson(content, type);

    }
}