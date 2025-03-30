package com.m3z0id.gunGame.shop;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.subclasses.Effect;
import com.m3z0id.gunGame.config.subclasses.ShopItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class CustomItem extends ItemStack {
    List<PotionEffect> effects = new ArrayList<>();
    int price;
    int slot;
    public CustomItem(ShopItem shopItem, int amount) {
        super(shopItem.getItem(), amount);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', shopItem.getName()));
        meta.setLore(List.of("", ChatColor.translateAlternateColorCodes('&', GunGame.config.getPriceLore().replaceAll("%price%", String.valueOf(shopItem.getCost())))));
        this.setItemMeta(meta);
        for(Effect effect : shopItem.getEffects()) {
            effects.add(effect.getEffect().createEffect(effect.getDuration()*20, effect.getLevel()));
        }
        this.price = shopItem.getCost();
        this.slot = shopItem.getSlot();
    }
    public List<PotionEffect> getEffects() {
        return effects;
    }
    public int getPrice() {
        return price;
    }
    public int getSlot() {
        return slot;
    }
    public ItemStack asItemStack() {
        return this;
    }
    public boolean equalsItemStack(CustomItem customItem) {
        return customItem.asItemStack().equals(asItemStack());
    }
    public boolean equalsItemStack(ItemStack itemStack) {
        return itemStack.equals(asItemStack());
    }
}
