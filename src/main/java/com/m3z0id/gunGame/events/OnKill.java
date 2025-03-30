package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.Updater;
import com.m3z0id.gunGame.utils.Saver;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class OnKill implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        if(e.getEntity().getWorld() != GunGame.currentWorld) return;
        if(e.getEntity().getGameMode() == GameMode.SPECTATOR || e.getEntity().getGameMode() == GameMode.CREATIVE) return;
        e.setCancelled(true);
        Player p = e.getEntity();

        p.setHealth(20);
        GunGame.deaths.put(p.getName(), GunGame.deaths.getOrDefault(p.getName(), 0) + 1);
        Saver.saveMap(GunGame.deaths, GunGame.instance.deathFile);
        GunGame.levels.put(p.getName(), GunGame.levels.getOrDefault(p.getName(), 1) - 1 == 0 ? 1 : (int) Math.floor((double) GunGame.levels.getOrDefault(p.getName(), 1) - ((double) (GunGame.levels.getOrDefault(p.getName(), 1) * GunGame.config.getLossPercentage()) / 100)));
        Updater.updatePlayer(p.getUniqueId(), GunGame.levels.get(p.getName()));

        p.teleport(GunGame.currentWorld.getSpawnLocation());
        p.getActivePotionEffects().forEach(potionEffect -> {
            p.removePotionEffect(potionEffect.getType());
        });

        if(p.getKiller() == null) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getWaterDeathMessage()));
            return;
        }

        Player killer = p.getKiller();
        GunGame.instance.economy.depositPlayer(killer, 1);

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getDeathMessage().replaceAll("%killerName%", killer.getName()).replaceAll("%level%", String.valueOf(killer.getLevel()))));
        killer.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getKillMessage().replaceAll("%deadName%", p.getName()).replaceAll("%level%", String.valueOf(p.getLevel()))));
        p.teleport(GunGame.currentWorld.getSpawnLocation());
        p.getActivePotionEffects().forEach(potionEffect -> {
            p.removePotionEffect(potionEffect.getType());
        });
        GunGame.kills.put(killer.getName(), GunGame.kills.getOrDefault(killer.getName(), 0) + 1);
        Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);
        killer.removePotionEffect(PotionEffectType.SLOW);
        killer.addPotionEffect(PotionEffectType.REGENERATION.createEffect(15, 5));
        GunGame.levels.put(killer.getName(), GunGame.levels.getOrDefault(killer.getName(), 1) + 1);
        if(GunGame.levels.getOrDefault(killer.getName(), 1) > GunGame.records.getOrDefault(killer.getName(), 1)) {
            GunGame.records.put(killer.getName(), GunGame.levels.get(killer.getName()));
            Saver.saveMap(GunGame.records, GunGame.instance.recordsFile);
        }
        Updater.updatePlayer(killer.getUniqueId(), GunGame.levels.get(killer.getName()));
    }
    /*@EventHandler
    public void onKillTemp(EntityDeathEvent e) {
        if(e.getEntity().getWorld() != GunGame.currentLocation.getWorld()) return;
        if(e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        e.setDroppedExp(0);

        killer.removePotionEffect(PotionEffectType.SLOW);
        GunGame.instance.economy.depositPlayer(killer, 1);

        GunGame.kills.put(killer.getName(), GunGame.kills.getOrDefault(killer.getName(), 0) + 1);
        Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);

        killer.addPotionEffect(PotionEffectType.REGENERATION.createEffect(15, 5));
        GunGame.levels.put(killer.getName(), GunGame.levels.getOrDefault(killer.getName(), 1) + 1);
        Updater.updatePlayer(killer.getUniqueId(), GunGame.levels.get(killer.getName()));
    }*/
}
