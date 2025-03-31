package com.m3z0id.gunGame;

import com.m3z0id.gunGame.kit.Kits;
import com.m3z0id.gunGame.utils.Saver;
import com.m3z0id.gunGame.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Updater {
    public static void updatePlayer(UUID uuid, int level) {
        Player player = Bukkit.getPlayer(uuid);
        if(player == null) {
            return;
        }

        player.removePotionEffect(PotionEffectType.SLOW);

        player.getInventory().clear();
        player.setLevel(level);
        player.setExp(1F);
        player.getInventory().setArmorContents(Utils.reverseArray(Kits.KITS.get(Math.min(Kits.KITS.size()-1, level-1)).getArmor().toArray(new ItemStack[4])));
        player.getInventory().setItem(0, Kits.KITS.get(Math.min(Kits.KITS.size()-1, level-1)).getWeapon());

        player.getInventory().setItem(GunGame.config.getShopInventoryItem().getSlot(), GunGame.config.getShopInventoryItem().getItem());

        if(GunGame.levels.getOrDefault(player.getName(), 1) > GunGame.records.getOrDefault(player.getName(), 1)) {
            GunGame.records.put(player.getName(), GunGame.levels.getOrDefault(player.getName(), 1));
            Saver.saveMap(GunGame.records, GunGame.instance.recordsFile);
        }

        if(level >= 55){
            player.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 0));
        }
    }
}
