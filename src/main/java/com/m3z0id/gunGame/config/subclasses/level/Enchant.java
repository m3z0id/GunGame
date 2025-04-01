package com.m3z0id.gunGame.config.subclasses.level;

import org.bukkit.enchantments.Enchantment;

public class Enchant {
    private Enchantment enchantment;
    private Integer level;

    public int getLevel(){
        if(level == null || level == 0) return 1;
        return Math.abs(level);
    }
    public Enchantment getEnchantment(){
        return enchantment;
    }
}
