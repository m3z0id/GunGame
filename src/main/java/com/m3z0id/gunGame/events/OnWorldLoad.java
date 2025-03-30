package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import java.util.ArrayList;

public class OnWorldLoad implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        String worldName = event.getWorld().getName();
        for(String entry : GunGame.config.getWorldNames()){
            if(worldName.equals(entry)){
                if(GunGame.instance.loadedWorlds == null) GunGame.instance.loadedWorlds = new ArrayList<>();
                GunGame.instance.loadedWorlds.add(event.getWorld());
                GunGame.instance.update(GunGame.instance.loadedWorlds);
            }
        }
    }
}
