package com.m3z0id.gunGame.config;

import org.bukkit.Material;

import java.util.List;

public class ShopItem {
    String name;
    int cost;
    Material item;
    String title;
    List<Effect> effects;

    public ShopItem(){

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public Material getItem() {
        return item;
    }
    public void setItem(Material item) {
        this.item = item;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Effect> getEffects() {
        return effects;
    }
    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
