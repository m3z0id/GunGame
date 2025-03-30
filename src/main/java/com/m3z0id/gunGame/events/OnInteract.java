package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.shop.ShopInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnInteract implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item.equals(GunGame.config.getShopInventoryItem().getItem())){
            event.setCancelled(true);
            ShopInventory shopInventory = new ShopInventory();
            player.openInventory(shopInventory.getInventory());
        }
    }
}
