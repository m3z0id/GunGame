package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
        if(player.getWorld() != GunGame.currentWorld) return;
        if(player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE) return;

        player.getInventory().clear();
        player.setExp(0);
        player.setLevel(0);

        if(GunGame.itemLevels != null && !GunGame.itemLevels.getLevels().isEmpty()) GunGamePlayer.fromPlayer(player).applyKit(GunGame.itemLevels.getLevels().get(0));

        if(!GunGame.database.playerExists(player.getName())) GunGame.database.addPlayer(player.getName());
    }
}
