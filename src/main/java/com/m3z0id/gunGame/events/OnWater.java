package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnWater implements Listener {
    @EventHandler
    public void onWater(PlayerMoveEvent event) {
        if(event.getPlayer().getWorld() != GunGame.currentWorld) return;
        if(event.getPlayer().getGameMode() == GameMode.SPECTATOR || event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if(event.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
            Player player = event.getPlayer();
            player.setHealth(0);
        }
    }
}
