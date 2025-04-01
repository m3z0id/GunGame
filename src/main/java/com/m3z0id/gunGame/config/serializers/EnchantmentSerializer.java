package com.m3z0id.gunGame.config.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Type;

public class EnchantmentSerializer implements JsonDeserializer<Enchantment> {
    @Override
    public Enchantment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Enchantment.getByKey(NamespacedKey.minecraft(jsonElement.getAsString()));
    }
}
