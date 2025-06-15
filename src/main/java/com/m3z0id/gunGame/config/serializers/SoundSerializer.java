package com.m3z0id.gunGame.config.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class SoundSerializer implements JsonDeserializer<Sound> {
    @Override
    public Sound deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Registry.SOUNDS.get(NamespacedKey.minecraft(jsonElement.getAsString()));
    }
}
