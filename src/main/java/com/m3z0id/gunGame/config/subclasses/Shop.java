package com.m3z0id.gunGame.config;

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
    public void setSize(int size) {
        this.size = size;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<ShopItem> getItems() {
        return items;
    }
    public void setItems(List<ShopItem> items) {
        this.items = items;
    }
}
