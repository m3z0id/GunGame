package com.m3z0id.gunGame.shop;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.subclasses.config.Effect;
import com.m3z0id.gunGame.config.subclasses.config.ShopItem;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
        super(shopItem.getItem().createItemStack());
        this.setAmount(amount);

        ItemMeta meta = this.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize(shopItem.getName()).decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(MiniMessage.miniMessage().deserialize(GunGame.lang.getPriceLore().replaceAll("%price%", String.valueOf(shopItem.getCost()))).decoration(TextDecoration.ITALIC, false)));
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
}
