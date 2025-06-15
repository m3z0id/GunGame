package com.m3z0id.gunGame.config.subclasses.config;

import org.bukkit.inventory.ItemType;

import java.util.List;

public class ShopItem {
    String name;
    int cost;
    ItemType item;
    int slot;
    List<Effect> effects;

    public ShopItem(){

    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public ItemType getItem() {
        return item;
    }
    public int getSlot() {
        return slot;
    }
    public List<Effect> getEffects() {
        return effects;
    }
}
