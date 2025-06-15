package com.m3z0id.gunGame.config.serializers;

import com.google.gson.*;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

public class PotionEffectTypeSerializer implements JsonDeserializer<PotionEffectType> {
    @Override
    public PotionEffectType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Registry.POTION_EFFECT_TYPE.get(NamespacedKey.minecraft(jsonElement.getAsString()));
        //return PotionEffectType.getByName(jsonElement.getAsString());
    }
}
