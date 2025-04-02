package com.m3z0id.gunGame;

import com.m3z0id.gunGame.config.Stats;
import com.m3z0id.gunGame.config.subclasses.level.ItemEntry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Updater {
    public static void updatePlayer(UUID uuid, int level) {
        Player player = Bukkit.getPlayer(uuid);
        if(player == null) {
            return;
        }

        if(GunGame.records == null) GunGame.records = new HashMap<>();
        if(GunGame.levels == null) GunGame.levels = new HashMap<>();
        if(GunGame.kills == null) GunGame.kills = new HashMap<>();
        if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();

        player.removePotionEffect(PotionEffectType.SLOW);

        player.getInventory().clear();
        player.setLevel(level);
        player.setExp(1F);
        player.getInventory().setArmorContents(GunGame.itemLevels.getLevels().get(Math.min(level-1, GunGame.itemLevels.getLevels().size()-1)).getArmor());
        ItemEntry[] inv = GunGame.itemLevels.getLevels().get(Math.min(level - 1, GunGame.itemLevels.getLevels().size() - 1)).getInventory();

        if(inv != null){
            if(!Arrays.asList(inv).isEmpty()){
                for (ItemEntry itemEntry : inv) {
                    player.getInventory().setItem(itemEntry.getSlot(), itemEntry.asItemStack());
                }
            }
        }
        player.getInventory().setItem(0, GunGame.itemLevels.getLevels().get(Math.min(level-1, GunGame.itemLevels.getLevels().size()-1)).getWeapon());

        player.getInventory().setItem(GunGame.config.getShopInventoryItem().getSlot(), GunGame.config.getShopInventoryItem().getItem());

        if(GunGame.levels.getOrDefault(player.getName(), 1) > GunGame.records.getOrDefault(player.getName(), 1)) {
            GunGame.records.put(player.getName(), GunGame.levels.getOrDefault(player.getName(), 1));
            Stats.saveStat(GunGame.instance.recordsFile, GunGame.records);
        }

        if(level >= 55){
            player.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 0));
        }
    }
}
