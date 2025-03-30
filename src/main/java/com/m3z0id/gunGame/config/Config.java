package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.serializers.MaterialSerializer;
import com.m3z0id.gunGame.config.serializers.PotionEffectTypeSerializer;
import com.m3z0id.gunGame.config.subclasses.Shop;
import com.m3z0id.gunGame.config.subclasses.ShopInventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.util.List;

public class Config {
    String serverPrefix;
    int lossPercentage;
    int mapChangeInterval;
    String waterDeathMessage;
    String deathMessage;
    String killMessage;
    String insufficientFundsMessage;
    String successfulPurchaseMessage;
    String priceLore;
    String invalidSubcommand;
    String successMessage;
    List<String> worlds;
    ShopInventoryItem shopInventoryItem;
    Shop shop;

    public Config() {

    }

    public String getServerPrefix() {
        return serverPrefix;
    }
    public int getMapChangeInterval() {
        return mapChangeInterval;
    }
    public String getWaterDeathMessage() {
        return waterDeathMessage;
    }
    public String getDeathMessage() {
        return deathMessage;
    }
    public String getKillMessage() {
        return killMessage;
    }
    public String getInsufficientFundsMessage() {
        return insufficientFundsMessage;
    }
    public String getSuccessfulPurchaseMessage() {
        return successfulPurchaseMessage;
    }
    public String getPriceLore(){
        return priceLore;
    }
    public String getInvalidSubcommand() {
        return invalidSubcommand;
    }
    public String getSuccessMessage() {
        return successMessage;
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PotionEffectType.class, new PotionEffectTypeSerializer())
                .registerTypeAdapter(Material.class, new MaterialSerializer())
                .create();

        try(InputStream inputStream = new FileInputStream(conf)) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuilder.append(line).append(System.lineSeparator());
                }
            }
            return gson.fromJson(stringBuilder.toString(), Config.class);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Failed to load config.json and a backup, " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(GunGame.instance);
        }
        return null;
    }
}