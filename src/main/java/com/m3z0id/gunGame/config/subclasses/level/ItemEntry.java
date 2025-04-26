package com.m3z0id.gunGame.config.subclasses.level;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemEntry {
    private Material item;
    private String displayName;
    private Boolean unbreakable;
    private Integer amount;
    private Integer slot;
    private List<Enchant> enchants;
    private List<ItemFlag> itemFlags;

    private String getDisplayName() {
        if(displayName == null) {
            ItemStack itemStack = new ItemStack(item);
            ItemMeta itemMeta = itemStack.getItemMeta();
            return itemMeta.getDisplayName();
        }
        return displayName;
    }
    private int getAmount() {
        if(amount == null || amount == 0) return 1;
        return Math.abs(amount);
    }
    public Integer getSlot() {
        return slot;
    }
    private List<Enchant> getEnchants() {
        return enchants;
    }
    private List<ItemFlag> getItemFlags() {
        return itemFlags;
    }
    private boolean getUnbreakable() {
        if(unbreakable == null) return true;
        return unbreakable;
    }
    public ItemStack asItemStack() {
        if(item == null) return null;
        ItemStack itemStack = new ItemStack(item, getAmount());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getDisplayName());
        if(getEnchants() != null && !getEnchants().isEmpty()) {
            getEnchants().forEach(enchant -> itemMeta.addEnchant(enchant.getEnchantment(), enchant.getLevel(), true));
        }
        if(getItemFlags() != null && !getItemFlags().isEmpty()) {
            getItemFlags().forEach(itemMeta::addItemFlags);
        }
        itemMeta.setUnbreakable(getUnbreakable());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
