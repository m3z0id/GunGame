package com.m3z0id.gunGame.shop;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.subclasses.config.ShopItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShopInventory implements InventoryHolder {
    public List<CustomItem> items = new ArrayList<>();
    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, GunGame.config.getShop().getSize(), ChatColor.translateAlternateColorCodes('&', GunGame.config.getShop().getTitle()));
        for(ShopItem item : GunGame.config.getShop().getItems()){
            CustomItem customItem = new CustomItem(item, 1);
            items.add(customItem);
            inventory.setItem(customItem.getSlot(), customItem);
        }
        return inventory;
    }
}
