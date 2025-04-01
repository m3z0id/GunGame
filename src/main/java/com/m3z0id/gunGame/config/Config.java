package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.serializers.MaterialSerializer;
import com.m3z0id.gunGame.config.serializers.PotionEffectTypeSerializer;
import com.m3z0id.gunGame.config.subclasses.config.Shop;
import com.m3z0id.gunGame.config.subclasses.config.ShopInventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class Config {
    int lossPercentage;
    int mapChangeInterval;
    List<String> worlds;
    ShopInventoryItem shopInventoryItem;
    Shop shop;

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