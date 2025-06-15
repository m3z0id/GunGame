package com.m3z0id.gunGame.config.serializers;

import com.google.gson.*;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemType;

import java.lang.reflect.Type;

public class ItemTypeSerializer implements JsonDeserializer<ItemType> {
    @Override
    public ItemType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Registry.ITEM.get(NamespacedKey.minecraft(jsonElement.getAsString()));
        //return Material.getMaterial(jsonElement.getAsString());
    }
}
