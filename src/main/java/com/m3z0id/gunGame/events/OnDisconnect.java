package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnDisconnect implements Listener {
    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        GunGame.levels.remove(e.getPlayer().getName());
    }
}
