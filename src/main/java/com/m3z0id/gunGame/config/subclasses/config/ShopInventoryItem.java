package com.m3z0id.gunGame.config.subclasses.config;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopInventoryItem {
    private String name;
    private ItemType item;
    private int slot;

    public ItemStack getItem() {
        ItemStack item = this.item.createItemStack();
        ItemMeta meta = item.getItemMeta();
        meta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize(this.name).decoration(TextDecoration.ITALIC, false));
        item.setItemMeta(meta);
        return item;
    }
    public int getSlot() {
        return this.slot;
    }
}
