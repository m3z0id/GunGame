package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.serializers.MaterialSerializer;
import com.m3z0id.gunGame.config.serializers.PotionEffectTypeSerializer;
import com.m3z0id.gunGame.config.serializers.SoundSerializer;
import com.m3z0id.gunGame.config.subclasses.config.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class Config {
    int lossPercentage;
    int mapChangeInterval;
    KillEvent killEvent;
    SoundEntry soundOnBuy;
    SoundEntry soundOnLevelup;
    List<String> worlds;
    ShopInventoryItem shopInventoryItem;
    Shop shop;
    List<Buff> buffs;

    public KillEvent getKillEvent() {
        return killEvent;
    }
    public int getMapChangeInterval() {
        return mapChangeInterval;
    }
    public int getLossPercentage(){
        return lossPercentage;
    }
    public List<String> getWorldNames() {
        return worlds;
    }
    public ShopInventoryItem getShopInventoryItem() {
        return shopInventoryItem;
    }
    public Shop getShop() {
        return shop;
    }
    public List<Buff> getBuffs() {
        return buffs;
    }
    public SoundEntry getSoundOnBuy() {
        return soundOnBuy;
    }
    public SoundEntry getSoundOnLevelup() {
        return soundOnLevelup;
    }

    public static Config loadConfig() {
        File conf = new File(GunGame.instance.getDataFolder(), "config.json");
        if(!conf.exists()) {
            try {
                Files.copy(Objects.requireNonNull(GunGame.class.getResourceAsStream("/config.json")), conf.toPath());
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to drop config file.");
                return null;
            }
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PotionEffectType.class, new PotionEffectTypeSerializer())
                .registerTypeAdapter(Material.class, new MaterialSerializer())
                .registerTypeAdapter(Sound.class, new SoundSerializer())
                .create();

        try(Reader reader = new FileReader(conf)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Failed to load config.json and a backup, " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(GunGame.instance);
        }
        return null;
    }
}