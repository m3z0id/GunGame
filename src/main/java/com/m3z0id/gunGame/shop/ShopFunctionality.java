package com.m3z0id.gunGame.shop;

import com.m3z0id.gunGame.GunGame;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopFunctionality implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(!(event.getClickedInventory().getHolder() instanceof ShopInventory holder)) return;
        if(event.getCurrentItem() == null) return;

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        ItemStack currentItem = event.getCurrentItem();

        for(CustomItem item : holder.items) {;
            if(currentItem.toString().equals(item.asItemStack().toString())) {
                if(GunGame.instance.economy.getBalance(player) < item.getPrice()){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.lang.getServerPrefix() + GunGame.lang.getInsufficientFundsMessage()));
                    player.closeInventory();

                } else {
                    GunGame.instance.economy.withdrawPlayer(player, item.getPrice());
                    player.addPotionEffects(item.getEffects());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessfulPurchaseMessage()));
                    player.closeInventory();
                }
                break;
            }
        }
    }
}
