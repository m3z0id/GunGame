package com.m3z0id.gunGame.config.subclasses;

import com.m3z0id.gunGame.config.subclasses.level.ItemEntry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class LevelEntry {
    private ItemEntry helmet;
    private ItemEntry chestplate;
    private ItemEntry leggings;
    private ItemEntry boots;
    private ItemEntry weapon;
    private ItemEntry[] inventory;

    public ItemStack[] getArmor() {
        ItemStack helmetStack = helmet == null ? null : helmet.asItemStack();
        ItemStack chestplateStack = chestplate == null ? null : chestplate.asItemStack();
        ItemStack leggingsStack = leggings == null ? null : leggings.asItemStack();
        ItemStack bootsStack = boots == null ? null : boots.asItemStack();
        return new ItemStack[]{bootsStack, leggingsStack, chestplateStack, helmetStack};
    }
    public ItemStack getWeapon(){
        if(weapon == null) return null;
        return weapon.asItemStack();
    }
    public ItemEntry[] getInventory() {
        return inventory;
    }
}
