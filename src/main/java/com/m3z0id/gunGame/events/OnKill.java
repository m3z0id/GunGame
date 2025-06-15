package com.m3z0id.gunGame.events;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnKill implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        if(e.getEntity().getWorld() != GunGame.currentWorld) return;
        if(e.getEntity().getGameMode() == GameMode.SPECTATOR || e.getEntity().getGameMode() == GameMode.CREATIVE) return;
        e.setCancelled(true);
        Player p = e.getEntity();
        int deadPrevLevel = GunGamePlayer.fromPlayer(p).getCurrentLevel();
        p.setHealth(20);
        GunGamePlayer.fromPlayer(p).addDeath();
        p.teleport(GunGame.currentWorld.getSpawnLocation());

        if(p.getKiller() == null) {
            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getWaterDeathMessage()).decoration(TextDecoration.ITALIC, false));
            return;
        }

        Player killer = p.getKiller();
        GunGame.instance.economy.depositPlayer(killer, 1);

        int killerPrevLevel = GunGamePlayer.fromPlayer(killer).getCurrentLevel();

        if(GunGame.config.getSoundOnLevelup() != null){
            killer.playSound(killer.getLocation(), GunGame.config.getSoundOnLevelup().getSound(), GunGame.config.getSoundOnLevelup().getVolume(), GunGame.config.getSoundOnLevelup().getPitch());
        }

        p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getDeathMessage().replaceAll("%killerName%", killer.getName()).replaceAll("%level%", String.valueOf(killerPrevLevel))).decoration(TextDecoration.ITALIC, false));
        killer.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getKillMessage().replaceAll("%deadName%", p.getName()).replaceAll("%level%", String.valueOf(deadPrevLevel))).decoration(TextDecoration.ITALIC, false));

        p.teleport(GunGame.currentWorld.getSpawnLocation());
        p.getActivePotionEffects().forEach(potionEffect -> {
            p.removePotionEffect(potionEffect.getType());
        });

        GunGamePlayer.fromPlayer(killer).addKill();
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
