package com.m3z0id.gunGame.config.serializers;

import com.google.gson.*;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

public class PotionEffectTypeSerializer implements JsonSerializer<PotionEffectType>, JsonDeserializer<PotionEffectType> {
    @Override
    public PotionEffectType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return PotionEffectType.getByName(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(PotionEffectType potionEffectType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(potionEffectType.getName());
    }
}
