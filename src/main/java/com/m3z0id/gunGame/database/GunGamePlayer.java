package com.m3z0id.gunGame.database;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.subclasses.LevelEntry;
import com.m3z0id.gunGame.config.subclasses.level.ItemEntry;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;

public class GunGamePlayer {
    Player player;
    public GunGamePlayer(Player player) {
        this.player = player;
    }
    public int getKillCount(){
        return GunGame.database.getPlayerKills(player.getName());
    }
    public int getDeathCount(){
        return GunGame.database.getPlayerDeaths(player.getName());
    }
    public int getCurrentLevel(){
        return GunGame.levels.getOrDefault(player.getName(), 1);
    }
    public int getHighestLevel(){
        return GunGame.database.getPlayerHighestLevel(player.getName());
    }

    public void addKill(){
        addLevel();
        addKills(1);
    }
    public void addKills(int amount){
        setKills(getKillCount() + amount);
    }
    public void setKills(int amount){
        GunGame.database.setPlayerKills(player.getName(), amount);
    }

    public void addDeath(){
        setLevels(getCurrentLevel() - 1 == 0 ? 1 : (int) (double) (getCurrentLevel() - (getCurrentLevel() * GunGame.config.getLossPercentage() / 100)));
        addDeaths(1);
    }
    public void addDeaths(int amount){
        setDeaths(getDeathCount() + amount);
    }
    public void setDeaths(int amount){
        GunGame.database.setPlayerDeaths(player.getName(), amount);
    }

    public void addHighestLevel(){
        addHighestLevels(1);
    }
    public void addHighestLevels(int amount){
        setHighestLevel(getHighestLevel() + amount);
    }
    public void setHighestLevel(int amount){
        GunGame.database.setPlayerHighestLevel(player.getName(), amount);
    }

    public void addLevel(){
        addLevels(1);

        player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(15, 5));
        player.removePotionEffect(PotionEffectType.SLOWNESS);
        if(getCurrentLevel() >= 55){
            player.addPotionEffect(PotionEffectType.SLOWNESS.createEffect(Integer.MAX_VALUE, 0));
        }
    }
    public void addLevels(int amount){
        setLevels(getCurrentLevel() + amount);
    }
    public void setLevels(int amount){
        if(GunGame.levels == null) GunGame.levels = new HashMap<>();
        GunGame.levels.put(player.getName(), amount);
        if(getHighestLevel() < GunGame.levels.get(player.getName())) setHighestLevel(GunGame.levels.get(player.getName()));
        applyKit(GunGame.itemLevels.getLevels().get(Math.min(amount-1, GunGame.itemLevels.getLevels().size()-1)));
        player.setExp(1F);
        player.setLevel(amount);
    }

    public void applyKit(LevelEntry entry){
        player.removePotionEffect(PotionEffectType.SLOWNESS);
        player.getInventory().clear();
        player.getInventory().setArmorContents(entry.getArmor());
        if(entry.getInventory() != null && !Arrays.asList(entry.getInventory()).isEmpty()){
            for (ItemEntry itemEntry : entry.getInventory()) {
                player.getInventory().setItem(itemEntry.getSlot(), itemEntry.asItemStack());
            }
        }
        player.getInventory().setItem(0, entry.getWeapon());
        player.getInventory().setItem(GunGame.config.getShopInventoryItem().getSlot(), GunGame.config.getShopInventoryItem().getItem());
    }

    public Player getPlayer() {
        return player;
    }
    public static GunGamePlayer fromPlayer(Player player) {
        if (player == null) return null;
        return new GunGamePlayer(player);
    }
}
