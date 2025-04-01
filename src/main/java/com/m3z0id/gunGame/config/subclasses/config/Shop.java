package com.m3z0id.gunGame.config.subclasses.config;

import java.util.List;

public class Shop {
    int size;
    String title;
    List<ShopItem> items;

    public Shop(){

    }

    public int getSize() {
        return size;
    }
    public String getTitle() {
        return title;
    }
    public List<ShopItem> getItems() {
        return items;
    }
}
