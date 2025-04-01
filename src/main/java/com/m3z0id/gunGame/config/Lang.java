package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.m3z0id.gunGame.GunGame;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Objects;

public class Lang {
    String serverPrefix;
    String waterDeathMessage;
    String deathMessage;
    String killMessage;
    String insufficientFundsMessage;
    String successfulPurchaseMessage;
    String priceLore;
    String invalidSubcommand;
    String successMessage;

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
    public String getServerPrefix() {
        return serverPrefix;
    }

    public static Lang loadLang(){
        File langFile = new File(GunGame.instance.getDataFolder(), "lang.json");
        if (!langFile.exists()) {
            try {
                Files.copy(Objects.requireNonNull(GunGame.class.getResourceAsStream("/lang.json")), langFile.toPath());
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to drop lang file.");
                return null;
            }
        }
        Gson gson = new Gson();
        try (Reader reader = new FileReader(langFile)) {
            return gson.fromJson(reader, Lang.class);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to load lang file.");
            return null;
        }
    }
}
