package com.m3z0id.gunGame.Utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CustomItemStack {
    public static ItemStack newstack(@NotNull ItemStack itemStack, @NotNull Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return itemStack;
    }
    public static ItemStack newstack(@NotNull ItemStack itemStack, @NotNull Enchantment enchantment) {
        return newstack(itemStack, enchantment, 1);
    }
}
