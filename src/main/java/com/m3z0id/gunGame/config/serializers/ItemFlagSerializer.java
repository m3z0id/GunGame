package com.m3z0id.gunGame.config.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.bukkit.inventory.ItemFlag;

import java.lang.reflect.Type;

public class ItemFlagSerializer implements JsonDeserializer<ItemFlag> {
    @Override
    public ItemFlag deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ItemFlag.valueOf(jsonElement.getAsString());
    }
}
