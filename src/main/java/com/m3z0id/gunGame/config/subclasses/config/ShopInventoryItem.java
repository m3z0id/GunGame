package com.m3z0id.gunGame.config.subclasses.config;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopInventoryItem {
    private String name;
    private Material item;
    private int slot;

    public ItemStack getItem() {
        ItemStack item = new ItemStack(this.item);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
        item.setItemMeta(meta);
        return item;
    }
    public int getSlot() {
        return this.slot;
    }
}
