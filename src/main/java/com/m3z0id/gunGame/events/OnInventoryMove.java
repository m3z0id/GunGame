package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnInventoryMove implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getLocation().getWorld() != GunGame.currentWorld) return;
        if(event.getWhoClicked().getGameMode() == GameMode.SPECTATOR || event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(event.getWhoClicked().getWorld() != GunGame.currentWorld) return;
        if(event.getWhoClicked().getGameMode() == GameMode.SPECTATOR || event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(event.getPlayer().getWorld() != GunGame.currentWorld) return;
        if(event.getPlayer().getGameMode() == GameMode.SPECTATOR || event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
    }
}
