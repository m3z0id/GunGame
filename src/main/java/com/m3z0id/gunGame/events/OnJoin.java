package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.Updater;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerChangedWorldEvent event) {
        if(event.getPlayer().getWorld() != GunGame.currentWorld) return;
        if(event.getPlayer().getGameMode() == GameMode.SPECTATOR || event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        Player player = event.getPlayer();

        doStuff(player);
    }
    @EventHandler
    public void onSwitchGamemode(PlayerGameModeChangeEvent event){
        if(event.getPlayer().getWorld() != GunGame.currentWorld) return;
        if(event.getPlayer().getGameMode() == GameMode.SPECTATOR || event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        Player player = event.getPlayer();

        doStuff(player);
    }

    private void doStuff(Player player) {
        GunGame.levels.put(player.getName(), 1);
        if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();
        GunGame.deaths.putIfAbsent(player.getName(), 0);
        if(GunGame.kills == null) GunGame.kills = new HashMap<>();
        GunGame.kills.putIfAbsent(player.getName(), 0);

        player.setExp(0);
        player.setLevel(0);
        player.getActivePotionEffects().forEach(potionEffect -> {
            player.removePotionEffect(potionEffect.getType());
        });

        Updater.updatePlayer(player.getUniqueId(), GunGame.levels.get(player.getName()));
    }
}
