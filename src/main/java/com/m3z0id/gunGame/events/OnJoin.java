package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        add(player);
    }
    @EventHandler
    public void onJoin(PlayerChangedWorldEvent event) {
        add(event.getPlayer());
    }

    @EventHandler
    public void onSwitchGamemode(PlayerGameModeChangeEvent event){
        add(event.getPlayer());
    }

    private void add(Player player) {
        if(player.getWorld() != GunGame.currentWorld){
            GunGame.levels.remove(player.getName());
            return;
        }

        if(GunGame.levels == null) GunGame.levels = new HashMap<>();

        GunGamePlayer ggplayer = GunGamePlayer.fromPlayer(player);
        ggplayer.setLevels(1);

        if(!GunGame.database.playerExists(player.getName())) GunGame.database.addPlayer(player.getName());
    }
}
