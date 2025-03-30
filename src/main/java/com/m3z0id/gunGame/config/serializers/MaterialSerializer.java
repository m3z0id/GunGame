package com.m3z0id.gunGame.config.serializers;

import com.google.gson.*;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class MaterialSerializer implements JsonSerializer<Material>, JsonDeserializer<Material> {
    @Override
    public JsonElement serialize(Material material, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(material.name());
    }

    @Override
    public Material deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Material.getMaterial(jsonElement.getAsString());
    }
}
