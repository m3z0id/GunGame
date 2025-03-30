package com.m3z0id.gunGame.kit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;

public class KitCreator {
    private final ItemStack helmet;
    private final ItemStack chestplate;
    private final ItemStack leggings;
    private final ItemStack boots;
    private final ItemStack weapon;

    public KitCreator(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack weapon) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.weapon = weapon;

        if(helmet != null) {
            ItemMeta helmetMeta = this.helmet.getItemMeta();
            helmetMeta.setUnbreakable(true);
            this.helmet.setItemMeta(helmetMeta);
        }
        if(chestplate != null) {
            ItemMeta chestplateMeta = this.chestplate.getItemMeta();
            chestplateMeta.setUnbreakable(true);
            this.chestplate.setItemMeta(chestplateMeta);
        }
        if(leggings != null) {
            ItemMeta leggingsMeta = this.leggings.getItemMeta();
            leggingsMeta.setUnbreakable(true);
            this.leggings.setItemMeta(leggingsMeta);
        }
        if(boots != null) {
            ItemMeta bootsMeta = this.boots.getItemMeta();
            bootsMeta.setUnbreakable(true);
            this.boots.setItemMeta(bootsMeta);
        }
        if(weapon != null) {
            ItemMeta weaponMeta = this.weapon.getItemMeta();
            weaponMeta.setUnbreakable(true);
            this.weapon.setItemMeta(weaponMeta);
        }
    }

    public ItemStack getHelmet() {
        return helmet;
    }
    public ItemStack getChestplate() {
        return chestplate;
    }
    public ItemStack getLeggings() {
        return leggings;
    }
    public ItemStack getBoots() {
        return boots;
    }
    public ItemStack getWeapon(){
        return weapon;
    }

    public Collection<ItemStack> getArmor(){
        Collection<ItemStack> armor = new ArrayList<>();
        armor.add(helmet);
        armor.add(chestplate);
        armor.add(leggings);
        armor.add(boots);
        return armor;
    }
    public Collection<ItemStack> getKitItems() {
        Collection<ItemStack> kitItems = new ArrayList<>();
        kitItems.add(helmet);
        kitItems.add(chestplate);
        kitItems.add(leggings);
        kitItems.add(boots);
        kitItems.add(weapon);
        return kitItems;
    }
}
